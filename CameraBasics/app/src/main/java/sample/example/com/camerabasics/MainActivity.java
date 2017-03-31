package sample.example.com.camerabasics;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {


    Button readButton;
    Map<String,Integer> map ;
    String TAG = "VokelProject";
    int REQUEST_CODE =100;
    ArrayList<Object> words = new ArrayList<>();
    private RecyclerView recyclerView;
    private FrequencyAdapter mAdapter;
    private  int REQUEST_PERMISSION_SETTING  = 10;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 100:
                if (resultCode == RESULT_OK) {
                    Uri uri = data.getData();
                    try {
                        String path = getPath(this, uri);
                        File file = new File(path);
                        String content = readFile(file);
                        content = content.replace(".","");
                        content = content.replace(",","");
                        String[] string_array = content.split("\\s");
                        map = convertTohMap(string_array);
                        map = sortByValue(map);
                        words = convertToArrayList(map);
                        mAdapter = new FrequencyAdapter(MainActivity.this,words);
                        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                        recyclerView.setLayoutManager(mLayoutManager);
                        recyclerView.setAdapter(mAdapter);

                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;

            case 10:
                if(AppUtils.checkStoragePermission(MainActivity.this)) {
                    chooseFile();
                    break;
                }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public static String getPath(Context context, Uri uri) throws URISyntaxException {
        if ("content".equalsIgnoreCase(uri.getScheme())) {
            String[] projection = { "_data" };
            Cursor cursor = null;

            try {
                cursor = context.getContentResolver().query(uri, projection, null, null, null);
                int column_index = cursor.getColumnIndexOrThrow("_data");
                if (cursor.moveToFirst()) {
                    return cursor.getString(column_index);
                }
            } catch (Exception e) {
                // Eat it
            }
        }
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = (RecyclerView) findViewById(R.id.recycle);
        readButton = (Button) findViewById(R.id.getExternalStorage);
        readButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> listPermissionsNeeded = new ArrayList<>();

                if(AppUtils.isExternalStorageAvailable()){
                   if(AppUtils.checkStoragePermission(MainActivity.this)){
                       chooseFile();
                   }else{
                       listPermissionsNeeded.add(Manifest.permission.READ_EXTERNAL_STORAGE);
                        AppUtils.requestPermission(MainActivity.this,listPermissionsNeeded);
                   }
                }

            }
        });



    }

    private void chooseFile(){
        Intent chooser = new Intent(Intent.ACTION_GET_CONTENT);
        Uri uri = Uri.parse(Environment.getDownloadCacheDirectory().getPath().toString());
        chooser.addCategory(Intent.CATEGORY_OPENABLE);
        chooser.setDataAndType(uri, "text/csv");

        try {
            startActivityForResult(chooser, 100);
        }
        catch (android.content.ActivityNotFoundException ex)
        {
            Toast.makeText(getApplicationContext(), "Please install a File Manager.",
                    Toast.LENGTH_SHORT).show();
        }
    }




    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case 1: {

                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    chooseFile();

                } else {
                    if (checkIfPermissiondeniedForever(Manifest.permission.READ_EXTERNAL_STORAGE)) {

                        if (AppUtils.getFromSharedPrefs(MainActivity.this, "deniedPermission").equals("true")) {
                            AppUtils.showMessageOKCancel(this, "You need to allow access to external storage",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                            Uri uri = Uri.fromParts("package", getPackageName(), null);
                                            intent.setData(uri);
                                            startActivityForResult(intent, REQUEST_PERMISSION_SETTING);
                                        }
                                    });
                        }
                        AppUtils.saveInSharedPrefs(MainActivity.this, "deniedPermission", "true");
                    }else {
                        Toast.makeText(MainActivity.this, "Permission denied to read External storage", Toast.LENGTH_SHORT).show();
                    }
                }
                return;
            }

        }

    }

    public boolean checkIfPermissiondeniedForever(String permissions){
        return !ActivityCompat.shouldShowRequestPermissionRationale(this, permissions);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void checkReadStoragePermission(){
        if (checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            chooseFile();

        }
        else{
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE);
        }
    }




    private  String readFile(File file) throws IOException {
        try {

            FileInputStream fis = null;
            String str = "";

            try {
                fis = new FileInputStream(file);
                int content;
                while ((content = fis.read()) != -1) {
                    // convert to char and display it
                    str += (char) content;
                }

               return str;

            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static <K, V> Map<K, V> sortByValue(Map<K, V> map) {
        List<Map.Entry<K, V>> list = new LinkedList<>(map.entrySet());
        Collections.sort(list, new Comparator<Object>() {
            @SuppressWarnings("unchecked")
            public int compare(Object o1, Object o2) {
                return ((Comparable<V>) ((Map.Entry<K, V>) (o1)).getValue()).compareTo(((Map.Entry<K, V>) (o2)).getValue());
            }
        });

        Map<K, V> result = new LinkedHashMap<>();
        for (Iterator<Map.Entry<K, V>> it = list.iterator(); it.hasNext();) {
            Map.Entry<K, V> entry = (Map.Entry<K, V>) it.next();
            result.put(entry.getKey(), entry.getValue());
        }

        return result;
    }

    private Map<String, Integer> convertTohMap(String[] array){
        Map<String,Integer> map = new HashMap<>();
        for(int i=0;i<array.length;i++){
            array[i] = array[i].toLowerCase();
            if(array[i].length()!=0) {
                if (map.containsKey(array[i])) {
                    int value = map.get(array[i]);
                    map.put(array[i], ++value);

                } else {
                    map.put(array[i], 1);
                }
            }


        }
        return map;
    }

    private ArrayList<Object> convertToArrayList(Map<String, Integer> map){
        ArrayList<Object> words = new ArrayList<>();
        int size = map.size();
        int q = size/10;
        int count=0;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {

            if(count==q*10){
                words.add(""+(count)+"-"+(size));
            }
            else if(count%10==0){
                words.add(""+(count)+"-"+(count+10));
            }
            count++;
            String key = entry.getKey();
            Integer value = entry.getValue();
            Word word = new Word(value,key);
            words.add(word);

        }

        return words;

    }





}

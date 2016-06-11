package com.example.samplemusic;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.example.samplemusic.Utils.CSVFile;
import com.example.samplemusic.Utils.DbHelper;
import com.example.samplemusic.databinding.ActivityMainBinding;
import com.example.samplemusic.models.Song;
import com.example.samplemusic.viewModels.FirstModel;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<Song> songsList;
    private static String TAG = "MainActivity";
    public static  HashMap<String,List<Song>> map1;
    public static HashMap<String ,List<Song>> map2;
    public static HashMap<String,List<List<Song>>> map1List;
    public static ArrayList<String > artistList;
    public static ArrayList<String > albumtList;
    public CustomAdapter adp;
    ListView listView;
    int sizeOfArray = 2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding  binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        FirstModel firstModel = new FirstModel();
        binding.setFirstModel(firstModel);
        listView = (ListView)findViewById(R.id.listView);

        InputStream inputStream = getResources().openRawResource(R.raw.sample_music_data);
        CSVFile csvFile = new CSVFile(inputStream);
        List scoreList = csvFile.read();
        songsList = new ArrayList<>();


        for(int i=1;i<scoreList.size();i++){
            String[] strings = (String[])scoreList.get(i);
            Song song123 = new Song();
            song123.setSongName(strings[0]);
            song123.setArtistName(strings[1]);
            song123.setAlbumName(strings[2]);

            songsList.add(song123);


        }

        ArtistVsSongs(songsList);
        getUnigeListArtist();
        adp = new CustomAdapter(this,getMApList(artistList.get(0)));
        listView.setAdapter(adp);
        AlbumVsSongs(songsList);
        getUnigeListAlbum();
        getUnigeListArtist();

    }
    public void ArtistVsSongs(List<Song> list){
        map1 = new HashMap<>();
        List<Song> dummyList = new ArrayList<>();
        for(int i =0;i<list.size();i++){
            if(map1.containsKey(list.get(i).getArtistName())) {
                dummyList = map1.get(list.get(i).getArtistName());
                dummyList.add(list.get(i));
            }
            else{
            dummyList = new ArrayList<>();
            dummyList.add(list.get(i));}
            map1.put(list.get(i).getArtistName(), dummyList);



        }


    }
    public List<List<Song>> getMApList(String album){
        List<Song>songs = map1.get(album);

            List<List<Song>> smallLists = split(songs,3);


        return smallLists;
    }



    public static <T extends Object> List<List<T>> split(List<T> list, int targetSize) {
        List<List<T>> lists = new ArrayList<List<T>>();
        for (int i = 0; i < list.size(); i += targetSize) {
            lists.add(list.subList(i, Math.min(i + targetSize, list.size())));
        }
        return lists;
    }

    public ArrayList<String> getUnigeListArtist(){
        artistList = new ArrayList<>();
        for(String key : map1.keySet()){
            artistList.add(key);
        }
        return artistList;
    }

    public ArrayList<String> getUnigeListAlbum(){
        albumtList = new ArrayList<>();
        for(String key : map2.keySet()){
            albumtList.add(key);
        }
        return albumtList;
    }

    public void AlbumVsSongs(ArrayList<Song> list){
        map2 = new HashMap<>();
        List<Song> dummyList2 = new ArrayList<>();
        for(int i =0;i<list.size();i++){
            if(map2.containsKey(list.get(i).getAlbumName())) {
                dummyList2 = map2.get(list.get(i).getAlbumName());
                dummyList2.add(list.get(i));
            }
            else{
                dummyList2 = new ArrayList<>();
                dummyList2.add(list.get(i));}
            map2.put(list.get(i).getAlbumName(),dummyList2);
        }

    }



//    public void addFragmentA(){
//        FirstFragment firstFragment = new FirstFragment();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.add(R.id.myfragment,firstFragment,"firstFragment");
//        fragmentTransaction.commit();
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

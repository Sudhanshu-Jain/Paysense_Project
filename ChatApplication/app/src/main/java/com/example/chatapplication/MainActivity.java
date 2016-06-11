package com.example.chatapplication;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText username_edit;
    Button submit;
    String username;
    String imei;
    String registerUrl = "http://10.0.2.2/webapp/init.php";
    ArrayList<String> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username_edit = (EditText)findViewById(R.id.edit_user);
        submit = (Button)findViewById(R.id.submit);
//        BackgroundTask backgroundTask = new BackgroundTask(MainActivity.this);
//        backgroundTask.execute("tt","1234");



        username_edit.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                username_edit.setHint("");
                return false;
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = username_edit.getText().toString();
                imei = getIMEINumber();
                Utils.savePrefs(MainActivity.this,"username",username);
                if (isNetworkAvailable(MainActivity.this)) {
                    
                    BackgroundTask backgroundTask = new BackgroundTask(MainActivity.this);
                    backgroundTask.execute(username,imei);
                    AddAllUsers addAllUsers = new AddAllUsers(MainActivity.this);
                    addAllUsers.execute(imei);
//                    Intent intent = new Intent(MainActivity.this,UserListActivity.class);
//                    intent.putExtra("userList",userList);
                } else {
                    Toast.makeText(getApplicationContext(),"Internet Not Available", Toast.LENGTH_LONG).show();
                }




            }
        });

    }

    public boolean isNetworkAvailable(final Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }



    public String getIMEINumber(){
        TelephonyManager mngr = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        return mngr.getDeviceId();
    }

    public class AddAllUsers extends AsyncTask<String,Void,ArrayList<String>> {

        Context context;
        ProgressDialog pd;
        public AddAllUsers(Context context) {
            this.context = MainActivity.this;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(context);
            pd.setTitle("Finding Users...");
            pd.show();
        }

        @Override
        protected ArrayList<String> doInBackground(String... params) {

            String reg_url = "http://192.168.1.4/webapp/test.php";
            ArrayList<String> userList = new ArrayList<>();
            String imei = params[0];
            try {
                URL url = new URL(reg_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                //httpURLConnection.setDoInput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                String data = URLEncoder.encode("imei", "UTF-8") + "=" + URLEncoder.encode(imei, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();
                InputStream IS = httpURLConnection.getInputStream();

                BufferedReader reader = new BufferedReader(new InputStreamReader(IS));
                StringBuilder sb = new StringBuilder();

                String line = null;
                try {
                    while ((line = reader.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        IS.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                //httpURLConnection.connect();
                httpURLConnection.disconnect();
                JSONArray jsonArray = new JSONArray(sb.toString());
                for(int i=0;i<jsonArray.length();i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String username = jsonObject.getString("username");
                    userList.add(username);

                }
                return userList;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<String> strings) {
            super.onPostExecute(strings);
            pd.dismiss();
            Intent intent = new Intent(context,UserListActivity.class);
            intent.putExtra("userList",strings);
            context.startActivity(intent);
        }
    }

//    public class RegisterUser extends AsyncTask<String,Void,String>{
//
//        String username;
//        String imei;
//        String reply;
//
//
//        public RegisterUser(String username,String imei) {
//            this.username = username;
//            this.imei = imei;
//        }
//
////        @Override
////        protected void onPostExecute(String s) {
////            if(s.equals("Data already exist.."))
////                Toast.makeText(getApplicationContext(),"Username already exists", Toast.LENGTH_LONG).show();
////
////        }
//
//        @Override
//        protected String doInBackground(String... params) {
//            HttpURLConnection connection = null;
//            try{
//                String data =  URLEncoder.encode("userName", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8");
//                data += "&" + URLEncoder.encode("imei", "UTF-8") + "=" + URLEncoder.encode(imei, "UTF-8");
//
//                URL url = new URL(params[0]);
//                connection = (HttpURLConnection) url.openConnection();
//                connection.setRequestMethod("POST");
//                connection.connect();                                                                                   a
//
//                int responsecode = connection.getResponseCode();
////                Log.i("abfkhf","uuuu"+responsecode);
//                OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
//                wr.write(data);
//                wr.flush();
//
//                InputStream in = connection.getInputStream();
//                StringBuffer sb = new StringBuffer();
//                try {
//                    int chr;
//                    while ((chr = in.read()) != -1) {
//                        sb.append((char) chr);
//                    }
//                    reply = sb.toString();
//                } finally {
//                    in.close();
//                }
//
//            } catch (UnsupportedEncodingException e) {
//                e.printStackTrace();
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return reply;
//        }
//    }
}

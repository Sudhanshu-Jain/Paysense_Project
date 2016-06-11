package com.example.chatapplication;

import android.os.AsyncTask;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by sudhanshu on 11/5/16.
 */
public class SendMessage extends AsyncTask<String,Void,String > {
    @Override
    protected String doInBackground(String... params) {
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");

            Calendar calobj = Calendar.getInstance();
            CharSequence text = df.format(calobj.getTime());
            String url = "http://192.168.1.4/webapp/sendMessagetoServer";
            URL url1 = new URL(url);
            String message_from = params[0];
            String message = params[1];
            String message_to = params[2];
            HttpURLConnection httpURLConnection = (HttpURLConnection) url1.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            //httpURLConnection.setDoInput(true);
            OutputStream OS = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
            String data = URLEncoder.encode("messagefrom", "UTF-8") + "=" + URLEncoder.encode(message_from, "UTF-8") + "&" +
                    URLEncoder.encode("message", "UTF-8") + "=" + URLEncoder.encode(message, "UTF-8") + "&" +
                    URLEncoder.encode("messageto", "UTF-8") + "=" + URLEncoder.encode(message_to, "UTF-8") + "&" +
                    URLEncoder.encode("date", "UTF-8") + "=" + URLEncoder.encode(text.toString(), "UTF-8");
            bufferedWriter.write(data);
            bufferedWriter.flush();
            bufferedWriter.close();
            OS.close();
            InputStream IS = httpURLConnection.getInputStream();

            ByteArrayOutputStream result = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length;
            while((length = IS.read(buffer))!=-1) {
                result.write(buffer, 0, length);
            }
                return result.toString("UTF-8");

            //httpURLConnection.connect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

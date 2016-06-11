package com.example.chatapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by sudhanshu on 10/5/16.
 */
public class Utils {

    public static void savePrefs(Context ctx,String key,String value){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(ctx);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString(key, value);
        edit.commit();

    }

    public static String loadPrefs(Context ctx,String key){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(ctx);
        String user = sp.getString(key, "");
        return user;
    }
}

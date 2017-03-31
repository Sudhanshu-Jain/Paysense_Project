package sample.example.com.camerabasics;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;

import java.util.List;

/**
 * Created by sudhanshu on 30/3/17.
 */

public class AppUtils {

    private static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 1;
    private static final String APP_NAME = "VOKEL";

    public static boolean isExternalStorageAvailable() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(extStorageState)) {
            return true;
        }
        return false;
    }

    public static boolean checkStoragePermission(Context context){
        PackageManager pm = context.getPackageManager();
        int hasPerm = pm.checkPermission(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                context.getPackageName());
        if (hasPerm != PackageManager.PERMISSION_GRANTED) {
            return false;
        }
        return true;
    }

    public static String getFromSharedPrefs(Context context, String key) {
        SharedPreferences editor = context.getSharedPreferences(APP_NAME, Context.MODE_PRIVATE);
        return editor.getString(key, "");

    }

    public static void saveInSharedPrefs(Context context, String key, String value) {
        SharedPreferences.Editor editor = context.getSharedPreferences(APP_NAME, Context.MODE_PRIVATE).edit();
        editor.putString(key, value);
        boolean saved = editor.commit();
        if (!saved) {
            saved = editor.commit();
            if (!saved) {
                editor.commit();
            }
        }

    }

    public static void requestPermission(Context context,List<String> list){

        Activity activity = (Activity) context;
        ActivityCompat.requestPermissions(activity, list.toArray(new String[list.size()]), REQUEST_ID_MULTIPLE_PERMISSIONS);

    }

    public static void showMessageOKCancel(Activity activity, String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(activity)
                .setMessage(message)
                .setPositiveButton("Settings", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }
}

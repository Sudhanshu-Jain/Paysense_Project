package com.example.samplemusic.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.samplemusic.models.Song;

import java.util.ArrayList;

/**
 * Created by sudhanshu on 7/4/16.
 */
public class DbHelper extends SQLiteOpenHelper {

    private static DbHelper instance;
    private static String TAG = "DBHELPER";
    public static String TABLE_NAME = Constants.TABLE_NAME;
    private static String DB_NAME = "SONGS_DATABASE";
    private static int DB_VERSION = 1;
    public static String ID_FIELD_NAME = "id";
    public static String ARTIST_NAME = "artist_name";
    public static String ALBUM_NAME = "album_name";
    public static String SONG_NAME = "song_name";

    private static final String CREATE_SONGS_TABLE = "CREATE TABLE IF NOT EXISTS "
            + TABLE_NAME + " ("
            + ID_FIELD_NAME + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + ALBUM_NAME + " TEXT, " + ARTIST_NAME + " TEXT, " + SONG_NAME+ " TEXT);";

    public DbHelper(Context context) {
        super(context,DB_NAME, null, DB_VERSION);

    }

    public static DbHelper getDatabaseHelper(Context context) {
        if (instance == null) {
            instance = new DbHelper(context.getApplicationContext());
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_SONGS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME);
        db.execSQL(CREATE_SONGS_TABLE);

    }
    public void addSongtoTable(Song song) {
        SQLiteDatabase db = getWritableDatabase();
        long result = -1;

        try {
            ContentValues cv = new ContentValues();
            cv.put(ARTIST_NAME, song.getArtistName());
            cv.put(SONG_NAME, song.getSongName());
            cv.put(ALBUM_NAME, song.getAlbumName());
            result = db.insertWithOnConflict(TABLE_NAME, null, cv, 5);
            if (result == -1)
                Log.e(TAG, "Song cannot be inserted");
        } catch (SQLiteException e) {
            Log.e(TAG, "song insertion failed");
        } finally {
            close();
        }


    }

    public int getDatabaseSize(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM " + TABLE_NAME, null);
        int count = cursor.getCount();
        return count;
    }

    public ArrayList<String> getUniqueList(){
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<String> artistList =new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT DISTINCT " + ARTIST_NAME + " FROM " + TABLE_NAME, null);
        int count = cursor.getCount();
        if(count>0)
            cursor.moveToFirst();
        do{
            String artistName =cursor.getString(cursor.getColumnIndex(DbHelper.ARTIST_NAME));
            artistList.add(artistName);
        }while (cursor.moveToNext());
        return artistList;
    }
}

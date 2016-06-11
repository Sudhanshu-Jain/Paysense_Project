//package com.example.chatapplication;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//import android.util.Log;
//
///**
// * Created by sudhanshu on 10/5/16.
// */
//public class DBAdapter extends SQLiteOpenHelper {
//
//    SQLiteDatabase db;
//    private static DBAdapter instance;
//    private static final String TAG = "DBAdapter";
//
//    public static final String DATABASE_NAME = "Message_DB";
//    public static final String TABLE_NAME = "Message_Log";
//    public static final String USER_TABLE = "Contacts";
//    public static final int DATABASE_VERSION = 1;
//
//
//    public static final String ID = "_id";
//    public static final String MESSAGE_FROM = "message_from";
//    public static final String MESSAGE = "message";
//    public static final String DATE = "date";
//    public static final String MESSAGE_TO = "message_to";
//    public static final String USER_NAME = "username";
//
//
//    private static final String CREATE_MESSAGE_TABLE = "CREATE TABLE " + TABLE_NAME +
//            "( " + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + MESSAGE_FROM +
//            " TEXT NOT NULL, " + MESSAGE + " TEXT," + MESSAGE_TO + " TEXT, " + DATE +" DATETIME DEFAULT (datetime('now','localtime')));";
//
//    private static final String CREATE_USERS_TABLE = "CREATE TABLE " + USER_TABLE +
//            "( " + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + USER_NAME +
//            " TEXT );";
//
//    public DBAdapter(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
//        super(context, name, factory, version);
//    }
//
//
//    static DBAdapter getDatabaseHelper(Context context) {
//        if (instance == null) {
//            instance = new DBAdapter(context.getApplicationContext(),DATABASE_NAME,null,DATABASE_VERSION);
//        }
//        return instance;
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        db.execSQL(CREATE_MESSAGE_TABLE);
//        db.execSQL(CREATE_USERS_TABLE);
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
//        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);
//        db.execSQL(CREATE_MESSAGE_TABLE);
//        db.execSQL(CREATE_USERS_TABLE);
//    }
//
//    public long insertUser (String name){
//        SQLiteDatabase db = getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(USER_NAME,name);
//
//        long result = db.insert(USER_TABLE, null, contentValues);
//
//        if(result!=-1)
//            Log.d(TAG, "Message added");
//        return result;
//    }
//
//    public boolean checkUser(String username){
//        SQLiteDatabase db = getReadableDatabase();
//        Cursor c = db.rawQuery("SELECT * FROM " + USER_TABLE + " WHERE " + USER_NAME + " = " + "'" + username + "'", null);
//        if (c!=null)
//            return true;
//
//        return false;
//    }
//    public Cursor getUsers(){
//        SQLiteDatabase db = getReadableDatabase();
//        Cursor c = db.rawQuery("SELECT " + USER_NAME + " FROM " + USER_TABLE + " WHERE " + USER_NAME +" <>"+"'"+MainActivity1.bob +"'",null);
//        if(c!=null){
//            c.moveToFirst();
//        }
//        return c;
//    }
//
//
//
//
//    public Cursor getMessages(String username,String reciever_name){
//
//        SQLiteDatabase db = getReadableDatabase();
//        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + MESSAGE_TO + " = " + "'" + username + "'" + " AND " + MESSAGE_FROM + " = " + "'" + reciever_name + "' " +
//                " OR " + MESSAGE_FROM + " = " + "'" + username + "' " + " AND " + MESSAGE_TO + " = " + "'" + reciever_name + "'", null);
//        if(c!=null){
//            c.moveToFirst();
//        }
//        return c;
//    }
//}

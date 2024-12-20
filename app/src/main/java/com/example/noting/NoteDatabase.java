package com.example.noting;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class NoteDatabase extends SQLiteOpenHelper {
    public static final String TABLE_NAME = "notes";
    public static final String CONTENT = "content";
    public static final String ID = "_id";
    public static final String TIME = "time";
    public static final String MODE = "mode";

    public NoteDatabase(Context context){
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME
                + "("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT," //主键
                + CONTENT + " TEXT NOT NULL,"
                + TIME + " TEXT NOT NULL,"
                + MODE + " INTEGER DEFAULT 1)"
        );
    }
//更新数据库设计版本函数
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        for(int i=oldVersion; i<newVersion; i++){
//            switch(i){
//                case 1:
//                    break;
//                case 2:
//                    updateMode(db);
//                default:
//                    break;
//            }
//        }
    }
}

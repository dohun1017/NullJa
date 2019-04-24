package com.nullja.nullja;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DataBase {
    public static String DB_Name = "NullJa_DB"; //DB명

    public static void createTable(SQLiteDatabase db) {
        //String createTableSQL = ""; //테이블 생성문

        Log.i("DataBase.createTable","실행");
        //database.execSQL(createTableSQL);

    }
}

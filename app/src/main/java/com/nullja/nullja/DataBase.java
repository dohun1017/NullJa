package com.nullja.nullja;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DataBase {
    public static String DB_Name = "NullJa_DB"; //DB명
    public static String Table_Name = "hotpl";

    public static void createTable(SQLiteDatabase db) {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS " + Table_Name
                + "("
                + " hotplnum integer PRIMARY KEY autoincrement, "
                + " hotplname text, "
                + " hotplat real, hotplon real, "
                + " hotplinfo text, "
                + " hotplimage blob);"; //테이블 생성문

        Log.i("DataBase.createTable","실행");
        db.execSQL(createTableSQL);

    }
}

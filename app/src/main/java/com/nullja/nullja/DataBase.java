package com.nullja.nullja;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.tasks.Task;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.ExecutionException;

public class DataBase {
    public static String DB_Name = "NullJa_DB"; //DB명
    public static String Table_Name = "hotpl";

    public static void createTable(SQLiteDatabase db) {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS " + Table_Name
                + "("
                + " hotplnum integer PRIMARY KEY autoincrement, "
                + " hotplcat integer, "
                + " hotplname text, "
                + " hotpladdr text, "
                + " hotplat real, hotplon real, "
                + " hotplinfo text, "
                + " hotplimage blob);"; //테이블 생성문

        Log.i("DataBase.createTable","실행");
        db.execSQL(createTableSQL);
    }


    public static void insertData(SQLiteDatabase db, urlHotpl hotpl){
        String insertDataSQL = "INSERT INTO " + Table_Name
                +" (hotplcat, hotplname, hotpladdr, hotplat, hotplon, hotplimage)VALUES (?, ?, ?, ?, ?, ?);";
        Integer hotplcat=hotpl.hotplcat;
        String hotplname=hotpl.hotplname;
        String hotpladdr=hotpl.hotpladdr;
        Double hotplat=hotpl.hotplat;
        Double hotplon=hotpl.hotplon;
        String hotplimage=hotpl.hotplimage;

        SQLiteStatement insertState = db.compileStatement(insertDataSQL);
        insertState.bindDouble(1, hotplcat);
        insertState.bindString(2, hotplname);
        insertState.bindString(3, hotpladdr);
        insertState.bindDouble(4,hotplat);
        insertState.bindDouble(5, hotplon);
        insertState.bindBlob(6, urlToBlob(hotplimage));
        insertState.executeInsert();

        Log.i("DataBase.insertData","실행됨");
    }

    private static byte[] urlToBlob(String URL){
        byte[] rdt=null;

        task tk = new task();
        try {
            rdt = tk.execute(URL).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return rdt;
    }

}

class task extends AsyncTask<String, Void, byte[]> {
    @Override
    protected byte[] doInBackground(String... params){

        try {
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            byte[] data = new byte[50];

            URL url = new URL(params[0]);
            HttpURLConnection con = null;

            con = (HttpURLConnection)url.openConnection();
            InputStream is = con.getInputStream();

            BufferedInputStream bis = new BufferedInputStream(is);
            int current = 0;
            while ((current = bis.read(data,0,data.length)) != -1) {
                buffer.write(data,0, current);
            }
            return buffer.toByteArray();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return  null;
    }
}
package com.nullja.nullja;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
        //database.execSQL(createTableSQL);
        Connection connection = null;
        Statement st = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql:주소:포트/DB명" , "username", "password");
            st = connection.createStatement();

            String sql;
            sql = "select * FROM table;";

            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                String sqlRecipeProcess = rs.getString("column명");
            }

            rs.close();
            st.close();
            connection.close();
        } catch (SQLException se1) {
            se1.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (st != null)
                    st.close();
            } catch (SQLException se2) {
            }
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
}

package simamat.lagukebangsaan.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseAccess {

    private SQLiteOpenHelper sqLiteOpenHelper;
    private SQLiteDatabase sqLiteDatabase;
    private static DatabaseAccess instance;
    Cursor c = null;

    //private constructor so that object creation from outside the class is avoided
    private DatabaseAccess(Context context) {
        this.sqLiteOpenHelper = new DatabaseOpenHelper(context);
    }

    //to return the single instance of database
    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    //to open the database
    public void open() {
        this.sqLiteDatabase = sqLiteOpenHelper.getWritableDatabase();
    }

    //closing the database connection
    public void close() {
        if (sqLiteDatabase != null) {
            this.sqLiteDatabase.close();
        }
    }

    //a method query and return the result from database
    public String getJudul(String id) {

        c = sqLiteDatabase.rawQuery("select judul_lagu from lagu where id = '"+ id +"'", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext()) {
            String judulLagu = c.getString(0);
            buffer.append(""+judulLagu);
        }

        return buffer.toString();
    }

    public String getPencipta(String id) {

        c = sqLiteDatabase.rawQuery("select pencipta_lagu from lagu where id = '"+ id +"'", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext()) {
            String penciptaLagu = c.getString(0);
            buffer.append(""+penciptaLagu);
        }

        return buffer.toString();
    }

    public String getLirik(String id) {

        c = sqLiteDatabase.rawQuery("select lirik_lagu from lagu where id = '"+ id +"'", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext()) {
            String lirikLagu = c.getString(0);
            buffer.append(""+lirikLagu);
        }

        return buffer.toString();
    }

    public String getUrlVideo(String id) {

        c = sqLiteDatabase.rawQuery("select link_lagu from lagu where id = '"+ id +"'", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext()) {
            String linkLagu = c.getString(0);
            buffer.append(""+linkLagu);
        }

        return buffer.toString();
    }

}

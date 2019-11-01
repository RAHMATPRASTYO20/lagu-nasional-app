package simamat.lagukebangsaan.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseAccess {

    private SQLiteOpenHelper sqLiteOpenHelper;
    private SQLiteDatabase sqLiteDatabase;
    private static DatabaseAccess instance;
    Cursor c = null;

//    static SQLiteDatabase db = sqLiteOpenHelper.getReadableDatabase();
//    static Cursor name = db.rawQuery("select judul_lagu from lagu",null);
//    public static final String COLUMN_JUDUL = name.toString();

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

    public List<String> getAllJudul() {
        List<String> list = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery("select judul_lagu from lagu", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(0));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    //a method query and return the result from database
    public String getJudul(String judul) {

        c = sqLiteDatabase.rawQuery("select judul_lagu from lagu where judul_lagu = '"+ judul +"'", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext()) {
            String judulLagu = c.getString(0);
            buffer.append(""+judulLagu);
        }

        return buffer.toString();
    }

    public String getPencipta(String judul) {

        c = sqLiteDatabase.rawQuery("select pencipta_lagu from lagu where judul_lagu = '"+ judul +"'", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext()) {
            String penciptaLagu = c.getString(0);
            buffer.append(""+penciptaLagu);
        }

        return buffer.toString();
    }

    public String getLirik(String judul) {

        c = sqLiteDatabase.rawQuery("select lirik_lagu from lagu where judul_lagu = '"+ judul +"'", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext()) {
            String lirikLagu = c.getString(0);
            buffer.append(""+lirikLagu);
        }

        return buffer.toString();
    }

    public String getUrlVideo(String judul) {

        c = sqLiteDatabase.rawQuery("select link_lagu from lagu where judul_lagu = '"+ judul +"'", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext()) {
            String linkLagu = c.getString(0);
            buffer.append(""+linkLagu);
        }

        return buffer.toString();
    }


}

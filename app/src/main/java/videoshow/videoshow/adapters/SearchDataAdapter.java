package videoshow.videoshow.adapters;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.Settings;

/**
 * Created by liaoyuan on 06/03/17.
 */

public class SearchDataAdapter extends SQLiteOpenHelper{

    final String SQL_CREATE_TABLE = "create table movie_table (" +
            "_id integer primary key autoincrement, " +
            "movie_title vachar(50), " +
            "movie_description vachar(5000)";

    public SearchDataAdapter(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        System.out.println("call update");
    }
}

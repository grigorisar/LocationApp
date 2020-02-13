package gr.hua.grigorisar.locationapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class LocationOpenHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "CONTACTS_DB";
    private static final int DB_VERSION = 1;

    public static final String TABLE = "LOCATIONS";
    public static final String KEY_ID = "ID";
    public static final String KEY_LAT = "LAT";
    public static final String KEY_LON = "LON";
    public static final String KEY_TIME = "UNIX_TIMESTAMP";
    public static final String CREATE_QUERY = "CREATE TABLE "+TABLE+" ("+KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT , "+KEY_LAT+" TEXT ,"+KEY_LON+" TEXT ,"+KEY_TIME+" TEXT)";


    public LocationOpenHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

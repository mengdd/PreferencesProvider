package com.ddmeng.preferencesprovider.provider;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.DefaultDatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import com.ddmeng.preferencesprovider.BuildConfig;
import com.ddmeng.preferencesprovider.provider.preferences.PreferencesColumns;

public class PreferencesSQLiteOpenHelper extends SQLiteOpenHelper {
    private static final String TAG = PreferencesSQLiteOpenHelper.class.getSimpleName();

    public static final String DATABASE_FILE_NAME = "preferencesProvider.db";
    private static final int DATABASE_VERSION = 1;
    private static PreferencesSQLiteOpenHelper sInstance;
    private final Context mContext;
    private final PreferencesSQLiteOpenHelperCallbacks mOpenHelperCallbacks;

    // @formatter:off
    public static final String SQL_CREATE_TABLE_PREFERENCES = "CREATE TABLE IF NOT EXISTS "
            + PreferencesColumns.TABLE_NAME + " ( "
            + PreferencesColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + PreferencesColumns.MODULE + " TEXT, "
            + PreferencesColumns.KEY + " TEXT NOT NULL, "
            + PreferencesColumns.VALUE + " TEXT "
            + ", CONSTRAINT unique_name UNIQUE (module, key) ON CONFLICT REPLACE"
            + " );";

    // @formatter:on

    public static PreferencesSQLiteOpenHelper getInstance(Context context) {
        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null) {
            sInstance = newInstance(context.getApplicationContext());
        }
        return sInstance;
    }

    private static PreferencesSQLiteOpenHelper newInstance(Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            return newInstancePreHoneycomb(context);
        }
        return newInstancePostHoneycomb(context);
    }


    /*
     * Pre Honeycomb.
     */
    private static PreferencesSQLiteOpenHelper newInstancePreHoneycomb(Context context) {
        return new PreferencesSQLiteOpenHelper(context);
    }

    private PreferencesSQLiteOpenHelper(Context context) {
        super(context, DATABASE_FILE_NAME, null, DATABASE_VERSION);
        mContext = context;
        mOpenHelperCallbacks = new PreferencesSQLiteOpenHelperCallbacks();
    }


    /*
     * Post Honeycomb.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private static PreferencesSQLiteOpenHelper newInstancePostHoneycomb(Context context) {
        return new PreferencesSQLiteOpenHelper(context, new DefaultDatabaseErrorHandler());
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private PreferencesSQLiteOpenHelper(Context context, DatabaseErrorHandler errorHandler) {
        super(context, DATABASE_FILE_NAME, null, DATABASE_VERSION, errorHandler);
        mContext = context;
        mOpenHelperCallbacks = new PreferencesSQLiteOpenHelperCallbacks();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        if (BuildConfig.DEBUG) Log.d(TAG, "onCreate");
        mOpenHelperCallbacks.onPreCreate(mContext, db);
        db.execSQL(SQL_CREATE_TABLE_PREFERENCES);
        mOpenHelperCallbacks.onPostCreate(mContext, db);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        mOpenHelperCallbacks.onOpen(mContext, db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        mOpenHelperCallbacks.onUpgrade(mContext, db, oldVersion, newVersion);
    }
}

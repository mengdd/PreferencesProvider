package com.ddmeng.preferencesprovider.provider;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.ddmeng.preferencesprovider.provider.preferences.PreferencesContentValues;
import com.ddmeng.preferencesprovider.provider.preferences.PreferencesCursor;
import com.ddmeng.preferencesprovider.provider.preferences.PreferencesSelection;
import com.ddmeng.preferencesprovider.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

public class PreferencesHelper {

    private static final String TAG = PreferencesHelper.class.getSimpleName();

    private final Context context;
    private final ContentResolver contentResolver;

    public PreferencesHelper(Context context) {
        this.context = context.getApplicationContext();
        this.contentResolver = this.context.getContentResolver();

    }

    public void insert(@NonNull final String moduleName, @NonNull final String key, @Nullable Object value) {
        insert(moduleName, key, String.valueOf(value));
    }

    public void insert(@NonNull final String moduleName, @NonNull final String key, @Nullable String value) {
        LogUtils.i(TAG, "insert: module " + moduleName + ", " + key + " = " + value);
        PreferencesContentValues contentValues = new PreferencesContentValues();
        contentValues.putModule(moduleName);
        contentValues.putKey(key);
        contentValues.putValue(value);
        contentValues.insert(contentResolver);
    }

    public String query(@NonNull final String moduleName, @NonNull final String key) {
        LogUtils.i(TAG, "query start: module " + moduleName + ", " + key);
        String value = null;
        PreferencesCursor cursor = new PreferencesSelection().module(moduleName).and().key(key).query(contentResolver);
        if (cursor != null && cursor.moveToFirst()) {
            value = cursor.getValue();
        }
        closeQuietly(cursor);
        LogUtils.i(TAG, "query end: module " + moduleName + ", " + key + " = " + value);
        return value;
    }

    public int remove(@NonNull final String moduleName, @NonNull final String key) {
        return new PreferencesSelection().module(moduleName).and().key(key).delete(contentResolver);
    }

    public int clear(@NonNull final String moduleName) {
        return new PreferencesSelection().module(moduleName).delete(contentResolver);
    }

    public List<PreferenceItem> getAll(@NonNull final String moduleName) {
        PreferencesCursor cursor = new PreferencesSelection().module(moduleName).query(contentResolver);
        final ArrayList<PreferenceItem> list = new ArrayList<>();
        if (cursor != null) {
            for (boolean hasItem = cursor.moveToFirst(); hasItem; hasItem = cursor.moveToNext()) {
                list.add(new PreferenceItem(cursor));
            }
        }
        closeQuietly(cursor);
        return list;
    }

    private static void closeQuietly(Cursor cursor) {
        if (cursor != null) {
            cursor.close();
        }
    }

}

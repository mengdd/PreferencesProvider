package com.ddmeng.preferencesprovider.provider.preferences;

import android.net.Uri;
import android.provider.BaseColumns;

import com.ddmeng.preferencesprovider.provider.PreferencesProvider;

/**
 * Columns for the {@code preferences} table.
 */
public class PreferencesColumns implements BaseColumns {
    public static final String TABLE_NAME = "preferences";
    public static final Uri CONTENT_URI = Uri.parse(PreferencesProvider.CONTENT_URI_BASE + "/" + TABLE_NAME);

    /**
     * Primary key.
     */
    public static final String _ID = BaseColumns._ID;

    public static final String MODULE = "module";

    public static final String KEY = "key";

    public static final String VALUE = "value";


    public static final String DEFAULT_ORDER = TABLE_NAME + "." +_ID;

    // @formatter:off
    public static final String[] ALL_COLUMNS = new String[] {
            _ID,
            MODULE,
            KEY,
            VALUE
    };
    // @formatter:on

    public static boolean hasColumns(String[] projection) {
        if (projection == null) return true;
        for (String c : projection) {
            if (c.equals(MODULE) || c.contains("." + MODULE)) return true;
            if (c.equals(KEY) || c.contains("." + KEY)) return true;
            if (c.equals(VALUE) || c.contains("." + VALUE)) return true;
        }
        return false;
    }

}

package com.ddmeng.preferencesprovider.provider.preferences;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.ddmeng.preferencesprovider.provider.base.AbstractCursor;

/**
 * Cursor wrapper for the {@code preferences} table.
 */
public class PreferencesCursor extends AbstractCursor implements PreferencesModel {
    public PreferencesCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Primary key.
     */
    public long getId() {
        Long res = getLongOrNull(PreferencesColumns._ID);
        if (res == null)
            throw new NullPointerException("The value of '_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code module} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getModule() {
        String res = getStringOrNull(PreferencesColumns.MODULE);
        return res;
    }

    /**
     * Get the {@code key} value.
     * Cannot be {@code null}.
     */
    @NonNull
    public String getKey() {
        String res = getStringOrNull(PreferencesColumns.KEY);
        if (res == null)
            throw new NullPointerException("The value of 'key' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code value} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getValue() {
        String res = getStringOrNull(PreferencesColumns.VALUE);
        return res;
    }
}

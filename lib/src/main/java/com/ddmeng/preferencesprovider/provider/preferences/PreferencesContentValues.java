package com.ddmeng.preferencesprovider.provider.preferences;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.ddmeng.preferencesprovider.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code preferences} table.
 */
public class PreferencesContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return PreferencesColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where           The selection to use (can be {@code null}).
     * @return count of updated rows.
     */
    public int update(ContentResolver contentResolver, @Nullable PreferencesSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param context The context to use for getting content resolver.
     * @param where   The selection to use (can be {@code null}).
     * @return count of updated rows.
     */
    public int update(Context context, @Nullable PreferencesSelection where) {
        return context.getContentResolver().update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public PreferencesContentValues putModule(@Nullable String value) {
        mContentValues.put(PreferencesColumns.MODULE, value);
        return this;
    }

    public PreferencesContentValues putModuleNull() {
        mContentValues.putNull(PreferencesColumns.MODULE);
        return this;
    }

    public PreferencesContentValues putKey(@NonNull String value) {
        if (value == null) throw new IllegalArgumentException("key must not be null");
        mContentValues.put(PreferencesColumns.KEY, value);
        return this;
    }


    public PreferencesContentValues putValue(@Nullable String value) {
        mContentValues.put(PreferencesColumns.VALUE, value);
        return this;
    }

    public PreferencesContentValues putValueNull() {
        mContentValues.putNull(PreferencesColumns.VALUE);
        return this;
    }
}

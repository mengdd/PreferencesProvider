package com.ddmeng.preferencesprovider.provider.base;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;

public abstract class AbstractContentValues {
    protected final ContentValues mContentValues = new ContentValues();

    /**
     * Returns the {@code uri} argument to pass to the {@code ContentResolver} methods.
     *
     * @return Uri to pass to ContentResolver.
     */
    public abstract Uri uri();

    /**
     * Returns the {@code ContentValues} wrapped by this object.
     *
     * @return ContentValues.
     */
    public ContentValues values() {
        return mContentValues;
    }

    /**
     * Inserts a row into a table using the values stored by this object.
     *
     * @param contentResolver The content resolver to use.
     * @return Uri of this new row.
     */
    public Uri insert(ContentResolver contentResolver) {
        return contentResolver.insert(uri(), values());
    }

    /**
     * Inserts a row into a table using the values stored by this object.
     *
     * @param context The context to use.
     * @return Uri of this new row.
     */
    public Uri insert(Context context) {
        return context.getContentResolver().insert(uri(), values());
    }
}
package com.ddmeng.preferencesprovider.provider.preferences;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.ddmeng.preferencesprovider.provider.base.AbstractSelection;

/**
 * Selection for the {@code preferences} table.
 */
public class PreferencesSelection extends AbstractSelection<PreferencesSelection> {
    @Override
    protected Uri baseUri() {
        return PreferencesColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection      A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code PreferencesCursor} object, which is positioned before the first entry, or null.
     */
    public PreferencesCursor query(ContentResolver contentResolver, String[] projection) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new PreferencesCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, null)}.
     *
     * @param contentResolver the content resolver to query.
     * @return A {@code PreferencesCursor} object, which is positioned before the first entry, or null.
     */
    public PreferencesCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null);
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param context    The context to use for getting the content resolver.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code PreferencesCursor} object, which is positioned before the first entry, or null.
     */
    public PreferencesCursor query(Context context, String[] projection) {
        Cursor cursor = context.getContentResolver().query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new PreferencesCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(context, null)}.
     *
     * @param context the context to use for getting the content resolver.
     * @return A {@code PreferencesCursor} object, which is positioned before the first entry, or null.
     */
    public PreferencesCursor query(Context context) {
        return query(context, null);
    }


    public PreferencesSelection id(long... value) {
        addEquals("preferences." + PreferencesColumns._ID, toObjectArray(value));
        return this;
    }

    public PreferencesSelection idNot(long... value) {
        addNotEquals("preferences." + PreferencesColumns._ID, toObjectArray(value));
        return this;
    }

    public PreferencesSelection orderById(boolean desc) {
        orderBy("preferences." + PreferencesColumns._ID, desc);
        return this;
    }

    public PreferencesSelection orderById() {
        return orderById(false);
    }

    public PreferencesSelection module(String... value) {
        addEquals(PreferencesColumns.MODULE, value);
        return this;
    }

    public PreferencesSelection moduleNot(String... value) {
        addNotEquals(PreferencesColumns.MODULE, value);
        return this;
    }

    public PreferencesSelection moduleLike(String... value) {
        addLike(PreferencesColumns.MODULE, value);
        return this;
    }

    public PreferencesSelection moduleContains(String... value) {
        addContains(PreferencesColumns.MODULE, value);
        return this;
    }

    public PreferencesSelection moduleStartsWith(String... value) {
        addStartsWith(PreferencesColumns.MODULE, value);
        return this;
    }

    public PreferencesSelection moduleEndsWith(String... value) {
        addEndsWith(PreferencesColumns.MODULE, value);
        return this;
    }

    public PreferencesSelection orderByModule(boolean desc) {
        orderBy(PreferencesColumns.MODULE, desc);
        return this;
    }

    public PreferencesSelection orderByModule() {
        orderBy(PreferencesColumns.MODULE, false);
        return this;
    }

    public PreferencesSelection key(String... value) {
        addEquals(PreferencesColumns.KEY, value);
        return this;
    }

    public PreferencesSelection keyNot(String... value) {
        addNotEquals(PreferencesColumns.KEY, value);
        return this;
    }

    public PreferencesSelection keyLike(String... value) {
        addLike(PreferencesColumns.KEY, value);
        return this;
    }

    public PreferencesSelection keyContains(String... value) {
        addContains(PreferencesColumns.KEY, value);
        return this;
    }

    public PreferencesSelection keyStartsWith(String... value) {
        addStartsWith(PreferencesColumns.KEY, value);
        return this;
    }

    public PreferencesSelection keyEndsWith(String... value) {
        addEndsWith(PreferencesColumns.KEY, value);
        return this;
    }

    public PreferencesSelection orderByKey(boolean desc) {
        orderBy(PreferencesColumns.KEY, desc);
        return this;
    }

    public PreferencesSelection orderByKey() {
        orderBy(PreferencesColumns.KEY, false);
        return this;
    }

    public PreferencesSelection value(String... value) {
        addEquals(PreferencesColumns.VALUE, value);
        return this;
    }

    public PreferencesSelection valueNot(String... value) {
        addNotEquals(PreferencesColumns.VALUE, value);
        return this;
    }

    public PreferencesSelection valueLike(String... value) {
        addLike(PreferencesColumns.VALUE, value);
        return this;
    }

    public PreferencesSelection valueContains(String... value) {
        addContains(PreferencesColumns.VALUE, value);
        return this;
    }

    public PreferencesSelection valueStartsWith(String... value) {
        addStartsWith(PreferencesColumns.VALUE, value);
        return this;
    }

    public PreferencesSelection valueEndsWith(String... value) {
        addEndsWith(PreferencesColumns.VALUE, value);
        return this;
    }

    public PreferencesSelection orderByValue(boolean desc) {
        orderBy(PreferencesColumns.VALUE, desc);
        return this;
    }

    public PreferencesSelection orderByValue() {
        orderBy(PreferencesColumns.VALUE, false);
        return this;
    }
}

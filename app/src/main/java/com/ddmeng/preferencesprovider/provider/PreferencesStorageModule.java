package com.ddmeng.preferencesprovider.provider;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.ddmeng.preferencesprovider.provider.exception.ItemNotFoundException;
import com.ddmeng.preferencesprovider.provider.exception.WrongTypeException;

import java.util.List;

public class PreferencesStorageModule {

    private final String moduleName;
    private PreferencesHelper preferencesHelper;

    public PreferencesStorageModule(Context context, String moduleName) {
        this.moduleName = moduleName;
        preferencesHelper = new PreferencesHelper(context);
    }

    public void put(@NonNull final String key, final String value) {
        preferencesHelper.insert(moduleName, key, value);
    }

    public void put(@NonNull final String key, final int value) {
        preferencesHelper.insert(moduleName, key, value);
    }

    public void put(@NonNull final String key, final float value) {
        preferencesHelper.insert(moduleName, key, value);
    }

    public void put(@NonNull final String key, final long value) {
        preferencesHelper.insert(moduleName, key, value);
    }

    public void put(@NonNull final String key, final boolean value) {
        preferencesHelper.insert(moduleName, key, value);
    }

    public boolean getBoolean(@NonNull final String key, final boolean defaultValue) {
        try {
            return getBoolean(key);
        } catch (ItemNotFoundException e) {
            return defaultValue;
        }
    }

    public boolean getBoolean(@NonNull final String key) throws ItemNotFoundException {
        final String value = getString(key);
        return Boolean.parseBoolean(value);
    }

    public float getFloat(@NonNull final String key, final float defaultValue) {
        try {
            return getFloat(key);
        } catch (ItemNotFoundException e) {
            return defaultValue;
        }
    }

    public float getFloat(@NonNull final String key) throws ItemNotFoundException {
        final String value = getString(key);
        throwForNullValue(value, Float.class, key);
        try {
            return Float.parseFloat(value);
        } catch (NumberFormatException e) {
            throw new WrongTypeException(e);
        }
    }

    public int getInt(@NonNull final String key, final int defaultValue) {
        try {
            return getInt(key);
        } catch (ItemNotFoundException e) {
            return defaultValue;
        }
    }

    public int getInt(@NonNull final String key) throws ItemNotFoundException {
        final String value = getString(key);
        throwForNullValue(value, Integer.class, key);
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new WrongTypeException(e);
        }
    }

    public long getLong(@NonNull final String key, final long defaultValue) {
        try {
            return getLong(key);
        } catch (ItemNotFoundException e) {
            return defaultValue;
        }
    }

    public long getLong(@NonNull final String key) throws ItemNotFoundException {
        final String value = getString(key);
        throwForNullValue(value, Long.class, key);
        try {
            return Long.parseLong(value);
        } catch (NumberFormatException e) {
            throw new WrongTypeException(e);
        }
    }


    public String getString(@NonNull final String key) throws ItemNotFoundException {
        String value = preferencesHelper.query(moduleName, key);
        if (value == null) {
            throw new ItemNotFoundException(String.format("Value for Key <%s> not found", key));
        }
        return value;
    }

    @Nullable
    public String getString(@NonNull final String key, final String defaultValue) {
        try {
            return getString(key);
        } catch (ItemNotFoundException e) {
            return defaultValue;
        }
    }

    public int remove(@NonNull final String key) {
        return preferencesHelper.remove(moduleName, key);
    }

    public int clear() {
        return preferencesHelper.clear(moduleName);
    }

    public List<PreferenceItem> getAll() {
        return preferencesHelper.getAll(moduleName);
    }

    /**
     * logs a warning that warns that the given value for the given key is null and null is only
     * supported when reading it as a String and not other java primitives
     */
    private void throwForNullValue(@Nullable final String value,
                                   final Class<?> clazz, final @NonNull String key) throws WrongTypeException {
        if (value == null) {
            throw new WrongTypeException("The value for key <" + key + "> is null. "
                    + "You obviously saved this value as String and try to access it with type "
                    + clazz.getSimpleName() + " which cannot be null. "
                    + " Always use getString(key, defaultValue) when accessing data you saved with put(String).");
        }
    }
}

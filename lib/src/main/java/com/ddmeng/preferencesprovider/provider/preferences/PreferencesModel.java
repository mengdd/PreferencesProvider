package com.ddmeng.preferencesprovider.provider.preferences;

import com.ddmeng.preferencesprovider.provider.base.BaseModel;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Data model for the {@code preferences} table.
 */
public interface PreferencesModel extends BaseModel {

    /**
     * Get the {@code module} value.
     * Can be {@code null}.
     */
    @Nullable
    String getModule();

    /**
     * Get the {@code key} value.
     * Cannot be {@code null}.
     */
    @NonNull
    String getKey();

    /**
     * Get the {@code value} value.
     * Can be {@code null}.
     */
    @Nullable
    String getValue();
}

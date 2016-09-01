package com.ddmeng.preferencesprovider.provider;

import com.ddmeng.preferencesprovider.provider.preferences.PreferencesCursor;

public class PreferenceItem {
    private final String module;
    private final String key;
    private final String value;

    public PreferenceItem(String module, String key, String value) {
        this.module = module;
        this.key = key;
        this.value = value;
    }

    public PreferenceItem(PreferencesCursor cursor) {
        this.module = cursor.getModule();
        this.key = cursor.getKey();
        this.value = cursor.getValue();
    }

    public String getModule() {
        return module;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "PreferenceItem{" +
                "module='" + module + '\'' +
                ", key='" + key + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}

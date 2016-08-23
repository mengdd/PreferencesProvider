package com.ddmeng.preferencesprovider;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

import com.ddmeng.preferencesprovider.provider.PreferenceStorageModule;
import com.ddmeng.preferencesprovider.provider.PreferencesHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.key)
    EditText keyInput;
    @BindView(R.id.value)
    EditText valueInput;
    @BindView(R.id.value_output)
    TextView valueOutput;
    @BindView(R.id.query_data_output)
    TextView queryDataOutput;

    PreferencesHelper helper;
    PreferenceStorageModule preferenceStorageModule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        helper = new PreferencesHelper(this);
        preferenceStorageModule = new PreferenceStorageModule(this, "HelloModule1");
    }

    @OnClick(R.id.insert)
    void insert() {
        String key = keyInput.getText().toString();
        String value = valueInput.getText().toString();
        helper.insert("test module", key, value);
    }

    @OnClick(R.id.query)
    void query() {
        String key = keyInput.getText().toString();
        String value = helper.query("test module", key);
        valueOutput.setText(value);
    }

    @OnClick(R.id.insert_data)
    void insertData() {
        preferenceStorageModule.put("stringKey", "Hello");
        preferenceStorageModule.put("booleanKey1", true);
        preferenceStorageModule.put("booleanKey2", false);
        preferenceStorageModule.put("intKey", 123);
        preferenceStorageModule.put("floatKey", 1.5f);
        preferenceStorageModule.put("longKey", 123L);
    }

    @OnClick(R.id.query_data)
    void queryData() {
        // normal cases, existing data, no default value given
        String stringValue = preferenceStorageModule.getString("stringKey");
        boolean boolean1 = preferenceStorageModule.getBoolean("booleanKey1");
        boolean boolean2 = preferenceStorageModule.getBoolean("booleanKey2");
        int intValue = preferenceStorageModule.getInt("intKey");
        float floatValue = preferenceStorageModule.getFloat("floatKey");
        long longValue = preferenceStorageModule.getLong("longKey");

        String queryResult = "string: " + stringValue + "\n" +
                "boolean: " + boolean1 + "\n" +
                "boolean: " + boolean2 + "\n" +
                "int: " + intValue + "\n" +
                "float: " + floatValue + "\n" +
                "long: " + longValue + "\n";
        queryDataOutput.setText(queryResult);

    }
}

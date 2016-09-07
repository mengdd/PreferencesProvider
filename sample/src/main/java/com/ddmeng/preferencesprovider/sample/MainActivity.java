package com.ddmeng.preferencesprovider.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

import com.ddmeng.preferencesprovider.provider.PreferenceItem;
import com.ddmeng.preferencesprovider.provider.PreferencesHelper;
import com.ddmeng.preferencesprovider.provider.PreferencesStorageModule;
import com.ddmeng.preferencesprovider.provider.exception.ItemNotFoundException;
import com.ddmeng.preferencesprovider.utils.LogUtils;

import java.util.List;

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
    PreferencesStorageModule myModule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        helper = new PreferencesHelper(this);
        myModule = new PreferencesStorageModule(this, "HelloModule1");
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
        myModule.put("stringKey", "Hello");
        myModule.put("booleanKey1", true);
        myModule.put("booleanKey2", false);
        myModule.put("intKey", 123);
        myModule.put("floatKey", 1.5f);
        myModule.put("longKey", 123L);
    }

    @OnClick(R.id.query_data)
    void queryData() {
        // normal cases, existing data, no default value given, have to add try-catch block
        try {
            String stringValue = myModule.getString("stringKey");
            boolean boolean1 = myModule.getBoolean("booleanKey1");
            boolean boolean2 = myModule.getBoolean("booleanKey2");
            int intValue = myModule.getInt("intKey");
            float floatValue = myModule.getFloat("floatKey");
            long longValue = myModule.getLong("longKey");

            String queryResult = "string: " + stringValue + "\n" +
                    "boolean: " + boolean1 + "\n" +
                    "boolean: " + boolean2 + "\n" +
                    "int: " + intValue + "\n" +
                    "float: " + floatValue + "\n" +
                    "long: " + longValue + "\n";
            queryDataOutput.setText(queryResult);
        } catch (ItemNotFoundException e) {
            e.printStackTrace();
        }


    }

    @OnClick(R.id.query_data_not_exist)
    void queryDataNotExist() {
        // the data is not existed, no default value given
        try {
            String stringValue = myModule.getString("non-existing-key");
        } catch (ItemNotFoundException e) {
            e.printStackTrace();
            LogUtils.e("item not found for non-existing-key");
        }

        // the data is not existed, default values given

        String stringValue = myModule.getString("non-existing-key-2", "defaultValue");
        boolean boolean1 = myModule.getBoolean("non-existing-booleanKey1", false);
        boolean boolean2 = myModule.getBoolean("non-existing-booleanKey2", false);
        int intValue = myModule.getInt("non-existing-intKey", 0);
        float floatValue = myModule.getFloat("non-existing-floatKey", 0f);
        long longValue = myModule.getLong("non-existing-longKey", 0L);

        String queryResult = "string: " + stringValue + "\n" +
                "boolean: " + boolean1 + "\n" +
                "boolean: " + boolean2 + "\n" +
                "int: " + intValue + "\n" +
                "float: " + floatValue + "\n" +
                "long: " + longValue + "\n";
        queryDataOutput.setText(queryResult);

    }

    @OnClick(R.id.remove)
    void remove() {
        int count = myModule.remove("stringKey");
        queryDataOutput.setText(String.valueOf(count));
    }

    @OnClick(R.id.clear)
    void clear() {
        int count = myModule.clear();
        queryDataOutput.setText(String.valueOf(count));
    }

    @OnClick(R.id.get_all)
    void getAll() {
        List<PreferenceItem> items = myModule.getAll();
        String result = "get all-> count: " + items.size() + "\n";
        for (PreferenceItem item : items) {
            result += item.toString() + "\n";
        }
        queryDataOutput.setText(result);
    }
}

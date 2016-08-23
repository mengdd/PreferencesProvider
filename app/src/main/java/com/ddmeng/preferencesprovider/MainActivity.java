package com.ddmeng.preferencesprovider;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

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

    PreferencesHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        helper = new PreferencesHelper(this);
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
}

package com.example.android.myintentapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MoveWithDataActivity extends AppCompatActivity {

    public static String EXTRA_AGE = "extra_age";
    public static String EXTRA_NAME = "extra_name";
    private TextView tvDataReceived;


    public static String getExtraAge() {
        return EXTRA_AGE;
    }

    public static void setExtraAge(String extraAge) {
        EXTRA_AGE = extraAge;
    }

    public static String getExtraName() {
        return EXTRA_NAME;
    }

    public static void setExtraName(String extraName) {
        EXTRA_NAME = extraName;
    }

    public TextView getTvDataReceived() {
        return tvDataReceived;
    }

    public void setTvDataReceived(TextView tvDataReceived) {
        this.tvDataReceived = tvDataReceived;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_move_with_data);

        tvDataReceived = (TextView) findViewById(R.id.tv_data_received);
        String name = getIntent().getStringExtra(EXTRA_NAME);
        int age = getIntent().getIntExtra(EXTRA_AGE, 0);
        String text = "Name : " + name + ", Your Age : " + age;
        tvDataReceived.setText(text);
    }
}

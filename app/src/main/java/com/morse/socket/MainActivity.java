package com.morse.socket;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tvResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvResponse = findViewById(R.id.tv_response);
    }

    public void getRequest(View view) {
        // TODO: 2018/3/20 发送get请求 
    }

    public void postRequest(View view) {
        // TODO: 2018/3/20 发送post请求
    }
}

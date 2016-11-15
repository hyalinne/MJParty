package com.example.hyalinne.mjparty;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private boolean isLogin;

    public MainActivity() {
        isLogin = false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(!isLogin) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void findParty(View view) {
    }

    public void makeParty(View view) {

    }
}

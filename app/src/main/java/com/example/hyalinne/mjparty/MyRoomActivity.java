package com.example.hyalinne.mjparty;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MyRoomActivity extends AppCompatActivity {
    private TextView tv1;
    private TextView tv2;

    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_room);

        Intent intent = getIntent();
        email = intent.getStringExtra("email");

        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        httpThread t = new httpThread();
        t.start();
    }

    private class httpThread extends Thread {
        public void run() {
            String response = RoomHttp.getRoom("http://52.79.82.56/party_user/ulist", email);
            // party list >> ArrayList<object>
            try {
                JSONArray jsonArray = new JSONArray(response);
                ArrayList<JSONObject> objects = new ArrayList<>();
                for(int i =0; i < jsonArray.length(); i++) {
                    objects.add(i, jsonArray.getJSONObject(i));
                }
                tv1.setText(objects.get(0).getString("email"));
                tv2.setText(objects.get(1).getString("email"));

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

}

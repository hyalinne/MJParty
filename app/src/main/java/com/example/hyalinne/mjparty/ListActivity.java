package com.example.hyalinne.mjparty;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    private TextView tv1;
    private TextView tv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        httpThread t = new httpThread();
        t.start();
    }

    private class httpThread extends Thread {
        public void run() {
            String response = PartyHttp.getParty("http://52.79.82.56/partys/list");
            // party list >> ArrayList<object>
            try {
                JSONArray jsonArray = new JSONArray(response);
                ArrayList<JSONObject> objects = new ArrayList<>();
                for(int i =0; i < jsonArray.length(); i++) {
                    objects.add(i, jsonArray.getJSONObject(i));
                }
                tv1.setText(objects.get(0).getString("category"));
                tv2.setText(objects.get(1).getString("name"));

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}

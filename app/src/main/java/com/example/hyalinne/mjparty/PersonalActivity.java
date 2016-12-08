package com.example.hyalinne.mjparty;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PersonalActivity extends AppCompatActivity {
    private SharedPreferences pref;

    private EditText name;
    private EditText age;
    private EditText gender;
    private EditText major;

    private String email_txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);

        // 세팅
        pref = getSharedPreferences("MJParty", MODE_PRIVATE);
        TextView email = (TextView) findViewById(R.id.personal_email);
        email_txt = pref.getString("email", null);
        email.setText("E-Mail : " + email_txt);
        name = (EditText) findViewById(R.id.ps_name);
        age = (EditText) findViewById(R.id.ps_age);
        gender = (EditText) findViewById(R.id.ps_gender);
        major = (EditText) findViewById(R.id.ps_major);
        httpThread1 t = new httpThread1();
        t.start();
    }

    private class httpThread1 extends Thread {
        public void run() {
            String response = httpClient.getUser("http://52.79.82.56/users/getUser", email_txt);
            try {
                JSONArray jsoneArray = new JSONArray(response);
                JSONObject jsonObject = jsoneArray.getJSONObject(0);
                if (jsonObject != null) {
                    name.setText(jsonObject.getString("name"));
                    age.setText(String.valueOf(jsonObject.getInt("age")));
                    gender.setText(jsonObject.getString("gender"));
                    major.setText(jsonObject.getString("major"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public void modifyPS(View view) {
        httpThread2 t = new httpThread2();
        t.start();
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("new", false);
        editor.commit();
        this.finish();
    }

    private class httpThread2 extends Thread {
        public void run() {
            String response = httpClient.userModifyPost("http://52.79.82.56/users/updateUser", email_txt, pref.getString("passwd", null), name.getText().toString(), Integer.parseInt(age.getText().toString()), gender.getText().toString(), major.getText().toString());
        }
    }
}

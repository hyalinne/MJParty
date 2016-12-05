package com.example.hyalinne.mjparty;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class PersonalActivity extends AppCompatActivity {
    private SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);

        // 세팅
        pref = getSharedPreferences("MJParty", MODE_PRIVATE);
        TextView email = (TextView) findViewById(R.id.personal_email);
        email.setText("E-Mail : " + pref.getString("email", null));
        if(!pref.getBoolean("new", true)) {
            EditText name = (EditText) findViewById(R.id.ps_name);
            name.setText(pref.getString("name", null));
            EditText age = (EditText) findViewById(R.id.ps_age);
            age.setText(String.valueOf(pref.getInt("age", 0)));
            EditText gender = (EditText) findViewById(R.id.ps_gender);
            gender.setText(pref.getString("gender", null));
            EditText major = (EditText) findViewById(R.id.ps_major);
            major.setText(pref.getString("major", null));
        }
    }

    public void modifyPS(View view) {

    }
}

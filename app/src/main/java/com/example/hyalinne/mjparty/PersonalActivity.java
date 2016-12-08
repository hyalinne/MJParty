package com.example.hyalinne.mjparty;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PersonalActivity extends AppCompatActivity {
    private SharedPreferences pref;

    private EditText name;
    private EditText age;
    private EditText gender;
    private EditText major;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);

        // 세팅
        pref = getSharedPreferences("MJParty", MODE_PRIVATE);
        TextView email = (TextView) findViewById(R.id.personal_email);
        email.setText("E-Mail : " + pref.getString("email", null));
        name = (EditText) findViewById(R.id.ps_name);
        age = (EditText) findViewById(R.id.ps_age);
        gender = (EditText) findViewById(R.id.ps_gender);
        major = (EditText) findViewById(R.id.ps_major);
        if(!pref.getBoolean("new", true)) {
            name.setText(pref.getString("name", null));
            age.setText(String.valueOf(pref.getInt("age", 0)));
            gender.setText(pref.getString("gender", null));
            major.setText(pref.getString("major", null));
        }
    }

    public void modifyPS(View view) {
        User user = new User(pref.getString("email", null), pref.getString("passwd", null), name.getText().toString(), Integer.parseInt(age.getText().toString()), gender.getText().toString(), major.getText().toString());
        Toast.makeText(getApplicationContext(), user.getEmail() + user.getPasswd() + user.getName() + user.getAge() + user.getGender() + user.getMajor(), Toast.LENGTH_LONG).show();
        String response = httpClient.userModifyPost("http://52.79.82.56/users/updateUser", user);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("name", user.getName());
        editor.putInt("age", user.getAge());
        editor.putString("gender", user.getGender());
        editor.putString("major", user.getMajor());
        editor.commit();
        if (pref.getBoolean("new", true)) {
            editor.putBoolean("new", false);
            editor.commit();
        }
        this.finish();
    }
}

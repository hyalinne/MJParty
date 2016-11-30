package com.example.hyalinne.mjparty;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MakeRoomActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make);
    }

    public void makeParty(View view) {
        Intent makeRoom = new Intent(this, RoomActivity.class);
        EditText title = (EditText) findViewById(R.id.title);
        EditText subject = (EditText) findViewById(R.id.subject);
        EditText time = (EditText) findViewById(R.id.time);
        EditText number = (EditText) findViewById(R.id.number);

        try {
            final String[] roomInfo = {
                    title.getText().toString(),
                    subject.getText().toString(),
                    time.getText().toString(),
                    number.getText().toString() };
            makeRoom.putExtra("roomInfo", roomInfo);
            startActivity(makeRoom);
            this.finish();
        } catch (Exception e) {
            Toast.makeText(this, "입력란을 확인해 주세요", Toast.LENGTH_SHORT).show();
        }
    }

    public void makeCancel(View view) {
        this.finish();
    }
}
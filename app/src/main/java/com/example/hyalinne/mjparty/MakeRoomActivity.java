package com.example.hyalinne.mjparty;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

public class MakeRoomActivity extends AppCompatActivity {
    private String[] roomInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_make);
    }

    public void makeParty(View view) {
        Intent makeRoom = new Intent(this, RoomActivity.class);
        EditText title = (EditText) findViewById(R.id.title);
        EditText subject = (EditText) findViewById(R.id.subject);
        EditText time = (EditText) findViewById(R.id.time);
        EditText number = (EditText) findViewById(R.id.number);

        try {
            /* TODO : roomInfo : api, name, category, party_date, max_number, place */
            roomInfo = new String[]{
                    title.getText().toString(),
                    subject.getText().toString(),
                    time.getText().toString(),
                    number.getText().toString()};
            makeRoom.putExtra("roomInfo", roomInfo);
            httpThread t = new httpThread();
            t.start();
            startActivity(makeRoom);
            this.finish();
        } catch (Exception e) {
            Toast.makeText(this, "입력란을 확인해 주세요", Toast.LENGTH_SHORT).show();
        }
    }

    public void makeCancel(View view) {
        this.finish();
    }

    private class httpThread extends Thread {
        public void run() {
            PartyHttp.newParty("http://52.79.82.56/partys/new", roomInfo);
        }
    }
}
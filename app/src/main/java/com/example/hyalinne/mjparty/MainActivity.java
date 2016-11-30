package com.example.hyalinne.mjparty;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 로그인 확인
        // 로그인 기록이 없으면 로그인 화면을 띄움
        pref = getPreferences(MODE_PRIVATE);
        if(!pref.getBoolean("login", false)) {
            login();
        }

        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    // 로그인 함수
    private void login() {
        startActivity(new Intent(this, LoginActivity.class));
        // 한 번 로그인하면 로그인 기록을 유지
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("login", true);
        editor.commit();
    }

    private void logout() {
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("login", false);
        editor.commit();
        startActivity(new Intent(this, LoginActivity.class));
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu, menu);
//        return true;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_personal) {
            Toast.makeText(getApplicationContext(), "personal", Toast.LENGTH_LONG).show();
        } else if (id == R.id.nav_party) {
            Toast.makeText(getApplicationContext(), "party", Toast.LENGTH_LONG).show();
        } else if (id == R.id.nav_setting) {
            Toast.makeText(getApplicationContext(), "setting", Toast.LENGTH_LONG).show();
            Intent settingIntent = new Intent(this, SettingsActivity.class);
            startActivity(settingIntent);
        } else if (id == R.id.nav_logout) {
            logout();
            Toast.makeText(getApplicationContext(), "logout", Toast.LENGTH_LONG).show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void findParty(View view) {

    }

    public void makeParty(View view) {
        Intent makeParty = new Intent(this, MakeRoomActivity.class);
        startActivity(makeParty);
    }

    public void openMap(View view) {
        Intent mapIntent = new Intent(this, MapsActivity.class);
        startActivity(mapIntent);
    }
}

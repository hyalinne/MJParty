package com.example.hyalinne.mjparty;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private SharedPreferences pref;

    private BackPressCloseHandler backPressCloseHandler;

    public final static String VIDEO_URL = "http://blogattach.naver.com/49dc55e5f1a9ad715fb3dbe9d734493591c53ad4da/20161207_230_blogfile/heamin0_1481041770222_Gz4855_mp4/%BD%C5%BA%F1%C7%D1+%B5%BF%B9%B0%BB%E7%C0%FC++FANTASTIC+BEASTS+AND+WHERE+TO+FIND+THEM++4%C2%F7+%B0%F8%BD%C4+%BF%B9%B0%ED%C6%ED+%28%C7%D1%B1%B9%BE%EE+CC%29.mp4?type=attachment";
    public final static int URL = 1;
    public final static int SDCARD = 2;
    private VideoView videoView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        backPressCloseHandler = new BackPressCloseHandler(this);

        // 로그인 확인
        // 로그인 기록이 없으면 로그인 화면을 띄움
        pref = getSharedPreferences("MJParty", MODE_PRIVATE);
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

        //레이아웃 위젯 findViewById
        videoView = (VideoView) findViewById(R.id.view);
        //미디어컨트롤러 추가하는 부분
        MediaController controller = new MediaController(MainActivity.this);
        videoView.setMediaController(controller);

        //비디오뷰 포커스를 요청함
        videoView.requestFocus();

        int type = URL;
        switch (type) {
            case URL:
                //동영상 경로가 URL일 경우
                videoView.setVideoURI(Uri.parse(VIDEO_URL));
                break;

            case SDCARD:
                //동영상 경로가 SDCARD일 경우
                //String path = Environment.getExternalStorageDirectory() + "/movie.mp4";
                videoView.setVideoPath("/sdcard/movie.mp4");
                break;
        }


        //동영상이 재생준비가 완료되었을 때를 알 수 있는 리스너
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                Toast.makeText(MainActivity.this,
                        "동영상이 준비되었습니다.", Toast.LENGTH_SHORT).show();

                playVideo();
            }
        });

        //동영상 재생이 완료된 걸 알 수 있는 리스너
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                //동영상 재생이 완료된 후 호출되는 메소드
                Toast.makeText(MainActivity.this,
                        "동영상 재생이 완료되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        Intent intent = getIntent();
        if(intent != null) {
            String address = intent.getStringExtra("address");
            TextView tv = (TextView) findViewById(R.id.currentlocation);
            tv.setText(address);
        }
    }

    //동영상 재생 Method
    private void playVideo() {
        //비디오를 처음부터 재생할 때 0으로 시작(파라메터 sec)
        videoView.seekTo(0);
        videoView.start();
    }

    //동영상 정지 Method
    private void stopVideo() {
        //비디오 재생 잠시 멈춤
        videoView.pause();
        //비디오 재생 완전 멈춤
//        videoView.stopPlayback();
        //videoView를 null로 반환 시 동영상의 반복 재생이 불가능
//        videoView = null;

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
        backPressCloseHandler.onBackPressed();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

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
            startActivity(new Intent(this, PersonalActivity.class));
        } else if (id == R.id.nav_party) {
        } else if (id == R.id.nav_setting) {
            startActivity(new Intent(this, SettingsActivity.class));
        } else if (id == R.id.nav_logout) {
            logout();
            Toast.makeText(getApplicationContext(), "logout", Toast.LENGTH_LONG).show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void findParty(View view) {
        String email = pref.getString("email", null);
        Toast.makeText(getApplicationContext(), "User " + email, Toast.LENGTH_LONG).show();
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

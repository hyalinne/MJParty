package com.example.hyalinne.mjparty;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pref = getPreferences(MODE_PRIVATE);
        if(!pref.getBoolean("login", false)) {
            startActivity(new Intent(this, LoginActivity.class));
        }
        setContentView(R.layout.activity_main);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("login", true);
        editor.commit();
    }

    private void startLocationService() {
        LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        try {
            Location lastLocation = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (lastLocation != null) {
                Double latitude = lastLocation.getLatitude();
                Double longitude = lastLocation.getLongitude();
                Toast.makeText(getApplicationContext(), "Last Known Loacation" + latitude + "\t" + longitude, Toast.LENGTH_LONG).show();
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        startLocationService();
    }

    public void findParty(View view) {
        startLocationService();
    }

    public void makeParty(View view) {
        Intent makeParty = new Intent(this, MakeRoomActivity.class);
        startActivity(makeParty);
    }

    public void openMap(View view) {
        Intent mapIntent = new Intent(this, MapsActivity.class);
        startActivity(mapIntent);
    }

//    private class GPSListener implements LocationListener {
//        public void onLocationChanged(Location location) {
//            Double latitude = location.getLatitude();
//            Double longitude = location.getLongitude();
//            Toast.makeText(getApplicationContext(), latitude + "\t" +  longitude, Toast.LENGTH_SHORT).show();
//        }
//
//        @Override
//        public void onStatusChanged(String provider, int status, Bundle extras) { }
//
//        @Override
//        public void onProviderEnabled(String provider) { }
//
//        @Override
//        public void onProviderDisabled(String provider) { }
//    }
}

package com.techno.getlocation.Screens;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.techno.getlocation.Global.AppLog;
import com.techno.getlocation.Model.MyLatLong;
import com.techno.getlocation.R;
import com.techno.getlocation.Screens.Common.GetLocationBaseActivity;
import com.techno.getlocation.Service.LocationService;

import java.text.DecimalFormat;

public class MainActivity extends GetLocationBaseActivity implements View.OnClickListener {
    TextView tvCLocation, tvLiveLocation;
    Button btnLocation, btnStart, btnStop;
    LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            initViews();
            lieteners();
           // startService(new Intent(getBaseContext(), LocationService.class));
//            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//                return;
//            }
//            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, new LocationListener() {
//                @Override
//                public void onLocationChanged(Location location) {
//                    DecimalFormat df = new DecimalFormat("#.000000");
//                    double latitude = Double.valueOf(df.format(location.getLatitude()));
//                    double longitude = Double.valueOf(df.format(location.getLongitude()));
//
//                    AppLog.e("From Lat==>" + "" + latitude);
//                    AppLog.e("From Long==>" + "" + longitude);
//                    ///adding  into array
//                    myLatLongArrayList.add(new MyLatLong(latitude, longitude));
//
//                    StringBuilder builder = new StringBuilder();
//                    for (MyLatLong details : myLatLongArrayList) {
//                        builder.append(details.getLat() + "," + details.getLon() + "\n");
//                    }
//                    tvLiveLocation.setText(builder.toString());
//                }
//
//                @Override
//                public void onStatusChanged(String s, int i, Bundle bundle) {
//
//                }
//
//                @Override
//                public void onProviderEnabled(String s) {
//
//                }
//
//                @Override
//                public void onProviderDisabled(String s) {
//
//                }
//            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
       // stopService(new Intent(getBaseContext(), LocationService.class));
    }

    public void initViews() {
        try {
            tvCLocation = findViewById(R.id.tvCLocation);
            tvLiveLocation = findViewById(R.id.tvLiveLocation);
            btnLocation = findViewById(R.id.btnLocation);
            btnStart = findViewById(R.id.btnStart);
            btnStop = findViewById(R.id.btnStop);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void lieteners() {
        try {
            btnLocation.setOnClickListener(this);
            btnStart.setOnClickListener(this);
            btnStop.setOnClickListener(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View view) {
        try {
            switch (view.getId()) {
                case R.id.btnLocation:
                    tvCLocation.setText("Currant Lat-Long" + "\n" + currantLatLong());
                    break;

                case R.id.btnStart:
                    startService(new Intent(getBaseContext(), LocationService.class));
                    break;
                case R.id.btnStop:
                    stopService(new Intent(getBaseContext(), LocationService.class));
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();


        }
    }
}

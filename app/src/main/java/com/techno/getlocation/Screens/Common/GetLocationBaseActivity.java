package com.techno.getlocation.Screens.Common;

import android.location.Location;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.techno.getlocation.GetLocationApplication;
import com.techno.getlocation.Global.AppLog;
import com.techno.getlocation.Model.MyLatLong;
import com.techno.getlocation.Utils.Location.LocationHelper;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by Arbaz.
 * Date: 8/5/18
 * Time: 5:50 PM
 */
public class GetLocationBaseActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    public LocationHelper locationHelper;
    public Location mLastLocation;
    public ArrayList<MyLatLong> myLatLongArrayList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            locationHelper = new LocationHelper(this);
            locationHelper.checkpermission();

            // check availability of play services
            if (locationHelper.checkPlayServices()) {
                // Building the GoogleApi client
                locationHelper.buildGoogleApiClient();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        // Once connected with google api, get the location
        mLastLocation = locationHelper.getLocation();
        if (mLastLocation != null) {
            GetLocationApplication.latitude = mLastLocation.getLatitude();
            GetLocationApplication.longitude = mLastLocation.getLongitude();
            //     Toast.makeText(this, "FromBase activity:" + "lat:" + mLastLocation.getLongitude()+ "," + "long:" + mLastLocation.getLongitude(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        locationHelper.connectApiClient();
    }

    /**
     * Google api callback methods
     */
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        AppLog.e("Connection failed:" + " ConnectionResult.getErrorCode() = "
                + connectionResult.getErrorCode());
    }

    // Permission check functions
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        // redirects to utils
        locationHelper.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public String currantLatLong() {
        String cLatLong = "";
        try {

            if (mLastLocation != null) {
                DecimalFormat df = new DecimalFormat("#.000000");
                double latitude = Double.valueOf(df.format(mLastLocation.getLatitude()));
                double longitude = Double.valueOf(df.format(mLastLocation.getLongitude()));
                Log.e("Currant Lat==>", "" + latitude);
                Log.e("Currant Long==>", "" + longitude);
                if (latitude != 0.0 && longitude != 0.0) {
                    cLatLong = "" + latitude + "," + longitude;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cLatLong;
    }
}

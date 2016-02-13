package com.mini2assignment4.ui;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mini2assignment4.R;
import com.mini2assignment4.model.GPSInfo;

import java.util.Map;

/**
 * Created by xlin2 on 2015/11/16.
 */
public class SendInfoOnClickListener implements View.OnClickListener {
    private Activity activity;
    private MapView mapView;

    private final String phoneNumber = "4123535959";

    public SendInfoOnClickListener(Activity activity, MapView mapView) {
        this.activity = activity;
        this.mapView = mapView;
    }

    @Override
    public void onClick(View v) {
        GPSInfo info = new GPSInfo(activity);
        if (info.isCanGetLocation()) {
            double longitude = info.getLongitude();
            double latitude = info.getLatitude();

            // update textview
            TextView textLongitude = (TextView) activity.findViewById(R.id.txt_logitude);
            TextView textLatitude = (TextView) activity.findViewById(R.id.txt_latitude);

            textLongitude.setText(String.format("%.4f", longitude));
            textLatitude.setText(String.format("%.4f", latitude));

            // send sms message
            sendMessage(longitude, latitude);

            // add a mark on map according to latitude and longitude
            configureMap(mapView.getMap(), latitude, longitude);
        }

        // not able to get location
        else {
            Toast.makeText(activity.getApplicationContext(), "Cannot get location", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Move the camera of the map according to latitude and longitude
     * @param map: map object
     * @param lat: latitude
     * @param lon: longitude
     */
    private void configureMap(GoogleMap map, double lat, double lon)
    {
        try {
            LatLng curLoc = new LatLng(lat, lon);
            map.moveCamera(CameraUpdateFactory.newLatLng(curLoc));
            map.addMarker(new MarkerOptions().position(curLoc).title("Marker in Tilbury"));
        } catch (Exception e) {
            Toast.makeText(activity.getApplicationContext(),
                    "No google play service, cannot show map. ", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Send longitude and latitude information to the destination phone number
     * @param longitude
     * @param latitude
     */
    private void sendMessage(double longitude, double latitude) {
        Toast.makeText(activity.getApplicationContext(),
                "Send location to " + phoneNumber,
                Toast.LENGTH_LONG).show();
        String message = "\tLocation:" +
                         "\n\tLongitude: " + String.format("%.4f", longitude) +
                         "\n\tLatitude: " + String.format("%.4f", latitude);
        SmsManager manager = SmsManager.getDefault();
        manager.sendTextMessage(phoneNumber, null, message, null, null);
    }
}

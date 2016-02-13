package com.mini2assignment4.model;

import android.app.Service;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

import com.mini2assignment4.exception.ErrType;
import com.mini2assignment4.exception.GPSException;

/**
 * Created by xlin2 on 2015/11/16.
 */
public class GPSInfo implements LocationListener {
    private final Context context;

    // parameters to be passed into requestLocationUpdates
    private final long MIN_DIST = 10;
    private final long MIN_TIME = 6000;

    // whether user has enabled service
    private boolean isGPSEnabled = false;
    private boolean isNetworkEnabled = false;
    private boolean canGetLocation = false;

    private double latitude;
    private double longitude;
    private LocationManager manager;
    private Location location;

    public GPSInfo(Context context) {
        this.context = context;
        getLocation();
    }

    /**
     * Whether use have enabled GPS
     * @return
     */
    public boolean isGPSEnabled() {
        return isGPSEnabled;
    }

    /**
     * Whether user have enabled network
     * @return
     */
    public boolean isNetworkEnabled() {
        return isNetworkEnabled;
    }

    public boolean isCanGetLocation() {
        return canGetLocation;
    }

    /**
     * Get the value of latitude
     */
    public double getLatitude() {
        if (location != null)
            latitude = location.getLatitude();
        return latitude;
    }

    /**
     * Get the value of longitude
     */
    public double getLongitude() {
        if (location != null)
            longitude = location.getLongitude();
        return longitude;
    }

    /**
     * Get location manager and try to get the location information
     * @return location information
     */
    public Location getLocation() {
        try {
            manager = (LocationManager) context.getSystemService(Service.LOCATION_SERVICE);

            if (manager == null)
                throw new GPSException(ErrType.NO_LOC_MANAGER);

            // whether user have enabled gps and network
            isGPSEnabled = manager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            isNetworkEnabled = manager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

            if (!isGPSEnabled && !isNetworkEnabled) {
                canGetLocation = false;
                throw new GPSException(ErrType.NO_SERVIE);
            } else {
                canGetLocation = true;

                // get location from gps
                if (isGPSEnabled) {
                    manager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                                                    MIN_TIME,
                                                    MIN_DIST,
                                                    this);
                    location = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                }

                // get location from network
                else {
                    manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                                                    MIN_TIME,
                                                    MIN_DIST,
                                                    this);
                    location = manager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                }
            }

        } catch (GPSException e) {
            e.handle();
            Toast.makeText(context,
                    "Can not get Location because " + e.getErrMsg(),
                    Toast.LENGTH_LONG).show();
            return null;
        }

        return location;
    }

    @Override
    public void onLocationChanged(Location location) {
        getLatitude();
        getLocation();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        getLatitude();
        getLocation();
    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}

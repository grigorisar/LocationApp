package gr.hua.grigorisar.locationapplication;

import android.content.ContentValues;
import android.widget.Toast;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;



public class LocationService extends Service {

    final LocationOpenHelper helper = new LocationOpenHelper(LocationService.this);

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
//        Toast.makeText(this, "IN LOCATION SERVICE", Toast.LENGTH_LONG).show();

        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {


            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 10, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    addLocation(location);
                }

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {

                }

                @Override
                public void onProviderEnabled(String provider) {
                }

                @Override
                public void onProviderDisabled(String provider) {

                }
            });
        }else{
            Toast.makeText(this, "CANNOT ACCESS LOCATION", Toast.LENGTH_SHORT).show();
            System.out.println("CANNOT ACCESS LOCATION");
            return START_STICKY;
        }


        return START_STICKY;

    }
    public void addLocation(Location location) {
        ContentValues values = new ContentValues();
        values.put(LocationOpenHelper.KEY_LAT, location.getLatitude());
        values.put(LocationOpenHelper.KEY_LON, location.getLongitude());
        values.put(LocationOpenHelper.KEY_TIME, location.getTime());
        //                Toast.makeText(LocationService.this, "Values:"+values, Toast.LENGTH_SHORT).show();
        System.out.println("Values: " + values);

        try {
            helper.getReadableDatabase().insert(LocationOpenHelper.TABLE, null, values);
            System.out.println("Location Captured");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Internal Error Cannot pass");
        }
    }

}
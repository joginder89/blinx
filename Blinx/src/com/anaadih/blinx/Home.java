package com.anaadih.blinx;

import android.app.Activity;
import android.os.Bundle;

import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.GoogleMap;
import android.widget.Toast;
import android.content.Context;

import android.view.Menu;
//import android.view.MenuItem;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

public class Home extends Activity implements LocationListener{
	 
    // Google Map
    private GoogleMap googleMap;
    private LocationManager locationManager;
    private static final long MIN_TIME = 400;
    private static final float MIN_DISTANCE = 1000;
//    private final LatLng map_center = new LatLng(21.3,-157.825);
     
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        
     
       // googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
 
        try {
            initilizeMap();
 
        } catch (Exception e) {
            e.printStackTrace();
        }
 
    }
 
    /**
     * function to load map. If map is not created it will create it for you
     * */
    private void initilizeMap() {
        if (googleMap == null) {
            googleMap = ((MapFragment) getFragmentManager().findFragmentById(
                    R.id.map)).getMap();
            googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            // Enable or disable current location
            googleMap.setMyLocationEnabled(true);
            googleMap.getUiSettings().setMyLocationButtonEnabled(true);
            googleMap.getUiSettings().setRotateGesturesEnabled(true);
            
            locationManager = (LocationManager)this.getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, MIN_TIME, MIN_DISTANCE, this);
    		
            // check if map is created successfully or not
            if (googleMap == null) {
                Toast.makeText(getApplicationContext(),
                        "Sorry! unable to create maps", Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }
 
    @Override
    protected void onResume() {
        super.onResume();
        initilizeMap();
    }
    
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to action bar if present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
     }
    @Override
    public void onLocationChanged(Location location) {
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        
        // Move camera view and zoom to location
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 13));
        
        Toast.makeText(getApplicationContext(),
                "InSideLocationChanged=>"+latLng, Toast.LENGTH_SHORT).show();
        
        //locationManager.removeUpdates(this);

    }

    @Override
    public void onProviderDisabled(String provider) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onProviderEnabled(String provider) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub

    }
}
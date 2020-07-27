package com.example.sara.navigation;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.vision.barcode.Barcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

     GoogleMap mMap;
    Intent i;
    Bundle b;
    String s,s1;
    ArrayList<String> m=new ArrayList<String>();
    LatLng latLng;
    Barcode.GeoPoint p=null;
    ArrayList<MarkerOptions> markersArray = new ArrayList<MarkerOptions>();
   // String[] indirizzo;
   ArrayList<String> indirizzo=new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        i=getIntent();
        b=i.getExtras();
        m=b.getStringArrayList("mess");

        for (int i = 0; i < m.size(); i++ ){
                s=m.get(i);
            String[] lines= s.split("\\n");

             String ciao=lines[1];
            indirizzo.add(ciao.substring(0,ciao.length()-3));
           //  Toast.makeText(this ,indirizzo,Toast.LENGTH_SHORT).show();

            // Log.d("Main", "IL mEssaggio e :" + mess.get(i));

        }
//
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }

    private void getLatLongFromAddress(String address)
    {
        double lat= 0.0, lng= 0.0;

        Geocoder geoCoder = new Geocoder(this, Locale.getDefault());
        try
        {
            List<Address> addresses = geoCoder.getFromLocationName(address , 1);
            if (addresses.size() > 0)
            {
                Address add=addresses.get(0);
                 latLng= new LatLng(add.getLatitude(),add.getLongitude());

              /*  Barcode.GeoPoint p = new Barcode.GeoPoint(
                        (int) (addresses.get(0).getLatitude() * 1E6),
                        (int) (addresses.get(0).getLongitude() * 1E6));

                lat=p.getLatitudeE6()/1E6;
                lng=p.getLongitudeE6()/1E6;
*/
                Log.d("Latitude", ""+latLng);
                //Log.d("Longitude", ""+lng);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(40.773211, 14.796570);
       // mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Fisciano"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(15));
        //LatLng roma = new LatLng();

        for(int i=0; i<indirizzo.size(); i++)
        {
            s1=indirizzo.get(i) + " Fisciano";
            getLatLongFromAddress(s1);
            markersArray.add(new MarkerOptions().position(latLng).title(m.get(i)));
            mMap.addMarker(markersArray.get(i));
        }

        //indirizzo=indirizzo+" Fisciano";
       // getLatLongFromAddress(indirizzo);


     // MarkerOptions k=new MarkerOptions().position(latLng).title("hey").snippet(s);

        //mMap.addMarker(k);


    }



}

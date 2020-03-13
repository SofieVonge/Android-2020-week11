package com.example.mapdemo;

import androidx.fragment.app.FragmentActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.widget.EditText;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        builder = new AlertDialog.Builder(this);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        Repo.mMap = mMap;
        Log.i("load", "map was loaded");
        addDefaultPlaces();

        addPin();

    }



    private void addPin() {
        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(final LatLng latLng) {
                Log.i("click", "map was long clicked");
                //build the builder
                final EditText input = new EditText(MapsActivity.this);
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                builder.setTitle("Set title of pin")
                        .setView(input)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String title = input.getText().toString();
                        Repo.addNewPin(title, latLng);
                        mMap.addMarker(new MarkerOptions().position(latLng).title(title));

                    }
                })
                        .setNegativeButton("Cancel", null)
                        .show();

            }
        });
    }

    private void addDefaultPlaces() {
        // need to give minumum 3 decimals to be precise
        LatLng london = new LatLng(51.476853, -0.0005002);
        mMap.addMarker(new MarkerOptions().position(london).title("Marker in London"));
        // mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(london, 12)); //zoom the camera

        LatLng pizzeria = new LatLng(55.7146459,12.5707835);
        mMap.addMarker(new MarkerOptions().position(pizzeria).title("Pizze!"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pizzeria, 12));

        LatLng venue = new LatLng(55.6922751,12.6143473);
        mMap.addMarker(new MarkerOptions().position(venue).title("Copenhell"));
    }


}

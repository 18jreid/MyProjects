package com.example.maps;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.maps.databinding.ActivityMapsBinding;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

public class MapsActivity extends AppCompatActivity {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private Marker marker;
    private Polyline line;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ActivityResultLauncher<String> launcher = registerForActivityResult(
                new ActivityResultContracts.RequestPermission(),
                isGranted -> {
                    if (isGranted) {
                        System.out.println("User granted permission");
                    } else {
                        System.out.println("User denied permission");
                    }
                }
        );

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            System.out.println("User has already given us permission, setup location services");
            setupMap();
        } else {
            launcher.launch(Manifest.permission.ACCESS_FINE_LOCATION);
        }
    }

    private void setupMap() {
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync((map) -> {
            mMap = map;

            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
                fusedLocationProviderClient.getLastLocation().addOnCompleteListener((task) -> {
                    Location location = task.getResult();
                    CameraPosition pos = new CameraPosition.Builder()
                            .target(new LatLng(location.getLatitude(), location.getLongitude()))
                            .zoom(10)
                            .build();

                    mMap.animateCamera(CameraUpdateFactory.newCameraPosition(pos));

                    mMap.setOnMapClickListener(latLng -> {
                        if (marker != null) {
                            marker.remove();
                        }
                        if (line != null) {
                            line.remove();
                        }

                        marker = mMap.addMarker(
                                new MarkerOptions().position(latLng)
                        );
                        line = mMap.addPolyline(
                                new PolylineOptions()
                                    .add(
                                            new LatLng(location.getLatitude(), location.getLongitude()),
                                            latLng
                                    )
                                    .color(Color.GREEN)
                        );
                    });
                });

                mMap.setMyLocationEnabled(true);
//                mMap.setOnMapClickListener(latLng -> {
//                    mMap.addMarker(
//                            new MarkerOptions()
//                                .position(latLng)
//                                .draggable(true));
//                });
            }
        });
    }
}
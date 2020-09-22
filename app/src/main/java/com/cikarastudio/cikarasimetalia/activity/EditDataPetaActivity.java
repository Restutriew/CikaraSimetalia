package com.cikarastudio.cikarasimetalia.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.cikarastudio.cikarasimetalia.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class EditDataPetaActivity extends AppCompatActivity implements OnMapReadyCallback {

    Button btn_updateDataPeta;
    ImageView btn_backPeta;

    GoogleMap mMap;
    MapView mMapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data_peta);

        mMapView = (MapView) findViewById(R.id.map);
        if (mMapView != null) {
            mMapView.onCreate(null);
            mMapView.onResume();
            mMapView.getMapAsync(this);
        }

        btn_backPeta = findViewById(R.id.btn_backEditPeta);
        btn_backPeta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btn_updateDataPeta = findViewById(R.id.btn_updateDataPeta);
        btn_updateDataPeta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
//        mMap.setMapStyle(googleMap);

        // Add a marker in Sydney and move the camera

        mMap.addMarker(new MarkerOptions().position(new LatLng(-7.328082, 108.366358)).title("Dinas Kesehatan Kabupaten Ciamis"));
        CameraPosition sydney = CameraPosition.builder().target(new LatLng(-7.328082, 108.366358)).zoom(16).tilt(45).build();
        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(sydney));
    }
}
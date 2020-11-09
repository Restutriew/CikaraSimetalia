package com.cikarastudio.cikarasimetalia.maps;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.cikarastudio.cikarasimetalia.R;
import com.cikarastudio.cikarasimetalia.activity.bumil.DetailBumilActivity;
import com.cikarastudio.cikarasimetalia.adapter.MyBumilAdapter;
import com.cikarastudio.cikarasimetalia.model.Bumil;
import com.cikarastudio.cikarasimetalia.session.SessionManager;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class BumilMapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    SessionManager sessionManager;
    String id_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bumil_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        sessionManager = new SessionManager(BumilMapsActivity.this);
        HashMap<String, String> user = sessionManager.getUserDetail();
        id_user = user.get(sessionManager.ID);

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        String URL_READBOOKING = "http://simetalia.com/pkm/webservice/bumildesa/" + id_user;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_READBOOKING,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("read");

                            if (success.equals("1")) {
                                if (jsonArray.length() > 0) {
                                    for (int i = 0; i < jsonArray.length(); i++) {

                                        JSONObject object = jsonArray.getJSONObject(i);
                                        String nama_bumil = object.getString("nama_bumil").trim();
                                        String lat = object.getString("lat").trim();
                                        String longi = object.getString("longi").trim();
                                        String skrinning = object.getString("skrinning").trim();
                                        if (skrinning.equals("KRR")) {
                                            mMap.addMarker(new MarkerOptions()
                                                    .position(new LatLng(Double.parseDouble(lat), Double.parseDouble(longi)))
                                                    .title(nama_bumil)
                                                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_green))
                                                    .snippet("Status : " + skrinning));
                                        } else if (skrinning.equals("KRT")) {
                                            mMap.addMarker(new MarkerOptions()
                                                    .position(new LatLng(Double.parseDouble(lat), Double.parseDouble(longi)))
                                                    .title(nama_bumil)
                                                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_yellow))
                                                    .snippet("Status : " + skrinning));
                                        } else if (skrinning.equals("KRST")) {
                                            mMap.addMarker(new MarkerOptions()
                                                    .position(new LatLng(Double.parseDouble(lat), Double.parseDouble(longi)))
                                                    .title(nama_bumil)
                                                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_red))
                                                    .snippet("Status : " + skrinning));
                                        } else if (skrinning.equals("belum ada data")){
                                            mMap.addMarker(new MarkerOptions()
                                                    .position(new LatLng(Double.parseDouble(lat), Double.parseDouble(longi)))
                                                    .title(nama_bumil)
                                                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE))
                                                    .snippet("Status : " + skrinning));
                                        }

                                        CameraPosition sydneycam = CameraPosition.builder().target(new LatLng(-7.370145700628985, 108.42473219087225)).zoom(12).tilt(45).build();
                                        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(sydneycam));
                                    }
                                } else {
                                    Toast.makeText(BumilMapsActivity.this, "Data Peta Tidak Ada!", Toast.LENGTH_SHORT).show();
                                }
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(BumilMapsActivity.this, "Data Peta Tidak Ada!" + e.toString(), Toast.LENGTH_SHORT).show();
                        }

                    }
                }
                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(BumilMapsActivity.this, "Koneksi Gagal!" + error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
        };
        int socketTimeout = 10000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(policy);
        RequestQueue requestQueue = Volley.newRequestQueue(BumilMapsActivity.this);
        requestQueue.add(stringRequest);
    }
}
package com.cikarastudio.cikarasimetalia.activity.bumil;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.cikarastudio.cikarasimetalia.R;
import com.cikarastudio.cikarasimetalia.dialog.LoadingDialog;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class EditDataPetaActivity extends AppCompatActivity {

    String id_bumil;
    LoadingDialog loadingDialog;
    ImageView btn_backPeta;
    TextView tv_latitude, tv_longitude;
    LinearLayout btn_getLocation, btn_updateDataPeta;
    private static final int REQUEST_CODE_LOCATION_PERMISSION = 1;
    public static final String ID_BUMIL = "extra_data";
    private static final String URL_UPDATELATLONG = "http://simetalia.com/android/update_latlong.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data_peta);

        loadingDialog = new LoadingDialog(EditDataPetaActivity.this);
        loadingDialog.startLoading();

        Intent intent = getIntent();
        id_bumil = intent.getStringExtra(ID_BUMIL);

        tv_latitude = findViewById(R.id.tv_latitude);
        tv_longitude = findViewById(R.id.tv_longitude);

        btn_backPeta = findViewById(R.id.btn_backEditPeta);
        btn_backPeta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btn_getLocation = findViewById(R.id.btn_getLocation);
        btn_updateDataPeta = findViewById(R.id.btn_updateDataPeta);

        getDataLatLong();

        btn_updateDataPeta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateDataPeta();
            }
        });

        btn_getLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(
                        getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(
                            EditDataPetaActivity.this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                            REQUEST_CODE_LOCATION_PERMISSION);
                } else {
                    getCurrentLocation();
                }
            }
        });
    }

    private void updateDataPeta() {
        loadingDialog.startLoading();
        final String latitude = tv_latitude.getText().toString();
        final String longitude = tv_longitude.getText().toString();

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        final String updated_at = dateFormat.format(cal.getTime());


        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_UPDATELATLONG,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")) {
                                Toast.makeText(EditDataPetaActivity.this, "Update Peta Sukses", Toast.LENGTH_LONG).show();
                                loadingDialog.dissmissDialog();
                            }else if (success.equals("0")){
                                Toast.makeText(EditDataPetaActivity.this, "Update Peta Gagal", Toast.LENGTH_LONG).show();
                                loadingDialog.dissmissDialog();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(EditDataPetaActivity.this, "Update Peta Gagal!"+e.toString(), Toast.LENGTH_LONG).show();
                            loadingDialog.dissmissDialog();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(EditDataPetaActivity.this, "Update Peta Gagal! : Cek Koneksi Anda", Toast.LENGTH_LONG).show();
                        loadingDialog.dissmissDialog();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                params.put("id", id_bumil);
                params.put("latitude", latitude);
                params.put("longitude", longitude);
                params.put("updated_at", updated_at);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void getDataLatLong() {
        String URL_READBOOKING = "http://simetalia.com/pkm/webservice/bumil/" + id_bumil;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_READBOOKING,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONObject object = jsonObject.getJSONObject("read");

                            if (success.equals("1")) {
                                String get_latitud = object.getString("lat").trim();
                                String get_longitud = object.getString("longi").trim();

                                if (get_latitud.equals("null")) {
                                    tv_latitude.setText("Tidak Ada Data");
                                    tv_longitude.setText("Tidak Ada Data");
                                } else {
                                    tv_latitude.setText(get_latitud);
                                    tv_longitude.setText(get_longitud);
                                }

                                loadingDialog.dissmissDialog();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(EditDataPetaActivity.this, "Data Peta Tidak Ada!" + e.toString(), Toast.LENGTH_SHORT).show();
                            loadingDialog.dissmissDialog();
                        }

                    }
                }
                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(EditDataPetaActivity.this, "Koneksi Gagal!" + error.toString(), Toast.LENGTH_SHORT).show();
                loadingDialog.dissmissDialog();
            }
        }) {
        };
        RequestQueue requestQueue = Volley.newRequestQueue(EditDataPetaActivity.this);
        requestQueue.add(stringRequest);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_LOCATION_PERMISSION && grantResults.length > 0) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getCurrentLocation();
            } else {
                Toast.makeText(this, "Permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void getCurrentLocation() {
        loadingDialog.startLoading();

        final LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(1000);
        locationRequest.setFastestInterval(3000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        LocationServices.getFusedLocationProviderClient(EditDataPetaActivity.this).
                requestLocationUpdates(locationRequest, new LocationCallback() {
                    @Override
                    public void onLocationResult(LocationResult locationResult) {
                        super.onLocationResult(locationResult);
                        LocationServices.getFusedLocationProviderClient(EditDataPetaActivity.this)
                                .removeLocationUpdates(this);
                        if (locationResult != null && locationResult.getLocations().size() > 0) {
                            int latestLocationIndex = locationResult.getLocations().size() - 1;
                            double latitude =
                                    locationResult.getLocations().get(latestLocationIndex).getLatitude();
                            double longitude =
                                    locationResult.getLocations().get(latestLocationIndex).getLongitude();
                            tv_latitude.setText(String.valueOf(latitude));
                            tv_longitude.setText(String.valueOf(longitude));
                        }
                        loadingDialog.dissmissDialog();
                    }
                }, Looper.getMainLooper());
    }

}


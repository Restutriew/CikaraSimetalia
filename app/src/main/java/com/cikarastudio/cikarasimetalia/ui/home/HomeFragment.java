package com.cikarastudio.cikarasimetalia.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.anychart.APIlib;
import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;
import com.cikarastudio.cikarasimetalia.R;
import com.cikarastudio.cikarasimetalia.dialog.LoadingDialog;
import com.cikarastudio.cikarasimetalia.session.SessionManager;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HomeFragment extends Fragment implements OnMapReadyCallback {

    TextView tv_berandaDataBumil, tv_berandaJumlahKehamilan, tv_berandaJumlahKRST, tv_berandaPemeriksaanHariIni;
    LoadingDialog loadingDialog;
    SessionManager sessionManager;
    String id_user;
    GoogleMap mMap;
    MapView mMapView;
    View root;
    AnyChartView anyChartViewResiko, anyChartViewBPJS;

    String[] jenisResiko = {"KRST", "KRT", "KRR"};
    int[] jumlahResiko = {112, 223, 800};

    String[] jenisBPJS = {"Memiliki", "Tidak Memiliki", "Asuransi Lainnya"};
    int[] jumlahBPJS = {211, 423, 500};

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_home, container, false);

        sessionManager = new SessionManager(getActivity());
        sessionManager.checkLogin();
        HashMap<String, String> user = sessionManager.getUserDetail();
        id_user = user.get(sessionManager.ID);

        tv_berandaDataBumil = root.findViewById(R.id.tv_berandaDataBumil);
        tv_berandaJumlahKehamilan = root.findViewById(R.id.tv_berandaJumlahKehamilan);
        tv_berandaJumlahKRST = root.findViewById(R.id.tv_berandaJumlahKRST);
        tv_berandaPemeriksaanHariIni = root.findViewById(R.id.tv_berandaPemeriksaanHariIni);

        loadingDialog = new LoadingDialog(getActivity());
        loadingDialog.startLoading();

        load_dataBeranda();

        anyChartViewResiko = root.findViewById(R.id.chartResiko);
        APIlib.getInstance().setActiveAnyChartView(anyChartViewResiko);
        setupchartResiko();

        anyChartViewBPJS = root.findViewById(R.id.chartBPJS);
        APIlib.getInstance().setActiveAnyChartView(anyChartViewBPJS);
        setupchartBPJS();

        return root;


    }

    private void load_dataBeranda() {
        String URL_READBOOKING = "http://simetalia.com/pkm/webservice/dashboard/"+id_user;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_READBOOKING,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONObject read = jsonObject.getJSONObject("read");
                            if (success.equals("1")) {

                                String bumil = read.getString("bumil").trim();
                                String kehamilan = read.getString("kehamilan").trim();
                                String kunjungan = read.getString("kunjungan").trim();
                                String pemeriksaan = read.getString("pemeriksaan").trim();

                                tv_berandaDataBumil.setText(bumil);
                                tv_berandaJumlahKehamilan.setText(kehamilan);
                                tv_berandaJumlahKRST.setText(kunjungan);
                                tv_berandaPemeriksaanHariIni.setText(pemeriksaan);

                                //hilangkan loading
                                loadingDialog.dissmissDialog();

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            loadingDialog.dissmissDialog();
                            Toast.makeText(getContext(), "Data Akun Tidak Ada!" + e.toString(), Toast.LENGTH_LONG).show();
                        }

                    }
                }
                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loadingDialog.dissmissDialog();
                Toast.makeText(getContext(), "Tidak Ada Koneksi Internet!" + error.toString(), Toast.LENGTH_LONG).show();
            }
        })
        {
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }

    private void setupchartBPJS() {
        Pie pie = AnyChart.pie();
        List<DataEntry> dataEntries = new ArrayList<>();

        for (int i = 0; i < jenisBPJS.length; i++) {
            dataEntries.add(new ValueDataEntry(jenisBPJS[i], jumlahBPJS[i]));
        }
        pie.data(dataEntries);
        pie.title("Asuransi Kesehatan");
        anyChartViewBPJS.setChart(pie);
    }

    private void setupchartResiko() {
        Pie pie = AnyChart.pie();
        List<DataEntry> dataEntries = new ArrayList<>();

        for (int i = 0; i < jenisResiko.length; i++) {
            dataEntries.add(new ValueDataEntry(jenisResiko[i], jumlahResiko[i]));
        }
        pie.data(dataEntries);
        pie.title("Resiko");
        anyChartViewResiko.setChart(pie);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mMapView = (MapView) root.findViewById(R.id.map);
        if (mMapView != null) {
            mMapView.onCreate(null);
            mMapView.onResume();
            mMapView.getMapAsync(this);
        }

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
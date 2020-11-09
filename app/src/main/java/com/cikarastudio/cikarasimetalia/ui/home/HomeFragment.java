package com.cikarastudio.cikarasimetalia.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

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
import com.cikarastudio.cikarasimetalia.maps.BumilMapsActivity;
import com.cikarastudio.cikarasimetalia.maps.MapsActivity;
import com.cikarastudio.cikarasimetalia.R;
import com.cikarastudio.cikarasimetalia.dialog.LoadingDialog;
import com.cikarastudio.cikarasimetalia.session.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HomeFragment extends Fragment {

    TextView tv_berandaDataBumil, tv_berandaJumlahKehamilan, tv_berandaJumlahKRST, tv_berandaPemeriksaanHariIni;
    LoadingDialog loadingDialog;
    SessionManager sessionManager;
    String id_user;
    View root;
    AnyChartView anyChartViewResiko, anyChartViewBPJS;
    CardView petaPuskesmas,petaBumil;

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

        loadingDialog = new LoadingDialog(getActivity());
        loadingDialog.startLoading();

        tv_berandaDataBumil = root.findViewById(R.id.tv_berandaDataBumil);
        tv_berandaJumlahKehamilan = root.findViewById(R.id.tv_berandaJumlahKehamilan);
        tv_berandaJumlahKRST = root.findViewById(R.id.tv_berandaJumlahKRST);
        tv_berandaPemeriksaanHariIni = root.findViewById(R.id.tv_berandaPemeriksaanHariIni);
        petaPuskesmas = root.findViewById(R.id.petaPuskesmas);
        petaBumil = root.findViewById(R.id.petaBumil);

        petaPuskesmas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent kePetaPuskesmas = new Intent(getActivity(), MapsActivity.class);
                startActivity(kePetaPuskesmas);
            }
        });

        petaBumil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent kePetaBumil = new Intent(getActivity(), BumilMapsActivity.class);
                startActivity(kePetaBumil);
            }
        });

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

}
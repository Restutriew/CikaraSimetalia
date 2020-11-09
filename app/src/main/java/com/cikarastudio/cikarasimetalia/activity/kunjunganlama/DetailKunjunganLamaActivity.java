package com.cikarastudio.cikarasimetalia.activity.kunjunganlama;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.cikarastudio.cikarasimetalia.R;
import com.cikarastudio.cikarasimetalia.activity.bumil.DetailBumilActivity;
import com.cikarastudio.cikarasimetalia.activity.bumil.EditDataPetaActivity;
import com.cikarastudio.cikarasimetalia.activity.kunjunganbaru.DetailKunjunganBaruActivity;
import com.cikarastudio.cikarasimetalia.activity.kunjunganbaru.EditKunjunganBaruDataSubyektifActivity;
import com.cikarastudio.cikarasimetalia.activity.kunjunganbaru.TambahDataKunjunganBaruActivity;
import com.cikarastudio.cikarasimetalia.adapter.MyKunjunganBaruAdapter;
import com.cikarastudio.cikarasimetalia.adapter.MyKunjunganLamaAdapter;
import com.cikarastudio.cikarasimetalia.dialog.LoadingDialog;
import com.cikarastudio.cikarasimetalia.model.KunjunganBaruModel;
import com.cikarastudio.cikarasimetalia.model.KunjunganLamaModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DetailKunjunganLamaActivity extends AppCompatActivity {

    public static final String ID_KUNJUNGANBARU = "extra_data";
    LoadingDialog loadingDialog;
    String id_kunjunganBaru;
    LinearLayout btn_tambahDataKunjunganLama;
    RecyclerView recyclerView;
    private ArrayList<KunjunganLamaModel> kunjunganLamaList;
    private MyKunjunganLamaAdapter myKunjunganLamaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_kunjungan_lama);

        loadingDialog = new LoadingDialog(DetailKunjunganLamaActivity.this);
        loadingDialog.startLoading();

        Intent intent = getIntent();
        id_kunjunganBaru = intent.getStringExtra(ID_KUNJUNGANBARU);

        btn_tambahDataKunjunganLama = findViewById(R.id.btn_tambahDataKunjunganLama);

        recyclerView = findViewById(R.id.list_kunjunganLama);
        kunjunganLamaList = new ArrayList<>();
        recyclerView.setHasFixedSize(true);

        loadDataKunjunganLama();

        btn_tambahDataKunjunganLama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent keTambahKunjunganLama = new Intent(DetailKunjunganLamaActivity.this, TambahDataKunjunganLamaActivity.class);
                keTambahKunjunganLama.putExtra(TambahDataKunjunganLamaActivity.ID_KUNJUNGANLAMA, id_kunjunganBaru);
                startActivity(keTambahKunjunganLama);
            }
        });

    }

    private void loadDataKunjunganLama() {
        String URL_READBOOKING = "http://simetalia.com/pkm/webservice/kunjunganlamaall/" + id_kunjunganBaru;
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

                                        final String id_kunjunganLama = object.getString("id").trim();
                                        String tanggal = object.getString("tgl_kunjungan").trim();
                                        String keluhan = object.getString("keluhan_utama").trim();

                                        kunjunganLamaList.add(new KunjunganLamaModel(id_kunjunganLama, tanggal, keluhan));
                                        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                                        myKunjunganLamaAdapter = new MyKunjunganLamaAdapter(getApplicationContext(), kunjunganLamaList);
                                        recyclerView.setAdapter(myKunjunganLamaAdapter);
                                        loadingDialog.dissmissDialog();
                                        myKunjunganLamaAdapter.setOnItemClickCallback(new MyKunjunganLamaAdapter.OnItemClickCallback() {
                                            @Override
                                            public void onItemClicked(KunjunganLamaModel data) {
                                                Intent transferDataKunjunganLama = new Intent(DetailKunjunganLamaActivity.this, DataKunjunganLamaActivity.class);
                                                transferDataKunjunganLama.putExtra(DataKunjunganLamaActivity.KUNJUNGANLAMA_DATA, data);
                                                startActivity(transferDataKunjunganLama);
                                            }
                                        });
                                    }
                                } else {
                                    Toast.makeText(DetailKunjunganLamaActivity.this, "Data Kunjungan Lama Tidak Ada!", Toast.LENGTH_SHORT).show();
                                    loadingDialog.dissmissDialog();
                                }
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(DetailKunjunganLamaActivity.this, "Data Kunjungan Lama Tidak Ada!" + e.toString(), Toast.LENGTH_SHORT).show();
                        }

                    }
                }
                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(DetailKunjunganLamaActivity.this, "Koneksi Gagal!" + error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
        };
        RequestQueue requestQueue = Volley.newRequestQueue(DetailKunjunganLamaActivity.this);
        requestQueue.add(stringRequest);
    }
}
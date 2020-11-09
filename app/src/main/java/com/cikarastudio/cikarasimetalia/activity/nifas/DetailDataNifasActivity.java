package com.cikarastudio.cikarasimetalia.activity.nifas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
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
import com.cikarastudio.cikarasimetalia.activity.kunjunganlama.DataKunjunganLamaActivity;
import com.cikarastudio.cikarasimetalia.activity.kunjunganlama.DetailKunjunganLamaActivity;
import com.cikarastudio.cikarasimetalia.activity.kunjunganlama.TambahDataKunjunganLamaActivity;
import com.cikarastudio.cikarasimetalia.activity.skrinning.TambahDataScreeningActivity;
import com.cikarastudio.cikarasimetalia.adapter.MyKunjunganLamaAdapter;
import com.cikarastudio.cikarasimetalia.adapter.MyNifasAdapter;
import com.cikarastudio.cikarasimetalia.dialog.LoadingDialog;
import com.cikarastudio.cikarasimetalia.model.KunjunganLamaModel;
import com.cikarastudio.cikarasimetalia.model.NifasModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DetailDataNifasActivity extends AppCompatActivity {

    public static final String ID_KUNJUNGANBARU = "extra_data";
    LoadingDialog loadingDialog;
    String id_kunjunganBaru;
    RecyclerView recyclerView;
    private ArrayList<NifasModel> nifasList;
    private MyNifasAdapter myNifasAdapter;
    LinearLayout btn_tambahDataNifas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_data_nifas);

        loadingDialog = new LoadingDialog(DetailDataNifasActivity.this);
        loadingDialog.startLoading();

        Intent intent = getIntent();
        id_kunjunganBaru = intent.getStringExtra(ID_KUNJUNGANBARU);

        btn_tambahDataNifas = findViewById(R.id.btn_tambahDataNifas);
        recyclerView = findViewById(R.id.list_dataNifas);
        nifasList = new ArrayList<>();
        recyclerView.setHasFixedSize(true);

        loadDataNifas();

        btn_tambahDataNifas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent keTambahNifas = new Intent(DetailDataNifasActivity.this, TambahDataNifasActivity.class);
                keTambahNifas.putExtra(TambahDataKunjunganLamaActivity.ID_KUNJUNGANLAMA, id_kunjunganBaru);
                startActivity(keTambahNifas);
            }
        });

    }

    private void loadDataNifas() {
        String URL_READBOOKING = "http://simetalia.com/pkm/webservice/nifasall/" + id_kunjunganBaru;
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
                                        String tanggal = object.getString("tanggal_kunjungan").trim();
                                        String kunjunganKe = object.getString("kunjungan_ke").trim();

                                        nifasList.add(new NifasModel(id_kunjunganLama, tanggal, kunjunganKe));
                                        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                                        myNifasAdapter = new MyNifasAdapter(getApplicationContext(), nifasList);
                                        recyclerView.setAdapter(myNifasAdapter);
                                        loadingDialog.dissmissDialog();
                                        myNifasAdapter.setOnItemClickCallback(new MyNifasAdapter.OnItemClickCallback() {
                                            @Override
                                            public void onItemClicked(NifasModel data) {
                                                Intent transferDataNifas = new Intent(DetailDataNifasActivity.this, EditDataNifasActivity.class);
                                                transferDataNifas.putExtra(EditDataNifasActivity.NIFAS_DATA, data);
                                                startActivity(transferDataNifas);
                                            }
                                        });
                                    }
                                } else {
                                    Toast.makeText(DetailDataNifasActivity.this, "Data Nifas Tidak Ada!", Toast.LENGTH_SHORT).show();
                                    loadingDialog.dissmissDialog();
                                }
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(DetailDataNifasActivity.this, "Data Nifas Tidak Ada!" + e.toString(), Toast.LENGTH_SHORT).show();
                        }

                    }
                }
                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(DetailDataNifasActivity.this, "Koneksi Gagal!" + error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
        };
        RequestQueue requestQueue = Volley.newRequestQueue(DetailDataNifasActivity.this);
        requestQueue.add(stringRequest);
    }
}
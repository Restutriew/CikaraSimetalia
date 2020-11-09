package com.cikarastudio.cikarasimetalia.activity.bumil;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.cikarastudio.cikarasimetalia.R;
import com.cikarastudio.cikarasimetalia.activity.kunjunganbaru.DetailKunjunganBaruActivity;
import com.cikarastudio.cikarasimetalia.activity.kunjunganbaru.TambahDataKunjunganBaruActivity;
import com.cikarastudio.cikarasimetalia.adapter.MyKunjunganBaruAdapter;
import com.cikarastudio.cikarasimetalia.dialog.LoadingDialog;
import com.cikarastudio.cikarasimetalia.model.Bumil;
import com.cikarastudio.cikarasimetalia.model.KunjunganBaruModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DetailBumilActivity extends AppCompatActivity {

    LoadingDialog loadingDialog;
    public static final String BUMIL_DATA = "extra_data";
    ImageView btn_back;
    CardView cr_detailIbu, cr_detailSuami, cr_detailLainnya, cr_detailPeta;
    RecyclerView recyclerView;
    String id_bumilget;
    private ArrayList<KunjunganBaruModel> kunjunganBaruList;
    private MyKunjunganBaruAdapter myKunjunganBaruAdapter;
    LinearLayout btn_tambahDataKunjunganBaru, btn_deleteDataBumil;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_bumil);

        Bumil bumilData = getIntent().getParcelableExtra(BUMIL_DATA);
        final String id_bumil = bumilData.getId_bumil();
        id_bumilget = id_bumil;

        loadingDialog = new LoadingDialog(DetailBumilActivity.this);
        loadingDialog.startLoading();

        btn_back = findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        recyclerView = findViewById(R.id.list_kunjunganBaru);
        kunjunganBaruList = new ArrayList<>();
        recyclerView.setHasFixedSize(true);

        cr_detailIbu = findViewById(R.id.cr_detailIbu);
        cr_detailSuami = findViewById(R.id.cr_detailSuami);
        cr_detailLainnya = findViewById(R.id.cr_detailLainnya);
        cr_detailPeta = findViewById(R.id.cr_detailPeta);
        btn_tambahDataKunjunganBaru = findViewById(R.id.btn_tambahDataKunjunganBaru);

        //load_data
        load_dataKunjunganBaru();

        //tombol atas
        cr_detailIbu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cr_detailIbuintent = new Intent(DetailBumilActivity.this, EditIbuHamilActivity.class);
                cr_detailIbuintent.putExtra(EditIbuHamilActivity.ID_BUMIL, id_bumil);
                startActivity(cr_detailIbuintent);
            }
        });

        cr_detailSuami.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent detailSuami = new Intent(DetailBumilActivity.this, EditDataSuamiActivity.class);
                detailSuami.putExtra(EditDataSuamiActivity.ID_BUMIL, id_bumil);
                startActivity(detailSuami);
            }
        });

        cr_detailLainnya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent detailLainnya = new Intent(DetailBumilActivity.this, EditDataKeluargaActivity.class);
                detailLainnya.putExtra(EditDataKeluargaActivity.ID_BUMIL, id_bumil);
                startActivity(detailLainnya);
            }
        });

        cr_detailPeta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent detailPeta = new Intent(DetailBumilActivity.this, EditDataPetaActivity.class);
                detailPeta.putExtra(EditDataPetaActivity.ID_BUMIL, id_bumil);
                startActivity(detailPeta);
            }
        });

        //tombol bawah
        btn_tambahDataKunjunganBaru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent keTambahKunjunganBaru = new Intent(DetailBumilActivity.this, TambahDataKunjunganBaruActivity.class);
                keTambahKunjunganBaru.putExtra(EditDataPetaActivity.ID_BUMIL, id_bumil);
                startActivity(keTambahKunjunganBaru);
            }
        });

    }

    private void load_dataKunjunganBaru() {
        String URL_READBOOKING = "http://simetalia.com/pkm/webservice/kunjunganbaruall/" + id_bumilget;
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

                                        final String id_kunjunganBaru = object.getString("id").trim();
                                        String tanggal = object.getString("tanggal").trim();
                                        String status = object.getString("skrinning").trim();
                                        Integer kunjunganKe = i + 1;
                                        String kunjunganKeFix = String.valueOf(kunjunganKe);
                                        String palingFixKunjunganBaru = "Kehamilan Ke-" + kunjunganKeFix;

                                        kunjunganBaruList.add(new KunjunganBaruModel(id_kunjunganBaru, tanggal, status, palingFixKunjunganBaru));
                                        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                                        myKunjunganBaruAdapter = new MyKunjunganBaruAdapter(getApplicationContext(), kunjunganBaruList);
                                        recyclerView.setAdapter(myKunjunganBaruAdapter);
                                        loadingDialog.dissmissDialog();
                                        myKunjunganBaruAdapter.setOnItemClickCallback(new MyKunjunganBaruAdapter.OnItemClickCallback() {
                                            @Override
                                            public void onItemClicked(KunjunganBaruModel data) {

                                                Intent transferDataKunjunganBaru = new Intent(DetailBumilActivity.this, DetailKunjunganBaruActivity.class);
                                                transferDataKunjunganBaru.putExtra(DetailKunjunganBaruActivity.KUNJUNGANBARU_DATA, data);
                                                startActivity(transferDataKunjunganBaru);
                                            }
                                        });
                                    }
                                } else {
                                    Toast.makeText(DetailBumilActivity.this, "Data Kehamilan Tidak Ada!", Toast.LENGTH_SHORT).show();
                                    loadingDialog.dissmissDialog();
                                }
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(DetailBumilActivity.this, "Data Kehamilan Tidak Ada!" + e.toString(), Toast.LENGTH_SHORT).show();
                        }

                    }
                }
                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(DetailBumilActivity.this, "Koneksi Gagal!" + error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
        };
        RequestQueue requestQueue = Volley.newRequestQueue(DetailBumilActivity.this);
        requestQueue.add(stringRequest);

    }
}
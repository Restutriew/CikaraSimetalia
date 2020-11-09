package com.cikarastudio.cikarasimetalia.activity.kunjunganlama;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.cikarastudio.cikarasimetalia.R;
import com.cikarastudio.cikarasimetalia.activity.kunjunganbaru.EditKunjunganBaruDataPlanActivity;
import com.cikarastudio.cikarasimetalia.activity.kunjunganbaru.EditKunjunganBaruDataSubyektifActivity;
import com.cikarastudio.cikarasimetalia.dialog.LoadingDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class EditKunjunganLamaDataSubyektifActivity extends AppCompatActivity {

    public static final String ID_KUNJUNGANLAMA = "extra_data";
    String id_kunjunganLama, tgl_kunjungan;
    LoadingDialog loadingDialog;
    EditText tv_editSubyektifLamaTanggal,tv_editSubyektifLamaKeluhanUtama,tv_editSubyektifLamaKeluhanTambahan;
    ImageView btn_toggleEditSubyektifLama;
    LinearLayout btn_updateDataSubyektifLama;
    private static final String URL_UPDATESUBYEKTIFLAMA = "http://simetalia.com/android/update_subyektif_lama.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_kunjungan_lama_data_subyektif);

        loadingDialog = new LoadingDialog(EditKunjunganLamaDataSubyektifActivity.this);
        loadingDialog.startLoading();

        Intent intent = getIntent();
        id_kunjunganLama = intent.getStringExtra(ID_KUNJUNGANLAMA);

        tv_editSubyektifLamaTanggal = findViewById(R.id.tv_editSubyektifLamaTanggal);
        tv_editSubyektifLamaKeluhanUtama = findViewById(R.id.tv_editSubyektifLamaKeluhanUtama);
        tv_editSubyektifLamaKeluhanTambahan = findViewById(R.id.tv_editSubyektifLamaKeluhanTambahan);
        btn_toggleEditSubyektifLama = findViewById(R.id.btn_toggleEditSubyektifLama);
        btn_updateDataSubyektifLama = findViewById(R.id.btn_updateDataSubyektifLama);

        loadDataSubyektif();

        btn_toggleEditSubyektifLama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggledataSubyektif();
            }
        });

        btn_updateDataSubyektifLama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateDataSUbyektif();
            }
        });

    }

    private void updateDataSUbyektif() {
        loadingDialog.startLoading();
        String tanggalawal = tv_editSubyektifLamaTanggal.getText().toString().trim();
        if (tanggalawal.equals("")) {
            tgl_kunjungan = "";
        } else {
            String tanggaltengahtanggal = tanggalawal.substring(0, 2);
            String bulantengahtanggal = tanggalawal.substring(3, 5);
            String tahuntengahtanggal = tanggalawal.substring(6, 10);
            tgl_kunjungan = tahuntengahtanggal + "-" + bulantengahtanggal + "-" + tanggaltengahtanggal;
        }

        final String keluhan_utama = tv_editSubyektifLamaKeluhanUtama.getText().toString().trim();
        final String keluhan_tambahan = tv_editSubyektifLamaKeluhanTambahan.getText().toString().trim();

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        final String updated_at = dateFormat.format(cal.getTime());

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_UPDATESUBYEKTIFLAMA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")) {
                                Toast.makeText(EditKunjunganLamaDataSubyektifActivity.this, "Update Data Subyektif Berhasil", Toast.LENGTH_LONG).show();
                                loadingDialog.dissmissDialog();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(EditKunjunganLamaDataSubyektifActivity.this, "Update Data Subyektif Gagal!", Toast.LENGTH_LONG).show();
                            loadingDialog.dissmissDialog();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(EditKunjunganLamaDataSubyektifActivity.this, "Update Data Subyektif Gagal! : Cek Koneksi Anda", Toast.LENGTH_LONG).show();
                        loadingDialog.dissmissDialog();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                params.put("id", id_kunjunganLama);
                params.put("tgl_kunjungan", tgl_kunjungan);
                params.put("keluhan_utama", keluhan_utama);
                params.put("keluhan_tambahan", keluhan_tambahan);
                params.put("updated_at", updated_at);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void toggledataSubyektif() {
        if (!tv_editSubyektifLamaTanggal.isEnabled()) {
            tv_editSubyektifLamaTanggal.setEnabled(true);
            tv_editSubyektifLamaTanggal.setFocusable(true);
            tv_editSubyektifLamaTanggal.setCursorVisible(true);
            tv_editSubyektifLamaTanggal.setFocusableInTouchMode(true);
            tv_editSubyektifLamaTanggal.setTextColor(Color.parseColor("#000000"));
            tv_editSubyektifLamaKeluhanUtama.setEnabled(true);
            tv_editSubyektifLamaKeluhanUtama.setFocusable(true);
            tv_editSubyektifLamaKeluhanUtama.setCursorVisible(true);
            tv_editSubyektifLamaKeluhanUtama.setFocusableInTouchMode(true);
            tv_editSubyektifLamaKeluhanUtama.setTextColor(Color.parseColor("#000000"));
            tv_editSubyektifLamaKeluhanTambahan.setEnabled(true);
            tv_editSubyektifLamaKeluhanTambahan.setFocusable(true);
            tv_editSubyektifLamaKeluhanTambahan.setCursorVisible(true);
            tv_editSubyektifLamaKeluhanTambahan.setFocusableInTouchMode(true);
            tv_editSubyektifLamaKeluhanTambahan.setTextColor(Color.parseColor("#000000"));
            Toast.makeText(EditKunjunganLamaDataSubyektifActivity.this, "Mode Edit Diaktifkan!", Toast.LENGTH_LONG).show();
        } else if (tv_editSubyektifLamaTanggal.isEnabled()) {
            tv_editSubyektifLamaTanggal.setEnabled(false);
            tv_editSubyektifLamaTanggal.setFocusable(false);
            tv_editSubyektifLamaTanggal.setCursorVisible(false);
            tv_editSubyektifLamaTanggal.setTextColor(Color.parseColor("#919191"));
            tv_editSubyektifLamaKeluhanUtama.setEnabled(false);
            tv_editSubyektifLamaKeluhanUtama.setFocusable(false);
            tv_editSubyektifLamaKeluhanUtama.setCursorVisible(false);
            tv_editSubyektifLamaKeluhanUtama.setTextColor(Color.parseColor("#919191"));
            tv_editSubyektifLamaKeluhanTambahan.setEnabled(false);
            tv_editSubyektifLamaKeluhanTambahan.setFocusable(false);
            tv_editSubyektifLamaKeluhanTambahan.setCursorVisible(false);
            tv_editSubyektifLamaKeluhanTambahan.setTextColor(Color.parseColor("#919191"));
            Toast.makeText(EditKunjunganLamaDataSubyektifActivity.this, "Mode Edit Dinonaktifkan!", Toast.LENGTH_SHORT).show();
        }
    }

    private void loadDataSubyektif() {
        String URL_READBOOKING = "http://simetalia.com/pkm/webservice/kunjunganlama/" + id_kunjunganLama;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_READBOOKING,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONObject object = jsonObject.getJSONObject("read");

                            if (success.equals("1")) {

                                String tgl_kunjungan = object.getString("tgl_kunjungan").trim();
                                String keluhan_utama = object.getString("keluhan_utama").trim();
                                String keluhan_tambahan = object.getString("keluhan_tambahan").trim();

                                tv_editSubyektifLamaTanggal.setText(tgl_kunjungan);
                                tv_editSubyektifLamaKeluhanUtama.setText(keluhan_utama);
                                tv_editSubyektifLamaKeluhanTambahan.setText(keluhan_tambahan);

                                loadingDialog.dissmissDialog();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(EditKunjunganLamaDataSubyektifActivity.this, "Data Subyektif Tidak Ada!" + e.toString(), Toast.LENGTH_SHORT).show();
                            loadingDialog.dissmissDialog();
                        }

                    }
                }
                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(EditKunjunganLamaDataSubyektifActivity.this, "Koneksi Gagal!" + error.toString(), Toast.LENGTH_SHORT).show();
                loadingDialog.dissmissDialog();
            }
        }) {
        };
        RequestQueue requestQueue = Volley.newRequestQueue(EditKunjunganLamaDataSubyektifActivity.this);
        requestQueue.add(stringRequest);
    }
}
package com.cikarastudio.cikarasimetalia.activity.kunjunganlama;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
import com.cikarastudio.cikarasimetalia.activity.kunjunganbaru.TambahDataKunjunganBaruActivity;
import com.cikarastudio.cikarasimetalia.dialog.LoadingDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class TambahDataKunjunganLamaActivity extends AppCompatActivity {

    LoadingDialog loadingDialog;
    Dialog myDialog;
    String pkmkehamilan_id;
    public static final String ID_KUNJUNGANLAMA = "extra_data";
    EditText et_tambahKunjunganLamaTanggal, et_tambahKunjunganLamaKeluhanUtama, et_tambahKunjunganLamaKeluhanTambahan;
    LinearLayout btn_tambahKunjunganLama;
    private static String URL_TAMBAHKUNJUNGANLAMA = "http://simetalia.com/android/tambah_data_kunjungan_lama.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_data_kunjungan_lama);

        myDialog = new Dialog(this);
        loadingDialog = new LoadingDialog(TambahDataKunjunganLamaActivity.this);

        Intent intent = getIntent();
        pkmkehamilan_id = intent.getStringExtra(ID_KUNJUNGANLAMA);

        et_tambahKunjunganLamaTanggal = findViewById(R.id.et_tambahKunjunganLamaTanggal);
        et_tambahKunjunganLamaKeluhanUtama = findViewById(R.id.et_tambahKunjunganLamaKeluhanUtama);
        et_tambahKunjunganLamaKeluhanTambahan = findViewById(R.id.et_tambahKunjunganLamaKeluhanTambahan);
        btn_tambahKunjunganLama = findViewById(R.id.btn_tambahKunjunganLama);

        btn_tambahKunjunganLama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cekFormKunjunganLama();
            }
        });

    }

    private void cekFormKunjunganLama() {
        String Stgl_kunjungan = et_tambahKunjunganLamaTanggal.getText().toString();

        if (Stgl_kunjungan.equals("")) {
            et_tambahKunjunganLamaTanggal.setError("Silahkan Masukkan Tanggal Kunjungan!");
            et_tambahKunjunganLamaTanggal.requestFocus();
        } else {
            tambahDataKunjunganLama();
        }
    }

    private void tambahDataKunjunganLama() {
        loadingDialog.startLoading();
        final String tanggalawal = et_tambahKunjunganLamaTanggal.getText().toString().trim();
        String tanggaltengah = tanggalawal.substring(0, 2);
        String bulantengah = tanggalawal.substring(3, 5);
        String tahuntengah = tanggalawal.substring(6, 10);
        final String tgl_kunjungan = tahuntengah + "-" + bulantengah + "-" + tanggaltengah;

        final String keluhan_utama = et_tambahKunjunganLamaKeluhanUtama.getText().toString().trim();
        final String keluhan_tambahan = et_tambahKunjunganLamaKeluhanTambahan.getText().toString().trim();

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        final String created_at = dateFormat.format(cal.getTime());

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_TAMBAHKUNJUNGANLAMA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")) {
                                Toast.makeText(TambahDataKunjunganLamaActivity.this, "Tambah Kunjungan Lama Sukses", Toast.LENGTH_LONG).show();
                                loadingDialog.dissmissDialog();
                                finish();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(TambahDataKunjunganLamaActivity.this, "Tambah Kunjunga Lama Gagal!", Toast.LENGTH_LONG).show();
                            loadingDialog.dissmissDialog();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(TambahDataKunjunganLamaActivity.this, "Tambah Kunjungan Lama Gagal! : Cek Koneksi Anda", Toast.LENGTH_LONG).show();
                        loadingDialog.dissmissDialog();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                params.put("pkmkehamilan_id", pkmkehamilan_id);
                params.put("tgl_kunjungan", tgl_kunjungan);
                params.put("keluhan_utama", keluhan_utama);
                params.put("keluhan_tambahan", keluhan_tambahan);
                params.put("created_at", created_at);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}
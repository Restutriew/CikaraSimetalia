package com.cikarastudio.cikarasimetalia.activity.kunjunganbaru;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class TambahDataKunjunganBaruActivity extends AppCompatActivity {

    LoadingDialog loadingDialog;
    Dialog myDialog;
    String pkmbumil_id, keguguran, waktu_beri, hpht;
    public static final String ID_BUMIL = "extra_data";
    ImageView btn_back;
    EditText et_tambahKunjunganBaruTanggal, et_tambahKunjunganBaruKeluhanUtama, et_tambahKunjunganBaruKeluhanTambahan,
            et_tambahKunjunganBaruRiwayatPernikahan, et_tambahKunjunganBaruRiwayatPenyakit, et_tambahKunjunganBaruRiwayatPenyakitKeluarga,
            et_tambahKunjunganBaruRiwayaAlergi, et_tambahKunjunganBaruRiwayatAnakHidup, et_tambahKunjunganBaruRiwayatAnakMati,
            et_tambahKunjunganBaruRiwayatAnakLahirPrematur, et_tambahKunjunganBaruKeguguranTerakhir, et_tambahKunjunganBaruNamaPenolong,
            et_tambahKunjunganBaruKomplikasi, et_tambahKunjunganBaruJenisKontrasepsi, et_tambahKunjunganBaruWaktuDiberikan,
            et_tambahKunjunganBaruGravida, et_tambahKunjunganBaruHPHT, et_tambahKunjunganBaruMulaiAdaGerakanJanin,
            et_tambahKunjunganBaruLainnya;
    Spinner sp_tambahKunjunganBaruPenolongPersalinanTerakhir, sp_tambahKunjunganBaruCaraPersalinanTerakhir,
            sp_tambahKunjunganBaruApakahBerKB, sp_tambahKunjunganBaruSuntikTT;
    LinearLayout btn_tambahKunjunganBaru;
    private static String URL_TAMBAHKUNJUNGANBARU = "http://simetalia.com/android/tambah_data_kunjungan_baru.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_data_kunjungan_baru);

        myDialog = new Dialog(this);
        loadingDialog = new LoadingDialog(TambahDataKunjunganBaruActivity.this);

        Intent intent = getIntent();
        pkmbumil_id = intent.getStringExtra(ID_BUMIL);

        btn_back = findViewById(R.id.btn_backKunjungan);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        et_tambahKunjunganBaruTanggal = findViewById(R.id.et_tambahKunjunganBaruTanggal);
        et_tambahKunjunganBaruKeluhanUtama = findViewById(R.id.et_tambahKunjunganBaruKeluhanUtama);
        et_tambahKunjunganBaruKeluhanTambahan = findViewById(R.id.et_tambahKunjunganBaruKeluhanTambahan);
        et_tambahKunjunganBaruRiwayatPernikahan = findViewById(R.id.et_tambahKunjunganBaruRiwayatPernikahan);
        et_tambahKunjunganBaruRiwayatPenyakit = findViewById(R.id.et_tambahKunjunganBaruRiwayatPenyakit);
        et_tambahKunjunganBaruRiwayatPenyakitKeluarga = findViewById(R.id.et_tambahKunjunganBaruRiwayatPenyakitKeluarga);
        et_tambahKunjunganBaruRiwayaAlergi = findViewById(R.id.et_tambahKunjunganBaruRiwayaAlergi);
        et_tambahKunjunganBaruRiwayatAnakHidup = findViewById(R.id.et_tambahKunjunganBaruRiwayatAnakHidup);
        et_tambahKunjunganBaruRiwayatAnakMati = findViewById(R.id.et_tambahKunjunganBaruRiwayatAnakMati);
        et_tambahKunjunganBaruRiwayatAnakLahirPrematur = findViewById(R.id.et_tambahKunjunganBaruRiwayatAnakLahirPrematur);
        et_tambahKunjunganBaruKeguguranTerakhir = findViewById(R.id.et_tambahKunjunganBaruKeguguranTerakhir);
        et_tambahKunjunganBaruNamaPenolong = findViewById(R.id.et_tambahKunjunganBaruNamaPenolong);
        et_tambahKunjunganBaruKomplikasi = findViewById(R.id.et_tambahKunjunganBaruKomplikasi);
        et_tambahKunjunganBaruJenisKontrasepsi = findViewById(R.id.et_tambahKunjunganBaruJenisKontrasepsi);
        et_tambahKunjunganBaruWaktuDiberikan = findViewById(R.id.et_tambahKunjunganBaruWaktuDiberikan);
        et_tambahKunjunganBaruGravida = findViewById(R.id.et_tambahKunjunganBaruGravida);
        et_tambahKunjunganBaruHPHT = findViewById(R.id.et_tambahKunjunganBaruHPHT);
        et_tambahKunjunganBaruMulaiAdaGerakanJanin = findViewById(R.id.et_tambahKunjunganBaruMulaiAdaGerakanJanin);
        et_tambahKunjunganBaruLainnya = findViewById(R.id.et_tambahKunjunganBaruLainnya);

        sp_tambahKunjunganBaruPenolongPersalinanTerakhir = findViewById(R.id.et_tambahKunjunganBaruPenolongPersalinanTerakhir);
        sp_tambahKunjunganBaruCaraPersalinanTerakhir = findViewById(R.id.et_tambahKunjunganBaruCaraPersalinanTerakhir);
        sp_tambahKunjunganBaruApakahBerKB = findViewById(R.id.et_tambahKunjunganBaruApakahBerKB);
        sp_tambahKunjunganBaruSuntikTT = findViewById(R.id.et_tambahKunjunganBaruSuntikTT);

        //simpan
        btn_tambahKunjunganBaru = findViewById(R.id.btn_tambahKunjunganBaru);
        btn_tambahKunjunganBaru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cekFormTambahKunjunganBaru();
            }
        });

        //spinner
        data_spinner();

    }

    private void cekFormTambahKunjunganBaru() {
        String Stanggal = et_tambahKunjunganBaruTanggal.getText().toString();

        if (Stanggal.equals("")) {
            et_tambahKunjunganBaruTanggal.setError("Silahkan Masukkan Tanggal Kunjungan!");
            et_tambahKunjunganBaruTanggal.requestFocus();
        } else {
            tambahDataKunjunganBaru();
        }
    }

    private void tambahDataKunjunganBaru() {
        loadingDialog.startLoading();
        final String tanggalawal = et_tambahKunjunganBaruTanggal.getText().toString().trim();
        String tanggaltengah = tanggalawal.substring(0, 2);
        String bulantengah = tanggalawal.substring(3, 5);
        String tahuntengah = tanggalawal.substring(6, 10);
        final String tanggal = tahuntengah + "-" + bulantengah + "-" + tanggaltengah;

        final String keluhan_utama = et_tambahKunjunganBaruKeluhanUtama.getText().toString().trim();
        final String keluhan_tambahan = et_tambahKunjunganBaruKeluhanTambahan.getText().toString().trim();
        final String riwayat_nikah = et_tambahKunjunganBaruRiwayatPernikahan.getText().toString().trim();
        final String riwayat_penyakit = et_tambahKunjunganBaruRiwayatPenyakit.getText().toString().trim();
        final String riwayat_penyakit_keluarga = et_tambahKunjunganBaruRiwayatPenyakitKeluarga.getText().toString().trim();
        final String riwayat_alergi = et_tambahKunjunganBaruRiwayaAlergi.getText().toString().trim();
        final String jml_anak_h = et_tambahKunjunganBaruRiwayatAnakHidup.getText().toString().trim();
        final String jml_anak_m = et_tambahKunjunganBaruRiwayatAnakMati.getText().toString().trim();
        final String jml_anak_pr = et_tambahKunjunganBaruRiwayatAnakLahirPrematur.getText().toString().trim();
        String keguguranawal = et_tambahKunjunganBaruKeguguranTerakhir.getText().toString().trim();
        String tanggalkegugurantengah = keguguranawal.substring(0, 2);
        String bulankegugurantengah = keguguranawal.substring(3, 5);
        String tahunkegugurantengah = keguguranawal.substring(6, 10);
        keguguran = tahunkegugurantengah + "-" + bulankegugurantengah + "-" + tanggalkegugurantengah;


        final String tolong_salin = sp_tambahKunjunganBaruPenolongPersalinanTerakhir.getSelectedItem().toString();

        final String hasil_tolong_salin = et_tambahKunjunganBaruNamaPenolong.getText().toString().trim();

        final String cara_salin = sp_tambahKunjunganBaruCaraPersalinanTerakhir.getSelectedItem().toString();

        final String komplikasi = et_tambahKunjunganBaruKomplikasi.getText().toString().trim();

        final String kb_sebelum_hamil = sp_tambahKunjunganBaruApakahBerKB.getSelectedItem().toString();

        final String jns_kontrasepsi = et_tambahKunjunganBaruJenisKontrasepsi.getText().toString().trim();

        final String suntikan_tt = sp_tambahKunjunganBaruSuntikTT.getSelectedItem().toString();

        final String waktu_beriawal = et_tambahKunjunganBaruWaktuDiberikan.getText().toString().trim();
        String tanggalwaktu_beritengah = waktu_beriawal.substring(0, 2);
        String bulanwaktu_beritengah = waktu_beriawal.substring(3, 5);
        String tahunwaktu_beritengah = waktu_beriawal.substring(6, 10);
        waktu_beri = tahunwaktu_beritengah + "-" + bulanwaktu_beritengah + "-" + tanggalwaktu_beritengah;

        final String gravida = et_tambahKunjunganBaruGravida.getText().toString().trim();
        final String hphtawal = et_tambahKunjunganBaruHPHT.getText().toString().trim();
        String tanggalhphttengah = hphtawal.substring(0, 2);
        String bulanhphttengah = hphtawal.substring(3, 5);
        String tahunhphttengah = hphtawal.substring(6, 10);
        hpht = tahunhphttengah + "-" + bulanhphttengah + "-" + tanggalhphttengah;

        final String gerakan_janin = et_tambahKunjunganBaruMulaiAdaGerakanJanin.getText().toString().trim();
        final String lainnya = et_tambahKunjunganBaruLainnya.getText().toString().trim();

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        final String created_at = dateFormat.format(cal.getTime());

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_TAMBAHKUNJUNGANBARU,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")) {
                                Toast.makeText(TambahDataKunjunganBaruActivity.this, "Tambah Kunjungan Baru Sukses"+ tanggal, Toast.LENGTH_LONG).show();
                                loadingDialog.dissmissDialog();
                                finish();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(TambahDataKunjunganBaruActivity.this, "Tambah Kunjungan Baru Gagal!", Toast.LENGTH_LONG).show();
                            loadingDialog.dissmissDialog();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(TambahDataKunjunganBaruActivity.this, "Tambah Kunjungan Baru Gagal! : Cek Koneksi Anda", Toast.LENGTH_LONG).show();
                        loadingDialog.dissmissDialog();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                params.put("pkmbumil_id", pkmbumil_id);
                params.put("tanggal", tanggal);
                params.put("keluhan_utama", keluhan_utama);
                params.put("keluhan_tambahan", keluhan_tambahan);
                params.put("riwayat_nikah", riwayat_nikah);
                params.put("rwyt_penyakit", riwayat_penyakit);
                params.put("rwyt_penyakit_keluarga", riwayat_penyakit_keluarga);
                params.put("rwyt_alergi", riwayat_alergi);
                params.put("jml_anak_h", jml_anak_h);
                params.put("jml_anak_m", jml_anak_m);
                params.put("jml_anak_pr", jml_anak_pr);
                params.put("keguguran", keguguran);
                params.put("tolong_salin", tolong_salin);
                params.put("hasil_tolong_salin", hasil_tolong_salin);
                params.put("cara_salin", cara_salin);
                params.put("komplikasi", komplikasi);
                params.put("kb_sebelum_hml", kb_sebelum_hamil);
                params.put("jns_kontrasepsi", jns_kontrasepsi);
                params.put("suntikan_tt", suntikan_tt);
                params.put("waktu_beri", waktu_beri);
                params.put("gravida", gravida);
                params.put("hpht", hpht);
                params.put("gerakan_janin", gerakan_janin);
                params.put("lainnya", lainnya);
                params.put("created_at", created_at);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }


    private void data_spinner() {
        ArrayAdapter<String> penolongPersalinanAdapter = new ArrayAdapter<>(TambahDataKunjunganBaruActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.tolong_salin));
        penolongPersalinanAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_tambahKunjunganBaruPenolongPersalinanTerakhir.setAdapter(penolongPersalinanAdapter);

        ArrayAdapter<String> caraPersalinanAdapter = new ArrayAdapter<>(TambahDataKunjunganBaruActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.cara_salin));
        caraPersalinanAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_tambahKunjunganBaruCaraPersalinanTerakhir.setAdapter(caraPersalinanAdapter);

        ArrayAdapter<String> apakahBerKBAdapter = new ArrayAdapter<>(TambahDataKunjunganBaruActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.kb_sebelum_hml));
        apakahBerKBAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_tambahKunjunganBaruApakahBerKB.setAdapter(apakahBerKBAdapter);

        ArrayAdapter<String> suntikanTTAdapter = new ArrayAdapter<>(TambahDataKunjunganBaruActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.suntikan_tt));
        suntikanTTAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_tambahKunjunganBaruSuntikTT.setAdapter(suntikanTTAdapter);
    }
}
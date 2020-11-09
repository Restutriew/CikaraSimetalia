package com.cikarastudio.cikarasimetalia.activity.kunjunganbaru;

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
import com.cikarastudio.cikarasimetalia.activity.bumil.EditIbuHamilActivity;
import com.cikarastudio.cikarasimetalia.dialog.LoadingDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class EditKunjunganBaruDataSubyektifActivity extends AppCompatActivity {

    public static final String ID_KUNJUNGANBARU = "extra_data";
    String id_kunjunganBaru, tanggal, keguguran, waktu_beri, hpht;
    LoadingDialog loadingDialog;
    EditText tv_editSubyektifBaruTanggal, tv_editSubyektifBaruKeluhanUtama, tv_editSubyektifBaruKeluhanTambahan, tv_editSubyektifBaruRiwayatNikah,
            tv_editSubyektifBaruRiwayatPenyakit, tv_editSubyektifBaruRiwayatPenyakitKeluarga, tv_editSubyektifBaruRiwayatAlergi, tv_editSubyektifBaruJmlAnakHidup,
            tv_editSubyektifBaruJmlAnakMati, tv_editSubyektifBaruJmlAnakPrematur, tv_editSubyektifBaruKeguguran, tv_editSubyektifBaruHasilTolongSalin,
            tv_editSubyektifBaruKomplikasi, tv_editSubyektifBaruJNSKontrasepsi, tv_editSubyektifBaruWaktuBeri, tv_editSubyektifBaruGravida, tv_editSubyektifBaruHPHT,
            tv_editSubyektifBaruGerakanJanin, tv_editSubyektifBaruLainnya;
    Spinner sp_editSubyektifBaruTolongSalin, sp_editSubyektifBaruCaraSalin, sp_editSubyektifBaruKBSebelumHamil, sp_editSubyektifBaruSuntikanTT;
    ImageView btn_toggleEditSubyektifBaru;
    LinearLayout btn_updateDataSubyektifBaru;
    private static final String URL_UPDATESUBYEKTIFBARU = "http://simetalia.com/android/update_subyektif_baru.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_kunjungan_baru_data_subyektif);

        loadingDialog = new LoadingDialog(EditKunjunganBaruDataSubyektifActivity.this);
        loadingDialog.startLoading();

        Intent intent = getIntent();
        id_kunjunganBaru = intent.getStringExtra(ID_KUNJUNGANBARU);

        tv_editSubyektifBaruTanggal = findViewById(R.id.tv_editSubyektifBaruTanggal);
        tv_editSubyektifBaruKeluhanUtama = findViewById(R.id.tv_editSubyektifBaruKeluhanUtama);
        tv_editSubyektifBaruKeluhanTambahan = findViewById(R.id.tv_editSubyektifBaruKeluhanTambahan);
        tv_editSubyektifBaruRiwayatNikah = findViewById(R.id.tv_editSubyektifBaruRiwayatNikah);
        tv_editSubyektifBaruRiwayatPenyakit = findViewById(R.id.tv_editSubyektifBaruRiwayatPenyakit);
        tv_editSubyektifBaruRiwayatPenyakitKeluarga = findViewById(R.id.tv_editSubyektifBaruRiwayatPenyakitKeluarga);
        tv_editSubyektifBaruRiwayatAlergi = findViewById(R.id.tv_editSubyektifBaruRiwayatAlergi);
        tv_editSubyektifBaruJmlAnakHidup = findViewById(R.id.tv_editSubyektifBaruJmlAnakHidup);
        tv_editSubyektifBaruJmlAnakMati = findViewById(R.id.tv_editSubyektifBaruJmlAnakMati);
        tv_editSubyektifBaruJmlAnakPrematur = findViewById(R.id.tv_editSubyektifBaruJmlAnakPrematur);
        tv_editSubyektifBaruKeguguran = findViewById(R.id.tv_editSubyektifBaruKeguguran);
        sp_editSubyektifBaruTolongSalin = findViewById(R.id.sp_editSubyektifBaruTolongSalin);
        tv_editSubyektifBaruHasilTolongSalin = findViewById(R.id.tv_editSubyektifBaruHasilTolongSalin);
        sp_editSubyektifBaruCaraSalin = findViewById(R.id.sp_editSubyektifBaruCaraSalin);
        tv_editSubyektifBaruKomplikasi = findViewById(R.id.tv_editSubyektifBaruKomplikasi);
        sp_editSubyektifBaruKBSebelumHamil = findViewById(R.id.sp_editSubyektifBaruKBSebelumHamil);
        tv_editSubyektifBaruJNSKontrasepsi = findViewById(R.id.tv_editSubyektifBaruJNSKontrasepsi);
        sp_editSubyektifBaruSuntikanTT = findViewById(R.id.sp_editSubyektifBaruSuntikanTT);
        tv_editSubyektifBaruWaktuBeri = findViewById(R.id.tv_editSubyektifBaruWaktuBeri);
        tv_editSubyektifBaruGravida = findViewById(R.id.tv_editSubyektifBaruGravida);
        tv_editSubyektifBaruHPHT = findViewById(R.id.tv_editSubyektifBaruHPHT);
        tv_editSubyektifBaruGerakanJanin = findViewById(R.id.tv_editSubyektifBaruGerakanJanin);
        tv_editSubyektifBaruLainnya = findViewById(R.id.tv_editSubyektifBaruLainnya);
        btn_toggleEditSubyektifBaru = findViewById(R.id.btn_toggleEditSubyektifBaru);
        btn_updateDataSubyektifBaru = findViewById(R.id.btn_updateDataSubyektifBaru);

        sp_editSubyektifBaruSuntikanTT.setEnabled(false);
        sp_editSubyektifBaruKBSebelumHamil.setEnabled(false);
        sp_editSubyektifBaruCaraSalin.setEnabled(false);
        sp_editSubyektifBaruTolongSalin.setEnabled(false);

        loadDataSubyektifBaru();

        btn_toggleEditSubyektifBaru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleSubyektifBaru();
            }
        });

        btn_updateDataSubyektifBaru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateDataSubyektifBaru();
            }
        });

    }

    private void updateDataSubyektifBaru() {
        loadingDialog.startLoading();
        String tanggalawal = tv_editSubyektifBaruTanggal.getText().toString().trim();
        if (tanggalawal.equals("")) {
            tanggal = "";
        } else {
            String tanggaltengahtanggal = tanggalawal.substring(0, 2);
            String bulantengahtanggal = tanggalawal.substring(3, 5);
            String tahuntengahtanggal = tanggalawal.substring(6, 10);
            tanggal = tahuntengahtanggal + "-" + bulantengahtanggal + "-" + tanggaltengahtanggal;
        }

        String keguguranawal = tv_editSubyektifBaruKeguguran.getText().toString().trim();
        if (keguguranawal.equals("")) {
            keguguran = "";
        } else {
            String tanggaltengahkeguguran = keguguranawal.substring(0, 2);
            String bulantengahkeguguran = keguguranawal.substring(3, 5);
            String tahuntengahkeguguran = keguguranawal.substring(6, 10);
            keguguran = tahuntengahkeguguran + "-" + bulantengahkeguguran + "-" + tanggaltengahkeguguran;
        }

        String waktuberiawal = tv_editSubyektifBaruWaktuBeri.getText().toString().trim();
        if (waktuberiawal.equals("")) {
            waktu_beri = "";
        } else {
            String tanggaltengahwaktuberi = waktuberiawal.substring(0, 2);
            String bulantengahwaktuberi = waktuberiawal.substring(3, 5);
            String tahuntengahwaktuberi = waktuberiawal.substring(6, 10);
            waktu_beri = tahuntengahwaktuberi + "-" + bulantengahwaktuberi + "-" + tanggaltengahwaktuberi;
        }

        String hphtawal = tv_editSubyektifBaruHPHT.getText().toString().trim();
        if (hphtawal.equals("")) {
            hpht = "";
        } else {
            String tanggaltengahhpht = hphtawal.substring(0, 2);
            String bulantengahhpht = hphtawal.substring(3, 5);
            String tahuntengahhpht = hphtawal.substring(6, 10);
            hpht = tahuntengahhpht + "-" + bulantengahhpht + "-" + tanggaltengahhpht;
        }

        final String keluhan_utama = tv_editSubyektifBaruKeluhanUtama.getText().toString().trim();
        final String keluhan_tambahan = tv_editSubyektifBaruKeluhanTambahan.getText().toString().trim();
        final String riwayat_nikah = tv_editSubyektifBaruRiwayatNikah.getText().toString().trim();
        final String rwyt_penyakit = tv_editSubyektifBaruRiwayatPenyakit.getText().toString().trim();
        final String rwyt_penyakit_keluarga = tv_editSubyektifBaruRiwayatPenyakitKeluarga.getText().toString().trim();
        final String rwyt_alergi = tv_editSubyektifBaruRiwayatAlergi.getText().toString().trim();
        final String jml_anak_h = tv_editSubyektifBaruJmlAnakHidup.getText().toString().trim();
        final String jml_anak_m = tv_editSubyektifBaruJmlAnakMati.getText().toString().trim();
        final String jml_anak_pr = tv_editSubyektifBaruJmlAnakPrematur.getText().toString().trim();
        final String hasil_tolong_salin = tv_editSubyektifBaruHasilTolongSalin.getText().toString().trim();
        final String komplikasi = tv_editSubyektifBaruKomplikasi.getText().toString().trim();
        final String jns_kontrasepsi = tv_editSubyektifBaruJNSKontrasepsi.getText().toString().trim();
        final String gravida = tv_editSubyektifBaruGravida.getText().toString().trim();
        final String gerakan_janin = tv_editSubyektifBaruGerakanJanin.getText().toString().trim();
        final String lainnya = tv_editSubyektifBaruLainnya.getText().toString().trim();

        final String tolong_salin = sp_editSubyektifBaruTolongSalin.getSelectedItem().toString();
        final String cara_salin = sp_editSubyektifBaruCaraSalin.getSelectedItem().toString();
        final String kb_sebelum_hml = sp_editSubyektifBaruKBSebelumHamil.getSelectedItem().toString();
        final String suntikan_tt = sp_editSubyektifBaruSuntikanTT.getSelectedItem().toString();

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        final String updated_at = dateFormat.format(cal.getTime());

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_UPDATESUBYEKTIFBARU,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")) {
                                Toast.makeText(EditKunjunganBaruDataSubyektifActivity.this, "Update Data Subyektif Berhasil", Toast.LENGTH_LONG).show();
                                loadingDialog.dissmissDialog();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(EditKunjunganBaruDataSubyektifActivity.this, "Update Data Subyektif Gagal!", Toast.LENGTH_LONG).show();
                            loadingDialog.dissmissDialog();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(EditKunjunganBaruDataSubyektifActivity.this, "Update Data Subyektif Gagal! : Cek Koneksi Anda", Toast.LENGTH_LONG).show();
                        loadingDialog.dissmissDialog();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                params.put("id", id_kunjunganBaru);
                params.put("tanggal", tanggal);
                params.put("keluhan_utama", keluhan_utama);
                params.put("keluhan_tambahan", keluhan_tambahan);
                params.put("riwayat_nikah", riwayat_nikah);
                params.put("rwyt_penyakit", rwyt_penyakit);
                params.put("rwyt_penyakit_keluarga", rwyt_penyakit_keluarga);
                params.put("rwyt_alergi", rwyt_alergi);
                params.put("jml_anak_h", jml_anak_h);
                params.put("jml_anak_m", jml_anak_m);
                params.put("jml_anak_pr", jml_anak_pr);
                params.put("keguguran", keguguran);
                params.put("tolong_salin", tolong_salin);
                params.put("hasil_tolong_salin", hasil_tolong_salin);
                params.put("cara_salin", cara_salin);
                params.put("komplikasi", komplikasi);
                params.put("kb_sebelum_hml", kb_sebelum_hml);
                params.put("jns_kontrasepsi", jns_kontrasepsi);
                params.put("suntikan_tt", suntikan_tt);
                params.put("waktu_beri", waktu_beri);
                params.put("gravida", gravida);
                params.put("hpht", hpht);
                params.put("gerakan_janin", gerakan_janin);
                params.put("lainnya", lainnya);
                params.put("updated_at", updated_at);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void toggleSubyektifBaru() {
        if (!tv_editSubyektifBaruTanggal.isEnabled()) {
            tv_editSubyektifBaruTanggal.setEnabled(true);
            tv_editSubyektifBaruTanggal.setFocusable(true);
            tv_editSubyektifBaruTanggal.setCursorVisible(true);
            tv_editSubyektifBaruTanggal.setFocusableInTouchMode(true);
            tv_editSubyektifBaruTanggal.setTextColor(Color.parseColor("#000000"));
            tv_editSubyektifBaruKeluhanUtama.setEnabled(true);
            tv_editSubyektifBaruKeluhanUtama.setFocusable(true);
            tv_editSubyektifBaruKeluhanUtama.setCursorVisible(true);
            tv_editSubyektifBaruKeluhanUtama.setFocusableInTouchMode(true);
            tv_editSubyektifBaruKeluhanUtama.setTextColor(Color.parseColor("#000000"));
            tv_editSubyektifBaruKeluhanTambahan.setEnabled(true);
            tv_editSubyektifBaruKeluhanTambahan.setFocusable(true);
            tv_editSubyektifBaruKeluhanTambahan.setCursorVisible(true);
            tv_editSubyektifBaruKeluhanTambahan.setFocusableInTouchMode(true);
            tv_editSubyektifBaruKeluhanTambahan.setTextColor(Color.parseColor("#000000"));
            tv_editSubyektifBaruRiwayatNikah.setEnabled(true);
            tv_editSubyektifBaruRiwayatNikah.setFocusable(true);
            tv_editSubyektifBaruRiwayatNikah.setCursorVisible(true);
            tv_editSubyektifBaruRiwayatNikah.setFocusableInTouchMode(true);
            tv_editSubyektifBaruRiwayatNikah.setTextColor(Color.parseColor("#000000"));
            tv_editSubyektifBaruRiwayatPenyakit.setEnabled(true);
            tv_editSubyektifBaruRiwayatPenyakit.setFocusable(true);
            tv_editSubyektifBaruRiwayatPenyakit.setCursorVisible(true);
            tv_editSubyektifBaruRiwayatPenyakit.setFocusableInTouchMode(true);
            tv_editSubyektifBaruRiwayatPenyakit.setTextColor(Color.parseColor("#000000"));
            tv_editSubyektifBaruRiwayatPenyakitKeluarga.setEnabled(true);
            tv_editSubyektifBaruRiwayatPenyakitKeluarga.setFocusable(true);
            tv_editSubyektifBaruRiwayatPenyakitKeluarga.setCursorVisible(true);
            tv_editSubyektifBaruRiwayatPenyakitKeluarga.setFocusableInTouchMode(true);
            tv_editSubyektifBaruRiwayatPenyakitKeluarga.setTextColor(Color.parseColor("#000000"));
            tv_editSubyektifBaruRiwayatAlergi.setEnabled(true);
            tv_editSubyektifBaruRiwayatAlergi.setFocusable(true);
            tv_editSubyektifBaruRiwayatAlergi.setCursorVisible(true);
            tv_editSubyektifBaruRiwayatAlergi.setFocusableInTouchMode(true);
            tv_editSubyektifBaruRiwayatAlergi.setTextColor(Color.parseColor("#000000"));
            tv_editSubyektifBaruJmlAnakHidup.setEnabled(true);
            tv_editSubyektifBaruJmlAnakHidup.setFocusable(true);
            tv_editSubyektifBaruJmlAnakHidup.setCursorVisible(true);
            tv_editSubyektifBaruJmlAnakHidup.setFocusableInTouchMode(true);
            tv_editSubyektifBaruJmlAnakHidup.setTextColor(Color.parseColor("#000000"));
            tv_editSubyektifBaruJmlAnakMati.setEnabled(true);
            tv_editSubyektifBaruJmlAnakMati.setFocusable(true);
            tv_editSubyektifBaruJmlAnakMati.setCursorVisible(true);
            tv_editSubyektifBaruJmlAnakMati.setFocusableInTouchMode(true);
            tv_editSubyektifBaruJmlAnakMati.setTextColor(Color.parseColor("#000000"));
            tv_editSubyektifBaruJmlAnakPrematur.setEnabled(true);
            tv_editSubyektifBaruJmlAnakPrematur.setFocusable(true);
            tv_editSubyektifBaruJmlAnakPrematur.setCursorVisible(true);
            tv_editSubyektifBaruJmlAnakPrematur.setFocusableInTouchMode(true);
            tv_editSubyektifBaruJmlAnakPrematur.setTextColor(Color.parseColor("#000000"));
            tv_editSubyektifBaruKeguguran.setEnabled(true);
            tv_editSubyektifBaruKeguguran.setFocusable(true);
            tv_editSubyektifBaruKeguguran.setCursorVisible(true);
            tv_editSubyektifBaruKeguguran.setFocusableInTouchMode(true);
            tv_editSubyektifBaruKeguguran.setTextColor(Color.parseColor("#000000"));
            tv_editSubyektifBaruHasilTolongSalin.setEnabled(true);
            tv_editSubyektifBaruHasilTolongSalin.setFocusable(true);
            tv_editSubyektifBaruHasilTolongSalin.setCursorVisible(true);
            tv_editSubyektifBaruHasilTolongSalin.setFocusableInTouchMode(true);
            tv_editSubyektifBaruHasilTolongSalin.setTextColor(Color.parseColor("#000000"));
            tv_editSubyektifBaruKomplikasi.setEnabled(true);
            tv_editSubyektifBaruKomplikasi.setFocusable(true);
            tv_editSubyektifBaruKomplikasi.setCursorVisible(true);
            tv_editSubyektifBaruKomplikasi.setFocusableInTouchMode(true);
            tv_editSubyektifBaruKomplikasi.setTextColor(Color.parseColor("#000000"));
            tv_editSubyektifBaruJNSKontrasepsi.setEnabled(true);
            tv_editSubyektifBaruJNSKontrasepsi.setFocusable(true);
            tv_editSubyektifBaruJNSKontrasepsi.setCursorVisible(true);
            tv_editSubyektifBaruJNSKontrasepsi.setFocusableInTouchMode(true);
            tv_editSubyektifBaruJNSKontrasepsi.setTextColor(Color.parseColor("#000000"));
            tv_editSubyektifBaruWaktuBeri.setEnabled(true);
            tv_editSubyektifBaruWaktuBeri.setFocusable(true);
            tv_editSubyektifBaruWaktuBeri.setCursorVisible(true);
            tv_editSubyektifBaruWaktuBeri.setFocusableInTouchMode(true);
            tv_editSubyektifBaruWaktuBeri.setTextColor(Color.parseColor("#000000"));
            tv_editSubyektifBaruGravida.setEnabled(true);
            tv_editSubyektifBaruGravida.setFocusable(true);
            tv_editSubyektifBaruGravida.setCursorVisible(true);
            tv_editSubyektifBaruGravida.setFocusableInTouchMode(true);
            tv_editSubyektifBaruGravida.setTextColor(Color.parseColor("#000000"));
            tv_editSubyektifBaruHPHT.setEnabled(true);
            tv_editSubyektifBaruHPHT.setFocusable(true);
            tv_editSubyektifBaruHPHT.setCursorVisible(true);
            tv_editSubyektifBaruHPHT.setFocusableInTouchMode(true);
            tv_editSubyektifBaruHPHT.setTextColor(Color.parseColor("#000000"));
            tv_editSubyektifBaruGerakanJanin.setEnabled(true);
            tv_editSubyektifBaruGerakanJanin.setFocusable(true);
            tv_editSubyektifBaruGerakanJanin.setCursorVisible(true);
            tv_editSubyektifBaruGerakanJanin.setFocusableInTouchMode(true);
            tv_editSubyektifBaruGerakanJanin.setTextColor(Color.parseColor("#000000"));
            tv_editSubyektifBaruLainnya.setEnabled(true);
            tv_editSubyektifBaruLainnya.setFocusable(true);
            tv_editSubyektifBaruLainnya.setCursorVisible(true);
            tv_editSubyektifBaruLainnya.setFocusableInTouchMode(true);
            tv_editSubyektifBaruLainnya.setTextColor(Color.parseColor("#000000"));
            sp_editSubyektifBaruTolongSalin.setEnabled(true);
            sp_editSubyektifBaruCaraSalin.setEnabled(true);
            sp_editSubyektifBaruKBSebelumHamil.setEnabled(true);
            sp_editSubyektifBaruSuntikanTT.setEnabled(true);
            Toast.makeText(EditKunjunganBaruDataSubyektifActivity.this, "Mode Edit Diaktifkan!", Toast.LENGTH_LONG).show();
        } else if (tv_editSubyektifBaruTanggal.isEnabled()) {
            tv_editSubyektifBaruTanggal.setEnabled(false);
            tv_editSubyektifBaruTanggal.setFocusable(false);
            tv_editSubyektifBaruTanggal.setCursorVisible(false);
            tv_editSubyektifBaruTanggal.setTextColor(Color.parseColor("#919191"));
            tv_editSubyektifBaruKeluhanUtama.setEnabled(false);
            tv_editSubyektifBaruKeluhanUtama.setFocusable(false);
            tv_editSubyektifBaruKeluhanUtama.setCursorVisible(false);
            tv_editSubyektifBaruKeluhanUtama.setTextColor(Color.parseColor("#919191"));
            tv_editSubyektifBaruKeluhanTambahan.setEnabled(false);
            tv_editSubyektifBaruKeluhanTambahan.setFocusable(false);
            tv_editSubyektifBaruKeluhanTambahan.setCursorVisible(false);
            tv_editSubyektifBaruKeluhanTambahan.setTextColor(Color.parseColor("#919191"));
            tv_editSubyektifBaruRiwayatNikah.setEnabled(false);
            tv_editSubyektifBaruRiwayatNikah.setFocusable(false);
            tv_editSubyektifBaruRiwayatNikah.setCursorVisible(false);
            tv_editSubyektifBaruRiwayatNikah.setTextColor(Color.parseColor("#919191"));
            tv_editSubyektifBaruRiwayatPenyakit.setEnabled(false);
            tv_editSubyektifBaruRiwayatPenyakit.setFocusable(false);
            tv_editSubyektifBaruRiwayatPenyakit.setCursorVisible(false);
            tv_editSubyektifBaruRiwayatPenyakit.setTextColor(Color.parseColor("#919191"));
            tv_editSubyektifBaruRiwayatPenyakitKeluarga.setEnabled(false);
            tv_editSubyektifBaruRiwayatPenyakitKeluarga.setFocusable(false);
            tv_editSubyektifBaruRiwayatPenyakitKeluarga.setCursorVisible(false);
            tv_editSubyektifBaruRiwayatPenyakitKeluarga.setTextColor(Color.parseColor("#919191"));
            tv_editSubyektifBaruRiwayatAlergi.setEnabled(false);
            tv_editSubyektifBaruRiwayatAlergi.setFocusable(false);
            tv_editSubyektifBaruRiwayatAlergi.setCursorVisible(false);
            tv_editSubyektifBaruRiwayatAlergi.setTextColor(Color.parseColor("#919191"));
            tv_editSubyektifBaruJmlAnakHidup.setEnabled(false);
            tv_editSubyektifBaruJmlAnakHidup.setFocusable(false);
            tv_editSubyektifBaruJmlAnakHidup.setCursorVisible(false);
            tv_editSubyektifBaruJmlAnakHidup.setTextColor(Color.parseColor("#919191"));
            tv_editSubyektifBaruJmlAnakMati.setEnabled(false);
            tv_editSubyektifBaruJmlAnakMati.setFocusable(false);
            tv_editSubyektifBaruJmlAnakMati.setCursorVisible(false);
            tv_editSubyektifBaruJmlAnakMati.setTextColor(Color.parseColor("#919191"));
            tv_editSubyektifBaruJmlAnakPrematur.setEnabled(false);
            tv_editSubyektifBaruJmlAnakPrematur.setFocusable(false);
            tv_editSubyektifBaruJmlAnakPrematur.setCursorVisible(false);
            tv_editSubyektifBaruJmlAnakPrematur.setTextColor(Color.parseColor("#919191"));
            tv_editSubyektifBaruKeguguran.setEnabled(false);
            tv_editSubyektifBaruKeguguran.setFocusable(false);
            tv_editSubyektifBaruKeguguran.setCursorVisible(false);
            tv_editSubyektifBaruKeguguran.setTextColor(Color.parseColor("#919191"));
            tv_editSubyektifBaruHasilTolongSalin.setEnabled(false);
            tv_editSubyektifBaruHasilTolongSalin.setFocusable(false);
            tv_editSubyektifBaruHasilTolongSalin.setCursorVisible(false);
            tv_editSubyektifBaruHasilTolongSalin.setTextColor(Color.parseColor("#919191"));
            tv_editSubyektifBaruKomplikasi.setEnabled(false);
            tv_editSubyektifBaruKomplikasi.setFocusable(false);
            tv_editSubyektifBaruKomplikasi.setCursorVisible(false);
            tv_editSubyektifBaruKomplikasi.setTextColor(Color.parseColor("#919191"));
            tv_editSubyektifBaruJNSKontrasepsi.setEnabled(false);
            tv_editSubyektifBaruJNSKontrasepsi.setFocusable(false);
            tv_editSubyektifBaruJNSKontrasepsi.setCursorVisible(false);
            tv_editSubyektifBaruJNSKontrasepsi.setTextColor(Color.parseColor("#919191"));
            tv_editSubyektifBaruWaktuBeri.setEnabled(false);
            tv_editSubyektifBaruWaktuBeri.setFocusable(false);
            tv_editSubyektifBaruWaktuBeri.setCursorVisible(false);
            tv_editSubyektifBaruWaktuBeri.setTextColor(Color.parseColor("#919191"));
            tv_editSubyektifBaruGravida.setEnabled(false);
            tv_editSubyektifBaruGravida.setFocusable(false);
            tv_editSubyektifBaruGravida.setCursorVisible(false);
            tv_editSubyektifBaruGravida.setTextColor(Color.parseColor("#919191"));
            tv_editSubyektifBaruRiwayatPenyakit.setEnabled(false);
            tv_editSubyektifBaruRiwayatPenyakit.setFocusable(false);
            tv_editSubyektifBaruRiwayatPenyakit.setCursorVisible(false);
            tv_editSubyektifBaruRiwayatPenyakit.setTextColor(Color.parseColor("#919191"));
            tv_editSubyektifBaruHPHT.setEnabled(false);
            tv_editSubyektifBaruHPHT.setFocusable(false);
            tv_editSubyektifBaruHPHT.setCursorVisible(false);
            tv_editSubyektifBaruHPHT.setTextColor(Color.parseColor("#919191"));
            tv_editSubyektifBaruGerakanJanin.setEnabled(false);
            tv_editSubyektifBaruGerakanJanin.setFocusable(false);
            tv_editSubyektifBaruGerakanJanin.setCursorVisible(false);
            tv_editSubyektifBaruGerakanJanin.setTextColor(Color.parseColor("#919191"));
            tv_editSubyektifBaruLainnya.setEnabled(false);
            tv_editSubyektifBaruLainnya.setFocusable(false);
            tv_editSubyektifBaruLainnya.setCursorVisible(false);
            tv_editSubyektifBaruLainnya.setTextColor(Color.parseColor("#919191"));
            sp_editSubyektifBaruSuntikanTT.setEnabled(false);
            sp_editSubyektifBaruKBSebelumHamil.setEnabled(false);
            sp_editSubyektifBaruTolongSalin.setEnabled(false);
            sp_editSubyektifBaruCaraSalin.setEnabled(false);
            Toast.makeText(EditKunjunganBaruDataSubyektifActivity.this, "Mode Edit Dinonaktifkan!", Toast.LENGTH_SHORT).show();
        }
    }

    private void loadDataSubyektifBaru() {
        String URL_READBOOKING = "http://simetalia.com/pkm/webservice/kunjunganbaru/" + id_kunjunganBaru;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_READBOOKING,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONObject object = jsonObject.getJSONObject("read");

                            if (success.equals("1")) {

                                String tanggal = object.getString("tanggal").trim();
                                String keluhan_utama = object.getString("keluhan_utama").trim();
                                String keluhan_tambahan = object.getString("keluhan_tambahan").trim();
                                String riwayat_nikah = object.getString("riwayat_nikah").trim();
                                String rwyt_penyakit = object.getString("rwyt_penyakit").trim();
                                String rwyt_penyakit_keluarga = object.getString("rwyt_penyakit_keluarga").trim();
                                String rwyt_alergi = object.getString("rwyt_alergi").trim();
                                String jml_anak_h = object.getString("jml_anak_h").trim();
                                String jml_anak_m = object.getString("jml_anak_m").trim();
                                String jml_anak_pr = object.getString("jml_anak_pr").trim();
                                String keguguran = object.getString("keguguran").trim();
                                String tolong_salin = object.getString("tolong_salin").trim();
                                String hasil_tolong_salin = object.getString("hasil_tolong_salin").trim();
                                String cara_salin = object.getString("cara_salin").trim();
                                String komplikasi = object.getString("komplikasi").trim();
                                String kb_sebelum_hml = object.getString("kb_sebelum_hml").trim();
                                String jns_kontrasepsi = object.getString("jns_kontrasepsi").trim();
                                String suntikan_tt = object.getString("suntikan_tt").trim();
                                String waktu_beri = object.getString("waktu_beri").trim();
                                String gravida = object.getString("gravida").trim();
                                String hpht = object.getString("hpht").trim();
                                String gerakan_janin = object.getString("gerakan_janin").trim();
                                String lainnya = object.getString("lainnya").trim();

                                tv_editSubyektifBaruTanggal.setText(tanggal);
                                tv_editSubyektifBaruKeluhanUtama.setText(keluhan_utama);
                                tv_editSubyektifBaruKeluhanTambahan.setText(keluhan_tambahan);
                                tv_editSubyektifBaruRiwayatNikah.setText(riwayat_nikah);
                                tv_editSubyektifBaruRiwayatPenyakit.setText(rwyt_penyakit);
                                tv_editSubyektifBaruRiwayatPenyakitKeluarga.setText(rwyt_penyakit_keluarga);
                                tv_editSubyektifBaruRiwayatAlergi.setText(rwyt_alergi);
                                tv_editSubyektifBaruJmlAnakHidup.setText(jml_anak_h);
                                tv_editSubyektifBaruJmlAnakMati.setText(jml_anak_m);
                                tv_editSubyektifBaruJmlAnakPrematur.setText(jml_anak_pr);
                                tv_editSubyektifBaruKeguguran.setText(keguguran);
                                tv_editSubyektifBaruHasilTolongSalin.setText(hasil_tolong_salin);
                                tv_editSubyektifBaruKomplikasi.setText(komplikasi);
                                tv_editSubyektifBaruJNSKontrasepsi.setText(jns_kontrasepsi);
                                tv_editSubyektifBaruWaktuBeri.setText(waktu_beri);
                                tv_editSubyektifBaruGravida.setText(gravida);
                                tv_editSubyektifBaruHPHT.setText(hpht);
                                tv_editSubyektifBaruGerakanJanin.setText(gerakan_janin);
                                tv_editSubyektifBaruLainnya.setText(lainnya);


                                ArrayAdapter<String> tolongsalinAdapter = new ArrayAdapter<String>(EditKunjunganBaruDataSubyektifActivity.this,
                                        android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.tolong_salin));
                                tolongsalinAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                sp_editSubyektifBaruTolongSalin.setAdapter(tolongsalinAdapter);
                                if (tolong_salin != null) {
                                    int spinnerPositionTolongSalin = tolongsalinAdapter.getPosition(tolong_salin);
                                    sp_editSubyektifBaruTolongSalin.setSelection(spinnerPositionTolongSalin);
                                }

                                ArrayAdapter<String> caraSalinAdapter = new ArrayAdapter<>(EditKunjunganBaruDataSubyektifActivity.this,
                                        android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.cara_salin));
                                caraSalinAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                sp_editSubyektifBaruCaraSalin.setAdapter(caraSalinAdapter);
                                if (cara_salin != null) {
                                    int spinnerPositionGoldar = caraSalinAdapter.getPosition(cara_salin);
                                    sp_editSubyektifBaruCaraSalin.setSelection(spinnerPositionGoldar);
                                }

                                ArrayAdapter<String> kbSebelumHamilAdapter = new ArrayAdapter<String>(EditKunjunganBaruDataSubyektifActivity.this,
                                        android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.kb_sebelum_hml));
                                kbSebelumHamilAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                sp_editSubyektifBaruKBSebelumHamil.setAdapter(kbSebelumHamilAdapter);
                                if (kb_sebelum_hml != null) {
                                    int spinnerPositionPendidikan = kbSebelumHamilAdapter.getPosition(kb_sebelum_hml);
                                    sp_editSubyektifBaruKBSebelumHamil.setSelection(spinnerPositionPendidikan);
                                }

                                ArrayAdapter<String> suntikanTTAdapter = new ArrayAdapter<String>(EditKunjunganBaruDataSubyektifActivity.this,
                                        android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.suntikan_tt));
                                kbSebelumHamilAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                sp_editSubyektifBaruSuntikanTT.setAdapter(suntikanTTAdapter);
                                if (suntikan_tt != null) {
                                    int spinnerPositionSuntikanTT = kbSebelumHamilAdapter.getPosition(suntikan_tt);
                                    sp_editSubyektifBaruSuntikanTT.setSelection(spinnerPositionSuntikanTT);
                                }

                                loadingDialog.dissmissDialog();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(EditKunjunganBaruDataSubyektifActivity.this, "Data Ibu Hamil Tidak Ada!" + e.toString(), Toast.LENGTH_SHORT).show();
                            loadingDialog.dissmissDialog();
                        }

                    }
                }
                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(EditKunjunganBaruDataSubyektifActivity.this, "Koneksi Gagal!" + error.toString(), Toast.LENGTH_SHORT).show();
                loadingDialog.dissmissDialog();
            }
        }) {
        };
        RequestQueue requestQueue = Volley.newRequestQueue(EditKunjunganBaruDataSubyektifActivity.this);
        requestQueue.add(stringRequest);
    }
}
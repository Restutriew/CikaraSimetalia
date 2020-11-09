package com.cikarastudio.cikarasimetalia.activity.nifas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
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
import com.cikarastudio.cikarasimetalia.activity.kehamilan.TambahDataPersalinanActivity;
import com.cikarastudio.cikarasimetalia.activity.kunjunganbaru.TambahDataKunjunganBaruActivity;
import com.cikarastudio.cikarasimetalia.dialog.LoadingDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class TambahDataNifasActivity extends AppCompatActivity {

    public static final String ID_KUNJUNGANBARU = "extra_data";
    LoadingDialog loadingDialog;
    String pkmkehamilan_id, tanggal_rujukan;
    EditText et_tambahNifasTanggalKunjungan, et_tambahNifasKunjunganKe, et_tambahNifasKeluhan, et_tambahKondisiPerineum,
            et_tambahNifasTFU, et_tambahNifasJalanLahir, et_tambahNifasPayudara, et_tambahNifasHasilPostpartumKe3,
            et_tambahNifasJenisKontrasepsi, et_tambahNifasKomplikasi, et_tambahNifasKomplikasiNifas,
            et_tambahNifasAsuhan, et_tambahNifasKonseling, et_tambahNifasTanggalRujukan, et_tambahNifasNamaFaskes,
            et_tambahNifasAlasanDirujuk, et_tambahNifasDiagnosaSementara, et_tambahNifasTindakanSementara;
    Spinner sp_tambaNifasKeadaanUmum, sp_tambahNifasKesadaran, sp_tambahNifasTandaVital, sp_tambahNifasPendarahan,
            sp_tambahNifasTandaInfeksi, sp_tambahNifasKondisiUterus, sp_tambahNifasLochea, sp_tambahNifasProduksiASI,
            sp_tambahNifasKapsulVita, sp_tambahNifasTabletFE, sp_tambahNifasPostpartumKe3, sp_tambahNifasKontrasepsi,
            sp_tambahNifasBAB, sp_tambahNifasBAK, sp_tambaNifasFaskes;
    LinearLayout btn_tambahNifas;
    private static String URL_TAMBAHNIFAS = "http://simetalia.com/android/tambah_data_nifas.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_data_nifas);

        loadingDialog = new LoadingDialog(TambahDataNifasActivity.this);

        Intent intent = getIntent();
        pkmkehamilan_id = intent.getStringExtra(ID_KUNJUNGANBARU);

        et_tambahNifasTanggalKunjungan = findViewById(R.id.et_tambahNifasTanggalKunjungan);
        et_tambahNifasKunjunganKe = findViewById(R.id.et_tambahNifasKunjunganKe);
        et_tambahNifasKeluhan = findViewById(R.id.et_tambahNifasKeluhan);
        sp_tambaNifasKeadaanUmum = findViewById(R.id.sp_tambaNifasKeadaanUmum);
        sp_tambahNifasKesadaran = findViewById(R.id.sp_tambahNifasKesadaran);
        sp_tambahNifasTandaVital = findViewById(R.id.sp_tambahNifasTandaVital);
        sp_tambahNifasPendarahan = findViewById(R.id.sp_tambahNifasPendarahan);
        et_tambahKondisiPerineum = findViewById(R.id.et_tambahKondisiPerineum);
        sp_tambahNifasTandaInfeksi = findViewById(R.id.sp_tambahNifasTandaInfeksi);
        sp_tambahNifasKondisiUterus = findViewById(R.id.sp_tambahNifasKondisiUterus);
        et_tambahNifasTFU = findViewById(R.id.et_tambahNifasTFU);
        sp_tambahNifasLochea = findViewById(R.id.sp_tambahNifasLochea);
        et_tambahNifasJalanLahir = findViewById(R.id.et_tambahNifasJalanLahir);
        et_tambahNifasPayudara = findViewById(R.id.et_tambahNifasPayudara);
        sp_tambahNifasProduksiASI = findViewById(R.id.sp_tambahNifasProduksiASI);
        sp_tambahNifasKapsulVita = findViewById(R.id.sp_tambahNifasKapsulVita);
        sp_tambahNifasTabletFE = findViewById(R.id.sp_tambahNifasTabletFE);
        sp_tambahNifasPostpartumKe3 = findViewById(R.id.sp_tambahNifasPostpartumKe3);
        et_tambahNifasHasilPostpartumKe3 = findViewById(R.id.et_tambahNifasHasilPostpartumKe3);
        sp_tambahNifasKontrasepsi = findViewById(R.id.sp_tambahNifasKontrasepsi);
        et_tambahNifasJenisKontrasepsi = findViewById(R.id.et_tambahNifasJenisKontrasepsi);
        et_tambahNifasKomplikasi = findViewById(R.id.et_tambahNifasKomplikasi);
        sp_tambahNifasBAB = findViewById(R.id.sp_tambahNifasBAB);
        sp_tambahNifasBAK = findViewById(R.id.sp_tambahNifasBAK);
        et_tambahNifasKomplikasiNifas = findViewById(R.id.et_tambahNifasKomplikasiNifas);
        et_tambahNifasAsuhan = findViewById(R.id.et_tambahNifasAsuhan);
        et_tambahNifasKonseling = findViewById(R.id.et_tambahNifasKonseling);
        et_tambahNifasTanggalRujukan = findViewById(R.id.et_tambahNifasTanggalRujukan);
        sp_tambaNifasFaskes = findViewById(R.id.sp_tambaNifasFaskes);
        et_tambahNifasNamaFaskes = findViewById(R.id.et_tambahNifasNamaFaskes);
        et_tambahNifasAlasanDirujuk = findViewById(R.id.et_tambahNifasAlasanDirujuk);
        et_tambahNifasDiagnosaSementara = findViewById(R.id.et_tambahNifasDiagnosaSementara);
        et_tambahNifasTindakanSementara = findViewById(R.id.et_tambahNifasTindakanSementara);
        btn_tambahNifas = findViewById(R.id.btn_tambahNifas);

        data_spinner();

        btn_tambahNifas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cekFormNifas();
            }
        });

    }

    private void data_spinner() {
        ArrayAdapter<String> keadaanUmumAdapter = new ArrayAdapter<>(TambahDataNifasActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.keadaan_umum));
        keadaanUmumAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_tambaNifasKeadaanUmum.setAdapter(keadaanUmumAdapter);

        ArrayAdapter<String> kesadaranAdapter = new ArrayAdapter<>(TambahDataNifasActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.kesadaran));
        kesadaranAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_tambahNifasKesadaran.setAdapter(kesadaranAdapter);

        ArrayAdapter<String> tandaVitalAdapter = new ArrayAdapter<>(TambahDataNifasActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.tanda_vital));
        tandaVitalAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_tambahNifasTandaVital.setAdapter(tandaVitalAdapter);

        ArrayAdapter<String> pendarahanAdapter = new ArrayAdapter<>(TambahDataNifasActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.normal_tdk));
        pendarahanAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_tambahNifasPendarahan.setAdapter(pendarahanAdapter);

        ArrayAdapter<String> tandaInfeksiAdapter = new ArrayAdapter<>(TambahDataNifasActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.ada_tdk));
        tandaInfeksiAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_tambahNifasTandaInfeksi.setAdapter(tandaInfeksiAdapter);

        ArrayAdapter<String> kondisiUterusAdapter = new ArrayAdapter<>(TambahDataNifasActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.keras_lnk));
        kondisiUterusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_tambahNifasKondisiUterus.setAdapter(kondisiUterusAdapter);

        ArrayAdapter<String> locheaAdapter = new ArrayAdapter<>(TambahDataNifasActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.lochea));
        locheaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_tambahNifasLochea.setAdapter(locheaAdapter);

        ArrayAdapter<String> produksiAsiAdapter = new ArrayAdapter<>(TambahDataNifasActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.cukup_tdk));
        produksiAsiAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_tambahNifasProduksiASI.setAdapter(produksiAsiAdapter);

        ArrayAdapter<String> kapsulVitaAdapter = new ArrayAdapter<>(TambahDataNifasActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.kapsul_vita));
        kapsulVitaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_tambahNifasKapsulVita.setAdapter(kapsulVitaAdapter);

        ArrayAdapter<String> tabletFEAdapter = new ArrayAdapter<>(TambahDataNifasActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.tablet_fe));
        tabletFEAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_tambahNifasTabletFE.setAdapter(tabletFEAdapter);

        ArrayAdapter<String> postpartumKe3Adapter = new ArrayAdapter<>(TambahDataNifasActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.ya_tidak));
        postpartumKe3Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_tambahNifasPostpartumKe3.setAdapter(postpartumKe3Adapter);

        ArrayAdapter<String> kontrasepsiAdapter = new ArrayAdapter<>(TambahDataNifasActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.ya_tidak));
        kontrasepsiAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_tambahNifasKontrasepsi.setAdapter(kontrasepsiAdapter);

        ArrayAdapter<String> BABAdapter = new ArrayAdapter<>(TambahDataNifasActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.ya_tidak));
        BABAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_tambahNifasBAB.setAdapter(BABAdapter);

        ArrayAdapter<String> BAKAdapter = new ArrayAdapter<>(TambahDataNifasActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.ya_tidak));
        BAKAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_tambahNifasBAK.setAdapter(BAKAdapter);

        ArrayAdapter<String> faskesAdapter = new ArrayAdapter<>(TambahDataNifasActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.faskes));
        faskesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_tambaNifasFaskes.setAdapter(faskesAdapter);


    }

    private void cekFormNifas() {
        String Stanggalkunjungan = et_tambahNifasTanggalKunjungan.getText().toString();
        String SkunjunganKe = et_tambahNifasKunjunganKe.getText().toString();
        String Skeluhan = et_tambahNifasKeluhan.getText().toString();

        if (Stanggalkunjungan.length() < 10) {
            et_tambahNifasTanggalKunjungan.setError("Silahkan Masukkan Tanggal Kunjungan!");
            et_tambahNifasTanggalKunjungan.requestFocus();
        } else if (SkunjunganKe.equals("")) {
            et_tambahNifasKunjunganKe.setError("Silahkan Masukkan Kunjungan Ke!");
            et_tambahNifasKunjunganKe.requestFocus();
        } else if (Skeluhan.equals("")) {
            et_tambahNifasKeluhan.setError("Silahkan Masukkan Keluhan!");
            et_tambahNifasKeluhan.requestFocus();
        } else {
            tambahDataNifas();
        }
    }

    private void tambahDataNifas() {
        loadingDialog.startLoading();
        final String tanggalawal = et_tambahNifasTanggalKunjungan.getText().toString().trim();
        String tanggaltengah = tanggalawal.substring(0, 2);
        String bulantengah = tanggalawal.substring(3, 5);
        String tahuntengah = tanggalawal.substring(6, 10);
        final String tanggal_kunjungan = tahuntengah + "-" + bulantengah + "-" + tanggaltengah;

        final String kunjungan_ke = et_tambahNifasKunjunganKe.getText().toString().trim();
        final String keluhan = et_tambahNifasKeluhan.getText().toString().trim();
        final String kondisi_perineum = et_tambahKondisiPerineum.getText().toString().trim();
        final String tfu = et_tambahNifasTFU.getText().toString().trim();
        final String jalan_lahir = et_tambahNifasJalanLahir.getText().toString().trim();
        final String payudara = et_tambahNifasPayudara.getText().toString().trim();
        final String hasil_postpartum_ke3 = et_tambahNifasHasilPostpartumKe3.getText().toString().trim();
        final String jenis_kontrasepsi = et_tambahNifasJenisKontrasepsi.getText().toString().trim();
        final String komplikasi = et_tambahNifasKomplikasi.getText().toString().trim();
        final String komplikasi_nifas = et_tambahNifasKomplikasiNifas.getText().toString().trim();
        final String asuhan = et_tambahNifasAsuhan.getText().toString().trim();
        final String konseling = et_tambahNifasKonseling.getText().toString().trim();

        String tanggal_rujukan_awal = et_tambahNifasTanggalRujukan.getText().toString().trim();
        if (tanggal_rujukan_awal.length() == 10) {
            String tanggalrujukantengah = tanggal_rujukan_awal.substring(0, 2);
            String bulanrujukantengah = tanggal_rujukan_awal.substring(3, 5);
            String tahunrujukantengah = tanggal_rujukan_awal.substring(6, 10);
            tanggal_rujukan = tahunrujukantengah + "-" + bulanrujukantengah + "-" + tanggalrujukantengah;
        } else {
            tanggal_rujukan = "";
        }

        final String nama_faskes = et_tambahNifasNamaFaskes.getText().toString().trim();
        final String alasan_dirujuk = et_tambahNifasAlasanDirujuk.getText().toString().trim();
        final String diagnosa_sementara = et_tambahNifasAlasanDirujuk.getText().toString().trim();
        final String tindakan_sementara = et_tambahNifasAlasanDirujuk.getText().toString().trim();

        final String keadaan_umum = sp_tambaNifasKeadaanUmum.getSelectedItem().toString();
        final String kesadaran = sp_tambahNifasKesadaran.getSelectedItem().toString();
        final String tanda_vital = sp_tambahNifasTandaVital.getSelectedItem().toString();
        final String pendarahan = sp_tambahNifasPendarahan.getSelectedItem().toString();
        final String tanda_infeksi = sp_tambahNifasTandaInfeksi.getSelectedItem().toString();
        final String kondisi_uterus = sp_tambahNifasKondisiUterus.getSelectedItem().toString();
        final String lochea = sp_tambahNifasLochea.getSelectedItem().toString();
        final String produksi_asi = sp_tambahNifasProduksiASI.getSelectedItem().toString();
        final String kapsul_vita = sp_tambahNifasKapsulVita.getSelectedItem().toString();
        final String tablet_fe = sp_tambahNifasTabletFE.getSelectedItem().toString();
        final String postpartum_ke3 = sp_tambahNifasPostpartumKe3.getSelectedItem().toString();
        final String kontrasepsi = sp_tambahNifasKontrasepsi.getSelectedItem().toString();
        final String bab = sp_tambahNifasBAB.getSelectedItem().toString();
        final String bak = sp_tambahNifasBAK.getSelectedItem().toString();
        final String faskes = sp_tambaNifasFaskes.getSelectedItem().toString();

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        final String created_at = dateFormat.format(cal.getTime());

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_TAMBAHNIFAS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")) {
                                Toast.makeText(TambahDataNifasActivity.this, "Tambah Nifas Sukses", Toast.LENGTH_LONG).show();
                                loadingDialog.dissmissDialog();
//                                finish();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(TambahDataNifasActivity.this, "Tambah Nifas Gagal!", Toast.LENGTH_LONG).show();
                            loadingDialog.dissmissDialog();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(TambahDataNifasActivity.this, "Tambah Nifas Gagal! : Cek Koneksi Anda", Toast.LENGTH_LONG).show();
                        loadingDialog.dissmissDialog();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                params.put("pkmkehamilan_id", pkmkehamilan_id);
                params.put("tanggal_kunjungan", tanggal_kunjungan);
                params.put("kunjungan_ke", kunjungan_ke);
                params.put("keluhan", keluhan);
                params.put("keadaan_umum", keadaan_umum);
                params.put("kesadaran", kesadaran);
                params.put("tanda_vital", tanda_vital);
                params.put("pendarahan", pendarahan);
                params.put("kondisi_perineum", kondisi_perineum);
                params.put("tanda_infeksi", tanda_infeksi);
                params.put("kondisi_uterus", kondisi_uterus);
                params.put("tfu", tfu);
                params.put("lochea", lochea);
                params.put("jalan_lahir", jalan_lahir);
                params.put("payudara", payudara);
                params.put("produksi_asi", produksi_asi);
                params.put("kapsul_vita", kapsul_vita);
                params.put("tablet_fe", tablet_fe);
                params.put("postpartum_ke3", postpartum_ke3);
                params.put("hasil_postpartum_ke3", hasil_postpartum_ke3);
                params.put("kontrasepsi", kontrasepsi);
                params.put("jenis_kontrasepsi", jenis_kontrasepsi);
                params.put("komplikasi", komplikasi);
                params.put("bab", bab);
                params.put("bak", bak);
                params.put("komplikasi_nifas", komplikasi_nifas);
                params.put("asuhan", asuhan);
                params.put("konseling", konseling);
                params.put("tanggal_rujukan", tanggal_rujukan);
                params.put("faskes", faskes);
                params.put("nama_faskes", nama_faskes);
                params.put("alasan_dirujuk", alasan_dirujuk);
                params.put("diagnosa_sementara", diagnosa_sementara);
                params.put("tindakan_sementara", tindakan_sementara);
                params.put("created_at", created_at);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
package com.cikarastudio.cikarasimetalia.activity.nifas;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
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
import com.cikarastudio.cikarasimetalia.activity.kehamilan.TambahDataPersalinanActivity;
import com.cikarastudio.cikarasimetalia.activity.kunjunganbaru.EditKunjunganBaruDataObyektifActivity;
import com.cikarastudio.cikarasimetalia.dialog.LoadingDialog;
import com.cikarastudio.cikarasimetalia.model.KunjunganLamaModel;
import com.cikarastudio.cikarasimetalia.model.NifasModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class EditDataNifasActivity extends AppCompatActivity {

    public static final String NIFAS_DATA = "extra_data";
    String id_nifasget,tanggal_rujukan;
    LoadingDialog loadingDialog;
    EditText tv_editNifasTanggalKunjungan, tv_editNifasKunjunganKe, tv_editNifasKeluhan,
            tv_editNifasKondisiPerineum, tv_editNifasTFU, tv_editNifasJalanLahir, tv_editNifasPayudara,
            tv_editNifasHasilPostpartumKe3, tv_editNifasJenisKontrasepsi, tv_editNifasKomplikasi,
            tv_editNifasKomplikasiNifas, tv_editNifasAsuhan, tv_editNifasKonseling, tv_editNifasTanggalRujukan,
            tv_editNifasNamaFaskes, tv_editNifasAlasanDirujuk, tv_editNifasDiagnosaSementara,
            tv_editNifasTindakanSementara;
    Spinner sp_editNifasKeadaanUmum, sp_editNifasKesadaran, sp_editNifasTandaVital, sp_editNifasPendarahan,
            sp_editNifasTandaInfeksi, sp_editNifasKondisiUterus, sp_editNifasLOCHEA, sp_editNifasProduksiASI,
            sp_editNifasKapsulVita, sp_editNifasTabletFE, sp_editNifasPostpartumKe3, sp_editNifasKontrasepsi,
            sp_editNifasBAB, sp_editNifasBAK, sp_editNifasFaskes;
    ImageView btn_toggleEditNifas;
    LinearLayout btn_updateDataNifas;
    private static String URL_UPDATENIFAS = "http://simetalia.com/android/update_data_nifas.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data_nifas);

        loadingDialog = new LoadingDialog(EditDataNifasActivity.this);
        loadingDialog.startLoading();

        NifasModel NifasData = getIntent().getParcelableExtra(NIFAS_DATA);
        final String id_nifas = NifasData.getId_nifas();
        id_nifasget = id_nifas;

        tv_editNifasTanggalKunjungan = findViewById(R.id.tv_editNifasTanggalKunjungan);
        tv_editNifasKunjunganKe = findViewById(R.id.tv_editNifasKunjunganKe);
        tv_editNifasKeluhan = findViewById(R.id.tv_editNifasKeluhan);
        sp_editNifasKeadaanUmum = findViewById(R.id.sp_editNifasKeadaanUmum);
        sp_editNifasKesadaran = findViewById(R.id.sp_editNifasKesadaran);
        sp_editNifasTandaVital = findViewById(R.id.sp_editNifasTandaVital);
        sp_editNifasPendarahan = findViewById(R.id.sp_editNifasPendarahan);
        tv_editNifasKondisiPerineum = findViewById(R.id.tv_editNifasKondisiPerineum);
        sp_editNifasTandaInfeksi = findViewById(R.id.sp_editNifasTandaInfeksi);
        sp_editNifasKondisiUterus = findViewById(R.id.sp_editNifasKondisiUterus);
        tv_editNifasTFU = findViewById(R.id.tv_editNifasTFU);
        sp_editNifasLOCHEA = findViewById(R.id.sp_editNifasLOCHEA);
        tv_editNifasJalanLahir = findViewById(R.id.tv_editNifasJalanLahir);
        tv_editNifasPayudara = findViewById(R.id.tv_editNifasPayudara);
        sp_editNifasProduksiASI = findViewById(R.id.sp_editNifasProduksiASI);
        sp_editNifasKapsulVita = findViewById(R.id.sp_editNifasKapsulVita);
        sp_editNifasTabletFE = findViewById(R.id.sp_editNifasTabletFE);
        sp_editNifasPostpartumKe3 = findViewById(R.id.sp_editNifasPostpartumKe3);
        tv_editNifasHasilPostpartumKe3 = findViewById(R.id.tv_editNifasHasilPostpartumKe3);
        sp_editNifasKontrasepsi = findViewById(R.id.sp_editNifasKontrasepsi);
        tv_editNifasJenisKontrasepsi = findViewById(R.id.tv_editNifasJenisKontrasepsi);
        tv_editNifasKomplikasi = findViewById(R.id.tv_editNifasKomplikasi);
        sp_editNifasBAB = findViewById(R.id.sp_editNifasBAB);
        sp_editNifasBAK = findViewById(R.id.sp_editNifasBAK);
        tv_editNifasKomplikasiNifas = findViewById(R.id.tv_editNifasKomplikasiNifas);
        tv_editNifasAsuhan = findViewById(R.id.tv_editNifasAsuhan);
        tv_editNifasKonseling = findViewById(R.id.tv_editNifasKonseling);
        tv_editNifasTanggalRujukan = findViewById(R.id.tv_editNifasTanggalRujukan);
        sp_editNifasFaskes = findViewById(R.id.sp_editNifasFaskes);
        tv_editNifasNamaFaskes = findViewById(R.id.tv_editNifasNamaFaskes);
        tv_editNifasAlasanDirujuk = findViewById(R.id.tv_editNifasAlasanDirujuk);
        tv_editNifasDiagnosaSementara = findViewById(R.id.tv_editNifasDiagnosaSementara);
        tv_editNifasTindakanSementara = findViewById(R.id.tv_editNifasTindakanSementara);
        btn_toggleEditNifas = findViewById(R.id.btn_toggleEditNifas);
        btn_updateDataNifas = findViewById(R.id.btn_updateDataNifas);

        sp_editNifasKeadaanUmum.setEnabled(false);
        sp_editNifasKesadaran.setEnabled(false);
        sp_editNifasTandaVital.setEnabled(false);
        sp_editNifasPendarahan.setEnabled(false);
        sp_editNifasTandaInfeksi.setEnabled(false);
        sp_editNifasKondisiUterus.setEnabled(false);
        sp_editNifasLOCHEA.setEnabled(false);
        sp_editNifasProduksiASI.setEnabled(false);
        sp_editNifasKapsulVita.setEnabled(false);
        sp_editNifasTabletFE.setEnabled(false);
        sp_editNifasPostpartumKe3.setEnabled(false);
        sp_editNifasKontrasepsi.setEnabled(false);
        sp_editNifasBAB.setEnabled(false);
        sp_editNifasBAK.setEnabled(false);
        sp_editNifasFaskes.setEnabled(false);

        spinnerNifas();
        loadDataNifas();

        btn_toggleEditNifas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleNifas();
            }
        });

        btn_updateDataNifas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cekFormNifas();
            }
        });

    }

    private void cekFormNifas() {
        String Stanggalkunjungan = tv_editNifasTanggalKunjungan.getText().toString();
        String SkunjunganKe = tv_editNifasKunjunganKe.getText().toString();
        String Skeluhan = tv_editNifasKeluhan.getText().toString();

        if (Stanggalkunjungan.length() < 10) {
            tv_editNifasTanggalKunjungan.setError("Silahkan Masukkan Tanggal Kunjungan!");
            tv_editNifasTanggalKunjungan.requestFocus();
        } else if (SkunjunganKe.equals("")) {
            tv_editNifasKunjunganKe.setError("Silahkan Masukkan Kunjungan Ke!");
            tv_editNifasKunjunganKe.requestFocus();
        } else if (Skeluhan.equals("")) {
            tv_editNifasKeluhan.setError("Silahkan Masukkan Keluhan!");
            tv_editNifasKeluhan.requestFocus();
        } else {
            updateDataNifas();
        }
    }

    private void updateDataNifas() {
        loadingDialog.startLoading();
        final String tanggalawal = tv_editNifasTanggalKunjungan.getText().toString().trim();
        String tanggaltengah = tanggalawal.substring(0, 2);
        String bulantengah = tanggalawal.substring(3, 5);
        String tahuntengah = tanggalawal.substring(6, 10);
        final String tanggal_kunjungan = tahuntengah + "-" + bulantengah + "-" + tanggaltengah;

        final String kunjungan_ke = tv_editNifasKunjunganKe.getText().toString().trim();
        final String keluhan = tv_editNifasKeluhan.getText().toString().trim();
        final String kondisi_perineum = tv_editNifasKondisiPerineum.getText().toString().trim();
        final String tfu = tv_editNifasTFU.getText().toString().trim();
        final String jalan_lahir = tv_editNifasJalanLahir.getText().toString().trim();
        final String payudara = tv_editNifasPayudara.getText().toString().trim();
        final String hasil_postpartum_ke3 = tv_editNifasHasilPostpartumKe3.getText().toString().trim();
        final String jenis_kontrasepsi = tv_editNifasJenisKontrasepsi.getText().toString().trim();
        final String komplikasi = tv_editNifasKomplikasi.getText().toString().trim();
        final String komplikasi_nifas = tv_editNifasKomplikasiNifas.getText().toString().trim();
        final String asuhan = tv_editNifasAsuhan.getText().toString().trim();
        final String konseling = tv_editNifasKonseling.getText().toString().trim();

        String tanggal_rujukan_awal = tv_editNifasTanggalRujukan.getText().toString().trim();
        if (tanggal_rujukan_awal.length() == 10) {
            String tanggalrujukantengah = tanggal_rujukan_awal.substring(0, 2);
            String bulanrujukantengah = tanggal_rujukan_awal.substring(3, 5);
            String tahunrujukantengah = tanggal_rujukan_awal.substring(6, 10);
            tanggal_rujukan = tahunrujukantengah + "-" + bulanrujukantengah + "-" + tanggalrujukantengah;
        } else {
            tanggal_rujukan = "";
        }

        final String nama_faskes = tv_editNifasNamaFaskes.getText().toString().trim();
        final String alasan_dirujuk = tv_editNifasAlasanDirujuk.getText().toString().trim();
        final String diagnosa_sementara = tv_editNifasDiagnosaSementara.getText().toString().trim();
        final String tindakan_sementara = tv_editNifasAlasanDirujuk.getText().toString().trim();

        final String keadaan_umum = sp_editNifasKeadaanUmum.getSelectedItem().toString();
        final String kesadaran = sp_editNifasKesadaran.getSelectedItem().toString();
        final String tanda_vital = sp_editNifasTandaVital.getSelectedItem().toString();
        final String pendarahan = sp_editNifasPendarahan.getSelectedItem().toString();
        final String tanda_infeksi = sp_editNifasTandaInfeksi.getSelectedItem().toString();
        final String kondisi_uterus = sp_editNifasKondisiUterus.getSelectedItem().toString();
        final String lochea = sp_editNifasLOCHEA.getSelectedItem().toString();
        final String produksi_asi = sp_editNifasProduksiASI.getSelectedItem().toString();
        final String kapsul_vita = sp_editNifasKapsulVita.getSelectedItem().toString();
        final String tablet_fe = sp_editNifasTabletFE.getSelectedItem().toString();
        final String postpartum_ke3 = sp_editNifasPostpartumKe3.getSelectedItem().toString();
        final String kontrasepsi = sp_editNifasKontrasepsi.getSelectedItem().toString();
        final String bab = sp_editNifasBAB.getSelectedItem().toString();
        final String bak = sp_editNifasBAK.getSelectedItem().toString();
        final String faskes = sp_editNifasFaskes.getSelectedItem().toString();

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        final String updated_at = dateFormat.format(cal.getTime());

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_UPDATENIFAS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")) {
                                Toast.makeText(EditDataNifasActivity.this, "Update Nifas Sukses", Toast.LENGTH_LONG).show();
                                loadingDialog.dissmissDialog();
//                                finish();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(EditDataNifasActivity.this, "Update Nifas Gagal!", Toast.LENGTH_LONG).show();
                            loadingDialog.dissmissDialog();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(EditDataNifasActivity.this, "Update Nifas Gagal! : Cek Koneksi Anda", Toast.LENGTH_LONG).show();
                        loadingDialog.dissmissDialog();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                params.put("id", id_nifasget);
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
                params.put("updated_at", updated_at);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void toggleNifas() {
        if (!tv_editNifasTanggalKunjungan.isEnabled()) {
            tv_editNifasTanggalKunjungan.setEnabled(true);
            tv_editNifasTanggalKunjungan.setFocusable(true);
            tv_editNifasTanggalKunjungan.setCursorVisible(true);
            tv_editNifasTanggalKunjungan.setFocusableInTouchMode(true);
            tv_editNifasTanggalKunjungan.setTextColor(Color.parseColor("#000000"));
            tv_editNifasKunjunganKe.setEnabled(true);
            tv_editNifasKunjunganKe.setFocusable(true);
            tv_editNifasKunjunganKe.setCursorVisible(true);
            tv_editNifasKunjunganKe.setFocusableInTouchMode(true);
            tv_editNifasKunjunganKe.setTextColor(Color.parseColor("#000000"));
            tv_editNifasKeluhan.setEnabled(true);
            tv_editNifasKeluhan.setFocusable(true);
            tv_editNifasKeluhan.setCursorVisible(true);
            tv_editNifasKeluhan.setFocusableInTouchMode(true);
            tv_editNifasKeluhan.setTextColor(Color.parseColor("#000000"));
            tv_editNifasKondisiPerineum.setEnabled(true);
            tv_editNifasKondisiPerineum.setFocusable(true);
            tv_editNifasKondisiPerineum.setCursorVisible(true);
            tv_editNifasKondisiPerineum.setFocusableInTouchMode(true);
            tv_editNifasKondisiPerineum.setTextColor(Color.parseColor("#000000"));
            tv_editNifasTFU.setEnabled(true);
            tv_editNifasTFU.setFocusable(true);
            tv_editNifasTFU.setCursorVisible(true);
            tv_editNifasTFU.setFocusableInTouchMode(true);
            tv_editNifasTFU.setTextColor(Color.parseColor("#000000"));
            tv_editNifasJalanLahir.setEnabled(true);
            tv_editNifasJalanLahir.setFocusable(true);
            tv_editNifasJalanLahir.setCursorVisible(true);
            tv_editNifasJalanLahir.setFocusableInTouchMode(true);
            tv_editNifasJalanLahir.setTextColor(Color.parseColor("#000000"));
            tv_editNifasPayudara.setEnabled(true);
            tv_editNifasPayudara.setFocusable(true);
            tv_editNifasPayudara.setCursorVisible(true);
            tv_editNifasPayudara.setFocusableInTouchMode(true);
            tv_editNifasPayudara.setTextColor(Color.parseColor("#000000"));
            tv_editNifasHasilPostpartumKe3.setEnabled(true);
            tv_editNifasHasilPostpartumKe3.setFocusable(true);
            tv_editNifasHasilPostpartumKe3.setCursorVisible(true);
            tv_editNifasHasilPostpartumKe3.setFocusableInTouchMode(true);
            tv_editNifasHasilPostpartumKe3.setTextColor(Color.parseColor("#000000"));
            tv_editNifasJenisKontrasepsi.setEnabled(true);
            tv_editNifasJenisKontrasepsi.setFocusable(true);
            tv_editNifasJenisKontrasepsi.setCursorVisible(true);
            tv_editNifasJenisKontrasepsi.setFocusableInTouchMode(true);
            tv_editNifasJenisKontrasepsi.setTextColor(Color.parseColor("#000000"));
            tv_editNifasKomplikasi.setEnabled(true);
            tv_editNifasKomplikasi.setFocusable(true);
            tv_editNifasKomplikasi.setCursorVisible(true);
            tv_editNifasKomplikasi.setFocusableInTouchMode(true);
            tv_editNifasKomplikasi.setTextColor(Color.parseColor("#000000"));
            tv_editNifasKomplikasiNifas.setEnabled(true);
            tv_editNifasKomplikasiNifas.setFocusable(true);
            tv_editNifasKomplikasiNifas.setCursorVisible(true);
            tv_editNifasKomplikasiNifas.setFocusableInTouchMode(true);
            tv_editNifasKomplikasiNifas.setTextColor(Color.parseColor("#000000"));
            tv_editNifasAsuhan.setEnabled(true);
            tv_editNifasAsuhan.setFocusable(true);
            tv_editNifasAsuhan.setCursorVisible(true);
            tv_editNifasAsuhan.setFocusableInTouchMode(true);
            tv_editNifasAsuhan.setTextColor(Color.parseColor("#000000"));
            tv_editNifasKonseling.setEnabled(true);
            tv_editNifasKonseling.setFocusable(true);
            tv_editNifasKonseling.setCursorVisible(true);
            tv_editNifasKonseling.setFocusableInTouchMode(true);
            tv_editNifasKonseling.setTextColor(Color.parseColor("#000000"));
            tv_editNifasTanggalRujukan.setEnabled(true);
            tv_editNifasTanggalRujukan.setFocusable(true);
            tv_editNifasTanggalRujukan.setCursorVisible(true);
            tv_editNifasTanggalRujukan.setFocusableInTouchMode(true);
            tv_editNifasTanggalRujukan.setTextColor(Color.parseColor("#000000"));
            tv_editNifasNamaFaskes.setEnabled(true);
            tv_editNifasNamaFaskes.setFocusable(true);
            tv_editNifasNamaFaskes.setCursorVisible(true);
            tv_editNifasNamaFaskes.setFocusableInTouchMode(true);
            tv_editNifasNamaFaskes.setTextColor(Color.parseColor("#000000"));
            tv_editNifasAlasanDirujuk.setEnabled(true);
            tv_editNifasAlasanDirujuk.setFocusable(true);
            tv_editNifasAlasanDirujuk.setCursorVisible(true);
            tv_editNifasAlasanDirujuk.setFocusableInTouchMode(true);
            tv_editNifasAlasanDirujuk.setTextColor(Color.parseColor("#000000"));
            tv_editNifasDiagnosaSementara.setEnabled(true);
            tv_editNifasDiagnosaSementara.setFocusable(true);
            tv_editNifasDiagnosaSementara.setCursorVisible(true);
            tv_editNifasDiagnosaSementara.setFocusableInTouchMode(true);
            tv_editNifasDiagnosaSementara.setTextColor(Color.parseColor("#000000"));
            tv_editNifasTindakanSementara.setEnabled(true);
            tv_editNifasTindakanSementara.setFocusable(true);
            tv_editNifasTindakanSementara.setCursorVisible(true);
            tv_editNifasTindakanSementara.setFocusableInTouchMode(true);
            tv_editNifasTindakanSementara.setTextColor(Color.parseColor("#000000"));
            sp_editNifasKeadaanUmum.setEnabled(true);
            sp_editNifasKesadaran.setEnabled(true);
            sp_editNifasKesadaran.setEnabled(true);
            sp_editNifasTandaVital.setEnabled(true);
            sp_editNifasPendarahan.setEnabled(true);
            sp_editNifasTandaInfeksi.setEnabled(true);
            sp_editNifasKondisiUterus.setEnabled(true);
            sp_editNifasLOCHEA.setEnabled(true);
            sp_editNifasProduksiASI.setEnabled(true);
            sp_editNifasKapsulVita.setEnabled(true);
            sp_editNifasTabletFE.setEnabled(true);
            sp_editNifasPostpartumKe3.setEnabled(true);
            sp_editNifasKontrasepsi.setEnabled(true);
            sp_editNifasBAB.setEnabled(true);
            sp_editNifasBAK.setEnabled(true);
            sp_editNifasFaskes.setEnabled(true);
            Toast.makeText(EditDataNifasActivity.this, "Mode Edit Diaktifkan!", Toast.LENGTH_LONG).show();
        } else if (tv_editNifasTanggalKunjungan.isEnabled()) {
            tv_editNifasTanggalKunjungan.setEnabled(false);
            tv_editNifasTanggalKunjungan.setFocusable(false);
            tv_editNifasTanggalKunjungan.setCursorVisible(false);
            tv_editNifasTanggalKunjungan.setTextColor(Color.parseColor("#919191"));
            tv_editNifasKunjunganKe.setEnabled(false);
            tv_editNifasKunjunganKe.setFocusable(false);
            tv_editNifasKunjunganKe.setCursorVisible(false);
            tv_editNifasKunjunganKe.setTextColor(Color.parseColor("#919191"));
            tv_editNifasKeluhan.setEnabled(false);
            tv_editNifasKeluhan.setFocusable(false);
            tv_editNifasKeluhan.setCursorVisible(false);
            tv_editNifasKeluhan.setTextColor(Color.parseColor("#919191"));
            tv_editNifasKondisiPerineum.setEnabled(false);
            tv_editNifasKondisiPerineum.setFocusable(false);
            tv_editNifasKondisiPerineum.setCursorVisible(false);
            tv_editNifasKondisiPerineum.setTextColor(Color.parseColor("#919191"));
            tv_editNifasTFU.setEnabled(false);
            tv_editNifasTFU.setFocusable(false);
            tv_editNifasTFU.setCursorVisible(false);
            tv_editNifasTFU.setTextColor(Color.parseColor("#919191"));
            tv_editNifasJalanLahir.setEnabled(false);
            tv_editNifasJalanLahir.setFocusable(false);
            tv_editNifasJalanLahir.setCursorVisible(false);
            tv_editNifasJalanLahir.setTextColor(Color.parseColor("#919191"));
            tv_editNifasPayudara.setEnabled(false);
            tv_editNifasPayudara.setFocusable(false);
            tv_editNifasPayudara.setCursorVisible(false);
            tv_editNifasPayudara.setTextColor(Color.parseColor("#919191"));
            tv_editNifasHasilPostpartumKe3.setEnabled(false);
            tv_editNifasHasilPostpartumKe3.setFocusable(false);
            tv_editNifasHasilPostpartumKe3.setCursorVisible(false);
            tv_editNifasHasilPostpartumKe3.setTextColor(Color.parseColor("#919191"));
            tv_editNifasJenisKontrasepsi.setEnabled(false);
            tv_editNifasJenisKontrasepsi.setFocusable(false);
            tv_editNifasJenisKontrasepsi.setCursorVisible(false);
            tv_editNifasJenisKontrasepsi.setTextColor(Color.parseColor("#919191"));
            tv_editNifasKomplikasi.setEnabled(false);
            tv_editNifasKomplikasi.setFocusable(false);
            tv_editNifasKomplikasi.setCursorVisible(false);
            tv_editNifasKomplikasi.setTextColor(Color.parseColor("#919191"));
            tv_editNifasKomplikasiNifas.setEnabled(false);
            tv_editNifasKomplikasiNifas.setFocusable(false);
            tv_editNifasKomplikasiNifas.setCursorVisible(false);
            tv_editNifasKomplikasiNifas.setTextColor(Color.parseColor("#919191"));
            tv_editNifasAsuhan.setEnabled(false);
            tv_editNifasAsuhan.setFocusable(false);
            tv_editNifasAsuhan.setCursorVisible(false);
            tv_editNifasAsuhan.setTextColor(Color.parseColor("#919191"));
            tv_editNifasKonseling.setEnabled(false);
            tv_editNifasKonseling.setFocusable(false);
            tv_editNifasKonseling.setCursorVisible(false);
            tv_editNifasKonseling.setTextColor(Color.parseColor("#919191"));
            tv_editNifasTanggalRujukan.setEnabled(false);
            tv_editNifasTanggalRujukan.setFocusable(false);
            tv_editNifasTanggalRujukan.setCursorVisible(false);
            tv_editNifasTanggalRujukan.setTextColor(Color.parseColor("#919191"));
            tv_editNifasNamaFaskes.setEnabled(false);
            tv_editNifasNamaFaskes.setFocusable(false);
            tv_editNifasNamaFaskes.setCursorVisible(false);
            tv_editNifasNamaFaskes.setTextColor(Color.parseColor("#919191"));
            tv_editNifasAlasanDirujuk.setEnabled(false);
            tv_editNifasAlasanDirujuk.setFocusable(false);
            tv_editNifasAlasanDirujuk.setCursorVisible(false);
            tv_editNifasAlasanDirujuk.setTextColor(Color.parseColor("#919191"));
            tv_editNifasDiagnosaSementara.setEnabled(false);
            tv_editNifasDiagnosaSementara.setFocusable(false);
            tv_editNifasDiagnosaSementara.setCursorVisible(false);
            tv_editNifasDiagnosaSementara.setTextColor(Color.parseColor("#919191"));
            tv_editNifasTindakanSementara.setEnabled(false);
            tv_editNifasTindakanSementara.setFocusable(false);
            tv_editNifasTindakanSementara.setCursorVisible(false);
            tv_editNifasTindakanSementara.setTextColor(Color.parseColor("#919191"));
            sp_editNifasKeadaanUmum.setEnabled(false);
            sp_editNifasKesadaran.setEnabled(false);
            sp_editNifasTandaVital.setEnabled(false);
            sp_editNifasPendarahan.setEnabled(false);
            sp_editNifasTandaInfeksi.setEnabled(false);
            sp_editNifasKondisiUterus.setEnabled(false);
            sp_editNifasLOCHEA.setEnabled(false);
            tv_editNifasPayudara.setEnabled(false);
            sp_editNifasProduksiASI.setEnabled(false);
            sp_editNifasKapsulVita.setEnabled(false);
            sp_editNifasTabletFE.setEnabled(false);
            sp_editNifasPostpartumKe3.setEnabled(false);
            sp_editNifasKontrasepsi.setEnabled(false);
            sp_editNifasBAB.setEnabled(false);
            sp_editNifasBAK.setEnabled(false);
            sp_editNifasFaskes.setEnabled(false);
            Toast.makeText(EditDataNifasActivity.this, "Mode Edit Dinonaktifkan!", Toast.LENGTH_SHORT).show();
        }
    }

    private void loadDataNifas() {
        String URL_READBOOKING = "http://simetalia.com/pkm/webservice/nifas/" + id_nifasget;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_READBOOKING,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONObject object = jsonObject.getJSONObject("read");

                            if (success.equals("1")) {

                                String tanggal_kunjungan = object.getString("tanggal_kunjungan").trim();
                                String kunjungan_ke = object.getString("kunjungan_ke").trim();
                                String keluhan = object.getString("keluhan").trim();
                                String keadaan_umum = object.getString("keadaan_umum").trim();
                                String kesadaran = object.getString("kesadaran").trim();
                                String tanda_vital = object.getString("tanda_vital").trim();
                                String pendarahan = object.getString("pendarahan").trim();
                                String kondisi_perineum = object.getString("kondisi_perineum").trim();
                                String tanda_infeksi = object.getString("tanda_infeksi").trim();
                                String kondisi_uterus = object.getString("kondisi_uterus").trim();
                                String tfu = object.getString("tfu").trim();
                                String lochea = object.getString("lochea").trim();
                                String jalan_lahir = object.getString("jalan_lahir").trim();
                                String payudara = object.getString("payudara").trim();
                                String produksi_asi = object.getString("produksi_asi").trim();
                                String kapsul_vita = object.getString("kapsul_vita").trim();
                                String tablet_fe = object.getString("tablet_fe").trim();
                                String postpartum_ke3 = object.getString("postpartum_ke3").trim();
                                String hasil_postpartum_ke3 = object.getString("hasil_postpartum_ke3").trim();
                                String kontrasepsi = object.getString("kontrasepsi").trim();
                                String jenis_kontrasepsi = object.getString("jenis_kontrasepsi").trim();
                                String komplikasi = object.getString("komplikasi").trim();
                                String bab = object.getString("bab").trim();
                                String bak = object.getString("bak").trim();
                                String komplikasi_nifas = object.getString("komplikasi_nifas").trim();
                                String asuhan = object.getString("asuhan").trim();
                                String konseling = object.getString("konseling").trim();
                                String tanggal_rujukan = object.getString("tanggal_rujukan").trim();
                                String faskes = object.getString("faskes").trim();
                                String nama_faskes = object.getString("nama_faskes").trim();
                                String alasan_dirujuk = object.getString("alasan_dirujuk").trim();
                                String diagnosa_sementara = object.getString("diagnosa_sementara").trim();
                                String tindakan_sementara = object.getString("tindakan_sementara").trim();

                                tv_editNifasTanggalKunjungan.setText(tanggal_kunjungan);
                                tv_editNifasKunjunganKe.setText(kunjungan_ke);
                                tv_editNifasKeluhan.setText(keluhan);
                                tv_editNifasKondisiPerineum.setText(kondisi_perineum);
                                tv_editNifasTFU.setText(tfu);
                                tv_editNifasJalanLahir.setText(jalan_lahir);
                                tv_editNifasPayudara.setText(payudara);
                                tv_editNifasHasilPostpartumKe3.setText(hasil_postpartum_ke3);
                                tv_editNifasJenisKontrasepsi.setText(jenis_kontrasepsi);
                                tv_editNifasKomplikasi.setText(komplikasi);
                                tv_editNifasKomplikasiNifas.setText(komplikasi_nifas);
                                tv_editNifasAsuhan.setText(asuhan);
                                tv_editNifasKonseling.setText(konseling);
                                tv_editNifasTanggalRujukan.setText(tanggal_rujukan);
                                tv_editNifasNamaFaskes.setText(nama_faskes);
                                tv_editNifasAlasanDirujuk.setText(alasan_dirujuk);
                                tv_editNifasDiagnosaSementara.setText(diagnosa_sementara);
                                tv_editNifasTindakanSementara.setText(tindakan_sementara);

                                ArrayAdapter<String> keadaanUmumAdapter = new ArrayAdapter<>(EditDataNifasActivity.this,
                                        android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.keadaan_umum));
                                keadaanUmumAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                sp_editNifasKeadaanUmum.setAdapter(keadaanUmumAdapter);
                                if (keadaan_umum != null) {
                                    int spinnerPositionKeadaanUmum = keadaanUmumAdapter.getPosition(keadaan_umum);
                                    sp_editNifasKeadaanUmum.setSelection(spinnerPositionKeadaanUmum);
                                }

                                ArrayAdapter<String> kesadaranAdapter = new ArrayAdapter<>(EditDataNifasActivity.this,
                                        android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.kesadaran));
                                kesadaranAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                sp_editNifasKesadaran.setAdapter(kesadaranAdapter);
                                if (kesadaran != null) {
                                    int spinnerPositionKesadaran = kesadaranAdapter.getPosition(kesadaran);
                                    sp_editNifasKesadaran.setSelection(spinnerPositionKesadaran);
                                }

                                ArrayAdapter<String> tandaVitalAdapter = new ArrayAdapter<>(EditDataNifasActivity.this,
                                        android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.tanda_vital));
                                tandaVitalAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                sp_editNifasTandaVital.setAdapter(tandaVitalAdapter);
                                if (tanda_vital != null) {
                                    int spinnerPositionTandaVital = tandaVitalAdapter.getPosition(tanda_vital);
                                    sp_editNifasTandaVital.setSelection(spinnerPositionTandaVital);
                                }

                                ArrayAdapter<String> pendarahanAdapter = new ArrayAdapter<>(EditDataNifasActivity.this,
                                        android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.normal_tdk));
                                pendarahanAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                sp_editNifasPendarahan.setAdapter(pendarahanAdapter);
                                if (pendarahan != null) {
                                    int spinnerPositionPendarahan = pendarahanAdapter.getPosition(pendarahan);
                                    sp_editNifasPendarahan.setSelection(spinnerPositionPendarahan);
                                }

                                ArrayAdapter<String> tandaInfeksiAdapter = new ArrayAdapter<>(EditDataNifasActivity.this,
                                        android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.ada_tdk));
                                tandaInfeksiAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                sp_editNifasTandaInfeksi.setAdapter(tandaInfeksiAdapter);
                                if (tanda_infeksi != null) {
                                    int spinnerPositionTandaInfeksi = tandaInfeksiAdapter.getPosition(tanda_infeksi);
                                    sp_editNifasTandaInfeksi.setSelection(spinnerPositionTandaInfeksi);
                                }

                                ArrayAdapter<String> kondisiUterusAdapter = new ArrayAdapter<>(EditDataNifasActivity.this,
                                        android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.keras_lnk));
                                kondisiUterusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                sp_editNifasKondisiUterus.setAdapter(kondisiUterusAdapter);
                                if (kondisi_uterus != null) {
                                    int spinnerPositionKondisiUterus = kondisiUterusAdapter.getPosition(kondisi_uterus);
                                    sp_editNifasKondisiUterus.setSelection(spinnerPositionKondisiUterus);
                                }

                                ArrayAdapter<String> locheaAdapter = new ArrayAdapter<>(EditDataNifasActivity.this,
                                        android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.lochea));
                                locheaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                sp_editNifasLOCHEA.setAdapter(locheaAdapter);
                                if (lochea != null) {
                                    int spinnerPositionLochea = locheaAdapter.getPosition(lochea);
                                    sp_editNifasLOCHEA.setSelection(spinnerPositionLochea);
                                }

                                ArrayAdapter<String> produksiAsiAdapter = new ArrayAdapter<>(EditDataNifasActivity.this,
                                        android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.cukup_tdk));
                                produksiAsiAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                sp_editNifasProduksiASI.setAdapter(produksiAsiAdapter);
                                if (produksi_asi != null) {
                                    int spinnerPositionProduksiAsi = produksiAsiAdapter.getPosition(produksi_asi);
                                    sp_editNifasProduksiASI.setSelection(spinnerPositionProduksiAsi);
                                }

                                ArrayAdapter<String> kapsulVitaAdapter = new ArrayAdapter<>(EditDataNifasActivity.this,
                                        android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.kapsul_vita));
                                kapsulVitaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                sp_editNifasKapsulVita.setAdapter(kapsulVitaAdapter);
                                if (kapsul_vita != null) {
                                    int spinnerPositionKapsulVita = kapsulVitaAdapter.getPosition(kapsul_vita);
                                    sp_editNifasKapsulVita.setSelection(spinnerPositionKapsulVita);
                                }

                                ArrayAdapter<String> tabletFEAdapter = new ArrayAdapter<>(EditDataNifasActivity.this,
                                        android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.tablet_fe));
                                tabletFEAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                sp_editNifasTabletFE.setAdapter(tabletFEAdapter);
                                if (tablet_fe != null) {
                                    int spinnerPositionTabletFE = tabletFEAdapter.getPosition(tablet_fe);
                                    sp_editNifasTabletFE.setSelection(spinnerPositionTabletFE);
                                }

                                ArrayAdapter<String> postpartumKe3Adapter = new ArrayAdapter<>(EditDataNifasActivity.this,
                                        android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.ya_tidak));
                                postpartumKe3Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                sp_editNifasPostpartumKe3.setAdapter(postpartumKe3Adapter);
                                if (postpartum_ke3 != null) {
                                    int spinnerPositionPostpartum = postpartumKe3Adapter.getPosition(postpartum_ke3);
                                    sp_editNifasPostpartumKe3.setSelection(spinnerPositionPostpartum);
                                }

                                ArrayAdapter<String> kontrasepsiAdapter = new ArrayAdapter<>(EditDataNifasActivity.this,
                                        android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.ya_tidak));
                                kontrasepsiAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                sp_editNifasKontrasepsi.setAdapter(kontrasepsiAdapter);
                                if (kontrasepsi != null) {
                                    int spinnerPositionKontrasepsi = kontrasepsiAdapter.getPosition(kontrasepsi);
                                    sp_editNifasKontrasepsi.setSelection(spinnerPositionKontrasepsi);
                                }

                                ArrayAdapter<String> BABAdapter = new ArrayAdapter<>(EditDataNifasActivity.this,
                                        android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.ya_tidak));
                                BABAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                sp_editNifasBAB.setAdapter(BABAdapter);
                                if (bab != null) {
                                    int spinnerPositionBAB = BABAdapter.getPosition(bab);
                                    sp_editNifasBAB.setSelection(spinnerPositionBAB);
                                }

                                ArrayAdapter<String> BAKAdapter = new ArrayAdapter<>(EditDataNifasActivity.this,
                                        android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.ya_tidak));
                                BAKAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                sp_editNifasBAK.setAdapter(BAKAdapter);
                                if (bak != null) {
                                    int spinnerPositionBAK = BAKAdapter.getPosition(bak);
                                    sp_editNifasBAK.setSelection(spinnerPositionBAK);
                                }

                                ArrayAdapter<String> faskesAdapter = new ArrayAdapter<>(EditDataNifasActivity.this,
                                        android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.faskes));
                                faskesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                sp_editNifasFaskes.setAdapter(faskesAdapter);
                                if (faskes != null) {
                                    int spinnerPositionFaskes = faskesAdapter.getPosition(faskes);
                                    sp_editNifasFaskes.setSelection(spinnerPositionFaskes);
                                }

                                loadingDialog.dissmissDialog();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(EditDataNifasActivity.this, "Data Nifas Tidak Ada!"+ e.toString(), Toast.LENGTH_SHORT).show();
                            loadingDialog.dissmissDialog();
                        }

                    }
                }
                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(EditDataNifasActivity.this, "Koneksi Gagal!" + error.toString(), Toast.LENGTH_SHORT).show();
                loadingDialog.dissmissDialog();
            }
        }) {
        };
        RequestQueue requestQueue = Volley.newRequestQueue(EditDataNifasActivity.this);
        requestQueue.add(stringRequest);
    }

    private void spinnerNifas() {
        ArrayAdapter<String> keadaanUmumAdapter = new ArrayAdapter<>(EditDataNifasActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.keadaan_umum));
        keadaanUmumAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_editNifasKeadaanUmum.setAdapter(keadaanUmumAdapter);

        ArrayAdapter<String> kesadaranAdapter = new ArrayAdapter<>(EditDataNifasActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.kesadaran));
        kesadaranAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_editNifasKesadaran.setAdapter(kesadaranAdapter);

        ArrayAdapter<String> tandaVitalAdapter = new ArrayAdapter<>(EditDataNifasActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.tanda_vital));
        tandaVitalAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_editNifasTandaVital.setAdapter(tandaVitalAdapter);

        ArrayAdapter<String> pendarahanAdapter = new ArrayAdapter<>(EditDataNifasActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.normal_tdk));
        pendarahanAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_editNifasPendarahan.setAdapter(pendarahanAdapter);

        ArrayAdapter<String> tandaInfeksiAdapter = new ArrayAdapter<>(EditDataNifasActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.ada_tdk));
        tandaInfeksiAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_editNifasTandaInfeksi.setAdapter(tandaInfeksiAdapter);

        ArrayAdapter<String> kondisiUterusAdapter = new ArrayAdapter<>(EditDataNifasActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.keras_lnk));
        kondisiUterusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_editNifasKondisiUterus.setAdapter(kondisiUterusAdapter);

        ArrayAdapter<String> locheaAdapter = new ArrayAdapter<>(EditDataNifasActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.lochea));
        locheaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_editNifasLOCHEA.setAdapter(locheaAdapter);

        ArrayAdapter<String> produksiAsiAdapter = new ArrayAdapter<>(EditDataNifasActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.cukup_tdk));
        produksiAsiAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_editNifasProduksiASI.setAdapter(produksiAsiAdapter);

        ArrayAdapter<String> kapsulVitaAdapter = new ArrayAdapter<>(EditDataNifasActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.kapsul_vita));
        kapsulVitaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_editNifasKapsulVita.setAdapter(kapsulVitaAdapter);

        ArrayAdapter<String> tabletFEAdapter = new ArrayAdapter<>(EditDataNifasActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.tablet_fe));
        tabletFEAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_editNifasTabletFE.setAdapter(tabletFEAdapter);

        ArrayAdapter<String> postpartumKe3Adapter = new ArrayAdapter<>(EditDataNifasActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.ya_tidak));
        postpartumKe3Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_editNifasPostpartumKe3.setAdapter(postpartumKe3Adapter);

        ArrayAdapter<String> kontrasepsiAdapter = new ArrayAdapter<>(EditDataNifasActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.ya_tidak));
        kontrasepsiAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_editNifasKontrasepsi.setAdapter(kontrasepsiAdapter);

        ArrayAdapter<String> BABAdapter = new ArrayAdapter<>(EditDataNifasActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.ya_tidak));
        BABAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_editNifasBAB.setAdapter(BABAdapter);

        ArrayAdapter<String> BAKAdapter = new ArrayAdapter<>(EditDataNifasActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.ya_tidak));
        BAKAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_editNifasBAK.setAdapter(BAKAdapter);

        ArrayAdapter<String> faskesAdapter = new ArrayAdapter<>(EditDataNifasActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.faskes));
        faskesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_editNifasFaskes.setAdapter(faskesAdapter);
    }
}
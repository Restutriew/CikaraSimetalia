package com.cikarastudio.cikarasimetalia.activity.skrinning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
import com.cikarastudio.cikarasimetalia.activity.kunjunganlama.DetailKunjunganLamaActivity;
import com.cikarastudio.cikarasimetalia.activity.kunjunganlama.EditKunjunganLamaDataP4KActivity;
import com.cikarastudio.cikarasimetalia.activity.kunjunganlama.EditKunjunganLamaDataSubyektifActivity;
import com.cikarastudio.cikarasimetalia.dialog.LoadingDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class TambahDataScreeningActivity extends AppCompatActivity {

    public static final String ID_KUNJUNGANBARU = "extra_data";
    LoadingDialog loadingDialog;
    String id_kunjunganBaru;
    Spinner sp_editSkrinningHamilMuda, sp_editSkrinningHamilTua, sp_editSkrinningHamilLambat,
            sp_editSkrinningHamilLama, sp_editSkrinningHamilCepat, sp_editSkrinningBanyakAnak,
            sp_editSkrinningUmurTua, sp_editSkrinningPendek, sp_editSkrinningHamilGagal,
            sp_editSkrinningLahirVakum, sp_editSkrinningUriDirogoh, sp_editSkrinningLahirInfus,
            sp_editSkrinningOperasiSesar, sp_editSkrinningPenyakitAB, sp_editSkrinningPenyakitCD,
            sp_editSkrinningDiabetes, sp_editSkrinningPenyakitMenular, sp_editSkrinningBengkakDarahTinggi,
            sp_editSkrinningHamilKembar, sp_editSkrinningHydramnion, sp_editSkrinningBayiMati,
            sp_editSkrinningLebihBulan, sp_editSkrinningLetakSungsang, sp_editSkrinningLetakLintang,
            sp_editSkrinningPendarahan, sp_editSkrinningKejang;
    ImageView btn_toggleEditSkrinning;
    LinearLayout btn_updateDataSkrinning;
    private static final String URL_UPDATESKRINNING = "http://simetalia.com/android/tambah_update_skrinning.php";

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_data_screening);

        loadingDialog = new LoadingDialog(TambahDataScreeningActivity.this);
        loadingDialog.startLoading();

        Intent intent = getIntent();
        id_kunjunganBaru = intent.getStringExtra(ID_KUNJUNGANBARU);

        sp_editSkrinningHamilMuda = findViewById(R.id.sp_editSkrinningHamilMuda);
        sp_editSkrinningHamilTua = findViewById(R.id.sp_editSkrinningHamilTua);
        sp_editSkrinningHamilLambat = findViewById(R.id.sp_editSkrinningHamilLambat);
        sp_editSkrinningHamilLama = findViewById(R.id.sp_editSkrinningHamilLama);
        sp_editSkrinningHamilCepat = findViewById(R.id.sp_editSkrinningHamilCepat);
        sp_editSkrinningBanyakAnak = findViewById(R.id.sp_editSkrinningBanyakAnak);
        sp_editSkrinningUmurTua = findViewById(R.id.sp_editSkrinningUmurTua);
        sp_editSkrinningPendek = findViewById(R.id.sp_editSkrinningPendek);
        sp_editSkrinningHamilGagal = findViewById(R.id.sp_editSkrinningHamilGagal);
        sp_editSkrinningLahirVakum = findViewById(R.id.sp_editSkrinningLahirVakum);
        sp_editSkrinningUriDirogoh = findViewById(R.id.sp_editSkrinningUriDirogoh);
        sp_editSkrinningLahirInfus = findViewById(R.id.sp_editSkrinningLahirInfus);
        sp_editSkrinningOperasiSesar = findViewById(R.id.sp_editSkrinningOperasiSesar);
        sp_editSkrinningPenyakitAB = findViewById(R.id.sp_editSkrinningPenyakitAB);
        sp_editSkrinningPenyakitCD = findViewById(R.id.sp_editSkrinningPenyakitCD);
        sp_editSkrinningDiabetes = findViewById(R.id.sp_editSkrinningDiabetes);
        sp_editSkrinningPenyakitMenular = findViewById(R.id.sp_editSkrinningPenyakitMenular);
        sp_editSkrinningBengkakDarahTinggi = findViewById(R.id.sp_editSkrinningBengkakDarahTinggi);
        sp_editSkrinningHamilKembar = findViewById(R.id.sp_editSkrinningHamilKembar);
        sp_editSkrinningHydramnion = findViewById(R.id.sp_editSkrinningHydramnion);
        sp_editSkrinningBayiMati = findViewById(R.id.sp_editSkrinningBayiMati);
        sp_editSkrinningLebihBulan = findViewById(R.id.sp_editSkrinningLebihBulan);
        sp_editSkrinningLetakSungsang = findViewById(R.id.sp_editSkrinningLetakSungsang);
        sp_editSkrinningLetakLintang = findViewById(R.id.sp_editSkrinningLetakLintang);
        sp_editSkrinningPendarahan = findViewById(R.id.sp_editSkrinningPendarahan);
        sp_editSkrinningKejang = findViewById(R.id.sp_editSkrinningKejang);
        btn_toggleEditSkrinning = findViewById(R.id.btn_toggleEditSkrinning);
        btn_updateDataSkrinning = findViewById(R.id.btn_updateDataSkrinning);


        sp_editSkrinningHamilMuda.setEnabled(false);
        sp_editSkrinningHamilTua.setEnabled(false);
        sp_editSkrinningHamilLambat.setEnabled(false);
        sp_editSkrinningHamilLama.setEnabled(false);
        sp_editSkrinningHamilCepat.setEnabled(false);
        sp_editSkrinningBanyakAnak.setEnabled(false);
        sp_editSkrinningUmurTua.setEnabled(false);
        sp_editSkrinningPendek.setEnabled(false);
        sp_editSkrinningHamilGagal.setEnabled(false);
        sp_editSkrinningLahirVakum.setEnabled(false);
        sp_editSkrinningUriDirogoh.setEnabled(false);
        sp_editSkrinningLahirInfus.setEnabled(false);
        sp_editSkrinningOperasiSesar.setEnabled(false);
        sp_editSkrinningPenyakitAB.setEnabled(false);
        sp_editSkrinningPenyakitCD.setEnabled(false);
        sp_editSkrinningDiabetes.setEnabled(false);
        sp_editSkrinningPenyakitMenular.setEnabled(false);
        sp_editSkrinningBengkakDarahTinggi.setEnabled(false);
        sp_editSkrinningHamilKembar.setEnabled(false);
        sp_editSkrinningHydramnion.setEnabled(false);
        sp_editSkrinningBayiMati.setEnabled(false);
        sp_editSkrinningLebihBulan.setEnabled(false);
        sp_editSkrinningLetakSungsang.setEnabled(false);
        sp_editSkrinningLetakLintang.setEnabled(false);
        sp_editSkrinningPendarahan.setEnabled(false);
        sp_editSkrinningKejang.setEnabled(false);


        spinnerSkrinning();
        loadDataSkrinning();

        btn_toggleEditSkrinning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleSkrinning();
            }
        });

        btn_updateDataSkrinning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateSkrinning();
            }
        });

    }

    private void updateSkrinning() {
        loadingDialog.startLoading();
        final String hamil_muda = sp_editSkrinningHamilMuda.getSelectedItem().toString();
        final String hamil_tua = sp_editSkrinningHamilTua.getSelectedItem().toString();
        final String hamil_lambat = sp_editSkrinningHamilLambat.getSelectedItem().toString();
        final String hamil_lama = sp_editSkrinningHamilLama.getSelectedItem().toString();
        final String hamil_cepat = sp_editSkrinningHamilCepat.getSelectedItem().toString();
        final String banyak_anak = sp_editSkrinningBanyakAnak.getSelectedItem().toString();
        final String umur_tua = sp_editSkrinningUmurTua.getSelectedItem().toString();
        final String pendek = sp_editSkrinningPendek.getSelectedItem().toString();
        final String hamil_gagal = sp_editSkrinningHamilGagal.getSelectedItem().toString();
        final String lahir_vakum = sp_editSkrinningLahirVakum.getSelectedItem().toString();
        final String uri_dirogoh = sp_editSkrinningUriDirogoh.getSelectedItem().toString();
        final String lahir_infus = sp_editSkrinningLahirInfus.getSelectedItem().toString();
        final String operasi_sesar = sp_editSkrinningOperasiSesar.getSelectedItem().toString();
        final String penyakit_ab = sp_editSkrinningPenyakitAB.getSelectedItem().toString();
        final String penyakit_cd = sp_editSkrinningPenyakitCD.getSelectedItem().toString();
        final String diabetes = sp_editSkrinningDiabetes.getSelectedItem().toString();
        final String penyakit_menular = sp_editSkrinningPenyakitMenular.getSelectedItem().toString();
        final String bengkak_darahtinggi = sp_editSkrinningBengkakDarahTinggi.getSelectedItem().toString();
        final String hamil_kembar = sp_editSkrinningHamilKembar.getSelectedItem().toString();
        final String hydramnion = sp_editSkrinningHydramnion.getSelectedItem().toString();
        final String bayi_mati = sp_editSkrinningBayiMati.getSelectedItem().toString();
        final String lebih_bulan = sp_editSkrinningLebihBulan.getSelectedItem().toString();
        final String letak_sungsang = sp_editSkrinningLetakSungsang.getSelectedItem().toString();
        final String letak_lintang = sp_editSkrinningLetakLintang.getSelectedItem().toString();
        final String pendarahan = sp_editSkrinningPendarahan.getSelectedItem().toString();
        final String kejang = sp_editSkrinningKejang.getSelectedItem().toString();

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        final String updated_at = dateFormat.format(cal.getTime());

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_UPDATESKRINNING,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")) {
                                Toast.makeText(TambahDataScreeningActivity.this, "Update Data Skrinning Berhasil", Toast.LENGTH_LONG).show();
                                loadingDialog.dissmissDialog();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(TambahDataScreeningActivity.this, "Update Data Skrinning Gagal!", Toast.LENGTH_LONG).show();
                            loadingDialog.dissmissDialog();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(TambahDataScreeningActivity.this, "Update Data Skrinning Gagal! : Cek Koneksi Anda", Toast.LENGTH_LONG).show();
                        loadingDialog.dissmissDialog();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                params.put("pkmkehamilan_id", id_kunjunganBaru);
                params.put("hamil_muda", hamil_muda);
                params.put("hamil_tua", hamil_tua);
                params.put("hamil_lambat", hamil_lambat);
                params.put("hamil_lama", hamil_lama);
                params.put("hamil_cepat", hamil_cepat);
                params.put("banyak_anak", banyak_anak);
                params.put("umur_tua", umur_tua);
                params.put("pendek", pendek);
                params.put("hamil_gagal", hamil_gagal);
                params.put("lahir_vakum", lahir_vakum);
                params.put("uri_dirogoh", uri_dirogoh);
                params.put("lahir_infus", lahir_infus);
                params.put("operasi_sesar", operasi_sesar);
                params.put("penyakit_ab", penyakit_ab);
                params.put("penyakit_cd", penyakit_cd);
                params.put("diabetes", diabetes);
                params.put("penyakit_menular", penyakit_menular);
                params.put("bengkak_darahtinggi", bengkak_darahtinggi);
                params.put("hamil_kembar", hamil_kembar);
                params.put("hydramnion", hydramnion);
                params.put("bayi_mati", bayi_mati);
                params.put("lebih_bulan", lebih_bulan);
                params.put("letak_sungsang", letak_sungsang);
                params.put("letak_lintang", letak_lintang);
                params.put("pendarahan", pendarahan);
                params.put("kejang", kejang);
                params.put("updated_at", updated_at);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void toggleSkrinning() {
        if (!sp_editSkrinningHamilMuda.isEnabled()) {
            sp_editSkrinningHamilMuda.setEnabled(true);
            sp_editSkrinningHamilTua.setEnabled(true);
            sp_editSkrinningHamilLambat.setEnabled(true);
            sp_editSkrinningHamilLama.setEnabled(true);
            sp_editSkrinningHamilCepat.setEnabled(true);
            sp_editSkrinningBanyakAnak.setEnabled(true);
            sp_editSkrinningUmurTua.setEnabled(true);
            sp_editSkrinningPendek.setEnabled(true);
            sp_editSkrinningHamilGagal.setEnabled(true);
            sp_editSkrinningLahirVakum.setEnabled(true);
            sp_editSkrinningUriDirogoh.setEnabled(true);
            sp_editSkrinningLahirInfus.setEnabled(true);
            sp_editSkrinningOperasiSesar.setEnabled(true);
            sp_editSkrinningPenyakitAB.setEnabled(true);
            sp_editSkrinningPenyakitCD.setEnabled(true);
            sp_editSkrinningDiabetes.setEnabled(true);
            sp_editSkrinningPenyakitMenular.setEnabled(true);
            sp_editSkrinningBengkakDarahTinggi.setEnabled(true);
            sp_editSkrinningHamilKembar.setEnabled(true);
            sp_editSkrinningHydramnion.setEnabled(true);
            sp_editSkrinningBayiMati.setEnabled(true);
            sp_editSkrinningLebihBulan.setEnabled(true);
            sp_editSkrinningLetakSungsang.setEnabled(true);
            sp_editSkrinningLetakLintang.setEnabled(true);
            sp_editSkrinningPendarahan.setEnabled(true);
            sp_editSkrinningKejang.setEnabled(true);
            Toast.makeText(TambahDataScreeningActivity.this, "Mode Edit Diaktifkan!", Toast.LENGTH_LONG).show();
        } else if (sp_editSkrinningHamilMuda.isEnabled()) {
            sp_editSkrinningHamilMuda.setEnabled(false);
            sp_editSkrinningHamilTua.setEnabled(false);
            sp_editSkrinningHamilLambat.setEnabled(false);
            sp_editSkrinningHamilLama.setEnabled(false);
            sp_editSkrinningHamilCepat.setEnabled(false);
            sp_editSkrinningBanyakAnak.setEnabled(false);
            sp_editSkrinningUmurTua.setEnabled(false);
            sp_editSkrinningPendek.setEnabled(false);
            sp_editSkrinningHamilGagal.setEnabled(false);
            sp_editSkrinningLahirVakum.setEnabled(false);
            sp_editSkrinningUriDirogoh.setEnabled(false);
            sp_editSkrinningLahirInfus.setEnabled(false);
            sp_editSkrinningOperasiSesar.setEnabled(false);
            sp_editSkrinningPenyakitAB.setEnabled(false);
            sp_editSkrinningPenyakitCD.setEnabled(false);
            sp_editSkrinningDiabetes.setEnabled(false);
            sp_editSkrinningPenyakitMenular.setEnabled(false);
            sp_editSkrinningBengkakDarahTinggi.setEnabled(false);
            sp_editSkrinningHamilKembar.setEnabled(false);
            sp_editSkrinningHydramnion.setEnabled(false);
            sp_editSkrinningBayiMati.setEnabled(false);
            sp_editSkrinningLebihBulan.setEnabled(false);
            sp_editSkrinningLetakSungsang.setEnabled(false);
            sp_editSkrinningLetakLintang.setEnabled(false);
            sp_editSkrinningPendarahan.setEnabled(false);
            sp_editSkrinningKejang.setEnabled(false);
            Toast.makeText(TambahDataScreeningActivity.this, "Mode Edit Dinonaktifkan!", Toast.LENGTH_SHORT).show();
        }
    }

    private void spinnerSkrinning() {
        ArrayAdapter<String> tidakYaAdapter = new ArrayAdapter<String>(TambahDataScreeningActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.tidak_ya));
        tidakYaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_editSkrinningHamilMuda.setAdapter(tidakYaAdapter);
        sp_editSkrinningHamilTua.setAdapter(tidakYaAdapter);
        sp_editSkrinningHamilLambat.setAdapter(tidakYaAdapter);
        sp_editSkrinningHamilLama.setAdapter(tidakYaAdapter);
        sp_editSkrinningHamilCepat.setAdapter(tidakYaAdapter);
        sp_editSkrinningHamilGagal.setAdapter(tidakYaAdapter);
        sp_editSkrinningLahirVakum.setAdapter(tidakYaAdapter);
        sp_editSkrinningUriDirogoh.setAdapter(tidakYaAdapter);
        sp_editSkrinningLahirInfus.setAdapter(tidakYaAdapter);
        sp_editSkrinningOperasiSesar.setAdapter(tidakYaAdapter);
        sp_editSkrinningPenyakitAB.setAdapter(tidakYaAdapter);
        sp_editSkrinningPenyakitCD.setAdapter(tidakYaAdapter);
        sp_editSkrinningDiabetes.setAdapter(tidakYaAdapter);
        sp_editSkrinningPenyakitMenular.setAdapter(tidakYaAdapter);
        sp_editSkrinningBengkakDarahTinggi.setAdapter(tidakYaAdapter);
        sp_editSkrinningHamilKembar.setAdapter(tidakYaAdapter);
        sp_editSkrinningHydramnion.setAdapter(tidakYaAdapter);
        sp_editSkrinningBayiMati.setAdapter(tidakYaAdapter);
        sp_editSkrinningLebihBulan.setAdapter(tidakYaAdapter);
        sp_editSkrinningLetakSungsang.setAdapter(tidakYaAdapter);
        sp_editSkrinningLetakLintang.setAdapter(tidakYaAdapter);
        sp_editSkrinningPendarahan.setAdapter(tidakYaAdapter);
        sp_editSkrinningKejang.setAdapter(tidakYaAdapter);

        ArrayAdapter<String> tidakAdapter = new ArrayAdapter<String>(TambahDataScreeningActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.tidak));
        tidakAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_editSkrinningBanyakAnak.setAdapter(tidakAdapter);
        sp_editSkrinningUmurTua.setAdapter(tidakAdapter);

        ArrayAdapter<String> yaAdapter = new ArrayAdapter<String>(TambahDataScreeningActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.ya));
        yaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_editSkrinningPendek.setAdapter(yaAdapter);

    }

    private void loadDataSkrinning() {
        String URL_READBOOKING = "http://simetalia.com/pkm/webservice/dataskrinning/" + id_kunjunganBaru;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_READBOOKING,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONObject object = jsonObject.getJSONObject("read");

                            if (success.equals("1")) {

                                String hamil_muda = object.getString("hamil_muda").trim();
                                String hamil_tua = object.getString("hamil_tua").trim();
                                String hamil_lambat = object.getString("hamil_lambat").trim();
                                String hamil_lama = object.getString("hamil_lama").trim();
                                String hamil_cepat = object.getString("hamil_cepat").trim();
                                String banyak_anak = object.getString("banyak_anak").trim();
                                String umur_tua = object.getString("umur_tua").trim();
                                String pendek = object.getString("pendek").trim();
                                String hamil_gagal = object.getString("hamil_gagal").trim();
                                String lahir_vakum = object.getString("lahir_vakum").trim();
                                String uri_dirogoh = object.getString("uri_dirogoh").trim();
                                String lahir_infus = object.getString("lahir_infus").trim();
                                String operasi_sesar = object.getString("operasi_sesar").trim();
                                String penyakit_ab = object.getString("penyakit_ab").trim();
                                String penyakit_cd = object.getString("penyakit_cd").trim();
                                String diabetes = object.getString("diabetes").trim();
                                String penyakit_menular = object.getString("penyakit_menular").trim();
                                String bengkak_darahtinggi = object.getString("bengkak_darahtinggi").trim();
                                String hamil_kembar = object.getString("hamil_kembar").trim();
                                String hydramnion = object.getString("hydramnion").trim();
                                String bayi_mati = object.getString("bayi_mati").trim();
                                String lebih_bulan = object.getString("lebih_bulan").trim();
                                String letak_sungsang = object.getString("letak_sungsang").trim();
                                String letak_lintang = object.getString("letak_lintang").trim();
                                String pendarahan = object.getString("pendarahan").trim();
                                String kejang = object.getString("kejang").trim();

                                ArrayAdapter<String> tidakYaAdapter = new ArrayAdapter<String>(TambahDataScreeningActivity.this,
                                        android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.tidak_ya));
                                tidakYaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                sp_editSkrinningHamilMuda.setAdapter(tidakYaAdapter);
                                if (hamil_muda != null) {
                                    int spinnerPositionHamilMudaa = tidakYaAdapter.getPosition(hamil_muda);
                                    sp_editSkrinningHamilMuda.setSelection(spinnerPositionHamilMudaa);
                                }

                                sp_editSkrinningHamilTua.setAdapter(tidakYaAdapter);
                                if (hamil_tua != null) {
                                    int spinnerPositionHamilTua = tidakYaAdapter.getPosition(hamil_tua);
                                    sp_editSkrinningHamilTua.setSelection(spinnerPositionHamilTua);
                                }

                                sp_editSkrinningHamilLambat.setAdapter(tidakYaAdapter);
                                if (hamil_lambat != null) {
                                    int spinnerPositionHamilMuda = tidakYaAdapter.getPosition(hamil_lambat);
                                    sp_editSkrinningHamilLambat.setSelection(spinnerPositionHamilMuda);
                                }

                                sp_editSkrinningHamilLama.setAdapter(tidakYaAdapter);
                                if (hamil_lama != null) {
                                    int spinnerPositionHamilMuda = tidakYaAdapter.getPosition(hamil_lama);
                                    sp_editSkrinningHamilLama.setSelection(spinnerPositionHamilMuda);
                                }

                                sp_editSkrinningHamilCepat.setAdapter(tidakYaAdapter);
                                if (hamil_cepat != null) {
                                    int spinnerPositionHamilMuda = tidakYaAdapter.getPosition(hamil_cepat);
                                    sp_editSkrinningHamilCepat.setSelection(spinnerPositionHamilMuda);
                                }

                                sp_editSkrinningHamilGagal.setAdapter(tidakYaAdapter);
                                if (hamil_gagal != null) {
                                    int spinnerPositionHamilMuda = tidakYaAdapter.getPosition(hamil_gagal);
                                    sp_editSkrinningHamilGagal.setSelection(spinnerPositionHamilMuda);
                                }

                                sp_editSkrinningLahirVakum.setAdapter(tidakYaAdapter);
                                if (lahir_vakum != null) {
                                    int spinnerPositionHamilMuda = tidakYaAdapter.getPosition(lahir_vakum);
                                    sp_editSkrinningLahirVakum.setSelection(spinnerPositionHamilMuda);
                                }

                                sp_editSkrinningUriDirogoh.setAdapter(tidakYaAdapter);
                                if (uri_dirogoh != null) {
                                    int spinnerPositionHamilMuda = tidakYaAdapter.getPosition(uri_dirogoh);
                                    sp_editSkrinningUriDirogoh.setSelection(spinnerPositionHamilMuda);
                                }

                                sp_editSkrinningLahirInfus.setAdapter(tidakYaAdapter);
                                if (lahir_infus != null) {
                                    int spinnerPositionHamilMuda = tidakYaAdapter.getPosition(lahir_infus);
                                    sp_editSkrinningLahirInfus.setSelection(spinnerPositionHamilMuda);
                                }

                                sp_editSkrinningOperasiSesar.setAdapter(tidakYaAdapter);
                                if (operasi_sesar != null) {
                                    int spinnerPositionHamilMuda = tidakYaAdapter.getPosition(operasi_sesar);
                                    sp_editSkrinningOperasiSesar.setSelection(spinnerPositionHamilMuda);
                                }

                                sp_editSkrinningPenyakitAB.setAdapter(tidakYaAdapter);
                                if (penyakit_ab != null) {
                                    int spinnerPositionHamilMuda = tidakYaAdapter.getPosition(penyakit_ab);
                                    sp_editSkrinningPenyakitAB.setSelection(spinnerPositionHamilMuda);
                                }

                                sp_editSkrinningPenyakitCD.setAdapter(tidakYaAdapter);
                                if (penyakit_cd != null) {
                                    int spinnerPositionHamilMuda = tidakYaAdapter.getPosition(penyakit_cd);
                                    sp_editSkrinningPenyakitCD.setSelection(spinnerPositionHamilMuda);
                                }

                                sp_editSkrinningDiabetes.setAdapter(tidakYaAdapter);
                                if (diabetes != null) {
                                    int spinnerPositionHamilMuda = tidakYaAdapter.getPosition(diabetes);
                                    sp_editSkrinningDiabetes.setSelection(spinnerPositionHamilMuda);
                                }

                                sp_editSkrinningPenyakitMenular.setAdapter(tidakYaAdapter);
                                if (penyakit_menular != null) {
                                    int spinnerPositionHamilMuda = tidakYaAdapter.getPosition(penyakit_menular);
                                    sp_editSkrinningPenyakitMenular.setSelection(spinnerPositionHamilMuda);
                                }

                                sp_editSkrinningBengkakDarahTinggi.setAdapter(tidakYaAdapter);
                                if (bengkak_darahtinggi != null) {
                                    int spinnerPositionHamilMuda = tidakYaAdapter.getPosition(bengkak_darahtinggi);
                                    sp_editSkrinningBengkakDarahTinggi.setSelection(spinnerPositionHamilMuda);
                                }

                                sp_editSkrinningHamilKembar.setAdapter(tidakYaAdapter);
                                if (hamil_kembar != null) {
                                    int spinnerPositionHamilMuda = tidakYaAdapter.getPosition(hamil_kembar);
                                    sp_editSkrinningHamilKembar.setSelection(spinnerPositionHamilMuda);
                                }

                                sp_editSkrinningHydramnion.setAdapter(tidakYaAdapter);
                                if (hydramnion != null) {
                                    int spinnerPositionHamilMuda = tidakYaAdapter.getPosition(hydramnion);
                                    sp_editSkrinningHydramnion.setSelection(spinnerPositionHamilMuda);
                                }

                                sp_editSkrinningBayiMati.setAdapter(tidakYaAdapter);
                                if (bayi_mati != null) {
                                    int spinnerPositionHamilMuda = tidakYaAdapter.getPosition(bayi_mati);
                                    sp_editSkrinningBayiMati.setSelection(spinnerPositionHamilMuda);
                                }

                                sp_editSkrinningLebihBulan.setAdapter(tidakYaAdapter);
                                if (lebih_bulan != null) {
                                    int spinnerPositionHamilMuda = tidakYaAdapter.getPosition(lebih_bulan);
                                    sp_editSkrinningLebihBulan.setSelection(spinnerPositionHamilMuda);
                                }

                                sp_editSkrinningLetakSungsang.setAdapter(tidakYaAdapter);
                                if (letak_sungsang != null) {
                                    int spinnerPositionHamilMuda = tidakYaAdapter.getPosition(letak_sungsang);
                                    sp_editSkrinningLetakSungsang.setSelection(spinnerPositionHamilMuda);
                                }

                                sp_editSkrinningLetakLintang.setAdapter(tidakYaAdapter);
                                if (letak_lintang != null) {
                                    int spinnerPositionHamilMuda = tidakYaAdapter.getPosition(letak_lintang);
                                    sp_editSkrinningLetakLintang.setSelection(spinnerPositionHamilMuda);
                                }

                                sp_editSkrinningPendarahan.setAdapter(tidakYaAdapter);
                                if (pendarahan != null) {
                                    int spinnerPositionHamilMuda = tidakYaAdapter.getPosition(pendarahan);
                                    sp_editSkrinningPendarahan.setSelection(spinnerPositionHamilMuda);
                                }

                                sp_editSkrinningKejang.setAdapter(tidakYaAdapter);
                                if (kejang != null) {
                                    int spinnerPositionHamilMuda = tidakYaAdapter.getPosition(kejang);
                                    sp_editSkrinningKejang.setSelection(spinnerPositionHamilMuda);
                                }

                                ArrayAdapter<String> tidakAdapter = new ArrayAdapter<String>(TambahDataScreeningActivity.this,
                                        android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.tidak));
                                tidakAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                sp_editSkrinningBanyakAnak.setAdapter(tidakAdapter);
                                if (banyak_anak != null) {
                                    int spinnerPositionHamilMuda = tidakAdapter.getPosition(banyak_anak);
                                    sp_editSkrinningBanyakAnak.setSelection(spinnerPositionHamilMuda);
                                }

                                sp_editSkrinningUmurTua.setAdapter(tidakAdapter);
                                if (umur_tua != null) {
                                    int spinnerPositionHamilMuda = tidakAdapter.getPosition(umur_tua);
                                    sp_editSkrinningUmurTua.setSelection(spinnerPositionHamilMuda);
                                }

                                ArrayAdapter<String> yaAdapter = new ArrayAdapter<String>(TambahDataScreeningActivity.this,
                                        android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.ya));
                                yaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                sp_editSkrinningPendek.setAdapter(yaAdapter);
                                if (pendek != null) {
                                    int spinnerPositionHamilMuda = yaAdapter.getPosition(pendek);
                                    sp_editSkrinningPendek.setSelection(spinnerPositionHamilMuda);
                                }

                                loadingDialog.dissmissDialog();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(TambahDataScreeningActivity.this, "Data Skrinning Tidak Ada!", Toast.LENGTH_SHORT).show();
                            loadingDialog.dissmissDialog();
                        }

                    }
                }
                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(TambahDataScreeningActivity.this, "Koneksi Gagal!" + error.toString(), Toast.LENGTH_SHORT).show();
                loadingDialog.dissmissDialog();
            }
        }) {
        };
        RequestQueue requestQueue = Volley.newRequestQueue(TambahDataScreeningActivity.this);
        requestQueue.add(stringRequest);
    }
}
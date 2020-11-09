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
import com.cikarastudio.cikarasimetalia.dialog.LoadingDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class EditKunjunganBaruDataObyektifActivity extends AppCompatActivity {

    public static final String ID_KUNJUNGANBARU = "extra_data";
    String id_kunjunganBaru;
    LoadingDialog loadingDialog;
    EditText tv_editObyektifBaruBeratBadan, tv_editObyektifBaruTinggiBadan, tv_editObyektifBaruLiLa,
            tv_editObyektifBaruTD, tv_editObyektifBaruPulse, tv_editObyektifBaruRespirasi, tv_editObyektifBaruSuhu,
            tv_editObyektifBaruKepala, tv_editObyektifBaruMuka, tv_editObyektifBaruLeher, tv_editOByektifBaruDada,
            tv_editObyektifBaruBentukPerut, tv_editObyektifBaruLeopold1, tv_editObyektifBaruLeopold2,
            tv_editObyektifBaruLeopold3, tv_editObyektifBaruLeopold4, tv_editObyektifBaruTFU, tv_editObyektifBaruDJJ,
            tv_editObyektifBaruGerakanJanin, tv_editObyektifBaruLingkarPerut, tv_editObyektifBaruKontraksi,
            tv_editObyektifBaruHB, tv_editObyektifBaruProtein, tv_editObyektifBaruGlukosa, tv_editObyektifBaruHBSAG,
            tv_editObyektifBaruHIV, tv_editObyektifBaruLainnyaLab, tv_editObyektifBaruUSG, tv_editObyektifBaruPenunjangLain;
    Spinner sp_editObyektifBaruKeadaanUmum, sp_editObyektifBaruKesadaran, sp_editObyektifBaruStriaeGravidarum,
            sp_editOByektifBaruLinea, sp_editObyektifBaruVulva, sp_editObyektifBaruVarises,
            sp_editObyektifBaruEdema, sp_editObyektifBaruRefleks,sp_editOByektifBaruBekasLuka;
    ImageView btn_toggleEditObyektifBaru;
    LinearLayout btn_updateDataObyektifBaru;
    private static final String URL_UPDATEOBYEKTIFBARU = "http://simetalia.com/android/update_obyektif_baru.php";


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_kunjungan_baru_data_obyektif);

        loadingDialog = new LoadingDialog(EditKunjunganBaruDataObyektifActivity.this);
        loadingDialog.startLoading();

        Intent intent = getIntent();
        id_kunjunganBaru = intent.getStringExtra(ID_KUNJUNGANBARU);

        sp_editObyektifBaruKeadaanUmum = findViewById(R.id.sp_editObyektifBaruKeadaanUmum);
        sp_editObyektifBaruKesadaran = findViewById(R.id.sp_editObyektifBaruKesadaran);
        tv_editObyektifBaruBeratBadan = findViewById(R.id.tv_editObyektifBaruBeratBadan);
        tv_editObyektifBaruTinggiBadan = findViewById(R.id.tv_editObyektifBaruTinggiBadan);
        tv_editObyektifBaruLiLa = findViewById(R.id.tv_editObyektifBaruLiLa);
        tv_editObyektifBaruTD = findViewById(R.id.tv_editObyektifBaruTD);
        tv_editObyektifBaruPulse = findViewById(R.id.tv_editObyektifBaruPulse);
        tv_editObyektifBaruRespirasi = findViewById(R.id.tv_editObyektifBaruRespirasi);
        tv_editObyektifBaruSuhu = findViewById(R.id.tv_editObyektifBaruSuhu);
        tv_editObyektifBaruKepala = findViewById(R.id.tv_editObyektifBaruKepala);
        tv_editObyektifBaruMuka = findViewById(R.id.tv_editObyektifBaruMuka);
        tv_editObyektifBaruLeher = findViewById(R.id.tv_editObyektifBaruLeher);
        tv_editOByektifBaruDada = findViewById(R.id.tv_editOByektifBaruDada);
        tv_editObyektifBaruBentukPerut = findViewById(R.id.tv_editObyektifBaruBentukPerut);
        sp_editObyektifBaruStriaeGravidarum = findViewById(R.id.sp_editObyektifBaruStriaeGravidarum);
        sp_editOByektifBaruLinea = findViewById(R.id.sp_editOByektifBaruLinea);
        sp_editOByektifBaruBekasLuka = findViewById(R.id.sp_editObyektifBaruBekasLuka);
        tv_editObyektifBaruLeopold1 = findViewById(R.id.tv_editObyektifBaruLeopold1);
        tv_editObyektifBaruLeopold2 = findViewById(R.id.tv_editObyektifBaruLeopold2);
        tv_editObyektifBaruLeopold3 = findViewById(R.id.tv_editObyektifBaruLeopold3);
        tv_editObyektifBaruLeopold4 = findViewById(R.id.tv_editObyektifBaruLeopold4);
        tv_editObyektifBaruTFU = findViewById(R.id.tv_editObyektifBaruTFU);
        tv_editObyektifBaruDJJ = findViewById(R.id.tv_editObyektifBaruDJJ);
        tv_editObyektifBaruGerakanJanin = findViewById(R.id.tv_editObyektifBaruGerakanJanin);
        tv_editObyektifBaruLingkarPerut = findViewById(R.id.tv_editObyektifBaruLingkarPerut);
        tv_editObyektifBaruKontraksi = findViewById(R.id.tv_editObyektifBaruKontraksi);
        sp_editObyektifBaruVulva = findViewById(R.id.sp_editObyektifBaruVulva);
        sp_editObyektifBaruVarises = findViewById(R.id.sp_editObyektifBaruVarises);
        sp_editObyektifBaruEdema = findViewById(R.id.sp_editObyektifBaruEdema);
        sp_editObyektifBaruRefleks = findViewById(R.id.sp_editObyektifBaruRefleks);
        tv_editObyektifBaruHB = findViewById(R.id.tv_editObyektifBaruHB);
        tv_editObyektifBaruProtein = findViewById(R.id.tv_editObyektifBaruProtein);
        tv_editObyektifBaruGlukosa = findViewById(R.id.tv_editObyektifBaruGlukosa);
        tv_editObyektifBaruHBSAG = findViewById(R.id.tv_editObyektifBaruHBSAG);
        tv_editObyektifBaruHIV = findViewById(R.id.tv_editObyektifBaruHIV);
        tv_editObyektifBaruLainnyaLab = findViewById(R.id.tv_editObyektifBaruLainnyaLab);
        tv_editObyektifBaruUSG = findViewById(R.id.tv_editObyektifBaruUSG);
        tv_editObyektifBaruPenunjangLain = findViewById(R.id.tv_editObyektifBaruPenunjangLain);
        btn_toggleEditObyektifBaru = findViewById(R.id.btn_toggleEditObyektifBaru);
        btn_updateDataObyektifBaru = findViewById(R.id.btn_updateDataObyektifBaru);


        sp_editObyektifBaruKeadaanUmum.setEnabled(false);
        sp_editObyektifBaruKesadaran.setEnabled(false);
        sp_editObyektifBaruStriaeGravidarum.setEnabled(false);
        sp_editOByektifBaruLinea.setEnabled(false);
        sp_editOByektifBaruBekasLuka.setEnabled(false);
        sp_editObyektifBaruVulva.setEnabled(false);
        sp_editObyektifBaruVarises.setEnabled(false);
        sp_editObyektifBaruEdema.setEnabled(false);
        sp_editObyektifBaruRefleks.setEnabled(false);

        loadDataObyektifBaru();

        btn_toggleEditObyektifBaru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleObyektifBaru();
            }
        });

        btn_updateDataObyektifBaru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateDataObyektifBaru();
            }
        });

    }

    private void updateDataObyektifBaru() {
        loadingDialog.startLoading();

        final String bb = tv_editObyektifBaruBeratBadan.getText().toString().trim();
        final String tb = tv_editObyektifBaruTinggiBadan.getText().toString().trim();
        final String lila = tv_editObyektifBaruLiLa.getText().toString().trim();
        final String td = tv_editObyektifBaruTD.getText().toString().trim();
        final String pulse = tv_editObyektifBaruPulse.getText().toString().trim();
        final String respirasi = tv_editObyektifBaruRespirasi.getText().toString().trim();
        final String suhu = tv_editObyektifBaruSuhu.getText().toString().trim();
        final String kepala = tv_editObyektifBaruKepala.getText().toString().trim();
        final String muka = tv_editObyektifBaruMuka.getText().toString().trim();
        final String leher = tv_editObyektifBaruLeher.getText().toString().trim();
        final String dada = tv_editOByektifBaruDada.getText().toString().trim();
        final String bentuk_perut = tv_editObyektifBaruBentukPerut.getText().toString().trim();
        final String leopold1 = tv_editObyektifBaruLeopold1.getText().toString().trim();
        final String leopold2 = tv_editObyektifBaruLeopold2.getText().toString().trim();
        final String leopold3 = tv_editObyektifBaruLeopold3.getText().toString().trim();
        final String leopold4 = tv_editObyektifBaruLeopold4.getText().toString().trim();
        final String tfu = tv_editObyektifBaruTFU.getText().toString().trim();
        final String djj = tv_editObyektifBaruDJJ.getText().toString().trim();
        final String gerak_janin = tv_editObyektifBaruGerakanJanin.getText().toString().trim();
        final String lingkar_perut = tv_editObyektifBaruLingkarPerut.getText().toString().trim();
        final String kontraksi = tv_editObyektifBaruKontraksi.getText().toString().trim();
        final String hb = tv_editObyektifBaruHB.getText().toString().trim();
        final String protein = tv_editObyektifBaruProtein.getText().toString().trim();
        final String glukosa = tv_editObyektifBaruGlukosa.getText().toString().trim();
        final String hbsag = tv_editObyektifBaruHBSAG.getText().toString().trim();
        final String hiv = tv_editObyektifBaruHIV.getText().toString().trim();
        final String lainnya_lab = tv_editObyektifBaruLainnyaLab.getText().toString().trim();
        final String usg = tv_editObyektifBaruUSG.getText().toString().trim();
        final String penunjang_lain = tv_editObyektifBaruPenunjangLain.getText().toString().trim();

        final String keadaan_umum = sp_editObyektifBaruKeadaanUmum.getSelectedItem().toString();
        final String kesadaran = sp_editObyektifBaruKesadaran.getSelectedItem().toString();
        final String striae_gravidarum = sp_editObyektifBaruStriaeGravidarum.getSelectedItem().toString();
        final String linea = sp_editOByektifBaruLinea.getSelectedItem().toString();
        final String bekas_luka = sp_editOByektifBaruBekasLuka.getSelectedItem().toString();
        final String vulva = sp_editObyektifBaruVulva.getSelectedItem().toString();
        final String varises = sp_editObyektifBaruVarises.getSelectedItem().toString();
        final String edema = sp_editObyektifBaruEdema.getSelectedItem().toString();
        final String refleks = sp_editObyektifBaruRefleks.getSelectedItem().toString();


        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        final String updated_at = dateFormat.format(cal.getTime());

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_UPDATEOBYEKTIFBARU,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")) {
                                Toast.makeText(EditKunjunganBaruDataObyektifActivity.this, "Update Data Obyektif Berhasil", Toast.LENGTH_LONG).show();
                                loadingDialog.dissmissDialog();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(EditKunjunganBaruDataObyektifActivity.this, "Update Data Obyektif Gagal!", Toast.LENGTH_LONG).show();
                            loadingDialog.dissmissDialog();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(EditKunjunganBaruDataObyektifActivity.this, "Update Data Obyektif Gagal! : Cek Koneksi Anda", Toast.LENGTH_LONG).show();
                        loadingDialog.dissmissDialog();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                params.put("id", id_kunjunganBaru);
                params.put("keadaan_umum", keadaan_umum);
                params.put("kesadaran", kesadaran);
                params.put("bb", bb);
                params.put("tb", tb);
                params.put("lila", lila);
                params.put("td", td);
                params.put("pulse", pulse);
                params.put("respirasi", respirasi);
                params.put("suhu", suhu);
                params.put("kepala", kepala);
                params.put("muka", muka);
                params.put("leher", leher);
                params.put("dada", dada);
                params.put("bentuk_perut", bentuk_perut);
                params.put("striae_gravidarum", striae_gravidarum);
                params.put("linea", linea);
                params.put("bekas_luka", bekas_luka);
                params.put("leopold1", leopold1);
                params.put("leopold2", leopold2);
                params.put("leopold3", leopold3);
                params.put("leopold4", leopold4);
                params.put("tfu", tfu);
                params.put("djj", djj);
                params.put("gerak_janin", gerak_janin);
                params.put("lingkar_perut", lingkar_perut);
                params.put("kontraksi", kontraksi);
                params.put("vulva", vulva);
                params.put("varises", varises);
                params.put("edema", edema);
                params.put("refleks", refleks);
                params.put("hb", hb);
                params.put("protein", protein);
                params.put("glukosa", glukosa);
                params.put("hbsag", hbsag);
                params.put("hiv", hiv);
                params.put("lainnya_lab", lainnya_lab);
                params.put("usg", usg);
                params.put("penunjang_lain", penunjang_lain);
                params.put("updated_at", updated_at);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void toggleObyektifBaru() {
        if (!tv_editObyektifBaruBeratBadan.isEnabled()) {
            tv_editObyektifBaruBeratBadan.setEnabled(true);
            tv_editObyektifBaruBeratBadan.setFocusable(true);
            tv_editObyektifBaruBeratBadan.setCursorVisible(true);
            tv_editObyektifBaruBeratBadan.setFocusableInTouchMode(true);
            tv_editObyektifBaruBeratBadan.setTextColor(Color.parseColor("#000000"));
            tv_editObyektifBaruTinggiBadan.setEnabled(true);
            tv_editObyektifBaruTinggiBadan.setFocusable(true);
            tv_editObyektifBaruTinggiBadan.setCursorVisible(true);
            tv_editObyektifBaruTinggiBadan.setFocusableInTouchMode(true);
            tv_editObyektifBaruTinggiBadan.setTextColor(Color.parseColor("#000000"));
            tv_editObyektifBaruLiLa.setEnabled(true);
            tv_editObyektifBaruLiLa.setFocusable(true);
            tv_editObyektifBaruLiLa.setCursorVisible(true);
            tv_editObyektifBaruLiLa.setFocusableInTouchMode(true);
            tv_editObyektifBaruLiLa.setTextColor(Color.parseColor("#000000"));
            tv_editObyektifBaruTD.setEnabled(true);
            tv_editObyektifBaruTD.setFocusable(true);
            tv_editObyektifBaruTD.setCursorVisible(true);
            tv_editObyektifBaruTD.setFocusableInTouchMode(true);
            tv_editObyektifBaruTD.setTextColor(Color.parseColor("#000000"));
            tv_editObyektifBaruPulse.setEnabled(true);
            tv_editObyektifBaruPulse.setFocusable(true);
            tv_editObyektifBaruPulse.setCursorVisible(true);
            tv_editObyektifBaruPulse.setFocusableInTouchMode(true);
            tv_editObyektifBaruPulse.setTextColor(Color.parseColor("#000000"));
            tv_editObyektifBaruRespirasi.setEnabled(true);
            tv_editObyektifBaruRespirasi.setFocusable(true);
            tv_editObyektifBaruRespirasi.setCursorVisible(true);
            tv_editObyektifBaruRespirasi.setFocusableInTouchMode(true);
            tv_editObyektifBaruRespirasi.setTextColor(Color.parseColor("#000000"));
            tv_editObyektifBaruSuhu.setEnabled(true);
            tv_editObyektifBaruSuhu.setFocusable(true);
            tv_editObyektifBaruSuhu.setCursorVisible(true);
            tv_editObyektifBaruSuhu.setFocusableInTouchMode(true);
            tv_editObyektifBaruSuhu.setTextColor(Color.parseColor("#000000"));
            tv_editObyektifBaruKepala.setEnabled(true);
            tv_editObyektifBaruKepala.setFocusable(true);
            tv_editObyektifBaruKepala.setCursorVisible(true);
            tv_editObyektifBaruKepala.setFocusableInTouchMode(true);
            tv_editObyektifBaruKepala.setTextColor(Color.parseColor("#000000"));
            tv_editObyektifBaruMuka.setEnabled(true);
            tv_editObyektifBaruMuka.setFocusable(true);
            tv_editObyektifBaruMuka.setCursorVisible(true);
            tv_editObyektifBaruMuka.setFocusableInTouchMode(true);
            tv_editObyektifBaruMuka.setTextColor(Color.parseColor("#000000"));
            tv_editObyektifBaruLeher.setEnabled(true);
            tv_editObyektifBaruLeher.setFocusable(true);
            tv_editObyektifBaruLeher.setCursorVisible(true);
            tv_editObyektifBaruLeher.setFocusableInTouchMode(true);
            tv_editObyektifBaruLeher.setTextColor(Color.parseColor("#000000"));
            tv_editOByektifBaruDada.setEnabled(true);
            tv_editOByektifBaruDada.setFocusable(true);
            tv_editOByektifBaruDada.setCursorVisible(true);
            tv_editOByektifBaruDada.setFocusableInTouchMode(true);
            tv_editOByektifBaruDada.setTextColor(Color.parseColor("#000000"));
            tv_editObyektifBaruBentukPerut.setEnabled(true);
            tv_editObyektifBaruBentukPerut.setFocusable(true);
            tv_editObyektifBaruBentukPerut.setCursorVisible(true);
            tv_editObyektifBaruBentukPerut.setFocusableInTouchMode(true);
            tv_editObyektifBaruBentukPerut.setTextColor(Color.parseColor("#000000"));
            tv_editObyektifBaruLeopold1.setEnabled(true);
            tv_editObyektifBaruLeopold1.setFocusable(true);
            tv_editObyektifBaruLeopold1.setCursorVisible(true);
            tv_editObyektifBaruLeopold1.setFocusableInTouchMode(true);
            tv_editObyektifBaruLeopold1.setTextColor(Color.parseColor("#000000"));
            tv_editObyektifBaruLeopold2.setEnabled(true);
            tv_editObyektifBaruLeopold2.setFocusable(true);
            tv_editObyektifBaruLeopold2.setCursorVisible(true);
            tv_editObyektifBaruLeopold2.setFocusableInTouchMode(true);
            tv_editObyektifBaruLeopold2.setTextColor(Color.parseColor("#000000"));
            tv_editObyektifBaruLeopold3.setEnabled(true);
            tv_editObyektifBaruLeopold3.setFocusable(true);
            tv_editObyektifBaruLeopold3.setCursorVisible(true);
            tv_editObyektifBaruLeopold3.setFocusableInTouchMode(true);
            tv_editObyektifBaruLeopold3.setTextColor(Color.parseColor("#000000"));
            tv_editObyektifBaruLeopold4.setEnabled(true);
            tv_editObyektifBaruLeopold4.setFocusable(true);
            tv_editObyektifBaruLeopold4.setCursorVisible(true);
            tv_editObyektifBaruLeopold4.setFocusableInTouchMode(true);
            tv_editObyektifBaruLeopold4.setTextColor(Color.parseColor("#000000"));
            tv_editObyektifBaruTFU.setEnabled(true);
            tv_editObyektifBaruTFU.setFocusable(true);
            tv_editObyektifBaruTFU.setCursorVisible(true);
            tv_editObyektifBaruTFU.setFocusableInTouchMode(true);
            tv_editObyektifBaruTFU.setTextColor(Color.parseColor("#000000"));
            tv_editObyektifBaruDJJ.setEnabled(true);
            tv_editObyektifBaruDJJ.setFocusable(true);
            tv_editObyektifBaruDJJ.setCursorVisible(true);
            tv_editObyektifBaruDJJ.setFocusableInTouchMode(true);
            tv_editObyektifBaruDJJ.setTextColor(Color.parseColor("#000000"));
            tv_editObyektifBaruGerakanJanin.setEnabled(true);
            tv_editObyektifBaruGerakanJanin.setFocusable(true);
            tv_editObyektifBaruGerakanJanin.setCursorVisible(true);
            tv_editObyektifBaruGerakanJanin.setFocusableInTouchMode(true);
            tv_editObyektifBaruGerakanJanin.setTextColor(Color.parseColor("#000000"));
            tv_editObyektifBaruGerakanJanin.setEnabled(true);
            tv_editObyektifBaruLingkarPerut.setEnabled(true);
            tv_editObyektifBaruLingkarPerut.setFocusable(true);
            tv_editObyektifBaruLingkarPerut.setCursorVisible(true);
            tv_editObyektifBaruLingkarPerut.setFocusableInTouchMode(true);
            tv_editObyektifBaruLingkarPerut.setTextColor(Color.parseColor("#000000"));
            tv_editObyektifBaruKontraksi.setEnabled(true);
            tv_editObyektifBaruKontraksi.setFocusable(true);
            tv_editObyektifBaruKontraksi.setCursorVisible(true);
            tv_editObyektifBaruKontraksi.setFocusableInTouchMode(true);
            tv_editObyektifBaruKontraksi.setTextColor(Color.parseColor("#000000"));
            tv_editObyektifBaruHB.setEnabled(true);
            tv_editObyektifBaruHB.setFocusable(true);
            tv_editObyektifBaruHB.setCursorVisible(true);
            tv_editObyektifBaruHB.setFocusableInTouchMode(true);
            tv_editObyektifBaruHB.setTextColor(Color.parseColor("#000000"));
            tv_editObyektifBaruProtein.setEnabled(true);
            tv_editObyektifBaruProtein.setFocusable(true);
            tv_editObyektifBaruProtein.setCursorVisible(true);
            tv_editObyektifBaruProtein.setFocusableInTouchMode(true);
            tv_editObyektifBaruProtein.setTextColor(Color.parseColor("#000000"));
            tv_editObyektifBaruGlukosa.setEnabled(true);
            tv_editObyektifBaruGlukosa.setFocusable(true);
            tv_editObyektifBaruGlukosa.setCursorVisible(true);
            tv_editObyektifBaruGlukosa.setFocusableInTouchMode(true);
            tv_editObyektifBaruGlukosa.setTextColor(Color.parseColor("#000000"));
            tv_editObyektifBaruHBSAG.setEnabled(true);
            tv_editObyektifBaruHBSAG.setFocusable(true);
            tv_editObyektifBaruHBSAG.setCursorVisible(true);
            tv_editObyektifBaruHBSAG.setFocusableInTouchMode(true);
            tv_editObyektifBaruHBSAG.setTextColor(Color.parseColor("#000000"));
            tv_editObyektifBaruHIV.setEnabled(true);
            tv_editObyektifBaruHIV.setFocusable(true);
            tv_editObyektifBaruHIV.setCursorVisible(true);
            tv_editObyektifBaruHIV.setFocusableInTouchMode(true);
            tv_editObyektifBaruHIV.setTextColor(Color.parseColor("#000000"));
            tv_editObyektifBaruLainnyaLab.setEnabled(true);
            tv_editObyektifBaruLainnyaLab.setFocusable(true);
            tv_editObyektifBaruLainnyaLab.setCursorVisible(true);
            tv_editObyektifBaruLainnyaLab.setFocusableInTouchMode(true);
            tv_editObyektifBaruLainnyaLab.setTextColor(Color.parseColor("#000000"));
            tv_editObyektifBaruUSG.setEnabled(true);
            tv_editObyektifBaruUSG.setFocusable(true);
            tv_editObyektifBaruUSG.setCursorVisible(true);
            tv_editObyektifBaruUSG.setFocusableInTouchMode(true);
            tv_editObyektifBaruUSG.setTextColor(Color.parseColor("#000000"));
            tv_editObyektifBaruPenunjangLain.setEnabled(true);
            tv_editObyektifBaruPenunjangLain.setFocusable(true);
            tv_editObyektifBaruPenunjangLain.setCursorVisible(true);
            tv_editObyektifBaruPenunjangLain.setFocusableInTouchMode(true);
            tv_editObyektifBaruPenunjangLain.setTextColor(Color.parseColor("#000000"));
            sp_editObyektifBaruKeadaanUmum.setEnabled(true);
            sp_editObyektifBaruKesadaran.setEnabled(true);
            sp_editObyektifBaruStriaeGravidarum.setEnabled(true);
            sp_editOByektifBaruLinea.setEnabled(true);
            sp_editOByektifBaruBekasLuka.setEnabled(true);
            sp_editObyektifBaruVulva.setEnabled(true);
            sp_editObyektifBaruVarises.setEnabled(true);
            sp_editObyektifBaruEdema.setEnabled(true);
            sp_editObyektifBaruRefleks.setEnabled(true);
            Toast.makeText(EditKunjunganBaruDataObyektifActivity.this, "Mode Edit Diaktifkan!", Toast.LENGTH_LONG).show();
        } else if (tv_editObyektifBaruBeratBadan.isEnabled()) {
            tv_editObyektifBaruBeratBadan.setEnabled(false);
            tv_editObyektifBaruBeratBadan.setFocusable(false);
            tv_editObyektifBaruBeratBadan.setCursorVisible(false);
            tv_editObyektifBaruBeratBadan.setTextColor(Color.parseColor("#919191"));
            tv_editObyektifBaruTinggiBadan.setEnabled(false);
            tv_editObyektifBaruTinggiBadan.setFocusable(false);
            tv_editObyektifBaruTinggiBadan.setCursorVisible(false);
            tv_editObyektifBaruTinggiBadan.setTextColor(Color.parseColor("#919191"));
            tv_editObyektifBaruLiLa.setEnabled(false);
            tv_editObyektifBaruLiLa.setFocusable(false);
            tv_editObyektifBaruLiLa.setCursorVisible(false);
            tv_editObyektifBaruLiLa.setTextColor(Color.parseColor("#919191"));
            tv_editObyektifBaruTD.setEnabled(false);
            tv_editObyektifBaruTD.setFocusable(false);
            tv_editObyektifBaruTD.setCursorVisible(false);
            tv_editObyektifBaruTD.setTextColor(Color.parseColor("#919191"));
            tv_editObyektifBaruPulse.setEnabled(false);
            tv_editObyektifBaruPulse.setFocusable(false);
            tv_editObyektifBaruPulse.setCursorVisible(false);
            tv_editObyektifBaruPulse.setTextColor(Color.parseColor("#919191"));
            tv_editObyektifBaruRespirasi.setEnabled(false);
            tv_editObyektifBaruRespirasi.setFocusable(false);
            tv_editObyektifBaruRespirasi.setCursorVisible(false);
            tv_editObyektifBaruRespirasi.setTextColor(Color.parseColor("#919191"));
            tv_editObyektifBaruSuhu.setEnabled(false);
            tv_editObyektifBaruSuhu.setFocusable(false);
            tv_editObyektifBaruSuhu.setCursorVisible(false);
            tv_editObyektifBaruSuhu.setTextColor(Color.parseColor("#919191"));
            tv_editObyektifBaruKepala.setEnabled(false);
            tv_editObyektifBaruKepala.setFocusable(false);
            tv_editObyektifBaruKepala.setCursorVisible(false);
            tv_editObyektifBaruKepala.setTextColor(Color.parseColor("#919191"));
            tv_editObyektifBaruMuka.setEnabled(false);
            tv_editObyektifBaruMuka.setFocusable(false);
            tv_editObyektifBaruMuka.setCursorVisible(false);
            tv_editObyektifBaruMuka.setTextColor(Color.parseColor("#919191"));
            tv_editObyektifBaruLeher.setEnabled(false);
            tv_editObyektifBaruLeher.setFocusable(false);
            tv_editObyektifBaruLeher.setCursorVisible(false);
            tv_editObyektifBaruLeher.setTextColor(Color.parseColor("#919191"));
            tv_editOByektifBaruDada.setEnabled(false);
            tv_editOByektifBaruDada.setFocusable(false);
            tv_editOByektifBaruDada.setCursorVisible(false);
            tv_editOByektifBaruDada.setTextColor(Color.parseColor("#919191"));
            tv_editObyektifBaruBentukPerut.setEnabled(false);
            tv_editObyektifBaruBentukPerut.setFocusable(false);
            tv_editObyektifBaruBentukPerut.setCursorVisible(false);
            tv_editObyektifBaruBentukPerut.setTextColor(Color.parseColor("#919191"));
            tv_editObyektifBaruLeopold1.setEnabled(false);
            tv_editObyektifBaruLeopold1.setFocusable(false);
            tv_editObyektifBaruLeopold1.setCursorVisible(false);
            tv_editObyektifBaruLeopold1.setTextColor(Color.parseColor("#919191"));
            tv_editObyektifBaruLeopold2.setEnabled(false);
            tv_editObyektifBaruLeopold2.setFocusable(false);
            tv_editObyektifBaruLeopold2.setCursorVisible(false);
            tv_editObyektifBaruLeopold2.setTextColor(Color.parseColor("#919191"));
            tv_editObyektifBaruLeopold3.setEnabled(false);
            tv_editObyektifBaruLeopold3.setFocusable(false);
            tv_editObyektifBaruLeopold3.setCursorVisible(false);
            tv_editObyektifBaruLeopold3.setTextColor(Color.parseColor("#919191"));
            tv_editObyektifBaruLeopold4.setEnabled(false);
            tv_editObyektifBaruLeopold4.setFocusable(false);
            tv_editObyektifBaruLeopold4.setCursorVisible(false);
            tv_editObyektifBaruLeopold4.setTextColor(Color.parseColor("#919191"));
            tv_editObyektifBaruTFU.setEnabled(false);
            tv_editObyektifBaruTFU.setFocusable(false);
            tv_editObyektifBaruTFU.setCursorVisible(false);
            tv_editObyektifBaruTFU.setTextColor(Color.parseColor("#919191"));
            tv_editObyektifBaruDJJ.setEnabled(false);
            tv_editObyektifBaruDJJ.setFocusable(false);
            tv_editObyektifBaruDJJ.setCursorVisible(false);
            tv_editObyektifBaruDJJ.setTextColor(Color.parseColor("#919191"));
            tv_editObyektifBaruGerakanJanin.setEnabled(false);
            tv_editObyektifBaruGerakanJanin.setFocusable(false);
            tv_editObyektifBaruGerakanJanin.setCursorVisible(false);
            tv_editObyektifBaruGerakanJanin.setTextColor(Color.parseColor("#919191"));
            tv_editObyektifBaruLingkarPerut.setEnabled(false);
            tv_editObyektifBaruLingkarPerut.setFocusable(false);
            tv_editObyektifBaruLingkarPerut.setCursorVisible(false);
            tv_editObyektifBaruLingkarPerut.setTextColor(Color.parseColor("#919191"));
            tv_editObyektifBaruKontraksi.setEnabled(false);
            tv_editObyektifBaruKontraksi.setFocusable(false);
            tv_editObyektifBaruKontraksi.setCursorVisible(false);
            tv_editObyektifBaruKontraksi.setTextColor(Color.parseColor("#919191"));
            tv_editObyektifBaruHB.setEnabled(false);
            tv_editObyektifBaruHB.setFocusable(false);
            tv_editObyektifBaruHB.setCursorVisible(false);
            tv_editObyektifBaruHB.setTextColor(Color.parseColor("#919191"));
            tv_editObyektifBaruProtein.setEnabled(false);
            tv_editObyektifBaruProtein.setFocusable(false);
            tv_editObyektifBaruProtein.setCursorVisible(false);
            tv_editObyektifBaruProtein.setTextColor(Color.parseColor("#919191"));
            tv_editObyektifBaruGlukosa.setEnabled(false);
            tv_editObyektifBaruGlukosa.setFocusable(false);
            tv_editObyektifBaruGlukosa.setCursorVisible(false);
            tv_editObyektifBaruGlukosa.setTextColor(Color.parseColor("#919191"));
            tv_editObyektifBaruHBSAG.setEnabled(false);
            tv_editObyektifBaruHBSAG.setFocusable(false);
            tv_editObyektifBaruHBSAG.setCursorVisible(false);
            tv_editObyektifBaruHBSAG.setTextColor(Color.parseColor("#919191"));
            tv_editObyektifBaruHIV.setEnabled(false);
            tv_editObyektifBaruHIV.setFocusable(false);
            tv_editObyektifBaruHIV.setCursorVisible(false);
            tv_editObyektifBaruHIV.setTextColor(Color.parseColor("#919191"));
            tv_editObyektifBaruLainnyaLab.setEnabled(false);
            tv_editObyektifBaruLainnyaLab.setFocusable(false);
            tv_editObyektifBaruLainnyaLab.setCursorVisible(false);
            tv_editObyektifBaruLainnyaLab.setTextColor(Color.parseColor("#919191"));
            tv_editObyektifBaruUSG.setEnabled(false);
            tv_editObyektifBaruUSG.setFocusable(false);
            tv_editObyektifBaruUSG.setCursorVisible(false);
            tv_editObyektifBaruUSG.setTextColor(Color.parseColor("#919191"));
            tv_editObyektifBaruPenunjangLain.setEnabled(false);
            tv_editObyektifBaruPenunjangLain.setFocusable(false);
            tv_editObyektifBaruPenunjangLain.setCursorVisible(false);
            tv_editObyektifBaruPenunjangLain.setTextColor(Color.parseColor("#919191"));
            sp_editObyektifBaruKeadaanUmum.setEnabled(false);
            sp_editObyektifBaruKesadaran.setEnabled(false);
            sp_editObyektifBaruStriaeGravidarum.setEnabled(false);
            sp_editOByektifBaruLinea.setEnabled(false);
            sp_editOByektifBaruBekasLuka.setEnabled(false);
            sp_editObyektifBaruVulva.setEnabled(false);
            sp_editObyektifBaruVarises.setEnabled(false);
            sp_editObyektifBaruEdema.setEnabled(false);
            sp_editObyektifBaruRefleks.setEnabled(false);
            Toast.makeText(EditKunjunganBaruDataObyektifActivity.this, "Mode Edit Dinonaktifkan!", Toast.LENGTH_SHORT).show();
        }
    }

    private void loadDataObyektifBaru() {
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

                                String keadaan_umum = object.getString("keadaan_umum").trim();
                                String kesadaran = object.getString("kesadaran").trim();
                                String bb = object.getString("bb").trim();
                                String tb = object.getString("tb").trim();
                                String lila = object.getString("lila").trim();
                                String td = object.getString("td").trim();
                                String pulse = object.getString("pulse").trim();
                                String respirasi = object.getString("respirasi").trim();
                                String suhu = object.getString("suhu").trim();
                                String kepala = object.getString("kepala").trim();
                                String muka = object.getString("muka").trim();
                                String leher = object.getString("leher").trim();
                                String dada = object.getString("dada").trim();
                                String bentuk_perut = object.getString("bentuk_perut").trim();
                                String striae_gravidarum = object.getString("striae_gravidarum").trim();
                                String linea = object.getString("linea").trim();
                                String bekas_luka = object.getString("bekas_luka").trim();
                                String leopold1 = object.getString("leopold1").trim();
                                String leopold2 = object.getString("leopold2").trim();
                                String leopold3 = object.getString("leopold3").trim();
                                String leopold4 = object.getString("leopold4").trim();
                                String tfu = object.getString("tfu").trim();
                                String djj = object.getString("djj").trim();
                                String gerak_janin = object.getString("gerak_janin").trim();
                                String lingkar_perut = object.getString("lingkar_perut").trim();
                                String kontraksi = object.getString("kontraksi").trim();
                                String vulva = object.getString("vulva").trim();
                                String varises = object.getString("varises").trim();
                                String edema = object.getString("edema").trim();
                                String refleks = object.getString("refleks").trim();
                                String hb = object.getString("hb").trim();
                                String protein = object.getString("protein").trim();
                                String glukosa = object.getString("glukosa").trim();
                                String hbsag = object.getString("hbsag").trim();
                                String hiv = object.getString("hiv").trim();
                                String lainnya_lab = object.getString("lainnya_lab").trim();
                                String usg = object.getString("usg").trim();
                                String penunjang_lain = object.getString("penunjang_lain").trim();

                                tv_editObyektifBaruBeratBadan.setText(bb);
                                tv_editObyektifBaruTinggiBadan.setText(tb);
                                tv_editObyektifBaruLiLa.setText(lila);
                                tv_editObyektifBaruTD.setText(td);
                                tv_editObyektifBaruPulse.setText(pulse);
                                tv_editObyektifBaruRespirasi.setText(respirasi);
                                tv_editObyektifBaruSuhu.setText(suhu);
                                tv_editObyektifBaruKepala.setText(kepala);
                                tv_editObyektifBaruMuka.setText(muka);
                                tv_editObyektifBaruLeher.setText(leher);
                                tv_editOByektifBaruDada.setText(dada);
                                tv_editObyektifBaruBentukPerut.setText(bentuk_perut);
                                tv_editObyektifBaruLeopold1.setText(leopold1);
                                tv_editObyektifBaruLeopold2.setText(leopold2);
                                tv_editObyektifBaruLeopold3.setText(leopold3);
                                tv_editObyektifBaruLeopold4.setText(leopold4);
                                tv_editObyektifBaruTFU.setText(tfu);
                                tv_editObyektifBaruDJJ.setText(djj);
                                tv_editObyektifBaruGerakanJanin.setText(gerak_janin);
                                tv_editObyektifBaruLingkarPerut.setText(lingkar_perut);
                                tv_editObyektifBaruKontraksi.setText(kontraksi);
                                tv_editObyektifBaruHB.setText(hb);
                                tv_editObyektifBaruProtein.setText(protein);
                                tv_editObyektifBaruGlukosa.setText(glukosa);
                                tv_editObyektifBaruHBSAG.setText(hbsag);
                                tv_editObyektifBaruHIV.setText(hiv);
                                tv_editObyektifBaruLainnyaLab.setText(lainnya_lab);
                                tv_editObyektifBaruUSG.setText(usg);
                                tv_editObyektifBaruPenunjangLain.setText(penunjang_lain);


                                ArrayAdapter<String> keadaanUmumAdapter = new ArrayAdapter<String>(EditKunjunganBaruDataObyektifActivity.this,
                                        android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.keadaan_umum));
                                keadaanUmumAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                sp_editObyektifBaruKeadaanUmum.setAdapter(keadaanUmumAdapter);
                                if (keadaan_umum != null) {
                                    int spinnerPositionTolongSalin = keadaanUmumAdapter.getPosition(keadaan_umum);
                                    sp_editObyektifBaruKeadaanUmum.setSelection(spinnerPositionTolongSalin);
                                }

                                ArrayAdapter<String> kesadaranAdapter = new ArrayAdapter<>(EditKunjunganBaruDataObyektifActivity.this,
                                        android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.kesadaran));
                                kesadaranAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                sp_editObyektifBaruKesadaran.setAdapter(kesadaranAdapter);
                                if (kesadaran != null) {
                                    int spinnerPositionGoldar = kesadaranAdapter.getPosition(kesadaran);
                                    sp_editObyektifBaruKesadaran.setSelection(spinnerPositionGoldar);
                                }

                                ArrayAdapter<String> striaeGravidarumAdapter = new ArrayAdapter<String>(EditKunjunganBaruDataObyektifActivity.this,
                                        android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.ya_tidak));
                                striaeGravidarumAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                sp_editObyektifBaruStriaeGravidarum.setAdapter(striaeGravidarumAdapter);
                                if (striae_gravidarum != null) {
                                    int spinnerPositionPendidikan = striaeGravidarumAdapter.getPosition(striae_gravidarum);
                                    sp_editObyektifBaruStriaeGravidarum.setSelection(spinnerPositionPendidikan);
                                }

                                ArrayAdapter<String> lineaAdapter = new ArrayAdapter<String>(EditKunjunganBaruDataObyektifActivity.this,
                                        android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.ya_tidak));
                                lineaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                sp_editOByektifBaruLinea.setAdapter(lineaAdapter);
                                if (linea != null) {
                                    int spinnerPositionSuntikanTT = lineaAdapter.getPosition(linea);
                                    sp_editOByektifBaruLinea.setSelection(spinnerPositionSuntikanTT);
                                }

                                ArrayAdapter<String> bekasLukaAdapter = new ArrayAdapter<String>(EditKunjunganBaruDataObyektifActivity.this,
                                        android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.ya_tidak));
                                bekasLukaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                sp_editOByektifBaruBekasLuka.setAdapter(bekasLukaAdapter);
                                if (bekas_luka != null) {
                                    int spinnerPositionBekasLuka = bekasLukaAdapter.getPosition(bekas_luka);
                                    sp_editOByektifBaruBekasLuka.setSelection(spinnerPositionBekasLuka);
                                }

                                ArrayAdapter<String> vulvaAdapter = new ArrayAdapter<String>(EditKunjunganBaruDataObyektifActivity.this,
                                        android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.vulva));
                                vulvaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                sp_editObyektifBaruVulva.setAdapter(vulvaAdapter);
                                if (vulva != null) {
                                    int spinnerPositionVulva = vulvaAdapter.getPosition(vulva);
                                    sp_editObyektifBaruVulva.setSelection(spinnerPositionVulva);
                                }

                                ArrayAdapter<String> varisesAdapter = new ArrayAdapter<String>(EditKunjunganBaruDataObyektifActivity.this,
                                        android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.ya_tidak));
                                varisesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                sp_editObyektifBaruVarises.setAdapter(varisesAdapter);
                                if (varises != null) {
                                    int spinnerPositionvarises = varisesAdapter.getPosition(varises);
                                    sp_editObyektifBaruVarises.setSelection(spinnerPositionvarises);
                                }

                                ArrayAdapter<String> edemaAdapter = new ArrayAdapter<String>(EditKunjunganBaruDataObyektifActivity.this,
                                        android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.ya_tidak));
                                edemaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                sp_editObyektifBaruEdema.setAdapter(edemaAdapter);
                                if (edema != null) {
                                    int spinnerPositionedema = edemaAdapter.getPosition(edema);
                                    sp_editObyektifBaruEdema.setSelection(spinnerPositionedema);
                                }

                                ArrayAdapter<String> refleksAdapter = new ArrayAdapter<String>(EditKunjunganBaruDataObyektifActivity.this,
                                        android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.pos_neg));
                                refleksAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                sp_editObyektifBaruRefleks.setAdapter(refleksAdapter);
                                if (refleks != null) {
                                    int spinnerPositionreflesk = refleksAdapter.getPosition(refleks);
                                    sp_editObyektifBaruRefleks.setSelection(spinnerPositionreflesk);
                                }

                                loadingDialog.dissmissDialog();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(EditKunjunganBaruDataObyektifActivity.this, "Data Obyektif Tidak Ada!" + e.toString(), Toast.LENGTH_SHORT).show();
                            loadingDialog.dissmissDialog();
                        }

                    }
                }
                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(EditKunjunganBaruDataObyektifActivity.this, "Koneksi Gagal!" + error.toString(), Toast.LENGTH_SHORT).show();
                loadingDialog.dissmissDialog();
            }
        }) {
        };
        RequestQueue requestQueue = Volley.newRequestQueue(EditKunjunganBaruDataObyektifActivity.this);
        requestQueue.add(stringRequest);
    }
}
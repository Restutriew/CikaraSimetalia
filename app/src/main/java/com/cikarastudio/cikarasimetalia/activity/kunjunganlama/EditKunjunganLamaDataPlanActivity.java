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
import com.cikarastudio.cikarasimetalia.activity.kunjunganbaru.EditKunjunganBaruDataPlanActivity;
import com.cikarastudio.cikarasimetalia.dialog.LoadingDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class EditKunjunganLamaDataPlanActivity extends AppCompatActivity {

    public static final String ID_KUNJUNGANLAMA = "extra_data";
    String id_kunjunganLama;
    LoadingDialog loadingDialog;
    EditText tv_editPlanBaruTerapiLain, tv_editPlanBaruEdukasi;
    Spinner sp_editPlanBaruTabletFE, sp_editPlanBaruJumObat, sp_editPlanBaruImunisasiTT, sp_editPlanBaruYangKe;
    ImageView btn_toggleEditPlanBaru;
    LinearLayout btn_updateDataPlanBaru;
    private static final String URL_UPDATEPLANLAMA = "http://simetalia.com/android/update_plan_lama.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_kunjungan_lama_data_plan);

        loadingDialog = new LoadingDialog(EditKunjunganLamaDataPlanActivity.this);
        loadingDialog.startLoading();

        Intent intent = getIntent();
        id_kunjunganLama = intent.getStringExtra(ID_KUNJUNGANLAMA);

        sp_editPlanBaruTabletFE = findViewById(R.id.sp_editPlanLamaTabletFE);
        sp_editPlanBaruJumObat = findViewById(R.id.sp_editPlanLamaJumObat);
        sp_editPlanBaruImunisasiTT = findViewById(R.id.sp_editPlanLamaImunisasiTT);
        sp_editPlanBaruYangKe = findViewById(R.id.sp_editPlanLamaYangKe);
        tv_editPlanBaruTerapiLain = findViewById(R.id.tv_editPlanLamaTerapiLain);
        tv_editPlanBaruEdukasi = findViewById(R.id.tv_editPlanLamaEdukasi);
        btn_toggleEditPlanBaru = findViewById(R.id.btn_toggleEditPlanLama);
        btn_updateDataPlanBaru = findViewById(R.id.btn_updateDataPlanLama);

        sp_editPlanBaruTabletFE.setEnabled(false);
        sp_editPlanBaruJumObat.setEnabled(false);
        sp_editPlanBaruImunisasiTT.setEnabled(false);
        sp_editPlanBaruYangKe.setEnabled(false);

        loadDataPlanBaru();

        btn_toggleEditPlanBaru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                togglePlanBaru();
            }
        });

        btn_updateDataPlanBaru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateDataPlanBaru();
            }
        });

    }

    private void updateDataPlanBaru() {
        loadingDialog.startLoading();

        final String terapi_lain = tv_editPlanBaruTerapiLain.getText().toString().trim();
        final String edukasi = tv_editPlanBaruEdukasi.getText().toString().trim();

        final String tablet_fe = sp_editPlanBaruTabletFE.getSelectedItem().toString();
        final String jum_obat = sp_editPlanBaruJumObat.getSelectedItem().toString();
        final String imunisasi_tt = sp_editPlanBaruImunisasiTT.getSelectedItem().toString();
        final String yang_ke = sp_editPlanBaruYangKe.getSelectedItem().toString();

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        final String updated_at = dateFormat.format(cal.getTime());

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_UPDATEPLANLAMA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")) {
                                Toast.makeText(EditKunjunganLamaDataPlanActivity.this, "Update Data Plan Berhasil", Toast.LENGTH_LONG).show();
                                loadingDialog.dissmissDialog();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(EditKunjunganLamaDataPlanActivity.this, "Update Data Plan Gagal!", Toast.LENGTH_LONG).show();
                            loadingDialog.dissmissDialog();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(EditKunjunganLamaDataPlanActivity.this, "Update Data Plan Gagal! : Cek Koneksi Anda", Toast.LENGTH_LONG).show();
                        loadingDialog.dissmissDialog();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                params.put("id", id_kunjunganLama);
                params.put("tablet_fe", tablet_fe);
                params.put("jum_obat", jum_obat);
                params.put("imunisasi_tt", imunisasi_tt);
                params.put("yang_ke", yang_ke);
                params.put("terapi_lain", terapi_lain);
                params.put("edukasi", edukasi);
                params.put("updated_at", updated_at);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void togglePlanBaru() {
        if (!tv_editPlanBaruTerapiLain.isEnabled()) {
            tv_editPlanBaruTerapiLain.setEnabled(true);
            tv_editPlanBaruTerapiLain.setFocusable(true);
            tv_editPlanBaruTerapiLain.setCursorVisible(true);
            tv_editPlanBaruTerapiLain.setFocusableInTouchMode(true);
            tv_editPlanBaruTerapiLain.setTextColor(Color.parseColor("#000000"));
            tv_editPlanBaruEdukasi.setEnabled(true);
            tv_editPlanBaruEdukasi.setFocusable(true);
            tv_editPlanBaruEdukasi.setCursorVisible(true);
            tv_editPlanBaruEdukasi.setFocusableInTouchMode(true);
            tv_editPlanBaruEdukasi.setTextColor(Color.parseColor("#000000"));
            sp_editPlanBaruTabletFE.setEnabled(true);
            sp_editPlanBaruJumObat.setEnabled(true);
            sp_editPlanBaruImunisasiTT.setEnabled(true);
            sp_editPlanBaruYangKe.setEnabled(true);
            Toast.makeText(EditKunjunganLamaDataPlanActivity.this, "Mode Edit Diaktifkan!", Toast.LENGTH_LONG).show();
        } else if (tv_editPlanBaruTerapiLain.isEnabled()) {
            tv_editPlanBaruTerapiLain.setEnabled(false);
            tv_editPlanBaruTerapiLain.setFocusable(false);
            tv_editPlanBaruTerapiLain.setCursorVisible(false);
            tv_editPlanBaruTerapiLain.setTextColor(Color.parseColor("#919191"));
            tv_editPlanBaruEdukasi.setEnabled(false);
            tv_editPlanBaruEdukasi.setFocusable(false);
            tv_editPlanBaruEdukasi.setCursorVisible(false);
            tv_editPlanBaruEdukasi.setTextColor(Color.parseColor("#919191"));
            sp_editPlanBaruTabletFE.setEnabled(false);
            sp_editPlanBaruJumObat.setEnabled(false);
            sp_editPlanBaruImunisasiTT.setEnabled(false);
            sp_editPlanBaruYangKe.setEnabled(false);
            Toast.makeText(EditKunjunganLamaDataPlanActivity.this, "Mode Edit Dinonaktifkan!", Toast.LENGTH_SHORT).show();
        }
    }

    private void loadDataPlanBaru() {
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

                                String tablet_fe = object.getString("tablet_fe").trim();
                                String jum_obat = object.getString("jum_obat").trim();
                                String imunisasi_tt = object.getString("imunisasi_tt").trim();
                                String yang_ke = object.getString("yang_ke").trim();
                                String terapi_lain = object.getString("terapi_lain").trim();
                                String edukasi = object.getString("edukasi").trim();

                                tv_editPlanBaruTerapiLain.setText(terapi_lain);
                                tv_editPlanBaruEdukasi.setText(edukasi);

                                ArrayAdapter<String> tabletFeAdapter = new ArrayAdapter<String>(EditKunjunganLamaDataPlanActivity.this,
                                        android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.ya_tidak));
                                tabletFeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                sp_editPlanBaruTabletFE.setAdapter(tabletFeAdapter);
                                if (tablet_fe != null) {
                                    int spinnerPositionTabletFe = tabletFeAdapter.getPosition(tablet_fe);
                                    sp_editPlanBaruTabletFE.setSelection(spinnerPositionTabletFe);
                                }

                                ArrayAdapter<String> jumObatAdapter = new ArrayAdapter<>(EditKunjunganLamaDataPlanActivity.this,
                                        android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.jum_obat));
                                jumObatAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                sp_editPlanBaruJumObat.setAdapter(jumObatAdapter);
                                if (jum_obat != null) {
                                    int spinnerPositionJumObat = jumObatAdapter.getPosition(jum_obat);
                                    sp_editPlanBaruJumObat.setSelection(spinnerPositionJumObat);
                                }

                                ArrayAdapter<String> imunisasiTTAdapter = new ArrayAdapter<String>(EditKunjunganLamaDataPlanActivity.this,
                                        android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.ya_tidak));
                                imunisasiTTAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                sp_editPlanBaruImunisasiTT.setAdapter(imunisasiTTAdapter);
                                if (imunisasi_tt != null) {
                                    int spinnerPositionImunisasiTT = imunisasiTTAdapter.getPosition(imunisasi_tt);
                                    sp_editPlanBaruImunisasiTT.setSelection(spinnerPositionImunisasiTT);
                                }

                                ArrayAdapter<String> yangKeAdapter = new ArrayAdapter<String>(EditKunjunganLamaDataPlanActivity.this,
                                        android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.yang_ke));
                                yangKeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                sp_editPlanBaruYangKe.setAdapter(yangKeAdapter);
                                if (yang_ke != null) {
                                    int spinnerPositionYangKe = yangKeAdapter.getPosition(yang_ke);
                                    sp_editPlanBaruYangKe.setSelection(spinnerPositionYangKe);
                                }

                                loadingDialog.dissmissDialog();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(EditKunjunganLamaDataPlanActivity.this, "Data Plan Tidak Ada!" + e.toString(), Toast.LENGTH_SHORT).show();
                            loadingDialog.dissmissDialog();
                        }

                    }
                }
                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(EditKunjunganLamaDataPlanActivity.this, "Koneksi Gagal!" + error.toString(), Toast.LENGTH_SHORT).show();
                loadingDialog.dissmissDialog();
            }
        }) {
        };
        RequestQueue requestQueue = Volley.newRequestQueue(EditKunjunganLamaDataPlanActivity.this);
        requestQueue.add(stringRequest);
    }
}
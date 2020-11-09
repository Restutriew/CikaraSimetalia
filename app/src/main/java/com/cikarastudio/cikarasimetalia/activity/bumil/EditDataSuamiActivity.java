package com.cikarastudio.cikarasimetalia.activity.bumil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
import com.cikarastudio.cikarasimetalia.dialog.LoadingDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class EditDataSuamiActivity extends AppCompatActivity {

    ImageView btn_backBapak, btn_toggleEditSuami;
    public static final String ID_BUMIL = "extra_data";
    LoadingDialog loadingDialog;
    String id_bumil;
    EditText tv_editSuamiNama, tv_editSuamiTempatLahir, tv_editSuamiTglLahir, tv_editSuamiPekerjaan;
    Spinner sp_editSuamiGoldar, sp_editSuamiAgama, sp_editSuamiPendidikan;
    LinearLayout btn_updateDataSuami;
    private static final String URL_UPDATESUAMI = "http://simetalia.com/android/update_suami.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data_suami);

        loadingDialog = new LoadingDialog(EditDataSuamiActivity.this);
        loadingDialog.startLoading();

        Intent intent = getIntent();
        id_bumil = intent.getStringExtra(ID_BUMIL);

        btn_backBapak = findViewById(R.id.btn_backEditBapa);
        btn_backBapak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        tv_editSuamiNama = findViewById(R.id.tv_editSuamiNama);
        tv_editSuamiTempatLahir = findViewById(R.id.tv_editSuamiTempatLahir);
        tv_editSuamiTglLahir = findViewById(R.id.tv_editSuamiTglLahir);
        tv_editSuamiPekerjaan = findViewById(R.id.tv_editSuamiPekerjaan);

        btn_toggleEditSuami = findViewById(R.id.btn_toggleEditSuami);

        btn_updateDataSuami = findViewById(R.id.btn_updateDataSuami);

        sp_editSuamiPendidikan = findViewById(R.id.sp_editSuamiPendidikan);
        sp_editSuamiGoldar = findViewById(R.id.sp_editSuamiGoldar);
        sp_editSuamiAgama = findViewById(R.id.sp_editSuamiAgama);

        sp_editSuamiPendidikan.setEnabled(false);
        sp_editSuamiAgama.setEnabled(false);
        sp_editSuamiGoldar.setEnabled(false);

        getDataSuami();

        btn_toggleEditSuami.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleSuami();
            }
        });
        btn_updateDataSuami.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cekFormSuami();
            }
        });

    }

    private void cekFormSuami() {
        String Snama = tv_editSuamiNama.getText().toString();
        String StempatLahir = tv_editSuamiTempatLahir.getText().toString();
        String StglLahir = tv_editSuamiTglLahir.getText().toString();
        String Spekerjaan = tv_editSuamiPekerjaan.getText().toString();

        if (Snama.equals("")) {
            tv_editSuamiNama.setError("Silahkan masukkan Nama!");
            tv_editSuamiNama.requestFocus();
        }else if (StempatLahir.equals("")) {
            tv_editSuamiTempatLahir.setError("Silahkan masukkan Tempat Lahir!");
            tv_editSuamiTempatLahir.requestFocus();
        }else if (StglLahir.equals("")) {
            tv_editSuamiTglLahir.setError("Silahkan masukkan Tanggal Lahir!");
            tv_editSuamiTglLahir.requestFocus();
        }else if (Spekerjaan.equals("")) {
            tv_editSuamiPekerjaan.setError("Silahkan masukkan Pekerjaan!");
            tv_editSuamiPekerjaan.requestFocus();
        }
        else{
            updateDataSuami();
        }
    }

    private void updateDataSuami() {
        loadingDialog.startLoading();
        final String s_nama = tv_editSuamiNama.getText().toString().trim();
        final String s_tempatlahir = tv_editSuamiTempatLahir.getText().toString().trim();

        String tglLahir = tv_editSuamiTglLahir.getText().toString().trim();
        String tanggal = tglLahir.substring(0, 2);
        String bulan = tglLahir.substring(3, 5);
        String tahun = tglLahir.substring(6, 10);
        final String s_tgllahir = tahun + "-" + bulan + "-" + tanggal;
        final String s_pekerjaan = tv_editSuamiPekerjaan.getText().toString().trim();

        final String s_goldar = sp_editSuamiGoldar.getSelectedItem().toString();
        final String s_agama = sp_editSuamiAgama.getSelectedItem().toString();
        final String s_pendidikan = sp_editSuamiPendidikan.getSelectedItem().toString();

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        final String updated_at = dateFormat.format(cal.getTime());

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_UPDATESUAMI,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")) {
                                Toast.makeText(EditDataSuamiActivity.this, "Update Suami Sukses", Toast.LENGTH_LONG).show();
                                loadingDialog.dissmissDialog();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(EditDataSuamiActivity.this, "Update Suami Gagal!"+e.toString(), Toast.LENGTH_LONG).show();
                            loadingDialog.dissmissDialog();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(EditDataSuamiActivity.this, "Update Suami Gagal! : Cek Koneksi Anda", Toast.LENGTH_LONG).show();
                        loadingDialog.dissmissDialog();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                params.put("id", id_bumil);
                params.put("s_nama", s_nama);
                params.put("s_tempatlahir", s_tempatlahir);
                params.put("s_tgllahir", s_tgllahir);
                params.put("s_pekerjaan", s_pekerjaan);
                params.put("s_goldar", s_goldar);
                params.put("s_agama", s_agama);
                params.put("s_pendidikan", s_pendidikan);
                params.put("updated_at", updated_at);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void toggleSuami() {
        if (!tv_editSuamiNama.isEnabled()) {
            tv_editSuamiNama.setEnabled(true);
            tv_editSuamiNama.setFocusable(true);
            tv_editSuamiNama.setCursorVisible(true);
            tv_editSuamiNama.setFocusableInTouchMode(true);
            tv_editSuamiNama.setTextColor(Color.parseColor("#000000"));
            tv_editSuamiTempatLahir.setEnabled(true);
            tv_editSuamiTempatLahir.setFocusable(true);
            tv_editSuamiTempatLahir.setCursorVisible(true);
            tv_editSuamiTempatLahir.setFocusableInTouchMode(true);
            tv_editSuamiTempatLahir.setTextColor(Color.parseColor("#000000"));
            tv_editSuamiTglLahir.setEnabled(true);
            tv_editSuamiTglLahir.setFocusable(true);
            tv_editSuamiTglLahir.setCursorVisible(true);
            tv_editSuamiTglLahir.setFocusableInTouchMode(true);
            tv_editSuamiTglLahir.setTextColor(Color.parseColor("#000000"));
            tv_editSuamiPekerjaan.setEnabled(true);
            tv_editSuamiPekerjaan.setFocusable(true);
            tv_editSuamiPekerjaan.setCursorVisible(true);
            tv_editSuamiPekerjaan.setFocusableInTouchMode(true);
            tv_editSuamiPekerjaan.setTextColor(Color.parseColor("#000000"));
            sp_editSuamiGoldar.setEnabled(true);
            sp_editSuamiPendidikan.setEnabled(true);
            sp_editSuamiAgama.setEnabled(true);
            Toast.makeText(EditDataSuamiActivity.this, "Mode Edit Diaktifkan!", Toast.LENGTH_SHORT).show();
        } else if (tv_editSuamiNama.isEnabled()) {
            tv_editSuamiNama.setEnabled(false);
            tv_editSuamiNama.setFocusable(false);
            tv_editSuamiNama.setCursorVisible(false);
            tv_editSuamiNama.setTextColor(Color.parseColor("#919191"));
            tv_editSuamiTempatLahir.setEnabled(false);
            tv_editSuamiTempatLahir.setFocusable(false);
            tv_editSuamiTempatLahir.setCursorVisible(false);
            tv_editSuamiTempatLahir.setTextColor(Color.parseColor("#919191"));
            tv_editSuamiTglLahir.setEnabled(false);
            tv_editSuamiTglLahir.setFocusable(false);
            tv_editSuamiTglLahir.setCursorVisible(false);
            tv_editSuamiTglLahir.setTextColor(Color.parseColor("#919191"));
            tv_editSuamiPekerjaan.setEnabled(false);
            tv_editSuamiPekerjaan.setFocusable(false);
            tv_editSuamiPekerjaan.setCursorVisible(false);
            tv_editSuamiPekerjaan.setTextColor(Color.parseColor("#919191"));
            sp_editSuamiAgama.setEnabled(false);
            sp_editSuamiPendidikan.setEnabled(false);
            sp_editSuamiGoldar.setEnabled(false);
            Toast.makeText(EditDataSuamiActivity.this, "Mode Edit Dinonaktifkan!", Toast.LENGTH_SHORT).show();
        }
    }

    private void getDataSuami() {
        String URL_READBOOKING = "http://simetalia.com/pkm/webservice/bumil/" + id_bumil;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_READBOOKING,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONObject object = jsonObject.getJSONObject("read");

                            if (success.equals("1")) {
                                String s_nama = object.getString("s_nama").trim();
                                String s_tempatlahir = object.getString("s_tempatlahir").trim();
                                String s_tgllahir = object.getString("s_tgllahir").trim();
                                String s_pekerjaan = object.getString("s_pekerjaan").trim();
                                String s_goldar = object.getString("s_goldar").trim();
                                String s_agama = object.getString("s_agama").trim();
                                String s_pendidikan = object.getString("s_pendidikan").trim();

                                tv_editSuamiNama.setText(s_nama);
                                tv_editSuamiTempatLahir.setText(s_tempatlahir);
                                tv_editSuamiTglLahir.setText(s_tgllahir);
                                tv_editSuamiPekerjaan.setText(s_pekerjaan);

                                ArrayAdapter<String> agamaSuamiAdapter = new ArrayAdapter<String>(EditDataSuamiActivity.this,
                                        android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.agama));
                                agamaSuamiAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                sp_editSuamiAgama.setAdapter(agamaSuamiAdapter);
                                if (s_agama != null) {
                                    int spinnerPositionAgama = agamaSuamiAdapter.getPosition(s_agama);
                                    sp_editSuamiAgama.setSelection(spinnerPositionAgama);
                                }

                                ArrayAdapter<String> goldarSuamiAdapter = new ArrayAdapter<>(EditDataSuamiActivity.this,
                                        android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.goldarah));
                                goldarSuamiAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                sp_editSuamiGoldar.setAdapter(goldarSuamiAdapter);
                                if (s_goldar != null) {
                                    int spinnerPositionGoldar = goldarSuamiAdapter.getPosition(s_goldar);
                                    sp_editSuamiGoldar.setSelection(spinnerPositionGoldar);
                                }

                                ArrayAdapter<String> pendidikanSuamiAdapter = new ArrayAdapter<String>(EditDataSuamiActivity.this,
                                        android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.pendidikan));
                                pendidikanSuamiAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                sp_editSuamiPendidikan.setAdapter(pendidikanSuamiAdapter);
                                if (s_pendidikan != null) {
                                    int spinnerPositionPendidikan = pendidikanSuamiAdapter.getPosition(s_pendidikan);
                                    sp_editSuamiPendidikan.setSelection(spinnerPositionPendidikan);
                                }

                                loadingDialog.dissmissDialog();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(EditDataSuamiActivity.this, "Data Suami Tidak Ada!" + e.toString(), Toast.LENGTH_SHORT).show();
                        }

                    }
                }
                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(EditDataSuamiActivity.this, "Koneksi Gagal!" + error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
        };
        RequestQueue requestQueue = Volley.newRequestQueue(EditDataSuamiActivity.this);
        requestQueue.add(stringRequest);
    }
}
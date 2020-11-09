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

public class EditDataKeluargaActivity extends AppCompatActivity {

    public static final String ID_BUMIL = "extra_data";
    ImageView btn_backKeluarga, btn_toggleEditLainnya;
    LoadingDialog loadingDialog;
    String id_bumil;
    EditText tv_editLainnyaAlamat, tv_editLainnyaNoJKN, tv_editLainnyaNoTelepon;
    Spinner sp_editLainnyaStatusEkonomi, sp_editLainnyaStatusBPJS;
    LinearLayout btn_updateDataLainnya;
    private static final String URL_UPDATELAINNYA = "http://simetalia.com/android/update_lainnya.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data_keluarga);

        loadingDialog = new LoadingDialog(EditDataKeluargaActivity.this);
        loadingDialog.startLoading();

        Intent intent = getIntent();
        id_bumil = intent.getStringExtra(ID_BUMIL);

        btn_backKeluarga = findViewById(R.id.btn_backEditKeluarga);
        btn_backKeluarga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        tv_editLainnyaAlamat = findViewById(R.id.tv_editLainnyaAlamat);
        tv_editLainnyaNoJKN = findViewById(R.id.tv_editLainnyaNoJKN);
        tv_editLainnyaNoTelepon = findViewById(R.id.tv_editLainnyaNoTelepon);
        sp_editLainnyaStatusBPJS = findViewById(R.id.sp_editLainnyaStatusBPJS);
        sp_editLainnyaStatusEkonomi = findViewById(R.id.sp_editLainnyaStatusEkonomi);
        btn_updateDataLainnya = findViewById(R.id.btn_updateDataLainnya);
        btn_toggleEditLainnya = findViewById(R.id.btn_toggleEditLainnya);

        sp_editLainnyaStatusEkonomi.setEnabled(false);
        sp_editLainnyaStatusBPJS.setEnabled(false);

        getDataLainnya();

        btn_toggleEditLainnya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleLainnya();
            }
        });

        btn_updateDataLainnya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cekFormLainnya();
            }
        });
    }

    private void cekFormLainnya() {
        String Salamat = tv_editLainnyaAlamat.getText().toString();
        String Sno_jkn = tv_editLainnyaNoJKN.getText().toString();
        String Sno_telp = tv_editLainnyaNoTelepon.getText().toString();

        if (Salamat.equals("")) {
            tv_editLainnyaAlamat.setError("Silahkan masukkan Alamat!");
            tv_editLainnyaAlamat.requestFocus();
        } else if (Sno_jkn.equals("")) {
            tv_editLainnyaNoJKN.setError("Silahkan masukkan Nomor JKN!");
            tv_editLainnyaNoJKN.requestFocus();
        } else if (Sno_telp.equals("")) {
            tv_editLainnyaNoTelepon.setError("Silahkan masukkan No Telepon!");
            tv_editLainnyaNoTelepon.requestFocus();
        } else {
            updateDataLainnya();
        }
    }

    private void updateDataLainnya() {
        loadingDialog.startLoading();
        final String alamat_bumil = tv_editLainnyaAlamat.getText().toString().trim();
        final String no_jkn = tv_editLainnyaNoJKN.getText().toString().trim();
        final String no_telp = tv_editLainnyaNoTelepon.getText().toString().trim();

        final String status_bumil = sp_editLainnyaStatusEkonomi.getSelectedItem().toString();
        final String status_bpjs = sp_editLainnyaStatusBPJS.getSelectedItem().toString();

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        final String updated_at = dateFormat.format(cal.getTime());

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_UPDATELAINNYA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")) {
                                Toast.makeText(EditDataKeluargaActivity.this, "Update Data Lainnya Sukses", Toast.LENGTH_LONG).show();
                                loadingDialog.dissmissDialog();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(EditDataKeluargaActivity.this, "Update Data Lainnya Gagal!"+e.toString(), Toast.LENGTH_LONG).show();
                            loadingDialog.dissmissDialog();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(EditDataKeluargaActivity.this, "Update Data Lainnya Gagal! : Cek Koneksi Anda", Toast.LENGTH_LONG).show();
                        loadingDialog.dissmissDialog();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                params.put("id", id_bumil);
                params.put("alamat_bumil", alamat_bumil);
                params.put("no_jkn", no_jkn);
                params.put("no_telp", no_telp);
                params.put("status_bumil", status_bumil);
                params.put("status_bpjs", status_bpjs);
                params.put("updated_at", updated_at);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    private void toggleLainnya() {
        if (!tv_editLainnyaAlamat.isEnabled()) {
            tv_editLainnyaAlamat.setEnabled(true);
            tv_editLainnyaAlamat.setFocusable(true);
            tv_editLainnyaAlamat.setCursorVisible(true);
            tv_editLainnyaAlamat.setFocusableInTouchMode(true);
            tv_editLainnyaAlamat.setTextColor(Color.parseColor("#000000"));
            tv_editLainnyaNoJKN.setEnabled(true);
            tv_editLainnyaNoJKN.setFocusable(true);
            tv_editLainnyaNoJKN.setCursorVisible(true);
            tv_editLainnyaNoJKN.setFocusableInTouchMode(true);
            tv_editLainnyaNoJKN.setTextColor(Color.parseColor("#000000"));
            tv_editLainnyaNoTelepon.setEnabled(true);
            tv_editLainnyaNoTelepon.setFocusable(true);
            tv_editLainnyaNoTelepon.setCursorVisible(true);
            tv_editLainnyaNoTelepon.setFocusableInTouchMode(true);
            tv_editLainnyaNoTelepon.setTextColor(Color.parseColor("#000000"));
            sp_editLainnyaStatusBPJS.setEnabled(true);
            sp_editLainnyaStatusEkonomi.setEnabled(true);
            Toast.makeText(EditDataKeluargaActivity.this, "Mode Edit Diaktifkan!", Toast.LENGTH_SHORT).show();
        } else if (tv_editLainnyaAlamat.isEnabled()) {
            tv_editLainnyaAlamat.setEnabled(false);
            tv_editLainnyaAlamat.setFocusable(false);
            tv_editLainnyaAlamat.setCursorVisible(false);
            tv_editLainnyaAlamat.setTextColor(Color.parseColor("#919191"));
            tv_editLainnyaNoJKN.setEnabled(false);
            tv_editLainnyaNoJKN.setFocusable(false);
            tv_editLainnyaNoJKN.setCursorVisible(false);
            tv_editLainnyaNoJKN.setTextColor(Color.parseColor("#919191"));
            tv_editLainnyaNoTelepon.setEnabled(false);
            tv_editLainnyaNoTelepon.setFocusable(false);
            tv_editLainnyaNoTelepon.setCursorVisible(false);
            tv_editLainnyaNoTelepon.setTextColor(Color.parseColor("#919191"));
            sp_editLainnyaStatusEkonomi.setEnabled(false);
            sp_editLainnyaStatusBPJS.setEnabled(false);
            Toast.makeText(EditDataKeluargaActivity.this, "Mode Edit Dinonaktifkan!", Toast.LENGTH_SHORT).show();
        }
    }

    private void getDataLainnya() {
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
                                String alamat_bumil = object.getString("alamat_bumil").trim();
                                String no_jkn = object.getString("no_jkn").trim();
                                String no_telp = object.getString("no_telp").trim();
                                String status_bumil = object.getString("status_bumil").trim();
                                String status_bpjs = object.getString("status_bpjs").trim();

                                tv_editLainnyaAlamat.setText(alamat_bumil);
                                tv_editLainnyaNoJKN.setText(no_jkn);
                                tv_editLainnyaNoTelepon.setText(no_telp);

                                ArrayAdapter<String> agamaSuamiAdapter = new ArrayAdapter<String>(EditDataKeluargaActivity.this,
                                        android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.status_ekonomi));
                                agamaSuamiAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                sp_editLainnyaStatusEkonomi.setAdapter(agamaSuamiAdapter);
                                if (status_bumil != null) {
                                    int spinnerPositionEkonomi = agamaSuamiAdapter.getPosition(status_bumil);
                                    sp_editLainnyaStatusEkonomi.setSelection(spinnerPositionEkonomi);
                                }

                                ArrayAdapter<String> goldarSuamiAdapter = new ArrayAdapter<>(EditDataKeluargaActivity.this,
                                        android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.status_bpjs));
                                goldarSuamiAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                sp_editLainnyaStatusBPJS.setAdapter(goldarSuamiAdapter);
                                if (status_bpjs != null) {
                                    int spinnerPositionBPJS = goldarSuamiAdapter.getPosition(status_bpjs);
                                    sp_editLainnyaStatusBPJS.setSelection(spinnerPositionBPJS);
                                }


                                loadingDialog.dissmissDialog();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(EditDataKeluargaActivity.this, "Data Suami Tidak Ada!" + e.toString(), Toast.LENGTH_SHORT).show();
                        }

                    }
                }
                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(EditDataKeluargaActivity.this, "Koneksi Gagal!" + error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
        };
        RequestQueue requestQueue = Volley.newRequestQueue(EditDataKeluargaActivity.this);
        requestQueue.add(stringRequest);
    }
}
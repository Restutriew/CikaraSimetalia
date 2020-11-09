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

public class EditKunjunganBaruDataAnalisisActivity extends AppCompatActivity {

    public static final String ID_KUNJUNGANBARU = "extra_data";
    String id_kunjunganBaru;
    LoadingDialog loadingDialog;
    EditText tv_editAnalisisBaruAnalisis;
    ImageView btn_toggleEditAnalisisBaru;
    LinearLayout btn_updateDataAnalisisBaru;
    private static final String URL_UPDATEAANALISISBARU = "http://simetalia.com/android/update_analisis_baru.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_kunjungan_baru_data_analisis);

        loadingDialog = new LoadingDialog(EditKunjunganBaruDataAnalisisActivity.this);
        loadingDialog.startLoading();

        Intent intent = getIntent();
        id_kunjunganBaru = intent.getStringExtra(ID_KUNJUNGANBARU);

        tv_editAnalisisBaruAnalisis = findViewById(R.id.tv_editAnalisisBaruAnalisis);
        btn_toggleEditAnalisisBaru = findViewById(R.id.btn_toggleEditAnalisisBaru);
        btn_updateDataAnalisisBaru = findViewById(R.id.btn_updateDataAnalisisBaru);

        loadDataAnalisisBaru();

        btn_toggleEditAnalisisBaru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleDataAnalisis();
            }
        });

        btn_updateDataAnalisisBaru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateDataAnalisis();
            }
        });

    }

    private void updateDataAnalisis() {
        loadingDialog.startLoading();
        final String analisis = tv_editAnalisisBaruAnalisis.getText().toString().trim();

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        final String updated_at = dateFormat.format(cal.getTime());

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_UPDATEAANALISISBARU,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")) {
                                Toast.makeText(EditKunjunganBaruDataAnalisisActivity.this, "Update Data Analisis Berhasil", Toast.LENGTH_LONG).show();
                                loadingDialog.dissmissDialog();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(EditKunjunganBaruDataAnalisisActivity.this, "Update Data Analisis Gagal!", Toast.LENGTH_LONG).show();
                            loadingDialog.dissmissDialog();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(EditKunjunganBaruDataAnalisisActivity.this, "Update Data Analisis Gagal! : Cek Koneksi Anda", Toast.LENGTH_LONG).show();
                        loadingDialog.dissmissDialog();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                params.put("id", id_kunjunganBaru);
                params.put("analisis", analisis);
                params.put("updated_at", updated_at);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void toggleDataAnalisis() {
        if (!tv_editAnalisisBaruAnalisis.isEnabled()) {
            tv_editAnalisisBaruAnalisis.setEnabled(true);
            tv_editAnalisisBaruAnalisis.setFocusable(true);
            tv_editAnalisisBaruAnalisis.setCursorVisible(true);
            tv_editAnalisisBaruAnalisis.setFocusableInTouchMode(true);
            tv_editAnalisisBaruAnalisis.setTextColor(Color.parseColor("#000000"));
            Toast.makeText(EditKunjunganBaruDataAnalisisActivity.this, "Mode Edit Diaktifkan!", Toast.LENGTH_LONG).show();
        } else if (tv_editAnalisisBaruAnalisis.isEnabled()) {
            tv_editAnalisisBaruAnalisis.setEnabled(false);
            tv_editAnalisisBaruAnalisis.setFocusable(false);
            tv_editAnalisisBaruAnalisis.setCursorVisible(false);
            tv_editAnalisisBaruAnalisis.setTextColor(Color.parseColor("#919191"));
            Toast.makeText(EditKunjunganBaruDataAnalisisActivity.this, "Mode Edit Dinonaktifkan!", Toast.LENGTH_SHORT).show();
        }
    }

    private void loadDataAnalisisBaru() {
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

                                String analisis = object.getString("analisis").trim();

                                tv_editAnalisisBaruAnalisis.setText(analisis);

                                loadingDialog.dissmissDialog();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(EditKunjunganBaruDataAnalisisActivity.this, "Data Analisis Tidak Ada!" + e.toString(), Toast.LENGTH_SHORT).show();
                            loadingDialog.dissmissDialog();
                        }

                    }
                }
                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(EditKunjunganBaruDataAnalisisActivity.this, "Koneksi Gagal!" + error.toString(), Toast.LENGTH_SHORT).show();
                loadingDialog.dissmissDialog();
            }
        }) {
        };
        RequestQueue requestQueue = Volley.newRequestQueue(EditKunjunganBaruDataAnalisisActivity.this);
        requestQueue.add(stringRequest);
    }
}
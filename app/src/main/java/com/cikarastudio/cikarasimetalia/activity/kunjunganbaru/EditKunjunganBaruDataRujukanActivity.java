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

public class EditKunjunganBaruDataRujukanActivity extends AppCompatActivity {

    public static final String ID_KUNJUNGANBARU = "extra_data";
    String id_kunjunganBaru, tanggal_rujukan;
    LoadingDialog loadingDialog;
    EditText tv_editRujukanBaruTanggalRujukan, tv_editRujukanBaruNamaFaskes, tv_editRUjukanBaruAlasanDirujuk,
            tv_editRUjukanDiagnosaSementara, tv_editRujukanBaruTindakanSementara;
    Spinner sp_editRujukanBaruFaskes;
    ImageView btn_toggleEditRujukanBaru;
    LinearLayout btn_updateDataRujukanBaru;
    private static final String URL_UPDATERUJUKANBARU = "http://simetalia.com/android/update_rujukan_baru.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_kunjungan_baru_data_rujukan);

        loadingDialog = new LoadingDialog(EditKunjunganBaruDataRujukanActivity.this);
        loadingDialog.startLoading();

        Intent intent = getIntent();
        id_kunjunganBaru = intent.getStringExtra(ID_KUNJUNGANBARU);

        tv_editRujukanBaruTanggalRujukan = findViewById(R.id.tv_editRujukanBaruTanggalRujukan);
        sp_editRujukanBaruFaskes = findViewById(R.id.sp_editRujukanBaruFaskes);
        tv_editRujukanBaruNamaFaskes = findViewById(R.id.tv_editRujukanBaruNamaFaskes);
        tv_editRUjukanBaruAlasanDirujuk = findViewById(R.id.tv_editRUjukanBaruAlasanDirujuk);
        tv_editRUjukanDiagnosaSementara = findViewById(R.id.tv_editRUjukanDiagnosaSementara);
        tv_editRujukanBaruTindakanSementara = findViewById(R.id.tv_editRujukanBaruTindakanSementara);
        btn_toggleEditRujukanBaru = findViewById(R.id.btn_toggleEditRujukanBaru);
        btn_updateDataRujukanBaru = findViewById(R.id.btn_updateDataRujukanBaru);

        sp_editRujukanBaruFaskes.setEnabled(false);

        loadDataRujukanBaru();

        btn_toggleEditRujukanBaru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleRUjukanBaru();
            }
        });

        btn_updateDataRujukanBaru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateDataRujukanBaru();
            }
        });

    }

    private void updateDataRujukanBaru() {
        loadingDialog.startLoading();
        final String tanggal_rujukanawal = tv_editRujukanBaruTanggalRujukan.getText().toString().trim();
        if (tanggal_rujukanawal.equals("")) {
            tanggal_rujukan = "";
        } else {
            String tanggaltengahtanggal_rujukan = tanggal_rujukanawal.substring(0, 2);
            String bulantengahtanggal_rujukan = tanggal_rujukanawal.substring(3, 5);
            String tahuntengahtanggal_rujukan = tanggal_rujukanawal.substring(6, 10);
            tanggal_rujukan = tahuntengahtanggal_rujukan + "-" + bulantengahtanggal_rujukan + "-" + tanggaltengahtanggal_rujukan;
        }

        final String nama_faskes = tv_editRujukanBaruNamaFaskes.getText().toString().trim();
        final String alasan_dirujuk = tv_editRUjukanBaruAlasanDirujuk.getText().toString().trim();
        final String diagnosa_sementara = tv_editRUjukanDiagnosaSementara.getText().toString().trim();
        final String tindakan_sementara = tv_editRujukanBaruTindakanSementara.getText().toString().trim();

        final String faskes = sp_editRujukanBaruFaskes.getSelectedItem().toString();

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        final String updated_at = dateFormat.format(cal.getTime());

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_UPDATERUJUKANBARU,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")) {
                                Toast.makeText(EditKunjunganBaruDataRujukanActivity.this, "Update Data Rujukan Berhasil", Toast.LENGTH_LONG).show();
                                loadingDialog.dissmissDialog();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(EditKunjunganBaruDataRujukanActivity.this, "Update Data Rujukan Gagal!", Toast.LENGTH_LONG).show();
                            loadingDialog.dissmissDialog();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(EditKunjunganBaruDataRujukanActivity.this, "Update Data Rujukan Gagal! : Cek Koneksi Anda", Toast.LENGTH_LONG).show();
                        loadingDialog.dissmissDialog();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                params.put("id", id_kunjunganBaru);
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

    private void toggleRUjukanBaru() {
        if (!tv_editRujukanBaruTanggalRujukan.isEnabled()) {
            tv_editRujukanBaruTanggalRujukan.setEnabled(true);
            tv_editRujukanBaruTanggalRujukan.setFocusable(true);
            tv_editRujukanBaruTanggalRujukan.setCursorVisible(true);
            tv_editRujukanBaruTanggalRujukan.setFocusableInTouchMode(true);
            tv_editRujukanBaruTanggalRujukan.setTextColor(Color.parseColor("#000000"));
            tv_editRujukanBaruNamaFaskes.setEnabled(true);
            tv_editRujukanBaruNamaFaskes.setFocusable(true);
            tv_editRujukanBaruNamaFaskes.setCursorVisible(true);
            tv_editRujukanBaruNamaFaskes.setFocusableInTouchMode(true);
            tv_editRujukanBaruNamaFaskes.setTextColor(Color.parseColor("#000000"));
            tv_editRUjukanBaruAlasanDirujuk.setEnabled(true);
            tv_editRUjukanBaruAlasanDirujuk.setFocusable(true);
            tv_editRUjukanBaruAlasanDirujuk.setCursorVisible(true);
            tv_editRUjukanBaruAlasanDirujuk.setFocusableInTouchMode(true);
            tv_editRUjukanBaruAlasanDirujuk.setTextColor(Color.parseColor("#000000"));
            tv_editRUjukanDiagnosaSementara.setEnabled(true);
            tv_editRUjukanDiagnosaSementara.setFocusable(true);
            tv_editRUjukanDiagnosaSementara.setCursorVisible(true);
            tv_editRUjukanDiagnosaSementara.setFocusableInTouchMode(true);
            tv_editRUjukanDiagnosaSementara.setTextColor(Color.parseColor("#000000"));
            tv_editRujukanBaruTindakanSementara.setEnabled(true);
            tv_editRujukanBaruTindakanSementara.setFocusable(true);
            tv_editRujukanBaruTindakanSementara.setCursorVisible(true);
            tv_editRujukanBaruTindakanSementara.setFocusableInTouchMode(true);
            tv_editRujukanBaruTindakanSementara.setTextColor(Color.parseColor("#000000"));
            sp_editRujukanBaruFaskes.setEnabled(true);
            Toast.makeText(EditKunjunganBaruDataRujukanActivity.this, "Mode Edit Diaktifkan!", Toast.LENGTH_LONG).show();
        } else if (tv_editRujukanBaruTanggalRujukan.isEnabled()) {
            tv_editRujukanBaruTanggalRujukan.setEnabled(false);
            tv_editRujukanBaruTanggalRujukan.setFocusable(false);
            tv_editRujukanBaruTanggalRujukan.setCursorVisible(false);
            tv_editRujukanBaruTanggalRujukan.setTextColor(Color.parseColor("#919191"));
            tv_editRujukanBaruNamaFaskes.setEnabled(false);
            tv_editRujukanBaruNamaFaskes.setFocusable(false);
            tv_editRujukanBaruNamaFaskes.setCursorVisible(false);
            tv_editRujukanBaruNamaFaskes.setTextColor(Color.parseColor("#919191"));
            tv_editRUjukanBaruAlasanDirujuk.setEnabled(false);
            tv_editRUjukanBaruAlasanDirujuk.setFocusable(false);
            tv_editRUjukanBaruAlasanDirujuk.setCursorVisible(false);
            tv_editRUjukanBaruAlasanDirujuk.setTextColor(Color.parseColor("#919191"));
            tv_editRUjukanDiagnosaSementara.setEnabled(false);
            tv_editRUjukanDiagnosaSementara.setFocusable(false);
            tv_editRUjukanDiagnosaSementara.setCursorVisible(false);
            tv_editRUjukanDiagnosaSementara.setTextColor(Color.parseColor("#919191"));
            tv_editRujukanBaruTindakanSementara.setEnabled(false);
            tv_editRujukanBaruTindakanSementara.setFocusable(false);
            tv_editRujukanBaruTindakanSementara.setCursorVisible(false);
            tv_editRujukanBaruTindakanSementara.setTextColor(Color.parseColor("#919191"));
            sp_editRujukanBaruFaskes.setEnabled(false);
            Toast.makeText(EditKunjunganBaruDataRujukanActivity.this, "Mode Edit Dinonaktifkan!", Toast.LENGTH_SHORT).show();
        }
    }

    private void loadDataRujukanBaru() {
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

                                String tanggal_rujukan = object.getString("tanggal_rujukan").trim();
                                String faskes = object.getString("faskes").trim();
                                String nama_faskes = object.getString("nama_faskes").trim();
                                String alasan_dirujuk = object.getString("alasan_dirujuk").trim();
                                String diagnosa_sementara = object.getString("diagnosa_sementara").trim();
                                String tindakan_sementara = object.getString("tindakan_sementara").trim();

                                tv_editRujukanBaruTanggalRujukan.setText(tanggal_rujukan);
                                tv_editRujukanBaruNamaFaskes.setText(nama_faskes);
                                tv_editRUjukanBaruAlasanDirujuk.setText(alasan_dirujuk);
                                tv_editRUjukanDiagnosaSementara.setText(diagnosa_sementara);
                                tv_editRujukanBaruTindakanSementara.setText(tindakan_sementara);

                                ArrayAdapter<String> faskesAdapter = new ArrayAdapter<String>(EditKunjunganBaruDataRujukanActivity.this,
                                        android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.faskes));
                                faskesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                sp_editRujukanBaruFaskes.setAdapter(faskesAdapter);
                                if (faskes != null) {
                                    int spinnerPositionFaskes = faskesAdapter.getPosition(faskes);
                                    sp_editRujukanBaruFaskes.setSelection(spinnerPositionFaskes);
                                }

                                loadingDialog.dissmissDialog();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(EditKunjunganBaruDataRujukanActivity.this, "Data Ibu Rujukan Tidak Ada!" + e.toString(), Toast.LENGTH_SHORT).show();
                            loadingDialog.dissmissDialog();
                        }

                    }
                }
                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(EditKunjunganBaruDataRujukanActivity.this, "Koneksi Gagal!" + error.toString(), Toast.LENGTH_SHORT).show();
                loadingDialog.dissmissDialog();
            }
        }) {
        };
        RequestQueue requestQueue = Volley.newRequestQueue(EditKunjunganBaruDataRujukanActivity.this);
        requestQueue.add(stringRequest);
    }
}
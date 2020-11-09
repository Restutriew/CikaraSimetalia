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

public class EditIbuHamilActivity extends AppCompatActivity {

    public static final String ID_BUMIL = "extra_data";
    LoadingDialog loadingDialog;
    ImageView btn_backEditBumil, btn_toggleEditBumil;
    String id_bumil;
    EditText tv_editBumilNIK, tv_editBumilNama, tv_editBumilTempatLahir, tv_editBumilTglLahir, tv_editBumilPekerjaan;
    Spinner sp_editBumilGolDarah, sp_editBumilAgama, sp_editBumilPendidikan;
    LinearLayout btn_updateDataBumil;
    private static final String URL_UPDATEBUMIL = "http://simetalia.com/android/update_bumil.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_ibu_hamil);

        loadingDialog = new LoadingDialog(EditIbuHamilActivity.this);
        loadingDialog.startLoading();

        Intent intent = getIntent();
        id_bumil = intent.getStringExtra(ID_BUMIL);

        btn_backEditBumil = findViewById(R.id.btn_backEditBumil);
        btn_backEditBumil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        tv_editBumilNIK = findViewById(R.id.tv_editBumilNIK);
        tv_editBumilNama = findViewById(R.id.tv_editBumilNama);
        tv_editBumilTempatLahir = findViewById(R.id.tv_editBumilTempatLahir);
        tv_editBumilTglLahir = findViewById(R.id.tv_editBumilTglLahir);
        tv_editBumilPekerjaan = findViewById(R.id.tv_editBumilPekerjaan);
        sp_editBumilGolDarah = findViewById(R.id.sp_editBumilGolDarah);
        sp_editBumilAgama = findViewById(R.id.sp_editBumilAgama);
        sp_editBumilPendidikan = findViewById(R.id.sp_editBumilPendidikan);
        btn_toggleEditBumil = findViewById(R.id.btn_toggleEditBumil);

        btn_updateDataBumil = findViewById(R.id.btn_updateDataBumil);

        sp_editBumilPendidikan.setEnabled(false);
        sp_editBumilAgama.setEnabled(false);
        sp_editBumilGolDarah.setEnabled(false);

        getDataBumil();

        btn_toggleEditBumil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleBumil();
            }
        });

        btn_updateDataBumil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cekFormEditBumil();
            }
        });

    }

    private void cekFormEditBumil() {
        String Snik = tv_editBumilNIK.getText().toString();
        String Snama = tv_editBumilNama.getText().toString();
        String StempatLahir = tv_editBumilTempatLahir.getText().toString();
        String StglLahir = tv_editBumilTglLahir.getText().toString();
        String Spekerjaan = tv_editBumilPekerjaan.getText().toString();

        if (Snik.equals("")) {
            tv_editBumilNIK.setError("Silahkan masukkan NIK!");
            tv_editBumilNIK.requestFocus();
        } else if (Snama.equals("")) {
            tv_editBumilNama.setError("Silahkan masukkan Nama!");
            tv_editBumilNama.requestFocus();
        }else if (StempatLahir.equals("")) {
            tv_editBumilTempatLahir.setError("Silahkan masukkan Tempat Lahir!");
            tv_editBumilTempatLahir.requestFocus();
        }else if (StglLahir.equals("")) {
            tv_editBumilTglLahir.setError("Silahkan masukkan Tanggal Lahir!");
            tv_editBumilTglLahir.requestFocus();
        }else if (Spekerjaan.equals("")) {
            tv_editBumilPekerjaan.setError("Silahkan masukkan Pekerjaan!");
            tv_editBumilPekerjaan.requestFocus();
        }
        else{
            updateBumil();
        }
    }

    private void updateBumil() {
        loadingDialog.startLoading();
        final String nikBumil = tv_editBumilNIK.getText().toString().trim();
        final String namaBumil = tv_editBumilNama.getText().toString().trim();
        final String tempatLahirBumil = tv_editBumilTempatLahir.getText().toString().trim();

        String tglLahir = tv_editBumilTglLahir.getText().toString().trim();
        String tanggal = tglLahir.substring(0, 2);
        String bulan = tglLahir.substring(3, 5);
        String tahun = tglLahir.substring(6, 10);
        final String tglLahirFixBumil = tahun + "-" + bulan + "-" + tanggal;
        final String goldarBumil = sp_editBumilGolDarah.getSelectedItem().toString();
        final String agamaBumil = sp_editBumilAgama.getSelectedItem().toString();
        final String pekerjaanBumil = tv_editBumilPekerjaan.getText().toString().trim();
        final String pendidikanBumil = sp_editBumilPendidikan.getSelectedItem().toString();

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        final String tanggalSekarang = dateFormat.format(cal.getTime());

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_UPDATEBUMIL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")) {
                                Toast.makeText(EditIbuHamilActivity.this, "Tambah Ibu Hamil Sukses", Toast.LENGTH_LONG).show();
                                loadingDialog.dissmissDialog();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(EditIbuHamilActivity.this, "Tambah Data Ibu Hamil Gagal!", Toast.LENGTH_LONG).show();
                            loadingDialog.dissmissDialog();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(EditIbuHamilActivity.this, "Tambah Data Bumil Gagal! : Cek Koneksi Anda", Toast.LENGTH_LONG).show();
                        loadingDialog.dissmissDialog();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                params.put("id", id_bumil);
                params.put("nik", nikBumil);
                params.put("nama_bumil", namaBumil);
                params.put("tempat_lahir", tempatLahirBumil);
                params.put("tgl_lahir", tglLahirFixBumil);
                params.put("goldar", goldarBumil);
                params.put("agama", agamaBumil);
                params.put("pekerjaan", pekerjaanBumil);
                params.put("pendidikan", pendidikanBumil);
                params.put("updated_at", tanggalSekarang);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void toggleBumil() {
        if (!tv_editBumilNIK.isEnabled()) {
            tv_editBumilNIK.setEnabled(true);
            tv_editBumilNIK.setFocusable(true);
            tv_editBumilNIK.setCursorVisible(true);
            tv_editBumilNIK.setFocusableInTouchMode(true);
            tv_editBumilNIK.setTextColor(Color.parseColor("#000000"));
            tv_editBumilNama.setEnabled(true);
            tv_editBumilNama.setFocusable(true);
            tv_editBumilNama.setCursorVisible(true);
            tv_editBumilNama.setFocusableInTouchMode(true);
            tv_editBumilNama.setTextColor(Color.parseColor("#000000"));
            tv_editBumilPekerjaan.setEnabled(true);
            tv_editBumilPekerjaan.setFocusable(true);
            tv_editBumilPekerjaan.setCursorVisible(true);
            tv_editBumilPekerjaan.setFocusableInTouchMode(true);
            tv_editBumilPekerjaan.setTextColor(Color.parseColor("#000000"));
            tv_editBumilTglLahir.setEnabled(true);
            tv_editBumilTglLahir.setFocusable(true);
            tv_editBumilTglLahir.setCursorVisible(true);
            tv_editBumilTglLahir.setFocusableInTouchMode(true);
            tv_editBumilTglLahir.setTextColor(Color.parseColor("#000000"));
            tv_editBumilTempatLahir.setEnabled(true);
            tv_editBumilTempatLahir.setFocusable(true);
            tv_editBumilTempatLahir.setCursorVisible(true);
            tv_editBumilTempatLahir.setFocusableInTouchMode(true);
            tv_editBumilTempatLahir.setTextColor(Color.parseColor("#000000"));
            sp_editBumilGolDarah.setEnabled(true);
            sp_editBumilPendidikan.setEnabled(true);
            sp_editBumilAgama.setEnabled(true);
            Toast.makeText(EditIbuHamilActivity.this, "Mode Edit Diaktifkan!", Toast.LENGTH_LONG).show();
        } else if (tv_editBumilNIK.isEnabled()) {
            tv_editBumilNIK.setEnabled(false);
            tv_editBumilNIK.setFocusable(false);
            tv_editBumilNIK.setCursorVisible(false);
            tv_editBumilNIK.setTextColor(Color.parseColor("#919191"));
            tv_editBumilNama.setEnabled(false);
            tv_editBumilNama.setFocusable(false);
            tv_editBumilNama.setCursorVisible(false);
            tv_editBumilNama.setTextColor(Color.parseColor("#919191"));
            tv_editBumilPekerjaan.setEnabled(false);
            tv_editBumilPekerjaan.setFocusable(false);
            tv_editBumilPekerjaan.setCursorVisible(false);
            tv_editBumilPekerjaan.setTextColor(Color.parseColor("#919191"));
            tv_editBumilTglLahir.setEnabled(false);
            tv_editBumilTglLahir.setFocusable(false);
            tv_editBumilTglLahir.setCursorVisible(false);
            tv_editBumilTglLahir.setTextColor(Color.parseColor("#919191"));
            tv_editBumilTempatLahir.setEnabled(false);
            tv_editBumilTempatLahir.setFocusable(false);
            tv_editBumilTempatLahir.setCursorVisible(false);
            tv_editBumilTempatLahir.setTextColor(Color.parseColor("#919191"));
            sp_editBumilAgama.setEnabled(false);
            sp_editBumilPendidikan.setEnabled(false);
            sp_editBumilGolDarah.setEnabled(false);
            Toast.makeText(EditIbuHamilActivity.this, "Mode Edit Dinonaktifkan!", Toast.LENGTH_SHORT).show();
        }
    }

    private void getDataBumil() {
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

                                String nik = object.getString("nik").trim();
                                String nama_bumil = object.getString("nama_bumil").trim();
                                String pendidikan = object.getString("pendidikan").trim();
                                String pekerjaan = object.getString("pekerjaan").trim();
                                String tgl_lahir = object.getString("tgl_lahir").trim();
                                String agama = object.getString("agama").trim();
                                String tempat_lahir = object.getString("tempat_lahir").trim();
                                String goldar = object.getString("goldar").trim();

                                tv_editBumilNIK.setText(nik);
                                tv_editBumilNama.setText(nama_bumil);
                                tv_editBumilPekerjaan.setText(pekerjaan);
                                tv_editBumilTglLahir.setText(tgl_lahir);
                                tv_editBumilTempatLahir.setText(tempat_lahir);


                                ArrayAdapter<String> agamaAdapter = new ArrayAdapter<String>(EditIbuHamilActivity.this,
                                        android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.agama));
                                agamaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                sp_editBumilAgama.setAdapter(agamaAdapter);
                                if (agama != null) {
                                    int spinnerPositionAgama = agamaAdapter.getPosition(agama);
                                    sp_editBumilAgama.setSelection(spinnerPositionAgama);
                                }

                                ArrayAdapter<String> goldarAdapter = new ArrayAdapter<>(EditIbuHamilActivity.this,
                                        android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.goldarah));
                                goldarAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                sp_editBumilGolDarah.setAdapter(goldarAdapter);
                                if (goldar != null) {
                                    int spinnerPositionGoldar = goldarAdapter.getPosition(goldar);
                                    sp_editBumilGolDarah.setSelection(spinnerPositionGoldar);
                                }

                                ArrayAdapter<String> pendidikanAdapter = new ArrayAdapter<String>(EditIbuHamilActivity.this,
                                        android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.pendidikan));
                                pendidikanAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                sp_editBumilPendidikan.setAdapter(pendidikanAdapter);
                                if (pendidikan != null) {
                                    int spinnerPositionPendidikan = pendidikanAdapter.getPosition(pendidikan);
                                    sp_editBumilPendidikan.setSelection(spinnerPositionPendidikan);
                                }

                                loadingDialog.dissmissDialog();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(EditIbuHamilActivity.this, "Data Ibu Hamil Tidak Ada!" + e.toString(), Toast.LENGTH_SHORT).show();
                        }

                    }
                }
                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(EditIbuHamilActivity.this, "Koneksi Gagal!" + error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
        };
        RequestQueue requestQueue = Volley.newRequestQueue(EditIbuHamilActivity.this);
        requestQueue.add(stringRequest);
    }
}

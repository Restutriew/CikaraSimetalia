package com.cikarastudio.cikarasimetalia.activity.kehamilan;

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
import com.cikarastudio.cikarasimetalia.activity.kunjunganlama.EditKunjunganLamaDataP4KActivity;
import com.cikarastudio.cikarasimetalia.activity.skrinning.TambahDataScreeningActivity;
import com.cikarastudio.cikarasimetalia.dialog.LoadingDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class TambahDataPersalinanActivity extends AppCompatActivity {

    public static final String ID_KUNJUNGANBARU = "extra_data";
    LoadingDialog loadingDialog;
    String id_kunjunganBaru, tanggal, tanggal_rujukan;
    EditText tv_editPersalinanTanggal, tv_editPersalinanNamaPenolong, tv_editPersalinanNamaTempat,
            tv_editPersalinanKeteranganTindakan, tv_editPersalinanKomplikasi, tv_editPersalinanTanggalRujukan,
            tv_editPersalinanNamaFaskes, tv_editPersalinanAlasanDirujuk, tv_editPersalinanDiagnosaSementara,
            tv_editPersalinanTindakanSementara;
    Spinner sp_editPersalinanPenolong, sp_editPersalinanTempat, sp_editPersalinanJenisPersalinan,
            sp_editPersalinanTindakan, sp_editPersalinanKeadaanIbu, sp_editPersalinanFaskes;
    ImageView btn_toggleEditPersalinan;
    LinearLayout btn_updateDataPersalinan;
    private static final String URL_UPDATEPERSALINAN = "http://simetalia.com/android/tambah_update_persalinan.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_data_persalinan);

        loadingDialog = new LoadingDialog(TambahDataPersalinanActivity.this);
        loadingDialog.startLoading();

        Intent intent = getIntent();
        id_kunjunganBaru = intent.getStringExtra(ID_KUNJUNGANBARU);

        tv_editPersalinanTanggal = findViewById(R.id.tv_editPersalinanTanggal);
        sp_editPersalinanPenolong = findViewById(R.id.sp_editPersalinanPenolong);
        tv_editPersalinanNamaPenolong = findViewById(R.id.tv_editPersalinanNamaPenolong);
        sp_editPersalinanTempat = findViewById(R.id.sp_editPersalinanTempat);
        tv_editPersalinanNamaTempat = findViewById(R.id.tv_editPersalinanNamaTempat);
        sp_editPersalinanJenisPersalinan = findViewById(R.id.sp_editPersalinanJenisPersalinan);
        sp_editPersalinanTindakan = findViewById(R.id.sp_editPersalinanTindakan);
        tv_editPersalinanKeteranganTindakan = findViewById(R.id.tv_editPersalinanKeteranganTindakan);
        tv_editPersalinanKomplikasi = findViewById(R.id.tv_editPersalinanKomplikasi);
        sp_editPersalinanKeadaanIbu = findViewById(R.id.sp_editPersalinanKeadaanIbu);
        tv_editPersalinanTanggalRujukan = findViewById(R.id.tv_editPersalinanTanggalRujukan);
        sp_editPersalinanFaskes = findViewById(R.id.sp_editPersalinanFaskes);
        tv_editPersalinanNamaFaskes = findViewById(R.id.tv_editPersalinanNamaFaskes);
        tv_editPersalinanAlasanDirujuk = findViewById(R.id.tv_editPersalinanAlasanDirujuk);
        tv_editPersalinanDiagnosaSementara = findViewById(R.id.tv_editPersalinanDiagnosaSementara);
        tv_editPersalinanTindakanSementara = findViewById(R.id.tv_editPersalinanTindakanSementara);
        btn_toggleEditPersalinan = findViewById(R.id.btn_toggleEditPersalinan);
        btn_updateDataPersalinan = findViewById(R.id.btn_updateDataPersalinan);

        sp_editPersalinanPenolong.setEnabled(false);
        sp_editPersalinanTempat.setEnabled(false);
        sp_editPersalinanJenisPersalinan.setEnabled(false);
        sp_editPersalinanTindakan.setEnabled(false);
        sp_editPersalinanKeadaanIbu.setEnabled(false);
        sp_editPersalinanFaskes.setEnabled(false);

        spinnerPersalinan();
        loadDataPersalinan();

        btn_toggleEditPersalinan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                togglePersalinan();
            }
        });

        btn_updateDataPersalinan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cekFormPersalinan();
            }
        });
    }

    private void cekFormPersalinan() {
        String cekTanggal = tv_editPersalinanTanggal.getText().toString().trim();
        if (cekTanggal.length() == 10) {
            updateDataPersalinan();
        } else {
            tv_editPersalinanTanggal.setError("Silahkan Masukkan Tanggal Dengan Benar!");
            tv_editPersalinanTanggal.requestFocus();
        }
    }

    private void updateDataPersalinan() {
        loadingDialog.startLoading();
        String tanggalawal = tv_editPersalinanTanggal.getText().toString().trim();
        String tanggaltengahtanggal = tanggalawal.substring(0, 2);
        String bulantengahtanggal = tanggalawal.substring(3, 5);
        String tahuntengahtanggal = tanggalawal.substring(6, 10);
        tanggal = tahuntengahtanggal + "-" + bulantengahtanggal + "-" + tanggaltengahtanggal;


        final String tanggalrujukanawal = tv_editPersalinanTanggalRujukan.getText().toString().trim();
        if (tanggalrujukanawal.length() < 10) {
            tanggal_rujukan = "";
        } else {
            String tanggaltengahrujukan = tanggalrujukanawal.substring(0, 2);
            String bulantengahrujukan = tanggalrujukanawal.substring(3, 5);
            String tahuntengahrujukan = tanggalrujukanawal.substring(6, 10);
            tanggal_rujukan = tahuntengahrujukan + "-" + bulantengahrujukan + "-" + tanggaltengahrujukan;
        }

        final String nama_penolong = tv_editPersalinanNamaPenolong.getText().toString().trim();
        final String nama_tempat = tv_editPersalinanNamaTempat.getText().toString().trim();
        final String keterangan_tindakan = tv_editPersalinanKeteranganTindakan.getText().toString().trim();
        final String komplikasi = tv_editPersalinanKomplikasi.getText().toString().trim();
        final String nama_faskes = tv_editPersalinanNamaFaskes.getText().toString().trim();
        final String alasan_dirujuk = tv_editPersalinanAlasanDirujuk.getText().toString().trim();
        final String diagnosa_sementara = tv_editPersalinanDiagnosaSementara.getText().toString().trim();
        final String tindakan_sementara = tv_editPersalinanTindakanSementara.getText().toString().trim();

        final String penolong = sp_editPersalinanPenolong.getSelectedItem().toString();
        final String tempat = sp_editPersalinanTempat.getSelectedItem().toString();
        final String jenis_persalinan = sp_editPersalinanJenisPersalinan.getSelectedItem().toString();
        final String tindakan = sp_editPersalinanTindakan.getSelectedItem().toString();
        final String keadaan_ibu = sp_editPersalinanKeadaanIbu.getSelectedItem().toString();
        final String faskes = sp_editPersalinanFaskes.getSelectedItem().toString();

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        final String updated_at = dateFormat.format(cal.getTime());

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_UPDATEPERSALINAN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")) {
                                Toast.makeText(TambahDataPersalinanActivity.this, "Update Data Persalinan Berhasil", Toast.LENGTH_LONG).show();
                                loadingDialog.dissmissDialog();
                            }else{
                                Toast.makeText(TambahDataPersalinanActivity.this, "Update Data Persalinan Gagal!", Toast.LENGTH_LONG).show();
                                loadingDialog.dissmissDialog();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(TambahDataPersalinanActivity.this, "Update Data Persalinan Gagal!", Toast.LENGTH_LONG).show();
                            loadingDialog.dissmissDialog();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(TambahDataPersalinanActivity.this, "Update Data Persalinan Gagal! : Cek Koneksi Anda", Toast.LENGTH_LONG).show();
                        loadingDialog.dissmissDialog();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                params.put("pkmkehamilan_id", id_kunjunganBaru);
                params.put("tanggal", tanggal);
                params.put("penolong", penolong);
                params.put("nama_penolong", nama_penolong);
                params.put("tempat", tempat);
                params.put("nama_tempat", nama_tempat);
                params.put("jenis_persalinan", jenis_persalinan);
                params.put("tindakan", tindakan);
                params.put("keterangan_tindakan", keterangan_tindakan);
                params.put("komplikasi", komplikasi);
                params.put("keadaan_ibu", keadaan_ibu);
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

    private void togglePersalinan() {
        if (!tv_editPersalinanTanggal.isEnabled()) {
            tv_editPersalinanTanggal.setEnabled(true);
            tv_editPersalinanTanggal.setFocusable(true);
            tv_editPersalinanTanggal.setCursorVisible(true);
            tv_editPersalinanTanggal.setFocusableInTouchMode(true);
            tv_editPersalinanTanggal.setTextColor(Color.parseColor("#000000"));
            tv_editPersalinanNamaPenolong.setEnabled(true);
            tv_editPersalinanNamaPenolong.setFocusable(true);
            tv_editPersalinanNamaPenolong.setCursorVisible(true);
            tv_editPersalinanNamaPenolong.setFocusableInTouchMode(true);
            tv_editPersalinanNamaPenolong.setTextColor(Color.parseColor("#000000"));
            tv_editPersalinanNamaTempat.setEnabled(true);
            tv_editPersalinanNamaTempat.setFocusable(true);
            tv_editPersalinanNamaTempat.setCursorVisible(true);
            tv_editPersalinanNamaTempat.setFocusableInTouchMode(true);
            tv_editPersalinanNamaTempat.setTextColor(Color.parseColor("#000000"));
            tv_editPersalinanKeteranganTindakan.setEnabled(true);
            tv_editPersalinanKeteranganTindakan.setFocusable(true);
            tv_editPersalinanKeteranganTindakan.setCursorVisible(true);
            tv_editPersalinanKeteranganTindakan.setFocusableInTouchMode(true);
            tv_editPersalinanKeteranganTindakan.setTextColor(Color.parseColor("#000000"));
            tv_editPersalinanKomplikasi.setEnabled(true);
            tv_editPersalinanKomplikasi.setFocusable(true);
            tv_editPersalinanKomplikasi.setCursorVisible(true);
            tv_editPersalinanKomplikasi.setFocusableInTouchMode(true);
            tv_editPersalinanKomplikasi.setTextColor(Color.parseColor("#000000"));
            tv_editPersalinanTanggalRujukan.setEnabled(true);
            tv_editPersalinanTanggalRujukan.setFocusable(true);
            tv_editPersalinanTanggalRujukan.setCursorVisible(true);
            tv_editPersalinanTanggalRujukan.setFocusableInTouchMode(true);
            tv_editPersalinanTanggalRujukan.setTextColor(Color.parseColor("#000000"));
            tv_editPersalinanTanggalRujukan.setEnabled(true);
            tv_editPersalinanNamaFaskes.setEnabled(true);
            tv_editPersalinanNamaFaskes.setFocusable(true);
            tv_editPersalinanNamaFaskes.setCursorVisible(true);
            tv_editPersalinanNamaFaskes.setFocusableInTouchMode(true);
            tv_editPersalinanNamaFaskes.setTextColor(Color.parseColor("#000000"));
            tv_editPersalinanAlasanDirujuk.setEnabled(true);
            tv_editPersalinanAlasanDirujuk.setFocusable(true);
            tv_editPersalinanAlasanDirujuk.setCursorVisible(true);
            tv_editPersalinanAlasanDirujuk.setFocusableInTouchMode(true);
            tv_editPersalinanAlasanDirujuk.setTextColor(Color.parseColor("#000000"));
            tv_editPersalinanDiagnosaSementara.setEnabled(true);
            tv_editPersalinanDiagnosaSementara.setFocusable(true);
            tv_editPersalinanDiagnosaSementara.setCursorVisible(true);
            tv_editPersalinanDiagnosaSementara.setFocusableInTouchMode(true);
            tv_editPersalinanDiagnosaSementara.setTextColor(Color.parseColor("#000000"));
            tv_editPersalinanTindakanSementara.setEnabled(true);
            tv_editPersalinanTindakanSementara.setFocusable(true);
            tv_editPersalinanTindakanSementara.setCursorVisible(true);
            tv_editPersalinanTindakanSementara.setFocusableInTouchMode(true);
            tv_editPersalinanTindakanSementara.setTextColor(Color.parseColor("#000000"));
            sp_editPersalinanPenolong.setEnabled(true);
            sp_editPersalinanTempat.setEnabled(true);
            sp_editPersalinanJenisPersalinan.setEnabled(true);
            sp_editPersalinanTindakan.setEnabled(true);
            sp_editPersalinanKeadaanIbu.setEnabled(true);
            sp_editPersalinanFaskes.setEnabled(true);
            Toast.makeText(TambahDataPersalinanActivity.this, "Mode Edit Diaktifkan!", Toast.LENGTH_LONG).show();
        } else if (tv_editPersalinanTanggal.isEnabled()) {
            tv_editPersalinanTanggal.setEnabled(false);
            tv_editPersalinanTanggal.setFocusable(false);
            tv_editPersalinanTanggal.setCursorVisible(false);
            tv_editPersalinanTanggal.setTextColor(Color.parseColor("#919191"));
            tv_editPersalinanNamaPenolong.setEnabled(false);
            tv_editPersalinanNamaPenolong.setFocusable(false);
            tv_editPersalinanNamaPenolong.setCursorVisible(false);
            tv_editPersalinanNamaPenolong.setTextColor(Color.parseColor("#919191"));
            tv_editPersalinanNamaTempat.setEnabled(false);
            tv_editPersalinanNamaTempat.setFocusable(false);
            tv_editPersalinanNamaTempat.setCursorVisible(false);
            tv_editPersalinanNamaTempat.setTextColor(Color.parseColor("#919191"));
            tv_editPersalinanKeteranganTindakan.setEnabled(false);
            tv_editPersalinanKeteranganTindakan.setFocusable(false);
            tv_editPersalinanKeteranganTindakan.setCursorVisible(false);
            tv_editPersalinanKeteranganTindakan.setTextColor(Color.parseColor("#919191"));
            tv_editPersalinanKomplikasi.setEnabled(false);
            tv_editPersalinanKomplikasi.setFocusable(false);
            tv_editPersalinanKomplikasi.setCursorVisible(false);
            tv_editPersalinanKomplikasi.setTextColor(Color.parseColor("#919191"));
            tv_editPersalinanTanggalRujukan.setEnabled(false);
            tv_editPersalinanTanggalRujukan.setFocusable(false);
            tv_editPersalinanTanggalRujukan.setCursorVisible(false);
            tv_editPersalinanTanggalRujukan.setTextColor(Color.parseColor("#919191"));
            tv_editPersalinanNamaFaskes.setEnabled(false);
            tv_editPersalinanNamaFaskes.setFocusable(false);
            tv_editPersalinanNamaFaskes.setCursorVisible(false);
            tv_editPersalinanNamaFaskes.setTextColor(Color.parseColor("#919191"));
            tv_editPersalinanAlasanDirujuk.setEnabled(false);
            tv_editPersalinanAlasanDirujuk.setFocusable(false);
            tv_editPersalinanAlasanDirujuk.setCursorVisible(false);
            tv_editPersalinanAlasanDirujuk.setTextColor(Color.parseColor("#919191"));
            tv_editPersalinanDiagnosaSementara.setEnabled(false);
            tv_editPersalinanDiagnosaSementara.setFocusable(false);
            tv_editPersalinanDiagnosaSementara.setCursorVisible(false);
            tv_editPersalinanDiagnosaSementara.setTextColor(Color.parseColor("#919191"));
            tv_editPersalinanTindakanSementara.setEnabled(false);
            tv_editPersalinanTindakanSementara.setFocusable(false);
            tv_editPersalinanTindakanSementara.setCursorVisible(false);
            tv_editPersalinanTindakanSementara.setTextColor(Color.parseColor("#919191"));
            sp_editPersalinanPenolong.setEnabled(false);
            sp_editPersalinanTempat.setEnabled(false);
            sp_editPersalinanJenisPersalinan.setEnabled(false);
            sp_editPersalinanTindakan.setEnabled(false);
            sp_editPersalinanKeadaanIbu.setEnabled(false);
            sp_editPersalinanFaskes.setEnabled(false);
            Toast.makeText(TambahDataPersalinanActivity.this, "Mode Edit Dinonaktifkan!", Toast.LENGTH_SHORT).show();
        }
    }

    private void loadDataPersalinan() {
        String URL_READBOOKING = "http://simetalia.com/pkm/webservice/persalinan/" + id_kunjunganBaru;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_READBOOKING,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONObject object = jsonObject.getJSONObject("read");

                            if (success.equals("1")) {

                                String tanggal = object.getString("tanggal").trim();
                                String penolong = object.getString("penolong").trim();
                                String nama_penolong = object.getString("nama_penolong").trim();
                                String tempat = object.getString("tempat").trim();
                                String nama_tempat = object.getString("nama_tempat").trim();
                                String jenis_persalinan = object.getString("jenis_persalinan").trim();
                                String tindakan = object.getString("tindakan").trim();
                                String keterangan_tindakan = object.getString("keterangan_tindakan").trim();
                                String komplikasi = object.getString("komplikasi").trim();
                                String keadaan_ibu = object.getString("keadaan_ibu").trim();
                                String tanggal_rujukan = object.getString("tanggal_rujukan").trim();
                                String faskes = object.getString("faskes").trim();
                                String nama_faskes = object.getString("nama_faskes").trim();
                                String alasan_dirujuk = object.getString("alasan_dirujuk").trim();
                                String diagnosa_sementara = object.getString("diagnosa_sementara").trim();
                                String tindakan_sementara = object.getString("tindakan_sementara").trim();

                                tv_editPersalinanTanggal.setText(tanggal);
                                tv_editPersalinanNamaPenolong.setText(nama_penolong);
                                tv_editPersalinanNamaTempat.setText(nama_tempat);
                                tv_editPersalinanKeteranganTindakan.setText(keterangan_tindakan);
                                tv_editPersalinanKomplikasi.setText(komplikasi);
                                tv_editPersalinanTanggalRujukan.setText(tanggal_rujukan);
                                tv_editPersalinanNamaFaskes.setText(nama_faskes);
                                tv_editPersalinanAlasanDirujuk.setText(alasan_dirujuk);
                                tv_editPersalinanDiagnosaSementara.setText(diagnosa_sementara);
                                tv_editPersalinanTindakanSementara.setText(tindakan_sementara);

                                ArrayAdapter<String> penolongAdapter = new ArrayAdapter<String>(TambahDataPersalinanActivity.this,
                                        android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.penolong));
                                penolongAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                sp_editPersalinanPenolong.setAdapter(penolongAdapter);
                                if (penolong != null) {
                                    int spinnerPositionPenolong = penolongAdapter.getPosition(penolong);
                                    sp_editPersalinanPenolong.setSelection(spinnerPositionPenolong);
                                }

                                ArrayAdapter<String> tempatAdapter = new ArrayAdapter<String>(TambahDataPersalinanActivity.this,
                                        android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.tempat_persalinan));
                                tempatAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                sp_editPersalinanTempat.setAdapter(tempatAdapter);
                                if (tempat != null) {
                                    int spinnerPositionTempat = tempatAdapter.getPosition(tempat);
                                    sp_editPersalinanTempat.setSelection(spinnerPositionTempat);
                                }

                                ArrayAdapter<String> jenisPersalinanAdapter = new ArrayAdapter<String>(TambahDataPersalinanActivity.this,
                                        android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.jenis_persalinan));
                                jenisPersalinanAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                sp_editPersalinanJenisPersalinan.setAdapter(jenisPersalinanAdapter);
                                if (jenis_persalinan != null) {
                                    int spinnerPositionJenisPersalinan = jenisPersalinanAdapter.getPosition(jenis_persalinan);
                                    sp_editPersalinanJenisPersalinan.setSelection(spinnerPositionJenisPersalinan);
                                }

                                ArrayAdapter<String> tindakanAdapter = new ArrayAdapter<String>(TambahDataPersalinanActivity.this,
                                        android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.tindakan_persalinan));
                                tindakanAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                sp_editPersalinanTindakan.setAdapter(tindakanAdapter);
                                if (tindakan != null) {
                                    int spinnerPositionTindakan = tindakanAdapter.getPosition(tindakan);
                                    sp_editPersalinanTindakan.setSelection(spinnerPositionTindakan);
                                }

                                ArrayAdapter<String> keadaanIbuAdapter = new ArrayAdapter<String>(TambahDataPersalinanActivity.this,
                                        android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.keadaan_ibu_persalinan));
                                keadaanIbuAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                sp_editPersalinanKeadaanIbu.setAdapter(keadaanIbuAdapter);
                                if (keadaan_ibu != null) {
                                    int spinnerPositionKeadaanIbu = keadaanIbuAdapter.getPosition(keadaan_ibu);
                                    sp_editPersalinanKeadaanIbu.setSelection(spinnerPositionKeadaanIbu);
                                }

                                ArrayAdapter<String> faskesAdapter = new ArrayAdapter<String>(TambahDataPersalinanActivity.this,
                                        android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.faskes));
                                faskesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                sp_editPersalinanFaskes.setAdapter(faskesAdapter);
                                if (faskes != null) {
                                    int spinnerPositionFaskes = faskesAdapter.getPosition(faskes);
                                    sp_editPersalinanFaskes.setSelection(spinnerPositionFaskes);
                                }


                                loadingDialog.dissmissDialog();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(TambahDataPersalinanActivity.this, "Data Persalinan Tidak Ada!", Toast.LENGTH_SHORT).show();
                            loadingDialog.dissmissDialog();
                        }

                    }
                }
                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(TambahDataPersalinanActivity.this, "Koneksi Gagal!" + error.toString(), Toast.LENGTH_SHORT).show();
                loadingDialog.dissmissDialog();
            }
        }) {
        };
        RequestQueue requestQueue = Volley.newRequestQueue(TambahDataPersalinanActivity.this);
        requestQueue.add(stringRequest);
    }

    private void spinnerPersalinan() {
        ArrayAdapter<String> penolongAdapter = new ArrayAdapter<String>(TambahDataPersalinanActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.penolong));
        penolongAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_editPersalinanPenolong.setAdapter(penolongAdapter);

        ArrayAdapter<String> tempatAdapter = new ArrayAdapter<String>(TambahDataPersalinanActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.tempat_persalinan));
        tempatAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_editPersalinanTempat.setAdapter(tempatAdapter);

        ArrayAdapter<String> jenisPersalinanAdapter = new ArrayAdapter<String>(TambahDataPersalinanActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.jenis_persalinan));
        jenisPersalinanAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_editPersalinanJenisPersalinan.setAdapter(jenisPersalinanAdapter);

        ArrayAdapter<String> tindakanAdapter = new ArrayAdapter<String>(TambahDataPersalinanActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.tindakan_persalinan));
        tindakanAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_editPersalinanTindakan.setAdapter(tindakanAdapter);

        ArrayAdapter<String> keadaanIbuAdapter = new ArrayAdapter<String>(TambahDataPersalinanActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.keadaan_ibu_persalinan));
        keadaanIbuAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_editPersalinanKeadaanIbu.setAdapter(keadaanIbuAdapter);

        ArrayAdapter<String> faskesAdapter = new ArrayAdapter<String>(TambahDataPersalinanActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.faskes));
        faskesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_editPersalinanFaskes.setAdapter(faskesAdapter);
    }
}
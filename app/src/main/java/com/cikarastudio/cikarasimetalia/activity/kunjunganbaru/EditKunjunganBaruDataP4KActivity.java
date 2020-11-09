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
import com.anychart.scales.Linear;
import com.cikarastudio.cikarasimetalia.R;
import com.cikarastudio.cikarasimetalia.dialog.LoadingDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class EditKunjunganBaruDataP4KActivity extends AppCompatActivity {

    public static final String ID_KUNJUNGANBARU = "extra_data";
    String id_kunjunganBaru, buku_kia, stiker;
    LoadingDialog loadingDialog;
    EditText tv_editP4KNamaPenolongBaru, tv_editP4KNamaTempatBaru, tv_editP4KPendampingSalinBaru,
            tv_editP4KNamaPendonorBaru, tv_editP4KBukuKIABaru, tv_editP4KBukuStikerBaru;
    Spinner sp_editP4KPenolongSalinBaru, sp_editP4KTempatSalinBaru, sp_editP4KKendaraanBaru,
            sp_editP4KKepemilikanBaru, sp_editP4KBiayaSalinBaru, sp_editP4KCalonDonorBaru;
    ImageView btn_toggleEditP4KBaru;
    LinearLayout btn_updateDataP4KBaru;
    private static final String URL_UPDATEP4KBARU = "http://simetalia.com/android/update_p4k_baru.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_kunjungan_baru_data_p4_k);

        loadingDialog = new LoadingDialog(EditKunjunganBaruDataP4KActivity.this);
        loadingDialog.startLoading();

        Intent intent = getIntent();
        id_kunjunganBaru = intent.getStringExtra(ID_KUNJUNGANBARU);

        sp_editP4KPenolongSalinBaru = findViewById(R.id.sp_editP4KPenolongSalinBaru);
        tv_editP4KNamaPenolongBaru = findViewById(R.id.tv_editP4KNamaPenolongBaru);
        sp_editP4KTempatSalinBaru = findViewById(R.id.sp_editP4KTempatSalinBaru);
        tv_editP4KNamaTempatBaru = findViewById(R.id.tv_editP4KNamaTempatBaru);
        tv_editP4KPendampingSalinBaru = findViewById(R.id.tv_editP4KPendampingSalinBaru);
        sp_editP4KKendaraanBaru = findViewById(R.id.sp_editP4KKendaraanBaru);
        sp_editP4KKepemilikanBaru = findViewById(R.id.sp_editP4KKepemilikanBaru);
        sp_editP4KBiayaSalinBaru = findViewById(R.id.sp_editP4KBiayaSalinBaru);
        sp_editP4KCalonDonorBaru = findViewById(R.id.sp_editP4KCalonDonorBaru);
        tv_editP4KNamaPendonorBaru = findViewById(R.id.tv_editP4KNamaPendonorBaru);
        tv_editP4KBukuKIABaru = findViewById(R.id.tv_editP4KBukuKIABaru);
        tv_editP4KBukuStikerBaru = findViewById(R.id.tv_editP4KBukuStikerBaru);
        btn_toggleEditP4KBaru = findViewById(R.id.btn_toggleEditP4KBaru);
        btn_updateDataP4KBaru = findViewById(R.id.btn_updateDataP4KBaru);


        sp_editP4KPenolongSalinBaru.setEnabled(false);
        sp_editP4KTempatSalinBaru.setEnabled(false);
        sp_editP4KKendaraanBaru.setEnabled(false);
        sp_editP4KKepemilikanBaru.setEnabled(false);
        sp_editP4KBiayaSalinBaru.setEnabled(false);
        sp_editP4KCalonDonorBaru.setEnabled(false);

        loadDatap4k();

        btn_toggleEditP4KBaru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                togglep4kBaru();
            }
        });

        btn_updateDataP4KBaru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateDataP4KBaru();
            }
        });

    }

    private void updateDataP4KBaru() {
        loadingDialog.startLoading();
        String buku_kiaawal = tv_editP4KBukuKIABaru.getText().toString().trim();
        if (buku_kiaawal.equals("")) {
            buku_kia = "";
        } else {
            String tanggaltengahbuku_kia = buku_kiaawal.substring(0, 2);
            String bulantengahbuku_kia = buku_kiaawal.substring(3, 5);
            String tahuntengahbuku_kia = buku_kiaawal.substring(6, 10);
            buku_kia = tahuntengahbuku_kia + "-" + bulantengahbuku_kia + "-" + tanggaltengahbuku_kia;
        }

        final String stikerawal = tv_editP4KBukuStikerBaru.getText().toString().trim();
        if (stikerawal.equals("")) {
            stiker = "";
        } else {
            String tanggaltengahstiker = stikerawal.substring(0, 2);
            String bulantengahstiker = stikerawal.substring(3, 5);
            String tahuntengahstiker = stikerawal.substring(6, 10);
            stiker = tahuntengahstiker + "-" + bulantengahstiker + "-" + tanggaltengahstiker;
        }

        final String nama_penolong = tv_editP4KNamaPenolongBaru.getText().toString().trim();
        final String nama_tempat = tv_editP4KNamaTempatBaru.getText().toString().trim();
        final String pendamping_salin = tv_editP4KPendampingSalinBaru.getText().toString().trim();
        final String nama_donor = tv_editP4KNamaPendonorBaru.getText().toString().trim();

        final String penolong_salin = sp_editP4KPenolongSalinBaru.getSelectedItem().toString();
        final String tempat_salin = sp_editP4KTempatSalinBaru.getSelectedItem().toString();
        final String kendaraan = sp_editP4KKendaraanBaru.getSelectedItem().toString();
        final String kepemilikan = sp_editP4KKepemilikanBaru.getSelectedItem().toString();
        final String biaya_salin = sp_editP4KBiayaSalinBaru.getSelectedItem().toString();
        final String calon_donor = sp_editP4KCalonDonorBaru.getSelectedItem().toString();

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        final String updated_at = dateFormat.format(cal.getTime());

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_UPDATEP4KBARU,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")) {
                                Toast.makeText(EditKunjunganBaruDataP4KActivity.this, "Update Data P4K Berhasil", Toast.LENGTH_LONG).show();
                                loadingDialog.dissmissDialog();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(EditKunjunganBaruDataP4KActivity.this, "Update Data P4k Gagal!", Toast.LENGTH_LONG).show();
                            loadingDialog.dissmissDialog();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(EditKunjunganBaruDataP4KActivity.this, "Update Data P4k Gagal! : Cek Koneksi Anda", Toast.LENGTH_LONG).show();
                        loadingDialog.dissmissDialog();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                params.put("id", id_kunjunganBaru);
                params.put("penolong_salin", penolong_salin);
                params.put("nama_penolong", nama_penolong);
                params.put("tempat_salin", tempat_salin);
                params.put("nama_tempat", nama_tempat);
                params.put("pendamping_salin", pendamping_salin);
                params.put("kendaraan", kendaraan);
                params.put("kepemilikan", kepemilikan);
                params.put("biaya_salin", biaya_salin);
                params.put("calon_donor", calon_donor);
                params.put("nama_donor", nama_donor);
                params.put("buku_kia", buku_kia);
                params.put("stiker", stiker);
                params.put("updated_at", updated_at);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void togglep4kBaru() {
        if (!tv_editP4KNamaPenolongBaru.isEnabled()) {
            tv_editP4KNamaPenolongBaru.setEnabled(true);
            tv_editP4KNamaPenolongBaru.setFocusable(true);
            tv_editP4KNamaPenolongBaru.setCursorVisible(true);
            tv_editP4KNamaPenolongBaru.setFocusableInTouchMode(true);
            tv_editP4KNamaPenolongBaru.setTextColor(Color.parseColor("#000000"));
            tv_editP4KNamaTempatBaru.setEnabled(true);
            tv_editP4KNamaTempatBaru.setFocusable(true);
            tv_editP4KNamaTempatBaru.setCursorVisible(true);
            tv_editP4KNamaTempatBaru.setFocusableInTouchMode(true);
            tv_editP4KNamaTempatBaru.setTextColor(Color.parseColor("#000000"));
            tv_editP4KPendampingSalinBaru.setEnabled(true);
            tv_editP4KPendampingSalinBaru.setFocusable(true);
            tv_editP4KPendampingSalinBaru.setCursorVisible(true);
            tv_editP4KPendampingSalinBaru.setFocusableInTouchMode(true);
            tv_editP4KPendampingSalinBaru.setTextColor(Color.parseColor("#000000"));
            tv_editP4KNamaPendonorBaru.setEnabled(true);
            tv_editP4KNamaPendonorBaru.setFocusable(true);
            tv_editP4KNamaPendonorBaru.setCursorVisible(true);
            tv_editP4KNamaPendonorBaru.setFocusableInTouchMode(true);
            tv_editP4KNamaPendonorBaru.setTextColor(Color.parseColor("#000000"));
            tv_editP4KBukuKIABaru.setEnabled(true);
            tv_editP4KBukuKIABaru.setFocusable(true);
            tv_editP4KBukuKIABaru.setCursorVisible(true);
            tv_editP4KBukuKIABaru.setFocusableInTouchMode(true);
            tv_editP4KBukuKIABaru.setTextColor(Color.parseColor("#000000"));
            tv_editP4KBukuStikerBaru.setEnabled(true);
            tv_editP4KBukuStikerBaru.setFocusable(true);
            tv_editP4KBukuStikerBaru.setCursorVisible(true);
            tv_editP4KBukuStikerBaru.setFocusableInTouchMode(true);
            tv_editP4KBukuStikerBaru.setTextColor(Color.parseColor("#000000"));
            sp_editP4KPenolongSalinBaru.setEnabled(true);
            sp_editP4KTempatSalinBaru.setEnabled(true);
            sp_editP4KKendaraanBaru.setEnabled(true);
            sp_editP4KKepemilikanBaru.setEnabled(true);
            sp_editP4KBiayaSalinBaru.setEnabled(true);
            sp_editP4KCalonDonorBaru.setEnabled(true);
            Toast.makeText(EditKunjunganBaruDataP4KActivity.this, "Mode Edit Diaktifkan!", Toast.LENGTH_LONG).show();
        } else if (tv_editP4KNamaPenolongBaru.isEnabled()) {
            tv_editP4KNamaPenolongBaru.setEnabled(false);
            tv_editP4KNamaPenolongBaru.setFocusable(false);
            tv_editP4KNamaPenolongBaru.setCursorVisible(false);
            tv_editP4KNamaPenolongBaru.setTextColor(Color.parseColor("#919191"));
            tv_editP4KNamaTempatBaru.setEnabled(false);
            tv_editP4KNamaTempatBaru.setFocusable(false);
            tv_editP4KNamaTempatBaru.setCursorVisible(false);
            tv_editP4KNamaTempatBaru.setTextColor(Color.parseColor("#919191"));
            tv_editP4KPendampingSalinBaru.setEnabled(false);
            tv_editP4KPendampingSalinBaru.setFocusable(false);
            tv_editP4KPendampingSalinBaru.setCursorVisible(false);
            tv_editP4KPendampingSalinBaru.setTextColor(Color.parseColor("#919191"));
            tv_editP4KNamaPendonorBaru.setEnabled(false);
            tv_editP4KNamaPendonorBaru.setFocusable(false);
            tv_editP4KNamaPendonorBaru.setCursorVisible(false);
            tv_editP4KNamaPendonorBaru.setTextColor(Color.parseColor("#919191"));
            tv_editP4KBukuKIABaru.setEnabled(false);
            tv_editP4KBukuKIABaru.setFocusable(false);
            tv_editP4KBukuKIABaru.setCursorVisible(false);
            tv_editP4KBukuKIABaru.setTextColor(Color.parseColor("#919191"));
            tv_editP4KBukuStikerBaru.setEnabled(false);
            tv_editP4KBukuStikerBaru.setFocusable(false);
            tv_editP4KBukuStikerBaru.setCursorVisible(false);
            tv_editP4KBukuStikerBaru.setTextColor(Color.parseColor("#919191"));
            sp_editP4KPenolongSalinBaru.setEnabled(false);
            sp_editP4KTempatSalinBaru.setEnabled(false);
            sp_editP4KKendaraanBaru.setEnabled(false);
            sp_editP4KKepemilikanBaru.setEnabled(false);
            sp_editP4KBiayaSalinBaru.setEnabled(false);
            sp_editP4KCalonDonorBaru.setEnabled(false);
            Toast.makeText(EditKunjunganBaruDataP4KActivity.this, "Mode Edit Dinonaktifkan!", Toast.LENGTH_SHORT).show();
        }
    }

    private void loadDatap4k() {
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

                                String penolong_salin = object.getString("penolong_salin").trim();
                                String nama_penolong = object.getString("nama_penolong").trim();
                                String tempat_salin = object.getString("tempat_salin").trim();
                                String nama_tempat = object.getString("nama_tempat").trim();
                                String pendamping_salin = object.getString("pendamping_salin").trim();
                                String kendaraan = object.getString("kendaraan").trim();
                                String kepemilikan = object.getString("kepemilikan").trim();
                                String biaya_salin = object.getString("biaya_salin").trim();
                                String calon_donor = object.getString("calon_donor").trim();
                                String nama_donor = object.getString("nama_donor").trim();
                                String buku_kia = object.getString("buku_kia").trim();
                                String stiker = object.getString("stiker").trim();

                                tv_editP4KNamaPenolongBaru.setText(nama_penolong);
                                tv_editP4KNamaTempatBaru.setText(nama_tempat);
                                tv_editP4KPendampingSalinBaru.setText(pendamping_salin);
                                tv_editP4KNamaPendonorBaru.setText(nama_donor);
                                tv_editP4KBukuKIABaru.setText(buku_kia);
                                tv_editP4KBukuStikerBaru.setText(stiker);

                                ArrayAdapter<String> penolongSalinAdapter = new ArrayAdapter<String>(EditKunjunganBaruDataP4KActivity.this,
                                        android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.tolong_salin));
                                penolongSalinAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                sp_editP4KPenolongSalinBaru.setAdapter(penolongSalinAdapter);
                                if (penolong_salin != null) {
                                    int spinnerPositionTolongSalin = penolongSalinAdapter.getPosition(penolong_salin);
                                    sp_editP4KPenolongSalinBaru.setSelection(spinnerPositionTolongSalin);
                                }

                                ArrayAdapter<String> tempatSalinAdapter = new ArrayAdapter<>(EditKunjunganBaruDataP4KActivity.this,
                                        android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.tempat_salin));
                                tempatSalinAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                sp_editP4KTempatSalinBaru.setAdapter(tempatSalinAdapter);
                                if (tempat_salin != null) {
                                    int spinnerPositionTempatSalin = tempatSalinAdapter.getPosition(tempat_salin);
                                    sp_editP4KTempatSalinBaru.setSelection(spinnerPositionTempatSalin);
                                }

                                ArrayAdapter<String> kendaraanAdapter = new ArrayAdapter<String>(EditKunjunganBaruDataP4KActivity.this,
                                        android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.kendaraan));
                                kendaraanAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                sp_editP4KKendaraanBaru.setAdapter(kendaraanAdapter);
                                if (kendaraan != null) {
                                    int spinnerPositionKendaraan = kendaraanAdapter.getPosition(kendaraan);
                                    sp_editP4KKendaraanBaru.setSelection(spinnerPositionKendaraan);
                                }

                                ArrayAdapter<String> kepemilikanAdapter = new ArrayAdapter<String>(EditKunjunganBaruDataP4KActivity.this,
                                        android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.kepemilikan));
                                kepemilikanAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                sp_editP4KKepemilikanBaru.setAdapter(kepemilikanAdapter);
                                if (kepemilikan != null) {
                                    int spinnerPositionKepemilikan = kepemilikanAdapter.getPosition(kepemilikan);
                                    sp_editP4KKepemilikanBaru.setSelection(spinnerPositionKepemilikan);
                                }

                                ArrayAdapter<String> biayaSalinAdapter = new ArrayAdapter<String>(EditKunjunganBaruDataP4KActivity.this,
                                        android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.biaya_salin));
                                biayaSalinAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                sp_editP4KBiayaSalinBaru.setAdapter(biayaSalinAdapter);
                                if (biaya_salin != null) {
                                    int spinnerPositionBiayaSalin = biayaSalinAdapter.getPosition(biaya_salin);
                                    sp_editP4KBiayaSalinBaru.setSelection(spinnerPositionBiayaSalin);
                                }

                                ArrayAdapter<String> calonDonorAdapter = new ArrayAdapter<String>(EditKunjunganBaruDataP4KActivity.this,
                                        android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.calon_donor));
                                calonDonorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                sp_editP4KCalonDonorBaru.setAdapter(calonDonorAdapter);
                                if (calon_donor != null) {
                                    int spinnerPositionCalonDonor = calonDonorAdapter.getPosition(calon_donor);
                                    sp_editP4KCalonDonorBaru.setSelection(spinnerPositionCalonDonor);
                                }


                                loadingDialog.dissmissDialog();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(EditKunjunganBaruDataP4KActivity.this, "Data Ibu Hamil Tidak Ada!" + e.toString(), Toast.LENGTH_SHORT).show();
                            loadingDialog.dissmissDialog();
                        }

                    }
                }
                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(EditKunjunganBaruDataP4KActivity.this, "Koneksi Gagal!" + error.toString(), Toast.LENGTH_SHORT).show();
                loadingDialog.dissmissDialog();
            }
        }) {
        };
        RequestQueue requestQueue = Volley.newRequestQueue(EditKunjunganBaruDataP4KActivity.this);
        requestQueue.add(stringRequest);
    }
}
package com.cikarastudio.cikarasimetalia.activity.bumil;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
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
import com.cikarastudio.cikarasimetalia.session.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


public class TambahDataIbuHamilActivity extends AppCompatActivity {

    SessionManager session;

    LoadingDialog loadingDialog;
    Spinner et_agama, et_pendidikan, et_golDarah;
    ImageView btn_back;
    LinearLayout btn_tambahDataBumil;
    Dialog myDialog;
    EditText et_tambahBumilNoNik, et_tambahBumilNamaBumil, et_tambahBumilTempatLahir, et_tambahBumilTglLahir, et_tambahBumilPekerjaan;
    private static String URL_TAMBAHBUMIL = "http://simetalia.com/android/tambah_data_bumil.php";
    String id_user,id_pkm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_data_ibu_hamil);

        myDialog = new Dialog(this);
        loadingDialog = new LoadingDialog(TambahDataIbuHamilActivity.this);

        session = new SessionManager(getApplicationContext());
        session.checkLogin();
        HashMap<String, String> user = session.getUserDetail();
        id_user = user.get(SessionManager.ID);



        et_tambahBumilNoNik = findViewById(R.id.et_tambahBumilNoNik);
        et_tambahBumilNamaBumil = findViewById(R.id.et_tambahBumilNamaBumil);
        et_tambahBumilTempatLahir = findViewById(R.id.et_tambahBumilTempatLahir);
        et_tambahBumilTglLahir = findViewById(R.id.et_tambahBumilTglLahir);
        et_tambahBumilPekerjaan = findViewById(R.id.et_tambahBumilPekerjaan);
        et_golDarah = findViewById(R.id.et_tambahBumilGolDarah);
        et_agama = findViewById(R.id.et_tambahBumilAgama);
        et_pendidikan = findViewById(R.id.et_tambahBumilPendidikan);
        btn_back = findViewById(R.id.btn_back);
        btn_tambahDataBumil = findViewById(R.id.btn_tambahDataBumil);


        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        ArrayAdapter<String> goldarAdapter = new ArrayAdapter<>(TambahDataIbuHamilActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.goldarah));
        goldarAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        et_golDarah.setAdapter(goldarAdapter);

        ArrayAdapter<String> agamaAdapter = new ArrayAdapter<String>(TambahDataIbuHamilActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.agama));
        agamaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        et_agama.setAdapter(agamaAdapter);

        ArrayAdapter<String> pendidikanAdapter = new ArrayAdapter<String>(TambahDataIbuHamilActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.pendidikan));
        pendidikanAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        et_pendidikan.setAdapter(pendidikanAdapter);


        AmbilDataIDPKMDesa();

        btn_tambahDataBumil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddBumil();
            }
        });
    }

    private void AmbilDataIDPKMDesa() {
        String URL_READBOOKING = "http://simetalia.com/pkm/webservice/nakes/" + id_user;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_READBOOKING,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONObject read = jsonObject.getJSONObject("read");
                            if (success.equals("1")) {

                                id_pkm = read.getString("pkmdesa_id").trim();

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(new TambahDataIbuHamilActivity(), "Data Akun Tidak Ada!" + e.toString(), Toast.LENGTH_LONG).show();
                        }

                    }
                }
                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(new TambahDataIbuHamilActivity(), "Tidak Ada Koneksi Internet!", Toast.LENGTH_LONG).show();
            }
        }) {
        };
        RequestQueue requestQueue = Volley.newRequestQueue(TambahDataIbuHamilActivity.this);
        requestQueue.add(stringRequest);
    }

    private void AddBumil() {
        loadingDialog.startLoading();
        final String nikBumil = et_tambahBumilNoNik.getText().toString().trim();
        final String namaBumil = et_tambahBumilNamaBumil.getText().toString().trim();
        final String tempatLahirBumil = et_tambahBumilTempatLahir.getText().toString().trim();

        String tglLahir = et_tambahBumilTglLahir.getText().toString().trim();
        String tanggal = tglLahir.substring(0, 2);
        String bulan = tglLahir.substring(3, 5);
        String tahun = tglLahir.substring(6, 10);
        final String tglLahirFixBumil = tahun + "-" + bulan + "-" + tanggal;
        final String goldarBumil = et_golDarah.getSelectedItem().toString();
        final String agamaBumil = et_agama.getSelectedItem().toString();
        final String pekerjaanBumil = et_tambahBumilPekerjaan.getText().toString().trim();
        final String pendidikanBumil = et_pendidikan.getSelectedItem().toString();

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        final String tanggalSekarang = dateFormat.format(cal.getTime());

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_TAMBAHBUMIL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")) {
                                Toast.makeText(TambahDataIbuHamilActivity.this, "Tambah Ibu Hamil Sukses", Toast.LENGTH_LONG).show();
                                loadingDialog.dissmissDialog();
                                finish();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(TambahDataIbuHamilActivity.this, "Tambah Data Ibu Hamil Gagal!", Toast.LENGTH_LONG).show();
                            loadingDialog.dissmissDialog();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(TambahDataIbuHamilActivity.this, "Tambah Data Bumil Gagal! : Cek Koneksi Anda", Toast.LENGTH_LONG).show();
                        loadingDialog.dissmissDialog();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                params.put("pkmdesa_id", id_pkm);
                params.put("nik", nikBumil);
                params.put("nama_bumil", namaBumil);
                params.put("tempat_lahir", tempatLahirBumil);
                params.put("tgl_lahir", tglLahirFixBumil);
                params.put("goldar", goldarBumil);
                params.put("agama", agamaBumil);
                params.put("pekerjaan", pekerjaanBumil);
                params.put("pendidikan", pendidikanBumil);
                params.put("created_at", tanggalSekarang);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

}
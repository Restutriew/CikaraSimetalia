package com.cikarastudio.cikarasimetalia.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class EditDataNakesActivity extends AppCompatActivity {

    LoadingDialog loadingDialog;
    SessionManager sessionManager;
    String id_user, id_nakesUpload;
    Button btn_updateDataNakes;
    ImageView btn_backNakes, btn_imgEditNakesNIP, btn_imgEditNakesTempatLahir, btn_imgEditNakesTglLahir, btn_imgEditNakesJK, btn_imgEditNakesAgama, btn_imgEditNakesAlamatPraktek;
    EditText tv_dataNakesNIP, tv_dataNakesTempatLahir, tv_dataNakesTglLahir, tv_dataNakesAlamatKerja;
    AutoCompleteTextView tv_dataNakesJK, tv_dataNakesAgama;
    private static String URL_UPDATENAKES = "http://simetalia.com/android/update_nakes.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data_nakes);

        sessionManager = new SessionManager(EditDataNakesActivity.this);
        sessionManager.checkLogin();
        HashMap<String, String> user = sessionManager.getUserDetail();
        id_user = user.get(sessionManager.ID);


        loadingDialog = new LoadingDialog(EditDataNakesActivity.this);
        loadingDialog.startLoading();

        tv_dataNakesNIP = findViewById(R.id.tv_editNakesNIP);
        tv_dataNakesTempatLahir = findViewById(R.id.tv_editNakesTempatLahir);
        tv_dataNakesTglLahir = findViewById(R.id.tv_editNakesTglLahir);

        tv_dataNakesAlamatKerja = findViewById(R.id.tv_editNakesAlamatPraktek);

        loadDataNakes();

        tv_dataNakesJK = findViewById(R.id.tv_editNakesJK);
        String[] countries = getResources().getStringArray(R.array.jk);
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, countries);
        tv_dataNakesJK.setAdapter(adapter);

        tv_dataNakesAgama = findViewById(R.id.tv_editNakesAgama);
        String[] agama = getResources().getStringArray(R.array.agama);
        ArrayAdapter<String> adapter1 =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, agama);
        tv_dataNakesAgama.setAdapter(adapter1);

        btn_backNakes = findViewById(R.id.btn_backEditNakes);
        btn_backNakes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btn_updateDataNakes = findViewById(R.id.btn_updateDataNakes);
        btn_updateDataNakes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateDataNakes();
            }
        });

        btn_imgEditNakesNIP = findViewById(R.id.btn_imgEditNakesNIP);
        btn_imgEditNakesNIP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!tv_dataNakesNIP.isEnabled()) {
                    tv_dataNakesNIP.setEnabled(true);
                    tv_dataNakesNIP.setFocusable(true);
                    tv_dataNakesNIP.setCursorVisible(true);
                    tv_dataNakesNIP.setFocusableInTouchMode(true);
                    tv_dataNakesNIP.setBackgroundColor(Color.parseColor("#D10069"));
                    tv_dataNakesNIP.setTextColor(Color.parseColor("#FFFFFF"));
                } else if (tv_dataNakesNIP.isEnabled()) {
                    tv_dataNakesNIP.setEnabled(false);
                    tv_dataNakesNIP.setFocusable(false);
                    tv_dataNakesNIP.setCursorVisible(false);
                    tv_dataNakesNIP.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    tv_dataNakesNIP.setTextColor(Color.parseColor("#000000"));
                }
            }
        });


        btn_imgEditNakesTempatLahir = findViewById(R.id.btn_imgEditNakesTempatLahir);
        btn_imgEditNakesTempatLahir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!tv_dataNakesTempatLahir.isEnabled()) {
                    tv_dataNakesTempatLahir.setEnabled(true);
                    tv_dataNakesTempatLahir.setFocusable(true);
                    tv_dataNakesTempatLahir.setCursorVisible(true);
                    tv_dataNakesTempatLahir.setFocusableInTouchMode(true);
                    tv_dataNakesTempatLahir.setBackgroundColor(Color.parseColor("#D10069"));
                    tv_dataNakesTempatLahir.setTextColor(Color.parseColor("#FFFFFF"));
                } else if (tv_dataNakesTempatLahir.isEnabled()) {
                    tv_dataNakesTempatLahir.setEnabled(false);
                    tv_dataNakesTempatLahir.setFocusable(false);
                    tv_dataNakesTempatLahir.setCursorVisible(false);
                    tv_dataNakesTempatLahir.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    tv_dataNakesTempatLahir.setTextColor(Color.parseColor("#000000"));
                }
            }
        });

        btn_imgEditNakesTglLahir = findViewById(R.id.btn_imgEditNakesTglLahir);
        btn_imgEditNakesTglLahir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!tv_dataNakesTglLahir.isEnabled()) {
                    tv_dataNakesTglLahir.setEnabled(true);
                    tv_dataNakesTglLahir.setFocusable(true);
                    tv_dataNakesTglLahir.setCursorVisible(true);
                    tv_dataNakesTglLahir.setFocusableInTouchMode(true);
                    tv_dataNakesTglLahir.setBackgroundColor(Color.parseColor("#D10069"));
                    tv_dataNakesTglLahir.setTextColor(Color.parseColor("#FFFFFF"));
                } else if (tv_dataNakesTglLahir.isEnabled()) {
                    tv_dataNakesTglLahir.setEnabled(false);
                    tv_dataNakesTglLahir.setFocusable(false);
                    tv_dataNakesTglLahir.setCursorVisible(false);
                    tv_dataNakesTglLahir.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    tv_dataNakesTglLahir.setTextColor(Color.parseColor("#000000"));
                }
            }
        });

        btn_imgEditNakesJK = findViewById(R.id.btn_imgEditNakesJK);
        btn_imgEditNakesJK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!tv_dataNakesJK.isEnabled()) {
                    tv_dataNakesJK.setEnabled(true);
                    tv_dataNakesJK.setFocusable(true);
                    tv_dataNakesJK.setCursorVisible(true);
                    tv_dataNakesJK.setFocusableInTouchMode(true);
                    tv_dataNakesJK.setBackgroundColor(Color.parseColor("#D10069"));
                    tv_dataNakesJK.setTextColor(Color.parseColor("#FFFFFF"));
                } else if (tv_dataNakesJK.isEnabled()) {
                    tv_dataNakesJK.setEnabled(false);
                    tv_dataNakesJK.setFocusable(false);
                    tv_dataNakesJK.setCursorVisible(false);
                    tv_dataNakesJK.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    tv_dataNakesJK.setTextColor(Color.parseColor("#000000"));
                }
            }
        });

        btn_imgEditNakesAgama = findViewById(R.id.btn_imgEditNakesAgama);
        btn_imgEditNakesAgama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!tv_dataNakesAgama.isEnabled()) {
                    tv_dataNakesAgama.setEnabled(true);
                    tv_dataNakesAgama.setFocusable(true);
                    tv_dataNakesAgama.setCursorVisible(true);
                    tv_dataNakesAgama.setFocusableInTouchMode(true);
                    tv_dataNakesAgama.setBackgroundColor(Color.parseColor("#D10069"));
                    tv_dataNakesAgama.setTextColor(Color.parseColor("#FFFFFF"));
                } else if (tv_dataNakesAgama.isEnabled()) {
                    tv_dataNakesAgama.setEnabled(false);
                    tv_dataNakesAgama.setFocusable(false);
                    tv_dataNakesAgama.setCursorVisible(false);
                    tv_dataNakesAgama.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    tv_dataNakesAgama.setTextColor(Color.parseColor("#000000"));
                }
            }
        });

        btn_imgEditNakesAlamatPraktek = findViewById(R.id.btn_imgEditNakesAlamatPraktek);
        btn_imgEditNakesAlamatPraktek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!tv_dataNakesAlamatKerja.isEnabled()) {
                    tv_dataNakesAlamatKerja.setEnabled(true);
                    tv_dataNakesAlamatKerja.setFocusable(true);
                    tv_dataNakesAlamatKerja.setCursorVisible(true);
                    tv_dataNakesAlamatKerja.setFocusableInTouchMode(true);
                    tv_dataNakesAlamatKerja.setBackgroundColor(Color.parseColor("#D10069"));
                    tv_dataNakesAlamatKerja.setTextColor(Color.parseColor("#FFFFFF"));
                } else if (tv_dataNakesAlamatKerja.isEnabled()) {
                    tv_dataNakesAlamatKerja.setEnabled(false);
                    tv_dataNakesAlamatKerja.setFocusable(false);
                    tv_dataNakesAlamatKerja.setCursorVisible(false);
                    tv_dataNakesAlamatKerja.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    tv_dataNakesAlamatKerja.setTextColor(Color.parseColor("#000000"));
                }
            }
        });
    }

    private void updateDataNakes() {
        final String nip = tv_dataNakesNIP.getText().toString().trim();
        final String tempat_lahir = tv_dataNakesTempatLahir.getText().toString().trim();
        final String tgl_lahir = tv_dataNakesTglLahir.getText().toString().trim();
        final String jk = tv_dataNakesJK.getText().toString().trim();
        final String agama = tv_dataNakesAgama.getText().toString().trim();
        final String alamat_kerja = tv_dataNakesAlamatKerja.getText().toString().trim();

        final String utanggal_sekarang;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        utanggal_sekarang = dateFormat.format(cal.getTime());
        final String tgl_sekarang = utanggal_sekarang;

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_UPDATENAKES,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")) {
                                Toast.makeText(EditDataNakesActivity.this, "Update Berhasil! ", Toast.LENGTH_SHORT).show();
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(EditDataNakesActivity.this, "Update Gagal ", Toast.LENGTH_SHORT).show();
                        }

                    }
                }
                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(EditDataNakesActivity.this, "Update Gagal: Jaringan Terganggu ", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id", id_nakesUpload);
                params.put("nip", nip);
                params.put("tempat_lahir", tempat_lahir);
                params.put("tgl_lahir", tgl_lahir);
                params.put("jk", jk);
                params.put("agama", agama);
                params.put("alamat_kerja", alamat_kerja);
                params.put("tanggal_sekarang", tgl_sekarang);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(EditDataNakesActivity.this);
        requestQueue.add(stringRequest);
    }

    private void loadDataNakes() {
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

                                String id_nakes = read.getString("id").trim();
                                String nip = read.getString("nip").trim();
                                String tempat_lahir = read.getString("tempat_lahir").trim();
                                String tgl_lahir = read.getString("tgl_lahir").trim();
                                String jk = read.getString("jk").trim();
                                String agama = read.getString("agama").trim();
                                String alamat_kerja = read.getString("alamat_kerja").trim();

                                tv_dataNakesNIP.setText(nip);
                                tv_dataNakesTempatLahir.setText(tempat_lahir);
                                tv_dataNakesTglLahir.setText(tgl_lahir);
                                tv_dataNakesJK.setText(jk);
                                tv_dataNakesAgama.setText(agama);
                                tv_dataNakesAlamatKerja.setText(alamat_kerja);

                                id_nakesUpload = id_nakes;

                                //hilangkan loading
                                loadingDialog.dissmissDialog();

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            loadingDialog.dissmissDialog();
                            Toast.makeText(EditDataNakesActivity.this, "Data Akun Tidak Ada!" + e.toString(), Toast.LENGTH_LONG).show();
                        }

                    }
                }
                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loadingDialog.dissmissDialog();
                Toast.makeText(EditDataNakesActivity.this, "Tidak Ada Koneksi Internet!", Toast.LENGTH_LONG).show();
            }
        }) {
        };
        RequestQueue requestQueue = Volley.newRequestQueue(EditDataNakesActivity.this);
        requestQueue.add(stringRequest);
    }
}
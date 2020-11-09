package com.cikarastudio.cikarasimetalia.ui.notifications;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.cikarastudio.cikarasimetalia.R;
import com.cikarastudio.cikarasimetalia.activity.bumil.EditDataNakesActivity;
import com.cikarastudio.cikarasimetalia.dialog.LoadingDialog;
import com.cikarastudio.cikarasimetalia.session.SessionManager;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.app.Activity.RESULT_OK;

public class NotificationsFragment extends Fragment {

    LoadingDialog loadingDialog;
    SessionManager sessionManager;
    CircleImageView img_photoprofile;
    LinearLayout btn_updatedataUser, btn_edit_nakes, btn_logout;
    String id_user;
    EditText tv_profileEditTelp, tv_profileEditEmail, tv_profileEditPassword, tv_profileEditAlamat, tv_profileEditNama;
    ImageView btn_imgEditName, btn_imgEditTelp, btn_imgEditEmail, btn_imgEditPassword, btn_imgEditAlamat, btn_editImg;
    private Bitmap bitmap;
    private static final String URL_UPDATE = "http://simetalia.com/android/update_user.php";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_notifications, container, false);

        sessionManager = new SessionManager(getActivity());
        sessionManager.checkLogin();
        HashMap<String, String> user = sessionManager.getUserDetail();
        id_user = user.get(sessionManager.ID);


        loadingDialog = new LoadingDialog(getActivity());
        loadingDialog.startLoading();

        btn_edit_nakes = root.findViewById(R.id.btn_edit_nakes);
        btn_edit_nakes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent keEditNakes = new Intent(getActivity(), EditDataNakesActivity.class);
                startActivity(keEditNakes);
            }
        });

        btn_updatedataUser = root.findViewById(R.id.btn_updateDataUser);
        btn_updatedataUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateDetail();
            }
        });

        btn_logout = root.findViewById(R.id.btn_logout);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sessionManager.logout();
            }
        });

        tv_profileEditNama = root.findViewById(R.id.tv_profileEditNama);
        tv_profileEditTelp = root.findViewById(R.id.tv_profileEditNoHp);
        tv_profileEditEmail = root.findViewById(R.id.tv_profileEditEmail);
        tv_profileEditPassword = root.findViewById(R.id.tv_profileEditPassword);
        tv_profileEditAlamat = root.findViewById(R.id.tv_profileEditAlamat);
        img_photoprofile = root.findViewById(R.id.img_photoProfile);

        load_dataBooking();

        btn_editImg = root.findViewById(R.id.btn_editPhoto);
        btn_editImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseImg();
            }
        });

        btn_imgEditName = root.findViewById(R.id.btn_imgEditname);
        btn_imgEditName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!tv_profileEditNama.isEnabled()) {
                    tv_profileEditNama.setEnabled(true);
                    tv_profileEditNama.setFocusable(true);
                    tv_profileEditNama.setCursorVisible(true);
                    tv_profileEditNama.setFocusableInTouchMode(true);
                    tv_profileEditNama.setBackgroundColor(Color.parseColor("#D10069"));
                    tv_profileEditNama.setTextColor(Color.parseColor("#FFFFFF"));
                } else if (tv_profileEditNama.isEnabled()) {
                    tv_profileEditNama.setEnabled(false);
                    tv_profileEditNama.setFocusable(false);
                    tv_profileEditNama.setCursorVisible(false);
                    tv_profileEditNama.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    tv_profileEditNama.setTextColor(Color.parseColor("#000000"));
                }

            }
        });

        btn_imgEditTelp = root.findViewById(R.id.btn_imgEdittlp);
        btn_imgEditTelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!tv_profileEditTelp.isEnabled()) {
                    tv_profileEditTelp.setEnabled(true);
                    tv_profileEditTelp.setFocusable(true);
                    tv_profileEditTelp.setCursorVisible(true);
                    tv_profileEditTelp.setFocusableInTouchMode(true);
                    tv_profileEditTelp.setBackgroundColor(Color.parseColor("#D10069"));
                    tv_profileEditTelp.setTextColor(Color.parseColor("#FFFFFF"));
                } else if (tv_profileEditTelp.isEnabled()) {
                    tv_profileEditTelp.setEnabled(false);
                    tv_profileEditTelp.setFocusable(false);
                    tv_profileEditTelp.setCursorVisible(false);
                    tv_profileEditTelp.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    tv_profileEditTelp.setTextColor(Color.parseColor("#000000"));
                }
            }
        });

        btn_imgEditEmail = root.findViewById(R.id.btn_imgEditEmail);
        btn_imgEditEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!tv_profileEditEmail.isEnabled()) {
                    tv_profileEditEmail.setEnabled(true);
                    tv_profileEditEmail.setFocusable(true);
                    tv_profileEditEmail.setCursorVisible(true);
                    tv_profileEditEmail.setFocusableInTouchMode(true);
                    tv_profileEditEmail.setBackgroundColor(Color.parseColor("#D10069"));
                    tv_profileEditEmail.setTextColor(Color.parseColor("#FFFFFF"));
                } else if (tv_profileEditEmail.isEnabled()) {
                    tv_profileEditEmail.setEnabled(false);
                    tv_profileEditEmail.setFocusable(false);
                    tv_profileEditEmail.setCursorVisible(false);
                    tv_profileEditEmail.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    tv_profileEditEmail.setTextColor(Color.parseColor("#000000"));
                }
            }
        });

        btn_imgEditPassword = root.findViewById(R.id.btn_imgEditpassword);
        btn_imgEditPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!tv_profileEditPassword.isEnabled()) {
                    tv_profileEditPassword.setEnabled(true);
                    tv_profileEditPassword.setFocusable(true);
                    tv_profileEditPassword.setCursorVisible(true);
                    tv_profileEditPassword.setFocusableInTouchMode(true);
                    tv_profileEditPassword.setBackgroundColor(Color.parseColor("#D10069"));
                    tv_profileEditPassword.setTextColor(Color.parseColor("#FFFFFF"));
                } else if (tv_profileEditPassword.isEnabled()) {
                    tv_profileEditPassword.setEnabled(false);
                    tv_profileEditPassword.setFocusable(false);
                    tv_profileEditPassword.setCursorVisible(false);
                    tv_profileEditPassword.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    tv_profileEditPassword.setTextColor(Color.parseColor("#000000"));
                }
            }
        });

        btn_imgEditAlamat = root.findViewById(R.id.btn_imgEditAlamat);
        btn_imgEditAlamat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!tv_profileEditAlamat.isEnabled()) {
                    tv_profileEditAlamat.setEnabled(true);
                    tv_profileEditAlamat.setFocusable(true);
                    tv_profileEditAlamat.setCursorVisible(true);
                    tv_profileEditAlamat.setFocusableInTouchMode(true);
                    tv_profileEditAlamat.setBackgroundColor(Color.parseColor("#D10069"));
                    tv_profileEditAlamat.setTextColor(Color.parseColor("#FFFFFF"));
                } else if (tv_profileEditAlamat.isEnabled()) {
                    tv_profileEditAlamat.setEnabled(false);
                    tv_profileEditAlamat.setFocusable(false);
                    tv_profileEditAlamat.setCursorVisible(false);
                    tv_profileEditAlamat.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    tv_profileEditAlamat.setTextColor(Color.parseColor("#000000"));
                }
            }
        });

        return root;
    }


    private void chooseImg() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Pilih Foto"), 1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filepath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), filepath);
                img_photoprofile.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private void updateDetail() {
        loadingDialog.startLoading();
//        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.JPEG, 100,byteArrayOutputStream);
//
//        byte [] imageByteArray = byteArrayOutputStream.toByteArray();
//        String encodedImage = Base64.encodeToString(imageByteArray,Base64.DEFAULT);
//        final String avatar = encodedImage;

        final String name = tv_profileEditNama.getText().toString().trim();
        final String telp = tv_profileEditTelp.getText().toString().trim();
        final String email = tv_profileEditEmail.getText().toString().trim();
        final String password = tv_profileEditPassword.getText().toString().trim();
        final String alamat = tv_profileEditAlamat.getText().toString().trim();

        final String utanggal_sekarang;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        utanggal_sekarang = dateFormat.format(cal.getTime());
        final String tgl_sekarang = utanggal_sekarang;

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_UPDATE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")) {
                                Toast.makeText(getContext(), "Update Berhasil! ", Toast.LENGTH_SHORT).show();
                                loadingDialog.dissmissDialog();
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getContext(), "Update Gagal ", Toast.LENGTH_SHORT).show();
                            loadingDialog.dissmissDialog();
                        }

                    }
                }
                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Update Gagal: Jaringan Terganggu " + error.toString(), Toast.LENGTH_SHORT).show();
                loadingDialog.dissmissDialog();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id", id_user);
                params.put("name", name);
                params.put("telp", telp);
                params.put("email", email);
                params.put("password", password);
                params.put("alamat", alamat);
//                params.put("avatar",avatar);
                params.put("tanggal_sekarang", tgl_sekarang);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);

    }


    private void load_dataBooking() {
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

                                String name = read.getString("name").trim();
                                String telp = read.getString("telp").trim();
                                String email = read.getString("email").trim();
                                String password = read.getString("password").trim();
                                String alamat = read.getString("alamat").trim();
                                String photo = read.getString("avatar").trim();

                                tv_profileEditNama.setText(name);
                                tv_profileEditTelp.setText(telp);
                                tv_profileEditEmail.setText(email);
//                                tv_profileEditPassword.setText(password);
                                tv_profileEditAlamat.setText(alamat);

                                String imageUrl = "http://simetalia.com/img/users/" + photo;

                                Picasso.with(getContext()).load(imageUrl).fit().centerCrop().into(img_photoprofile);
                                //hilangkan loading
                                loadingDialog.dissmissDialog();

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            loadingDialog.dissmissDialog();
                            Toast.makeText(getContext(), "Data Akun Tidak Ada!" + e.toString(), Toast.LENGTH_LONG).show();
                        }

                    }
                }
                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loadingDialog.dissmissDialog();
                Toast.makeText(getContext(), "Tidak Ada Koneksi Internet!", Toast.LENGTH_LONG).show();
            }
        }) {
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }


}
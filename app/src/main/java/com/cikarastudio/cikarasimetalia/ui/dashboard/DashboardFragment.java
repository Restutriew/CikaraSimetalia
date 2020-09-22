package com.cikarastudio.cikarasimetalia.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.cikarastudio.cikarasimetalia.R;
import com.cikarastudio.cikarasimetalia.activity.DetailBumilActivity;
import com.cikarastudio.cikarasimetalia.activity.TambahDataIbuHamilActivity;
import com.cikarastudio.cikarasimetalia.adapter.MyBumilAdapter;
import com.cikarastudio.cikarasimetalia.dialog.LoadingDialog;
import com.cikarastudio.cikarasimetalia.model.Bumil;
import com.cikarastudio.cikarasimetalia.session.SessionManager;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DashboardFragment extends Fragment {

    LoadingDialog loadingDialog;
    FloatingActionButton btn_tambahDataBumil;
    RecyclerView recyclerView;
    SessionManager sessionManager;
    private MyBumilAdapter myBumilAdapter;
    String id_user;
    private ArrayList<Bumil> bumilList;
    LinearLayout scrollView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);


        btn_tambahDataBumil = (FloatingActionButton) root.findViewById(R.id.btn_tambahDataBumil);

        sessionManager = new SessionManager(getActivity());
        HashMap<String, String> user = sessionManager.getUserDetail();
        id_user = user.get(sessionManager.ID);


        loadingDialog = new LoadingDialog(getActivity());
        loadingDialog.startLoading();

        bumilList = new ArrayList<>();
        recyclerView = root.findViewById(R.id.list_bumil);
        recyclerView.setHasFixedSize(true);
        parseJSON();

        scrollView = root.findViewById(R.id.scrollView1);
        scrollView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent scroll = new Intent(getActivity(), DetailBumilActivity.class);
//                startActivity(scroll);
            }
        });

        btn_tambahDataBumil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tmbhdatabumil = new Intent(getActivity(), TambahDataIbuHamilActivity.class);
                startActivity(tmbhdatabumil);
            }
        });
        return root;
    }

    private void parseJSON() {
        String URL_READBOOKING = "http://simetalia.com/pkm/webservice/bumildesa/" + id_user;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_READBOOKING,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("read");

                            if (success.equals("1")) {
                                for (int i = 0; i < jsonArray.length(); i++) {

                                    JSONObject object = jsonArray.getJSONObject(i);

                                    final String id_bumil = object.getString("id").trim();
                                    String nama_bumil = object.getString("nama_bumil").trim();
                                    String nik = object.getString("nik").trim();
                                    String status = object.getString("agama").trim();


                                    bumilList.add(new Bumil(id_bumil, nama_bumil, nik, status));
                                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                                    myBumilAdapter = new MyBumilAdapter(getContext(), bumilList);
                                    recyclerView.setAdapter(myBumilAdapter);
                                    myBumilAdapter.setOnItemClickCallback(new MyBumilAdapter.OnItemClickCallback() {
                                        @Override
                                        public void onItemClicked(Bumil data) {
//                                            Toast.makeText(getActivity(), "Data Booking Tidak Ada! " +data.getId_bumil(), Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                    loadingDialog.dissmissDialog();
                                }
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getActivity(), "Data Ibu Hamil Tidak Ada!" + e.toString(), Toast.LENGTH_SHORT).show();
                        }

                    }
                }
                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "Koneksi Gagal!" + error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }
}
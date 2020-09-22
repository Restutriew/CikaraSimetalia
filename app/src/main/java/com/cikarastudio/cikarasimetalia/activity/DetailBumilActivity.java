package com.cikarastudio.cikarasimetalia.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.cikarastudio.cikarasimetalia.R;

public class DetailBumilActivity extends AppCompatActivity {

    Button btn_keKunjungan,btn_hapusBumil;
    ImageView btn_back;
    CardView cr_detailBUmil, cr_detailBapak, cr_detailKeluarga, cr_detailMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_bumil);

        btn_back = findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btn_hapusBumil = findViewById(R.id.btn_hapusBumil);
        btn_hapusBumil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btn_keKunjungan = findViewById(R.id.btn_keKunjungan);
        btn_keKunjungan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent keKunjungan = new Intent(DetailBumilActivity.this, DataKunjunganBaruActivity.class);
                startActivity(keKunjungan);
            }
        });

        cr_detailBUmil = findViewById(R.id.cr_detailIbu);
        cr_detailBUmil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent keEditBumil = new Intent(DetailBumilActivity.this, EditIbuHamilActivity.class);
                startActivity(keEditBumil);
            }
        });


        cr_detailBapak = findViewById(R.id.cr_detailBapa);
        cr_detailBapak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent keEditBapak = new Intent(DetailBumilActivity.this, EditDataSuamiActivity.class);
                startActivity(keEditBapak);
            }
        });

        cr_detailKeluarga = findViewById(R.id.cr_detailKeluarga);
        cr_detailKeluarga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent keEditKeluarga = new Intent(DetailBumilActivity.this, EditDataKeluargaActivity.class);
                startActivity(keEditKeluarga);
            }
        });

        cr_detailMap = findViewById(R.id.cr_detailPeta);
        cr_detailMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent keEditPeta = new Intent(DetailBumilActivity.this, EditDataPetaActivity.class);
                startActivity(keEditPeta);
            }
        });

    }
}
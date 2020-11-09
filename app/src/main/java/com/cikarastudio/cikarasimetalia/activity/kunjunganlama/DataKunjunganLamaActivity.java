package com.cikarastudio.cikarasimetalia.activity.kunjunganlama;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.cikarastudio.cikarasimetalia.R;
import com.cikarastudio.cikarasimetalia.activity.kunjunganbaru.DetailKunjunganBaruActivity;
import com.cikarastudio.cikarasimetalia.activity.kunjunganbaru.EditKunjunganBaruDataObyektifActivity;
import com.cikarastudio.cikarasimetalia.model.KunjunganBaruModel;
import com.cikarastudio.cikarasimetalia.model.KunjunganLamaModel;

public class DataKunjunganLamaActivity extends AppCompatActivity {

    public static final String KUNJUNGANLAMA_DATA = "extra_data";
    String id_kunjunganLamaget;
    LinearLayout editSubyektifLama, editObyektifLama, editAnalisisLama,
            editPlanLama, editP4KLama, editRujukanLama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_kunjungan_lama);

        KunjunganLamaModel KunjunganLamaData = getIntent().getParcelableExtra(KUNJUNGANLAMA_DATA);
        final String id_kunjunganLama = KunjunganLamaData.getId_kunjunganLama();
        id_kunjunganLamaget = id_kunjunganLama;

//        Toast.makeText(DataKunjunganLamaActivity.this, "Data Kehamilan Tidak Ada!" + id_kunjunganLamaget, Toast.LENGTH_SHORT).show();

        editSubyektifLama = findViewById(R.id.editSubyektifLama);
        editObyektifLama = findViewById(R.id.editObyektifLama);
        editAnalisisLama = findViewById(R.id.editAnalisisLama);
        editPlanLama = findViewById(R.id.editPlanLama);
        editP4KLama = findViewById(R.id.editP4KLama);
        editRujukanLama = findViewById(R.id.editRujukanLama);

        editSubyektifLama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent keSubyektifLama = new Intent(DataKunjunganLamaActivity.this, EditKunjunganLamaDataSubyektifActivity.class);
                keSubyektifLama.putExtra(EditKunjunganLamaDataSubyektifActivity.ID_KUNJUNGANLAMA, id_kunjunganLamaget);
                startActivity(keSubyektifLama);
            }
        });

        editObyektifLama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent keObyektifLama = new Intent(DataKunjunganLamaActivity.this, EditKunjunganLamaDataObyektifActivity.class);
                keObyektifLama.putExtra(EditKunjunganLamaDataObyektifActivity.ID_KUNJUNGANLAMA, id_kunjunganLamaget);
                startActivity(keObyektifLama);
            }
        });

        editAnalisisLama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent keAnalisisLama = new Intent(DataKunjunganLamaActivity.this, EditKunjunganLamaDataAnalisisActivity.class);
                keAnalisisLama.putExtra(EditKunjunganLamaDataAnalisisActivity.ID_KUNJUNGANLAMA, id_kunjunganLamaget);
                startActivity(keAnalisisLama);
            }
        });

        editPlanLama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent kePlanLama = new Intent(DataKunjunganLamaActivity.this, EditKunjunganLamaDataPlanActivity.class);
                kePlanLama.putExtra(EditKunjunganLamaDataPlanActivity.ID_KUNJUNGANLAMA, id_kunjunganLamaget);
                startActivity(kePlanLama);
            }
        });

        editP4KLama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent keP4KLama = new Intent(DataKunjunganLamaActivity.this, EditKunjunganLamaDataP4KActivity.class);
                keP4KLama.putExtra(EditKunjunganLamaDataP4KActivity.ID_KUNJUNGANLAMA, id_kunjunganLamaget);
                startActivity(keP4KLama);
            }
        });

        editRujukanLama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent keRujukanLama = new Intent(DataKunjunganLamaActivity.this, EditKunjunganLamaDataRujukanActivity.class);
                keRujukanLama.putExtra(EditKunjunganLamaDataRujukanActivity.ID_KUNJUNGANLAMA, id_kunjunganLamaget);
                startActivity(keRujukanLama);
            }
        });
    }
}
package com.cikarastudio.cikarasimetalia.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.cikarastudio.cikarasimetalia.R;

import java.util.Calendar;

public class TambahDataIbuHamilActivity extends AppCompatActivity {
    TextView et_tgllahir,et_golDarah;
    Spinner et_agama, et_pendidikan;
    ImageView btn_back;
    Button btn_simpanDataIbuHamil;
    Dialog myDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_data_ibu_hamil);

        myDialog = new Dialog(this);

        btn_back = findViewById(R.id.btn_back);
        btn_simpanDataIbuHamil = findViewById(R.id.btn_simpanDataBumil);
        et_tgllahir = findViewById(R.id.et_tambahBumilTglLahir);
        et_golDarah = findViewById(R.id.et_tambahBumilGolDarah);
        et_agama = findViewById(R.id.et_tambahBumilAgama);
        et_pendidikan = findViewById(R.id.et_tambahBumilPendidikan);

        btn_simpanDataIbuHamil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        et_tgllahir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int currentDate = calendar.get(Calendar.DATE);
                int currentMonth = calendar.get(Calendar.MONTH);
                int currentYear = calendar.get(Calendar.YEAR);

                DatePickerDialog datePickerDialog = new DatePickerDialog(TambahDataIbuHamilActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int date) {
                        int monthplus = month + 1;
                        String monthString = String.valueOf(monthplus);
                        String dateString = String.valueOf(date);
                        if (monthplus < 10) {
                            monthString = "0" + monthplus;
                        }
                        if (date < 10) {

                            dateString = "0" + date;
                        }
                        String dateAllString = year + "-" + monthString + "-" + dateString;
                        et_tgllahir.setText(dateAllString);

                    }
                }, currentYear, currentMonth, currentDate);
                datePickerDialog.show();
            }
        });

        et_golDarah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupGolDar();
            }
        });

        ArrayAdapter<String> agamaAdapter = new ArrayAdapter<String>(TambahDataIbuHamilActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.agama));
        agamaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        et_agama.setAdapter(agamaAdapter);

        ArrayAdapter<String> pendidikanAdapter = new ArrayAdapter<String>(TambahDataIbuHamilActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.pendidikan));
        pendidikanAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        et_pendidikan.setAdapter(pendidikanAdapter);

    }

    private void popupGolDar() {
        myDialog.setContentView(R.layout.popup_golongan_darah);
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }
}
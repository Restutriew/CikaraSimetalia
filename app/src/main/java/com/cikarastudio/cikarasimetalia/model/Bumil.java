package com.cikarastudio.cikarasimetalia.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Bumil implements Parcelable {
    String id_bumil;
    String nama;
    String nik;
    String resiko;

    public Bumil(String id_bumil, String nama, String nik, String resiko) {
        this.id_bumil = id_bumil;
        this.nama = nama;
        this.nik = nik;
        this.resiko = resiko;
    }

    public String getId_bumil() {
        return id_bumil;
    }

    public void setId_bumil(String id_bumil) {
        this.id_bumil = id_bumil;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getResiko() {
        return resiko;
    }

    public void setResiko(String resiko) {
        this.resiko = resiko;
    }

    public static Creator<Bumil> getCREATOR() {
        return CREATOR;
    }

    protected Bumil(Parcel in) {
        id_bumil = in.readString();
        nama = in.readString();
        nik = in.readString();
        resiko = in.readString();
    }

    public static final Creator<Bumil> CREATOR = new Creator<Bumil>() {
        @Override
        public Bumil createFromParcel(Parcel in) {
            return new Bumil(in);
        }

        @Override
        public Bumil[] newArray(int size) {
            return new Bumil[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id_bumil);
        parcel.writeString(nama);
        parcel.writeString(nik);
        parcel.writeString(resiko);
    }
}

package com.cikarastudio.cikarasimetalia.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cikarastudio.cikarasimetalia.R;
import com.cikarastudio.cikarasimetalia.model.KunjunganBaruModel;
import com.cikarastudio.cikarasimetalia.model.KunjunganLamaModel;

import java.util.ArrayList;

public class MyKunjunganLamaAdapter extends RecyclerView.Adapter<MyKunjunganLamaAdapter.MyKunjunganLamaViewHolder> {

    private Context mContext;
    private ArrayList<KunjunganLamaModel> mKunjuganLamaList;

    private MyKunjunganLamaAdapter.OnItemClickCallback onItemClickCallback;


    public void setOnItemClickCallback(MyKunjunganLamaAdapter.OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    public interface OnItemClickCallback {
        void onItemClicked(KunjunganLamaModel data);
    }

    public MyKunjunganLamaAdapter(Context mContext, ArrayList<KunjunganLamaModel> mKunjuganLamaList) {
        this.mContext = mContext;
        this.mKunjuganLamaList = mKunjuganLamaList;
    }

    @NonNull
    @Override
    public MyKunjunganLamaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_kunjungan_lama, parent, false);
        return new MyKunjunganLamaAdapter.MyKunjunganLamaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyKunjunganLamaViewHolder holder, int position) {
        KunjunganLamaModel currentItem = mKunjuganLamaList.get(position);
        String tanggal = currentItem.getTanggal();
        String keluhan = currentItem.getKeluhan();

        holder.tanggal.setText(tanggal);
        holder.keluhan.setText(keluhan);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallback.onItemClicked(mKunjuganLamaList.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mKunjuganLamaList.size();
    }

    public class MyKunjunganLamaViewHolder extends RecyclerView.ViewHolder {
        public TextView tanggal;
        public TextView keluhan;

        MyKunjunganLamaViewHolder(@NonNull View itemView) {
            super(itemView);
            tanggal = itemView.findViewById(R.id.tgl_pemeriksaan);
            keluhan = itemView.findViewById(R.id.keluhan);
        }
    }
}

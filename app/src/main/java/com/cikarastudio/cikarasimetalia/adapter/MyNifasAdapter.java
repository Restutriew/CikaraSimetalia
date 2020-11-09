package com.cikarastudio.cikarasimetalia.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cikarastudio.cikarasimetalia.R;
import com.cikarastudio.cikarasimetalia.model.KunjunganLamaModel;
import com.cikarastudio.cikarasimetalia.model.NifasModel;

import java.util.ArrayList;

public class MyNifasAdapter extends RecyclerView.Adapter<MyNifasAdapter.MyNifasViewHolder> {

    private Context mContext;
    private ArrayList<NifasModel> mNifasList;

    private MyNifasAdapter.OnItemClickCallback onItemClickCallback;

    public void setOnItemClickCallback(MyNifasAdapter.OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    public interface OnItemClickCallback {
        void onItemClicked(NifasModel data);
    }

    public MyNifasAdapter(Context mContext, ArrayList<NifasModel> mNifasList) {
        this.mContext = mContext;
        this.mNifasList = mNifasList;
    }

    @NonNull
    @Override
    public MyNifasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_nifas, parent, false);
        return new MyNifasAdapter.MyNifasViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyNifasViewHolder holder, int position) {
        NifasModel currentItem = mNifasList.get(position);
        String tanggal = currentItem.getTanggal_kunjungan();
        String kunjungan_ke = currentItem.getKunjungan_ke();

        String kunjungankeFix = "Kunjungan Nifas Ke - "+kunjungan_ke;

        holder.tanggal.setText(tanggal);
        holder.kunjunganKe.setText(kunjungankeFix);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallback.onItemClicked(mNifasList.get(holder.getAdapterPosition()));
            }
        });

    }

    @Override
    public int getItemCount() {
        return mNifasList.size();
    }

    public class MyNifasViewHolder extends RecyclerView.ViewHolder {
        public TextView tanggal;
        public TextView kunjunganKe;

        MyNifasViewHolder(@NonNull View itemView) {
            super(itemView);
            tanggal = itemView.findViewById(R.id.tgl_kunjungan);
            kunjunganKe = itemView.findViewById(R.id.kunjunganNifasKe);
        }
    }
}

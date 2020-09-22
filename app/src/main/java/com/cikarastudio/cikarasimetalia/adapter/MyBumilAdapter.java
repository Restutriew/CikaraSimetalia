package com.cikarastudio.cikarasimetalia.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cikarastudio.cikarasimetalia.R;
import com.cikarastudio.cikarasimetalia.model.Bumil;

import java.util.ArrayList;

public class MyBumilAdapter extends RecyclerView.Adapter<MyBumilAdapter.MyBumilViewHolder> {

    private Context mContext;
    private ArrayList<Bumil> mBumilList;

    private OnItemClickCallback onItemClickCallback;


    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    public interface OnItemClickCallback {
        void onItemClicked(Bumil data);
    }

    public MyBumilAdapter(Context context, ArrayList<Bumil> bumilList) {
        mContext = context;
        mBumilList = bumilList;
    }

    @NonNull
    @Override
    public MyBumilViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_bumil, parent, false);
        return new MyBumilAdapter.MyBumilViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyBumilViewHolder holder, int position) {
        Bumil currentItem = mBumilList.get(position);

        String id_bumil = currentItem.getId_bumil();
        String nama_bumil = currentItem.getNama();
        String no_nik = currentItem.getNik();
        String resiko = currentItem.getResiko();

//        holder.id_booking.setText(id_booking);
        holder.nama.setText(nama_bumil);
        holder.no_nik.setText(no_nik);
        holder.resiko.setText(resiko);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallback.onItemClicked(mBumilList.get(holder.getAdapterPosition()));
            }
        });
    }

    public MyBumilAdapter(Context mContext, ArrayList<Bumil> mBumilList, OnItemClickCallback onItemClickCallback) {
        this.mContext = mContext;
        this.mBumilList = mBumilList;
        this.onItemClickCallback = onItemClickCallback;
    }

    @Override
    public int getItemCount() {
        return mBumilList.size();
    }

    public class MyBumilViewHolder extends RecyclerView.ViewHolder {
        public TextView nama;
        public TextView no_nik;
        public TextView resiko;
        MyBumilViewHolder(@NonNull View itemView) {
            super(itemView);
            nama = itemView.findViewById(R.id.namaBumil);
            no_nik = itemView.findViewById(R.id.noNikBumil);
            resiko = itemView.findViewById(R.id.resiko);
        }
    }
}

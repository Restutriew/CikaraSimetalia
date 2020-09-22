package com.cikarastudio.cikarasimetalia.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;

import com.cikarastudio.cikarasimetalia.R;

public class LoadingDialog {

    private Activity activity;
    private AlertDialog dialog;

     public LoadingDialog(Activity myActivity) {
        activity = myActivity;
    }

     public void startLoading(){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.loading_dialog,null));
        builder.setCancelable(true);

        dialog = builder.create();
        dialog.show();
    }

    public void dissmissDialog(){
        dialog.dismiss();
    }
}

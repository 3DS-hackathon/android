package com.github.dan4ik95dv.app.util;

import android.app.ProgressDialog;
import android.content.Context;

import com.github.dan4ik95dv.app.R;


public class Progress {

    private Context context = null;
    private ProgressDialog progressDialog = null;

    public Progress(Context context) {
        this.context = context;
    }

    public boolean isShowing() {
        return progressDialog != null;
    }

    public void show() {
        if (progressDialog == null || (!progressDialog.isShowing())) {
            progressDialog = new ProgressDialog(context);
            progressDialog.setCancelable(false);
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage(context.getString(R.string.connection));
            progressDialog.show();
        }
    }

    public void close() {
        if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

}

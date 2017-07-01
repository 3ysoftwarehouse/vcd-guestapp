package br.com.a3ysoftwarehouse.vcdguest.ui.base;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import be.appfoundry.nfclibrary.activities.NfcActivity;
import br.com.a3ysoftwarehouse.vcdguest.R;

/**
 * Created by Iago Belo on 21/06/17.
 */

public abstract class BaseNfcActivity<T extends IBasePresenter> extends NfcActivity
        implements IBaseView {
    // Constants
    private static final String TAG = "BaseActivity";

    // ProgressDialog
    private Dialog mProgressDialog;

    // Presenter
    private T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();

        mPresenter.onAttach();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mPresenter.onDettach();
    }

    private ProgressDialog showLoadingDialog(Context context) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.show();

        if (progressDialog.getWindow() != null) {
            progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }

        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(true);
        progressDialog.setCanceledOnTouchOutside(false);

        return progressDialog;
    }

    @Override
    public void showToast(String msg) {
        showToast(msg, Toast.LENGTH_SHORT);
    }

    @Override
    public void showToast(String msg, int time) {
        Toast.makeText(this, msg, time).show();
    }

    @Override
    public void showProgress(boolean show) {
        if (show) {
            mProgressDialog = showLoadingDialog(this);

        } else {
            if (mProgressDialog != null && mProgressDialog.isShowing()) {
                mProgressDialog.cancel();
            }
        }
    }

    public void showSnackBar(String message) {
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), message,
                Snackbar.LENGTH_SHORT);

        View sbView = snackbar.getView();

        TextView tv = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        tv.setTextColor(ContextCompat.getColor(this, R.color.white));

        snackbar.show();
    }

    protected void setPresenter(T t) {
        this.mPresenter = t;
    }

    protected T getPresenter() {
        return mPresenter;
    }
}

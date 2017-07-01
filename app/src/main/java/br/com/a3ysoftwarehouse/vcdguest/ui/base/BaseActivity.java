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
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import br.com.a3ysoftwarehouse.vcdguest.R;

/**
 * Created by Iago Belo on 06/06/17.
 */

public abstract class BaseActivity<T extends IBasePresenter> extends AppCompatActivity
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
    protected void onResume() {
        super.onResume();

        if(mPresenter != null) mPresenter.onAttach();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(mPresenter != null) mPresenter.onDettach();
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

    protected T getPresenter() {
        return mPresenter;
    }

    protected void setPresenter(T t) {
        mPresenter = t;
    }
}

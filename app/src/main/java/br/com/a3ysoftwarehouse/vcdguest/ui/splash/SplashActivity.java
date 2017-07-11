package br.com.a3ysoftwarehouse.vcdguest.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import br.com.a3ysoftwarehouse.vcdguest.R;
import br.com.a3ysoftwarehouse.vcdguest.ui.base.BaseActivity;
import br.com.a3ysoftwarehouse.vcdguest.ui.login.LoginActivity;
import br.com.a3ysoftwarehouse.vcdguest.ui.main.MainActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends BaseActivity<ISplashPresenter> implements ISplashView {
    // Constants
    private static final String TAG = "SplashActivity";

    // Views
    @BindView(R.id.splash_pb) ProgressBar mSplashPb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
    }

    @Override
    protected ISplashPresenter initPresenter() {
        return new SplashPresenter(this);
    }

    @Override
    public void showSplashProgress() {
        if (mSplashPb.getVisibility() != View.VISIBLE) mSplashPb.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideSplashProgress() {
        if (mSplashPb.getVisibility() != View.GONE) mSplashPb.setVisibility(View.GONE);
    }

    @Override
    public void openLoginActivity() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    @Override
    public void openMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}

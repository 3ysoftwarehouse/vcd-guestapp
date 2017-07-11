package br.com.a3ysoftwarehouse.vcdguest.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import br.com.a3ysoftwarehouse.vcdguest.R;
import br.com.a3ysoftwarehouse.vcdguest.ui.base.BaseActivity;
import br.com.a3ysoftwarehouse.vcdguest.ui.main.MainActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity<ILoginPresenter> implements ILoginView {
    // Constants
    private static final String TAG = "LoginActivity";

    // Views
    @BindView(R.id.user_edt) TextView mUserTv;
    @BindView(R.id.password_edt) TextView mPasswordEdt;
    @BindView(R.id.sign_in_bt) Button mSignInBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.sign_in_bt)
    public void onSignInBtClick() {
        String user = mUserTv.getText().toString();
        String password = mPasswordEdt.getText().toString();

        getPresenter().onSignInBtClick(user, password);
    }

    @Override
    protected ILoginPresenter initPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    public void openMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}

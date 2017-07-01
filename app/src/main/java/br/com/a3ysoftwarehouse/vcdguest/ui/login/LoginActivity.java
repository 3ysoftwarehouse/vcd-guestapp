package br.com.a3ysoftwarehouse.vcdguest.ui.login;

import android.os.Bundle;

import br.com.a3ysoftwarehouse.vcdguest.R;
import br.com.a3ysoftwarehouse.vcdguest.ui.base.BaseActivity;

public class LoginActivity extends BaseActivity<ILoginPresenter> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setPresenter(new LoginPresenter<>(this));

    }
}

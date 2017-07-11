package br.com.a3ysoftwarehouse.vcdguest.ui.login;

import android.support.annotation.NonNull;

import br.com.a3ysoftwarehouse.vcdguest.R;
import br.com.a3ysoftwarehouse.vcdguest.app.App;
import br.com.a3ysoftwarehouse.vcdguest.ui.base.BasePresenter;

/**
 * Created by Iago Belo on 22/06/17.
 */

public class LoginPresenter extends BasePresenter<ILoginView> implements ILoginPresenter {
    // Constants
    private static final String USER = "vcdguest";
    private static final String PASSWORD = "vcd12345";

    public LoginPresenter(ILoginView iLoginView) {
        super(iLoginView);
    }

    @Override
    public void onAttach() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDetach() {

    }

    @Override
    public void onSignInBtClick(String user, String password) {
        getView().showProgress(true);

        if (user != null && !user.isEmpty() && password != null && !password.isEmpty()) {
            getDataManager().setIsLogged(true);

            getView().showProgress(false);
            getView().openMainActivity();

        } else {
            getView().showProgress(false);
            getView().showToast(getString(R.string.login_error_msg));
        }
    }

    @NonNull
    private String getString(int id) {
        return App.getContext().getResources().getString(id);
    }
}

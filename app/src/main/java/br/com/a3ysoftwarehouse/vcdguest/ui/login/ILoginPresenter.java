package br.com.a3ysoftwarehouse.vcdguest.ui.login;

import br.com.a3ysoftwarehouse.vcdguest.ui.base.IBasePresenter;

/**
 * Created by Iago Belo on 22/06/17.
 */

public interface ILoginPresenter extends IBasePresenter {
    void onSignInBtClick(String user, String password);
}

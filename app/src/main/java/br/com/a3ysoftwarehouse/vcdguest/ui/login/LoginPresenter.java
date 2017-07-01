package br.com.a3ysoftwarehouse.vcdguest.ui.login;

import br.com.a3ysoftwarehouse.vcdguest.ui.base.BasePresenter;
import br.com.a3ysoftwarehouse.vcdguest.ui.base.IBaseView;

/**
 * Created by Iago Belo on 22/06/17.
 */

public class LoginPresenter<ILoginView> extends BasePresenter implements ILoginPresenter {

    public LoginPresenter(IBaseView iBaseView) {
        super(iBaseView);
    }

    @Override
    public void onAttach() {

    }

    @Override
    public void onDettach() {

    }
}

package br.com.a3ysoftwarehouse.vcdguest.ui.splash;

import br.com.a3ysoftwarehouse.vcdguest.ui.base.IBaseView;

/**
 * Created by Iago Belo on 06/07/17.
 */

public interface ISplashView extends IBaseView {
    void showSplashProgress();

    void hideSplashProgress();

    void openLoginActivity();

    void openMainActivity();
}

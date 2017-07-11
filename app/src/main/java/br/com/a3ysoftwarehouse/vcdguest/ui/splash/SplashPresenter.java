package br.com.a3ysoftwarehouse.vcdguest.ui.splash;

import android.support.annotation.NonNull;

import br.com.a3ysoftwarehouse.vcdguest.R;
import br.com.a3ysoftwarehouse.vcdguest.app.App;
import br.com.a3ysoftwarehouse.vcdguest.data.IDataManager;
import br.com.a3ysoftwarehouse.vcdguest.ui.base.BasePresenter;

/**
 * Created by Iago Belo on 06/07/17.
 */

public class SplashPresenter extends BasePresenter<ISplashView> implements ISplashPresenter,
        IDataManager.ISyncListener {
    // Constants
    private static final String TAG = "SplashPresenter";

    public SplashPresenter(ISplashView iSplashView) {
        super(iSplashView);
    }

    @Override
    public void onAttach() {

    }

    @Override
    public void onResume() {
        getView().showSplashProgress();

        getDataManager().syncPassengers(this);
    }

    @Override
    public void onDetach() {

    }

    @Override
    public void onSuccess() {
        if (getDataManager().getIsLogged()) getView().openMainActivity();
        else getView().openLoginActivity();
    }

    @Override
    public void onFailed() {
        getView().hideSplashProgress();
        getView().showToast(getString(R.string.sync_failed_msg));

        if (getDataManager().getIsLogged()) getView().openMainActivity();
        else getView().openLoginActivity();
    }

    @NonNull
    private String getString(int id) {
        return App.getContext().getResources().getString(id);
    }
}

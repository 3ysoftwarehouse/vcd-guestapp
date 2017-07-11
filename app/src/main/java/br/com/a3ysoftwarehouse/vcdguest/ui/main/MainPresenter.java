package br.com.a3ysoftwarehouse.vcdguest.ui.main;

import android.util.Log;

import br.com.a3ysoftwarehouse.vcdguest.R;
import br.com.a3ysoftwarehouse.vcdguest.observer.BackupTagObservable;
import br.com.a3ysoftwarehouse.vcdguest.observer.RestoreTagObservable;
import br.com.a3ysoftwarehouse.vcdguest.ui.base.BasePresenter;
import br.com.a3ysoftwarehouse.vcdguest.util.Utils;

/**
 * Created by Iago Belo on 22/06/2017.
 */

public class MainPresenter extends BasePresenter<IMainView> implements IMainPresenter,
        BackupTagObservable.IBackupTagObserver, RestoreTagObservable.IRestoreTagObserver {
    // Constants
    private static final String TAG = "MainPresenter";

    // Observables
    private BackupTagObservable mBackupTagObservable;
    private RestoreTagObservable mRestoreTagObservable;

    public MainPresenter(IMainView iMainView) {
        super(iMainView);

        mBackupTagObservable = BackupTagObservable.getInstance();
        mRestoreTagObservable = RestoreTagObservable.getInstance();
    }

    @Override
    public void onAttach() {
        Log.i(TAG, "onAttach()");
    }

    @Override
    public void onResume() {
        Log.i(TAG, "onResume()");

        mBackupTagObservable.subscribe(this);
        mRestoreTagObservable.subscribe(this);
    }

    @Override
    public void onDetach() {
        Log.i(TAG, "onDetach()");

        mBackupTagObservable.unsubscribe(this);
        mRestoreTagObservable.unsubscribe(this);
    }

    @Override
    public void onSyncPassengersItemClick() {

    }

    @Override
    public void onTagBackupItemClick() {
        getView().showProgress(true);

        getDataManager().backupTagsToServer(null);
    }

    @Override
    public void onRestoreTagItemClick() {
        getView().showProgress(true);

        getDataManager().restoreTagsFromServer(null);
    }

    @Override
    public void onBackupTagSuccess() {
        getView().showProgress(false);
    }

    @Override
    public void onBackupTagFailed() {
        getView().showProgress(false);
        getView().showToast(Utils.getString(R.string.error_msg));
    }

    @Override
    public void onRestoreTagSuccess() {
        getView().showProgress(false);
    }

    @Override
    public void onRestoreTagFailed() {
        getView().showProgress(false);
        getView().showToast(Utils.getString(R.string.error_msg));
    }
}

package br.com.a3ysoftwarehouse.vcdguest.ui.listPassenger;

import android.util.Log;
import android.view.View;

import br.com.a3ysoftwarehouse.vcdguest.data.model.Passenger;
import br.com.a3ysoftwarehouse.vcdguest.observer.BackupTagObservable;
import br.com.a3ysoftwarehouse.vcdguest.observer.NfcIdObservable;
import br.com.a3ysoftwarehouse.vcdguest.observer.RestoreTagObservable;
import br.com.a3ysoftwarehouse.vcdguest.ui.base.BasePresenter;

/**
 * Created by Iago Belo on 22/06/17.
 */

public class ListPassengersPresenter extends BasePresenter<IListPassengersView>
        implements IListPassengersPresenter, NfcIdObservable.INfcTagIdObserver,
        BackupTagObservable.IBackupTagObserver, RestoreTagObservable.IRestoreTagObserver {

    // Constants
    private static final String TAG = "ListPassengersPresenter";

    // Last detected tag.
    private String mLastTag;

    // Last detected tag time.
    private long mLastTagTime;

    // Observables
    private NfcIdObservable mNfcIdObservable;
    private BackupTagObservable mBackupTagObservable;
    private RestoreTagObservable mRestoreTagObservable;

    public ListPassengersPresenter(IListPassengersView iListPassengersView) {
        super(iListPassengersView);

        mNfcIdObservable = NfcIdObservable.getInstance();
        mBackupTagObservable = BackupTagObservable.getInstance();
        mRestoreTagObservable = RestoreTagObservable.getInstance();
    }

    @Override
    public void onAttach() {
        mNfcIdObservable.subscribe(this);
        mBackupTagObservable.subscribe(this);
        mRestoreTagObservable.subscribe(this);
    }

    @Override
    public void onResume() {
        getView().showProgress(true);

        setRecyclerViewData();

        getView().showProgress(false);
    }

    @Override
    public void onDetach() {
        mNfcIdObservable.unsubscribe(this);
        mBackupTagObservable.unsubscribe(this);
        mRestoreTagObservable.unsubscribe(this);
    }

    @Override
    public void onItemClick(View view, int position, Passenger passenger) {
        getView().openPassengerActivity(passenger);
    }

    @Override
    public void onNewTag(String tag) {
        if (canSearchPassenger(tag)) {
            Passenger passenger = getDataManager().getPassengerByTag(tag);

            if (passenger != null) {
                mLastTagTime = System.currentTimeMillis();
                mLastTag = tag;

                getView().openPassengerActivity(passenger);
            }
        } else {
            Log.i(TAG, "Aguarde...");
        }
    }

    @Override
    public void onBackupTagSuccess() {

    }

    @Override
    public void onBackupTagFailed() {
    }

    @Override
    public void onRestoreTagSuccess() {
        setRecyclerViewData();
    }

    @Override
    public void onRestoreTagFailed() {
    }

    private void setRecyclerViewData() {
        getView().setRecyclerViewData(getDataManager().getPassenger());
    }

    private boolean canSearchPassenger(String tag) {
        long resultTime = System.currentTimeMillis() - mLastTagTime;

        return !tag.equals(mLastTag) || resultTime > 3000;
    }
}

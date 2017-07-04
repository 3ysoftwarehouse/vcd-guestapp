package br.com.a3ysoftwarehouse.vcdguest.ui.listPassenger;

import android.util.Log;
import android.view.View;

import java.util.List;

import br.com.a3ysoftwarehouse.vcdguest.data.IDataManager;
import br.com.a3ysoftwarehouse.vcdguest.data.model.Passenger;
import br.com.a3ysoftwarehouse.vcdguest.observer.NfcIdObserver;
import br.com.a3ysoftwarehouse.vcdguest.ui.base.BasePresenter;

/**
 * Created by Iago Belo on 22/06/17.
 */

public class ListPassengersPresenter extends BasePresenter<IListPassengersView>
        implements IListPassengersPresenter, IDataManager.ISyncListener,
        NfcIdObserver.INfcTagIdListener {

    // Constants
    private static final String TAG = "ListPassengersPresenter";

    // Last detected tag
    private String mLastTag;

    // Last detected tag time
    private long mLastTagTime;

    private boolean isAttached;

    public ListPassengersPresenter(IListPassengersView iListPassengersView) {
        super(iListPassengersView);
    }

    @Override
    public void onAttach() {
        isAttached = true;

        NfcIdObserver.getInstance().subscribe(this);

        getDataManager().subscribePassengerSync(this);

        getView().showProgress(true);

        setRecyclerViewData();

        getView().showProgress(false);
    }

    @Override
    public void onDettach() {
        isAttached = false;
        NfcIdObserver.getInstance().unsubscribe(this);
    }

    @Override
    public void onItemClick(View view, int position, Passenger passenger) {
        getView().openPassengerActivity(passenger);
    }

    @Override
    public void onSuccess() {
        setRecyclerViewData();
    }

    @Override
    public void onFailed() {
        getView().showSnackBar("Falha ao sincronizar.");
    }

    private void setRecyclerViewData() {
        List<Passenger> passengerList = getDataManager().getPassengerByCod();

        getView().setRecyclerViewData(passengerList);
    }

    @Override
    public void onNewTag(String tag) {
        if (canSearchPassenger(tag) && isAttached) {
            Passenger passenger = getDataManager().getPassengerByTag(tag);

            if (passenger != null) {
                mLastTagTime = System.currentTimeMillis();
                mLastTag = tag;

                getView().openPassengerActivity(passenger);
            }
        }
    }

    private boolean canSearchPassenger(String tag) {
        long resultTime = System.currentTimeMillis() - mLastTagTime;

        return !tag.equals(mLastTag) || resultTime > 3000;
    }
}

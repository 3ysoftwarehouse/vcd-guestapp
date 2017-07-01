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
        implements IListPassengersPresenter, IDataManager.ISyncListener, NfcIdObserver.INfcTagIdListener {

    // Constants
    private static final String TAG = "ListPassengersPresenter";

    public ListPassengersPresenter(IListPassengersView iListPassengersView) {
        super(iListPassengersView);

        NfcIdObserver.getInstance().subscribe(this);
    }

    @Override
    public void onAttach() {
        Log.i(TAG, "onAttach()");

        getDataManager().subscribePassengerSync(this);

        getView().showProgress(true);

        setRecyclerViewData();

        getView().showProgress(false);
    }

    @Override
    public void onDettach() {
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
        List<Passenger> passengerList = getDataManager().getPassenger();

        getView().setRecyclerViewData(passengerList);
    }

    @Override
    public void onNewTag(String tag) {
        for (Passenger p : getDataManager().getPassenger()) {
            if (p.getTag() != null && p.getTag().equals(tag)) {
                getView().openPassengerActivity(p);
            }
        }
    }
}

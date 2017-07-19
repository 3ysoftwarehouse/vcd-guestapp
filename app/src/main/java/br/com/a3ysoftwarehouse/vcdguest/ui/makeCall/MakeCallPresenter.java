package br.com.a3ysoftwarehouse.vcdguest.ui.makeCall;

import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import br.com.a3ysoftwarehouse.vcdguest.R;
import br.com.a3ysoftwarehouse.vcdguest.app.App;
import br.com.a3ysoftwarehouse.vcdguest.data.model.Call;
import br.com.a3ysoftwarehouse.vcdguest.data.model.Item;
import br.com.a3ysoftwarehouse.vcdguest.data.model.Passenger;
import br.com.a3ysoftwarehouse.vcdguest.data.model.Tag;
import br.com.a3ysoftwarehouse.vcdguest.observer.NfcIdObservable;
import br.com.a3ysoftwarehouse.vcdguest.ui.base.BasePresenter;
import br.com.a3ysoftwarehouse.vcdguest.util.Utils;

/**
 * Created by Iago Belo on 23/06/2017.
 */

public class MakeCallPresenter extends BasePresenter<IMakeCallView> implements
        IMakeCallPresenter,
        NfcIdObservable.INfcTagIdObserver {
    // Constants
    private static final String TAG = "MakeCallPresenter";

    // Call status.
    private boolean isMakingCall;

    // Call
    private Call mCall;

    // All Passengers.
    private List<Passenger> mPassengerList;

    // Passenger clicked.
    private Passenger mClickedPassenger;

    public MakeCallPresenter(IMakeCallView iMakeCallView) {
        super(iMakeCallView);

        mPassengerList = new ArrayList<>(getDataManager().getPassenger());
    }

    @Override
    public void onAttach() {
        NfcIdObservable.getInstance().subscribe(this);
    }

    @Override
    public void onResume() {
        getView().setPassengersNotPresentsRcData(mPassengerList);
    }

    @Override
    public void onDetach() {
        NfcIdObservable.getInstance().unsubscribe(this);
    }

    @Override
    public void onItemClick(View view, int position, Passenger passenger) {
        if (isMakingCall) {
            mClickedPassenger = passenger;

            getView().showReleasePassengerDialog(passenger);

        } else {
            getView().showToast(Utils.getString(R.string.start_a_call));
        }
    }

    @Override
    public void onMakeCallBtClick() {
        if (isMakingCall) {
            Log.i(TAG, "List size: " + mCall.getItems().size());

            getView().setMakeCallBtPlayIcon();

            isMakingCall = false;

            if (mCall.getItems().size() == 0) {
                getView().showToast(Utils.getString(R.string.empty_call_msg));
            } else {
                saveCall();
            }

        } else {
            mCall = new Call();

            getView().setMakeCallSaveIcon();

            isMakingCall = true;
        }
    }

    @Override
    public void onDialogPositiveBtClick() {
        releasePassengerByCod(mClickedPassenger.getCOD());

        getView().showToast(Utils.getString(R.string.passenger_realesed_msg));
    }

    @Override
    public void onDialogNegativeBtClick() {
    }

    @Override
    public void onNewTag(String tag) {
        if (isMakingCall) {
            Utils.beep(App.getContext());

            releasePassengerByTag(tag);
        }
    }

    private void releasePassengerByTag(String tag) {
        Passenger passenger = searchPassengerByTag(tag);

        if (passenger != null) {
            Log.i(TAG, passenger.toString());

            Item item = new Item();
            item.setPaxCod(passenger.getCOD());
            item.setTime(System.currentTimeMillis());

            mCall.addItem(item);

            mPassengerList.remove(passenger);

            getView().setPassengersNotPresentsRcData(mPassengerList);
            getView().showPassengerDialog(passenger);
        }
    }

    private void releasePassengerByCod(String cod) {
        Passenger passenger = searchPassengerByCod(cod);

        if (passenger != null) {
            Item item = new Item();
            item.setPaxCod(passenger.getCOD());
            item.setTime(System.currentTimeMillis());

            mCall.addItem(item);

            mPassengerList.remove(passenger);

            getView().setPassengersNotPresentsRcData(mPassengerList);
        }
    }

    private Passenger searchPassengerByTag(String tag) {
        for (Passenger p : mPassengerList) {
            if (p.getTagList() != null && !p.getTagList().isEmpty()) {
                for (Tag t : p.getTagList()) if (t.getTag().equals(tag)) return p;
            }
        }

        return null;
    }

    private Passenger searchPassengerByCod(@NonNull String cod) {
        for (Passenger p : mPassengerList) {
            if (p.getCOD() != null && p.getCOD().equals(cod)) return p;
        }

        return null;
    }

    private void saveCall() {
        mCall.setId(System.currentTimeMillis());

        getDataManager().saveCall(mCall);

        mPassengerList = new ArrayList<>(getDataManager().getPassenger());
        getView().setPassengersNotPresentsRcData(mPassengerList);
        getView().showToast(Utils.getString(R.string.call_saved_msg));
    }
}

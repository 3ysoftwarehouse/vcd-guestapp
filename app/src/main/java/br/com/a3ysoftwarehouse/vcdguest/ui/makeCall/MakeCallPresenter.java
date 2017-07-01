package br.com.a3ysoftwarehouse.vcdguest.ui.makeCall;

import android.view.View;

import java.util.ArrayList;
import java.util.List;

import br.com.a3ysoftwarehouse.vcdguest.R;
import br.com.a3ysoftwarehouse.vcdguest.app.App;
import br.com.a3ysoftwarehouse.vcdguest.data.model.Call;
import br.com.a3ysoftwarehouse.vcdguest.data.model.Passenger;
import br.com.a3ysoftwarehouse.vcdguest.observer.NfcIdObserver;
import br.com.a3ysoftwarehouse.vcdguest.ui.base.BasePresenter;
import br.com.a3ysoftwarehouse.vcdguest.util.Utils;

/**
 * Created by Iago Belo on 23/06/2017.
 */

public class MakeCallPresenter extends BasePresenter<IMakeCallView> implements IMakeCallPresenter,
        NfcIdObserver.INfcTagIdListener {
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
        NfcIdObserver.getInstance().subscribe(this);

        mCall = new Call();

        getView().setPassengersNotPresentsRcData(mPassengerList);
    }

    @Override
    public void onDettach() {
        NfcIdObserver.getInstance().unsubscribe(this);
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
            if (mCall.getPassengersList().size() == 0) {
                getView().showToast(Utils.getString(R.string.empty_call_msg));

            } else {
                getView().setMakeCallBtPlayIcon();

                isMakingCall = false;

                saveCall();
            }

        } else {
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
            mCall.getPassengersList().add(passenger);

            mPassengerList.remove(passenger);

            getView().showPassengerDialog(passenger);
            getView().setPassengersNotPresentsRcData(mPassengerList);
        }
    }

    private void releasePassengerByCod(String cod) {
        Passenger passenger = searchPassengerByCod(cod);

        if (passenger != null) {
            mCall.getPassengersList().add(passenger);

            mPassengerList.remove(passenger);

            getView().showPassengerDialog(passenger);
            getView().setPassengersNotPresentsRcData(mPassengerList);
        }
    }

    private Passenger searchPassengerByTag(String tag) {
        for (Passenger p : mPassengerList) {
            if (p.getTag() != null) {
                if (p.getTag().equals(tag)) {
                    return p;
                }
            }
        }

        return null;
    }

    private Passenger searchPassengerByCod(String cod) {
        for (Passenger p : mPassengerList) {
            if (p.getCOD() != null) {
                if (p.getCOD().equals(cod)) {
                    return p;
                }
            }
        }

        return null;
    }

    private void saveCall() {
        mCall.setId(System.currentTimeMillis());

        // Save the current call.
        getDataManager().saveCall(mCall);

        mPassengerList = new ArrayList<>(getDataManager().getPassenger());
        getView().setPassengersNotPresentsRcData(mPassengerList);

        mCall = new Call();

        getView().showToast("Chamada salva.");
    }
}

package br.com.a3ysoftwarehouse.vcdguest.ui.makeCall;

import android.view.View;

import java.util.ArrayList;
import java.util.List;

import br.com.a3ysoftwarehouse.vcdguest.app.App;
import br.com.a3ysoftwarehouse.vcdguest.data.model.Call;
import br.com.a3ysoftwarehouse.vcdguest.data.model.Passenger;
import br.com.a3ysoftwarehouse.vcdguest.observer.NfcTagIdObserver;
import br.com.a3ysoftwarehouse.vcdguest.ui.base.BasePresenter;
import br.com.a3ysoftwarehouse.vcdguest.util.Utils;

/**
 * Created by Iago Belo on 23/06/2017.
 */

public class MakeCallPresenter extends BasePresenter<IMakeCallView> implements IMakeCallPresenter,
        NfcTagIdObserver.INfcTagIdListener {
    // Constants
    private static final String TAG = "MakeCallPresenter";

    // Is Making Call
    private boolean isMakingCall;

    // Passengers Present
    private Call mCall;

    private List<Passenger> mPassengersNotPresent;

    public MakeCallPresenter(IMakeCallView iMakeCallView) {
        super(iMakeCallView);

        mPassengersNotPresent = new ArrayList<>(getDataManager().getPassenger());

        mCall = new Call();

        NfcTagIdObserver.getInstance().subscribe(this);
    }

    @Override
    public void onAttach() {
        getView().setPassengersNotPresentsRcData(mPassengersNotPresent);
    }

    @Override
    public void onDettach() {

    }

    @Override
    public void onItemClick(View view, int position, Passenger passenger) {

    }

    @Override
    public void onMakeCallBtClick() {
        if (isMakingCall) {
            getView().setMakeCallBtText("Iniciar");
            isMakingCall = false;

            saveCall();

        } else {
            getView().setMakeCallBtText("Salvar");
            isMakingCall = true;
        }
    }

    @Override
    public void onNewTag(String tag) {
        if (isMakingCall) {
            Utils.beep(App.getContext());

            Passenger passenger = searchPassengerByTag(tag);

            if (passenger != null) {
                mCall.getPassengersList().add(passenger);

                mPassengersNotPresent.remove(passenger);

                getView().setPassengersNotPresentsRcData(mPassengersNotPresent);
            }
        }
    }

    private Passenger searchPassengerByTag(String tag) {
        for (Passenger p : mPassengersNotPresent) {
            if (p.getTag() != null) {
                if (p.getTag().equals(tag)) {
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

        mPassengersNotPresent = new ArrayList<>(getDataManager().getPassenger());
        getView().setPassengersNotPresentsRcData(mPassengersNotPresent);

        mCall = new Call();

        getView().showToast("Chamada salva.");
    }
}

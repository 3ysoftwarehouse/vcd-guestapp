package br.com.a3ysoftwarehouse.vcdguest.ui.passenger;

import java.io.File;

import br.com.a3ysoftwarehouse.vcdguest.R;
import br.com.a3ysoftwarehouse.vcdguest.app.App;
import br.com.a3ysoftwarehouse.vcdguest.data.model.Passenger;
import br.com.a3ysoftwarehouse.vcdguest.data.network.IApiHelper;
import br.com.a3ysoftwarehouse.vcdguest.exception.DatabaseException;
import br.com.a3ysoftwarehouse.vcdguest.ui.base.BasePresenter;
import br.com.a3ysoftwarehouse.vcdguest.util.Constants;
import br.com.a3ysoftwarehouse.vcdguest.util.Utils;

/**
 * Created by Iago Belo on 23/06/17.
 */

public class PassengerPresenter extends BasePresenter<IPassengerView>
        implements IPassengerPresenter, IApiHelper.IApiRequestListener<Void> {
    // Constants
    private static final String TAG = "PassengerPresenter";

    // Save TAG
    private boolean registerTag;

    // Passenger
    private Passenger mPassenger;

    // File name;
    private String mFileName;

    public PassengerPresenter(IPassengerView iPassengerView, Passenger passenger) {
        super(iPassengerView);

        this.mPassenger = passenger;
    }

    @Override
    public void onAttach() {

    }

    @Override
    public void onDettach() {

    }

    @Override
    public void onRegisterBraceletBtClick() {
        if (registerTag) {
            registerTag = false;

        } else {
            registerTag = true;

            getView().showToast("Encoste a pulseira.");
        }
    }

    @Override
    public void onTagRead(String tagId) {
        if (registerTag) {
            Utils.beep(App.getContext());

            try {
                // Update passenger tag
                getDataManager().updatePassengerTag(mPassenger.getCOD(), tagId);

                registerTag = false;

                getView().showToast(Utils.getString(R.string.registered_tag_msg));
                getView().showProgress(true);

                mPassenger = getDataManager().getPassenger(mPassenger.getCOD());

                getView().showPassengerData(mPassenger);
                getView().showProgress(false);
                getView().finishActivity();

            } catch (DatabaseException e) {
                getView().showToast(e.getMessage());
            }
        }
    }

    @Override
    public void onMedicalRecordBtClick() {
        getView().checkPermission();
    }

    @Override
    public void permissionsGranted() {
        getView().showProgress(true);

        String url = Constants.Api.GET_PASSENGER_RECORD + mPassenger.getCOD() + ".pdf";

        mFileName = mPassenger.getPAX().replace(" ", "_") + ".pdf";

        getDataManager().getPassengerRecord(url, Constants.Storage.EXTERNAL_STORAGE, mFileName, this);
    }

    @Override
    public void permissionsNotGranted() {
        getView().showToast(Utils.getString(R.string.permissions_not_granted_msg));
        getView().checkPermission();
    }

    @Override
    public void onSuccess(Void aVoid) {
        getView().showProgress(false);
        getView().openAdobeReader(new File(Constants.Storage.EXTERNAL_STORAGE + "/" + mFileName));
    }

    @Override
    public void onFailed() {
        getView().showProgress(false);
    }
}

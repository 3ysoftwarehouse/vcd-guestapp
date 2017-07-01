package br.com.a3ysoftwarehouse.vcdguest.ui.makeCall;

import java.util.List;

import br.com.a3ysoftwarehouse.vcdguest.data.model.Passenger;
import br.com.a3ysoftwarehouse.vcdguest.ui.base.IBaseView;

/**
 * Created by Iago Belo on 23/06/2017.
 */

public interface IMakeCallView extends IBaseView {
    void setPassengersNotPresentsRcData(List<Passenger> passengerList);

    void setMakeCallBtPlayIcon();

    void setMakeCallSaveIcon();

    void showReleasePassengerDialog(Passenger passenger);

    void showPassengerDialog(Passenger passenger);
}

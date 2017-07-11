package br.com.a3ysoftwarehouse.vcdguest.ui.passenger;

import java.io.File;

import br.com.a3ysoftwarehouse.vcdguest.data.model.Passenger;
import br.com.a3ysoftwarehouse.vcdguest.ui.base.IBaseView;

/**
 * Created by Iago Belo on 23/06/17.
 */

public interface IPassengerView extends IBaseView {
    void showPassengerData(Passenger passenger);

    void finishActivity();

    void openPdfIntent(File file);

    void checkPermission();
}

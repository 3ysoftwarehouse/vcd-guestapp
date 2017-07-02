package br.com.a3ysoftwarehouse.vcdguest.ui.passenger;

import br.com.a3ysoftwarehouse.vcdguest.ui.base.IBasePresenter;

/**
 * Created by Iago Belo on 23/06/17.
 */

public interface IPassengerPresenter extends IBasePresenter {
    void onRegisterBraceletBtClick();

    void onTagRead(String tagId);

    void onMedicalRecordBtClick();

    void permissionsGranted();

    void permissionsNotGranted();
}

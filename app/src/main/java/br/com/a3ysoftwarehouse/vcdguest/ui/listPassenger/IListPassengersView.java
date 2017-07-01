package br.com.a3ysoftwarehouse.vcdguest.ui.listPassenger;

import java.util.List;

import br.com.a3ysoftwarehouse.vcdguest.data.model.Passenger;
import br.com.a3ysoftwarehouse.vcdguest.ui.base.IBaseView;

/**
 * Created by Iago Belo on 22/06/17.
 */

public interface IListPassengersView extends IBaseView {
    void setRecyclerViewData(List<Passenger> passengerList);

    void openPassengerActivity(Passenger passenger);
}

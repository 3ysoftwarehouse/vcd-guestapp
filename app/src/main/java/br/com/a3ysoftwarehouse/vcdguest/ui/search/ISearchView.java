package br.com.a3ysoftwarehouse.vcdguest.ui.search;

import java.util.List;

import br.com.a3ysoftwarehouse.vcdguest.data.model.Passenger;
import br.com.a3ysoftwarehouse.vcdguest.ui.base.IBaseView;

/**
 * Created by Iago Belo on 06/07/17.
 */

public interface ISearchView extends IBaseView {
    void setRecyclerViewData(List<Passenger> passengerList);

    void openPassengerActivity(Passenger passenger);
}

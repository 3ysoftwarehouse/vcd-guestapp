package br.com.a3ysoftwarehouse.vcdguest.ui.listPassenger;

import br.com.a3ysoftwarehouse.vcdguest.data.model.Passenger;
import br.com.a3ysoftwarehouse.vcdguest.ui.base.IBasePresenter;
import br.com.a3ysoftwarehouse.vcdguest.ui.base.IRecyclerViewOnClickListener;

/**
 * Created by Iago Belo on 22/06/17.
 */

public interface IListPassengersPresenter
        extends IBasePresenter, IRecyclerViewOnClickListener<Passenger> {
}

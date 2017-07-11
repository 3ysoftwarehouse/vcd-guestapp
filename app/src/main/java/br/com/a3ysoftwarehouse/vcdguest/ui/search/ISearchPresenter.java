package br.com.a3ysoftwarehouse.vcdguest.ui.search;

import br.com.a3ysoftwarehouse.vcdguest.data.model.Passenger;
import br.com.a3ysoftwarehouse.vcdguest.ui.base.IBasePresenter;
import br.com.a3ysoftwarehouse.vcdguest.ui.base.IRecyclerViewOnClickListener;

/**
 * Created by Iago Belo on 06/07/17.
 */

public interface ISearchPresenter extends IBasePresenter, IRecyclerViewOnClickListener<Passenger> {
    void onSearchBtClick(String query);
}

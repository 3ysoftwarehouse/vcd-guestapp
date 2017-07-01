package br.com.a3ysoftwarehouse.vcdguest.ui.makeCall;

import android.view.View;

import br.com.a3ysoftwarehouse.vcdguest.data.model.Passenger;
import br.com.a3ysoftwarehouse.vcdguest.ui.base.IBasePresenter;
import br.com.a3ysoftwarehouse.vcdguest.ui.base.IRecyclerViewOnClickListener;

/**
 * Created by Iago Belo on 23/06/2017.
 */

public interface IMakeCallPresenter extends IBasePresenter, IRecyclerViewOnClickListener<Passenger> {
    @Override
    void onAttach();

    @Override
    void onDettach();

    @Override
    void onItemClick(View view, int position, Passenger passenger);

    void onMakeCallBtClick();
}

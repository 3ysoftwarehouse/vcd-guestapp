package br.com.a3ysoftwarehouse.vcdguest.ui.listCall;

import android.view.View;

import br.com.a3ysoftwarehouse.vcdguest.data.model.Call;
import br.com.a3ysoftwarehouse.vcdguest.ui.base.BasePresenter;

/**
 * Created by Iago Belo on 26/06/2017.
 */

public class ListCallPresenter extends BasePresenter<IListCallView> implements IListCallPresenter {

    public ListCallPresenter(IListCallView iListCallView) {
        super(iListCallView);
    }

    @Override
    public void onAttach() {
        getView().setRecyclerViewData(getDataManager().getCall());
    }

    @Override
    public void onDettach() {

    }

    @Override
    public void onItemClick(View view, int position, Call call) {

    }
}

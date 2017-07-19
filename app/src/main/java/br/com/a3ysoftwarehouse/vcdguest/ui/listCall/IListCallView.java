package br.com.a3ysoftwarehouse.vcdguest.ui.listCall;

import java.util.List;

import br.com.a3ysoftwarehouse.vcdguest.data.model.Call;
import br.com.a3ysoftwarehouse.vcdguest.ui.base.IBaseView;

/**
 * Created by Iago Belo on 26/06/2017.
 */

public interface IListCallView extends IBaseView {
    void setRecyclerViewData(List<Call> data);

    void openCallActivity(Call call);
}

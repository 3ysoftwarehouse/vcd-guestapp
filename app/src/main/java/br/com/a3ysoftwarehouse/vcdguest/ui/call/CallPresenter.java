package br.com.a3ysoftwarehouse.vcdguest.ui.call;

import com.github.mikephil.charting.data.Entry;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.a3ysoftwarehouse.vcdguest.data.model.Call;
import br.com.a3ysoftwarehouse.vcdguest.data.model.Item;
import br.com.a3ysoftwarehouse.vcdguest.ui.base.BasePresenter;

/**
 * Created by Iago Belo on 17/07/17.
 */

public class CallPresenter extends BasePresenter<ICallView> implements ICallPresenter {
    // Constants
    private static final String TAG = "CallPresenter";

    // Call
    private long mCallId;

    public CallPresenter(ICallView iCallView, long callId) {
        super(iCallView);
        this.mCallId = callId;
    }

    @Override
    public void onAttach() {

    }

    @Override
    public void onResume() {
        getView().initChart(getEntrys(mCallId));
    }

    @Override
    public void onDetach() {

    }

    private List<Entry> getEntrys(long callId) {
//        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

        List<Entry> entryList = new ArrayList<>();

        Call call = getDataManager().getCall(callId);

        for (Item item : call.getItems()) {
            entryList.add(new Entry(item.getTime(), (int) call.getId(), "teste"));
        }

        return entryList;
    }
}

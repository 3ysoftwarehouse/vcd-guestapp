package br.com.a3ysoftwarehouse.vcdguest.ui.makeCall;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import br.com.a3ysoftwarehouse.vcdguest.R;
import br.com.a3ysoftwarehouse.vcdguest.data.model.Passenger;
import br.com.a3ysoftwarehouse.vcdguest.ui.base.BaseFragment;
import br.com.a3ysoftwarehouse.vcdguest.ui.listPassenger.adapter.ListPassengerAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Iago Belo on 23/06/2017.
 */

public class MakeCallFragment extends BaseFragment<IMakeCallPresenter> implements IMakeCallView {
    // Constants
    private static final String TAG = "MakeCallFragment";

    // Views
    @BindView(R.id.passengers_not_present_rc) RecyclerView mPassengersNotPresentRc;
    @BindView(R.id.make_call_fab) Button mMakeCallFab;

    // Adapters
    private ListPassengerAdapter mPassengersPresentAdapter;
    private ListPassengerAdapter mPassengersNotPresentAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_make_call, container, false);
        ButterKnife.bind(this, view);
        setPresenter(new MakeCallPresenter(this));

        // mPassengersNotPresentRc
        LinearLayoutManager passengersNotPresentLm = new LinearLayoutManager(getActivity());
        mPassengersNotPresentRc.setLayoutManager(passengersNotPresentLm);

        mPassengersNotPresentAdapter =
                new ListPassengerAdapter(new ArrayList<Passenger>(), getPresenter());
        mPassengersNotPresentRc.setAdapter(mPassengersNotPresentAdapter);

        return view;
    }

    @Override
    public void setPassengersNotPresentsRcData(List<Passenger> passengerList) {
        Log.i(TAG, "setPassengersNotPresentsRcData()");

        mPassengersNotPresentAdapter.setData(passengerList);
        mPassengersNotPresentAdapter.notifyDataSetChanged();
    }

    @Override
    public void setMakeCallBtText(String text) {
        mMakeCallFab.setText(text);
    }

    @OnClick(R.id.make_call_fab)
    public void onMakeCallBtClick() {
        getPresenter().onMakeCallBtClick();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        getActivity().getMenuInflater().inflate(R.menu.menu_make_call, menu);
    }
}

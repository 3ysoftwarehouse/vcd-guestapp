package br.com.a3ysoftwarehouse.vcdguest.ui.listPassenger;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import br.com.a3ysoftwarehouse.vcdguest.R;
import br.com.a3ysoftwarehouse.vcdguest.data.model.Passenger;
import br.com.a3ysoftwarehouse.vcdguest.ui.base.BaseFragment;
import br.com.a3ysoftwarehouse.vcdguest.ui.listPassenger.adapter.ListPassengerAdapter;
import br.com.a3ysoftwarehouse.vcdguest.ui.passenger.PassengerActivity;
import br.com.a3ysoftwarehouse.vcdguest.util.Constants;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Iago Belo on 22/06/17.
 */

public class ListPassengersFragment extends BaseFragment<IListPassengersPresenter>
        implements IListPassengersView {

    // Constants
    private static final String TAG = "ListPassengersFragment";

    // View
    @BindView(R.id.list_passengers_rc) RecyclerView mListPassengersRc;
    private LinearLayoutManager mLayoutManager;
    private ListPassengerAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_passengers, container, false);
        ButterKnife.bind(this, view);
        setPresenter(new ListPassengersPresenter(this));

        // mListPassengersRc
        mListPassengersRc.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mListPassengersRc.setLayoutManager(mLayoutManager);

        mAdapter = new ListPassengerAdapter(new ArrayList<Passenger>(), getPresenter());
        mListPassengersRc.setAdapter(mAdapter);

        return view;
    }

    @Override
    public void setRecyclerViewData(List<Passenger> passengerList) {
        mAdapter.setData(passengerList);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void openPassengerActivity(Passenger passenger) {
        Intent i = new Intent(getActivity(), PassengerActivity.class);
        i.putExtra(Constants.Keys.PASSENGER, passenger);

        startActivity(i);
    }
}

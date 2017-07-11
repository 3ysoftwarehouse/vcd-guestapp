package br.com.a3ysoftwarehouse.vcdguest.ui.search;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import br.com.a3ysoftwarehouse.vcdguest.R;
import br.com.a3ysoftwarehouse.vcdguest.data.model.Passenger;
import br.com.a3ysoftwarehouse.vcdguest.ui.adapter.ListPassengerAdapter;
import br.com.a3ysoftwarehouse.vcdguest.ui.base.BaseFragment;
import br.com.a3ysoftwarehouse.vcdguest.ui.passenger.PassengerActivity;
import br.com.a3ysoftwarehouse.vcdguest.util.Constants;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Iago Belo on 06/07/17.
 */

public class SearchFragment extends BaseFragment<ISearchPresenter> implements ISearchView {
    // Constants
    private static final String TAG = "SearchFragment";

    // Views
    @BindView(R.id.search_bt) ImageView mSearchBt;
    @BindView(R.id.query_edt) EditText mQueryEdt;
    @BindView(R.id.query_result_rc) RecyclerView mQueryResultRc;

    private LinearLayoutManager mLinearLayoutManager;
    private ListPassengerAdapter mQueryResultAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        ButterKnife.bind(this, view);

        // mQueryResultRc
        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mQueryResultRc.setLayoutManager(mLinearLayoutManager);

        mQueryResultAdapter = new ListPassengerAdapter(new ArrayList<Passenger>(), getPresenter());
        mQueryResultAdapter.setType(ListPassengerAdapter.LINEAR);

        mQueryResultRc.setAdapter(mQueryResultAdapter);

        return view;
    }

    @OnClick(R.id.search_bt)
    public void onSearchBtClick() {
        String query = mQueryEdt.getText().toString();

        getPresenter().onSearchBtClick(query);
    }

    @Override
    protected ISearchPresenter initPresenter() {
        return new SearchPresenter(this);
    }

    @Override
    public void setRecyclerViewData(List<Passenger> passengerList) {
        mQueryResultAdapter.setData(passengerList);
        mQueryResultAdapter.notifyDataSetChanged();
    }

    @Override
    public void openPassengerActivity(Passenger passenger) {
        Intent intent = new Intent(getActivity(), PassengerActivity.class);
        intent.putExtra(Constants.Keys.PASSENGER, passenger);

        startActivity(intent);
    }
}

package br.com.a3ysoftwarehouse.vcdguest.ui.search;

import android.view.View;

import java.util.ArrayList;
import java.util.List;

import br.com.a3ysoftwarehouse.vcdguest.data.model.Passenger;
import br.com.a3ysoftwarehouse.vcdguest.ui.base.BasePresenter;

/**
 * Created by Iago Belo on 06/07/17.
 */

public class SearchPresenter extends BasePresenter<ISearchView> implements ISearchPresenter {
    // Constants
    private static final String TAG = "SearchPresenter";

    // Passengers List
    private static List<Passenger> mPassengerList;

    public SearchPresenter(ISearchView iSearchView) {
        super(iSearchView);
    }

    @Override
    public void onAttach() {
        mPassengerList = new ArrayList<>(getDataManager().getPassenger());
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDetach() {
        mPassengerList = null;
    }

    @Override
    public void onSearchBtClick(String query) {
        String queryLc = query.toLowerCase();
        List<Passenger> queryList = new ArrayList<>();

        for (Passenger p : mPassengerList) {
            if (p.toString().toLowerCase().contains(queryLc)) {
                queryList.add(p);
            }
        }

        getView().setRecyclerViewData(queryList);
    }

    @Override
    public void onItemClick(View view, int position, Passenger passenger) {
        getView().openPassengerActivity(passenger);
    }
}

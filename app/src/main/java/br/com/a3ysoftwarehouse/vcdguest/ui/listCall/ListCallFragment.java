package br.com.a3ysoftwarehouse.vcdguest.ui.listCall;

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
import br.com.a3ysoftwarehouse.vcdguest.data.model.Call;
import br.com.a3ysoftwarehouse.vcdguest.ui.base.BaseFragment;
import br.com.a3ysoftwarehouse.vcdguest.ui.listCall.adapter.ListCallAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Iago Belo on 26/06/2017.
 */

public class ListCallFragment extends BaseFragment<IListCallPresenter> implements IListCallView {
    // Constants
    private static final String TAG = "ListCallFragment";

    // Views
    @BindView(R.id.list_call_rc) RecyclerView mCallRc;
    private ListCallAdapter mListCallAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_call, container, false);
        ButterKnife.bind(this, view);
        setPresenter(new ListCallPresenter(this));

        // mCallRc
        LinearLayoutManager listCallLm = new LinearLayoutManager(getActivity());
        mCallRc.setLayoutManager(listCallLm);

        mListCallAdapter = new ListCallAdapter(new ArrayList<Call>(), getPresenter());
        mCallRc.setAdapter(mListCallAdapter);

        return view;
    }

    @Override
    public void setRecyclerViewData(List<Call> data) {
        mListCallAdapter.setData(data);
        mListCallAdapter.notifyDataSetChanged();
    }
}

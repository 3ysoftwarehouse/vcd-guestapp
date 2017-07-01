package br.com.a3ysoftwarehouse.vcdguest.ui.listCall.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import br.com.a3ysoftwarehouse.vcdguest.R;
import br.com.a3ysoftwarehouse.vcdguest.data.model.Call;
import br.com.a3ysoftwarehouse.vcdguest.ui.base.IRecyclerViewOnClickListener;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Iago Belo on 22/06/17.
 */

public class ListCallAdapter extends RecyclerView.Adapter<ListCallAdapter.ViewHolder> {
    private static final String TAG = "ListCallAdapter";
    private static IRecyclerViewOnClickListener<Call> mListener;
    private static List<Call> mPassengerList;

    public ListCallAdapter(List<Call> passengerList,
                           IRecyclerViewOnClickListener<Call> listener) {
        mPassengerList = passengerList;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_calls_recyclerview_layout, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Call c = mPassengerList.get(position);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date(c.getId());

        holder.nameTv.setText(sdf.format(date));
    }

    @Override
    public int getItemCount() {
        return mPassengerList.size();
    }

    public void setData(List<Call> passengerList) {
        Log.i(TAG, "setData()");
        mPassengerList = passengerList;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.call_date_tv) TextView nameTv;

        ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onItemClick(
                            v, getAdapterPosition(), mPassengerList.get(getAdapterPosition())
                    );
                }
            });
        }
    }
}

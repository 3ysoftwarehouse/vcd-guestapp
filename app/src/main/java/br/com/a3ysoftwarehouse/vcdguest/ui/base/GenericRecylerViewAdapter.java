package br.com.a3ysoftwarehouse.vcdguest.ui.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Iago Belo on 21/06/2017.
 */

public class GenericRecylerViewAdapter<T extends List>
        extends RecyclerView.Adapter<GenericRecylerViewAdapter.ViewHolder> {
    // Constants
    private static final String TAG = "GenericRecylerViewAdapter";

    // Data
    private T mData;

    // View
    private View mView;

    private GenericRecylerViewAdapter(Builder builder) {

    }

    protected T getData() {
        return this.mData;
    }

    public void setData(T t) {
        this.mData = t;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View v) {
            super(v);
        }
    }

    public class Builder {
        private T data;
        private View view;
        private ViewHolder viewHolder;
        private IRecyclerViewOnClickListener listener;
    }
}

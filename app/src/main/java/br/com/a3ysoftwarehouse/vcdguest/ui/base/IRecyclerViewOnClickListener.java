package br.com.a3ysoftwarehouse.vcdguest.ui.base;

import android.view.View;

/**
 * Created by Iago Belo on 22/06/17.
 */

public interface IRecyclerViewOnClickListener<T> {
    void onItemClick(View view, int position, T t);
}

package br.com.a3ysoftwarehouse.vcdguest.ui.call;

import com.github.mikephil.charting.data.Entry;

import java.util.List;

import br.com.a3ysoftwarehouse.vcdguest.ui.base.IBaseView;

/**
 * Created by Iago Belo on 17/07/17.
 */

interface ICallView extends IBaseView {
    void initChart(List<Entry> entryList);
}

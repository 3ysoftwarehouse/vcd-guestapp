package br.com.a3ysoftwarehouse.vcdguest.ui.base;

import br.com.a3ysoftwarehouse.vcdguest.data.DataManager;
import br.com.a3ysoftwarehouse.vcdguest.data.IDataManager;

/**
 * Created by Iago Belo on 23/05/17.
 */

public abstract class BasePresenter<T extends IBaseView> implements IBasePresenter {
    private T t;
    private IDataManager mIDataManager;

    public BasePresenter(T t) {
        this.t = t;
        this.mIDataManager = DataManager.getInstance();
    }

    public T getView() {
        return t;
    }

    public IDataManager getDataManager() {
        return mIDataManager;
    }
}

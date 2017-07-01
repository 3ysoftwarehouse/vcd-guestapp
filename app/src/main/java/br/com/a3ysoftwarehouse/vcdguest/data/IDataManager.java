package br.com.a3ysoftwarehouse.vcdguest.data;

import br.com.a3ysoftwarehouse.vcdguest.data.database.IDbHelper;
import br.com.a3ysoftwarehouse.vcdguest.data.model.Call;

/**
 * Created by Iago Belo on 21/06/17.
 */

public interface IDataManager extends IDbHelper {
    void syncPassengers();

    void subscribePassengerSync(ISyncListener listener);

    void saveCallCache(Call call);

    Call getCallCache();

    interface ISyncListener {
        void onSuccess();

        void onFailed();
    }
}

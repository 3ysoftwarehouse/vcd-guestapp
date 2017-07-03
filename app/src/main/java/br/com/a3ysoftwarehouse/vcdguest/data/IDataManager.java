package br.com.a3ysoftwarehouse.vcdguest.data;

import br.com.a3ysoftwarehouse.vcdguest.data.database.IDbHelper;
import br.com.a3ysoftwarehouse.vcdguest.data.network.IApiHelper;

/**
 * Created by Iago Belo on 21/06/17.
 */

public interface IDataManager extends IDbHelper, IApiHelper {
    void syncPassengers();

    void subscribePassengerSync(ISyncListener listener);

    void unsubscribePassengerSync(ISyncListener listener);

    interface ISyncListener {
        void onSuccess();

        void onFailed();
    }
}

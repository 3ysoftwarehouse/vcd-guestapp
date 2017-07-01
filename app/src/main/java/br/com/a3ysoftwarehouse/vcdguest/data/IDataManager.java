package br.com.a3ysoftwarehouse.vcdguest.data;

import br.com.a3ysoftwarehouse.vcdguest.data.database.IDbHelper;

/**
 * Created by Iago Belo on 21/06/17.
 */

public interface IDataManager extends IDbHelper {
    void syncPassengers();

    void subscribePassengerSync(ISyncListener listener);

    interface ISyncListener {
        void onSuccess();

        void onFailed();
    }
}

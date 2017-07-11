package br.com.a3ysoftwarehouse.vcdguest.data;

import br.com.a3ysoftwarehouse.vcdguest.data.database.IDbHelper;
import br.com.a3ysoftwarehouse.vcdguest.data.network.IApiHelper;
import br.com.a3ysoftwarehouse.vcdguest.data.preferences.IPreferencesHelper;

/**
 * Created by Iago Belo on 21/06/17.
 */

public interface IDataManager extends IDbHelper, IApiHelper, IPreferencesHelper {
    void syncPassengers(ISyncListener listener);

    void restoreTagsFromServer(ISyncListener listener);

    void backupTagsToServer(ISyncListener listener);

    interface ISyncListener {
        void onSuccess();

        void onFailed();
    }
}

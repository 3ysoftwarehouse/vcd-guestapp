package br.com.a3ysoftwarehouse.vcdguest.data.network;

import java.util.List;

import br.com.a3ysoftwarehouse.vcdguest.data.model.Passenger;

/**
 * Created by Iago Belo on 22/06/17.
 */

public interface IApiHelper {
    void getPassengers(IApiRequestListener<List<Passenger>> listener);

    void downloadFile(String url, String dirPath, String fileName,
                      IApiRequestListener<Void> listener);

    interface IApiRequestListener<T> {
        void onSuccess(T t);

        void onFailed();
    }
}

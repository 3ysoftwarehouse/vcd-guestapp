package br.com.a3ysoftwarehouse.vcdguest.data.database;

import java.util.List;

import br.com.a3ysoftwarehouse.vcdguest.data.model.Call;
import br.com.a3ysoftwarehouse.vcdguest.data.model.Passenger;

/**
 * Created by Iago Belo on 22/06/2017.
 */

public interface IDbHelper {
    List<Passenger> getPassenger();

    Passenger getPassenger(String cod);

    void savePassengers(List<Passenger> passengerList);

    void updatePassengerTag(final String cod, final String tag);

    void saveCall(Call call);

    List<Call> getCall();
}

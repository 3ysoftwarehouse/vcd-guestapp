package br.com.a3ysoftwarehouse.vcdguest.data.database;

import java.util.List;

import br.com.a3ysoftwarehouse.vcdguest.data.model.Call;
import br.com.a3ysoftwarehouse.vcdguest.data.model.Passenger;
import br.com.a3ysoftwarehouse.vcdguest.data.model.Tag;
import br.com.a3ysoftwarehouse.vcdguest.exception.DatabaseException;

/**
 * Created by Iago Belo on 22/06/2017.
 */

public interface IDbHelper {
    List<Passenger> getPassenger();

    Passenger getPassenger(String cod);

    Passenger getPassengerByTag(String tag);

    void savePassengers(List<Passenger> passengerList);

    void newPassengerTag(final String cod, final String tag) throws DatabaseException;

    List<Tag> getPassengerTags(String cod);

    void saveCall(Call call);

    List<Call> getCall();

    Call getCall(long id);

    List<Tag> getAllTags();

    void saveTags(List<Tag> tagList);
}

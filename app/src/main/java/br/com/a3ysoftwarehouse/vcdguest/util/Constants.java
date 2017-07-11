package br.com.a3ysoftwarehouse.vcdguest.util;

import android.os.Environment;

/**
 * Created by Iago Belo on 22/06/17.
 */

public final class Constants {
    public interface Keys {
        String PASSENGER = "passenger";
    }

    public interface Api {
        String GET_ALL_PASSENGERS = "http://iagobelo.com.br/projects/vcd";
        String GET_PASSENGER_RECORD = "http://vcdviagens.com.br/disney2017/pax/ficha/";
        String GET_TAGS = "http://iagobelo.com.br/projects/vcd/tag.php";
        String POST_TAGS = "http://iagobelo.com.br/projects/vcd/tag.php";
    }

    public interface Storage {
        String EXTERNAL_STORAGE = Environment.getExternalStorageDirectory().getAbsolutePath();
        String MEDICAL_RECORD_PATH = EXTERNAL_STORAGE + "/MedicalRecords";
    }
}

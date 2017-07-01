package br.com.a3ysoftwarehouse.vcdguest.util;

import android.content.Context;
import android.media.MediaPlayer;

import br.com.a3ysoftwarehouse.vcdguest.R;
import br.com.a3ysoftwarehouse.vcdguest.app.App;

/**
 * Created by Iago Belo on 23/06/2017.
 */

public final class Utils {
    private Utils() {
        throw new RuntimeException();
    }

    public static String getPerfilPictureUrl(String cod) {
        String baseUrl = "http://vcdviagens.com.br/disney2017/pax/fotos/";

        return baseUrl + cod + ".jpg";
    }

    public static String ByteArrayToHexString(byte[] inarray) {
        int i, j, in;
        String[] hex = {
                "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F"
        };
        String out = "";

        for (j = 0; j < inarray.length; ++j) {
            in = (int) inarray[j] & 0xff;
            i = (in >> 4) & 0x0f;
            out += hex[i];
            i = in & 0x0f;
            out += hex[i];
        }

        return out;
    }

    public static void beep(Context context) {
        MediaPlayer mp = MediaPlayer.create(context, R.raw.beep);
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.release();
            }
        });
        mp.start();
    }

    public static String getString(int id) {
        return App.getContext().getResources().getString(id);
    }
}

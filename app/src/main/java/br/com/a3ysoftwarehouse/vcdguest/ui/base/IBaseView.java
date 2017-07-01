package br.com.a3ysoftwarehouse.vcdguest.ui.base;

/**
 * Created by Iago Belo on 08/06/17.
 */

public interface IBaseView {
    void showToast(String msg);

    void showToast(String msg, int time);

    void showProgress(boolean show);

    void showSnackBar(String msg);
}

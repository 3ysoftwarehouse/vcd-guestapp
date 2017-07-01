package br.com.a3ysoftwarehouse.vcdguest.exception;

/**
 * Created by Iago Belo on 22/06/17.
 */

public class ApiException extends Exception {
    public ApiException() {
    }

    public ApiException(String message) {
        super(message);
    }

    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApiException(Throwable cause) {
        super(cause);
    }
}

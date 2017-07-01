package br.com.a3ysoftwarehouse.vcdguest.exception;

/**
 * Created by Iago Belo on 30/06/17.
 */

public class DatabaseException extends Throwable {
    public DatabaseException() {
    }

    public DatabaseException(String message) {
        super(message);
    }

    public DatabaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public DatabaseException(Throwable cause) {
        super(cause);
    }
}

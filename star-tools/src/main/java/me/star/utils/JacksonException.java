package me.star.utils;

/**
 * @author Star Chou
 * @create 2021/12/25 22:23
 */
public class JacksonException extends FormativeException {
    public JacksonException() {
        super();
    }

    public JacksonException(String message) {
        super(message);
    }

    public JacksonException(Throwable cause) {
        super(cause);
    }

    public JacksonException(String format, Object... arguments) {
        super(format, arguments);
    }
}


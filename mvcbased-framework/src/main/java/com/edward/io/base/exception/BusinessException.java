package com.edward.io.base.exception;

import java.text.MessageFormat;

public class BusinessException extends Exception {
    private static final long serialVersionUID = -5795099296481749369L;
    private int errorNo;

    public BusinessException(String message, Object... args) {
        this(1, format(message, args));
    }

    public BusinessException(int errorNo, String message, Object... args) {
        super(format(message, args));
        this.errorNo = errorNo;
    }

    public BusinessException(String message, Throwable cause, Object... args) {
        this(1, format(message, args), cause);
    }

    public BusinessException(int errorNo, String message, Throwable cause, Object... args) {
        super(format(message, args), cause);
        this.errorNo = errorNo;
    }

    public int getErrorNo() {
        return errorNo;
    }

    public String getErrorMessage() {
        return getLocalizedMessage();
    }

    private static String format(String tmpl, Object[] args) {
        if (args == null || args.length == 0) {
            return tmpl;
        }
        return MessageFormat.format(tmpl, args);
    }

    public static BusinessException wrapFor(Throwable e) {
        if (e instanceof BusinessException) {
            return (BusinessException) e;
        }
        String message = null;
        Throwable cause = e;
        while (cause != null) {
            message = cause.getMessage();
            if (message != null && message.length() > 0) {
                break;
            } else {
                cause = cause.getCause();
            }
        }
        return new BusinessException(message, e);
    }

}

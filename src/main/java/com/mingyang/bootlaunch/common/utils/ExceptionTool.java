package com.mingyang.bootlaunch.common.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.UndeclaredThrowableException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ExceptionTool {
    private static final Logger log = LoggerFactory.getLogger(ExceptionTool.class);

    public static RuntimeException unchecked(Throwable e) {
        if (e instanceof Error) {
            throw (Error)e;
        } else if (!(e instanceof IllegalAccessException) && !(e instanceof IllegalArgumentException) && !(e instanceof NoSuchMethodException)) {
            if (e instanceof InvocationTargetException) {
                return new RuntimeException(((InvocationTargetException)e).getTargetException());
            } else if (e instanceof RuntimeException) {
                return (RuntimeException)e;
            } else {
                if (e instanceof InterruptedException) {
                    Thread.currentThread().interrupt();
                }

                return new RuntimeException(e);
            }
        } else {
            return new IllegalArgumentException(e);
        }
    }

    public static Throwable unwrap(Throwable wrapped) {
        Throwable unwrapped = wrapped;

        while(true) {
            while(!(unwrapped instanceof InvocationTargetException)) {
                if (!(unwrapped instanceof UndeclaredThrowableException)) {
                    return unwrapped;
                }

                unwrapped = ((UndeclaredThrowableException)unwrapped).getUndeclaredThrowable();
            }

            unwrapped = ((InvocationTargetException)unwrapped).getTargetException();
        }
    }

    private ExceptionTool() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}
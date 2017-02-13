package com.realaicy.prod.jc.lib.core.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 关于异常的工具类.
 * <p/>
 * 参考了guava的Throwables。
 *
 * @author realaicy
 */
@SuppressWarnings({"StringBufferReplaceableByString", "unused"})
public final class Exceptions {


    /**
     * Do not instantiate Exceptions.
     */
    private Exceptions() {

    }

    /**
     * 将CheckedException转换为UncheckedException.
     *
     * @param ex the ex
     * @return the runtime exception
     */
    public static RuntimeException unchecked(final Throwable ex) {
        if (ex instanceof RuntimeException) {
            return (RuntimeException) ex;
        } else {
            return new RuntimeException(ex);
        }
    }

    /**
     * 将ErrorStack转化为String.
     *
     * @param ex the ex
     * @return the stack trace as string
     */
    public static String getStackTraceAsString(final Throwable ex) {
        StringWriter stringWriter = new StringWriter();
        ex.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }

    /**
     * 获取组合本异常信息与底层异常信息的异常描述, 适用于本异常为统一包装异常类，底层异常才是根本原因的情况。
     *
     * @param ex the ex
     * @return the error message with nested exception
     */
    public static String getErrorMessageWithNestedException(final Throwable ex) {
        Throwable nestedException = ex.getCause();
        return new StringBuilder().append(ex.getMessage()).append(" nested exception is ")
                .append(nestedException.getClass().getName()).append(":").append(nestedException.getMessage())
                .toString();
    }

    /**
     * 获取异常的Root Cause.
     *
     * @param ex the ex
     * @return the root cause
     */
    public static Throwable getRootCause(final Throwable ex) {
        Throwable rootCause = ex;
        while ((rootCause.getCause()) != null) {
            rootCause = rootCause.getCause();
        }
        return rootCause;
    }

    /**
     * 判断异常是否由某些底层的异常引起.
     *
     * @param ex                    the ex
     * @param causeExceptionClasses the cause exception classes
     * @return the boolean
     */
    @SafeVarargs
    public static boolean isCausedBy(final Exception ex, final Class<? extends Exception>... causeExceptionClasses) {
        Throwable cause = ex;
        while (cause != null) {
            for (Class<? extends Exception> causeClass : causeExceptionClasses) {
                if (causeClass.isInstance(cause)) {
                    return true;
                }
            }
            cause = cause.getCause();
        }
        return false;
    }
}

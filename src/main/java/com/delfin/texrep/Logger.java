package com.delfin.texrep;

interface Logger {

    default void info(String format, Object... args) {
        throw new RuntimeException("Unexpected method call for current implementation");
    }

    default void warn(String format, Object... args) {
        throw new RuntimeException("Unexpected method call for current implementation");
    }

    default void error(String format, Object... args) {
        throw new RuntimeException("Unexpected method call for current implementation");
    }

    default void debug(String format, Object... args) {
        throw new RuntimeException("Unexpected method call for current implementation");
    }

    default void warn(Throwable cause, String format, Object... args) {
        throw new RuntimeException("Unexpected method call for current implementation");
    }

    default void error(Throwable cause, String format, Object... args) {
        throw new RuntimeException("Unexpected method call for current implementation");
    }

    default void debug(Throwable cause, String format, Object... args) {
        throw new RuntimeException("Unexpected method call for current implementation");
    }
    
    default void flush() {
        throw new RuntimeException("Unexpected method call for current implementation");
    }

    static String concat(String format, Object... args) {
        if (format == null || args == null || format.isEmpty() ) {
            return format;
        }
        StringBuilder res = new StringBuilder();
        for (int i = 0, j = 0; j < format.length(); ++j) {
            char c = format.charAt(j);
            if (c == '{' && j < format.length() - 1 && '}' == format.charAt(j + 1) && i < args.length) {
                res.append(args[i]);
                ++j;
                ++i;
                continue;
            }
            res.append(c);
        }
        return res.toString();
    }

}

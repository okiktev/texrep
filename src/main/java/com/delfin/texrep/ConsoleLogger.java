package com.delfin.texrep;

class ConsoleLogger implements Logger {

    private boolean isVerbose;

    private StringBuilder out = new StringBuilder();

    ConsoleLogger(boolean isVerbose) {
        this.isVerbose = isVerbose;
    }

    @Override
    public void info(String format, Object... args) {
        log(null, format, args);
    }

    @Override
    public void warn(String format, Object... args) {
        log(null, format, args);
    }

    @Override
    public void error(String format, Object... args) {
        log(null, format, args);
    }

    @Override
    public void debug(String format, Object... args) {
        log(null, format, args);
    }

    @Override
    public void warn(Throwable cause, String format, Object... args) {
        log(cause, format, args);
    }

    @Override
    public void error(Throwable cause, String format, Object... args) {
        log(cause, format, args);
    }

    @Override
    public void debug(Throwable cause, String format, Object... args) {
        log(cause, format, args);
    }

    private void log(Throwable cause, String format, Object... args) {
        String msg = Logger.concat(format, args);
        String trace = toString(cause);
        if (isVerbose) {
            System.out.println(msg);
            if (trace != null) {
                System.out.println(trace);
            }
        } else {
            out.append(msg).append('\n');
            if (trace != null) {
                out.append(trace).append('\n');
            }
        }
    }

    private static String toString(Throwable cause) {
        if (cause == null) {
            return null;
        }
        StringBuilder res = new StringBuilder();
        res.append("\tERROR: ").append(cause.getMessage()).append('\n');
        for (StackTraceElement element : cause.getStackTrace()) {
            res.append('\t').append('\t').append(element.toString());
        }
        return res.toString();
    }

    @Override
    public void flush() {
        if (!isVerbose) {
            System.out.println(out);
        }
    }

}

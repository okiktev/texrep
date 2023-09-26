package com.delfin.texrep;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;

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
        if (isVerbose) {
        	PrintStream stream = cause == null ? System.out : System.err;
        	stream.println(msg);
            if (cause != null) {
            	cause.printStackTrace(stream);
            }
        } else {
            out.append(msg);
            if (cause != null) {
            	out.append('\n');
            	try (PrintWriter pw = new PrintWriter(new StringWriter())) {
            		out.append(pw.toString());
				} 
            }
            out.append('\n');
        }
    }

    @Override
    public void flush() {
        if (!isVerbose) {
            System.out.println(out);
        }
    }

}

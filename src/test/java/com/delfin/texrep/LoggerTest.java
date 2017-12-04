package com.delfin.texrep;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class LoggerTest {

    @Test
    public void concat_test() throws Exception {
        assertEquals("l{i{line}n}e", Logger.concat("l{i{{}}n}e", "line"));
        assertEquals("li{line}ne", Logger.concat("li{{}}ne", "line"));
        assertEquals("line", Logger.concat("{}", "line", "line"));
        assertEquals("liotherne", Logger.concat("li{}ne", "other"));
        assertEquals("line{", Logger.concat("line{", "other"));
        assertEquals("liothern123e", Logger.concat("li{}n{}e", "other", "123"));
        assertEquals("1li2ne3", Logger.concat("{}li{}ne{}", "1", "2", "3"));
        assertTrue(Logger.concat((String) null, "other") == null);
        assertEquals("", Logger.concat("", "arg"));
        assertEquals("notnull", Logger.concat("notnull", (String) null));
        assertEquals("notnull", Logger.concat("notnull"));
        assertEquals("line", Logger.concat("line"));
        assertEquals("l{}ine", Logger.concat("l{}ine"));
        assertEquals("lotherine{}", Logger.concat("l{}ine{}", "other"));
        assertEquals("l{}ine", Logger.concat("l{}ine", "{}"));
        assertEquals("l{}ine1", Logger.concat("l{}ine{}", "{}", "1"));
        assertEquals("l{}inenull", Logger.concat("l{}ine{}", "{}", null));
        assertEquals("l'2'ine", Logger.concat("l'{}'ine", "2"));
        assertEquals("line", Logger.concat("line", ""));
    }
}

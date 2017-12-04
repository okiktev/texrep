package com.delfin.texrep;

import org.junit.Test;

public class MainText {

    @Test
    public void testMain() {
        Main.main(new String[] { ".", "^.*\\.txt$" });
    }

}

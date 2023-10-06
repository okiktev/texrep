package com.delfin.texrep;

import org.junit.Test;

public class MainText {

    @Test
    public void testMain() {
        // Main.main(new String[] { ".", "^.*\\.txt$" });
        Main.main(new String[] { ".", "-eq", "README.md" });
    }

}

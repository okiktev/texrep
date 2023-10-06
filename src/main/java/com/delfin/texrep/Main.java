package com.delfin.texrep;

import java.io.File;
import java.util.List;

public class Main {
    
    static Logger logger;

    public static void main(String[] args) {
        int code = doAction(args);
        if (code != 0) {
            logger.flush();
        }
        System.exit(code);
    }

    private static int doAction(String[] args) {
        logger = createLogger(args);
        try {
            if (args == null || args.length < 2) {
                logger.info("usage: texrep <root dir> [-eq] <file name pattern>");
                return 2;
            }
            // TODO check valid file name pattern format
            List<File> filesToSearch = new Files(args).grab();
            new Replacer(filesToSearch).process();

            return 0;
        } catch (Exception e) {
            logger.error(e, "A critical error occurred");
            return 1;
        }
    }

    private static Logger createLogger(String[] args) {
        return new ConsoleLogger(true);
    }

}

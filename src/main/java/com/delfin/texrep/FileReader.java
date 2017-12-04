package com.delfin.texrep;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

abstract class FileReader {

    static class FileReaderException extends Exception {

        private static final long serialVersionUID = 2497321424711135838L;

        public FileReaderException(String message, Throwable cause) {
            super(message, cause);
        }

        public FileReaderException(Throwable cause) {
            super(cause);
        }
    }

    void read(File file) throws FileReaderException, FileNotFoundException {
        if (file == null) {
            throw new FileNotFoundException("File must not be null");
        }
        if (!file.exists()) {
            throw new FileNotFoundException("File " + file + " does not exist");
        }
        Scanner scanner = new Scanner(file);
        try {
            while (scanner.hasNextLine()) {
                proceed(scanner.nextLine());
            }
        } catch (Exception e) {
            throw new FileReaderException("An error occurred while processing file " + file, e);
        } finally {
            scanner.close();
        }
    }

    protected abstract void proceed(String line) throws FileReaderException;
}

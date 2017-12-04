package com.delfin.texrep;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;

import com.delfin.texrep.FileReader.FileReaderException;

class Replacer {

    private List<File> files;

    private Properties tokens;

    public Replacer(List<File> files) {
        this.files = files;
    }

    public void process() throws FileReaderException, IOException {
        for (File f : files) {
            File temp = File.createTempFile("texrep", "");
            final FileWriter writer = new FileWriter(temp);
            new FileReader() {
                @Override
                protected void proceed(String line) throws FileReaderException {
                    try {
                        writer.write(replaceTokenIn(line, f) + System.lineSeparator());
                    } catch (IOException e) {
                        throw new FileReaderException(e);
                    }
                }
            }.read(f);
            writer.flush();
            writer.close();
            java.nio.file.Files.move(Paths.get(temp.getAbsolutePath()), Paths.get(f.getAbsolutePath()), StandardCopyOption.REPLACE_EXISTING);
            // temp.renameTo(f.getAbsoluteFile());
            // int y = 0;
        }
    }

    protected String replaceTokenIn(String line, File file) throws FileNotFoundException, IOException {
        for (Entry<Object, Object> entry : getTokens().entrySet()) {
            String before = line;
            String key = (String) entry.getKey();
            String val = (String) entry.getValue();
            line = line.replace(key, val);
            if (!line.equals(before)) {
                Main.logger.debug("The '{}' has been replaced by '{}' in file {}", key, val, file.getAbsolutePath());
            }
        }
        return line;
    }

    private Properties getTokens() throws FileNotFoundException, IOException {
        if (tokens == null) {
            try (InputStream stream = new FileInputStream("tokens.properties")) {
                tokens = new Properties();
                tokens.load(stream);
            }
        }
        return tokens;
    }

}

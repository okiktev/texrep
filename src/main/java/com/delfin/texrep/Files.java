package com.delfin.texrep;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.stream.Collectors;

class Files {

    private Path root;

    private String pattern;

    Files(String[] args) {
        root = Paths.get(new File(args[0]).getAbsolutePath());
        pattern = args[1];
    }

    List<File> grab() throws IOException {
        return java.nio.file.Files.find(root, Integer.MAX_VALUE,
                (path, attrs) -> {
                    Main.logger.debug("Checking {} with file attributes {}", path, attrsToStr(attrs));
                    return path.toFile().getName().matches(pattern);
                })
                .map(Path::toFile)
                .collect(Collectors.toList());
    }

    private String attrsToStr(BasicFileAttributes attrs) {
    	return Logger.concat("BasicFileAttributes[creationTime={}, isRegularFile={}, lastAccessTime={}, lastModifiedTime={}, size={}]"
    			, attrs.creationTime(), attrs.isRegularFile(), attrs.lastAccessTime(), attrs.lastModifiedTime(), attrs.size());
    }

}

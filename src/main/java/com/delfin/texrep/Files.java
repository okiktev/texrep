package com.delfin.texrep;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Files {

    private Path root;

    private String pattern;

    private boolean isNotRegx;

    Files(String[] args) {
        root = getRoot(args);
        pattern = getPattern(args);
        isNotRegx = isRegx(args);
    }

    private static boolean isRegx(String[] args) {
        return Stream.of(args).filter(a -> a.equalsIgnoreCase("-eq")).findFirst().isPresent();
    }

    private static String getPattern(String[] args) {
        for (int i = 1; i < args.length; ++i) {
            if (!"-eq".equalsIgnoreCase(args[i])) {
                return args[i];
            }
        }
        throw new IllegalArgumentException("Unknown passed argument");
    }

    private static Path getRoot(String[] args) {
        return Paths.get(new File(args[0]).getAbsolutePath());
    }

    List<File> grab() throws IOException {
        return java.nio.file.Files.find(root, Integer.MAX_VALUE,
                (path, attrs) -> {
                    boolean res = isNotRegx ? path.toFile().getName().equals(pattern) : path.toFile().getName().matches(pattern);
                    if (res) {
                        Main.logger.debug("Found file {} with file attributes {}", path, attrsToStr(attrs));
                    }
                    return res;
                })
                .map(Path::toFile)
                .collect(Collectors.toList());
    }

    private String attrsToStr(BasicFileAttributes attrs) {
    	return Logger.concat("BasicFileAttributes[creationTime={}, isRegularFile={}, lastAccessTime={}, lastModifiedTime={}, size={}]"
    			, attrs.creationTime(), attrs.isRegularFile(), attrs.lastAccessTime(), attrs.lastModifiedTime(), attrs.size());
    }

}

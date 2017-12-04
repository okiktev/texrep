package com.delfin.texrep;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

class Files {
    
    private Path root;

    private String pattern;
    
    Files(String[] args) {
        root = Paths.get(new File(args[0]).getAbsolutePath());
        pattern = args[1];
    }
// ".*.pom"
    List<File> grab() throws IOException {
        
        //PathMatcher matcher = FileSystems.getDefault().getPathMatcher(pattern);
        
        return java.nio.file.Files.find(root, Integer.MAX_VALUE,
                (path, attrs) -> {
                    Main.logger.debug("Checking {} with file attributes {}", path, attrs);
                    return path.toFile().getName().matches(pattern);
                })
                // (path, basicFileAttributes) -> isMatch(path, basicFileAttributes))
                .map(Path::toFile)
                .collect(Collectors.toList());

    }
    
}

package com.github.caay2000.searcher.io;

import java.nio.file.Path;
import java.util.Set;
import java.util.stream.Stream;

public interface FileReader {

    Set<Path> readAllFiles(String directory);

    Stream<String> getFileLines(Path path);
}

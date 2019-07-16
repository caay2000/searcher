package com.github.caay2000.searcher.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import com.github.caay2000.searcher.model.ApplicationException;

public class SystemFileReader implements FileReader {

    @Override
    public Set<Path> readAllFiles(String directory) {

        try (Stream<Path> filesStream = Files.list(Paths.get(directory))) {
            return filesStream
                    .filter(file -> file.toFile().isFile())
                    .filter(Files::isReadable)
                    .collect(Collectors.toSet());
        } catch (IOException e) {
            throw new ApplicationException(String.format("error reading file %s", directory));
        }
    }

    @Override
    public Stream<String> getFileLines(Path path) {

        try {
            return Files.lines(path);
        } catch (IOException e) {
            throw new ApplicationException(String.format("error reading file %s", path.getFileName().toString()));
        }
    }
}

package caay2000.github.com.searcher.io;

import caay2000.github.com.searcher.model.ApplicationException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SystemFileReader implements FileReader {

    @Override
    public Set<Path> readAllFiles(String directory) {

        try {
            return Files.list(Paths.get(directory))
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

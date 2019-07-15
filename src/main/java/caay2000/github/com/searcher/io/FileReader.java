package caay2000.github.com.searcher.io;

import java.nio.file.Path;
import java.util.Set;
import java.util.stream.Stream;

public interface FileReader {

    Set<Path> readAllFiles(String directory);

    Stream<String> getFileLines(Path path);
}

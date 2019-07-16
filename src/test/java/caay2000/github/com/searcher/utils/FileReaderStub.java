package caay2000.github.com.searcher.utils;

import java.nio.file.Path;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Stream;

import caay2000.github.com.searcher.io.FileReader;

public class FileReaderStub implements FileReader {

    private final Set<Path> files;
    private final Queue<Stream<String>> lines;

    public FileReaderStub(Set<Path> files) {
        this.files = files;
        this.lines = new ArrayDeque<>(Arrays.asList(Stream.empty()));
    }

    public FileReaderStub(Set<Path> files, Stream<String>... lines) {
        this.files = files;
        this.lines = new ArrayDeque<>(Arrays.asList(lines));
    }

    @Override
    public Set<Path> readAllFiles(String directory) {
        return files;
    }

    @Override
    public Stream<String> getFileLines(Path path) {
        return lines.poll();
    }
}

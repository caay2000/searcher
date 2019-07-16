package caay2000.github.com.searcher.model;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import caay2000.github.com.searcher.io.FileReader;

public class DocumentLibraryFactory {

    private final FileReader fileReader;

    public DocumentLibraryFactory(FileReader fileReader) {
        this.fileReader = fileReader;
    }

    private static Stream<? extends String> splitByWord(String line) {
        return Arrays.stream(line.split("\\s+|,\\s*|\\.\\s*"));
    }

    public DocumentLibrary createNewLibrary(String directory) {

        DocumentLibrary library = new DocumentLibrary();
        for (Path file : fileReader.readAllFiles(directory)) {
            Set<String> words = fileReader.getFileLines(file)
                                          .flatMap(DocumentLibraryFactory::splitByWord)
                                          .collect(Collectors.toSet());
            if (!words.isEmpty()) {
                String fileName = file.getFileName().toString();
                library.addDocument(new Document(fileName, words));
            }
        }
        return library;
    }
}

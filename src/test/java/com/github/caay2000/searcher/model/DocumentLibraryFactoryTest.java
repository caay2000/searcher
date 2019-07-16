package com.github.caay2000.searcher.model;

import java.io.File;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;
import org.junit.Assert;
import org.junit.Test;
import com.github.caay2000.searcher.io.FileReader;
import com.github.caay2000.searcher.utils.FileReaderStub;

public class DocumentLibraryFactoryTest {

    private static final String filename = "filename";
    private static final String anotherFilename = "anotherFilename";
    private static final Path ONE_PATH = new File(filename).toPath();
    private static final Path ANOTHER_PATH = new File(anotherFilename).toPath();
    private static final String ANY_DIRECTORY = "directory";

    @Test
    public void noFiles() {

        DocumentLibraryFactory testee = new DocumentLibraryFactory(new FileReaderStub(new HashSet<>()));

        DocumentLibrary library = testee.createNewLibrary("path");

        Assert.assertEquals(0, library.size());
    }

    @Test
    public void fileWithoutWords() {

        Set<Path> files = new HashSet<>(Arrays.asList(ONE_PATH));
        FileReader fileReader = new FileReaderStub(files, Stream.empty());
        DocumentLibraryFactory testee = new DocumentLibraryFactory(fileReader);

        DocumentLibrary library = testee.createNewLibrary(ANY_DIRECTORY);

        Assert.assertEquals(0, library.size());
    }

    @Test
    public void fileWithOneWord() {

        Set<Path> files = new HashSet<>(Arrays.asList(ONE_PATH));
        FileReader fileReader = new FileReaderStub(files, Stream.of("word"));
        DocumentLibraryFactory testee = new DocumentLibraryFactory(fileReader);

        DocumentLibrary library = testee.createNewLibrary(ANY_DIRECTORY);

        Assert.assertEquals(1, countWords(library, filename));
    }

    @Test
    public void fileWithRepeatedWord() {

        Set<Path> files = new HashSet<>(Arrays.asList(ONE_PATH));
        FileReader fileReader = new FileReaderStub(files, Stream.of("word word"));
        DocumentLibraryFactory testee = new DocumentLibraryFactory(fileReader);

        DocumentLibrary library = testee.createNewLibrary(ANY_DIRECTORY);

        Assert.assertEquals(1, countWords(library, filename));
    }

    @Test
    public void fileWithMultipleWords() {

        Set<Path> files = new HashSet<>(Arrays.asList(ONE_PATH));
        FileReader fileReader = new FileReaderStub(files, Stream.of("word another"));
        DocumentLibraryFactory testee = new DocumentLibraryFactory(fileReader);

        DocumentLibrary library = testee.createNewLibrary(ANY_DIRECTORY);

        Assert.assertEquals(2, countWords(library, filename));
    }

    @Test
    public void multipleFilesWithMultipleWords() {

        Set<Path> files = new HashSet<>(Arrays.asList(ONE_PATH, ANOTHER_PATH));
        FileReader fileReader = new FileReaderStub(files, Stream.of("word another"), Stream.of("more word"));
        DocumentLibraryFactory testee = new DocumentLibraryFactory(fileReader);

        DocumentLibrary library = testee.createNewLibrary(ANY_DIRECTORY);

        Assert.assertEquals(2, library.size());
        Assert.assertEquals(2, countWords(library, filename));
        Assert.assertEquals(2, countWords(library, anotherFilename));
    }

    @Test
    public void fileWithMultipleWordsAndCommaOrDot() {

        Set<Path> files = new HashSet<>(Arrays.asList(ONE_PATH));
        FileReader fileReader = new FileReaderStub(files, Stream.of("word,another.More"));
        DocumentLibraryFactory testee = new DocumentLibraryFactory(fileReader);

        DocumentLibrary library = testee.createNewLibrary(ANY_DIRECTORY);

        Assert.assertEquals(3, countWords(library, filename));
    }

    @Test
    public void filenameIsStoredCorrectly() {

        Set<Path> files = new HashSet<>(Arrays.asList(ONE_PATH));
        FileReader fileReader = new FileReaderStub(files, Stream.of("word,another.More"));
        DocumentLibraryFactory testee = new DocumentLibraryFactory(fileReader);

        DocumentLibrary library = testee.createNewLibrary(ANY_DIRECTORY);

        Assert.assertEquals(1, countFiles(library));
    }

    private long countFiles(DocumentLibrary library) {
        return library.getDocuments().stream().count();
    }

    private long countWords(DocumentLibrary library, String anotherFilename) {
        return library.getDocuments().stream()
                .filter(e -> e.getFilename().equals(anotherFilename))
                .flatMap(e -> e.getWords().stream())
                .count();
    }
}
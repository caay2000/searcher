package com.github.caay2000.searcher;

import com.github.caay2000.searcher.io.ConsoleOperation;
import com.github.caay2000.searcher.utils.ConsoleSpy;
import com.github.caay2000.searcher.utils.FileReaderStub;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class SearcherApplicationTest {

    @Test
    public void invalidInputThrowsException() {

        ConsoleSpy consoleSpy = new ConsoleSpy();
        SearcherApplication testee = new SearcherApplication(null, consoleSpy);

        testee.execute(new String[]{});

        Assert.assertTrue(consoleSpy.getWrites().contains("ERROR: invalid number of params"));
    }

    @Test
    public void invalidInputPrintsHelp() {

        ConsoleSpy consoleSpy = new ConsoleSpy();
        SearcherApplication testee = new SearcherApplication(null, consoleSpy);

        testee.execute(new String[]{});
        Assert.assertTrue(consoleSpy.getWrites().contains("SEARCHER for Adevinta"));
        Assert.assertTrue(consoleSpy.getWrites().contains("how to run the program:"));
        Assert.assertTrue(consoleSpy.getWrites().contains("java -cp searcher.jar Searcher [path_to_your_directory]"));
        Assert.assertTrue(consoleSpy.getWrites().contains("Albert Casanovas(caay2000@gmail.com)"));
    }

    @Test
    public void quitExits() {

        ConsoleSpy consoleSpy = new ConsoleSpy(ConsoleOperation.aQuitOperation());
        FileReaderStub fileReaderStub = new FileReaderStub(new HashSet<>());
        SearcherApplication testee = new SearcherApplication(fileReaderStub, consoleSpy);

        testee.execute(new String[]{"example"});

        Assert.assertEquals(2, consoleSpy.getWrites().size());
    }

    @Test
    public void searchAndQuit() {

        ConsoleSpy consoleSpy = new ConsoleSpy(ConsoleOperation.aSearchOperation("search"),
                ConsoleOperation.aQuitOperation());
        FileReaderStub fileReaderStub = new FileReaderStub(new HashSet<>());
        SearcherApplication testee = new SearcherApplication(fileReaderStub, consoleSpy);

        testee.execute(new String[]{"example"});

        Assert.assertTrue(consoleSpy.getWrites().contains("no matches found"));
    }

    @Test
    public void searchReturnsOnly10Elements() {

        ConsoleSpy consoleSpy = new ConsoleSpy(ConsoleOperation.aSearchOperation("search"),
                ConsoleOperation.aQuitOperation());
        FileReaderStub fileReaderStub = new FileReaderStub(createPaths(15), createLines(15, "search"));
        SearcherApplication testee = new SearcherApplication(fileReaderStub, consoleSpy);

        testee.execute(new String[]{"example"});

        Assert.assertEquals(13, consoleSpy.getWrites().size());
    }

    private Set<Path> createPaths(int number) {
        return IntStream.range(1, number + 1)
                .mapToObj(e -> new File(String.valueOf(e)).toPath())
                .collect(Collectors.toSet());
    }

    private Stream<String>[] createLines(int number, String value) {


        return (Stream<String>[]) IntStream.range(0, number)
                .mapToObj(e -> Stream.of(value))
                .collect(Collectors.toList()).toArray(new Stream[0]);
    }


}

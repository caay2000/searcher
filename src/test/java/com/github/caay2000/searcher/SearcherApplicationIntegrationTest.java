package com.github.caay2000.searcher;

import com.github.caay2000.searcher.io.ConsoleOperation;
import com.github.caay2000.searcher.io.SystemFileReader;
import com.github.caay2000.searcher.utils.ConsoleSpy;
import org.junit.Assert;
import org.junit.Test;

public class SearcherApplicationIntegrationTest {

    @Test
    public void startAndQuit() {

        ConsoleSpy console = new ConsoleSpy(ConsoleOperation.aQuitOperation());
        SearcherApplication searcherApplication = new SearcherApplication(new SystemFileReader(), console);

        searcherApplication.execute(new String[]{getFilePath("examples")});

        Assert.assertTrue(console.getWrites().get(0).contains("files read in directory"));
    }

    @Test
    public void searchOneWord() {

        ConsoleSpy console = new ConsoleSpy(ConsoleOperation.aSearchOperation("dog"), ConsoleOperation.aQuitOperation());
        SearcherApplication searcherApplication = new SearcherApplication(new SystemFileReader(), console);

        searcherApplication.execute(new String[]{getFilePath("examples")});

        Assert.assertTrue(console.getWrites().contains("animals.txt : 100%"));
    }

    @Test
    public void searchTwoWords() {

        ConsoleSpy console = new ConsoleSpy(ConsoleOperation.aSearchOperation("plane skate"), ConsoleOperation.aQuitOperation());
        SearcherApplication searcherApplication = new SearcherApplication(new SystemFileReader(), console);

        searcherApplication.execute(new String[]{getFilePath("examples")});

        Assert.assertTrue(console.getWrites().contains("vehicles.txt : 100%"));
        Assert.assertTrue(console.getWrites().contains("things.txt : 50%"));
    }

    @Test
    public void notFound() {

        ConsoleSpy console = new ConsoleSpy(ConsoleOperation.aSearchOperation("notFoundWord"), ConsoleOperation.aQuitOperation());
        SearcherApplication searcherApplication = new SearcherApplication(new SystemFileReader(), console);

        searcherApplication.execute(new String[]{getFilePath("examples")});

        Assert.assertTrue(console.getWrites().contains("no matches found"));
    }

    private String getFilePath(String file) {

        try {
            return solveWindowsIssue(this.getClass().getClassLoader().getResource(file).getFile());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String solveWindowsIssue(String path) {
        return path.replaceFirst("/", "");
    }
}

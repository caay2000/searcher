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

        Assert.assertEquals(2, console.getWrites().size());
        Assert.assertTrue(console.getWrites().get(0).contains("files read in directory"));
    }

    @Test
    public void searchOneWord() {

        ConsoleSpy console = new ConsoleSpy(ConsoleOperation.aSearchOperation("dog"), ConsoleOperation.aQuitOperation());
        SearcherApplication searcherApplication = new SearcherApplication(new SystemFileReader(), console);

        searcherApplication.execute(new String[]{getFilePath("examples")});

        Assert.assertEquals(4, console.getWrites().size());
        Assert.assertEquals("animals.txt : 100%", console.getWrites().get(2));
    }

    @Test
    public void searchTwoWords() {

        ConsoleSpy console = new ConsoleSpy(ConsoleOperation.aSearchOperation("plane skate"), ConsoleOperation.aQuitOperation());
        SearcherApplication searcherApplication = new SearcherApplication(new SystemFileReader(), console);

        searcherApplication.execute(new String[]{getFilePath("examples")});

        Assert.assertEquals(5, console.getWrites().size());
        Assert.assertEquals("vehicles.txt : 100%", console.getWrites().get(2));
        Assert.assertEquals("things.txt : 50%", console.getWrites().get(3));
    }

    @Test
    public void notFound() {

        ConsoleSpy console = new ConsoleSpy(ConsoleOperation.aSearchOperation("notFoundWord"), ConsoleOperation.aQuitOperation());
        SearcherApplication searcherApplication = new SearcherApplication(new SystemFileReader(), console);

        searcherApplication.execute(new String[]{getFilePath("examples")});

        Assert.assertEquals(4, console.getWrites().size());
        Assert.assertEquals("no matches found", console.getWrites().get(2));
    }

    private String getFilePath(String file) {

        try {

            String path = this.getClass().getClassLoader().getResource(file).getFile();

            return solveWindowsIssue(path);
//            return "D:/kiwibcn/workspace/searcher/target/test-classes/" + file;
            //return "C:/Users/acasanovas/workspace/kiwibcn/searcher/target/test-classes/" + file;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String solveWindowsIssue(String path) {
        return path.replaceFirst("/", "");
    }
}

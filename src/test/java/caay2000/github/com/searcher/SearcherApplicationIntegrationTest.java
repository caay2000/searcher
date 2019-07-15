package caay2000.github.com.searcher;

import caay2000.github.com.searcher.io.ConsoleOperation;
import caay2000.github.com.searcher.io.SystemFileReader;
import caay2000.github.com.searcher.utils.ConsoleSpy;
import org.junit.Assert;
import org.junit.Test;

public class SearcherApplicationIntegrationTest {

    @Test
    public void startAndQuit() {

        ConsoleSpy console = new ConsoleSpy(ConsoleOperation.aQuitOperation());
        SearcherApplication searcherApplication = new SearcherApplication(new SystemFileReader(), console);

        searcherApplication.execute(new String[]{getFilePath("examples")});

        Assert.assertEquals(1, console.getWrites().size());
        Assert.assertTrue(console.getWrites().get(0).contains("2 files read in directory"));
    }

    @Test
    public void searchOneWord() {

        ConsoleSpy console = new ConsoleSpy(ConsoleOperation.aSearchOperation("dog"), ConsoleOperation.aQuitOperation());
        SearcherApplication searcherApplication = new SearcherApplication(new SystemFileReader(), console);

        searcherApplication.execute(new String[]{getFilePath("examples")});

        Assert.assertEquals(3, console.getWrites().size());
        Assert.assertEquals("animals.txt : 100%", console.getWrites().get(1));
        Assert.assertEquals("things.txt : 0%", console.getWrites().get(2));
    }

    private String getFilePath(String file) {

        try {

//        return this.getClass().getClassLoader().getResource(file).toURI().toString();
            return "D:/kiwibcn/workspace/searcher/target/test-classes/" + file;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

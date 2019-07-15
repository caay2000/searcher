package caay2000.github.com.searcher;

import caay2000.github.com.searcher.io.ConsoleOperation;
import caay2000.github.com.searcher.model.ApplicationException;
import caay2000.github.com.searcher.utils.ConsoleSpy;
import caay2000.github.com.searcher.utils.FileReaderStub;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.HashSet;

public class SearcherApplicationTest {

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Test
    public void invalidInputThrowsException() {

        expectedException.expect(ApplicationException.class);
        expectedException.expectMessage("invalid number of params");

        ConsoleSpy consoleSpy = new ConsoleSpy();
        SearcherApplication testee = new SearcherApplication(null, consoleSpy);

        testee.execute(new String[]{});

        Assert.assertEquals(1, consoleSpy.getWrites().size());
    }

    @Test
    public void invalidInputPrintsHelp() {

        ConsoleSpy consoleSpy = new ConsoleSpy();
        SearcherApplication testee = new SearcherApplication(null, consoleSpy);

        try {
            testee.execute(new String[]{});
        } catch (ApplicationException e) {
            Assert.assertEquals(5, consoleSpy.getWrites().size());
            Assert.assertEquals("SEARCHER for Adevinta", consoleSpy.getWrites().get(0));
            Assert.assertEquals("how to run the program:", consoleSpy.getWrites().get(1));
            Assert.assertEquals("java -cp searcher [path_to_your_directory]", consoleSpy.getWrites().get(2));
            Assert.assertEquals("", consoleSpy.getWrites().get(3));
            Assert.assertEquals("Albert Casanovas(caay2000@gmail.com)", consoleSpy.getWrites().get(4));
        }
    }

    @Test
    public void quitExits() {

        ConsoleSpy consoleSpy = new ConsoleSpy(ConsoleOperation.aQuitOperation());
        FileReaderStub fileReaderStub = new FileReaderStub(new HashSet<>());
        SearcherApplication testee = new SearcherApplication(fileReaderStub, consoleSpy);

        testee.execute(new String[]{"example"});
    }


    @Test
    public void searchAndQuit() {

        ConsoleSpy consoleSpy = new ConsoleSpy(ConsoleOperation.aSearchOperation("search"),
                ConsoleOperation.aQuitOperation());
        FileReaderStub fileReaderStub = new FileReaderStub(new HashSet<>());
        SearcherApplication testee = new SearcherApplication(fileReaderStub, consoleSpy);

        testee.execute(new String[]{"example"});
    }


}

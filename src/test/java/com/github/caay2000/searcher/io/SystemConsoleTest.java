package com.github.caay2000.searcher.io;

import com.github.caay2000.searcher.utils.InputStreamStub;
import com.github.caay2000.searcher.utils.OutputStreamSpy;
import org.junit.Assert;
import org.junit.Test;

public class SystemConsoleTest {

    @Test
    public void writeWorks() {

        OutputStreamSpy outputStream = new OutputStreamSpy();
        SystemConsole testee = new SystemConsole(outputStream, new InputStreamStub(""));

        testee.write("string");

        Assert.assertEquals("string", outputStream.getWrites());
    }

    @Test
    public void writelnWorks() {

        OutputStreamSpy outputStream = new OutputStreamSpy();
        SystemConsole testee = new SystemConsole(outputStream, new InputStreamStub(""));

        testee.writeln("string");

        Assert.assertEquals("string\r\n", outputStream.getWrites());
    }

    @Test
    public void readWorksWithQuit() {

        InputStreamStub inputStream = new InputStreamStub(":quit");
        SystemConsole testee = new SystemConsole(new OutputStreamSpy(), inputStream);

        ConsoleOperation operation = testee.read();

        Assert.assertEquals(ConsoleOperation.Operation.QUIT, operation.getOperation());
    }

    @Test
    public void readWorksWithSearch() {

        InputStreamStub inputStream = new InputStreamStub("example");
        SystemConsole testee = new SystemConsole(new OutputStreamSpy(), inputStream);

        ConsoleOperation operation = testee.read();

        Assert.assertEquals(ConsoleOperation.Operation.SEARCH, operation.getOperation());
        Assert.assertTrue(operation.getValue().contains("example"));
    }

}
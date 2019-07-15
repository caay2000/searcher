package caay2000.github.com.searcher.io;


import caay2000.github.com.searcher.model.ApplicationException;

import java.io.IOException;
import java.io.OutputStream;

public class SystemConsole implements Console {

    private final OutputStream outputStream;

    public SystemConsole(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    @Override
    public void write(String string) {
        try {
            outputStream.write(string.getBytes());
            outputStream.write(System.getProperty("line.separator").getBytes());
        } catch (IOException ioe) {
            throw new ApplicationException("error writing to system console");
        }
    }

    @Override
    public ConsoleOperation read() {
        return ConsoleOperation.aSearchOperation("value");
    }

}

package com.github.caay2000.searcher.io;

import com.github.caay2000.searcher.model.ApplicationException;

import java.io.*;

public class SystemConsole implements Console {

    private final OutputStream outputStream;
    private final BufferedReader bufferedReader;

    public SystemConsole(OutputStream outputStream, InputStream inputStream) {
        this.outputStream = outputStream;
        this.bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
    }

    @Override
    public void write(String string) {
        try {
            outputStream.write(string.getBytes());
        } catch (IOException ioe) {
            throw new ApplicationException("error writing to system console");
        }
    }

    @Override
    public void writeln(String string) {
        try {
            this.write(string);
            outputStream.write(System.getProperty("line.separator").getBytes());
        } catch (IOException ioe) {
            throw new ApplicationException("error writing to system console");
        }
    }

    @Override
    public ConsoleOperation read() {
        try {
            String line = bufferedReader.readLine();
            return parseConsoleOperation(line);
        } catch (IOException e) {
            throw new ApplicationException("error writing to system console");
        }
    }

    private ConsoleOperation parseConsoleOperation(String line) {
        if (line.startsWith(":quit")) {
            return ConsoleOperation.aQuitOperation();
        }
        return ConsoleOperation.aSearchOperation(line);
    }
}

package com.github.caay2000.searcher.utils;


import java.io.IOException;
import java.io.OutputStream;

public class OutputStreamSpy extends OutputStream {

    private StringBuilder builder = new StringBuilder();

    @Override
    public void write(int b) throws IOException {
        builder.append((char) (b & 0xFF));
    }

    public String getWrites() {
        return builder.toString();
    }
}

package com.github.caay2000.searcher.io;

public interface Console {

    void write(String string);

    void writeln(String string);

    ConsoleOperation read();
}

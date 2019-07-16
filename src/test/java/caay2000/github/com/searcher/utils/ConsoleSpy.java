package caay2000.github.com.searcher.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import caay2000.github.com.searcher.io.Console;
import caay2000.github.com.searcher.io.ConsoleOperation;

public class ConsoleSpy implements Console {

    private final List<String> writes;
    private final Queue<ConsoleOperation> consoleOperations;

    public ConsoleSpy(ConsoleOperation... consoleOperations) {
        this.consoleOperations = new LinkedList<>(Arrays.asList(consoleOperations));
        this.writes = new ArrayList<>();
    }

    @Override
    public void write(String value) {
        this.writes.add(value);
    }

    @Override
    public ConsoleOperation read() {
        return consoleOperations.poll();
    }

    public List<String> getWrites() {
        return this.writes;
    }
}

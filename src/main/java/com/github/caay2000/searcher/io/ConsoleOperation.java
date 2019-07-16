package com.github.caay2000.searcher.io;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class ConsoleOperation {

    private final Operation operation;
    private final String value;

    private ConsoleOperation(Operation operation, String value) {
        this.operation = operation;
        this.value = value;
    }

    public static ConsoleOperation aQuitOperation() {
        return new ConsoleOperation(Operation.QUIT, null);
    }

    public static ConsoleOperation aSearchOperation(String value) {
        return new ConsoleOperation(Operation.SEARCH, value);
    }

    public Operation getOperation() {
        return operation;
    }

    public Set<String> getValue() {
        return Arrays.stream(this.value.split("\\s+|,\\s*|\\.\\s*")).collect(Collectors.toSet());
    }

    public enum Operation {
        SEARCH,
        QUIT
    }
}

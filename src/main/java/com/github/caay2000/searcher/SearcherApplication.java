package com.github.caay2000.searcher;

import com.github.caay2000.searcher.io.Console;
import com.github.caay2000.searcher.io.ConsoleOperation;
import com.github.caay2000.searcher.io.FileReader;
import com.github.caay2000.searcher.model.ApplicationException;
import com.github.caay2000.searcher.model.DocumentLibrary;
import com.github.caay2000.searcher.model.DocumentLibraryFactory;
import com.github.caay2000.searcher.model.SearchResult;
import com.github.caay2000.searcher.processor.SearchProcessor;
import com.github.caay2000.searcher.processor.SimpleSearchProcessor;

public class SearcherApplication {

    private final Console console;
    private final DocumentLibraryFactory documentLibraryFactory;
    private final SearchProcessor searchProcessor;

    public SearcherApplication(FileReader fileReader, Console console) {
        this.console = console;
        this.documentLibraryFactory = new DocumentLibraryFactory(fileReader);
        this.searchProcessor = new SimpleSearchProcessor();
    }

    public void execute(String[] input) {

        try {
            validateInput(input);
            executeSearcher(input[0]);
        } catch (ApplicationException e) {
            console.writeln(String.format("ERROR: %s", e.getMessage()));
        }
    }

    private void executeSearcher(String path1) {
        String path = path1;
        DocumentLibrary documentLibrary = documentLibraryFactory.createNewLibrary(path);
        console.writeln(String.format("%d files read in directory %s", documentLibrary.size(), path));

        while (true) {
            console.write("search> ");
            ConsoleOperation operation = console.read();
            if (ConsoleOperation.Operation.QUIT.equals(operation.getOperation())) {
                break;
            }

            SearchResult searchResult = searchProcessor.search(operation.getValue(), documentLibrary);
            this.printResult(searchResult);
        }
    }

    private void printResult(SearchResult searchResult) {
        if (searchResult.getResult().isEmpty()) {
            console.writeln("no matches found");
        } else {
            searchResult.getResult().stream()
                    .limit(10)
                    .forEachOrdered(e -> console.writeln(String.format("%s : %d%%", e.getFilename(), e.getValue())));
        }
    }

    private void validateInput(String[] input) {
        if (input.length != 1) {
            printHelp();
            throw new ApplicationException("invalid number of params");
        }
    }

    private void printHelp() {
        console.writeln("SEARCHER for Adevinta");
        console.writeln("how to run the program:");
        console.writeln("java -cp searcher.jar Searcher [path_to_your_directory]");
        console.writeln("Albert Casanovas(caay2000@gmail.com)");
    }
}


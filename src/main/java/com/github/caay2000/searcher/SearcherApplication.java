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

        validateInput(input);

        String path = input[0];
        DocumentLibrary documentLibrary = documentLibraryFactory.createNewLibrary(path);
        console.write(String.format("%d files read in directory %s", documentLibrary.size(), path));

        while (true) {
            ConsoleOperation operation = console.read();
            if (ConsoleOperation.Operation.QUIT.equals(operation.getOperation())) {
                break;
            }

            SearchResult searchResult = searchProcessor.search(operation.getValue(), documentLibrary);
            this.printResult(searchResult);
        }
    }

    private void printResult(SearchResult searchResult) {
        searchResult.getResult().stream()
                    .limit(10)
                    .forEachOrdered(e -> console.write(String.format("%s : %d%%", e.getFilename(), e.getValue())));
    }

    private void validateInput(String[] input) {
        if (input.length != 1 || "-h".equals(input[0]) || "-help".equals(input[0])) {
            printHelp();
            throw new ApplicationException("invalid number of params");
        }
    }

    private void printHelp() {
        console.write("SEARCHER for Adevinta");
        console.write("how to run the program:");
        console.write("java -cp searcher [path_to_your_directory]");
        console.write("");
        console.write("Albert Casanovas(caay2000@gmail.com)");
    }
}


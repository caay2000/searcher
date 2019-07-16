package com.github.caay2000.searcher.model;

import java.util.Set;

public class Document {

    private final String filename;
    private final Set<String> words;

    public Document(String filename, Set<String> words) {
        this.filename = filename;
        this.words = words;
    }

    public String getFilename() {
        return filename;
    }

    public Set<String> getWords() {
        return words;
    }
}

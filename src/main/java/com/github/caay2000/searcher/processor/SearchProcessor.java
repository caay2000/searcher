package com.github.caay2000.searcher.processor;

import java.util.Set;

import com.github.caay2000.searcher.model.DocumentLibrary;
import com.github.caay2000.searcher.model.SearchResult;

public interface SearchProcessor {

    SearchResult search(Set<String> search, DocumentLibrary library);
}

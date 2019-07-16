package caay2000.github.com.searcher.processor;

import java.util.Set;

import caay2000.github.com.searcher.model.DocumentLibrary;
import caay2000.github.com.searcher.model.SearchResult;

public interface SearchProcessor {

    SearchResult search(Set<String> search, DocumentLibrary library);
}

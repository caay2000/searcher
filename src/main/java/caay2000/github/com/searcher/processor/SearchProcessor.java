package caay2000.github.com.searcher.processor;

import caay2000.github.com.searcher.model.DocumentLibrary;
import caay2000.github.com.searcher.model.SearchResult;

import java.util.Set;

public interface SearchProcessor {

    SearchResult search(Set<String> search, DocumentLibrary library);
}

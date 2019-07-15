package caay2000.github.com.searcher.processor;

import caay2000.github.com.searcher.model.Document;
import caay2000.github.com.searcher.model.DocumentLibrary;
import caay2000.github.com.searcher.model.SearchResult;

import java.util.Set;

public class SimpleSearchProcessor implements SearchProcessor {

    private final RatingCalculator ratingCalculator;

    public SimpleSearchProcessor() {
        this.ratingCalculator = new RatingCalculator();
    }

    @Override
    public SearchResult search(Set<String> search, DocumentLibrary library) {

        SearchResult searchResult = new SearchResult();
        for (Document document : library.getDocuments()) {

            long count = search.stream()
                    .filter(document.getWords()::contains)
                    .count();

            searchResult.addResult(document.getFilename(), ratingCalculator.getRating(search.size(), count));
        }
        return searchResult;
    }

    class RatingCalculator {

        int getRating(int total, long found) {
            if (found == 0) {
                return 0;
            }
            return (int) (found * 100) / total;
        }
    }
}

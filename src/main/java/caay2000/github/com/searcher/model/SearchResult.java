package caay2000.github.com.searcher.model;

import java.util.Set;
import java.util.TreeSet;

public class SearchResult {

    private final TreeSet<ResultItem> result;

    public SearchResult() {
        this.result = new TreeSet<>();
    }

    public int size() {
        return this.result.size();
    }

    public Set<ResultItem> getResult() {
        return result.descendingSet();
    }

    public void addResult(String filename, Integer value) {
        this.result.add(new ResultItem(filename, value));
    }

    public class ResultItem implements Comparable<ResultItem> {
        private final String filename;
        private final Integer value;

        public ResultItem(String filename, Integer value) {
            this.filename = filename;
            this.value = value;
        }

        public String getFilename() {
            return filename;
        }

        public Integer getValue() {
            return value;
        }

        @Override
        public int compareTo(ResultItem o) {
            return this.value.compareTo(o.getValue());
        }
    }
}

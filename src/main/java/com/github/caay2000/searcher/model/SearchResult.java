package com.github.caay2000.searcher.model;

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
        if (value > 0) {
            this.result.add(new ResultItem(filename, value));
        }
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
            int compareTo = this.value.compareTo(o.getValue());
            if (compareTo == 0) {
                compareTo = o.filename.compareTo(this.filename);
            }
            return compareTo;
        }
    }
}

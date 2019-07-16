package com.github.caay2000.searcher.model;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class SearchResultTest {

    @Test
    public void addWorks() {

        SearchResult testee = new SearchResult();

        testee.addResult("filename", 100);

        Assert.assertEquals("filename", getResultsAsSortedList(testee).get(0).getFilename());
        Assert.assertEquals(new Integer(100), getResultsAsSortedList(testee).get(0).getValue());
    }

    @Test
    public void getResultIsOrdered() {

        SearchResult testee = new SearchResult();

        testee.addResult("100", 100);
        testee.addResult("50", 50);
        testee.addResult("0", 0);

        List<SearchResult.ResultItem> list = getResultsAsSortedList(testee);

        Assert.assertEquals("100", list.get(0).getFilename());
        Assert.assertEquals("50", list.get(1).getFilename());
        Assert.assertEquals("0", list.get(2).getFilename());
    }

    private List<SearchResult.ResultItem> getResultsAsSortedList(SearchResult testee) {

        List<SearchResult.ResultItem> list = new ArrayList<>();
        list.addAll(testee.getResult());
        return list;
    }
}
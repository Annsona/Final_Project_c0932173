package com.example.moviecatalogue.model;

import java.util.List;

public class TmdbMovieResponse {

    private int page;
    private List<Movie> results;  // List of movies returned by the API

    // Getters and setters
    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<Movie> getResults() {
        return results;
    }

    public void setResults(List<Movie> results) {
        this.results = results;
    }
}

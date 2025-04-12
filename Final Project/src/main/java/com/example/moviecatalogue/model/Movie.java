package com.example.moviecatalogue.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Movie {

    @Id
    private int id;
    private String title;
    private String overview;
    private String releaseDate;
    private double voteAverage;
    private String posterPath;

    private static final String IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500";

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    // Method to generate the full URL for the poster image
    public String getFullPosterUrl() {
        if (posterPath != null && !posterPath.isEmpty()) {
            return IMAGE_BASE_URL + posterPath;
        }
        return "https://picsum.photos/500";
    }
}

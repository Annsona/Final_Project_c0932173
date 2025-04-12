package com.example.moviecatalogue.service;

import com.example.moviecatalogue.model.Movie;
import com.example.moviecatalogue.model.TmdbMovieResponse;
import com.example.moviecatalogue.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
public class TmdbService {

    @Value("${tmdb.api.key}")
    private String apiKey;

    @Value("${tmdb.api.base-url}")
    private String baseUrl;

    @Value("${tmdb.api.image-base-url}")
    private String imageBaseUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private MovieRepository movieRepository;

    // Method to fetch popular movies
    public TmdbMovieResponse getPopularMovies() {
        String uri = UriComponentsBuilder.fromHttpUrl(baseUrl + "/movie/popular")
                .queryParam("api_key", apiKey)
                .toUriString();

        TmdbMovieResponse response = restTemplate.getForObject(uri, TmdbMovieResponse.class);
        return response;
    }

    // Method to search movies by title
    public TmdbMovieResponse searchMoviesByTitle(String query) {
        String uri = UriComponentsBuilder.fromHttpUrl(baseUrl + "/search/movie")
                .queryParam("api_key", apiKey)
                .queryParam("query", query)
                .toUriString();
        return restTemplate.getForObject(uri, TmdbMovieResponse.class);
    }

    // Method to get movie details by ID
    public Movie getMovieDetails(int movieId) {
        String uri = UriComponentsBuilder.fromHttpUrl(baseUrl + "/movie/" + movieId)
                .queryParam("api_key", apiKey)
                .toUriString();
        return restTemplate.getForObject(uri, Movie.class);
    }

    // Save a movie as a favorite
    public Movie saveFavoriteMovie(Movie movie) {
        return movieRepository.save(movie);  // Save movie to the repository (in-memory or DB)
    }

    // Remove a movie from favorites
    public void removeFavoriteMovie(int movieId) {
        movieRepository.deleteById(movieId);  // Delete movie from the repository
    }

    // Get all favorite movies
    public List<Movie> getFavoriteMovies() {
        return movieRepository.findAll();  // Return all favorite movies from the repository
    }

    // Check if a movie is a favorite
    public boolean isFavoriteMovie(int movieId) {
        return movieRepository.existsById(movieId);  // Check if the movie is already a favorite
    }
}

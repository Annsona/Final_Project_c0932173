package com.example.moviecatalogue.controller;

import com.example.moviecatalogue.model.Movie;
import com.example.moviecatalogue.service.TmdbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class MovieController {

    @Autowired
    private TmdbService tmdbService;

    // Display Popular Movies
    @GetMapping("/")
    public String getPopularMovies(Model model) {
        // Fetch the list of popular movies from the API
        model.addAttribute("movies", tmdbService.getPopularMovies().getResults());
        return "index";  // Popular Movies Page
    }

    // Display Popular Movies (New Page)
    @GetMapping("/popular-movies")
    public String getPopularMoviesPage(Model model) {
        // Fetch the list of popular movies
        model.addAttribute("popularMovies", tmdbService.getPopularMovies().getResults());
        return "popular-movies";  // Display popular movies on a separate page
    }


    // Display Movie Details
    @GetMapping("/movie/{id}")
    public String getMovieDetails(@PathVariable int id, Model model) {
        // Fetch movie details
        Movie movie = tmdbService.getMovieDetails(id);

        // Check if the movie is in the favorites list
        boolean isFavorite = tmdbService.isFavoriteMovie(id);

        model.addAttribute("movie", movie);
        model.addAttribute("favorite", isFavorite);  // Pass the flag to the view

        return "movie-details";  // Movie Detail Page
    }

    @GetMapping("/search")
    public String searchMovies(@RequestParam String query, Model model) {
        // Fetch search results based on query
        model.addAttribute("searchResults", tmdbService.searchMoviesByTitle(query).getResults());
        model.addAttribute("imageBaseUrl", "https://image.tmdb.org/t/p/w500");
        return "search-results";  // Search Results Page
    }

    @GetMapping("/addToFavorites/{id}")
    public String addToFavorites(@PathVariable int id) {
        // Add movie to favorites
        Movie movie = tmdbService.getMovieDetails(id);

        // Only add the movie to favorites if it's not already there
        if (!tmdbService.isFavoriteMovie(id)) {
            tmdbService.saveFavoriteMovie(movie);
        }

        return "redirect:/favorites";  // Redirect to the favorites page
    }

    @GetMapping("/removeFromFavorites/{id}")
    public String removeFromFavorites(@PathVariable int id) {
        // Remove movie from favorites
        tmdbService.removeFavoriteMovie(id);

        return "redirect:/favorites";  // Redirect to the favorites page
    }

    @GetMapping("/favorites")
    public String getFavorites(Model model) {
        // Get the list of favorite movies
        model.addAttribute("favoriteMovies", tmdbService.getFavoriteMovies());
        return "Favorites";  // Page to display all favorite movies
    }
}

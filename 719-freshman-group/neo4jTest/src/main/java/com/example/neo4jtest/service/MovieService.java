package com.example.neo4jtest.service;

import com.example.neo4jtest.model.Movie;

public interface MovieService {
    void saveMovie(Movie movie);

    void deleteMovie(String mid);

    String searchMovieByMid(String mid);
}

package com.example.neo4jtest.service.impl;

import com.example.neo4jtest.dao.MovieRepository;
import com.example.neo4jtest.model.Movie;
import com.example.neo4jtest.service.MovieService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@Slf4j
public class MovieServiceImpl implements MovieService {
    @Autowired
    private MovieRepository movieRepository;

    @Override
    public void saveMovie(Movie movie) {
        if(StringUtils.isEmpty(movie.getMid())){
            movie.setMid("1");
        }
        movieRepository.save(movie);    //repository的save()方法会把主键相同的元素覆盖
    }

    @Override
    public void deleteMovie(String mid){
        movieRepository.deleteMovieByMid(mid);
    }
}

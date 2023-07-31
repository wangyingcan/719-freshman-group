package com.example.neo4jtest.service.impl;

import com.example.neo4jtest.dao.MovieRepository;
import com.example.neo4jtest.model.Movie;
import com.example.neo4jtest.service.MovieService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

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

    public String searchMovieByMid(String mid){
        Optional<Movie> movie=movieRepository.findMovieByMid(mid);      //利用mid查找Movie
        if(movie.isPresent()){      //如果查找成功
            return movie.get().getTitle();      //返回对应电影名
        }
        return "查找失败";      //如果查找失败
    }
}

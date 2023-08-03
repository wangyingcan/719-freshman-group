package com.example.neo4jtest.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.example.neo4jtest.model.Movie;
import com.example.neo4jtest.service.impl.MovieServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExcelMovieListener extends AnalysisEventListener<Movie> {

    private MovieServiceImpl movieService;

    public ExcelMovieListener(MovieServiceImpl movieService){
        this.movieService=movieService;
    }
    @Override
    public void invoke(Movie movie, AnalysisContext analysisContext) {
        movieService.saveMovie(movie);
    }

    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("表头信息："+headMap);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
    }
}

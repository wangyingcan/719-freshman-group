package com.example.neo4jtest.controller;
/**
 * 已完成：Movie的Excel版增方法、Movie的JSON版增方法、Movie的删方法
 * 未完成：
 */

import com.alibaba.excel.EasyExcel;
import com.example.neo4jtest.listener.ExcelMovieListener;
import com.example.neo4jtest.model.Movie;
import com.example.neo4jtest.service.impl.MovieServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "movie")
@Slf4j
public class MovieController {

    @Autowired
    private MovieServiceImpl movieService;
    @RequestMapping (value = "save")
    public void save(@RequestBody Movie movie) {
        movieService.saveMovie(movie);
    }

    @RequestMapping (value = "save1")
    public void save1() {
        String fileName = "C:\\Users\\wangyingcan\\Desktop\\IDC任务汇总\\1 719 大数据\\23.6.15 二期文件\\4 研一小组资料\\719-freshman-group\\neo4jTest\\database\\movie.xlsx";
        EasyExcel.read(fileName, Movie.class, new ExcelMovieListener(movieService)).sheet().doRead();
    }

    @RequestMapping (value = "delete")
    public void delete(@RequestParam("mid") String mid) {
        movieService.deleteMovie(mid);
    }

    @RequestMapping("search/{mid}")
    public String search(@PathVariable String mid){ return movieService.searchMovieByMid(mid);}     //将返回的字符串放在HTTP响应体中
}

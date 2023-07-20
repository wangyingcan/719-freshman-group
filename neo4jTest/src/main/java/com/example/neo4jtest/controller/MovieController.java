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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
        String fileName = "C:\\Users\\wangyingcan\\Desktop\\IDC任务汇总\\1 719 大数据\\23.6.15 二期文件\\4 研一小组资料\\我的demo[重写的findById]\\database\\movie.xlsx";
        //read方法的三个参数：第一个：文件名；第二个：实体类的Class对象；第三个：监听器的对象
        EasyExcel.read(fileName, Movie.class, new ExcelMovieListener(movieService)).sheet().doRead();
    }

    //删除movie节点
    @RequestMapping (value = "delete")
    public void delete(@RequestParam("mid") String mid) {
        movieService.deleteMovie(mid);
    }
}

package com.example.neo4jtest.controller;
/**
 * 已完成：RMRelation的Excel版增方法
 * 未完成：RMRelation的JSON版增方法、RMRelation的删、查
 */

import com.alibaba.excel.EasyExcel;
import com.example.neo4jtest.listener.ExcelPMRelationListener;
import com.example.neo4jtest.model.PMRelation;
import com.example.neo4jtest.service.impl.PMRelationServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "PMRelation")
@Slf4j
public class PMRelationController {
    @Autowired
    private PMRelationServiceImpl pmRelationService;

    @RequestMapping(value = "addRelation")
    public void addRelationship(@RequestParam("pid") String startId, @RequestParam("mid")String endId) {    //startId是pid，endId是mid；URL使用的参数名称和@RequestParam相关，方法的参数名作用域仅仅是方法局部
        pmRelationService.addPMRelation(startId, endId);
    }

    @RequestMapping(value = "addRelation1")
    public void addRelationship1() {
        String fileName = "C:\\Users\\wangyingcan\\Desktop\\IDC任务汇总\\1 719 大数据\\23.6.15 二期文件\\4 研一小组资料\\我的demo[重写的findById]\\database\\pmrelation.xlsx";
        EasyExcel.read(fileName, PMRelation.class, new ExcelPMRelationListener(pmRelationService)).sheet().doRead();
    }

    @RequestMapping(value = "deleteSomeRelation")
    public void deleteSomeRelationship(@RequestParam("pid") String startId, @RequestParam("mid")String endId) {    //startId是pid，endId是mid；URL使用的参数名称和@RequestParam相关，方法的参数名作用域仅仅是方法局部
        pmRelationService.deletePMRelationByPidMid(startId, endId);
    }
}

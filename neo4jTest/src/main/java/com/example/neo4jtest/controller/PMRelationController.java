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
    public void addRelationship(@RequestParam("pmid") String pmid,@RequestParam("pid") String startId, @RequestParam("mid")String endId) {
        pmRelationService.addPMRelation(pmid,startId, endId);
    }

    @RequestMapping(value = "addRelation1")
    public void addRelationship1() {
        String fileName = "C:\\Users\\wangyingcan\\Desktop\\IDC任务汇总\\1 719 大数据\\23.6.15 二期文件\\4 研一小组资料\\719-freshman-group\\neo4jTest\\database\\pmrelation.xlsx";
        EasyExcel.read(fileName, PMRelation.class, new ExcelPMRelationListener(pmRelationService)).sheet().doRead();
    }

    @RequestMapping(value = "deleteSomeRelation")
    public void deleteSomeRelationship(@RequestParam("pid") String startId, @RequestParam("mid")String endId) {
        pmRelationService.deletePMRelationByPidMid(startId, endId);
    }

    @RequestMapping(value = "findSomeRelation")
    public String findSomeRelationship(@RequestParam("pid") String startId, @RequestParam("mid")String endId) {
        return pmRelationService.findPMRelationByPidMid(startId, endId);
    }
}

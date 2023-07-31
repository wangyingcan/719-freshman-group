package com.example.neo4jtest.controller;
/**
 * 已完成：
 * 未完成：
 */

import com.alibaba.excel.EasyExcel;
import com.example.neo4jtest.listener.ExcelPPRelationListener;
import com.example.neo4jtest.model.PPRelation;
import com.example.neo4jtest.service.impl.PPRelationServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "PPRelation")
@Slf4j
public class PPRelationController {
    @Autowired
    private PPRelationServiceImpl ppRelationService;

    @RequestMapping(value = "addRelation")
    public void addRelationship(@RequestParam("ppid") String ppid,@RequestParam("pid1") String startId, @RequestParam("pid2")String endId) {
        ppRelationService.addPPRelation(ppid, startId, endId);
    }

    @RequestMapping(value = "addRelation1")
    public void addRelationship1() {
        String fileName = "C:\\Users\\wangyingcan\\Desktop\\IDC任务汇总\\1 719 大数据\\23.6.15 二期文件\\4 研一小组资料\\719-freshman-group\\neo4jTest\\database\\pprelation.xlsx";
        EasyExcel.read(fileName, PPRelation.class, new ExcelPPRelationListener(ppRelationService)).sheet().doRead();
    }

    @RequestMapping(value = "deleteSomeRelation")
    public void deleteSomeRelationship(@RequestParam("pid1") String startId, @RequestParam("pid2")String endId) {
        ppRelationService.deletePPRelationByPidMid(startId, endId);
    }

    @RequestMapping(value = "findSomeRelation")
    public String findSomeRelationship(@RequestParam("pid1") String startId, @RequestParam("pid2")String endId) {
        return ppRelationService.findPPRelationByPids(startId, endId);
    }
}

package com.example.neo4jtest.controller;
/**
 * 已完成：Person的Excel版增方法、Person的JSON版增方法、Person的删方法
 * 未完成：
 */

import com.alibaba.excel.EasyExcel;
import com.example.neo4jtest.listener.ExcelPersonListener;
import com.example.neo4jtest.model.Person;
import com.example.neo4jtest.service.impl.PersonServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "person")
@Slf4j
public class PersonController {

    @Autowired
    private PersonServiceImpl personService;

    @RequestMapping (value = "save")
    public void save(@RequestBody Person person) {
        personService.savePerson(person);
    }

    @RequestMapping (value = "save1")
    public void save1() {
        String fileName = "C:\\Users\\wangyingcan\\Desktop\\IDC任务汇总\\1 719 大数据\\23.6.15 二期文件\\4 研一小组资料\\719-freshman-group\\neo4jTest\\database\\person.xlsx";
        EasyExcel.read(fileName, Person.class, new ExcelPersonListener(personService)).sheet().doRead();
    }

    @RequestMapping (value = "delete")
    public void delete(@RequestParam("pid") String pid) {
        personService.deletePerson(pid);
    }

    @RequestMapping("search/{pid}")
    public String search(@PathVariable String pid){ return personService.searchPersonByPid(pid);}
}

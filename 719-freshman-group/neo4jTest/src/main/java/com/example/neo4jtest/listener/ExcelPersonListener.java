package com.example.neo4jtest.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.example.neo4jtest.model.Person;
import com.example.neo4jtest.service.impl.PersonServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExcelPersonListener extends AnalysisEventListener<Person> {

    private PersonServiceImpl personService;
    public ExcelPersonListener(PersonServiceImpl personService){
        this.personService=personService;
    }

    @Override
    public void invoke(Person person, AnalysisContext analysisContext) {
        personService.savePerson(person);
    }

    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("表头信息："+headMap);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
    }
}

package com.example.neo4jtest.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.example.neo4jtest.model.PMRelation;
import com.example.neo4jtest.service.impl.PMRelationServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExcelPMRelationListener extends AnalysisEventListener<PMRelation> {

    private PMRelationServiceImpl pmRelationService;

    public ExcelPMRelationListener(PMRelationServiceImpl pmRelationService){
        this.pmRelationService=pmRelationService;
    }

    List<PMRelation> list = new ArrayList<PMRelation>();

    @Override
    public void invoke(PMRelation pmRelation, AnalysisContext analysisContext) {
        pmRelationService.addPMRelation(pmRelation.getPid(),pmRelation.getMid());
    }

    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("表头信息："+headMap);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
    }
}

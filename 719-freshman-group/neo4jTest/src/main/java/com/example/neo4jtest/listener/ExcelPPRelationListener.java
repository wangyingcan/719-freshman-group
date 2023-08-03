package com.example.neo4jtest.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.example.neo4jtest.model.PPRelation;
import com.example.neo4jtest.service.impl.PPRelationServiceImpl;

import java.util.Map;

public class ExcelPPRelationListener extends AnalysisEventListener<PPRelation> {

    private PPRelationServiceImpl ppRelationService;

    public ExcelPPRelationListener(PPRelationServiceImpl PPRelationService){
        this.ppRelationService=PPRelationService;
    }

    @Override
    public void invoke(PPRelation ppRelation, AnalysisContext analysisContext) {
        ppRelationService.addPPRelation(ppRelation.getPpid(),ppRelation.getPid1(),ppRelation.getPid2());
    }

    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("表头信息："+headMap);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
    }
}

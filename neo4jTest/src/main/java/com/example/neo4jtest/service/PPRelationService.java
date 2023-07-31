package com.example.neo4jtest.service;

import com.example.neo4jtest.model.Person;

public interface PPRelationService {
    String addPPRelation(String ppid,Person startNode, Person endNode);

    String addPPRelation(String ppid,String startNodeId, String endNodeId);

    String deletePPRelationByPidMid(String startId,String endId);

    String findPPRelationByPids(String startId,String endId);

    boolean findPPRelationByPids1(String startId,String endId);
}

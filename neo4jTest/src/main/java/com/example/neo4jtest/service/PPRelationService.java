package com.example.neo4jtest.service;

import com.example.neo4jtest.model.Person;

public interface PPRelationService {
    String addPPRelation(Person startNode, Person endNode);

    String addPPRelation(String startNodeId, String endNodeId);

    String deletePPRelationByPidMid(String startId,String endId);
}

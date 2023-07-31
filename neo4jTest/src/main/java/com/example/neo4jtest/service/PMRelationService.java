package com.example.neo4jtest.service;

import com.example.neo4jtest.model.Movie;
import com.example.neo4jtest.model.Person;

public interface PMRelationService {
    String addPMRelation(String pmid,Person startNode, Movie endNode);

    String addPMRelation(String pmid,String startNodeId, String endNodeId);

    String deletePMRelationByPidMid(String startNodeId, String endNodeId);

    String findPMRelationByPidMid(String startId,String endId);

    boolean findPMRelationByPidMid1(String startId,String endId);
}

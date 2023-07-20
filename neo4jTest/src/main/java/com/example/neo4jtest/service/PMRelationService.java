package com.example.neo4jtest.service;

import com.example.neo4jtest.model.Movie;
import com.example.neo4jtest.model.Person;

public interface PMRelationService {
    /**
     * 添加关系
     *
     */
    String addPMRelation(Person startNode, Movie endNode);

    /**
     * 添加关系
     *
     */
    String addPMRelation(String startNodeId, String endNodeId);

    String deletePMRelationByPidMid(String startNodeId, String endNodeId);

}

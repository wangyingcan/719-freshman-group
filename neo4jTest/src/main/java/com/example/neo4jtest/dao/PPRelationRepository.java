package com.example.neo4jtest.dao;

import com.example.neo4jtest.model.PPRelation;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.repository.query.Query;

@EnableNeo4jRepositories
public interface PPRelationRepository extends Neo4jRepository<PPRelation,String> {
    @Query("Match(:Person{pid:$pid1})-[r]->(:Person{pid:$pid2}) delete r")
    void deletePMRelationByPids(String pid1,String pid2);
}

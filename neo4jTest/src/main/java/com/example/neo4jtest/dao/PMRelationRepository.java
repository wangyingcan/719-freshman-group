package com.example.neo4jtest.dao;

import com.example.neo4jtest.model.PMRelation;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.Map;
import java.util.Optional;

@EnableNeo4jRepositories
public interface PMRelationRepository extends Neo4jRepository<PMRelation,Long> {

    @Query("Match(:Person{pid:$pid})-[r]->(:Movie{mid:$mid}) delete r")
    void deletePMRelationByPidMid(String pid,String mid);

    @Query("MATCH(:Person{pid:$pid})-[r]->(:Movie{mid:$mid}) return r.pmid")
    Optional<String>findPMRelationByPidMid(String pid, String mid);
}

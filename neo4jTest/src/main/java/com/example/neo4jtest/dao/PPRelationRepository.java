package com.example.neo4jtest.dao;

import com.example.neo4jtest.model.PPRelation;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.Optional;

@EnableNeo4jRepositories
public interface PPRelationRepository extends Neo4jRepository<PPRelation,Long> {
    @Query("Match(:Person{pid:$pid1})-[r]->(:Person{pid:$pid2}) delete r")
    void deletePMRelationByPids(String pid1,String pid2);

    @Query("MATCH(:Person{pid:$pid1})-[r]->(:Person{pid:$pid2}) return r.ppid")
    Optional<String> findPPRelationByPids(String pid1, String pid2);
}

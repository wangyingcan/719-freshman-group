package com.example.neo4jtest.dao;


import com.example.neo4jtest.model.Person;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.Optional;

@EnableNeo4jRepositories
public interface PersonRepository extends Neo4jRepository<Person,String> {
    @Query("Match(n:Person{pid:$pid}) return n")    //cypher语句：查询pid为$pid的Person类节点
    Optional<Person> findPersonByPid(String pid);

    @Query("Match(n:Person{pid:$pid}) delete n")
    void deletePersonByPid(String pid);
}

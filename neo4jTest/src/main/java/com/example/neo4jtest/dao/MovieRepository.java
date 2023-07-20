package com.example.neo4jtest.dao;

import com.example.neo4jtest.model.Movie;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.Optional;

@EnableNeo4jRepositories
public interface MovieRepository extends Neo4jRepository<Movie,String> {
    @Query("Match(n:Movie{mid:$mid}) return n")    //cypher语句：查询pid为$pid的Person类节点
    Optional<Movie> findMovieByMid(String mid);

    @Query("Match(n:Movie{mid:$mid}) delete n")
    void deleteMovieByMid(String mid);

}

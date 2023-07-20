package com.example.neo4jtest.service.impl;

import com.example.neo4jtest.dao.MovieRepository;
import com.example.neo4jtest.dao.PMRelationRepository;
import com.example.neo4jtest.dao.PersonRepository;
import com.example.neo4jtest.model.Movie;
import com.example.neo4jtest.model.PMRelation;
import com.example.neo4jtest.model.Person;
import com.example.neo4jtest.service.PMRelationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class PMRelationServiceImpl implements PMRelationService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private PMRelationRepository pmRelationRepository;   //此处定义关系删除的方法

    @Override
    public String addPMRelation(Person startNode, Movie endNode) {
        PMRelation pmRelation = new PMRelation();
        pmRelation.setStartNode(startNode);
        endNode.getPmRelations().add(pmRelation);
        movieRepository.save(endNode);
        return "yes";
    }

    @Override
    public String addPMRelation(String startNodeId, String endNodeId) {
        Optional<Person> byId = personRepository.findPersonByPid(startNodeId);    //起始节点是Person
        Optional<Movie> byId1 = movieRepository.findMovieByMid(endNodeId);     //结束节点的Movie
        if (byId.isPresent() && byId1.isPresent()) {   //判断是否存在两节点
            return addPMRelation(byId.get(), byId1.get());
        }
        //这里代表节点不存在，存储失败(不报错、不提示)
        return "no";
    }

    @Override
    public String deletePMRelationByPidMid(String startNodeId, String endNodeId){
        Optional<Person> byId = personRepository.findPersonByPid(startNodeId);    //起始节点是Person
        Optional<Movie> byId1 = movieRepository.findMovieByMid(endNodeId);     //结束节点的Movie
        if (byId.isPresent() && byId1.isPresent()) {   //判断是否存在两节点
            pmRelationRepository.deletePMRelationByPidMid(startNodeId,endNodeId);
        }
        return "delete fail";
    }
}

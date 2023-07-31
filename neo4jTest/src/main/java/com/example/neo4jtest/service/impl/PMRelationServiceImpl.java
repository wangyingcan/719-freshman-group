package com.example.neo4jtest.service.impl;
/**
 * 存在的不足：没有用代码逻辑检测并保证pmid的唯一性
 */

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

import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class PMRelationServiceImpl implements PMRelationService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private PMRelationRepository pmRelationRepository;

    @Override
    public String addPMRelation(String pmid,Person startNode, Movie endNode) {
        PMRelation pmRelation = new PMRelation();
        pmRelation.setStartNode(startNode);
        pmRelation.setPid(startNode.getPid());
        pmRelation.setMid(endNode.getMid());
        pmRelation.setPmid(pmid);
        endNode.getPmRelations().add(pmRelation);
        movieRepository.save(endNode);
        return "yes";
    }

    @Override
    public String addPMRelation(String pmid,String startNodeId, String endNodeId) {
        Optional<Person> byId = personRepository.findPersonByPid(startNodeId);
        Optional<Movie> byId1 = movieRepository.findMovieByMid(endNodeId);
        //防止关系重复添加，需要进行查找关系
        boolean isExist=findPMRelationByPidMid1(startNodeId,endNodeId);
        if (byId.isPresent() && byId1.isPresent()&& !isExist) {
            return addPMRelation(pmid,byId.get(), byId1.get());
        }
        return "no";
    }

    @Override
    public String deletePMRelationByPidMid(String startNodeId, String endNodeId) {
        Optional<Person> byId = personRepository.findPersonByPid(startNodeId);
        Optional<Movie> byId1 = movieRepository.findMovieByMid(endNodeId);
        if (byId.isPresent() && byId1.isPresent()) {
            pmRelationRepository.deletePMRelationByPidMid(startNodeId, endNodeId);
        }
        return "delete fail";
    }

    @Override
    public String findPMRelationByPidMid(String startId, String endId) {
        Optional<String> pmRelation = pmRelationRepository.findPMRelationByPidMid(startId, endId);
        if(pmRelation.isPresent()) return pmRelation.get();
        return "查找失败";
    }

    @Override
    public boolean findPMRelationByPidMid1(String startId, String endId) {
        Optional<String> pmRelation = pmRelationRepository.findPMRelationByPidMid(startId, endId);
        if(pmRelation.isPresent()) return true;
        return false;
    }
}

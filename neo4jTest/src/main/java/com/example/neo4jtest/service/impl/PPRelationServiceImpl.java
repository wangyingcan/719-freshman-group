package com.example.neo4jtest.service.impl;

import com.example.neo4jtest.dao.PPRelationRepository;
import com.example.neo4jtest.dao.PersonRepository;
import com.example.neo4jtest.model.PPRelation;
import com.example.neo4jtest.model.Person;
import com.example.neo4jtest.service.PPRelationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class PPRelationServiceImpl implements PPRelationService {
    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PPRelationRepository ppRelationRepository;
    @Override
    public String addPPRelation(Person startNode, Person endNode) {
        PPRelation ppRelation = new PPRelation();
        ppRelation.setStartNode(startNode);
        endNode.getPpRelations().add(ppRelation);   //将这对关系加到箭头节点的ppRelations属性的队列中
        personRepository.save(endNode);
        return "yes";
    }

    @Override
    public String addPPRelation(String startNodeId, String endNodeId) {
        Optional<Person> byId = personRepository.findPersonByPid(startNodeId);
        Optional<Person> byId1 = personRepository.findPersonByPid(endNodeId);
        if (byId.isPresent() && byId1.isPresent()) {
            return addPPRelation(byId.get(), byId1.get());
        }
        return "no";
    }

    @Override
    public String deletePPRelationByPidMid(String startId,String endId){
        Optional<Person> byId = personRepository.findPersonByPid(startId);    //起始节点是Person
        Optional<Person> byId1 = personRepository.findPersonByPid(endId);     //结束节点的Movie
        if (byId.isPresent() && byId1.isPresent()) {   //判断是否存在两节点
            ppRelationRepository.deletePMRelationByPids(startId,endId);
        }
        return "delete fail";
    }

}

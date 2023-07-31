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
    public String addPPRelation(String ppid,Person startNode, Person endNode) {
        PPRelation ppRelation = new PPRelation();
        ppRelation.setStartNode(startNode);
        ppRelation.setPid1(startNode.getPid());
        ppRelation.setPid2(endNode.getPid());
        ppRelation.setPpid(ppid);
        endNode.getPpRelations().add(ppRelation);
        personRepository.save(endNode);
        return "yes";
    }

    @Override
    public String addPPRelation(String ppid,String startNodeId, String endNodeId) {
        Optional<Person> byId = personRepository.findPersonByPid(startNodeId);
        Optional<Person> byId1 = personRepository.findPersonByPid(endNodeId);
        boolean isExist=findPPRelationByPids1(startNodeId,endNodeId);
        if (byId.isPresent() && byId1.isPresent() && !isExist) {
            return addPPRelation(ppid,byId.get(), byId1.get());
        }
        return "no";
    }

    @Override
    public String deletePPRelationByPidMid(String startId,String endId){
        Optional<Person> byId = personRepository.findPersonByPid(startId);
        Optional<Person> byId1 = personRepository.findPersonByPid(endId);
        if (byId.isPresent() && byId1.isPresent()) {
            ppRelationRepository.deletePMRelationByPids(startId,endId);
        }
        return "delete fail";
    }

    @Override
    public String findPPRelationByPids(String startId, String endId) {
        Optional<String> ppRelation = ppRelationRepository.findPPRelationByPids(startId, endId);
        if(ppRelation.isPresent()) return ppRelation.get();
        return "查找失败";
    }

    @Override
    public boolean findPPRelationByPids1(String startId, String endId) {
        Optional<String> ppRelation = ppRelationRepository.findPPRelationByPids(startId, endId);
        if(ppRelation.isPresent()) return true;
        return false;
    }
}

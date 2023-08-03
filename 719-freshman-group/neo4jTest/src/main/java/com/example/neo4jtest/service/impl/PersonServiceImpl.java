package com.example.neo4jtest.service.impl;


import com.example.neo4jtest.dao.PersonRepository;
import com.example.neo4jtest.model.Movie;
import com.example.neo4jtest.model.Person;
import com.example.neo4jtest.service.PersonService;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PersonServiceImpl implements PersonService {
    @Autowired
    private PersonRepository personRepository;

    @Override
    public void savePerson(Person person) {
        if(StringUtils.isEmpty(person.getPid())){
            person.setPid("1");
        }
        personRepository.save(person);
    }

    @Override
    public void deletePerson(String pid) {
        personRepository.deletePersonByPid(pid);
    }


    public String searchPersonByPid(String pid) {
        Optional<Person> person = personRepository.findPersonByPid(pid);
        if (person.isPresent()) {
            return person.get().getName();
        }
        return "查找失败";
    }

}

package com.example.neo4jtest.service.impl;


import com.example.neo4jtest.dao.PersonRepository;
import com.example.neo4jtest.model.Person;
import com.example.neo4jtest.service.PersonService;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@Slf4j
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public List<Person> getAll() {
        Iterable<Person> all = personRepository.findAll();
        List<Person> persons = Lists.newArrayList(all);
        return persons;
    }

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

/*    @Override
    public void deleteById(String Pid) {
        personRepository.deleteById(Pid);
    }

    @Override
    public Person findById(String Pid) {
        Optional<Person> byId = personRepository.findById(Pid);
        if (byId.isPresent()) {
            return byId.get();
        }
        return new Person();
    }*/

}

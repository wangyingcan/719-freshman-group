package com.example.neo4jtest.service;

import com.example.neo4jtest.model.Person;

import java.util.List;

public interface PersonService {

    void savePerson(Person person);

    void deletePerson(String pid);

    String searchPersonByPid(String pid);
}

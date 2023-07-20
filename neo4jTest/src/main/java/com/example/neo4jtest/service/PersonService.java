package com.example.neo4jtest.service;

import com.example.neo4jtest.model.Person;

import java.util.List;

public interface PersonService {
    List<Person> getAll();
    /**
     * 修改
     */
    void savePerson(Person person);

    void deletePerson(String pid);

    //void deleteById(String Pid);

    //Person findById(String Pid);
}

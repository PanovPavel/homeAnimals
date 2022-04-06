package org.works.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.works.Person;
import org.works.dao.Dao;

import java.util.List;

@RestController
public class PersonController {
    @Autowired
    Dao<Person> daoPerson;

    @GetMapping("/api/person")
    public List<Person> getAllPerson(){
        return daoPerson.getAll();
    }
    @GetMapping("/api/person/{id}")
    public Person getPerson(@PathVariable int id){
        return daoPerson.get(id);
    }

    @PostMapping("/api/person")
    public void addPerson(@RequestBody Person person){
        daoPerson.saveOrUpdate(person);
    }

    @DeleteMapping("/api/person/{id}")
    public void deletePerson(@PathVariable int id){
        daoPerson.delete(id);
    }

}

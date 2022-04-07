package org.works.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.works.Person;
import org.works.Pet;
import org.works.dao.Dao;
import org.works.dao.PersonDao;
import org.works.exceptions.ConstraintUniquenessQtyTypePetException;
import org.works.exceptions.JsonMessage;

import java.util.Date;
import java.util.List;

@RestController
public class PersonController {
    @Autowired
    PersonDao daoPerson;

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

    @GetMapping("/api/person/{idPerson}/{idPet}")
    public void bindPetPerson(@PathVariable int idPerson, @PathVariable int idPet){
        daoPerson.bindPetInPerson(idPerson, idPet);
    }


    @ExceptionHandler
    public ResponseEntity<JsonMessage> handlerException(ConstraintUniquenessQtyTypePetException exception){
        JsonMessage jsonMessage = new JsonMessage();
        jsonMessage.setInfo(exception.getMessage());
        return new ResponseEntity<>(jsonMessage, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler
    public ResponseEntity<JsonMessage> handlerException(Exception exception){
        JsonMessage jsonMessage = new JsonMessage();
        jsonMessage.setInfo(exception.getMessage());
        return new ResponseEntity<>(jsonMessage, HttpStatus.BAD_REQUEST);
    }

}

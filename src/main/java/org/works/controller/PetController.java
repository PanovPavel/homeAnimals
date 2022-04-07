package org.works.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.works.Pet;
import org.works.TypePet;
import org.works.dao.Dao;
import org.works.dao.PetDao;
import org.works.dao.PetDaoImpl;
import org.works.exceptions.ConstraintUniquenessQtyTypePetException;
import org.works.exceptions.JsonMessage;
import org.works.service.PetService;

import java.util.List;

@RestController
public class PetController {
    @Autowired
    private PetService petDao;

    @GetMapping("/api/pet")
    public List<Pet> getAllTypePet(){
        return petDao.getAll();
    }
    @GetMapping("/api/pet/{id}")
    public Pet getPet(@PathVariable int id){
        return petDao.get(id);
    }
    @PostMapping("/api/pet")
    public void savePet(@RequestBody Pet pet){
        petDao.saveOrUpdate(pet);
    }
    @DeleteMapping("/api/pet/{id}")
    public void deletePet(@PathVariable int id){
        petDao.delete(id);
    }
    @GetMapping("/api/pet/rr")
    public List<Pet> getTimeIntervalAddPet(@RequestParam("id") int id, @RequestParam("start") String start, @RequestParam("end") String end){
        return petDao.getTimeIntervalAddPet(id, start, end);
    }
    @ExceptionHandler
    public ResponseEntity<JsonMessage> handlerException(Exception exception){
        JsonMessage jsonMessage = new JsonMessage();
        jsonMessage.setInfo(exception.getMessage());
        return new ResponseEntity<>(jsonMessage, HttpStatus.BAD_REQUEST);
    }

}

package org.works.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.works.Pet;
import org.works.TypePet;
import org.works.dao.Dao;
import org.works.dao.PetDaoImpl;

import java.util.List;

@RestController
public class PetController {
    @Autowired
    private Dao<Pet> petDao;
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
}

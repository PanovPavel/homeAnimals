package org.works.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.works.TypePet;
import org.works.dao.Dao;
import org.works.service.Service;

import java.lang.reflect.Type;
import java.util.List;

@RestController
public class TypePetController {
    @Autowired
    private Service<TypePet> typePetDao;

    @GetMapping("/api/type_pet/{id}")
    public TypePet getTypePet(@PathVariable int id){
        return typePetDao.get(id);
    }
    @GetMapping("/api/type_pet")
    public List<TypePet> getAllTypePet(){
        return typePetDao.getAll();
    }
    @DeleteMapping("/api/type_pet/{id}")
    public void deleteTypePet(@PathVariable int id){
        typePetDao.delete(id);
    }
    @PostMapping("/api/type_pet")
    public void addTypePet(@RequestBody TypePet typePet){
        typePetDao.saveOrUpdate(typePet);
    }
}

package org.works.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.works.TypePet;
import org.works.dao.Dao;

import java.util.List;

@Component
public class TypeService implements Service<TypePet>{
    @Autowired
    Dao<TypePet> typeService;

    @Override
    public List<TypePet> getAll() {
        return typeService.getAll();
    }

    @Override
    public TypePet get(int id) {
        return typeService.get(id);
    }

    @Override
    public void saveOrUpdate(TypePet typePet) {
        typeService.saveOrUpdate(typePet);
    }

    @Override
    public void delete(int id) {
        typeService.delete(id);
    }
}

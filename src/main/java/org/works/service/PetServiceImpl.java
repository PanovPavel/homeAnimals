package org.works.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.works.Pet;
import org.works.dao.PetDao;
import org.works.dao.PetDaoImpl;

import java.util.List;
@Component
public class PetServiceImpl implements Service<Pet>, PetService{
    @Autowired
    PetDao petDao;

    @Override
    public List<Pet> getTimeIntervalAddPet(int idPerson, String dateStart, String dataEnd) {
        return petDao.getTimeIntervalAddPet(idPerson, dateStart, dataEnd);
    }

    @Override
    public List<Pet> getAll() {
        return petDao.getAll();
    }

    @Override
    public Pet get(int id) {
        return petDao.get(id);
    }

    @Override
    public void saveOrUpdate(Pet pet) {
        petDao.saveOrUpdate(pet);
    }

    @Override
    public void delete(int id) {
        petDao.delete(id);
    }
}

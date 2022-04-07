package org.works.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.works.Person;
import org.works.dao.PersonDao;
import org.works.dao.PersonDaoImpl;
import org.works.exceptions.ConstraintUniquenessQtyTypePetException;

import java.util.List;
@Component
public class PersonServiceImpl implements Service<Person>, PersonService{
    @Autowired
    PersonDao personDao;

    @Override
    public void bindPetInPerson(int idPerson, int idPet) throws ConstraintUniquenessQtyTypePetException {
        personDao.bindPetInPerson(idPerson, idPet);
    }

    @Override
    public List<Person> getAll() {
        return personDao.getAll();
    }

    @Override
    public Person get(int id) {
        return personDao.get(id);
    }

    @Override
    public void saveOrUpdate(Person person) {
        personDao.saveOrUpdate(person);
    }

    @Override
    public void delete(int id) {
        personDao.delete(id);
    }
}

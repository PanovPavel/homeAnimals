package org.works.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.works.Person;
import org.works.Pet;
import org.works.mapper.PersonExtractor;

import java.util.List;

@Repository
public class PersonDaoImpl implements Dao<Person>{
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    PersonExtractor personMapper;
    @Override
    public List<Person> getAll() {
        String query =
                "SELECT a.*, pet.*, type_pet.id, type_pet.type_pet\n" +
                        "FROM person a\n" +
                        "         LEFT JOIN person_pet\n" +
                        "            ON a.id = person_pet.person_id\n" +
                        "         LEFT JOIN pet\n" +
                        "            ON person_pet.pet_id = pet.id\n" +
                        "         LEFT JOIN type_pet\n" +
                        "            ON pet.type_pet_id = type_pet.id";

        return jdbcTemplate.query(query, personMapper);
    }

    @Override
    public Person get(int id) {
        String query =
                "SELECT a.*, pet.*, type_pet.id, type_pet.type_pet\n" +
                        "FROM person a\n" +
                        "         left JOIN  person_pet\n" +
                        "                    ON a.id = person_pet.person_id\n" +
                        "         left JOIN pet\n" +
                        "            ON person_pet.pet_id = pet.id\n" +
                        "         left JOIN type_pet\n" +
                        "                    ON pet.type_pet_id = type_pet.id\n" +
                        "        WHERE a.id = ?;";
        return jdbcTemplate.query(query,personMapper, new Object[]{id}).get(0);
    }

    @Override
    public void saveOrUpdate(Person person) {
        if(person.getId() == 0 && person.getListPet() == null){
            jdbcTemplate.update("insert person(name, surname, lastname) VALUE (?, ?, ?)",
                    person.getName(), person.getSurname(), person.getLastname());
        }
        else{
            jdbcTemplate.update("update person set name = ?, surname = ?, lastname = ? WHERE id = ?",
                    person.getName(), person.getSurname(), person.getLastname(), person.getId());
        }
    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update("delete from person where id = ?", id);
    }


}

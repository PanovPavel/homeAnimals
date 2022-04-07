package org.works.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.works.Person;
import org.works.Pet;
import org.works.exceptions.ConstraintUniquenessQtyTypePetException;
import org.works.mapper.PersonExtractor;
import org.works.mapper.PetExtractor;

import javax.management.Query;
import javax.swing.tree.RowMapper;
import java.util.Date;
import java.util.List;

@Repository
public class PersonDaoImpl implements PersonDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private PersonExtractor personMapper;
    @Override
    public List<Person> getAll() {
        String query =
                "SELECT a.*, pet.*, type_pet.id, type_pet.type_pet, person_pet.data\n" +
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
                "SELECT a.*, pet.*, type_pet.id, type_pet.type_pet,  person_pet.data\n" +
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

    @Override
    public void bindPetInPerson(int idPerson, int idPet) {
        int countTypePetInOnePerson;
        Person person = new Person();
        String query =
                //сложить нормально
                "SELECT count(*) AS 'qua'\n" +
                        "from person_pet\n" +
                        "    INNER JOIN pet\n" +
                        "        ON person_pet.pet_id = pet.id\n" +
                        "    INNER JOIN type_pet tp\n" +
                        "        on pet.type_pet_id = tp.id\n" +
                        "GROUP BY person_id, type_pet_id\n" +
                        "HAVING person_id = " + idPerson + " and type_pet_id = " + idPet + "";
        try {
            countTypePetInOnePerson = jdbcTemplate.queryForObject(query, Integer.class);
        } catch (EmptyResultDataAccessException ex) {countTypePetInOnePerson = 0;}

        if (countTypePetInOnePerson >= person.getMaxCountTypePet()) {
            throw new ConstraintUniquenessQtyTypePetException("Limit quantity pets per one person");
        }else jdbcTemplate.update("insert person_pet(person_id, pet_id, data) VALUE(?, ?, now())", idPerson, idPet);
    }


}

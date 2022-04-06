package org.works.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.works.Person;
import org.works.mapper.PersonMapper;

import java.util.List;

@Repository
public class PersonDaoImpl implements Dao<Person>{
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    PersonMapper personMapper;
    @Override
    public List<Person> getAll() {
        return jdbcTemplate.query("Select * from person", personMapper);
    }

    @Override
    public Person get(int id) {
        return jdbcTemplate.queryForObject("Select * from person Where id = ?",
                new PersonMapper(), new Object[]{id});
    }

    @Override
    public void saveOrUpdate(Person person) {
        if(person.getId() == 0){
            jdbcTemplate.update("insert person(name, surname, lastname) VALUE (?, ?, ?)",
                    person.getName(), person.getSurname(), person.getLastname());
        }else{
            jdbcTemplate.update("update person set name = ?, surname = ?, lastname = ? WHERE id = ?",
                    person.getName(), person.getSurname(), person.getLastname(), person.getId());
        }
    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update("delete from person where id = ?", id);
    }
}

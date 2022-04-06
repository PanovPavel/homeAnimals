package org.works.mapper;


import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.works.Person;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class PersonMapper implements RowMapper<Person> {
    @Override
    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
        Person person = new Person();
        person.setId(rs.getInt("id"));
        person.setName(rs.getString("name"));
        person.setSurname(rs.getString("surname"));
        person.setLastname(rs.getString("lastname"));
        return person;
    }
}

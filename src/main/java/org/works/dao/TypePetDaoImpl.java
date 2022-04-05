package org.works.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.works.TypePet;
import org.works.mapper.TypePetMapper;

import java.util.List;

@Repository
public class TypePetDaoImpl implements Dao<TypePet>{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<TypePet> getAll() {
        return jdbcTemplate.query("Select * From type_pet", new TypePetMapper());
    }

    @Override
    public TypePet get(int id) {
        String query = "Select * From type_pet Where id=?";
        //Добавить IncorrectResultSetColumnCountException
        return jdbcTemplate.queryForObject(query,  new TypePetMapper(), new Object[]{id});
    }

    @Override
    public void saveOrUpdate(TypePet typePet) {
        if (typePet.getId() == 0){
            jdbcTemplate.update("insert type_pet(type_pet) values (?)", typePet.getType());
        }
        else jdbcTemplate.update("update type_pet set type_pet = ? where id = ?", typePet.getType(), typePet.getId());
    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update("DELETE from type_pet WHERE id = ?", id);
    }
}

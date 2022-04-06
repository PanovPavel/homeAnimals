package org.works.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.works.Pet;
import org.works.TypePet;
import org.works.dao.Dao;
import org.works.dao.TypePetDaoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
@Component
public class PetMapper implements RowMapper<Pet>  {
    @Autowired
    private Dao<TypePet> typeDao;
    public PetMapper() {

    }
    @Override
    public Pet mapRow(ResultSet rs, int rowNum) throws SQLException {
        Pet pet = new Pet();
        pet.setId(rs.getInt("id"));
        pet.setColor(rs.getString("color"));
        pet.setWeight(rs.getDouble("weight"));
        pet.setNickname(rs.getString("nickname"));
        pet.setTypePet(typeDao.get(rs.getInt("type_pet_id")));
        return pet;
    }
}

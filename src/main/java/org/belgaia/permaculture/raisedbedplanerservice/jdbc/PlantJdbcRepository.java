package org.belgaia.permaculture.raisedbedplanerservice.jdbc;

import org.belgaia.permaculture.raisedbedplanerservice.AlreadyExistingElementException;
import org.belgaia.permaculture.raisedbedplanerservice.beans.Plant;
import org.belgaia.permaculture.raisedbedplanerservice.beans.PlantGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class PlantJdbcRepository implements PlantRepository {

    private final Logger logger = LoggerFactory.getLogger(PlantJdbcRepository.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Plant> getAllPlants() {
        String sql = "SELECT * FROM plants";

        try {
            return jdbcTemplate.query(
                    sql,
                    new BeanPropertyRowMapper<>(Plant.class)
            );
        } catch(EmptyResultDataAccessException e) {
            return null;
        }
    }

    public Plant getPlantByName(String name) {
        String sql = "SELECT * FROM plants WHERE name=?";

        try {
            return jdbcTemplate.queryForObject(
                    sql,
                    new BeanPropertyRowMapper<>(Plant.class),
                    name
            );
        } catch(EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Plant createPlant(Plant plant) throws AlreadyExistingElementException {

        Plant existingPlant = getPlantByName(plant.getName());
        if(existingPlant!=null) {
            throw new AlreadyExistingElementException("Plant with name " + plant.getName() + " already exists.");
        }

        String sql = "INSERT INTO plants (name) VALUES (?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        int savedElement = jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
                    ps.setString(1, plant.getName());
                    return ps;
                }, keyHolder);

        Integer plantId = null;
        if(savedElement > 0 && keyHolder.getKey() != null) {
            plantId = keyHolder.getKey().intValue();
            logger.info("Successfully saved and initialized plant with ID {}", plantId);
        }
        return getPlantById(plantId);
    }

    private Plant getPlantById(Integer plantId) {
        String sql = "SELECT * FROM plants WHERE id=?";

        try {
            return jdbcTemplate.queryForObject(
                    sql,
                    new BeanPropertyRowMapper<>(Plant.class),
                    plantId
            );
        } catch(EmptyResultDataAccessException e) {
            return null;
        }
    }
}

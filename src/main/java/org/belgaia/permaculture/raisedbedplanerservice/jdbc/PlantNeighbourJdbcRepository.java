package org.belgaia.permaculture.raisedbedplanerservice.jdbc;

import org.belgaia.permaculture.raisedbedplanerservice.AlreadyExistingElementException;
import org.belgaia.permaculture.raisedbedplanerservice.beans.PlantGroup;
import org.belgaia.permaculture.raisedbedplanerservice.beans.PlantNeighbour;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PlantNeighbourJdbcRepository implements PlantNeighbourRepository {

    private final Logger logger = LoggerFactory.getLogger(PlantNeighbourJdbcRepository.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<PlantNeighbour> getAllPlantNeighbours() {
        String sql = "SELECT * FROM plant_neighbours";

        try {
            return jdbcTemplate.query(
                    sql,
                    new BeanPropertyRowMapper<>(PlantNeighbour.class)
            );
        } catch(EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<PlantNeighbour> getNeighboursOfPlant(String plantName, Boolean goodNeighbour, Boolean badNeighbour) {

        String sql = "SELECT * FROM plant_neighbours WHERE plant_one_name=? AND good_neighbour=? AND bad_neighbour=?";

        try {
            return jdbcTemplate.query(
                    sql,
                    new BeanPropertyRowMapper<>(PlantNeighbour.class),
                    plantName,
                    goodNeighbour,
                    badNeighbour
            );
        } catch(EmptyResultDataAccessException e) {
            return null;
        }
    }

    private PlantNeighbour getNeighbourRelationForPlants(String plantOneName, String plantTwoName) {
        String sql = "SELECT * FROM plant_neighbours WHERE plant_one_name=? AND plant_two_name=?";

        try {
            return jdbcTemplate.queryForObject(
                    sql,
                    new BeanPropertyRowMapper<>(PlantNeighbour.class),
                    plantOneName,
                    plantTwoName
            );
        } catch(EmptyResultDataAccessException e) {
            return null;
        }
    }

    public PlantNeighbour createPlantNeighbour(PlantNeighbour plantNeighbour) throws AlreadyExistingElementException {

        PlantNeighbour existingNeighbour = null;

        if (plantNeighbour.getGroupId() != null) {
            existingNeighbour = getPlantNeighboursByGroupId(plantNeighbour.getPlantOneId(), plantNeighbour.getGroupId());
            if(existingNeighbour!=null) {
                throw new AlreadyExistingElementException("Plant neighbour " + plantNeighbour.getPlantOneName() + " and " + plantNeighbour.getPlantTwoName() + " already exists.");
            }
        }

        existingNeighbour = getNeighbourRelationForPlants(plantNeighbour.getPlantOneName(), plantNeighbour.getPlantTwoName());
        if(existingNeighbour!=null) {
            throw new AlreadyExistingElementException("Plant neighbour " + plantNeighbour.getPlantOneName() + " and " + plantNeighbour.getPlantTwoName() + " already exists.");
        }

        String sql = "INSERT INTO plant_neighbours (plant_one_id, plant_one_name, plant_two_id, plant_two_name, group_id, group_name, good_neighbour, bad_neighbour, comment) VALUES (?,?,?,?,?,?,?,?,?)";

        jdbcTemplate.update(sql,
                plantNeighbour.getPlantOneId(),
                plantNeighbour.getPlantOneName(),
                plantNeighbour.getPlantTwoId(),
                plantNeighbour.getPlantTwoName(),
                plantNeighbour.getGroupId(),
                plantNeighbour.getGroupName(),
                plantNeighbour.isGoodNeighbour(),
                plantNeighbour.isBadNeighbour(),
                plantNeighbour.getComment()
        );
        return plantNeighbour;
    }

    public PlantNeighbour getPlantNeighboursByGroupId(Integer plantOneId, Integer groupId) {
        String sql = "SELECT * FROM plant_neighbours WHERE plant_one_id=? AND group_id=?";

        try {
            return jdbcTemplate.queryForObject(
                    sql,
                    new BeanPropertyRowMapper<>(PlantNeighbour.class),
                    plantOneId,
                    groupId
            );
        } catch(EmptyResultDataAccessException e) {
            return null;
        }
    }
}

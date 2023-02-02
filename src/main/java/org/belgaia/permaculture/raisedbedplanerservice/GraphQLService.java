package org.belgaia.permaculture.raisedbedplanerservice;

import org.belgaia.permaculture.raisedbedplanerservice.beans.Plant;
import org.belgaia.permaculture.raisedbedplanerservice.beans.Plant2Group;
import org.belgaia.permaculture.raisedbedplanerservice.beans.PlantGroup;
import org.belgaia.permaculture.raisedbedplanerservice.beans.PlantNeighbour;
import org.belgaia.permaculture.raisedbedplanerservice.dataimport.DataImporter;
import org.belgaia.permaculture.raisedbedplanerservice.jdbc.PlantGroupJdbcRepository;
import org.belgaia.permaculture.raisedbedplanerservice.jdbc.PlantNeighbourRepository;
import org.belgaia.permaculture.raisedbedplanerservice.jdbc.PlantToGroupRelRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GraphQLService {

    private final Logger logger = LoggerFactory.getLogger(GraphQLService.class);

    /* Deprecated
    @Autowired
    RaisedBedESRepository raisedBedESRepository;

    @Autowired
    PlantingESRepository plantingESRepository;

    @Autowired
    RaisedBedQueryBuilder elasticSearchQueryBuilder;
    */

    @Autowired
    DataImporter dataImporter;

    @Autowired
    PlantGroupJdbcRepository plantGroupJdbcRepository;

    @Autowired
    PlantNeighbourRepository plantNeighbourRepository;

    @Autowired
    PlantToGroupRelRepository plantToGroupRelRepository;

    public String importDataFromCsv() {
        dataImporter.importPlants();
        logger.info("Successfully imported plants to database.");
        dataImporter.importPlantGroups();
        logger.info("Successfully imported plant groups to database.");
        dataImporter.importPlantToGroupRelations();
        logger.info("Successfully imported plant to group relations to database.");
        dataImporter.importPlantNeighbours();
        logger.info("Successfully imported plant neighbours to database");
        return "Successfully imported data from CSV file into the database.";
    }

    public List<Plant> getNeighbourPlants(String plantName, Boolean goodNeighbour, Boolean badNeighbour) {
        logger.info("Search for plants that are good or bad neighbours of the given plant {}", plantName);

        validateNeighbourSearch(goodNeighbour, badNeighbour);

        List<PlantNeighbour> neighbours = plantNeighbourRepository.getNeighboursOfPlant(plantName, goodNeighbour, badNeighbour);

        List<Plant> neighbourPlants = new ArrayList<Plant>();
        for(PlantNeighbour neighbour : neighbours) {
            Plant plant = new Plant();
            plant.setId(neighbour.getPlantTwoId());
            plant.setName(neighbour.getPlantTwoName());
            neighbourPlants.add(plant);
        }
        return neighbourPlants;
    }

    public List<Plant> getPlantsByGroup(Integer groupId, String groupName) {

        logger.info("Search for plants by given plant group with ID {} and/or name {}", groupId, groupName);

        // get the ID of the group if just the name is given
        if(groupId == null && (!groupName.isEmpty())) {
            PlantGroup plantGroup = plantGroupJdbcRepository.getPlantGroupByName(groupName);
            groupId = plantGroup.getId();
        }

        List<Plant> plants = new ArrayList<>();
        for(Plant2Group plantGroupRelation : plantToGroupRelRepository.getPlantsByGroup(groupId)) {
            Plant plant = new Plant();
            plant.setName(plantGroupRelation.getPlantName());
            plant.setId(plantGroupRelation.getPlantId());
            plants.add(plant);
        }
        return plants;
    }

    private Boolean validateNeighbourSearch(Boolean goodNeighbour, Boolean badNeighbour) throws IllegalArgumentException {
        if(goodNeighbour && !badNeighbour) {
            return true;
        } else if (goodNeighbour && badNeighbour) {
            logger.error("You can't search for a good neighbour that is also a bad neighbour. You have to decide!");
            throw new IllegalArgumentException("You can't search for a good neighbour that is also a bad neighbour. You have to decide!");
        } else if (!goodNeighbour && badNeighbour) {
            return true;
        }
        logger.error("This case is not implemented. Please add it to the validation chain!");
        throw new IllegalArgumentException("This case is not implemented. Please add it to the validation chain!");
    }

    /* Deprecated
    public List<RaisedBed> getAllRaisedBeds() {
        return Lists.newArrayList(raisedBedESRepository.findAll());
    }*/

    /* Deprecated
    public RaisedBed getRaisedBedById(String id) {
        Optional<RaisedBed> foundRaisedBed = raisedBedESRepository.findById(id);
        if(foundRaisedBed.isPresent()) {
            return foundRaisedBed.get();
        }
        return null;
    }*/

    /* Deprecated
    public List<RaisedBed> getRaisedBedsWithPlantingsByYear(Integer year) {
        Map<String, Integer> propertyValues = new HashMap<String, Integer>();
        propertyValues.put("plantings.year", year);

        NestedQueryBuilder nestedQueryBuilder = elasticSearchQueryBuilder.nestedBoolQueryForIntegerValues(propertyValues, "plantings");
        List<RaisedBed> filteredRaisedBeds = Lists.newArrayList(raisedBedESRepository.search(nestedQueryBuilder));

        return filteredRaisedBeds;
    }*/

    /* Deprecated
    public Planting getPlantingByUUID(String plantingId) throws ElementNotFoundException {
        Map<String, String> propertyValues = new HashMap<String, String>();
        propertyValues.put("plantings.id", plantingId);

        NestedQueryBuilder nestedQueryBuilder = elasticSearchQueryBuilder.nestedBoolQueryForStringValues(propertyValues, "plantings");
        List<RaisedBed> filteredRaisedBeds = Lists.newArrayList(raisedBedESRepository.search(nestedQueryBuilder));

        for(RaisedBed raisedBed : filteredRaisedBeds) {
            for(Planting planting : raisedBed.getPlantings()) {
                String foundPlantingId = planting.getId();
                if(foundPlantingId != null && foundPlantingId.equals(plantingId)) {
                    return planting;
                }
            }
        }
        logger.info("Could not find planting with id {}", plantingId);
        throw new ElementNotFoundException("Could not find planting with id '" + plantingId + "'");
    }*/

    /* Deprecated
    public RaisedBed createRaisedBed(String name, Integer dimensionX, Integer dimensionY) throws AlreadyExistingElementException {
        RaisedBed foundRaisedBed = raisedBedESRepository.findByName(name);
        if(foundRaisedBed != null) {
            logger.error("Raised Bed with this name {} already exists.", name);
            throw new AlreadyExistingElementException("Raised Bed with name " + name + " already exists.");
        }

        RaisedBed raisedBed = new RaisedBed(name, dimensionX, dimensionY);
        return raisedBedESRepository.save(raisedBed);
    }*/

    /* Deprecated
    public RaisedBed createPlantingDesign(String raisedBedId, Integer year, String season, Integer fieldCountX, Integer fieldCountY) throws ElementNotFoundException {
        Optional<RaisedBed> existingRaisedBed = raisedBedESRepository.findById(raisedBedId);

        if(!existingRaisedBed.isPresent()) {
            throw new ElementNotFoundException("The Raised Bed with Id " + raisedBedId + " does not exist");
        }

        Planting planting = new Planting();
        planting.setId(String.valueOf(UUID.randomUUID()));
        planting.setYear(year);
        planting.setSeason(season);

        if(fieldCountX != null) {
            planting.setFieldCountX(fieldCountX);
        } else {
            planting.setFieldCountX(existingRaisedBed.get().getDefaultFieldCountX());
        }

        if(fieldCountY != null) {
            planting.setFieldCountY(fieldCountY);
        } else {
            planting.setFieldCountY(existingRaisedBed.get().getDefaultFieldCountY());
        }

        planting.setStatus(PlantingStatus.CREATED.status());

        RaisedBed updatedRaisedBed = existingRaisedBed.get();
        updatedRaisedBed.getPlantings().add(planting);
        raisedBedESRepository.save(updatedRaisedBed);

        return raisedBedESRepository.findById(raisedBedId).get();
    }*/

    /* Deprecated
    public Planting updatePlantingStatus(String plantingId, String status) throws ElementNotFoundException {

        RaisedBed foundRaisedBed = getRaisedBedByPlantingUUID(plantingId);
        for(Planting existingPlanting : foundRaisedBed.getPlantings()) {
            String foundPlantingId = existingPlanting.getId();
            if(foundPlantingId != null && foundPlantingId.equals(plantingId)) {
                existingPlanting.setStatus(status);
                raisedBedESRepository.save(foundRaisedBed);
                return existingPlanting;
            }
        }
        return null;
    }*/
    /* Deprecated
    private RaisedBed getRaisedBedByPlantingUUID(String plantingId) throws ElementNotFoundException {
        Map<String, String> propertyValues = new HashMap<>();
        propertyValues.put("id", plantingId);
        NestedQueryBuilder queryBuilder = elasticSearchQueryBuilder.nestedBoolQueryForStringValues(propertyValues, "plantings");
        List<RaisedBed> foundRaisedBeds = Lists.newArrayList(raisedBedESRepository.search(queryBuilder));

        if(foundRaisedBeds != null && foundRaisedBeds.size() > 0) {
            return foundRaisedBeds.get(0);
        }
        logger.info("\"Raised bed with the planting with UUID {} not found", plantingId);
        throw new ElementNotFoundException("Raised bed with a planting with UUID '" + plantingId + "' not found");
    }*/
}

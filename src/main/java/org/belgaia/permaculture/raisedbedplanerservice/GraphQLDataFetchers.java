package org.belgaia.permaculture.raisedbedplanerservice;

import graphql.schema.DataFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GraphQLDataFetchers {

    @Autowired
    GraphQLService service;

    public DataFetcher getNeighbourPlants() {
        return dataFetchingEnvironment -> {
            String plantName = dataFetchingEnvironment.getArgument("plantName");
            Boolean goodNeighbour = dataFetchingEnvironment.getArgument("goodNeighbour");
            Boolean badNeighbour = dataFetchingEnvironment.getArgument("badNeighbour");
            return service.getNeighbourPlants(plantName, goodNeighbour, badNeighbour);
        };
    }

    public DataFetcher getPlantsByGroup() {
        return dataFetchingEnvironment -> {
          String groupId = dataFetchingEnvironment.getArgument("groupId");
          String groupName = dataFetchingEnvironment.getArgument("groupName");

          Integer castedGroupId = null;
          if(groupId != null) {
              castedGroupId = Integer.valueOf(groupId);
          }
          return service.getPlantsByGroup(castedGroupId, groupName);
        };
    }

    public DataFetcher importDataFromCsv() {
        return dataFetchingEnvironment -> {
            return service.importDataFromCsv();
        };
    }

    /* Deprecated
    public DataFetcher getAllRaisedBedsFetcher() {
        return dataFetchingEnvironment -> service.getAllRaisedBeds();
    }*/

    /* Deprecated
    public DataFetcher getRaisedBedByIdFetcher() {
        return dataFetchingEnvironment -> {
            String id = dataFetchingEnvironment.getArgument("id");
            return service.getRaisedBedById(id);
        };
    }*/

    /* Deprecated
    public DataFetcher createRaisedBedFetcher() {
        return dataFetchingEnvironment -> {
            String name = dataFetchingEnvironment.getArgument("name");
            Integer dimensionX = dataFetchingEnvironment.getArgument("dimensionX");
            Integer dimensionY = dataFetchingEnvironment.getArgument("dimensionY");

            return service.createRaisedBed(name, dimensionX, dimensionY);
        };
    }*/

    /* Deprecated
    public DataFetcher createPlantingDesignFetcher() {
        return dataFetchingEnvironment -> {
            String raisedBedId = dataFetchingEnvironment.getArgument("raisedBedId");
            Integer year = dataFetchingEnvironment.getArgument("year");
            String season = dataFetchingEnvironment.getArgument("season");
            Integer fieldCountX = dataFetchingEnvironment.getArgument("fieldCountX");
            Integer fieldCountY = dataFetchingEnvironment.getArgument("fieldCountY");

            return service.createPlantingDesign(raisedBedId, year, season, fieldCountX, fieldCountY);
        };
    } */
    /* Deprecated
    public DataFetcher getRaisedBedsWithPlantingsByYearFetcher() {
        return dataFetchingEnvironment -> {
            Integer year = dataFetchingEnvironment.getArgument("year");
            return service.getRaisedBedsWithPlantingsByYear(year);
        };
    } */

    /* Deprecated
    public DataFetcher getPlantingByUUIDFetcher() {
        return dataFetchingEnvironment -> {
            String plantingId = dataFetchingEnvironment.getArgument("plantingId");
            return service.getPlantingByUUID(plantingId);
        };
    } */

    /* Deprecated
    public DataFetcher updatePlantingStatus() {
        return dataFetchingEnvironment -> {
            String plantingId = dataFetchingEnvironment.getArgument("plantingId");
            String status = dataFetchingEnvironment.getArgument("status");
            return service.updatePlantingStatus(plantingId, status);
        };
    } */

}

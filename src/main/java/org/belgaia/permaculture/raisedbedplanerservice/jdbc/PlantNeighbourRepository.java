package org.belgaia.permaculture.raisedbedplanerservice.jdbc;

import org.belgaia.permaculture.raisedbedplanerservice.AlreadyExistingElementException;
import org.belgaia.permaculture.raisedbedplanerservice.beans.PlantNeighbour;

import java.util.List;

public interface PlantNeighbourRepository {
    List<PlantNeighbour> getAllPlantNeighbours();
    List<PlantNeighbour> getNeighboursOfPlant(String plantName, Boolean goodNeighbour, Boolean badNeighbour);
    PlantNeighbour createPlantNeighbour(PlantNeighbour plantNeighbour) throws AlreadyExistingElementException;
}

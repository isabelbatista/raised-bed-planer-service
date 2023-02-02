package org.belgaia.permaculture.raisedbedplanerservice.dataimport;

import com.opencsv.bean.CsvBindByPosition;

public class CsvPlantToGroupRelDataSchema {
    @CsvBindByPosition(position = 0)
    private String plantGroupId;

    @CsvBindByPosition(position = 1)
    private String plantGroupName;

    @CsvBindByPosition(position = 2)
    private String plantId;

    @CsvBindByPosition(position = 3)
    private String plantName;

    public String getPlantGroupId() {
        return plantGroupId;
    }

    public void setPlantGroupId(String plantGroupId) {
        this.plantGroupId = plantGroupId;
    }

    public String getPlantGroupName() {
        return plantGroupName;
    }

    public void setPlantGroupName(String plantGroupName) {
        this.plantGroupName = plantGroupName;
    }

    public String getPlantId() {
        return plantId;
    }

    public void setPlantId(String plantId) {
        this.plantId = plantId;
    }

    public String getPlantName() {
        return plantName;
    }

    public void setPlantName(String plantName) {
        this.plantName = plantName;
    }
}

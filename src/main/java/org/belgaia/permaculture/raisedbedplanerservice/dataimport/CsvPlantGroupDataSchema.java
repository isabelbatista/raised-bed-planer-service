package org.belgaia.permaculture.raisedbedplanerservice.dataimport;

import com.opencsv.bean.CsvBindByPosition;

public class CsvPlantGroupDataSchema {
    @CsvBindByPosition(position = 0)
    private String ID;

    @CsvBindByPosition(position = 1)
    private String groupName;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}

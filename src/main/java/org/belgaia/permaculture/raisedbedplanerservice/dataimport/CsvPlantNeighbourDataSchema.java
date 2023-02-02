package org.belgaia.permaculture.raisedbedplanerservice.dataimport;

import com.opencsv.bean.CsvBindByPosition;

public class CsvPlantNeighbourDataSchema {

    @CsvBindByPosition(position = 0)
    private String plantOneId;

    @CsvBindByPosition(position = 1)
    private String plantOneName;

    @CsvBindByPosition(position = 2)
    private String plantTwoId;

    @CsvBindByPosition(position = 3)
    private String plantTwoName;

    @CsvBindByPosition(position = 4)
    private String groupId;

    @CsvBindByPosition(position = 5)
    private String groupName;

    @CsvBindByPosition(position = 6)
    private String goodNeighbour;

    @CsvBindByPosition(position = 7)
    private String badNeighbour;

    @CsvBindByPosition(position = 8)
    private String comment;

    public String getPlantOneId() {
        return plantOneId;
    }

    public void setPlantOneId(String plantOneId) {
        this.plantOneId = plantOneId;
    }

    public String getPlantOneName() {
        return plantOneName;
    }

    public void setPlantOneName(String plantOneName) {
        this.plantOneName = plantOneName;
    }

    public String getPlantTwoId() {
        return plantTwoId;
    }

    public void setPlantTwoId(String plantTwoId) {
        this.plantTwoId = plantTwoId;
    }

    public String getPlantTwoName() {
        return plantTwoName;
    }

    public void setPlantTwoName(String plantTwoName) {
        this.plantTwoName = plantTwoName;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGoodNeighbour() {
        return goodNeighbour;
    }

    public void setGoodNeighbour(String goodNeighbour) {
        this.goodNeighbour = goodNeighbour;
    }

    public String getBadNeighbour() {
        return badNeighbour;
    }

    public void setBadNeighbour(String badNeighbour) {
        this.badNeighbour = badNeighbour;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}

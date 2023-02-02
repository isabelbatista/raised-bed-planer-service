package org.belgaia.permaculture.raisedbedplanerservice.beans;

public class PlantNeighbour {

    private Integer plantOneId;
    private String plantOneName;
    private Integer plantTwoId;
    private String plantTwoName;
    private Integer groupId;
    private String groupName;
    private Boolean goodNeighbour;
    private Boolean badNeighbour;
    private String comment;

    public Integer getPlantOneId() {
        return plantOneId;
    }

    public void setPlantOneId(Integer plantOneId) {
        this.plantOneId = plantOneId;
    }

    public String getPlantOneName() {
        return plantOneName;
    }

    public void setPlantOneName(String plantOneName) {
        this.plantOneName = plantOneName;
    }

    public Integer getPlantTwoId() {
        return plantTwoId;
    }

    public void setPlantTwoId(Integer plantTwoId) {
        this.plantTwoId = plantTwoId;
    }

    public String getPlantTwoName() {
        return plantTwoName;
    }

    public void setPlantTwoName(String plantTwoName) {
        this.plantTwoName = plantTwoName;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Boolean isGoodNeighbour() {
        return goodNeighbour;
    }

    public void setGoodNeighbour(Boolean goodNeighbour) {
        this.goodNeighbour = goodNeighbour;
    }

    public Boolean isBadNeighbour() {
        return badNeighbour;
    }

    public void setBadNeighbour(Boolean badNeighbour) {
        this.badNeighbour = badNeighbour;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}

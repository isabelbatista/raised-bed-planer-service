package org.belgaia.permaculture.raisedbedplanerservice.beans;

public enum PlantingStatus {
    CREATED ("Created"),
    PLANTED ("Planted"),
    CLOSED ("Closed");

    private final java.lang.String status;

    PlantingStatus(java.lang.String status) {
        this.status = status;
    }

    public java.lang.String status() {
        return status;
    }
}

package com.estitt.model.dto;

public class StatusDTO {

    public StatusDTO() {}

    public StatusDTO(String status) {
        this.status = status;
    }

    public static StatusDTO success() {
        return new StatusDTO("success");
    }

    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

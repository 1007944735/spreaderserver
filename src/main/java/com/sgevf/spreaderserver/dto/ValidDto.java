package com.sgevf.spreaderserver.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ValidDto {
    private String uuuid;
    private String validUrl;

    public ValidDto() {
    }

    public ValidDto(String uuuid, String validUrl) {
        this.uuuid = uuuid;
        this.validUrl = validUrl;
    }

    public String getUuuid() {
        return uuuid;
    }

    public void setUuuid(String uuuid) {
        this.uuuid = uuuid;
    }

    public String getValidUrl() {
        return validUrl;
    }

    public void setValidUrl(String validUrl) {
        this.validUrl = validUrl;
    }
}

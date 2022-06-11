package com.example.demo2.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class HuaweiGetSiteId {
    
    
    @JsonProperty("errcode")
    private String errcode;
    @JsonProperty("errmsg")
    private String errmsg;
    @JsonProperty("totalRecords")
    private Integer totalRecords;
    @JsonProperty("pageIndex")
    private Integer pageIndex;
    @JsonProperty("pageSize")
    private Integer pageSize;
    @JsonProperty("data")
    private List<DataDTO> data;
    
    @NoArgsConstructor
    @Data
    public static class DataDTO {
        @JsonProperty("id")
        private String id;
        @JsonProperty("tenantId")
        private String tenantId;
        @JsonProperty("name")
        private String name;
        @JsonProperty("description")
        private String description;
        @JsonProperty("type")
        private List<String> type;
        @JsonProperty("latitude")
        private String latitude;
        @JsonProperty("longtitude")
        private String longtitude;
        @JsonProperty("longitude")
        private String longitude;
        @JsonProperty("contact")
        private String contact;
        @JsonProperty("tag")
        private List<?> tag;
        @JsonProperty("isolated")
        private Object isolated;
        @JsonProperty("email")
        private Object email;
        @JsonProperty("phone")
        private Object phone;
        @JsonProperty("postcode")
        private String postcode;
        @JsonProperty("address")
        private String address;
    }
}

package com.example.backend.food.client.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Photo {

    private String thumb;

    private String highres;

    private Boolean is_user_uploaded;
}

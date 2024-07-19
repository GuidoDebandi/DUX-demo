package com.dux.teams.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TeamDto {
    @JsonProperty("nombre")
    private String name;
    @JsonProperty("liga")
    private String league;
    @JsonProperty("pais")
    private String country;

    public boolean hasNullFields() {
        return name == null || league == null || country == null;
    }
}

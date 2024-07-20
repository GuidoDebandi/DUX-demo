package com.dux.backproxy.client.teams.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;



@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class Team {

  private Integer id;
  @JsonProperty("nombre")
  private String name;
  @JsonProperty("liga")
  private String league;
  @JsonProperty("pais")
  private String country;


}

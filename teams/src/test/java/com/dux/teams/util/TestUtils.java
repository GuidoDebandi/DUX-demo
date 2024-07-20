package com.dux.teams.util;

import com.dux.teams.dto.TeamDto;
import com.dux.teams.model.Team;

import java.util.List;
import java.util.Optional;

public class TestUtils {

    public static Integer ID_TEAM = 1;
    public static String NAME_TEAM = "Argentina";
    public static String LEAGUE_TEAM = "Internacional";
    public static String COUNTRY_TEAM = "Argentina";

    public static List<Team> getListTeams(){
        return List.of(getTeam());
    }

    public static Team getTeam(){
        return Team.builder().id(ID_TEAM).country(COUNTRY_TEAM).league(LEAGUE_TEAM).name(NAME_TEAM).build();
    }

    public static TeamDto getTeamDto(){
        return TeamDto.builder().country(COUNTRY_TEAM).league(LEAGUE_TEAM).name(NAME_TEAM).build();
    }

}

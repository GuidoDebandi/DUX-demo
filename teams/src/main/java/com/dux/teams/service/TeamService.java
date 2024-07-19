package com.dux.teams.service;

import com.dux.teams.dto.TeamDto;
import com.dux.teams.model.Team;

import java.util.List;

public interface TeamService {

    List<Team> findAllTeams();
    Team createTeam(TeamDto teamDto);
    List<Team> findTeamsByName(String name);
    Team findTeamById(Integer id);
    void deleteTeam(Integer id);
    Team updateTeam(Integer id, TeamDto teamDto);
}

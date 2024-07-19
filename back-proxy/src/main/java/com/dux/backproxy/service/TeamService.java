package com.dux.backproxy.service;

import com.dux.backproxy.client.teams.dto.Team;
import com.dux.backproxy.dto.response.BackProxyResponse;

import java.util.List;

public interface TeamService {

  List<Team> findAllteams();
  Team createTeam(Team teamDto);
  List<Team> findTeamsByName(String name);
  Team findTeamById(Integer id);
  void deleteTeam(Integer id);
  Team updateTeam(Integer id, Team teamDto);

}

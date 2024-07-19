package com.dux.backproxy.service.impl;

import com.dux.backproxy.client.teams.TeamsClient;
import com.dux.backproxy.client.teams.dto.Team;
import com.dux.backproxy.exception.BackProxyGenericException;
import com.dux.backproxy.service.TeamService;
import feign.RetryableException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class TeamServiceImpl implements TeamService {

  private static final String FIND_ALL_TEAMS = "::: Comienza la ejecución del método findAllTeams :::";
  private static final String FIND_TEAM_BY_ID = "::: Comienza la ejecución del método findTeamById :::";
  private static final String FIND_TEAM_BY_NAME = "::: Comienza la ejecución del método findTeamsByName :::";
  private static final String CREATE_TEAM = "::: Comienza la ejecución del método createTeam :::";
  private static final String UPDATE_TEAM = "::: Comienza la ejecución del método updateTeam :::";
  private static final String DELETE_TEAM = "::: Comienza la ejecución del método deleteTeam :::";
  private static final String TEAMS_CLIENT_FAILED_LOG = "::: Falló el servicio de teams, excepción -> {} :::";

  private static final String TEAMS_CLIENT_FAILED_MESSAGE = "Fallo en el servicio de Equipos";

  private final TeamsClient teamsClient;

  public TeamServiceImpl(TeamsClient teamsClient) {
    this.teamsClient = teamsClient;
  }

  public List<Team> findAllteams() {
    try {
      log.info(FIND_ALL_TEAMS);
      return teamsClient.findTeamsAll().getBody();
    } catch (RetryableException e) {
      log.info(TEAMS_CLIENT_FAILED_LOG, e.getMessage());
      throw new BackProxyGenericException(TEAMS_CLIENT_FAILED_MESSAGE,500);
    }
  }

  @Override public Team createTeam(Team teamDto) {
    try {
      log.info(CREATE_TEAM);
      return teamsClient.createTeam(teamDto).getBody();
    } catch (RetryableException e) {
      log.info(TEAMS_CLIENT_FAILED_LOG, e.getMessage());
      throw new BackProxyGenericException(TEAMS_CLIENT_FAILED_MESSAGE,500);
    }
  }

  @Override public List<Team> findTeamsByName(String name) {
    try {
      log.info(FIND_TEAM_BY_NAME);
      return teamsClient.findTeamByName(name).getBody();
    } catch (RetryableException e) {
      log.info(TEAMS_CLIENT_FAILED_LOG, e.getMessage());
      throw new BackProxyGenericException(TEAMS_CLIENT_FAILED_MESSAGE,500);
    }
  }

  @Override public Team findTeamById(Integer id) {
    try {
      log.info(FIND_TEAM_BY_ID);
      return teamsClient.findTeamById(id).getBody();
    } catch (RetryableException e) {
      log.info(TEAMS_CLIENT_FAILED_LOG, e.getMessage());
      throw new BackProxyGenericException(TEAMS_CLIENT_FAILED_MESSAGE,500);
    }
  }

  @Override public void deleteTeam(Integer id) {
    try {
      log.info(DELETE_TEAM);
      if(!teamsClient.deleteTeam(id).getStatusCode().equals(HttpStatus.NO_CONTENT)) {
        throw new BackProxyGenericException(TEAMS_CLIENT_FAILED_MESSAGE,500);
      }
    } catch (RetryableException e) {
      log.info(TEAMS_CLIENT_FAILED_LOG, e.getMessage());
      throw new BackProxyGenericException(TEAMS_CLIENT_FAILED_MESSAGE,500);
    }

  }

  @Override public Team updateTeam(Integer id, Team teamDto) {
    try {
      log.info(UPDATE_TEAM);
      return teamsClient.updateTeam(id,teamDto).getBody();
    } catch (RetryableException e) {
      log.info(TEAMS_CLIENT_FAILED_LOG, e.getMessage());
      throw new BackProxyGenericException(TEAMS_CLIENT_FAILED_MESSAGE,500);
    }
  }
}

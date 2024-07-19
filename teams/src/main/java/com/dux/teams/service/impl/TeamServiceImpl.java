package com.dux.teams.service.impl;

import com.dux.teams.dto.TeamDto;
import com.dux.teams.exception.TeamException;
import com.dux.teams.model.Team;
import com.dux.teams.repository.TeamRepository;
import com.dux.teams.service.TeamService;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {
    private static final Logger log = LoggerFactory.getLogger(TeamServiceImpl.class);

    private static final String ENTITY_NOT_FOND_MESSAGE = "Equipo no encontrado";

    @Autowired TeamRepository teamRepository;



    @Override public List<Team> findAllTeams() {
        return teamRepository.findAll();
    }

    @Override public Team createTeam(TeamDto teamDto) {
        if(teamDto.hasNullFields()){
            throw new TeamException("La solicitud es invalida");
        }

        Team team = Team.builder()
            .country(teamDto.getCountry())
            .name(teamDto.getName())
            .league(teamDto.getLeague())
            .build();

        log.debug(":: Executing saveTeam for value: {} ::",team);
        return teamRepository.save(team);
    }

    @Override public List<Team> findTeamsByName(String name) {
        log.debug(":: Executing findByName for value: {} ::",name);
        return teamRepository.findByNameContainingOrderByName(name);
    }

    @Override public Team findTeamById(Integer id) {
        return teamRepository.findById(id).orElseThrow(()-> new EntityNotFoundException(ENTITY_NOT_FOND_MESSAGE));
    }

    @Override public void deleteTeam(Integer id) {
        Team team = teamRepository.findById(id).orElseThrow(()-> new EntityNotFoundException(ENTITY_NOT_FOND_MESSAGE));
        teamRepository.delete(team);
    }

    @Override public Team updateTeam(Integer id, TeamDto teamDto) {
        Team team = teamRepository.findById(id).orElseThrow(()-> new EntityNotFoundException(ENTITY_NOT_FOND_MESSAGE));
        team.setCountry(teamDto.getCountry());
        team.setName(teamDto.getName());
        team.setLeague(teamDto.getLeague());
        return  teamRepository.save(team);
    }



}

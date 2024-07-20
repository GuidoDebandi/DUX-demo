package com.dux.teams.service;

import com.dux.teams.dto.TeamDto;
import com.dux.teams.exception.TeamException;
import com.dux.teams.repository.TeamRepository;
import com.dux.teams.service.impl.TeamServiceImpl;
import com.dux.teams.util.TestUtils;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.dux.teams.util.TestUtils.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class TeamServiceImplTest {
    @Mock
    TeamRepository teamRepository;

    @InjectMocks
    TeamServiceImpl teamService;

    @Test
    void getAllTeams200(){
        teamService.findAllTeams();

        verify(teamRepository,times(1)).findAll();

    }

    @Test
    void getTeamsByName200(){
        Mockito.when(teamRepository.findByNameContainingOrderByName(any())).thenReturn(TestUtils.getListTeams());

        String name = "argen";
        teamService.findTeamsByName(name);


        verify(teamRepository,times(1)).findByNameContainingOrderByName(name);
    }


    @Test
    void getTeamsById200(){
        Mockito.when(teamRepository.findById(any())).thenReturn(Optional.of(getTeam()));

        teamService.findTeamById(ID_TEAM);

        verify(teamRepository,times(1)).findById(ID_TEAM);
    }

    @Test
    void getTeamsById404(){
        Mockito.when(teamRepository.findById(any())).thenReturn(Optional.empty());

        Assertions.assertThrows(EntityNotFoundException.class, ()->teamService.findTeamById(ID_TEAM));

        verify(teamRepository,times(1)).findById(ID_TEAM);

    }

    @Test
    void deleteTeam204(){
        Mockito.when(teamRepository.findById(any())).thenReturn(Optional.of(getTeam()));

        teamService.deleteTeam(ID_TEAM);

        verify(teamRepository,times(1)).delete(any());
    }
    @Test
    void deleteTeam404(){
        Mockito.when(teamRepository.findById(any())).thenReturn(Optional.empty());

        Assertions.assertThrows(EntityNotFoundException.class, ()->teamService.findTeamById(ID_TEAM));

        verify(teamRepository,times(0)).delete(any());

    }

    @Test
    void saveTeam400(){
        TeamDto dto = getTeamDto();
        dto.setCountry(null);

        Assertions.assertThrows(TeamException.class, ()->teamService.createTeam(dto));

        verify(teamRepository,times(0)).save(any());

    }
    @Test
    void saveTeam201(){
        teamService.createTeam(getTeamDto());

        verify(teamRepository,times(1)).save(any());
    }

    @Test
    void updateTeam200(){
        Mockito.when(teamRepository.findById(any())).thenReturn(Optional.of(getTeam()));

        teamService.updateTeam(ID_TEAM,getTeamDto());

        verify(teamRepository,times(1)).save(any());
    }

    @Test
    void updateTeam404(){
        Mockito.when(teamRepository.findById(any())).thenReturn(Optional.empty());

        Assertions.assertThrows(EntityNotFoundException.class, ()->teamService.findTeamById(ID_TEAM));

        verify(teamRepository,times(0)).save(any());

    }
}
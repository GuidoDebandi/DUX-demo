package com.dux.backproxy.client.teams;

import com.dux.backproxy.client.teams.configuration.TeamsClientConfig;
import com.dux.backproxy.client.teams.dto.Team;
import com.dux.backproxy.dto.response.BackProxyResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "teams", url = "${teams.endpoint}", configuration = TeamsClientConfig.class)
public interface TeamsClient {
  @GetMapping( value = "/equipos", produces = { "application/json" },
      consumes = { "application/json" })
  ResponseEntity<List<Team>> findTeamsAll();

  @GetMapping( value = "/equipos/{id}", produces = { "application/json" },
      consumes = { "application/json" })
  ResponseEntity<Team> findTeamById(@PathVariable("id") Integer id);

  @GetMapping( value = "/equipos/buscar", produces = { "application/json" },
      consumes = { "application/json" })
  ResponseEntity<List<Team>> findTeamByName(@RequestParam("nombre") String name);

  @PostMapping( value = "/equipos", produces = { "application/json" },
      consumes = { "application/json" })
  ResponseEntity<Team> createTeam(@RequestBody Team teamDto);

  @PutMapping( value = "/equipos/{id}", produces = { "application/json" },
      consumes = { "application/json" })
  ResponseEntity<Team> updateTeam(@PathVariable("id") Integer id,@RequestBody Team teamDto);

  @DeleteMapping( value = "/equipos/{id}", produces = { "application/json" },
      consumes = { "application/json" })
  ResponseEntity<Team> deleteTeam(@PathVariable("id") Integer id);
}

package com.dux.backproxy.controller;

import com.dux.backproxy.client.security.exception.AuthException;
import com.dux.backproxy.client.teams.dto.Team;
import com.dux.backproxy.exception.errormanager.ErrorResponse;
import com.dux.backproxy.service.SecurityService;
import com.dux.backproxy.service.TeamService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
@Slf4j
public class TeamsController {

  @Value("${teams.uri}")
  public static final String TEAMS_URI = "/equipos";
  @Value("${teams.find.url}")
  public static final String FIND_URL = "/buscar";

  public static final String BAD_CREDENTIALS = "Credenciales Invalidas";

  private final TeamService teamService;

  private final SecurityService securityService;

  public TeamsController(TeamService teamService, SecurityService securityService) {
    this.teamService = teamService;
    this.securityService = securityService;
  }

  @Operation(summary = "Devuelve la lista de todos los equipos de fútbol registrados", description = "Devuelve la lista de todos los equipos de fútbol registrados.",tags = {"TeamsController"})
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successful operation",content = @Content(array = @ArraySchema(schema = @Schema(implementation = Team.class)))),
      @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
      @ApiResponse(responseCode = "500", description = "Error Inesperado", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
  })
  @GetMapping(path = TEAMS_URI)
  public ResponseEntity<List<Team>> findTeams(@RequestHeader("Authorization") String token){
    List<Team> response = null;
    if(Boolean.TRUE.equals(securityService.verifyToken(token))){
      response = teamService.findAllteams();
      return new ResponseEntity<>(response, HttpStatus.OK);
    }else {
      throw new AuthException(BAD_CREDENTIALS);
    }
  }

  @Operation(summary = "Devuelve la información del equipo en base al ID proporcionado", description = "Recupera un equipo con id igual al pasado por parametro desde una consulta a la base de datos.",tags = {"TeamsController"})
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Devuelve la información del equipo correspondiente al ID proporcionado.",content = @Content(schema = @Schema(implementation = Team.class))),
      @ApiResponse(responseCode = "404", description = "El equipo no existe", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
      @ApiResponse(responseCode = "500", description = "Error Inesperado", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
  })
  @GetMapping(path = TEAMS_URI+ "/{id}")
  public ResponseEntity<Team> findTeamById(@PathVariable Integer id,@RequestHeader("Authorization") String token){
    Team response = null;
    if(Boolean.TRUE.equals(securityService.verifyToken(token))){
      response = teamService.findTeamById(id);
      return new ResponseEntity<>(response, HttpStatus.OK);
    }else {
      throw new AuthException(BAD_CREDENTIALS);
    }
  }

  @Operation(summary = "Devuelve la lista de todos los equipos de fútbol registrados", description = "Devuelve la lista de todos los equipos de fútbol registrados.",tags = {"TeamsController"})
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successful operation",content = @Content(array = @ArraySchema(schema = @Schema(implementation = Team.class)))),
      @ApiResponse(responseCode = "500", description = "Error Inesperado", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
  })
  @GetMapping(path = TEAMS_URI + FIND_URL)
  public ResponseEntity<List<Team>> findTeamsByName(@RequestParam("nombre") String name,@RequestHeader("Authorization") String token){

    List<Team> response = null;
    if(Boolean.TRUE.equals(securityService.verifyToken(token))){
      response = teamService.findTeamsByName(name);
      return new ResponseEntity<>(response, HttpStatus.OK);
    }else {
      throw new AuthException(BAD_CREDENTIALS);
    }
  }

  @Operation(summary = "Devuelve la información del equipo en base al ID proporcionado", description = "Recupera un equipo con id igual al pasado por parametro desde una consulta a la base de datos.",tags = {"TeamsController"})
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Devuelve la información del equipo correspondiente al ID proporcionado.",content = @Content(schema = @Schema(implementation = Team.class))),
      @ApiResponse(responseCode = "400", description = "La solicitud es invalida", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
      @ApiResponse(responseCode = "500", description = "Error Inesperado", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
  })
  @PostMapping(path = TEAMS_URI)
  public ResponseEntity<Team> createTeam(@RequestBody Team teamDto,@RequestHeader("Authorization") String token){
    Team response = null;
    if(Boolean.TRUE.equals(securityService.verifyToken(token))){
      response = teamService.createTeam(teamDto);
      return new ResponseEntity<>(response,  HttpStatus.CREATED);
    }else {
      throw new AuthException(BAD_CREDENTIALS);
    }
  }


  @Operation(summary = "Devuelve la información del equipo en base al ID proporcionado", description = "Recupera un equipo con id igual al pasado por parametro desde una consulta a la base de datos.",tags = {"TeamsController"})
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Devuelve la información actualizada del equipo.",content = @Content(schema = @Schema(implementation = Team.class))),
      @ApiResponse(responseCode = "404", description = "El equipo no existe", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
      @ApiResponse(responseCode = "403", description = "Credenciales Invalidas", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
      @ApiResponse(responseCode = "500", description = "Error Inesperado", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
  })
  @PutMapping(path = TEAMS_URI+"/{id}")
  public ResponseEntity<Team> updateTeam(@RequestBody Team teamDto,@PathVariable Integer id,@RequestHeader("Authorization") String token){
    Team response = null;
    if(Boolean.TRUE.equals(securityService.verifyToken(token))){
      response = teamService.updateTeam(id,teamDto);
      return new ResponseEntity<>(response, HttpStatus.OK);
    }else {
      throw new AuthException(BAD_CREDENTIALS);
    }
  }


  @Operation(summary = "Devuelve la información del equipo en base al ID proporcionado", description = "Recupera un equipo con id igual al pasado por parametro desde una consulta a la base de datos.",tags = {"TeamsController"})
  @ApiResponses(value = {
      @ApiResponse(responseCode = "202", description = "Equipo eliminado con éxito.",content = @Content(schema = @Schema(implementation = Team.class))),
      @ApiResponse(responseCode = "404", description = "El equipo no existe", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
      @ApiResponse(responseCode = "500", description = "Error Inesperado", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
  })
  @DeleteMapping(path = TEAMS_URI+ "/{id}")
  public ResponseEntity<Team> deleteTeam(@PathVariable Integer id,@RequestHeader("Authorization") String token){
    if(Boolean.TRUE.equals(securityService.verifyToken(token))){
      teamService.deleteTeam(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }else {
      throw new AuthException(BAD_CREDENTIALS);
    }
  }
}

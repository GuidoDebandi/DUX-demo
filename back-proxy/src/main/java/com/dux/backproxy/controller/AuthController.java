package com.dux.backproxy.controller;

import com.dux.backproxy.client.security.dto.User;
import com.dux.backproxy.client.teams.dto.Team;
import com.dux.backproxy.dto.response.BackProxyAuthResponse;
import com.dux.backproxy.dto.response.BackProxyResponse;
import com.dux.backproxy.exception.errormanager.ErrorResponse;
import com.dux.backproxy.service.SecurityService;
import com.dux.backproxy.service.TeamService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class AuthController {
    private final SecurityService securityService;

  public AuthController(SecurityService securityService) {
    this.securityService = securityService;
  }

  @Operation(summary = "Recupera el token de un usario a parti de su usuario y contraseña", tags = {"security" })
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = BackProxyResponse.class))),
      @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class))),
      @ApiResponse(responseCode = "500", description = "Unexpected Error", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class))) })
  @PostMapping(value = "auth/login")
  public ResponseEntity<BackProxyAuthResponse<String>> login(@RequestBody User userDto) {
    String token = this.securityService.login(userDto);
    return ResponseEntity.ok(new BackProxyAuthResponse<>(token) );
  }

  @Operation(summary = "Recupera el token de un usario a parti de su usuario y contraseña", tags = {"security" })
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = BackProxyResponse.class))),
      @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class))),
      @ApiResponse(responseCode = "500", description = "Unexpected Error", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class))) })
  @PostMapping(value = "auth/register")
  public ResponseEntity<BackProxyAuthResponse<String>> register(@RequestBody User userDto) {
    String token = this.securityService.register(userDto);
    return ResponseEntity.ok(new BackProxyAuthResponse<>(token) );
  }
}

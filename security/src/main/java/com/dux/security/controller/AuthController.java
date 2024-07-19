package com.dux.security.controller;

import com.dux.security.config.auth.AuthFilter;
import com.dux.security.dto.LoginResponseDto;
import com.dux.security.dto.UserDto;
import com.dux.security.dto.SecurityResponse;
import com.dux.security.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(AuthController.URI)
@Tag(name = "TokenController", description = "Controller para decodificar el token")
public class AuthController {
  private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

  public static final String URI = "/auth";

  @Autowired
  private AuthService authService;

  @Operation(summary = "Valida el token enviado", description = "Valida que el token enviado sea valido para operar en el sistema", tags = {
      "TokenController" })
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = SecurityResponse.class))) })
  @GetMapping
  public SecurityResponse<Boolean> verify(@RequestHeader("Authorization") String token) {
    LOGGER.debug("AUTHOCONTROLLER: VALIDACION DE TOKEN EXITOSA DEVOLVIENDO TRUE ");
    return new SecurityResponse<>(Boolean.TRUE);
  }


  @Operation(summary = "Login de un usuario", description = "Realiza un login de un usuario validando la información enviada en el body", tags = {
      "TokenController" })
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = SecurityResponse.class))),
      @ApiResponse(responseCode = "400", description = "Error en los Datos enviados", content = @Content(schema = @Schema(implementation = SecurityResponse.class)))
  })
  @PostMapping("/login")
  public SecurityResponse<String> login(@RequestBody UserDto user) {
    return this.authService.login(user);
  }

  @Operation(summary = "Login de un usuario", description = "Realiza un login de un usuario validando la información enviada en el body", tags = {
      "TokenController" })
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = SecurityResponse.class))),
      @ApiResponse(responseCode = "400", description = "Error en los Datos enviados", content = @Content(schema = @Schema(implementation = SecurityResponse.class)))
  })
  @PostMapping("/register")
  public SecurityResponse<String> signup(@RequestBody UserDto user) {
    return this.authService.signup(user);
  }

}

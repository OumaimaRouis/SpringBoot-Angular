package com.gestion3.controller;

import com.gestion3.config.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:4200/", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})

public class AuthController {
    private AuthenticationManager authenticationManager;
    private JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody AuthenticationRequest authenticationRequest)
    {
        Authentication authentication=authenticationManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(
                                authenticationRequest.getUsername(),authenticationRequest.getPassword()
                        ));


               String jwtToken=  jwtService.generateToken((UserDetails) authentication.getPrincipal());
               Map<String, String> response = new HashMap<>();
               response.put("token", jwtToken);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    //put all the method here
}

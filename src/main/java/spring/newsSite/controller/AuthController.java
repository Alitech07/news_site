package spring.newsSite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.newsSite.entity.User;
import spring.newsSite.payload.ApiResponse;
import spring.newsSite.payload.LoginDTO;
import spring.newsSite.payload.RegisterDTO;
import spring.newsSite.security.JwtProvider;
import spring.newsSite.service.AuthService;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    AuthService authService;

    @PostMapping("/registr")
    public HttpEntity<?> register(@Valid @RequestBody RegisterDTO registerDTO){
        ApiResponse apiResponse = authService.registerUserService(registerDTO);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @PostMapping("/login")
    public HttpEntity<?> login(@Valid @RequestBody LoginDTO loginDTO){
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));
        User user = (User) authenticate.getPrincipal();
        String token = jwtProvider.generateToken(user.getUsername(), user.getPassword(), user.getRole());
        return ResponseEntity.ok(token);
    }
}

package com.asuajeyeison.blogApiRest.repositories.controllers;

import com.asuajeyeison.blogApiRest.security.JwtTokenProvider;
import com.asuajeyeison.blogApiRest.dto.JWTAuthResponseDTO;
import com.asuajeyeison.blogApiRest.dto.LoginDTOO;
import com.asuajeyeison.blogApiRest.dto.RegistroDTO;
import com.asuajeyeison.blogApiRest.models.Rol;
import com.asuajeyeison.blogApiRest.models.Usuario;
import com.asuajeyeison.blogApiRest.repositories.RolRepository;
import com.asuajeyeison.blogApiRest.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/iniciarSesion")
    public ResponseEntity<JWTAuthResponseDTO> authenticateUser(@RequestBody LoginDTOO loginDTO){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsernameOrEmail(), loginDTO.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        //obtenemos el token jwtTokenProvider
        String token = jwtTokenProvider.generarToken(authentication);

        return ResponseEntity.ok(new JWTAuthResponseDTO(token));
    }

    @PostMapping("/registrar")
    public ResponseEntity<?> registrarUser(@RequestBody RegistroDTO registroDTO){
        if(userRepository.existsByUsername(registroDTO.getUsername())){
            return new ResponseEntity<>("El username indicado ya existe", HttpStatus.BAD_REQUEST);
        }
        if(userRepository.existsByEmail(registroDTO.getEmail())){
            return new ResponseEntity<>("El email indicado ya existe", HttpStatus.BAD_REQUEST);
        }
        Usuario usuario= new Usuario();
        usuario.setName(registroDTO.getName());
        usuario.setUsername(registroDTO.getUsername());
        usuario.setEmail(registroDTO.getEmail());
        usuario.setPassword(passwordEncoder.encode(registroDTO.getPassword()));

        Rol rols = rolRepository.findByName("ROLE_ADMIN").get();
        usuario.setRols(Collections.singleton(rols));

        userRepository.save(usuario);
        return new ResponseEntity<>("Usuario registrado Exitosamente", HttpStatus.OK);

    }

}

package com.adryel0713.backend.controllers;

import com.adryel0713.backend.model.Membro;
import com.adryel0713.backend.security.JwtUtil;
import com.adryel0713.backend.services.MembroService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final MembroService membroService;

    public AuthController(MembroService membroService) {
        this.membroService = membroService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String,String> request){
        Membro membro = membroService.salvarMembro(request.get("username"),request.get("password"));
        return ResponseEntity.ok(membro);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String,String> request){
        Optional<Membro> membro = membroService.buscarMembroPorUsername(request.get("username"));
        if(membro.isPresent() && membroService.verificar(request.get("password"),membro.get().getPassword())){
            String token = JwtUtil.gerarToken(membro.get().getUsername());
            return ResponseEntity.ok(Map.of("token",token));
        }
        return ResponseEntity.badRequest().build();

    }
    @PutMapping("/promover")
    public ResponseEntity<String> promoverParaAdmin(@RequestParam String username) {
        String resposta = membroService.promoverParaAdmin(username);
        return ResponseEntity.ok(resposta);
    }

}

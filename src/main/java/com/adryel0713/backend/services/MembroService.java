package com.adryel0713.backend.services;

import com.adryel0713.backend.model.Membro;
import com.adryel0713.backend.model.Role;
import com.adryel0713.backend.repositories.MembroRepository;
import com.adryel0713.backend.repositories.RoleRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MembroService {

    private final MembroRepository membroRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public MembroService(MembroRepository membroRepository, RoleRepository roleRepository) {
        this.membroRepository = membroRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
        this.roleRepository = roleRepository;
    }

    public Membro salvarMembro(String username,String password){
        String passwordEncripted = this.passwordEncoder.encode(password);

        Membro membro = new Membro(username, passwordEncripted);

        Role userRole = roleRepository.findByNome("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("Role ROLE_USER não encontrada"));

        membro.getRoles().add(userRole);

        return membroRepository.save(membro);
    }
    public Optional<Membro> buscarMembroPorUsername(String username){
        return membroRepository.findByUsername(username);
    }
    public boolean verificar(String senhaDigitada, String senhaHash) {
        return passwordEncoder.matches(senhaDigitada, senhaHash);
    }

    public String promoverParaAdmin(String username) {
        Membro membro = membroRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Role adminRole = roleRepository.findByNome("ROLE_ADMIN")
                .orElseThrow(() -> new RuntimeException("Role 'ROLE_ADMIN' não encontrada"));

        if (membro.getRoles().contains(adminRole)) {
            return "Usuário já é ADMIN.";
        }

        membro.getRoles().add(adminRole);
        membroRepository.save(membro);

        return "Usuário promovido a ADMIN!";
    }

}

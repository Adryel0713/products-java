package com.adryel0713.backend.services;

import com.adryel0713.backend.model.Membro;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MembroDetailsService implements UserDetailsService {

    private final MembroService membroService;

    public MembroDetailsService(MembroService membroService) {
        this.membroService = membroService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Membro membro = membroService
                .buscarMembroPorUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario não encontrado!"));

        String[] roles = membro
                .getRoles()
                .stream()
                .map(role -> role.getNome().replace("ROLE_",""))
                .toArray(String[]::new);

        return User.builder()
                .username(membro.getUsername())
                .password(membro.getPassword()).roles(roles).build();
    }
}

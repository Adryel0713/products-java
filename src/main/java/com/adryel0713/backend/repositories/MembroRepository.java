package com.adryel0713.backend.repositories;

import com.adryel0713.backend.model.Membro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MembroRepository extends JpaRepository<Membro,Long> {

    Optional<Membro> findByUsername(String username);

}

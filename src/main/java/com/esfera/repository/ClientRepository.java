package com.esfera.repository;

import com.esfera.domain.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {




    Optional<Client> findById(Long id);

    Optional<Client> findClientByName(String name);

    void delete(Client id);

    Client save(Client client);

    Page<Client> findAll(Pageable p);
}




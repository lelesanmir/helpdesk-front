package com.leonardo.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leonardo.helpdesk.domain.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {

}

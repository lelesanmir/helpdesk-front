package com.leonardo.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leonardo.helpdesk.domain.Called;

public interface CalledRepository extends JpaRepository<Called, Integer> {

}

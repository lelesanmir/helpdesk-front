package com.leonardo.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leonardo.helpdesk.domain.Technician;

public interface TechnicianRepository extends JpaRepository<Technician, Integer> {

}

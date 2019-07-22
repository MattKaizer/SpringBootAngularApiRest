package com.mbm.springbapirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.mbm.springbapirest.models.Cliente;

public interface I_ClienteRepository extends JpaRepository<Cliente, Long> {

	
}

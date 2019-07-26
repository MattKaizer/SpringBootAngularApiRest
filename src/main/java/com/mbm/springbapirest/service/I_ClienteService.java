package com.mbm.springbapirest.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mbm.springbapirest.models.Cliente;


public interface I_ClienteService {

	public Cliente save(Cliente c);
	public void delete(long id);
	public List<Cliente> findAll();
	public Page<Cliente> findAll(Pageable PAGEABLE);
	public Cliente findById(long id);
	public Cliente edit(Cliente c);
	public List<Cliente> buscador(String cadena);
}

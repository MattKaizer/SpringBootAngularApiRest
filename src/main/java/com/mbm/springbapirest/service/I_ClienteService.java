package com.mbm.springbapirest.service;

import java.util.List;

import com.mbm.springbapirest.models.Cliente;


public interface I_ClienteService {

	public Cliente add(Cliente c);
	public List<Cliente> findAll();
	public Cliente findById(long id);
	public Cliente edit(Cliente c);
	public List<Cliente> buscador(String cadena);
}

package com.mbm.springbapirest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mbm.springbapirest.models.Cliente;
import com.mbm.springbapirest.repository.I_ClienteRepository;

@Service
public class ClienteService implements I_ClienteService {
	
	@Autowired
	private I_ClienteRepository repositorio;

	@Override
	public Cliente add(Cliente c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Cliente> findAll() {
		// TODO Auto-generated method stub
		return repositorio.findAll();
	}

	@Override
	public Cliente findById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cliente edit(Cliente c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Cliente> buscador(String cadena) {
		// TODO Auto-generated method stub
		return null;
	}

}

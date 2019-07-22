package com.mbm.springbapirest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mbm.springbapirest.service.I_ClienteService;
import com.mbm.springbapirest.models.*;
import java.util.List;


@RestController
@RequestMapping("/api")
public class ClienteRestController {

	@Autowired
	public I_ClienteService clienteService;
	
	@GetMapping("/clientes")
	public List<Cliente> list() {
		return clienteService.findAll();
	}
}

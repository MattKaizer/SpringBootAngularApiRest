package com.mbm.springbapirest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mbm.springbapirest.service.I_ClienteService;
import com.mbm.springbapirest.models.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;


@CrossOrigin
@RestController
@RequestMapping("/api")
public class ClienteRestController {

	@Autowired
	public I_ClienteService clienteService;
	
	/*
	 * List 
	 */
	@GetMapping("/clientes")
	public List<Cliente> list() {
		return clienteService.findAll();
	}
	
	//Pageable List
	@GetMapping("/clientes/page/{page}")
	public Page<Cliente> list(@PathVariable Integer page) {
		return clienteService.findAll(PageRequest.of(page, 6));
	}
	
	/*
	 * SHOW 
	 */
	
//	@GetMapping("/clientes/{id}")
//	public Cliente show(@PathVariable Long id) {
//		return clienteService.findById(id);
//	}
	
	@GetMapping("/clientes/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {

		Cliente cliente = null;
		Map<String, Object> response = new HashMap<>();

		try {
			cliente = clienteService.findById(id);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			response.put("mensaje", "Error en la consulta de la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		if (cliente == null) {
			response.put("mensaje", "El cliente con ID: ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
	}
	
	/*
	 * CREATE
	 */
//	@PostMapping("/clientes")
//	@ResponseStatus(HttpStatus.CREATED)
//	public Cliente create(@RequestBody Cliente c) {
//		return clienteService.save(c);
//	}
	
	@PostMapping("/clientes")
	public ResponseEntity<?> create(@Valid @RequestBody Cliente c, BindingResult result) {
		
		Cliente newCliente = null;
		Map<String, Object> response = new HashMap<>();
		
//		if (result.hasErrors()) {
//			List<String> errors = new ArrayList<>();
//			for (FieldError err : result.getFieldErrors()) {
//				errors.add("El campo " + err.getField() + ": " + err.getDefaultMessage());
//			}
//			response.put("errors", errors);
//			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
//		}
		
		//Para jdk8
		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo " + err.getField() + ": " + err.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try {
			newCliente = clienteService.save(c);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			response.put("mensaje", "Error en el insert la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		response.put("mensaje", "El cliente se ha creado con exito");
		response.put("cliente", newCliente);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	/*
	 * UPDATE
	 */
	
//	@PutMapping("/clientes/{id}")
//	public Cliente update(@RequestBody Cliente c, @PathVariable Long id) {
//		Cliente cCurrent = clienteService.findById(id);
//		cCurrent.setApellido(c.getApellido());
//		cCurrent.setNombre(c.getNombre());
//		cCurrent.setEmail(c.getEmail());
//		
//		return clienteService.save(cCurrent);
//	}
	
	
	@PutMapping("/clientes/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Cliente c, @PathVariable Long id,  BindingResult result) {
		
		Map<String, Object> response = new HashMap<>();
		Cliente updatedCli = null;
		
		//Para jdk8
		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo " + err.getField() + ": " + err.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		Cliente cCurrent = clienteService.findById(id);
		if (cCurrent == null) {
			response.put("mensaje", "Error, no se pudo editar, el cliente con ID: ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		try {
			cCurrent.setApellido(c.getApellido());
			cCurrent.setNombre(c.getNombre());
			cCurrent.setEmail(c.getEmail());
			updatedCli = clienteService.save(cCurrent);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			response.put("mensaje", "Error al actualizar la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		

		response.put("mensaje", "El cliente se ha actualizado con exito");
		response.put("cliente", updatedCli);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	/*
	 * DELETE
	 */
	
//	@DeleteMapping("/clientes/{id}")
//	@ResponseStatus(HttpStatus.NO_CONTENT)
//	public void delete(@PathVariable Long id) {
//		clienteService.delete(id);
//	}
	
	@DeleteMapping("/clientes/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		
		Map<String, Object> response = new HashMap<>();
		
		try {
			clienteService.delete(id);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			response.put("mensaje", "Error al borrar el cliente la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El cliente se ha eliminado con exito");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	
}

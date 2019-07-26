package com.mbm.springbapirest.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="clientes")
public class Cliente implements Serializable {


	private static final long serialVersionUID = 1L;
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@NotEmpty(message="no puede estar vacio")
	@Size(min=4, max=15, message="El tamaño tiene que estar entre 4 y 15 caracteres")
	@Column(nullable=false)
	private String nombre;
	@NotEmpty(message="no puede estar vacio")
	@Column(nullable=false)
	private String apellido;
	@Column(nullable=false)
	@NotEmpty(message="no puede estar vacio")
	@Email(message="no es una direccion de correo bien formada")
	private String email;
	@NotNull(message="no puede estar vacio")
	@Column(name="create_at")
	@Temporal(TemporalType.DATE)
	private Date createdAt;

	
//	@PrePersist
//	public void prePersistCreated() {
//		this.createdAt = new Date();
//	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

//	public Date getDeletedAt() {
//		return deletedAt;
//	}
//
//	public void setDeletedAt(Date deletedAt) {
//		this.deletedAt = deletedAt;
//	}

}

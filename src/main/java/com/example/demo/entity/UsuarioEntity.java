package com.example.demo.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "tb_Usuario")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id_Usuario" , nullable = false)
	private Integer idUsuario;
	
	@Column(name = "Correo", nullable = false)
	private String correo;
	
	@Column(name = "Contrasenia", nullable = false)
	private String password;
	
	@Column(name = "Nombre", nullable = false)
	private String nombre;
	
	@Column(name = "Apellido", nullable = false)
	private String apellido;
	
	@Column(name = "Fecha_Nacimiento", nullable = false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fechaNacimiento;

	@Column(name = "Url_Imagen", nullable = false)
	private String urlImagen;
	

}

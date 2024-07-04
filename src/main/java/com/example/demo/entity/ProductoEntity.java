package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "tb_Producto")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ProductoEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	@Column(name = "Id_Producto", nullable = false)
	private Integer idProducto;
	
	@Column(name = "Nombre_Producto", nullable = false)
	private String nomProducto;
	
	@Column(name = "Precio", nullable = false)
	private Double precio;
	
	@Column(name = "Stock", nullable = false)
	private Integer stock;
	
	@ManyToOne
	@JoinColumn(name= "Id_Categoria", nullable = false)
	private CategoriaEntity idCategoria;
	
}

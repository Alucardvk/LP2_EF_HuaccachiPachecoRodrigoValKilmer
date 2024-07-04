package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.ProductoEntity;

public interface ProductoService {
	  List<ProductoEntity> listarProductos();
	    ProductoEntity buscarProductoPorId(Integer idProducto);
	    ProductoEntity crearProducto(ProductoEntity producto);
	    ProductoEntity actualizarProducto(ProductoEntity producto);
	    void eliminarProducto(Integer idProducto);

}

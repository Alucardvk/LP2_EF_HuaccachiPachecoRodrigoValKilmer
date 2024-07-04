package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.ProductoEntity;
import com.example.demo.repository.ProductoRepository;
import com.example.demo.service.ProductoService;

@Service
public class ProductoServiceImpl implements ProductoService{
	
	@Autowired
	private ProductoRepository productoRepository;
	
	@Override
	public List<ProductoEntity> listarProductos() {
		// TODO Auto-generated method stub
		return productoRepository.findAll();
	}

	@Override
	public ProductoEntity buscarProductoPorId(Integer id) {
		// TODO Auto-generated method stub
		return productoRepository.findByidProducto(id);
	}

	@Override
	public ProductoEntity crearProducto(ProductoEntity producto) {
		// TODO Auto-generated method stub
		return productoRepository.save(producto);
	}

	@Override
	public ProductoEntity actualizarProducto(ProductoEntity producto) {
		ProductoEntity productoBuscado = buscarProductoPorId(producto.getIdProducto());
		if (productoBuscado != null) {
			productoBuscado.setNomProducto(producto.getNomProducto());
			productoBuscado.setPrecio(producto.getPrecio());
			productoBuscado.setStock(producto.getStock());;
			productoBuscado.setIdCategoria(producto.getIdCategoria());
			
			return productoRepository.save(productoBuscado);
		}
		return null;
	}

	@Override
	public void eliminarProducto(Integer id) {
		// TODO Auto-generated method stub
		productoRepository.deleteById(id);
		
	}

}

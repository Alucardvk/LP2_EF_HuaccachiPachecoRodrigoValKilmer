package com.example.demo.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.CategoriaEntity;
import com.example.demo.entity.ProductoEntity;
import com.example.demo.entity.UsuarioEntity;
import com.example.demo.repository.CategoriaRepository;
import com.example.demo.service.ProductoService;
import com.example.demo.service.UsuarioService;
import com.example.demo.service.impl.PdfService;

import jakarta.servlet.http.HttpSession;

@Controller
public class ProductoController {
	
	@Autowired
	private PdfService pdfService;
	@Autowired
	private ProductoService productoService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@GetMapping("/menu")
	public String mostrarMenu(HttpSession session, Model model) {
		if (session.getAttribute("usuario")==null) {
			return "redirect:/";
		}
		
		String correo= session.getAttribute("usuario").toString();
		UsuarioEntity usuarioEntity = usuarioService.buscarUsuarioPorCorreo(correo);
		model.addAttribute("nombre", usuarioEntity.getNombre());
		List<ProductoEntity>productos= productoService.listarProductos();
		model.addAttribute("productos", productos);
		
		
		return "menu";
	}
	
	
	@GetMapping("/agregarProducto")
	public String mostrarAgregarProducto(HttpSession session, Model model) {
	    if (session.getAttribute("usuario") == null) {
	        return "redirect:/";
	    }

	    String correo = session.getAttribute("usuario").toString();
	    UsuarioEntity usuarioEntity = usuarioService.buscarUsuarioPorCorreo(correo);
	    model.addAttribute("nombre", usuarioEntity.getNombre());

	    List<CategoriaEntity> categorias = categoriaRepository.findAll();
	    model.addAttribute("categoria", categorias);
	    model.addAttribute("producto", new ProductoEntity());

	    return "agregarProducto";
	}

	
    @PostMapping("/guardarProducto")
    public String guardarProducto(HttpSession session, ProductoEntity producto,  Model model) {
       productoService.crearProducto(producto);
       List<CategoriaEntity>categoria= categoriaRepository.findAll();
       model.addAttribute("categoria", categoria);
       return "redirect:/menu";
    }
    
    @GetMapping("/editarProducto/{id}")
	public String mostrarEditarProducto(@PathVariable("id") Integer id, Model model) {
		ProductoEntity producto = productoService.buscarProductoPorId(id);
		List<CategoriaEntity> categorias = categoriaRepository.findAll();
		
		model.addAttribute("producto", producto);
		model.addAttribute("categorias", categorias);
		
		return "editarProducto";
	}
	
	@PostMapping("/actualizarProducto")
	public String actualizarProducto(@ModelAttribute("producto") ProductoEntity producto) {
		productoService.actualizarProducto(producto);
		return "redirect:/menu";
	}
	
	@GetMapping("/detalleProducto/{id}")
	public String verDetalleProducto(@PathVariable("id") Integer id, Model model) {
		ProductoEntity producto = productoService.buscarProductoPorId(id);
		model.addAttribute("producto", producto);
		
		return "detalleProducto";
	}
	
	@GetMapping("/eliminarProducto/{id}")
	public String eliminarProducto(@PathVariable("id") Integer id) {
		productoService.eliminarProducto(id);
		return "redirect:/menu";
	}
	
	@GetMapping("/generar_pdf")
		
		public ResponseEntity<InputStreamResource> generarPDF(HttpSession session) throws IOException{

	    List<ProductoEntity> productoSession = null;
	    if (session.getAttribute("usuario") != null) {
	        productoSession = productoService.listarProductos();
	    } else {
	        productoSession = (List<ProductoEntity>) session.getAttribute("productos");
	    }
	    String correo = session.getAttribute("usuario").toString();
	    UsuarioEntity usuarioEntity = usuarioService.buscarUsuarioPorCorreo(correo);
	    String nombreUsuario = usuarioEntity.getNombre();

	    Map<String, Object> datosPdf = new HashMap<>();
	    datosPdf.put("productos", productoSession);
	    datosPdf.put("nombreUsuario", nombreUsuario);
	    
	    ByteArrayInputStream pdfBytes = pdfService.generarPdfDeHtml("template_pdf", datosPdf);
		
		HttpHeaders httpHeaders = new HttpHeaders();	
		httpHeaders.add("Content-Disposition", "inline; filename=productos.pdf");
		
		return ResponseEntity.ok()
				.headers(httpHeaders)
				.contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(pdfBytes));

	}

}

package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.service.UsuarioService;

import jakarta.servlet.http.HttpSession;

@Controller
public class ProductoController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping("/menu")
	public String mostrarMenu(HttpSession session, Model modell) {
		if (session.getAttribute("usuario")==null) {
			return "redirect:/";
		}
		return "menu";
	}

}

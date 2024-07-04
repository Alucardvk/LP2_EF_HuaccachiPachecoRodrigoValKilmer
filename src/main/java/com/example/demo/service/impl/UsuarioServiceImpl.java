package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.UsuarioEntity;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.service.UsuarioService;
import com.example.demo.util.Utilitarios;

import jakarta.servlet.http.HttpSession;

@Service
public class UsuarioServiceImpl implements UsuarioService{

	@Autowired
	private UsuarioRepository usuarioRepository;
	@Override
	public void crearUsuario(UsuarioEntity usuarioEntity, Model model, MultipartFile foto) {
		// TODO Auto-generated method stub
		String nombreFoto= Utilitarios.guardarImagen(foto);
		usuarioEntity.setUrlImagen(nombreFoto);
		
		String passwordHash= Utilitarios.extraerHash(usuarioEntity.getPassword());
		usuarioEntity.setPassword(passwordHash);
		
		usuarioRepository.save(usuarioEntity);
		
		model.addAttribute("registroExitoso", "Registro Exitoso" );
		model.addAttribute("usuario", new UsuarioEntity());
		
	}

	@Override
	public boolean validarUsuario(UsuarioEntity usuarioEntity, HttpSession session) {
		// TODO Auto-generated method stub
		UsuarioEntity usuarioEncontradoPorCorreo =
				usuarioRepository.findByCorreo(usuarioEntity.getCorreo());
		if (usuarioEncontradoPorCorreo == null) {
			return false;
		}if (!Utilitarios.checkPassword(usuarioEntity.getPassword(), usuarioEncontradoPorCorreo.getPassword())) {
			return false;
		}
		session.setAttribute("usuario",usuarioEncontradoPorCorreo.getNombre());
		
		return false;
	}

	@Override
	public UsuarioEntity buscarUsuarioPorCorreo(String correo) {
		// TODO Auto-generated method stub
		return null;
	}

}

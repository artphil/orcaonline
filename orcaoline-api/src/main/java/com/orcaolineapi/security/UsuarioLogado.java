package com.orcaolineapi.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.orcaolineapi.modelo.usuario.Usuario;
import com.orcaolineapi.repository.usuario.UsuarioRepository;

@Service
public class UsuarioLogado {

	@Autowired
	private UsuarioRepository usuarioRepository;

	public Usuario usuarioLogado() {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(email);
		Usuario usuario = usuarioOptional.orElseThrow(() -> new UsernameNotFoundException("Usuário e/ou senha inválidos"));
		return usuario;
	}
}

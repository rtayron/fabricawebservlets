package br.com.fabricadeprogramador.fabricaweb.persistencia.jdbc;

import br.com.fabricadeprogramador.fabricaweb.persistencia.entidade.Usuario;

public class AlterarUsuarioDAOTest {

	public static void main(String[] args) {
		
		Usuario usuario = new Usuario();
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		
		usuario.setNome("Seu Madruga");
		usuario.setLogin("madruga");
		usuario.setSenha("senha");
		usuario.setId(1L);
		
		usuarioDAO.alterar(usuario);
	}
	
}

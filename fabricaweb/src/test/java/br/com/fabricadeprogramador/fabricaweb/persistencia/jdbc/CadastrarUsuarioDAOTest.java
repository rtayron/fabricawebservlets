package br.com.fabricadeprogramador.fabricaweb.persistencia.jdbc;

import br.com.fabricadeprogramador.fabricaweb.persistencia.entidade.Usuario;

public class CadastrarUsuarioDAOTest {

	public static void main(String[] args) {
		Usuario usuario = new Usuario();
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		
		usuario.setNome("Chiquinha");
		usuario.setLogin("chiquinha");
		usuario.setSenha("filhadomadruga");

		usuario.setId(430L);
		
		usuarioDAO.cadastrar(usuario);
	}
}

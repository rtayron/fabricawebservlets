package br.com.fabricadeprogramador.fabricaweb.persistencia.jdbc;

import java.util.List;

import br.com.fabricadeprogramador.fabricaweb.persistencia.entidade.Usuario;

public class BuscaTodosUsuarioTest {

	public static void main(String[] args) {
		
		UsuarioDAO usuarioDao = new UsuarioDAO();
		
		List<Usuario> usuarios = usuarioDao.buscaTodos();
		
		for (Usuario usuario : usuarios) {
			System.out.println(usuario.getNome());
		}
	}
}

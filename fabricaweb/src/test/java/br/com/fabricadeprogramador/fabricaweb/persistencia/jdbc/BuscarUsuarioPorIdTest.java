package br.com.fabricadeprogramador.fabricaweb.persistencia.jdbc;

import br.com.fabricadeprogramador.fabricaweb.persistencia.entidade.Usuario;

public class BuscarUsuarioPorIdTest {

	public static void main(String[] args) {
		UsuarioDAO usuarioDao = new UsuarioDAO();
		
		long id = 1L;
		Usuario usuario = usuarioDao.buscarPorId(id);
		if(usuario != null){
			System.out.println(usuario.getNome());
			System.out.println(usuario.getLogin());
		}else{
			System.out.println("O usuário com o id: " + id + " não existe no sistema");
		}
	}
}

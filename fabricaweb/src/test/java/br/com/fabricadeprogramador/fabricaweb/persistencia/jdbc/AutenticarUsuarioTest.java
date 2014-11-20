package br.com.fabricadeprogramador.fabricaweb.persistencia.jdbc;

import br.com.fabricadeprogramador.fabricaweb.persistencia.entidade.Usuario;

public class AutenticarUsuarioTest {

	public static void main(String[] args) {
		UsuarioDAO usuarioDao = new UsuarioDAO();
		Usuario madruga = new Usuario();
		
		madruga.setLogin("madruga");
		madruga.setSenha("senha1");
		Usuario autenticado = usuarioDao.autenticar(madruga);
		
		if(autenticado != null){
			System.out.println("Autenticado ! :-)");
		}else{
			System.out.println("Usuario inexistente :( " + madruga.getLogin() + " | " + madruga.getSenha());
		}
		
	}
}

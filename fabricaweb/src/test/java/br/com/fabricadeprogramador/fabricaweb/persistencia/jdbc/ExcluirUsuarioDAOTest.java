package br.com.fabricadeprogramador.fabricaweb.persistencia.jdbc;

public class ExcluirUsuarioDAOTest {

	public static void main(String[] args) {
		
		UsuarioDAO usuarioDao = new UsuarioDAO();
		usuarioDao.excluir(4L);
	}
}

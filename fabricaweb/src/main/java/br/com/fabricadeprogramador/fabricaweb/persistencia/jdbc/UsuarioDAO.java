package br.com.fabricadeprogramador.fabricaweb.persistencia.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fabricadeprogramador.fabricaweb.persistencia.entidade.Usuario;

public class UsuarioDAO {

	private Connection conexao = Conexao.getConnection();

	public void cadastrar(Usuario usuario) {

		String sql = "INSERT INTO usuario (nome, login, senha) VALUES "
				+ "(?,?,md5(?))";
		try {
			PreparedStatement prepareStatement = conexao.prepareStatement(sql);

			prepareStatement.setString(1, usuario.getNome());
			prepareStatement.setString(2, usuario.getLogin());
			prepareStatement.setString(3, usuario.getSenha());

			prepareStatement.execute();

			prepareStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void alterar(Usuario usuario) {
		String sql = "UPDATE usuario SET nome = ?, login = ?, senha = md5(?) "
				+ " where id = ? ";

		try {
			PreparedStatement prepareStatement = conexao.prepareStatement(sql);
			prepareStatement.setString(1, usuario.getNome());
			prepareStatement.setString(2, usuario.getLogin());
			prepareStatement.setString(3, usuario.getSenha());
			prepareStatement.setLong(4, usuario.getId());

			prepareStatement.execute();
			prepareStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void excluir(Long id) {
		String sql = "delete from usuario where id = ?";
		try {
			PreparedStatement prepareStatement = conexao.prepareStatement(sql);
			prepareStatement.setLong(1, id);
			prepareStatement.executeUpdate();
			prepareStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Usuario buscarPorId(Long id){
		
		String sql = "select * from usuario where id = ?";
		Usuario usuario = null;
		try{
			PreparedStatement prepareStatement = conexao.prepareStatement(sql);
			prepareStatement.setLong(1, id);
			
			ResultSet resultado = prepareStatement.executeQuery();
			if(resultado.next()){
				usuario = new Usuario();
				usuario.setId(resultado.getLong("id"));
				usuario.setLogin(resultado.getString("login"));
				usuario.setNome(resultado.getString("nome"));
				usuario.setSenha(resultado.getString("senha"));
				
			}
			resultado.close();
			prepareStatement.close();
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		return usuario;
	}

	public void salvar(Usuario usuario) {

		if(usuario.getId() != null){
			alterar(usuario);
		}else{
			cadastrar(usuario);
		}
	}
	
	public List<Usuario> buscaTodos(){
		
		List<Usuario> usuarios = new ArrayList<Usuario>();
		String sql = "select * from usuario order by nome";
		try{
			PreparedStatement prepareStatement = conexao.prepareStatement(sql);
			ResultSet resultSet = prepareStatement.executeQuery();
			
			while (resultSet.next()) {
				Usuario usuario = new Usuario();
				usuario.setId(resultSet.getLong("id"));
				usuario.setLogin(resultSet.getString("login"));
				usuario.setNome(resultSet.getString("nome"));
				usuario.setSenha(resultSet.getString("senha"));
				
				usuarios.add(usuario);
			}
			prepareStatement.close();
			resultSet.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return usuarios;
	}
	
	/**
	 * METODO AUTENTICAR
	 * @param usuario - usuario a ser autenticado
	 * @return usuario autenticado se as credenciais informadas conicidire com as
	 * existentes no BD, <cod>null</code> caso não coicidir.
	 */
	public Usuario autenticar(Usuario usuario){
		Usuario usuarioAutenticado = null;
		String sql = "select * from usuario where login = ? and senha = md5(?)";
		try{
			PreparedStatement prepareStatement = conexao.prepareStatement(sql);
			prepareStatement.setString(1, usuario.getLogin());
			prepareStatement.setString(2, usuario.getSenha());
			ResultSet resultSet = prepareStatement.executeQuery();
			
			if(resultSet.next()){
				usuarioAutenticado = new Usuario();
				usuarioAutenticado.setId(resultSet.getLong("id"));
				usuarioAutenticado.setNome(resultSet.getString("nome"));
				usuarioAutenticado.setLogin(resultSet.getString("login"));
				usuarioAutenticado.setSenha(resultSet.getString("senha"));
			}
			prepareStatement.close();
			resultSet.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return usuarioAutenticado;
	}
}

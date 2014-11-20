package br.com.fabricadeprogramador.fabricaweb.persistencia.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

	public static Connection getConnection(){
		
		Connection conexao = null;
		
		try {
			Class.forName("org.postgresql.Driver");
			conexao = DriverManager.getConnection( "jdbc:postgresql://localhost:5432/fabricaweb_db", "postgres", "postgres");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return conexao;
	}
}

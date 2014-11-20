package br.com.fabricadeprogramador.fabricaweb.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fabricadeprogramador.fabricaweb.persistencia.entidade.Usuario;
import br.com.fabricadeprogramador.fabricaweb.persistencia.jdbc.UsuarioDAO;

/**
 * Servlet implementation class UsuarioController
 */
@WebServlet("/usuarioController.do")
public class UsuarioController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsuarioController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String acao = request.getParameter("acao");
		
		if("listar".equalsIgnoreCase(acao)){
			UsuarioDAO usuarioDao = new UsuarioDAO();
			
			List<Usuario> listaUsuario = usuarioDao.buscaTodos();
			request.setAttribute("lista", listaUsuario);
			request.getRequestDispatcher("WEB-INF/lista-usuarios.jsp").forward(request, response);
			
		}else if("editar".equalsIgnoreCase(acao)){
			UsuarioDAO usuarioDao = new UsuarioDAO();
			Long id = Long.valueOf(request.getParameter("id"));
			Usuario usuario = usuarioDao.buscarPorId(id);

			request.setAttribute("usuario", usuario);
			request.getRequestDispatcher("WEB-INF/usuario.jsp").forward(request, response);
		
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String acao = request.getParameter("acao");
		
		if("remover".equalsIgnoreCase(acao)){
			
			UsuarioDAO usuarioDao = new UsuarioDAO();
			String[] ids = request.getParameterValues("ids");
			if(ids != null){
				for (String id : ids) {
					usuarioDao.excluir(Long.valueOf(id));
				}
			
				enviaAlerta("Usuairo(s) removido(s) com sucesso", response);
			}
		}else if("cadastrar".equalsIgnoreCase(acao)){
			Long id = null;
			String stringId = request.getParameter("id");
			if(!stringId.isEmpty()){
				id = Long.valueOf(stringId);
			}
			
			String nome = request.getParameter("nome");
			String login = request.getParameter("login");
			String senha = request.getParameter("senha");
			
			Usuario usuario = new Usuario(id, nome, login, senha);
			UsuarioDAO usuarioDao = new UsuarioDAO();
			usuarioDao.salvar(usuario);
			
			enviaAlerta("Usuairo salvo com sucesso", response);
			
		}else if("novo".equalsIgnoreCase(acao)){
			request.getRequestDispatcher("WEB-INF/usuario.jsp").forward(request, response);
		}
	}
	
	private void enviaAlerta(String mensagem, HttpServletResponse response) throws IOException{
		response.getWriter().print("<script>alert('" + mensagem + "'); "
				+ "location.href='usuarioController.do?acao=listar' </script>");
	}

}

package br.com.fabricadeprogramador.fabricaweb.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.fabricadeprogramador.fabricaweb.persistencia.entidade.Usuario;
import br.com.fabricadeprogramador.fabricaweb.persistencia.jdbc.UsuarioDAO;

/**
 * Servlet implementation class AutenticarController
 */
@WebServlet("/autenticarController.do")
public class AutenticarController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String USUARIO_LOGADO = "usuarioLogado";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AutenticarController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//passando o false ou true indica se a sessão for nula cria ou não nova sessão padrão é sempre true
		HttpSession session = request.getSession(false);
		
		if (session != null) {
			session.invalidate();
		}
		response.sendRedirect("login.html");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	 	
		//para pegar o formulario digitado na pagina web
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		
		Usuario usuario = new Usuario();
		usuario.setLogin(login);
		usuario.setSenha(senha);
		
		
	 UsuarioDAO usuarioDao = new UsuarioDAO();
	 
	 	
	 	
		Usuario usuarioAutenticado = usuarioDao.autenticar(usuario);
		
		if (usuarioAutenticado != null) {
			//autenticado o usuario é interessante abrir o HTTPSession
			//5 cria sessão
			HttpSession sessao = request.getSession();
			//6 adiciona o objeto como atributo na sessão
			sessao.setAttribute(USUARIO_LOGADO, usuarioAutenticado);
			// Define um tempo para sessão expirar
			sessao.setMaxInactiveInterval(3000);
			//adiciona o request DATA
			request.setAttribute("data",new Date() );
			//7 Encaminha para a tela de bem vindo
			request.getRequestDispatcher("WEB-INF/index.jsp")
							.forward(request, response);
			
			
			
			
		} else {
			String mensagem = "Credenciais Inválidas!";
			response.getWriter().print("<script>alert('" + mensagem + "'); "
					+ "location.href='login.html' </script>");
		}
	}

}

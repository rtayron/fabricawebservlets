package br.com.fabricadeprogramador.fabricaweb.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Index2Controller
 */
@WebServlet("/homeController.do")
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//		-- com JavaScript
//		String html = "<!DOCTYPE html> <html><head><meta charset=\"ISO-8859-1\"><title>Insert title here</title></head><body><h2>Bem vindo ao Curso de Web Fábrica de "
//				+ "Programador</h2>	Hoje é " + new Date().toString() + "</body></html>";
//		response.getWriter().print(html);
		
//		-- com JSTL
		request.setAttribute("data", new Date().toString());
		request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}

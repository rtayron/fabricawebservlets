package br.com.fabricadeprogramador.fabricaweb.filter;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.fabricadeprogramador.fabricaweb.controller.AutenticarController;

/**
 * Servlet Filter implementation class FiltroAutenticacao
 */
@WebFilter(dispatcherTypes = { 
		DispatcherType.REQUEST, 
		DispatcherType.FORWARD,
		DispatcherType.INCLUDE, 
		DispatcherType.ERROR }, 
		urlPatterns = { "/*" })
public class FiltroAutenticacao implements Filter {

	/**
	 * Default constructor.
	 */
	public FiltroAutenticacao() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// Casting do HttpServeit Request
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;

		String url = httpServletRequest.getRequestURI();  //Retorna o link que está sendo solicitado
		HttpSession session = httpServletRequest.getSession(); //retorna sessão, se existe uma sessão
		
		if (session.getAttribute(AutenticarController.USUARIO_LOGADO) != null
				|| url.lastIndexOf("login.html") > -1   //verifica a ultima url que está sendo acessada
				|| url.lastIndexOf("autenticarController.do") > -1) { //se 
			chain.doFilter(request, response); // permite o fluxo da resquisição
			
		} else {
			// redireciona para o login
			HttpServletResponse httpServletResponse = (HttpServletResponse) response;
			httpServletResponse.sendRedirect("login.html");
			
			//outro jeito de fazer o sendRedirect
//			((HttpServletResponse) response).sendRedirect("login.html");
			
		}

		// pass the request the filter chain
	}

	/**
	 * location
	 * 
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}

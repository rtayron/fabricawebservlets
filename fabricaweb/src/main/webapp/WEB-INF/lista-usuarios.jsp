 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>FábricaWeb - Lista de Usuários</title>

<script type="text/javascript">

	//1
	/*
	document.forms[0].acao.value = 'remover';
	document.forms[0].submit();
	
	2
	document.listaUsuarios.acao.value = 'remover';
	document.listaUsuarios.submit();
	
	3
	var form = document.getElementById('listaUsuarios')
	form.acao.value = 'remover'
	form.submit()
	*/
function remover(){
	
	if(confirm('Deseja realmente remover este usuário?')){
		
		setaAcao('remover')
	}
}

function novo(){
	setaAcao('novo')
}

function setaAcao(acao){
	document.listaUsuarios.acao.value = acao;
	document.listaUsuarios.submit();	
}

</script>

</head>
<body>

	<%@include file="includes/menu.jsp" %>
	
	<form id="listaUsuarios" name="listaUsuarios" method="post" action="usuarioController.do">
		<input type="hidden" name="acao" value="remover"/>
		
		<table border="1">
			<thead>
				<tr>
					<th>Id</th>
					<th>Nome</th>
					<th>Login</th>
					<th>Ação</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${requestScope.lista}" var="usuario">
					<tr>
						<td>${usuario.id}</td>
						<td><c:out value="${usuario.nome}"></c:out></td>
						<td><c:out value="${usuario.login}"></c:out></td>
						<td>
							<input type="checkbox" name="ids" value="${usuario.id}" />
							<a href="usuarioController.do?acao=editar&id=${usuario.id}" >Editar</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<br/>
		<input type="button" value="Remover Selecionados" 
			onclick="remover();" />
		<input type="button" value="Novo" onclick="novo();">
	</form>

</body>
</html>
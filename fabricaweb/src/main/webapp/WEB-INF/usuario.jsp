<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>FábricaWeb - Usuário</title>

<script type="text/javascript">

function validar(){

	var mensagem = ''
	var nomeValido = validaCampo('nome')
	var loginValido = validaCampo('login')
	var senhaValida = validaCampo('senha')
	
	if(nomeValido == false){
		mensagem = 'O nome deve ser informado'
	}
	if(loginValido == false){
		mensagem += '\nO login deve ser informado'
	}
	if(senhaValida == false){
		mensagem += '\nA senha deve ser informada'
	}
	
	if(mensagem != ''){		
		alert(mensagem)
	}else{
		document.usuarioForm.submit()
	}
}

function validaCampo(id){

	var input = document.getElementById(id);
	if(input.value == ''){
		return false;
	}
	return true;
}

</script>

</head>
<body>

	<%@include file="includes/menu.jsp" %>
	<form name="usuarioForm" method="post" action="usuarioController.do">
		<input type="hidden" name="acao" value="cadastrar"/>
		<input type="hidden" name="id" value="${requestScope.usuario.id}"/>
		<label>Nome</label> <input type="text" id="nome" name="nome" value="${requestScope.usuario.nome}" />
		<label>Login</label> <input type="text" id="login" name="login"  value="${requestScope.usuario.login}"/>
		<label>Senha</label> <input type="password" id="senha" name="senha" />
		
		<input type="button"  value="Salvar" onclick="validar()"/>
	</form>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="s" uri="/struts-tags" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="<c:url value="style.css"/>" media="screen" />
<title>Sistema Editoriale Gruppo Blu</title>
</head>
<body>
<div>
<b><font color="#446f9e" size="2">
<fieldset><legend align="center">Inserire i campi dell'account che si vuole modificare</legend>
	<div id="insert">
	<s:form action="modificaAction">
		<!--<s:property value="#session['account1'].username" />-->
		<s:textfield name="username" label="Username" value="%{#session['account1'].username}" readonly="true" size="50"/>
		<s:password name="password" label="Password" value="%{#session['account1'].password}" disabled="false" size="50"/>
		<s:textfield name="nome" label="Nome" value="%{#session['account1'].nome}" disabled="false" size="50"/>
		<s:textfield name="cognome" label="Cognome" value="%{#session['account1'].cognome}" disabled="false" size="50"/>
		<s:textfield name="sigla_giornalista" label="Sigla Giornalista" value="%{#session['account1'].sigla_giornalista}" disabled="false" size="50"/>
		<s:textfield name="sigla_redazione" label="Sigla Redazione" value="%{#session['account1'].sigla_redazione}" disabled="false" size="50"/> 
<!-- 		<s:password name="password" label="Password" />
		<s:textfield name="nome" label="Nome" />
		<s:textfield name="cognome" label="Cognome" />
		<s:textfield name="sigla_giornalista" label="Sigla Giornalista" />
		<s:textfield name="sigla_redazione" label="Sigla Redazione" /> -->
		<s:submit value="Modifica" />
	</s:form>
	
	</div>	
</fieldset>
</font></b>
	 <span class="centro"> 
		<s:a href="ListaAction.action"> <img src="images/indietro.png" ></s:a>
	</span>	
	 <br>
	 <span class="testoCentro"> 
		<s:a href="ListaAction.action"> Torna alla Lista </s:a>
	 </span>
	
</div>
	
</body>
</html>
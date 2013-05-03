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
<fieldset><legend align="center">Inserire i campi dell'account che si vuole creare </legend>
	<div id="insert">
	<s:form action="InsertAction">
		<s:textfield name="userName" label="User Name" size="50"/>
		<s:password name="password" label="Password" size="50"/>
		<s:textfield name="nome" label="Nome" size="50" />
		<s:textfield name="cognome" label="Cognome" size="50"/>
		<s:textfield name="sigla_giornalista" label="Sigla Giornalista" size="50"/>
		<s:textfield name="sigla_redazione" label="Sigla Redazione" size="50"/>
		<s:submit value="Registra" />


	</s:form>
	</div>	
</fieldset>
</font></b>

</div>
</body>
</html>
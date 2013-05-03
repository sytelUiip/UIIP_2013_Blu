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
<fieldset><legend align="center">Inserire i campi della notizia che si vuole creare. </legend>
	<div align="left">
	<s:form action="InsertNewsAction.action">
		<s:textfield name="titolo" label="Titolo" size="100"/>
		<s:textfield name="sottotitolo" label="Sottotitolo" size="100"/>
		<s:textfield name="autore" label="Autore" value="%{#session['account'].username}" readonly="true" size="60"/>		
		<s:textarea cols="75" rows="15" name="testo" label="Testo" style="resize:none"/>
<%-- 		<s:textfield name="sigla_giornalista" label="Sigla Giornalista" />
		<s:textfield name="sigla_redazione" label="Sigla Redazione" />
 --%>		<s:submit value="Inserisci notizia" />


	</s:form>
	</div>	
</fieldset>
</font></b>

</div>
</body>
</html>
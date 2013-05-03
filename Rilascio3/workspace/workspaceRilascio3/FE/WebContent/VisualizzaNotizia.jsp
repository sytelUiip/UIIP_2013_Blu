<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="s" uri="/struts-tags" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="<c:url value="style.css"/>" media="screen" />
<title>Sistema Editoriale Gruppo Blu</title>
</head>
<body>
<b><font color="#446f9e" size="2">
<fieldset><legend align="center">Dettagli Notizia </legend>
	<div align="left">
	<s:form action="VisualizzaNewsAction">
		<s:textfield name="id_n" label="ID" value="%{#session['notizia'].id_N}" readonly="true" size="60"/>
		<s:textfield name="autore" label="Autore" value="%{#session['notizia'].autore}"readonly="true" size="60"/>
		<s:textfield name="datacreazione" label="DataCreazione" value="%{#session['notizia'].datacreazione}" readonly="true" size="60"/>
		<s:textfield name="lock_n" label="Lock" value="%{#session['notizia'].lock_n}" readonly="true" size="60"/>
		<s:textfield name="stato" label="Stato" value="%{#session['notizia'].stato}" readonly="true" size="60"/>
		<s:textfield name="ultimodigitatore" label="UltimoDigitatore" value="%{#session['notizia'].ultimodigitatore}" readonly="true" size="60"/>
		<s:textfield name="ultimoaccesso" label="UltimoAccesso" value="%{#session['account'].username}" readonly="true" size="60"/>
		<s:textfield name="titolo" label="Titolo" value="%{#session['notizia'].titolo}" readonly="true" size="60"/>	
		<s:textfield name="sottotitolo" label="Sottotitolo" value="%{#session['notizia'].sottotitolo}" readonly="true" size="60"/>		
		<s:textarea cols="75" rows="15" name="testo" label="Testo" value="%{#session['notizia'].testo}" readonly="true" style="resize:none"/>
		
	</s:form>
	</div>	
</fieldset>
</font></b>

	<span class="centro"> 
		<s:a href="ListaNewsAction.action"> <img src="images/indietro.png" ></s:a>
	</span>	
	 <br>
	 <span class="testoCentro"> 
		<s:a href="ListaNewsAction.action"> Torna alla Lista </s:a>
	 </span>
	
</div>
</body>
</html>
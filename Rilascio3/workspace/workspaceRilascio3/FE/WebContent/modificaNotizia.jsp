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
<fieldset><legend align="center">	Inserire i campi dell'account che si vuole modificare</legend>
	<div align="left">
	<s:form action="UpdateNewsAction">
 I campi colorati non sono modificabili
		<s:if test="%{#session.notizia.ultimoAccesso!=#session.account.username}"><div id="messaggio">La notizia è in fase di modifica da parte di un altro utente. Prova più tardi.</div></s:if>
		<s:else>
		<s:textfield name="id_n" label="ID" style="background-color: #66CCFF" value="%{#session['notizia'].id_N}" readonly="true" size="50"/>
		<s:textfield name="autore" label="Autore" style="background-color:  #66CCFF" value="%{#session['notizia'].autore}" readonly="true" size="50"/>
		<s:textfield name="datacreazione" label="DataCreazione" style="background-color: #66CCFF" value="%{#session['notizia'].datacreazione}" readonly="true" size="50"/>
		<s:textfield name="lock_n" label="Lock" style="background-color: #66CCFF" value="Y" readonly="true" size="50"/>
		<s:textfield name="stato" label="Stato" style="background-color: #66CCFF" value="%{#session['notizia'].stato}" readonly="true" size="50"/>
		<s:textfield name="ultimodigitatore" label="UltimoDigitatore" style="background-color:  #66CCFF" value="%{#session['notizia'].ultimodigitatore}" readonly="true" size="50"/>
		<s:textfield name="ultimoaccesso" label="UltimoAccesso" style="background-color:  #66CCFF" value="%{#session['account'].username}" readonly="true" size="50"/>
		<s:textfield name="titolo" label="Titolo" value="%{#session['notizia'].titolo}" disabled="false" size="50"/>		
		<s:textfield name="sottotitolo" label="Sottotitolo" value="%{#session['notizia'].sottotitolo}" disabled="false" size="50"/>		
		<s:textarea cols="75" rows="15" name="testo" label="Testo" value="%{#session['notizia'].testo}" disabled="false" style="resize:none"/>
		<s:submit value="Modifica" />
	
		<div class="destro" align="right">
		<s:a href="AnnullaModificheNotizia.action?idN=%{#session['notizia'].id_N}"><button>Rilascia</button></s:a>
		</div>
		</s:else> 

	</s:form>
	<br><br>
</fieldset>
</font></b>
	<span class="centro"> 
		<s:a href="ListaNewsAction.action?ultimoAccesso=%{#session['account'].username}"> <img src="images/indietro.png" ></s:a>
	</span>	
	 <br>
	 <span class="testoCentro"> 
		<s:a href="ListaNewsAction.action?ultimoAccesso=%{#session['account'].username}"> Torna alla Lista </s:a>
	 </span>
	
</div>
</body>
</html>
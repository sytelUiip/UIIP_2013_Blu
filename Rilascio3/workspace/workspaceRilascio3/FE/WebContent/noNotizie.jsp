<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div id="messaggio"> 
Non ci sono notizie da visualizzare
</div>	
<span class="centro"> 
		<s:a href="ListaNewsAction.action?ultimoAccesso=%{#session['account'].username}"> <img src="images/indietro.png" ></s:a>
	</span>	

	 <br>
	 <span class="testoCentro"> 
		<s:a href="ListaNewsAction.action?ultimoAccesso=%{#session['account'].username}"> Torna alla Lista </s:a>
	 </span>
<br>

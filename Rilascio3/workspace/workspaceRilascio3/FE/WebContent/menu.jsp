<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="s" uri="/struts-tags" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<nav id="menu">
	<div>
		<div class="menu">
		<%
		String stVariable="";
		if(session.getAttribute("tipo").equals("amministratore"))
		{
			stVariable="a";
		}
		else if(session.getAttribute("tipo").equals("giornalista")){
			stVariable="g";
		}
		else stVariable="c";
		%>
		 <s:if test="%{#session.tipo=='amministratore'}"> 
		 <img id="imm_menu" src="images/news222.png" width="80%" >
		 <span class="textmenu">Menu Operazioni</span>
			<ul>Amministratore		
			<li><s:a href="ListaAction.action">Lista Account</s:a></li>
			<li><s:a href="OpenInsertAction.action">Crea Account</s:a></li>
			</ul>
		 </s:if>
		 <s:elseif test="%{#session.tipo=='giornalista'}"> 
		 	<img id="imm_menu" src="images/news222.png" width="80%" >
			<span class="textmenu">Menu Operazioni</span>
			<ul>Giornalista		
			<li><s:a href="ListaNewsAction.action">Lista Notizia</s:a></li>
			<li><s:a href="OpenInsertNewsAction.action">Crea Notizia</s:a></li>
			</ul>
		 </s:elseif>
		 <s:else> 
		 	<img id="imm_menu" src="images/news222.png" width="80%" >
			<span class="textmenu">Menu Operazioni</span>
			<ul>Amministratore		
			<li><s:a href="ListaAction.action">Lista Account</s:a></li>
			<li><s:a href="OpenInsertAction.action">Crea Account</s:a></li>
			</ul>
			<ul>Giornalista		
			<li><s:a href="ListaNewsAction.action">Lista Notizie</s:a></li>
			<li><s:a href="OpenInsertNewsAction.action">Crea Notizia</s:a></li>
			</ul>
		 </s:else>
		 </div>
	</div>
</nav>
<div id="side-bar">		
<div class="lighterBackground">
    <p class="sideBarTitle">Informazioni</p>
    <span class="sideBarText">
    Gruppo Blu  <br/>
    UIIP - Biogem  <br/>
    Ariano Irpino (AV)  <br/>
    e-mail:gruppoblu@gmail.com <br/>
    </span>
</div>
</div>

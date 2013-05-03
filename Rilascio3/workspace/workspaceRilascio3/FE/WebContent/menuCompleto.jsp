<%@ taglib prefix="s" uri="/struts-tags" %>
<nav id="mainnav">
	<div id="menu">
		<span id="textmenu">Menu Operazioni</span>
		<ul>Amministratore		
		<li><s:a href="ListaAction.action">Lista Account</s:a></li>
		<li><s:a href="OpenInsertAction.action">Crea Account</s:a></li>
			Giornalista
		<li><s:a href="listaNotiziaAction" >Lista Notizie</s:a></li>
		<li><s:a href="CreaNotiziaAction"  >Crea Notizia</s:a></li>
		</ul>
	</div>
</nav>

<div id="side-bar">		
<div class="lighterBackground">
    <p class="sideBarTitle">Informazioni</p>
    <span class="sideBarText">
    Gruppo Blu  <br/>
    UIIP - Biogem  <br/>
    Ariano Irpino (AV)  <br/>
    e-mail:gruppoblu2013uiip@gmail.com <br/>
    </span>
</div>
</div>
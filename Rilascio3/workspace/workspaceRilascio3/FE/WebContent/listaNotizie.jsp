<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

<html>
<head>
<link rel="stylesheet" type="text/css" href="style.css" media="screen" />
</head>
<body>
<h1><font color="#446f9e">Lista Notizie - Sistema Editoriale Gruppo Blu</font></h1>
<table><tr><td>
<table>
		<form action="ListaNewsAction.action" method="post">
			<tr>
				<td colspan="2">
				<select name="ricerca" id="filtro_ricerca" style="width:250px">
						<option value="filtro">Filtro Ricerca</option>
						<option value="titolo">Titolo</option>
						<option value="autore">Autore</option>
						<option value="stato">Stato</option>
				</select>
			<tr>
				<td>Inserisci ricerca
				<td><input type="text" name="parametro" />
			<tr>
				<td><input type="submit" value="Cerca" /></td>
				<td><input type="reset" value="Annulla" /></td>
			</tr>
		</form>
</table>
</td><td>
<div class="legenda">
	<table>
	<tr><th><font color="#446f9e">Se cerchi una notizia</font></th><th><font color="#446f9e">Stato:</font></th></tr></font>
	<tr><td>Editabile</td><td>S</td></tr>
	<tr><td>Trasmessa</td><td>T</td></tr>
	<tr><td>In coda per essere trasmessa</td><td>Q</tr>
	<tr><td>Cancellata</td><td>C</tr>
	</table>
</div>	
</td></tr></table>
<br><br>
	<display:table class="color" id="row" name="sessionScope.listaNews" pagesize="5" requestURI="ListaNewsAction.action" requestURIcontext="true">
		<display:column property="id_N" title="id" media="none"/>
		<display:column property="titolo" title="titolo" />
		<display:column property="autore" title="autore" />
		<display:column property="datacreazione" title="datacreazione" media="none"/>
		<display:column property="datatrasmissione" title="datatrasmissione" media="none" />
		<display:column property="stato" title="stato" />
		<display:column property="lock_n" title="lock_n" media="none" />
		<display:column property="ultimodigitatore" title="ultimodigitatore" media="none"/>
		<display:column property="ultimoAccesso" title="ultimoaccesso" media="none"/>
		
		<display:column title="Lock">
			<s:if test='#attr.row.lock_n=="Y"'>
				<img src="images/lock.png" title="Notizia bloccata">
			</s:if>
			<s:if test='#attr.row.lock_n!="Y"'>
				<img src="images/rilascia.png" title="Notizia Modificabile">
		
			</s:if>
		</display:column>	
		
		
		
		
		<display:column title="Visualizza">
			<s:url id="visualizza" action="VisualizzaNewsAction.action">
			<s:param name="idN" value="#attr.row.id_N"></s:param>
				<s:param name="titolo" value="#attr.row.titolo"></s:param>
				<s:param name="autore" value="#attr.row.autore"></s:param>
				<s:param name="dataCreazione" value="#attr.row.datacreazione"></s:param>
				<s:param name="dataTrasmissione" value="#attr.row.datatrasmissione"></s:param>
				<s:param name="stato" value="#attr.row.stato"></s:param>
				<s:param name="lock_n" value="#attr.row.lock_n"></s:param>
				<s:param name="ultimoDigitatore" value="#attr.row.ultimodigitatore"></s:param>
				<%-- <s:param name="testo" value="%{#session['listaNews'].testo}"></s:param> --%>
			</s:url>
			<s:a href="%{visualizza}"><img src="images/visualizza2.png" class="image"></s:a>
		</display:column>
		<display:column title="Modifica">
		<s:if test='%{#attr.row.stato=="S"&#attr.row.lock_n=="N"}'> 
			<s:url id="modifica" action="OpenUpdateNewsAction.action">
				<s:param name="idN" value="#attr.row.id_N"></s:param>
				<s:param name="titolo" value="#attr.row.titolo"></s:param>
				<s:param name="sottotitolo" value="#attr.row.sottotitolo"></s:param>
				<s:param name="testo" value="#attr.row.testo"></s:param>
				<s:param name="autore" value="#attr.row.autore"></s:param>
				<s:param name="dataCreazione" value="#attr.row.datacreazione"></s:param>
				<s:param name="dataTrasmissione" value="#attr.row.datatrasmissione"></s:param>
				<s:param name="stato" value="#attr.row.stato"></s:param>
				<s:param name="lock_n" value="#attr.row.lock_n"></s:param>
				<s:param name="ultimoDigitatore" value="#attr.row.ultimodigitatore"></s:param>
				<s:param name="ultimoAccesso" value="#attr.row.ultimoAccesso"></s:param>
			</s:url>
				<s:a href="%{modifica}"><img src="images/modifica2.png" class="image"></s:a>
		</s:if>
 		<s:elseif test='%{#attr.row.stato=="S"&#attr.row.lock_n=="Y"}'>
 			<s:if test='%{#attr.row.ultimoAccesso==#session["account2"].username}'>
				<s:url id="modifica" action="OpenUpdateNewsAction.action">
					<s:param name="idN" value="#attr.row.id_N"></s:param>
					<s:param name="titolo" value="#attr.row.titolo"></s:param>
					<s:param name="sottotitolo" value="#attr.row.sottotitolo"></s:param>
					<s:param name="testo" value="#attr.row.testo"></s:param>
					<s:param name="autore" value="#attr.row.autore"></s:param>
					<s:param name="dataCreazione" value="#attr.row.datacreazione"></s:param>
					<s:param name="dataTrasmissione" value="#attr.row.datatrasmissione"></s:param>
					<s:param name="stato" value="#attr.row.stato"></s:param>
					<s:param name="lock_n" value="#attr.row.lock_n"></s:param>
					<s:param name="ultimoDigitatore" value="#attr.row.ultimodigitatore"></s:param>
					<s:param name="ultimoAccesso" value="#attr.row.ultimoAccesso"></s:param>
				</s:url>	
				<s:a href="%{modifica}"><img src="images/modifica2.png" class="image"></s:a> 
			</s:if>
		 </s:elseif>
		 
		</display:column>	
		
		<display:column title="Elimina" >
			<s:if test='%{#attr.row.stato!="C"&#attr.row.lock_n=="N"}'>
				<s:url id="delete" action="DeleteNewsAction.action">
					<s:param name="idN" value="#attr.row.id_N"></s:param>
					<s:param name="titolo" value="#attr.row.titolo"></s:param>
					<s:param name="autore" value="#attr.row.autore"></s:param>
					<s:param name="datacreazione" value="#attr.row.datacreazione"></s:param>
					<s:param name="datatrasmissione" value="#attr.row.datatrasmissione"></s:param>
					<s:param name="stato" value="#attr.row.stato"></s:param>
					<s:param name="lock_n" value="#attr.row.lock_n"></s:param>
					<s:param name="ultimodigitatore" value="#attr.row.ultimodigitatore"></s:param>
					<s:param name="ultimoAccesso" value="#attr.row.ultimoAccesso"></s:param>
				</s:url>
				<s:a href="%{delete}"><img src="images/elimina.png" class="image"></s:a>
			</s:if>
			<s:elseif test='%{#attr.row.stato!="C"&#attr.row.lock_n=="Y"}'>
				<s:if test='%{#attr.row.ultimoAccesso==#session["account2"].username}'>
					<s:url id="delete" action="DeleteNewsAction.action">
						<s:param name="idN" value="#attr.row.id_N"></s:param>
						<s:param name="titolo" value="#attr.row.titolo"></s:param>
						<s:param name="autore" value="#attr.row.autore"></s:param>
						<s:param name="datacreazione" value="#attr.row.datacreazione"></s:param>
						<s:param name="datatrasmissione" value="#attr.row.datatrasmissione"></s:param>
						<s:param name="stato" value="#attr.row.stato"></s:param>
						<s:param name="lock_n" value="#attr.row.lock_n"></s:param>
						<s:param name="ultimodigitatore" value="#attr.row.ultimodigitatore"></s:param>
						<s:param name="ultimoAccesso" value="#attr.row.ultimoAccesso"></s:param>
					</s:url>
				<s:a href="%{delete}"><img src="images/elimina.png" class="image"></s:a>
				</s:if>				
			</s:elseif>	
		</display:column>
		
		
	<display:column title="Trasmetti">
		<s:if test='%{#attr.row.stato=="S"&#attr.row.lock_n=="N"}'>
			<s:url id="trasmetti" action="TrasmettiNewsAction.action">
			<s:param name="idN" value="#attr.row.id_N"></s:param>
				<s:param name="titolo" value="#attr.row.titolo"></s:param>
				<s:param name="autore" value="#attr.row.autore"></s:param>
				<s:param name="datacreazione" value="#attr.row.datacreazione"></s:param>
				<s:param name="datatrasmissione" value="#attr.row.datatrasmissione"></s:param>
				<s:param name="stato" value="#attr.row.stato"></s:param>
				<s:param name="lock_n" value="#attr.row.lock_n"></s:param>
				<s:param name="ultimodigitatore" value="#attr.row.ultimodigitatore"></s:param>
				<s:param name="ultimoAccesso" value="#attr.row.ultimoAccesso"></s:param>
			</s:url>
			<s:a href="%{trasmetti}"><img src="images/trasmetti.png" class="image"></s:a>	
		</s:if>
		<s:elseif test='%{#attr.row.stato=="S"&#attr.row.lock_n=="Y"}'>
			<s:if test='%{#attr.row.ultimoAccesso==#session["account2"].username}'>
				<s:url id="trasmetti" action="TrasmettiNewsAction.action">
				<s:param name="idN" value="#attr.row.id_N"></s:param>
					<s:param name="titolo" value="#attr.row.titolo"></s:param>
					<s:param name="autore" value="#attr.row.autore"></s:param>
					<s:param name="datacreazione" value="#attr.row.datacreazione"></s:param>
					<s:param name="datatrasmissione" value="#attr.row.datatrasmissione"></s:param>
					<s:param name="stato" value="#attr.row.stato"></s:param>
					<s:param name="lock_n" value="#attr.row.lock_n"></s:param>
					<s:param name="ultimodigitatore" value="#attr.row.ultimodigitatore"></s:param>
					<s:param name="ultimoAccesso" value="#attr.row.ultimoAccesso"></s:param>
				</s:url>
				<s:a href="%{trasmetti}"><img src="images/trasmetti.png" class="image"></s:a>	
			</s:if>
		</s:elseif>	
	
	</display:column>	
</display:table>
</div>
	<form action="ListaNewsAction.action">
	<s:if test="%{#session.offset!=#session.numPagine}">
		<input type="submit" value="Avanti" name="Avanti"/>
	</s:if>
	<s:if test="%{#session.offset!=1}">
		<input type="submit" value="Indietro" name="Indietro"/>
	</s:if>
	</form>
<span class="centro">	
<s:a href="RilasciaNotizieAction?ultimoAccesso=%{#session['account2'].username}"><img src="images/rilascia.png"></s:a><br>
<s:a href="RilasciaNotizieAction?ultimoAccesso=%{#session['account2'].username}">Rilascia</s:a>	
</span>
</body>
</html>
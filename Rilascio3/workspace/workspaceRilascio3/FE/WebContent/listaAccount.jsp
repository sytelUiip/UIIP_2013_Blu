<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<html>
<body>
	<%--  <table border="1" bordercolor="blue" align="center" id="color">
 		<tr font-color="1c5f75" align="left"">
			<th>Nome</th>
			<th>Cognome</th>
			<th>Stato</th>
			<th>Visualizza</th>
			<th>Modifica</th>
			<th>Cancella</th>
		</tr>
		<s:iterator value="#session['listaAccount']" var="account1">
			<tr align="left">
				<td><s:property value="%{#account1.nome}" /></td>
				<td><s:property value="%{#account1.cognome}" /></td>
				<td><s:property value="%{#account1.stato}" /></td>
				<td><s:a
						href="OpenVisualizzaAction.action?username=%{#account1.username}&password=%{#account1.password}&nome=%{#account1.nome}&cognome=%{#account1.cognome}&sigla_giornalista=%{#account1.sigla_giornalista}&sigla_redazione=%{#account1.sigla_redazione}"><img src="images/visualizza2.jpg" class="image"></s:a>
				<td><s:a
						href="OpenUpdateAction.action?username=%{#account1.username}&password=%{#account1.password}&nome=%{#account1.nome}&cognome=%{#account1.cognome}&sigla_giornalista=%{#account1.sigla_giornalista}&sigla_redazione=%{#account1.sigla_redazione}"><img src="images/modifica2.jpg" class="image"></s:a>
				<td><s:a
						href="DeleteAction.action?username=%{#account1.username}&password=%{#account1.password}&nome=%{#account1.nome}&cognome=%{#account1.cognome}&sigla_giornalista=%{#account1.sigla_giornalista}&sigla_redazione=%{#account1.sigla_redazione}"><img src="images/elimina.jpg" class="image"></s:a>

				</td>
			</tr> 
		</s:iterator>	
				   <td>
	      <s:iterator value="%{#account.gruppi}" var="gruppo">  
	         <s:property value="%{#gruppo.nomeGruppo}" />
	      </s:iterator>
	   </td>
	</table> --%>
	<font color="#446f9e"><h2>
			<center>Lista degli account del sistema Gruppo Blu</center>
		</h2>
	</font>
	<div id="liste">
		<display:table class="color" id="row" name="sessionScope.listaAccount"
			pagesize="10" requestURI="ListaAction.action"
			requestURIcontext="true">
			<display:column property="username" title="username" media="none" />
			<display:column property="password" title="password" media="none" />
			<display:column property="nome" title="Nome" />
			<display:column property="cognome" title="Cognome" />
			<display:column property="sigla_giornalista"
				title="sigla_giornalista" media="none" />
			<display:column property="sigla_redazione" title="sigla_redazione"
				media="none" />
			<display:column property="stato" title="Stato" />
			<display:column title="Visualizza">
				<s:url id="visualizza" action="OpenVisualizzaAction.action">
					<s:param name="username" value="#attr.row.username"></s:param>
					<s:param name="password" value="#attr.row.password"></s:param>
					<s:param name="nome" value="#attr.row.nome"></s:param>
					<s:param name="cognome" value="#attr.row.cognome"></s:param>
					<s:param name="sigla_giornalista"
						value="#attr.row.sigla_giornalista"></s:param>
					<s:param name="sigla_redazione" value="#attr.row.sigla_redazione"></s:param>
				</s:url>
				<s:a href="%{visualizza}">
					<img src="images/visualizza2.png" class="image">
				</s:a>
			</display:column>
			<display:column title="Modifica">
				<s:url id="modifica" action="OpenUpdateAction.action">
					<s:param name="username" value="#attr.row.username"></s:param>
					<s:param name="password" value="#attr.row.password"></s:param>
					<s:param name="nome" value="#attr.row.nome"></s:param>
					<s:param name="cognome" value="#attr.row.cognome"></s:param>
					<s:param name="sigla_giornalista"
						value="#attr.row.sigla_giornalista"></s:param>
					<s:param name="sigla_redazione" value="#attr.row.sigla_redazione"></s:param>
				</s:url>
				<s:a href="%{modifica}">
					<img src="images/modifica2.png" class="image">
				</s:a>
			</display:column>
			<display:column title="Elimina">
				<s:url id="delete" action="DeleteAction.action">
					<s:param name="username" value="#attr.row.username"></s:param>
					<s:param name="password" value="#attr.row.password"></s:param>
					<s:param name="nome" value="#attr.row.nome"></s:param>
					<s:param name="cognome" value="#attr.row.cognome"></s:param>
					<s:param name="sigla_giornalista"
						value="#attr.row.sigla_giornalista"></s:param>
					<s:param name="sigla_redazione" value="#attr.row.sigla_redazione"></s:param>
				</s:url>
				<s:a href="%{delete}">
					<img src="images/elimina.png" class="image">
				</s:a>
			</display:column>
		</display:table>
		<form action="ListaAction.action">
			<s:if test="%{#session.offset!=#session.numPagine}">
				<input type="submit" value="Avanti" name="Avanti" />
			</s:if>
			<s:if test="%{#session.offset!=1}">
				<input type="submit" value="Indietro" name="Indietro" />
			</s:if>
		</form>
	</div>
</body>
</html>

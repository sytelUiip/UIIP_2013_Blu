<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
    
<div id="messaggio"> 
Bentornato <s:property value="%{#session.account.nome}" /> 
</div>   
<br>
<div id="messaggio2"> 
<img src="images/penna.png">
<br><br>
"Disapprovo quello che dite ma difender� fino alla morte il vostro diritto di dirlo"<br>
<div class="right"><i>Voltaire</i></div>
<br>
</div>
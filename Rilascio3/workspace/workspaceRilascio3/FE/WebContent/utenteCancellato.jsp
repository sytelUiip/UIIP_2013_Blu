<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
                <%@ taglib prefix="s" uri="/struts-tags" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
<div id="messaggio"> 
Siamo spiacenti, il suo account è stato disattivato.
</div>
	<span class="centro"> 
		<s:a href="ListaAction.action"> <img src="images/indietro.png" ></s:a>
	</span>	
	 <br>
	 <span class="testoCentro"> 
		<s:a href="ListaAction.action"> Torna alla Lista </s:a>
	 </span>
</body>
</html>
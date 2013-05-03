<?xml version="1.0" encoding="UTF-8"?>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!doctype html>

<html><!--  lang="en-US"> -->
<head>
<meta charset="UTF-8" />
<title>Sistema Editoriale BLU</title>
<link href="styleTemplate.css" rel="stylesheet" type="text/css">
</head>

<body>
<div id="wrap">

<nav id="mainnav">

<h1 id="textlogo">
Sistema Editoriale <span>BLU</span>
</h1>
</nav>

<section id="content">
<header id="homeheader">
<h2>
<span>Inserisci i dati per accedere al sistema</span>
<s:if test="hasActionErrors()">
	<div>
		<s:actionerror/>
	</div>
</s:if>
<br>
<table id="login" >
<s:form action="controllaLoginAction.action" method="POST">
   <tr><td width="343"><s:textfield name="userName" label="Username"/></td></tr>
   <tr><td><s:password name="password" label="Password"/></td></tr>
   <tr><td><s:submit value="LOGIN" /></td></tr>
</s:form>
</table>
</h2>
</header>
<section id="messaggio"></section>
<section id="page">
<header class="mainheading">
<h2 class="introhead">Put an important introduction heading for your visitors to notice.</h2>
</header><%-- 
<section id="introcol">
<div class="leftcol">

<h3>What we do ?</h3>
<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec bibendum blandit faucibus. Morbi commodo vestibulum lectus fringilla porttitor. Aenean sodales congue feugiat. </p>
</div>

<div class="rightcol">
<h3>Why Choose Us ?</h3>
<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec bibendum blandit faucibus. Morbi commodo vestibulum lectus fringilla porttitor. Aenean sodales congue feugiat. </p>
</div>

<div class="midcol">
<h3>Our Process</h3>
<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec bibendum blandit faucibus. Morbi commodo vestibulum lectus fringilla porttitor. Aenean sodales congue feugiat. </p>
</div>
<div class="clear"></div>
</section>
<section id="fourcols">
<header class="mainheading">

<div class="clear"></div>
</section>
</section>
</section>
</div> --%>


<footer>
<div id="bottom">
<a href="#">Home</a> | <a href="#">About Us</a> | <a href="#">Contact Us</a> |<a href="#"> Support</a> | <a href="#">Products</a> | <a href="#">Services</a>
<div class="clear"></div>
</div>
</footer>

</body>
</html>

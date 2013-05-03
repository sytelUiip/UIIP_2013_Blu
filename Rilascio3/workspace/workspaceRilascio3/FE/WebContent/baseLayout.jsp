<?xml version="1.0" encoding="UTF-8"?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="style.css" rel="stylesheet" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><tiles:insertAttribute name="title"></tiles:insertAttribute></title>
</head>
<body>
	<div id="wrap">
		<div>
			<table cellpadding="2" cellspacing="2" align="center">
				<tr>
					<td height="30" colspan="2">
						<div id="header">
							<tiles:insertAttribute name="header" />
						</div>
					</td>
				</tr>
				<td height="500" width="17%">
					<div id="sinistro">
						<tiles:insertAttribute name="menu" />
					</div>
				</td>
				<td>
					<div id="destro">
						<tiles:insertAttribute name="body" />
					</div>
				</td>
				</tr>
				<tr>
					<td height="30" colspan="2">
								<tiles:insertAttribute name="footer" />	
					</td>
				</tr>
								
						
</div>
</body>
</html>
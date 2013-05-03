<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<div id="header">
    <div class="midHeader">
    <nav id="mainnav">
            	<img src="images/news2.jpg" width="100%" height="190px"> 
      <!-- <h1 class="headerTitle">Sistema Editoriale Gruppo Blu</h1>  -->
            <span id="textlogo">
						<font size="6px">Sistema Editoriale BLU
						</font>
			</span>
			</nav>
      
    </div>

     <div class="subHeader">
    <div id="logout">
    <s:a href="LogoutAction.action">Logout</s:a>
     </div>
    </div> 
</div> --%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<div id="header">
    <div class="midHeader">
    <nav id="mainnav">
            	 <img src="images/news2.jpg" width="100%">
	</nav>
      
    </div>
<div id="userConnesso">
<b>Utente Connesso <s:property value="%{#session.account.nome}" /></b>
</div>  
<div id="logout">
<table border="1" cellpadding="6" ><tr><td>
<s:a href="LogoutAction.action">Logout</s:a>    
</td></tr></table> 

</div>    
</div> 

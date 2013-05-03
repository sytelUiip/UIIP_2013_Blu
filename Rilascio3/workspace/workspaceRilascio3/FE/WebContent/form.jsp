<div id="login">
<h1>Login</h1>
	
		<fieldset>
		
		<s:form action="controllaLoginAction">
   	<table>
		<tr>
			<s:textfield name="userName" label="User Name" />
		</tr>	
		<tr>
			<s:password name="password" label="Password"/>
		</tr>
			<tr><td colspan="2" align="center">
				<c:if test="${esito==null or !esito}">
					<s:submit value="Login" />
					<input type="reset" value="Annulla" />
				</c:if>
				</td></tr>
			</table>
		</fieldset>	
		</s:form>
	
</div>		
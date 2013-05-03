package uiip.action;

import java.util.*;
import javax.servlet.http.HttpSession;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;


public class LogoutAction extends ActionSupport{
	
	public String execute() throws Exception{
		System.out.println("sono in logout primo");
		Map session = ActionContext.getContext().getSession();	
		session.remove("account");
		System.out.println("sono in logout!");
	return "success";
	}
}


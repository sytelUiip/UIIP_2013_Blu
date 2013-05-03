package uiip.action.news;

import java.util.Map;

import uiip.ws.LoginWSStub;
import uiip.ws.NewsWSStub;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class OpenInsertNewsAction extends ActionSupport {
	
	public String execute(){
		try{
			return SUCCESS;
		}catch(Exception e){
			addActionError(getText(e.getMessage()));
			return ERROR;
		}
	}
	
	public void validate(){
		
	}

}

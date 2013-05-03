package uiip.action;

import java.util.Locale;
import java.util.Map;

import javax.mail.internet.*;
import org.apache.log4j.Logger;
import uiip.utility.crypt.MD5;
import uiip.ws.LoginWSStub;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.LocaleProvider;

public class LoginAction extends ActionSupport implements LocaleProvider{

    private String userName;
    private String password;
    private static Logger logger = Logger.getLogger("logApp");
    
    public String execute() {
    	logger.info("LoginAction");
    	LoginWSStub stub;
		try {
			stub = new LoginWSStub();			
			LoginWSStub.ControllaLogin login = new LoginWSStub.ControllaLogin();
			
			LoginWSStub.Account acc = new LoginWSStub.Account();
			
			login.setUsername(getUserName());
			login.setPassword(MD5.getHash(getPassword()));
			LoginWSStub.ControllaLoginResponse res = null; 
			 res = stub.controllaLogin(login);
						
			acc = res.get_return();			
			
			if(acc!=null && !acc.getStato().equals("C"))
			{
				Map session = ActionContext.getContext().getSession();
				session.put("account",res.get_return());
				if(acc.getGruppi().length==2)
				{
					ActionContext.getContext().getSession().put("tipo","completo");
					return "templateComp";
				}
				else
				{
					ActionContext.getContext().getSession().put("tipo", acc.getGruppi()[0].getNomeGruppo());
					if(acc.getGruppi()[0].getNomeGruppo().equals("amministratore"))
						return "templateAmm";
					else
						return "templateGiorn";
				}
			}
			else
			{
				if(acc.getStato().equals("C"))
					return "utenteCancellato";
//				 else
//					 return ERROR;
			}
		}catch (Exception e) {
			addActionError(getText(e.getMessage()));
			return "input";
		}
		return "input";
    }
   
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void validate() {
        if (getUserName().length() == 0) {
            addActionError(getText("userName.required"));
        }else{ 
	        InternetAddress emailAddr;
			try {
				emailAddr = new InternetAddress(getUserName());
				emailAddr.validate();
			} catch (AddressException e) {
				 addActionError(getText("userName.error"));
			}	       
        }
        if (getPassword().length() == 0) {
        	 addActionError( getText("password.required"));
        }
    }
}
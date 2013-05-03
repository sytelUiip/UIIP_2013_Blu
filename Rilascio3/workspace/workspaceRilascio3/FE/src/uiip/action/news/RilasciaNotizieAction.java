package uiip.action.news;

import java.util.Map;

import uiip.ws.LoginWSStub;
import uiip.ws.NewsWSStub;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class RilasciaNotizieAction extends ActionSupport{
	
	private String ultimoAccesso;
	
	public String execute(){
		NewsWSStub stub;
		try{
			stub = new NewsWSStub();
			System.out.println("SONO IN RILASCIA");
			NewsWSStub.RilasciaNotizie rilascia = new NewsWSStub.RilasciaNotizie();
			NewsWSStub.Account account = new NewsWSStub.Account();
			Map session = ActionContext.getContext().getSession();
			LoginWSStub.Account utente_loggato = (uiip.ws.LoginWSStub.Account) session.get("account");
			
			String username = utente_loggato.getUsername();
			String password = utente_loggato.getPassword();
			
			account.setUsername(getUltimoAccesso());
			rilascia.setPassword(password);
			rilascia.setUsername(username);
			rilascia.setAccount(account);
			stub.rilasciaNotizie(rilascia);
			
		}catch (Exception e) {
			addActionError(getText(e.getMessage()));
			return ERROR;
		}
		return SUCCESS;
	}

	public String getUltimoAccesso() {
		return ultimoAccesso;
	}

	public void setUltimoAccesso(String ultimoAccesso) {
		this.ultimoAccesso = ultimoAccesso;
	}

}

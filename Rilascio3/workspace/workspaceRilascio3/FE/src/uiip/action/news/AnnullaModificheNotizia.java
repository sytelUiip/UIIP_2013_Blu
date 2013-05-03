package uiip.action.news;

import java.util.Map;

import uiip.ws.LoginWSStub;
import uiip.ws.NewsWSStub;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class AnnullaModificheNotizia extends ActionSupport {
	
	private int idN;
	
	public String execute(){
		NewsWSStub stub;
		int id = 0;
		boolean result = false;
		try{
			stub = new NewsWSStub();
			NewsWSStub.AnnullaModifica annulla = new NewsWSStub.AnnullaModifica();
			NewsWSStub.Notizia notizia = new NewsWSStub.Notizia();
			Map session = ActionContext.getContext().getSession();
			LoginWSStub.Account utente_loggato = (uiip.ws.LoginWSStub.Account) session.get("account");
			
			String username = utente_loggato.getUsername();
			String password = utente_loggato.getPassword();
			
			notizia.setId_N(getIdN());
			annulla.setId_n(getIdN());
			annulla.setPassword(password);
			annulla.setUsername(username);
			NewsWSStub.AnnullaModificaResponse resp = stub.annullaModifica(annulla);
			
			result = resp.get_return();
			
		} catch(Exception e){
			addActionError(getText(e.getMessage()));
			return ERROR;
		}
		if(result)
			return SUCCESS;
		else
			return ERROR;
			
		
	}

	public int getIdN() {
		return idN;
	}

	public void setIdN(int idN) {
		this.idN = idN;
	}

}

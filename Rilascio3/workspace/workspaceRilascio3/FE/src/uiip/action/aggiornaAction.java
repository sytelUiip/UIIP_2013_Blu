package uiip.action;

import java.util.Map;

import uiip.utility.crypt.MD5;
import uiip.ws.AccountWSStub;
import uiip.ws.LoginWSStub;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class aggiornaAction extends ActionSupport{

	private String sigla_giornalista = null;
	private String sigla_redazione = null;
	private String username = null;
	private String password = null;
	private String nome=null;
	private String cognome=null;
	private String test=null;
	
	
	public String execute(){
		
		AccountWSStub stub;
		AccountWSStub.Account acc = new AccountWSStub.Account();
		
		Map session = ActionContext.getContext().getSession();
		LoginWSStub.Account utente_loggato = (LoginWSStub.Account) session.get("account");
		try{
			System.out.println("AGGIORNA ACTION!");
			stub = new AccountWSStub();
			AccountWSStub.InserisciAccount insert = new AccountWSStub.InserisciAccount();
			test=getTest();
			System.out.println("test"+test);
			acc.setNome(getNome());
			acc.setCognome(getCognome());
			acc.setUsername(getUsername());
			acc.setPassword(MD5.getHash(getPassword()));
			acc.setSigla_giornalista(getSigla_giornalista());
			acc.setSigla_redazione(getSigla_redazione());
			System.out.println("nome"+acc.getNome()+" cognome "+acc.getCognome());
			
			insert.setAccount(acc);
			insert.setUser(utente_loggato.getUsername());
			insert.setPass(utente_loggato.getPassword());
			
			AccountWSStub.InserisciAccountResponse resp = stub.inserisciAccount(insert);
			boolean result = resp.get_return();
			if(result==true)
				return SUCCESS;
			else
				return ERROR;
		} catch(Exception e){
			addActionError(getText(e.getMessage()));
			return ERROR;
		}
			
	}
	


	public String getTest() {
		return test;
	}



	public void setTest(String test) {
		this.test = test;
	}



	/*public void validate() {
        if (getUsername().length() == 0) {
            addFieldError("userName",  getText("userName.required"));
        } 
        if (getPassword().length() == 0) {
            addFieldError("password", getText("password.required"));
        }
        if (getNome().length() == 0) {
            addFieldError("nome", getText("nome.required"));
        }
        if (getCognome().length() == 0) {
            addFieldError("cognome", getText("cognome.required"));
        }
        if (getSigla_giornalista().length() == 0) {
            addFieldError("sigla_giornalista", getText("sigla_giornalista.required"));
        }
        if (getSigla_redazione().length() == 0) {
            addFieldError("sigla_redazione", getText("sigla_redazione.required"));
        }
    }*/


	public String getSigla_giornalista() {
		return sigla_giornalista;
	}
	public void setSigla_giornalista(String sigla_giornalista) {
		this.sigla_giornalista = sigla_giornalista;
	}
	public String getSigla_redazione() {
		return sigla_redazione;
	}
	public void setSigla_redazione(String sigla_redazione) {
		this.sigla_redazione = sigla_redazione;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

}

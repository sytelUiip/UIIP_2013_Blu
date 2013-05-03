package uiip.action;

import java.util.Map;

import uiip.utility.crypt.MD5;
import uiip.ws.AccountWSStub;
import uiip.ws.LoginWSStub;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class UpdateAction extends ActionSupport {

	private String sigla_giornalista = null;
	private String sigla_redazione = null;
	private String username = null;
	private String password = null;
	private String nome=null;
	private String cognome=null;
	
	public String execute(){
		AccountWSStub stub;
		try{
			stub = new AccountWSStub();
			
			AccountWSStub.ModificaAccount modifica = new AccountWSStub.ModificaAccount();
			AccountWSStub.Account account = new AccountWSStub.Account();
			
			account.setUsername(getUserName());
			account.setNome(getNome());
			account.setCognome(getCognome());
			account.setPassword(MD5.getHash(getPassword()));
			account.setSigla_giornalista(getSigla_giornalista());
			account.setSigla_redazione(getSigla_redazione());
			
			Map session = ActionContext.getContext().getSession();
			LoginWSStub.Account utente_loggato = (LoginWSStub.Account) session.get("account");
			
			
			modifica.setAccount(account);
			modifica.setUser(utente_loggato.getUsername());
			modifica.setPass(utente_loggato.getPassword());
			stub.modificaAccount(modifica);
			return SUCCESS;
			
		}catch(Exception ex){
			addActionError(getText(ex.getMessage()));
			return ERROR;
		}
	}
	
	public void validate(){
//        if (getPassword().length() == 0) {
//            addFieldError("password", getText("password.required"));
//        }
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
	}

	public String getUserName() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

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

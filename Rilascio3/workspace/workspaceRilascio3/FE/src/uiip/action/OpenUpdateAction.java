package uiip.action;

import java.util.Map;

import uiip.utility.crypt.MD5;
import uiip.ws.AccountWSStub;
import uiip.ws.LoginWSStub;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class OpenUpdateAction extends ActionSupport {

	private String sigla_giornalista;
	private String sigla_redazione;
	private String username;
	private String password;
	private String nome;
	private String cognome;
	
	public String execute(){
		AccountWSStub stub;
		try{
			stub = new AccountWSStub();
			
			AccountWSStub.ModificaAccount modifica = new AccountWSStub.ModificaAccount();
			AccountWSStub.Account account = new AccountWSStub.Account();
			
			account.setUsername(getUserName());
			account.setNome(getNome());
			account.setCognome(getCognome());
			account.setPassword(getPassword());
			account.setSigla_giornalista(getSigla_giornalista());
			account.setSigla_redazione(getSigla_redazione());
			
			ActionContext.getContext().getSession().put("account1", account);			
			
			LoginWSStub.Account acc = new LoginWSStub.Account();			
			LoginWSStub stub1 = new LoginWSStub();
			LoginWSStub.ControllaLogin login= null;
			login= new LoginWSStub.ControllaLogin();
			
			login.setUsername(getUserName());
			login.setPassword(getPassword());
			
			LoginWSStub.ControllaLoginResponse res = null;
			try{
				res= stub1.controllaLogin(login);
			}catch(Exception ex){
				addActionError(getText(ex.getMessage()));
				return ERROR;
			}
			acc = res.get_return();
			
			if(!acc.getStato().equals("C"))
				return SUCCESS;
			else
				return "UtenteCancellato";
			
		} catch(Exception e){
			addActionError(getText(e.getMessage()));
			return ERROR;
		}		
	}
	
	public void validate(){
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

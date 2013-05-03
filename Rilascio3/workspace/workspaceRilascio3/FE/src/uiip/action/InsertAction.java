package uiip.action;


import java.util.Map;
import java.util.logging.Logger;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import uiip.utility.crypt.MD5;
import uiip.ws.AccountWSStub;
import uiip.ws.LoginWSStub;
import uiip.ws.LoginWSStub.Account;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class InsertAction extends ActionSupport{

	private static Logger logger = Logger.getLogger("logApp");
	private String sigla_giornalista;
	private String sigla_redazione;
	private String userName;
	private String password;
	private String nome;
	private String cognome;
	private String stato;

	public String execute(){
		logger.info("InsertAction");
		AccountWSStub stub;
		AccountWSStub.Account acc = new AccountWSStub.Account();
		try{

			stub = new AccountWSStub();
			AccountWSStub.InserisciAccount insert = new AccountWSStub.InserisciAccount();

			acc.setNome(getNome());
			acc.setCognome(getCognome());
			acc.setUsername(getUserName());
			acc.setPassword(MD5.getHash(getPassword()));
			acc.setSigla_giornalista(getSigla_giornalista());
			acc.setSigla_redazione(getSigla_redazione());

			AccountWSStub.RitornaAccount control = new AccountWSStub.RitornaAccount();

			Map session = ActionContext.getContext().getSession();
			LoginWSStub.Account utente_loggato = (LoginWSStub.Account) session.get("account");
			
			control.setUsername(getUserName());
			control.setUser(utente_loggato.getUsername());
			control.setPass(utente_loggato.getPassword());

			AccountWSStub.RitornaAccountResponse res;
			
			try{
				res = stub.ritornaAccount(control);
			}catch(Exception ex){
				addActionError(getText(ex.getMessage()));
				return ERROR;
			}

			if(res.get_return()!=null && res.get_return().getUsername()!=null)
			{
				if( (res.get_return().getGruppi()!=null)&&(res.get_return().getGruppi().length==1) && (res.get_return().getGruppi()[0].getNomeGruppo().equals("amministratore"))
						&& !res.get_return().getStato().equals("C"))
				{
					insert.setAccount(acc); // Inserimento di nuovo utente
					insert.setUser(utente_loggato.getUsername());
					insert.setPass(utente_loggato.getPassword());
					AccountWSStub.InserisciAccountResponse resp;
					try{
						resp= stub.inserisciAccount(insert);
					}catch(Exception ex){
						addActionError(getText(ex.getMessage()));
						return ERROR;
					}
					return SUCCESS;
				}
				else
				{
					if(res.get_return().getStato().equals("C"))
						return "UtenteCancellato";
					else
						return "UtenteGiaPresente";
				}
			}
			else
			{
				insert.setAccount(acc); // Inserimento di nuovo utente
				insert.setUser(utente_loggato.getUsername());
				insert.setPass(utente_loggato.getPassword());
				AccountWSStub.InserisciAccountResponse resp;
				try{
					resp= stub.inserisciAccount(insert);
				}catch(Exception ex){
					addActionError(getText(ex.getMessage()));
					return ERROR;
				}
				return "InserimentoAccount";
			}
		} catch(Exception e){
			addActionError(getText(e.getMessage()));
			return "UtenteGiaPresente";
		}			

	}

	public void validate() {
		if (getUserName().length() == 0) {
            addFieldError("userName",  getText("userName.required"));
        } else{ 
	        InternetAddress emailAddr;
			try {
				emailAddr = new InternetAddress(getUserName());
				emailAddr.validate();
			} catch (AddressException e) {
				addFieldError("userName",  getText("userName.error"));
			}	       
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

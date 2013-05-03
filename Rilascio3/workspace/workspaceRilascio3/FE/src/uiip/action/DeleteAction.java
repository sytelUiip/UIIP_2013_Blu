package uiip.action;

import java.util.Map;

import uiip.utility.crypt.MD5;
import uiip.ws.AccountWSStub;
import uiip.ws.LoginWSStub;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class DeleteAction extends ActionSupport {
	
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
			
			
			LoginWSStub s = new LoginWSStub();
			LoginWSStub.ControllaLogin login = new LoginWSStub.ControllaLogin();
			LoginWSStub.Account acc = new LoginWSStub.Account();
			
			Map session = ActionContext.getContext().getSession();
			LoginWSStub.Account utente_loggato = (LoginWSStub.Account) session.get("account");
			
			AccountWSStub.CancellaAccount cancella = new AccountWSStub.CancellaAccount();
			AccountWSStub.Account account = new AccountWSStub.Account();
			
			
			System.out.println("Arrico prima di account.setUsername(getUserName())");
			account.setUsername(getUserName());
			
			System.out.println("Arrivo prima di login.setUsername(getUserName());");
			login.setUsername(getUserName());
			login.setPassword(getPassword());
			
			System.out.println("Arrivo prima di LoginWSStub.ControllaLoginResponse res = s.controllaLogin(login);");
			LoginWSStub.ControllaLoginResponse res = s.controllaLogin(login);
			
			
			System.out.println("Arrivo prima di acc = res.get_return();");
			acc = res.get_return();			
			
			if(acc!=null && !acc.getStato().equals("C")){
				if(acc.getGruppi().length==2){
					cancella.setAccount(account);
					cancella.setUser(utente_loggato.getUsername());
					cancella.setPass(utente_loggato.getPassword());
					stub.cancellaAccount(cancella);
					return "CancellaUtenteCompleto";
				}
				else if(acc.getGruppi()[0].getNomeGruppo().equals("giornalista")){
					cancella.setAccount(account);
					cancella.setUser(utente_loggato.getUsername());
					cancella.setPass(utente_loggato.getPassword());
					stub.cancellaAccount(cancella);
					return "CancellaGiornalista";
				}
				else
				{
					return "NonPuoiCancellareAdmin";
				}
			}
			else
			{
				if(acc.getStato().equals("C"))
					return "AccountDisattivato";
				else
				{
					System.out.println("sono nell'else");
					return ERROR;
					
				}
			}
		} catch(Exception e){
			addActionError(getText(e.getMessage()));
			System.out.println("sono nel catch");
			return ERROR;
		}
    }

	public void validate(){
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





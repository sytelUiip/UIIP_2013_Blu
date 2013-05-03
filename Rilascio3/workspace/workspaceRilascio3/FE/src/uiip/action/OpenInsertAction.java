package uiip.action;

import uiip.ws.AccountWSStub;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class OpenInsertAction extends ActionSupport {

	private String sigla_giornalista;
	private String sigla_redazione;
	private String username;
	private String password;
	private String nome;
	private String cognome;
	
	public String execute(){
		/*AccountWSStub stub;
		try{
			stub = new AccountWSStub();
			
			AccountWSStub.InserisciAccount inserisci=null;
			try{
				inserisci= new AccountWSStub.InserisciAccount();
			}catch(Exception ex){
				addActionError(getText(ex.getMessage()));
				return ERROR;
			}
			AccountWSStub.Account account = new AccountWSStub.Account();
		} catch(Exception e){
			e.printStackTrace();
		}*/
		return SUCCESS;
		
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

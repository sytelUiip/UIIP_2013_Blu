package uiip.action;

import uiip.ws.AccountWSStub.Gruppo;
import uiip.ws.LoginWSStub;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class OpenVisualizzaAction extends ActionSupport {

	private String sigla_giornalista;
	private String sigla_redazione;
	private String username;
	private String password;
	private String nome;
	private String cognome;
	private Gruppo[] gruppi;
	
	public String execute(){
		LoginWSStub stub;
		try {
			stub = new LoginWSStub();
			LoginWSStub.ControllaLogin visualizza = new LoginWSStub.ControllaLogin();
			
			visualizza.setUsername(getUserName());
			visualizza.setPassword(getPassword());
			
			LoginWSStub.ControllaLoginResponse resp = stub.controllaLogin(visualizza);
			
			LoginWSStub.Account account = new LoginWSStub.Account();
			account = resp.get_return();
			ActionContext.getContext().getSession().put("account1", account);
			
			if(account.getStato().equals("C")){
				return "utenteCancellatoNoVisualizz";
			}
			String test="negativo";
			if(account.getGruppi().length==1){
				if(account.getGruppi()[0].getNomeGruppo().equals("amministratore")){
					test="positivo";
				 }
			}
			ActionContext.getContext().getSession().put("test", test);
			return SUCCESS;
		} catch(Exception e){
			addActionError(getText(e.getMessage()));
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

	public Gruppo[] getGruppi() {
		return gruppi;
	}

	public void setGruppi(Gruppo[] gruppi) {
		this.gruppi = gruppi;
	}
	
}

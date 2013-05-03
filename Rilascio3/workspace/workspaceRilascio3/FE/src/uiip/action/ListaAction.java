package uiip.action;

import java.util.Map;

import uiip.ws.AccountWSStub;
import uiip.ws.LoginWSStub;
import uiip.ws.AccountWSStub.Account;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ListaAction extends ActionSupport {

	private String sigla_giornalista;
	private String sigla_redazione;
	private String userName;
	private String password;
	private String nome;
	private String cognome;
	private int offset;
	private boolean avanti=false;
	private boolean indietro=false;

	public String execute() {
		AccountWSStub stub;

		try {
			Account[] accounts;
			stub = new AccountWSStub();
			AccountWSStub.ListaAccountOffset lista = new AccountWSStub.ListaAccountOffset();

			Map session = ActionContext.getContext().getSession();
			LoginWSStub.Account utente_loggato = (LoginWSStub.Account) session.get("account");
			
			System.out.println("SESSION.GET(OFFSET INIZIALE) "+session.get("offset"));
			if(session.get("offset")==null || (Integer)session.get("offset")==0){
				offset=1;
				AccountWSStub.NumeroTotUtenti count = new AccountWSStub.NumeroTotUtenti();
			
				count.setUsername(utente_loggato.getUsername());
				count.setPassword(utente_loggato.getPassword());
				AccountWSStub.NumeroTotUtentiResponse resp = stub.numeroTotUtenti(count);
				int numUtenti = resp.get_return();
				int numPagine = numUtenti/10;
				int resto = numUtenti%10;
				if(resto!=0){
					numPagine++;
				}
				session.put("numPagine",numPagine);
			}else{
				offset=(Integer) session.get("offset");
				if(avanti && offset<(Integer)session.get("numPagine")){
					offset++;
					avanti = false;
				}
				if(indietro && offset > 1){
					offset--;
					indietro = false;
				}
				
			}
			
			System.out.println("OFFSET "+offset);
			session.put("offset",offset);
			
			lista.setInizio(offset*10-9);
			lista.setFine(offset*10);
			lista.setUser(utente_loggato.getUsername());
			lista.setPass(utente_loggato.getPassword());
			
			AccountWSStub.ListaAccountOffsetResponse resp= null;

				resp = stub.listaAccountOffset(lista);
			
			
			if(resp.get_return().length>0){
			accounts=resp.get_return();       
			ActionContext.getContext().getSession().put("listaAccount",accounts);
			return SUCCESS;
			}
			else{
				return ERROR;
			
			}
		}catch (Exception e) {
			addActionError(getText(e.getMessage()));
			return ERROR;
		}

	}

	public void validate() {

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

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}



	public void setAvanti(boolean avanti) {
		this.avanti = true;
	}


	public void setIndietro(boolean indietro) {
		this.indietro = true;
	}

}

package uiip.action.news;

import java.util.Map;

import uiip.ws.LoginWSStub;
import uiip.ws.NewsWSStub;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class VisualizzaNewsAction extends ActionSupport {
	
	private int idN;
	private String titolo;
	private String autore;
	private String datacreazione;
	private String datatrasmissione;
	private String stato;
	private String lock_n;
	private String ultimodigitatore;
	private String ultimoaccesso;
	private String sottotitolo;
	private String testo;
	private int lunghezza;
	
	public String execute(){
		NewsWSStub stub;
		try{
			stub = new NewsWSStub();
			NewsWSStub.RitornaNotizia ritornanotizia=new NewsWSStub.RitornaNotizia();
			NewsWSStub.Notizia notizia = new NewsWSStub.Notizia();
			
			Map session = ActionContext.getContext().getSession();
			LoginWSStub.Account utente_loggato = (uiip.ws.LoginWSStub.Account) session.get("account");
			
			String username = utente_loggato.getUsername();
			String password = utente_loggato.getPassword();
			ritornanotizia.setPassword(password);
			ritornanotizia.setUsername(username);
			
			notizia.setAutore(getAutore());
			notizia.setTitolo(getTitolo());
			notizia.setSottotitolo(getSottotitolo());
			//notizia.setTesto(getTesto());
			notizia.setDatacreazione(getDatacreazione());
			notizia.setDatatrasmissione(getDatatrasmissione());
			notizia.setStato(getStato());
			notizia.setLock_n(getLock_n());
			notizia.setUltimodigitatore(getUltimoaccesso());
			notizia.setUltimoAccesso(getUltimoaccesso());
			notizia.setId_N(getIdN());
		
			//notizia.setLunghezzatesto(getTesto().length());
			
			//ritornanotizia.setId_n(notizia.getId_N());
			ritornanotizia.setId_n(getIdN());
			NewsWSStub.RitornaNotiziaResponse resp=stub.ritornaNotizia(ritornanotizia);
			notizia=resp.get_return();
			ActionContext.getContext().getSession().put("notizia", notizia);
			return SUCCESS;
		}catch(Exception e){
			addActionError(getText(e.getMessage()));
			return ERROR;
		}
		
		
		}


	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public String getAutore() {
		return autore;
	}

	public void setAutore(String autore) {
		this.autore = autore;
	}

	public String getDatacreazione() {
		return datacreazione;
	}

	public void setDatacreazione(String datacreazione) {
		this.datacreazione = datacreazione;
	}

	public String getDatatrasmissione() {
		return datatrasmissione;
	}

	public void setDatatrasmissione(String datatrasmissione) {
		this.datatrasmissione = datatrasmissione;
	}

	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	public String getLock_n() {
		return lock_n;
	}

	public void setLock_n(String lock_n) {
		this.lock_n = lock_n;
	}

	public String getUltimodigitatore() {
		return ultimodigitatore;
	}

	public void setUltimodigitatore(String ultimodigitatore) {
		this.ultimodigitatore = ultimodigitatore;
	}

	public String getUltimoaccesso() {
		return ultimoaccesso;
	}

	public void setUltimoaccesso(String ultimoaccesso) {
		this.ultimoaccesso = ultimoaccesso;
	}

	public String getSottotitolo() {
		return sottotitolo;
	}

	public void setSottotitolo(String sottotitolo) {
		this.sottotitolo = sottotitolo;
	}

	public String getTesto() {
		return testo;
	}

	public void setTesto(String testo) {
		this.testo = testo;
	}

	public int getLunghezza() {
		return lunghezza;
	}

	public void setLunghezza(int lunghezza) {
		this.lunghezza = lunghezza;
	}


	public int getIdN() {
		return idN;
	}


	public void setIdN(int idN) {
		this.idN = idN;
	}
	

}

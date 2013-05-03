package uiip.action.news;

import java.util.Map;

import uiip.ws.LoginWSStub;
import uiip.ws.NewsWSStub;
//import uiip.ws.NewsWSStub.Account;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class UpdateNewsAction extends ActionSupport {
	
	private int id_n;
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
			NewsWSStub.ModificaNotizia modifica = new NewsWSStub.ModificaNotizia();
			NewsWSStub.Notizia notizia = new NewsWSStub.Notizia();
			
			Map session = ActionContext.getContext().getSession();
			LoginWSStub.Account utente_loggato =(uiip.ws.LoginWSStub.Account) session.get("account");
			
			notizia.setAutore(getAutore());
			notizia.setTitolo(getTitolo());
			notizia.setSottotitolo(getSottotitolo());
			notizia.setTesto(getTesto());
			notizia.setDatacreazione(getDatacreazione());
			notizia.setDatatrasmissione(getDatatrasmissione());
			notizia.setStato("S");
			notizia.setLock_n("N");
			notizia.setUltimodigitatore(getUltimoaccesso());
			notizia.setUltimoAccesso(getUltimoaccesso());
			notizia.setId_N(getId_n());
			notizia.setLunghezzatesto(getTesto().length());
						
			modifica.setNotizia(notizia);
			modifica.setUsername(utente_loggato.getUsername());
			modifica.setPassword(utente_loggato.getPassword());
			stub.modificaNotizia(modifica);
			
			return SUCCESS;
		}catch(Exception e){
			addActionError(getText(e.getMessage()));
			return ERROR;
		}
		
	}

	public int getId_n() {
		return id_n;
	}

	public void setId_n(int id_n) {
		this.id_n = id_n;
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
	
	public void validate() {
		if (getAutore().length() == 0) {
            addFieldError("autore",  getText("autore.required"));
        } 
        if (getTitolo().length() == 0) {
            addFieldError("titolo", getText("titolo.required"));
        }
        if (getSottotitolo().length() == 0) {
            addFieldError("sottotitolo", getText("sottotitolo.required"));
        }
        if (getTesto().length() == 0) {
            addFieldError("testo", getText("testo.required"));
        }
	}

}

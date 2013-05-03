package uiip.action.news;

import java.util.Map;

import uiip.ws.LoginWSStub;
import uiip.ws.NewsWSStub;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class OpenUpdateNewsAction extends ActionSupport {
	
	private int idN;
	private String titolo;
	private String autore;
	private String dataCreazione;
	private String dataTrasmissione;
	private String stato;
	private String lock_n;
	private String ultimoDigitatore;
	private String ultimoAccesso;
	private String sottotitolo;
	private String testo;
	private int lunghezza;
	
	public String execute(){
		NewsWSStub stub;
		boolean result = false;
		try{
			stub = new NewsWSStub();
			NewsWSStub.Notizia not = new NewsWSStub.Notizia();
			NewsWSStub.Notizia_in_modifica modifica = new NewsWSStub.Notizia_in_modifica();
			
			Map session = ActionContext.getContext().getSession();
			LoginWSStub.Account utente_loggato = (LoginWSStub.Account)session.get("account2");			
			
			not.setId_N(getIdN());
			
			modifica.setNotizia(not);
			modifica.setUsername(utente_loggato.getUsername());
			modifica.setPassword(utente_loggato.getPassword());
			not.setUltimodigitatore(getUltimoDigitatore());
			not.setUltimoAccesso(utente_loggato.getUsername());
			not.setAutore(getAutore());
			not.setTitolo(getTitolo());
			not.setSottotitolo(getSottotitolo());
			not.setTesto(getTesto());
			//not.setLock_n(getLock_n());
			not.setLock_n("Y");
			not.setDatacreazione(getDataCreazione());
			not.setDatatrasmissione(getDataTrasmissione());
			not.setLunghezzatesto(getLunghezza());
			not.setStato(getStato());
			
			NewsWSStub.Notizia_in_modificaResponse resp = stub.notizia_in_modifica(modifica);
			
			result = resp.get_return();
			
			System.out.println("OPEN UPDATE NEWS ACTION: "+result);
			ActionContext.getContext().getSession().put("notizia", not);
			ActionContext.getContext().getSession().put("account",utente_loggato);
			
		}catch(Exception e){
			addActionError(getText(e.getMessage()));
			return ERROR;
		}
		
		if(result)
			return SUCCESS;
		else
			return ERROR;
	}
	

	public int getIdN() {
		return idN;
	}
	public void setIdN(int idN) {
		this.idN = idN;
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
	public String getDataCreazione() {
		return dataCreazione;
	}
	public void setDataCreazione(String dataCreazione) {
		this.dataCreazione = dataCreazione;
	}
	public String getDataTrasmissione() {
		return dataTrasmissione;
	}
	public void setDataTrasmissione(String dataTrasmissione) {
		this.dataTrasmissione = dataTrasmissione;
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

	public String getUltimoDigitatore() {
		return ultimoDigitatore;
	}
	public void setUltimoDigitatore(String ultimoDigitatore) {
		this.ultimoDigitatore = ultimoDigitatore;
	}

	public String getUltimoAccesso() {
		return ultimoAccesso;
	}

	public void setUltimoAccesso(String ultimoAccesso) {
		this.ultimoAccesso = ultimoAccesso;
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
	
}

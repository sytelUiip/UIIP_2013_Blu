package uiip.action.news;

import java.util.Map;
import java.util.logging.Logger;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import uiip.ws.LoginWSStub;
import uiip.ws.NewsWSStub;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class InsertNewsAction extends ActionSupport {
	
	private static Logger logger = Logger.getLogger("logApp");

	private String autore;
	private String titolo;
	private String sottotitolo;
	private String lock;
	private String stato;
	private String ultimoDigitatore;
	private String testo;
	private int lunghezza;
	private String datacreazione;
	private String datatrasmissione;
	private String ultimoaccesso;
	private int idN;
	
	
	public String execute(){
		logger.info("metodo execute di InsertNewsAction");
	NewsWSStub stub;
	try{
		stub = new NewsWSStub();
		NewsWSStub.Notizia notizia = new NewsWSStub.Notizia();
		NewsWSStub.InserisciNotizia inserisci = new NewsWSStub.InserisciNotizia();

		/* PRENDE AUTORE NOTIZIA DA SESSIONE LOGIN */
		Map session = ActionContext.getContext().getSession();
		LoginWSStub.Account utente_loggato = (LoginWSStub.Account)session.get("account");

		String username=utente_loggato.getUsername();
		String password=utente_loggato.getPassword();
		
		notizia.setTitolo(getTitolo());
		notizia.setSottotitolo(getSottotitolo());
		notizia.setAutore(utente_loggato.getUsername());
		notizia.setUltimodigitatore(utente_loggato.getUsername());
		notizia.setUltimoAccesso(utente_loggato.getUsername());
		notizia.setStato(getStato());
		notizia.setLock_n(getLock());
		notizia.setDatacreazione(getDatacreazione());
		notizia.setDatatrasmissione(getDatacreazione());
		notizia.setTesto(getTesto());
		notizia.setLunghezzatesto(getTesto().length());
		
		
		inserisci.setUsername(username);
		inserisci.setPassword(password);
		inserisci.setNotizia(notizia);
		
		stub.inserisciNotizia(inserisci);
		return SUCCESS;
		
	} catch(Exception e){
		addActionError(getText(e.getMessage()));
		return ERROR;
	}
	
	}

	public String getLock() {
		return lock;
	}

	public void setLock(String lock) {
		this.lock = lock;
	}

	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	public int getIdN() {
		return idN;
	}

	public void setIdN(int idN) {
		this.idN = idN;
	}



	public String getAutore() {
		return autore;
	}

	public void setAutore(String autore) {
		this.autore = autore;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public String getSottotitolo() {
		return sottotitolo;
	}

	public void setSottotitolo(String sottotitolo) {
		this.sottotitolo = sottotitolo;
	}

	public String getUltimoDigitatore() {
		return ultimoDigitatore;
	}

	public void setUltimoDigitatore(String ultimoDigitatore) {
		this.ultimoDigitatore = ultimoDigitatore;
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

	public String getUltimoaccesso() {
		return ultimoaccesso;
	}

	public void setUltimoaccesso(String ultimoaccesso) {
		this.ultimoaccesso = ultimoaccesso;
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

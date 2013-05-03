package uiip.action.news;

import java.util.Map;

import uiip.ws.LoginWSStub;
import uiip.ws.NewsWSStub;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class DeleteNewsAction extends ActionSupport
{
	private int idN;
	private String titolo;
	private String autore;
	private String dataCreazione;
	private String dataTrasmissione;
	private String stato;
	private String lock;
	private String ultimoDigitatore;
	
	public String execute(){
		NewsWSStub stub;
		boolean result = false;
		try{
			stub = new NewsWSStub();
			NewsWSStub.CancellaNotizia cancNews = new NewsWSStub.CancellaNotizia();
			NewsWSStub.Notizia notizia = new NewsWSStub.Notizia();
			
			Map session = ActionContext.getContext().getSession();
			LoginWSStub.Account utente_loggato = (uiip.ws.LoginWSStub.Account) session.get("account");
			
			String username = utente_loggato.getUsername();
			String password = utente_loggato.getPassword();
			cancNews.setUsername(username);
			cancNews.setPassword(password);			
			
			notizia.setId_N(getIdN());
			notizia.setAutore(getAutore());
			notizia.setDatacreazione(getDataCreazione());
			notizia.setDatatrasmissione(getDataTrasmissione());
			notizia.setTitolo(getTitolo());
			notizia.setStato(getStato());
			notizia.setLock_n(getLock());
			notizia.setUltimodigitatore(getUltimoDigitatore());
			cancNews.setNotizia(notizia);
			NewsWSStub.CancellaNotiziaResponse resp = stub.cancellaNotizia(cancNews);
			
			result = resp.get_return();
			
			//return SUCCESS;
		} catch(Exception e){
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

	public String getLock() {
		return lock;
	}

	public void setLock(String lock) {
		this.lock = lock;
	}

	public String getUltimoDigitatore() {
		return ultimoDigitatore;
	}

	public void setUltimoDigitatore(String ultimoDigitatore) {
		this.ultimoDigitatore = ultimoDigitatore;
	}
	
}

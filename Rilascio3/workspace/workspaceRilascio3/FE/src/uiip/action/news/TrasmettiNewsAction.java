package uiip.action.news;

import uiip.ws.LoginWSStub;
import uiip.ws.NewsBatchWSStub;
import uiip.ws.LoginWSStub.Account;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class TrasmettiNewsAction extends ActionSupport {
	
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
		NewsBatchWSStub stub;
		try{
			stub = new NewsBatchWSStub();
			NewsBatchWSStub.Notizia notizia=new NewsBatchWSStub.Notizia();
			NewsBatchWSStub.TrasmettiNotizia trasmettinotizia=new NewsBatchWSStub.TrasmettiNotizia();
			LoginWSStub.Account acc2= (Account) ActionContext.getContext().getSession().get("account2");
			
			notizia.setAutore(getAutore());
			notizia.setTitolo(getTitolo());
			notizia.setSottotitolo(getSottotitolo());
			notizia.setTesto(getTesto());
			notizia.setDatacreazione(getDatacreazione());
			notizia.setDatatrasmissione(getDatatrasmissione());
			notizia.setStato(getStato());
			notizia.setLock_n(getLock_n());
			notizia.setUltimodigitatore(acc2.getUsername());
			//notizia.setUltimoAccesso(getUltimoaccesso());
			notizia.setUltimoAccesso(acc2.getUsername());
			notizia.setId_N(getIdN());
			//notizia.setLunghezzatesto(getTesto().length());
			trasmettinotizia.setNotizia(notizia);
			trasmettinotizia.setUsername(acc2.getUsername());
			trasmettinotizia.setUser(acc2.getUsername());
			trasmettinotizia.setPass(acc2.getPassword());
			//stub.trasmettiNotizia(trasmettinotizia);
			NewsBatchWSStub.TrasmettiNotiziaResponse resp=stub.trasmettiNotizia(trasmettinotizia);
			int res=resp.get_return();
			if(res==1)
			return SUCCESS;
			else
				return ERROR;			
	}catch(Exception e){
		addActionError(getText(e.getMessage()));
		return ERROR;
	}

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

}
	

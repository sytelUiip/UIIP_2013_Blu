package uiip.entity;

import java.sql.Date;
import java.util.GregorianCalendar;

public class Notizia {
	private String lock_n;
	private String stato;
	private String titolo;
	private String sottotitolo;
	private String autore;
	private String ultimodigitatore;
	private String datacreazione;
	private String datatrasmissione;
	private String testo;
	private int lunghezzatesto;
	private int id_N;
	private String ultimoAccesso;
	
	public int getId_N() {
		return id_N;
	}
	public void setId_N(int id_N) {
		this.id_N = id_N;
	}
	public String getLock_n() {
		return lock_n;
	}
	public void setLock_n(String lock_n) {
		this.lock_n = lock_n;
	}
	public String getStato() {
		return stato;
	}
	public void setStato(String stato) {
		this.stato = stato;
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
	public String getAutore() {
		return autore;
	}
	public void setAutore(String autore) {
		this.autore = autore;
	}
	public String getUltimodigitatore() {
		return ultimodigitatore;
	}
	public void setUltimodigitatore(String ultimodigitatore) {
		this.ultimodigitatore = ultimodigitatore;
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
	public String getTesto() {
		return testo;
	}
	public void setTesto(String testo) {
		this.testo = testo;
	}
	public int getLunghezzatesto() {
		return lunghezzatesto;
	}
	public void setLunghezzatesto(int lunghezzatesto) {
		this.lunghezzatesto = lunghezzatesto;
	}
	public String getUltimoAccesso() {
		return ultimoAccesso;
	}
	public void setUltimoAccesso(String ultimoAccesso) {
		this.ultimoAccesso = ultimoAccesso;
	}
}

package uiip.entity;


import java.io.Serializable;
import java.util.List;

public class Account implements Serializable
{
	private String stato = null;
	private String sigla_giornalista = null;
	private String sigla_redazione = null;
	private String username = null;
	private String password = null;
	private String nome=null;
	private String cognome=null;
	private Gruppo[] gruppi;

	public Account(){}
	
	public Account(String nome,String cognome,String username, String password,String sigla_giornalista,
			String sigla_redazione) {
		super();
		this.sigla_giornalista = sigla_giornalista;
		this.sigla_redazione = sigla_redazione;
		this.username = username;
		this.password = password;
		this.nome =nome;
		this.cognome = cognome;
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

	public String getStato() {
		return stato;
		}

	public void setStato(String stato) {
		this.stato = stato;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Gruppo[] getGruppi() {
		return gruppi;
	}

	public void setGruppi(Gruppo[] gruppi) {
		this.gruppi = gruppi;
	}

	
}	

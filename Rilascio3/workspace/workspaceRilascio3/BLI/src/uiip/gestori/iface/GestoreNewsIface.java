package uiip.gestori.iface;

import uiip.entity.Account;
import uiip.entity.Notizia;

public interface GestoreNewsIface 
{
	//public Notizia[] listaNotizie(String user,String pass) throws Exception;
	
	public boolean cancellaNotizia(Notizia notizia,String user,String pass) throws Exception;
	public boolean inserisciNotizia(Notizia notizia,String user,String pass) throws Exception;
	public Notizia visualizzaNotizia(int id_n,String user,String pass) throws Exception;
	//public boolean trasmettiNotizia(Notizia notizia) throws Exception;
	//public boolean annullaModificheNotizia(int id_n) throws Exception;
	//public String nome_notizia_da_trasmettere(int id) throws Exception;
	//public Notizia[] visualizza_per_parametro(String param, String ricerca,String user,String pass) throws Exception;
	public boolean annulla(int id_n,String user,String pass) throws Exception;
	public boolean rilascia_notizie(Account account,String user,String pass) throws Exception;
	public boolean notizia_in_modifica(Notizia notizia, String user,String pass) throws Exception;
	//public boolean permesso_update(Notizia notizia, Account account) throws Exception;
	//public int modificaNotizia(Notizia notizia) throws Exception;
	public int modifica_effettiva(Notizia notizia,Account account,String user,String pass) throws Exception;

	public Notizia[] listaNotizieOffset(String user, String pass, int inizio, int fine)
			throws Exception;
	public Notizia[] visualizza_per_parametro_offset(String param, String ricerca,
			String user, String pass, int inizio, int fine) throws Exception;
	public int ritornaNumeroNotizie(String user, String pass) throws Exception;
}

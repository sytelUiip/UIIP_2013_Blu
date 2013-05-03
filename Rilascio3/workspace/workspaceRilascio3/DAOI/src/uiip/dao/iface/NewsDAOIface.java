package uiip.dao.iface;

import java.sql.Connection;

import uiip.entity.Account;
import uiip.entity.Notizia;

public interface NewsDAOIface {
	
	public Connection getConnection() throws Exception;
	//public Notizia[] listNews() throws Exception;
	public boolean deleteNews(Notizia notizia) throws Exception;
	public boolean insertNews(Notizia notizia) throws Exception;
	public Notizia viewNews(int id_n) throws Exception; 
	//public Notizia[] visualizza_per_parametro(String param, String ricerca) throws Exception;
	public boolean cancellaUpdateNews(int id_n) throws Exception;
	public boolean rilascia_notizie(Account account) throws Exception;
	public boolean news_in_update(Notizia notizia, String user) throws Exception;
	public int total_update(Notizia notizia,String user) throws Exception;
	public Notizia[] listNewsOffset(int inizio, int fine) throws Exception;
	public Notizia[] visualizza_per_parametro_offset(String param, String ricerca,
			int inizio, int fine) throws Exception;
	public int getCountNotizie() throws Exception;
}

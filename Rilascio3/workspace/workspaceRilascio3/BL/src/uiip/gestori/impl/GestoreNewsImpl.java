package uiip.gestori.impl;

import java.util.logging.Logger;

import uiip.dao.iface.LoginDAOIface;
import uiip.dao.iface.NewsDAOIface;
import uiip.dao.impl.LoginDAOImpl;
import uiip.dao.impl.NewsDAOImpl;
import uiip.entity.Account;
import uiip.entity.Notizia;
import uiip.gestori.iface.GestoreNewsIface;

public class GestoreNewsImpl implements GestoreNewsIface {

	private static Logger logger = Logger.getLogger("logApp");
	
	public GestoreNewsImpl(){
		super();
	}
	
	@Override
	public boolean inserisciNotizia(Notizia notizia,String user,String pass) throws Exception {
		logger.info("metodo: inserisciNotizia, in: notizia.id_N=\""+notizia.getId_N()+"\"");
		NewsDAOIface dao = new NewsDAOImpl();
		boolean esito=false;
		LoginDAOIface daoL = new LoginDAOImpl();
		if(!daoL.controllaLogin(user, pass)){
			 throw new Exception("COD_NOTEXIST");
		}
		else
			esito=dao.insertNews(notizia);
		return esito;
	}
	
	/*@Override
	public Notizia[] listaNotizie(String user,String pass) throws Exception {
		logger.info("metodo: listaNotizie");
		Notizia[] lista_notizie = null;
		NewsDAOIface dao = new NewsDAOImpl();
		LoginDAOIface daoL = new LoginDAOImpl();
		System.out.println("in GESTORE NEWS BL username "+user+"pass "+pass);
		if(!daoL.controllaLogin(user, pass)){
			 throw new Exception("COD_NOTEXIST");
		}
		else lista_notizie = dao.listNews();
		return lista_notizie;
	}*/
	
	@Override
	public Notizia[] listaNotizieOffset(String user,String pass, int inizio, int fine) throws Exception {
		logger.info("metodo: listaNotizie");
		Notizia[] lista_notizie = null;
		NewsDAOIface dao = new NewsDAOImpl();
		LoginDAOIface daoL = new LoginDAOImpl();
		System.out.println("in GESTORE NEWS BL username "+user+"pass "+pass);
		if(!daoL.controllaLogin(user, pass)){
			 throw new Exception("COD_NOTEXIST");
		}
		else lista_notizie = dao.listNewsOffset(inizio, fine);
		return lista_notizie;
	}

	@Override
	public int modifica_effettiva(Notizia notizia,Account account,String user,String pass) throws Exception {
		logger.info("metodo: modifica_effettiva");
		NewsDAOIface dao = new NewsDAOImpl();
		LoginDAOIface daoL = new LoginDAOImpl();
		if(!daoL.controllaLogin(user, pass)){
			 throw new Exception("COD_NOTEXIST");
		}
		else return dao.total_update(notizia, user);
	}

	@Override
	public boolean cancellaNotizia(Notizia notizia,String user,String pass) throws Exception {
		logger.info("metodo: cancellaNotizia, in: notizia.id_N=\""+notizia.getId_N()+"\"");
		NewsDAOIface dao = new NewsDAOImpl();
		LoginDAOIface daoL = new LoginDAOImpl();
		if(!daoL.controllaLogin(user, pass)){
			 throw new Exception("COD_NOTEXIST");
		}
		else return dao.deleteNews(notizia);
	}

	@Override
	public Notizia visualizzaNotizia(int id_n,String user,String pass) throws Exception {
		logger.info("metodo: ritornaAccount, in: id_n=\""+id_n+"\"");
		NewsDAOIface dao = new NewsDAOImpl();
		LoginDAOIface daoL = new LoginDAOImpl();
		if(!daoL.controllaLogin(user, pass)){
			 throw new Exception("COD_NOTEXIST");
		}
		else return dao.viewNews(id_n);
	}

	/*@Override
	public Notizia[] visualizza_per_parametro(String param, String ricerca,String user,String pass)
			throws Exception {
		logger.info("metodo:visualizza_per_parametro,in: param=\""+param+"\""+"ricerca=\""+ricerca+"\"");
		NewsDAOIface dao = new NewsDAOImpl();
		Notizia[] news;
		LoginDAOIface daoL = new LoginDAOImpl();
		if(!daoL.controllaLogin(user, pass)){
			 throw new Exception("COD_NOTEXIST");
		}
		else{
			news=dao.visualizza_per_parametro(param, ricerca);
		}
		return news;
	}*/
	
	@Override
	public Notizia[] visualizza_per_parametro_offset(String param, String ricerca,String user,String pass, int inizio, int fine)
			throws Exception {
		logger.info("metodo:visualizza_per_parametro,in: param=\""+param+"\""+"ricerca=\""+ricerca+"\"");
		NewsDAOIface dao = new NewsDAOImpl();
		Notizia[] news;
		LoginDAOIface daoL = new LoginDAOImpl();
		if(!daoL.controllaLogin(user, pass)){
			 throw new Exception("COD_NOTEXIST");
		}
		else{
			news=dao.visualizza_per_parametro_offset(param, ricerca, inizio, fine);
		}
		return news;
	}

	@Override
	public boolean annulla(int id_n,String user,String pass) throws Exception {
		logger.info("metodo:annulla,in: id_n+=\""+id_n+"\"");
		NewsDAOIface dao = new NewsDAOImpl();
		LoginDAOIface daoL = new LoginDAOImpl();
		if(!daoL.controllaLogin(user, pass)){
			 throw new Exception("COD_NOTEXIST");
		}
		else 
			return	dao.cancellaUpdateNews(id_n);
		
	}

	@Override
	public boolean rilascia_notizie(Account account,String user,String pass) throws Exception {
		logger.info("metodo: rilascia_notizie");
		NewsDAOIface dao = new NewsDAOImpl();
		LoginDAOIface daoL = new LoginDAOImpl();
		if(!daoL.controllaLogin(user, pass)){
			 throw new Exception("COD_NOTEXIST");
		}
		else 
			return	dao.rilascia_notizie(account);
		
	}

	@Override
	public boolean notizia_in_modifica(Notizia notizia, String user,String pass) throws Exception {
		logger.info("metodo: notizia_in_modifica,in: Notizia_id=\""+notizia.getId_N()+"\"+user=\""+user+"\"");
		NewsDAOIface dao = new NewsDAOImpl();
		LoginDAOIface daoL = new LoginDAOImpl();
		if(!daoL.controllaLogin(user, pass)){
			 throw new Exception("COD_NOTEXIST");
		}
		else 
			return dao.news_in_update(notizia, user);
	}

	@Override
	public int ritornaNumeroNotizie(String user,String pass) throws Exception {
		logger.info("metodo: ritornaNumeroNotizie");
		NewsDAOIface dao = new NewsDAOImpl();
		LoginDAOIface daoL = new LoginDAOImpl();
		if(!daoL.controllaLogin(user, pass)){
			 throw new Exception("COD_NOTEXIST");
		}
		else 
			return dao.getCountNotizie();
	}
	
}

package uiip.ws;

import org.apache.axis2.AxisFault;
import org.apache.log4j.Logger;
import uiip.entity.Account;
import uiip.entity.Notizia;
import uiip.gestori.iface.GestoreLoginIface;
import uiip.gestori.iface.GestoreNewsIface;
import uiip.gestori.impl.GestoreLoginImpl;
import uiip.gestori.impl.GestoreNewsImpl;

public class NewsWS {
	private static Logger logger = Logger.getLogger("logApp");
	
	public NewsWS() {
		super();
	}

	/*public Notizia[] listaNotize(String username,String password) throws AxisFault {
		logger.info("metodo: listaNotizie");
		Notizia[] lista_notizie = null;
		GestoreNewsIface gestListaNews = new GestoreNewsImpl();
		GestoreLoginIface gestLogin = new GestoreLoginImpl();
		try {
			gestLogin.controllaAccount(username, password);
			System.out.println("username in WS"+username+"Password in WS"+password);
			lista_notizie = gestListaNews.listaNotizie(username, password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new AxisFault(e.getMessage());
		}
		return lista_notizie;
	}*/
	
	public Notizia[] listaNotizeOffset(String username,String password, int inizio, int fine) throws AxisFault {
		logger.info("metodo: listaNotizie");
		Notizia[] lista_notizie = null;
		GestoreNewsIface gestListaNews = new GestoreNewsImpl();
		GestoreLoginIface gestLogin = new GestoreLoginImpl();
		try {
			gestLogin.controllaAccount(username, password);
			System.out.println("username in WS"+username+"Password in WS"+password);
			lista_notizie = gestListaNews.listaNotizieOffset(username, password, inizio, fine);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new AxisFault(e.getMessage());
		}
		return lista_notizie;
	}

	public boolean cancellaNotizia(Notizia notizia,String username,String password) throws AxisFault {
		logger.info("metodo: cancellaNotizia, in notizia.id: "
				+ notizia.getId_N() + "\"");
		GestoreNewsIface gestCanc = new GestoreNewsImpl();
		GestoreLoginIface gestLogin = new GestoreLoginImpl();
		boolean result = false;
		try {
			gestLogin.controllaAccount(username, password);
			result = gestCanc.cancellaNotizia(notizia,username, password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new AxisFault(e.getMessage());
		}
		return result;
	}

	public boolean inserisciNotizia(Notizia notizia,String username,String password) throws AxisFault {
		logger.info("metodo: inserisciNotizia, in notizia.id "
				+ notizia.getId_N() + "\"");
		GestoreNewsIface gestInserimento = new GestoreNewsImpl();
		GestoreLoginIface gestLogin = new GestoreLoginImpl();
		boolean result = false;
		try {
			gestLogin.controllaAccount(username,password);
			result = gestInserimento.inserisciNotizia(notizia,username, password);
		} catch (Exception e) {
			throw new AxisFault(e.getMessage());
		}
		return result;
	}

	public Notizia ritornaNotizia(int id_n,String username,String password) throws AxisFault {
		Notizia notizia = null;
		logger.info("metodo: ritornaNotizia, in: id_n=\"" + id_n + "\"");
		GestoreNewsIface gestNotizia = new GestoreNewsImpl();
		GestoreLoginIface gestLogin = new GestoreLoginImpl();
		try {
			gestLogin.controllaAccount(username,password);
			notizia = gestNotizia.visualizzaNotizia(id_n,username, password);
		} catch (Exception e) {
			throw new AxisFault(e.getMessage());
		}
		return notizia;
	}

	public int modificaNotizia(Notizia notizia, Account account,String username,String password)
			throws AxisFault {
		logger.info("metodo: modificaNotizia");
		GestoreNewsIface gestNotizia = new GestoreNewsImpl();
		GestoreLoginIface gestLogin = new GestoreLoginImpl();
		int risultato = 0;
		try {
			gestLogin.controllaAccount(username,password);
			risultato = gestNotizia.modifica_effettiva(notizia, account,username, password);
		} catch (Exception e) {
			throw new AxisFault(e.getMessage());
		}
		return risultato;
	}

	/*public Notizia[] visualizza_per_parametro(String param, String ricerca,String username,String password)
			throws AxisFault {
		logger.info("metodo in NewsWS:visualizza per parametro,in param+=\""
				+ param + "\"" + "ricerca=\"" + ricerca + "\"");
		GestoreNewsIface gestNews = new GestoreNewsImpl();
		GestoreLoginIface gestLogin=new GestoreLoginImpl();
		Notizia[] news;
		try {
			gestLogin.controllaAccount(username,password);
			news = gestNews.visualizza_per_parametro(param, ricerca,username, password);
		} catch (Exception e) {
			throw new AxisFault(e.getMessage());
		}
		return news;
	}*/
	
	public Notizia[] visualizza_per_parametro_offset(String param, String ricerca,
			String username, String password, int inizio, int fine) throws AxisFault {
		logger.info("metodo in NewsWS:visualizza per parametro,in param+=\""
				+ param + "\"" + "ricerca=\"" + ricerca + "\"");
		GestoreNewsIface gestNews = new GestoreNewsImpl();
		GestoreLoginIface gestLogin = new GestoreLoginImpl();
		Notizia[] news;
		try {
			gestLogin.controllaAccount(username, password);
			news = gestNews.visualizza_per_parametro_offset(param, ricerca, username,
					password, inizio, fine);
		} catch (Exception e) {
			throw new AxisFault(e.getMessage());
		}
		return news;
	}

	public boolean annullaModifica(int id_n,String username,String password) throws AxisFault {
		logger.info("metodo in NewsWS:annulla,in id_n+=\"" + id_n + "\"");
		GestoreNewsIface gestNews = new GestoreNewsImpl();
		GestoreLoginIface gestLogin = new GestoreLoginImpl();
		try {
			gestLogin.controllaAccount(username,password);
			gestNews.annulla(id_n,username, password);
		} catch (Exception e) {
			throw new AxisFault(e.getMessage());
		}
		return true;
	}

	public boolean rilasciaNotizie(Account account,String username,String password) throws AxisFault {
		logger.info("metodo in NewsWS:rilascia_notizie");
		GestoreNewsIface gestNews = new GestoreNewsImpl();
		GestoreLoginIface gestLogin = new GestoreLoginImpl();
		try {
			gestLogin.controllaAccount(username,password);
			gestNews.rilascia_notizie(account,username, password);
		} catch (Exception e) {
			throw new AxisFault(e.getMessage());
		}
		return true;
	}

	public boolean notizia_in_modifica(Notizia notizia, String username,String password) throws AxisFault {
		logger.info("metodo in NewsWS:notizia_in_modifica,in Notizia_id=\""
				+ notizia.getId_N() + "\"+user=\"" + username + "\"");
		GestoreNewsIface gestNews = new GestoreNewsImpl();
		GestoreLoginIface gestLogin = new GestoreLoginImpl();
		try {
			gestLogin.controllaAccount(username, password);
			gestNews.notizia_in_modifica(notizia, username, password);
		} catch (Exception e) {
			throw new AxisFault(e.getMessage());
		}
		return true;
	}
	
	public int numeroTotNotizie(String username,String password) throws AxisFault {
		logger.info("metodo in numeroTotNotizie");
		GestoreNewsIface gestNews = new GestoreNewsImpl();
		GestoreLoginIface gestLogin = new GestoreLoginImpl();
		int count =0;
		try {
			gestLogin.controllaAccount(username, password);
			count = gestNews.ritornaNumeroNotizie(username, password);
		} catch (Exception e) {
			throw new AxisFault(e.getMessage());
		}
		return count;
	}
}

package uiip.gestori.impl;
import java.util.logging.Logger;


import uiip.dao.iface.LoginDAOIface;
import uiip.dao.iface.NewsBatchDAOIface;
import uiip.dao.impl.LoginDAOImpl;
import uiip.dao.impl.NewsBatchDAOImpl;
import uiip.entity.Notizia;
import uiip.gestori.iface.GestoreNewsBatchIface;


public class GestoreNewsBatchImpl implements GestoreNewsBatchIface {
		

	private static Logger logger = Logger.getLogger("logApp");
	
	public GestoreNewsBatchImpl(){
		super();
	}

	@Override
	public int trasmissione_effettiva(Notizia notizia,String username, String user, String pass) throws Exception {
		logger.info("metodo: trasmissione_effettiva");
		LoginDAOIface daoL = new LoginDAOImpl();
		int result = 0;
		if(!daoL.controllaLogin(user, pass)){
			 throw new Exception("COD_NOTEXIST");
		}
		else{
			NewsBatchDAOIface dao=new NewsBatchDAOImpl();
			result = dao.total_transmission(notizia, username);
		}
		return result;
	}


	@Override
	public boolean inserisciNotiziaRicevuta(Notizia notizia) throws Exception {
		logger.info("metodo: inserisciNotiziaRicevuta");
		NewsBatchDAOIface dao = new NewsBatchDAOImpl();
		return dao.insertNewsRicevuta(notizia);
	}
	
	@Override
	public Notizia[] ritornaListaNotizieDaTrasmettere()
			throws Exception {
		logger.info("metodo:ritornaNotizieDaTrasmettere\"");
		NewsBatchDAOIface dao = new NewsBatchDAOImpl();
		Notizia[] news;
		news=dao.lista_notizie_da_trasmettere();
		return news;
	}
	
	@Override
	public boolean notiziaTrasmessa(Notizia notizia) throws Exception {
		logger.info("metodo: notiziaTrasmessa");
		NewsBatchDAOIface dao=new NewsBatchDAOImpl();
		return dao.newsTrasmessa(notizia);
		
	}
}

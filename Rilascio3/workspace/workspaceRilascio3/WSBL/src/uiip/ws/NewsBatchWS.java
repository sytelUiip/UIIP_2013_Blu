package uiip.ws;

import org.apache.axis2.AxisFault;
import org.apache.log4j.Logger;

import uiip.entity.Notizia;
import uiip.gestori.iface.GestoreLoginIface;
import uiip.gestori.iface.GestoreNewsBatchIface;
import uiip.gestori.impl.GestoreLoginImpl;
import uiip.gestori.impl.GestoreNewsBatchImpl;

public class NewsBatchWS {
	private static Logger logger = Logger.getLogger("logApp");

	public NewsBatchWS() {
		super();
	}

	public int trasmettiNotizia(Notizia notizia,String username, String user, String pass) throws AxisFault{
		logger.info("metodo: trasmettiNotizia");
		GestoreNewsBatchIface gestNotizia=new GestoreNewsBatchImpl();
		int risultato = 0;
		GestoreLoginIface gestLogin = new GestoreLoginImpl();
		try {
			gestLogin.controllaAccount(user, pass);
			risultato = gestNotizia.trasmissione_effettiva(notizia, username, user, pass);
	}catch (Exception e) {
		throw new AxisFault(e.getMessage());
	}
	return risultato;
}
}

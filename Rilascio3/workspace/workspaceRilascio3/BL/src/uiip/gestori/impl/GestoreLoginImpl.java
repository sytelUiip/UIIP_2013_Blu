package uiip.gestori.impl;

import org.apache.log4j.Logger;

import uiip.dao.iface.LoginDAOIface;
import uiip.dao.impl.LoginDAOImpl;
import uiip.entity.Account;
import uiip.gestori.iface.GestoreLoginIface;

public class GestoreLoginImpl implements GestoreLoginIface{

	private static Logger logger = Logger.getLogger("logApp");

	
	@Override
	public boolean controllaAccount(String username, String password)
			throws Exception {
		logger.info("metodo: controllaAccount, in: username=\""+username+" password=\""+password+"\"");
		LoginDAOIface dao = new LoginDAOImpl();
		return dao.controllaLogin(username, password);
	}
	
	@Override
	public Account getAccount(String user, String pass) throws Exception {
		logger.info("metodo: getAccount, in: username=\""+user+" password=\""+pass+"\"");
		LoginDAOIface dao = new LoginDAOImpl();
		return dao.getAccount(user, pass);
	}
}

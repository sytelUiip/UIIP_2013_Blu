package uiip.ws;

import org.apache.axis2.AxisFault;
import org.apache.log4j.Logger;
import uiip.entity.Account;
import uiip.gestori.iface.GestoreLoginIface;
import uiip.gestori.impl.GestoreLoginImpl;

public class LoginWS {
	
	private static Logger logger= Logger.getLogger("logApp");
	
	public LoginWS() {
		super();
	}
	
	public Account controllaLogin(String username, String password) throws AxisFault{
		logger.info("metodo: controllaLogin, in: username=\""+ username +", password=\"" +password+"\"");
		boolean result = false;
		Account account = null;
		GestoreLoginIface gestLogin = new GestoreLoginImpl();
		try {
			result = gestLogin.controllaAccount(username, password);
			account = gestLogin.getAccount(username, password);
		} catch (Exception e) {	
			throw new AxisFault(e.getMessage());  
		}
		return account;
	}
}

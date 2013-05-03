package uiip.ws;

import org.apache.axis2.AxisFault;
import org.apache.log4j.Logger;
import uiip.entity.Account;
import uiip.gestori.iface.GestoreAccountIface;
import uiip.gestori.iface.GestoreLoginIface;
import uiip.gestori.iface.GestoreNewsIface;
import uiip.gestori.impl.GestoreAccountImpl;
import uiip.gestori.impl.GestoreLoginImpl;
import uiip.gestori.impl.GestoreNewsImpl;

public class AccountWS {
	
	private static Logger logger= Logger.getLogger("logApp");
	
	public AccountWS() {
		super();
	}
	
	public Account[] listaAccountOffset(String user, String pass, int inizio, int fine) throws AxisFault {
		logger.info("metodo: listaAccount");
		Account[] lista_account = null;
		GestoreAccountIface gestListAccount = new GestoreAccountImpl();
		GestoreLoginIface gestLogin = new GestoreLoginImpl();
		try {
			gestLogin.controllaAccount(user, pass);
			lista_account = gestListAccount.listaAccountOffset(user, pass, inizio, fine);
		} catch (Exception e) {
			throw new AxisFault(e.getMessage());
		}
		return lista_account;
	}
	
	public int modificaAccount(Account account, String user, String pass) throws AxisFault {
		logger.info("metodo: modificaAccount, in: account.username=\""
				+ account.getUsername() + "\"");
		GestoreAccountIface gestAccount = new GestoreAccountImpl();
		int result = 0;
		GestoreLoginIface gestLogin = new GestoreLoginImpl();
		try {
			gestLogin.controllaAccount(user, pass);
			result = gestAccount.modificaAccount(account, user, pass);
		} catch (Exception e) {
			throw new AxisFault(e.getMessage());
		}
		return result;
	}

	public boolean cancellaAccount(Account account, String user, String pass) throws AxisFault {
		logger.info("metodo: cancellaAccount, in: account.username=\""
				+ account.getUsername() + "\"");
		GestoreAccountIface gestAccount = new GestoreAccountImpl();
		boolean result = false;
		GestoreLoginIface gestLogin = new GestoreLoginImpl();
		try {
			gestLogin.controllaAccount(user, pass);
			result = gestAccount.cancellaAccount(account, user, pass);
		} catch (Exception e) {
			throw new AxisFault(e.getMessage());
		}
		return result;
	}

	public boolean inserisciAccount(Account account, String user, String pass) throws AxisFault {
		logger.info("metodo: inserisciAccount, in: account.username=\""
				+ account.getUsername() + "\"");
		GestoreAccountIface gestAccount = new GestoreAccountImpl();
		boolean result = false;
		//GestoreLoginIface gestLogin = new GestoreLoginImpl();
		try {
			//gestLogin.controllaAccount(user, pass);
			result = gestAccount.inserisciAccount(account, user, pass);
		} catch (Exception e) {
			throw new AxisFault(e.getMessage());
		}
		return result;
	}

	public Account ritornaAccount(String username, String user, String pass) throws AxisFault {
		Account account = null;
		logger.info("metodo: getAccount, in: username=\"" + username + "\"");
		
		System.out.println("SONO ENTRATO IN RITORNA ACCOUNT WS");
		GestoreLoginIface gestLogin = new GestoreLoginImpl();
		try {
			gestLogin.controllaAccount(user, pass);
			GestoreAccountIface gestAccount = new GestoreAccountImpl();
			account = gestAccount.ritornaAccount(username, user, pass);
		} catch (Exception e) {
			throw new AxisFault(e.getMessage());
		}
		return account;
	}
	
	public int numeroTotUtenti(String username,String password) throws AxisFault {
		logger.info("metodo in numeroTotUtenti");
		GestoreAccountIface gestAccount = new GestoreAccountImpl();
		GestoreLoginIface gestLogin = new GestoreLoginImpl();
		int count =0;
		try {
			gestLogin.controllaAccount(username, password);
			count = gestAccount.ritornaNumeroAccount(username, password);
		} catch (Exception e) {
			throw new AxisFault(e.getMessage());
		}
		return count;
	}
	
}

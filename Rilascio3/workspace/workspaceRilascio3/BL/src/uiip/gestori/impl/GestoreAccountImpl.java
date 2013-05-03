package uiip.gestori.impl;

import java.util.logging.Logger;
import uiip.gestori.iface.*;
import uiip.dao.iface.AccountDAOIface;
import uiip.dao.iface.LoginDAOIface;
import uiip.dao.iface.NewsDAOIface;
import uiip.dao.impl.AccountDAOImpl;
import uiip.dao.impl.LoginDAOImpl;
import uiip.dao.impl.NewsDAOImpl;
import uiip.entity.Account;

public class GestoreAccountImpl implements GestoreAccountIface {

	private static Logger logger = Logger.getLogger("logApp");

	public GestoreAccountImpl() {
		super();
	}

	/*@Override
	public Account[] listaAccount(String user,String pass) throws Exception {
		logger.info("metodo: listaAccount");
		Account[] lista_account = null;
		LoginDAOIface daoL = new LoginDAOImpl();
		if(!daoL.controllaLogin(user, pass)){
			 throw new Exception("COD_NOTEXIST");
		}
		else{
			AccountDAOIface dao = new AccountDAOImpl();
			lista_account=dao.listAccount();
		}
		return lista_account;
	}*/
	
	@Override
	public Account[] listaAccountOffset(String user,String pass, int inizio, int fine) throws Exception {
		logger.info("metodo: listaAccount");
		Account[] lista_account = null;
		LoginDAOIface daoL = new LoginDAOImpl();
		if(!daoL.controllaLogin(user, pass)){
			 throw new Exception("COD_NOTEXIST");
		}
		else{
			AccountDAOIface dao = new AccountDAOImpl();
			lista_account=dao.listAccountOffset(inizio, fine);
		}
		return lista_account;
	}
	
	@Override
	public int modificaAccount(Account account,String user,String pass) throws Exception {
		logger.info("metodo: modificaAccount, in: account.username=\""+account.getUsername()+"\"");
		LoginDAOIface daoL = new LoginDAOImpl();
		int result = 0;
		if(!daoL.controllaLogin(user, pass)){
			 throw new Exception("COD_NOTEXIST");
		}else{
			AccountDAOIface dao = new AccountDAOImpl();
			result=dao.updateAccount(account);
		}
		return result;
	}
	
	@Override
	public boolean cancellaAccount(Account account,String user,String pass) throws Exception{
		logger.info("metodo: cancellaAccount, in: account.username=\""+account.getUsername()+"\"");
		LoginDAOIface daoL = new LoginDAOImpl();
		boolean result = false;
		if(!daoL.controllaLogin(user, pass)){
			 throw new Exception("COD_NOTEXIST");
		}else{
			AccountDAOIface dao = new AccountDAOImpl();
			result = dao.deleteAccount(account);
		}
		return result;
	}
	
	@Override
	public boolean inserisciAccount(Account account,String user,String pass) throws Exception { 
		logger.info("metodo: inserisciAccount, in: account.username=\""+account.getUsername()+"\"");
		boolean result = false;
		LoginDAOIface daoL = new LoginDAOImpl();
		System.out.println("user di gestore account: "+user);
		System.out.println("password di gestore account: "+pass);
		if(!daoL.controllaLogin(user, pass)){
			 throw new Exception("COD_NOTEXIST");
		}else{
			AccountDAOIface dao = new AccountDAOImpl();
			result = dao.insertAccount(account);
		}
		return result;
	}

	@Override
	public Account ritornaAccount(String username,String user,String pass) throws Exception {
		logger.info("metodo: ritornaAccount, in: username=\""+username+"\"");
		Account account = null;
		LoginDAOIface daoL = new LoginDAOImpl();
		if(!daoL.controllaLogin(user, pass)){
			System.out.println("prima del throw");
			 throw new Exception("COD_NOTEXIST");
		}else{
			System.out.println("****else di ritornaAccount");
			AccountDAOIface dao = new AccountDAOImpl();
			account = dao.getAccount(username);
		}
		return account;
	}
	
	@Override
	public int ritornaNumeroAccount(String user,String pass) throws Exception {
		logger.info("metodo: ritornaNumeroAccount");
		AccountDAOIface dao = new AccountDAOImpl();
		LoginDAOIface daoL = new LoginDAOImpl();
		if(!daoL.controllaLogin(user, pass)){
			 throw new Exception("COD_NOTEXIST");
		}
		else 
			return dao.getCountAccount();
	}
}

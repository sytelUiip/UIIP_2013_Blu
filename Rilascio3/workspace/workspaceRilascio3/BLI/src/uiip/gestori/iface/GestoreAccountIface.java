package uiip.gestori.iface;

import uiip.entity.Account;

public interface GestoreAccountIface {
	
	//public Account[] listaAccount(String user,String pass) throws Exception;
	public int modificaAccount(Account account,String user,String pass) throws Exception;
	public boolean cancellaAccount(Account account,String user,String pass) throws Exception;
	public boolean inserisciAccount(Account account,String user,String pass) throws Exception;
	public Account ritornaAccount(String username,String user,String pass) throws Exception;
	public Account[] listaAccountOffset(String user, String pass, int inizio, int fine)
			throws Exception;
	public int ritornaNumeroAccount(String user, String pass) throws Exception;
	
}

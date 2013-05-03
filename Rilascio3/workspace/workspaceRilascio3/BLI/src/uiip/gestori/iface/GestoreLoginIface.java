package uiip.gestori.iface;

import uiip.entity.Account;

public interface GestoreLoginIface {
	public boolean controllaAccount(String username, String password) throws Exception;
	public Account getAccount(String user, String pass) throws Exception;
}

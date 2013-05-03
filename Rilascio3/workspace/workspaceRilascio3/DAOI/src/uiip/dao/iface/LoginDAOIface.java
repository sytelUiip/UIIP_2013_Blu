package uiip.dao.iface;

import java.sql.Connection;
import java.sql.SQLException;

import uiip.entity.Account;

public interface LoginDAOIface {

	public Connection getConnection() throws Exception;
	public boolean controllaLogin(String user,String pass) throws Exception;
	public Account getAccount(String user, String pass) throws Exception;
}

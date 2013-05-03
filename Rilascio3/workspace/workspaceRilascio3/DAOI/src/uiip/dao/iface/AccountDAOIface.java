package uiip.dao.iface;

import uiip.entity.Account;
import java.sql.Connection;

public interface AccountDAOIface {
	public Connection getConnection() throws Exception;
	//public Account[] listAccount() throws Exception;
	public int updateAccount(Account account) throws Exception;
	public boolean deleteAccount(Account account) throws Exception;
	public boolean insertAccount(Account account) throws Exception;
	public Account getAccount(String username) throws Exception;
	public Account[] listAccountOffset(int inizio, int fine) throws Exception;
	public int getCountAccount() throws Exception;
}


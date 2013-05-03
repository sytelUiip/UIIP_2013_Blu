package uiip.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Ignore;
import org.junit.Test;

import uiip.dao.iface.LoginDAOIface;
import uiip.dao.impl.LoginDAOImpl;
import uiip.entity.Account;

public class TestLogin {
	LoginDAOIface daoimpl = new LoginDAOImpl();

	@Test
	public void testGetAccount() throws Exception {
		String username = "antonio.laurano@gmail.com";
		String password = "189bbbb00c5f1fb7fba9ad9285f193d1";
		Account account = daoimpl.getAccount(username, password);
		String nome = "antonio";
		String cognome = "laurano";
		assertEquals(nome, account.getNome());
		assertEquals(cognome, account.getCognome());
	}

	@Test(expected=NullPointerException.class)
	public void testGetAccountInesistente() throws Exception {
		String username = "usernameSbagliato";
		String password = "passwordSbagliata";
		Account account = daoimpl.getAccount(username, password);
	}
	
	@Test(expected=NullPointerException.class)
	public void testGetAccountSenzaParametri() throws Exception {
		String username = "";
		String password = "";
		Account account = daoimpl.getAccount(username, password);
	}
	
	@Test
	public void testControllaAccount() throws Exception{
		String username = "antonio.laurano@gmail.com";
		String password = "189bbbb00c5f1fb7fba9ad9285f193d1";
		assertTrue(daoimpl.controllaLogin(username, password));
		
	}
	@Test
	public void testControllaAccountInesistente() throws Exception{
		String username = "usernameSbagliato";
		String password = "passwordSbagliata";
		assertFalse(daoimpl.controllaLogin(username, password));
	
	}

}

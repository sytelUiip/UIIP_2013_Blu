package uiip.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;

import org.junit.Ignore;
import org.junit.Test;

import uiip.dao.iface.AccountDAOIface;
import uiip.dao.impl.AccountDAOImpl;
import uiip.entity.Account;
import uiip.entity.Gruppo;

public class TestAccount {
	AccountDAOIface daoimpl = new AccountDAOImpl();

	@Ignore
	@Test
	public void testInserisciAccount() throws Exception {
		Account account = new Account();
		String username = "antoniolaurano@yahoo.it";
		String password = "password";
		String nome = "antonio";
		String cognome = "laurano";
		String siglaG = "giorn";
		String siglaR = "red";
		account.setUsername(username);
		account.setPassword(password);
		account.setNome(nome);
		account.setCognome(cognome);
		account.setSigla_giornalista(siglaG);
		account.setSigla_redazione(siglaR);
		daoimpl.insertAccount(account);
		Account account2 = daoimpl.getAccount(username);
		assertEquals(account2.getGruppi().length, 2);

	}

	@Ignore
	@Test(expected = Exception.class)
	public void testInserisciAccountGi‡Presente() throws Exception {
		Account account = new Account();
		String username = "antoniolaurano@yahoo.it";
		String password = "password";
		String nome = "antonio";
		String cognome = "laurano";
		String siglaG = "giorn";
		String siglaR = "red";
		account.setUsername(username);
		account.setPassword(password);
		account.setNome(nome);
		account.setCognome(cognome);
		account.setSigla_giornalista(siglaG);
		account.setSigla_redazione(siglaR);
		daoimpl.insertAccount(account);

	}

	// stessi campi del primo ma con username differente
	@Ignore
	@Test(expected = SQLException.class)
	public void testInserisciAccountUsernameDiversa() throws Exception {
		Account account = new Account();
		String username = "antonioLAURANO@yahoo.it";
		String password = "password";
		String nome = "antonio";
		String cognome = "laurano";
		String siglaG = "giorn";
		String siglaR = "red";
		account.setUsername(username);
		account.setPassword(password);
		account.setNome(nome);
		account.setCognome(cognome);
		account.setSigla_giornalista(siglaG);
		account.setSigla_redazione(siglaR);
		daoimpl.insertAccount(account);

	}

	@Ignore
	@Test
	public void testRitornaAccount() throws Exception {
		Account account = new Account();
		String nome = "antonio";
		String cognome = "laurano";

		account = daoimpl.getAccount("antoniolaurano@yahoo.it");
		assertEquals(nome, account.getNome());
		assertEquals(cognome, account.getCognome());

	}

	// testa se l'utente inserito Ë un giornalista
	@Ignore
	@Test
	public void testRitornaAccountSoloGiornalista() throws Exception {
		String username = "antoniolaurano@yahoo.it";
		Account account = daoimpl.getAccount(username);
		String giorn = account.getGruppi()[0].getNomeGruppo();
		assertEquals(account.getGruppi().length, 1);
		assertEquals(giorn, "giornalista");

	}

	// verifica se l'utente non Ë presente
	@Ignore
	@Test(expected = NullPointerException.class)
	public void tesatRitornaAccountInesistente() throws Exception {
		String username = "utenteInesistente@yahoo.it";
		daoimpl.getAccount(username);
	}

	@Ignore
	@Test
	public void testAggiungiFunzionalit‡Account() throws Exception {
		Account account = new Account();
		String username = "antonio.laurano@gmail.com";

		account.setUsername(username);

		daoimpl.insertAccount(account);
		Account account2 = new Account();
		account2 = daoimpl.getAccount(username);

		assertEquals(account2.getGruppi().length, 2);
	}

	@Ignore
	@Test
	public void testModificaAccount() throws Exception {
		String username = "antoniolaurano@yahoo.it";
		Account account = daoimpl.getAccount(username);
		String password = "ppp";
		String nome = "anto";
		String cognome = "laur";
		String siglaG = "g";
		String siglaR = "r";
		account.setUsername(username);
		account.setPassword(password);
		account.setNome(nome);
		account.setCognome(cognome);
		account.setSigla_giornalista(siglaG);
		account.setSigla_redazione(siglaR);
		account.setNome(nome);
		daoimpl.updateAccount(account);
		Account account2 = daoimpl.getAccount(username);
		assertEquals(nome, account2.getNome());
		assertEquals(cognome, account2.getCognome());
	}

	
	@Test
	public void testCancellaAccountSoloGiornalista() throws Exception {
		String username = "antoniolaurano@yahoo.it";
		Account account = daoimpl.getAccount(username);
		daoimpl.deleteAccount(account);
		account = daoimpl.getAccount(username);
		assertEquals(account.getGruppi().length, 0);
		
	}

	// testa se l'utente Ë amministratore cosa avviene alla cancellazione
	@Ignore
	@Test
	public void testCancellaAccountAmministratore() throws Exception {
		String username = "antonio.laurano@gmail.com";
		Account account = daoimpl.getAccount(username);
		daoimpl.deleteAccount(account);
		account = daoimpl.getAccount(username);
		Gruppo[] gruppi = account.getGruppi();
		assertEquals(gruppi.length, 1);
		
	}

}

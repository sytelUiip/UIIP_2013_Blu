package uiip.test;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Ignore;
import org.junit.Test;

import uiip.dao.impl.NewsDAOImpl;
import uiip.entity.Account;
import uiip.entity.Notizia;

public class TestNotizia {

	NewsDAOImpl dao = new NewsDAOImpl();
	@Ignore
	@Test
	public void testInserisciNotizia() throws Exception {
		Notizia notizia = new Notizia();
		Notizia notizia2=new Notizia();
		notizia.setTitolo("Roma");
		notizia.setSottotitolo("PIOVE");
		notizia.setAutore("fradm2@gmail.com");
		notizia.setUltimodigitatore("fradm2@gmail.com");
		notizia.setTesto("UIIPER");
		assertTrue(dao.insertNews(notizia));
		
	
		notizia2.setTitolo("Bari");
		notizia2.setSottotitolo("SOLE");
		notizia2.setAutore("fradm2@gmail.com");
		notizia2.setUltimodigitatore("fradm2@gmail.com");
		notizia2.setTesto("UIIPER2");
		assertTrue(dao.insertNews(notizia2));
		
	}
	

	@Test(expected=SQLException.class)
	public void testInsertCampoMancante() throws Exception {
		Notizia notizia = new Notizia();
//		notizia.setTitolo(" Roma");
		notizia.setSottotitolo("PIOVE");
		notizia.setAutore("fradm2@gmail.com");
		notizia.setUltimodigitatore("fradm2@gmail.com");
		notizia.setTesto("UIIPER");
		dao.insertNews(notizia);
	}
	@Ignore
	@Test
	public void testCancellaNews() throws Exception{
		Notizia notizia = new Notizia();
		notizia.setId_N(114);
		notizia.setTitolo("Roma");
		notizia.setSottotitolo("PIOVE");
		notizia.setAutore("fradm2@gmail.com");
		notizia.setUltimodigitatore("fradm2@gmail.com");
		notizia.setTesto("UIIPER");
		assertTrue(dao.deleteNews(notizia));
		
		
	}
	@Ignore
	@Test(expected=Exception.class)
	public void testCancellaNewsGiaCancellata() throws Exception{
		Notizia notizia = new Notizia();
		notizia.setId_N(114);
		notizia.setTitolo("Roma");
		notizia.setSottotitolo("PIOVE");
		notizia.setAutore("fradm2@gmail.com");
		notizia.setUltimodigitatore("fradm2@gmail.com");
		notizia.setTesto("UIIPER");
		dao.deleteNews(notizia);
		
		
	}
	@Ignore
	@Test
	public void testListaNotizie() throws Exception{
		
		Notizia[] lista=dao.listNewsOffset(1, 10);
		assertTrue(lista.length<=10);
	}
	@Ignore
	@Test
	public void testVisualizzaNotizia() throws Exception{
		int id=(114);
		Notizia notizia=dao.viewNews(id);
		assertEquals(114,notizia.getId_N());
	}
	@Ignore
	@Test
	public void testVisualizzaNewsPerTitolo() throws Exception{
		String param="RomA";
		String ricerca="titolo";
		int inizio=1;
		int fine=10;
		boolean res=true;
		Notizia[] lista_notizie=dao.visualizza_per_parametro_offset(param, ricerca,inizio,fine);
		for (int i = 0; i < lista_notizie.length; i++) {
			
			if(!lista_notizie[i].getTitolo().contains(param))
				res=false;
		}
		assertTrue(res);
	}
	
	@Ignore
	@Test
	public void testVisualizzaNewsPerAutore() throws Exception{
		String param="fra";
		String ricerca="autore";
		int inizio=1;
		int fine=10;
		boolean res=true;
		Notizia[] lista_notizie=dao.visualizza_per_parametro_offset(param, ricerca,inizio,fine);
		for (int i = 0; i < lista_notizie.length; i++) {
			if(!lista_notizie[i].getAutore().contains(param))
				res=false;
		}
		assertTrue(res);
	}
	@Ignore
	@Test
	public void testVisualizzaNewsPerStato() throws Exception{
		String param="S";
		String ricerca="stato";
		int inizio=1;
		int fine=10;
		boolean res=true;
		Notizia[] lista_notizie=dao.visualizza_per_parametro_offset(param, ricerca,inizio,fine);
		for (int i = 0; i < lista_notizie.length; i++) {
			if(!lista_notizie[i].getStato().contains(param))
				res=false;
		}
		assertTrue(res);
	}
	@Ignore
	@Test
	public void testPermessoUpdate() throws Exception{
		boolean res=false;
		Notizia notizia = new Notizia();
		notizia.setId_N(115);
		notizia.setTitolo("Bari");
		notizia.setSottotitolo("SOLE");
		notizia.setAutore("fradm2@gmail.com");
		notizia.setUltimodigitatore("fradm2@gmail.com");
		notizia.setTesto("UIIPER2");
		String user="m@gmail.com";
		res=dao.permesso_update(notizia, user);
		assertTrue(res);
		
	}
	@Ignore
	@Test
	public void testUpdateNews() throws Exception{
		int res=0;
		Notizia notizia = new Notizia();
		notizia.setId_N(115);
		notizia.setTitolo("Bari");
		notizia.setSottotitolo("Solemareluna");
		notizia.setAutore("fradm2@gmail.com");
		notizia.setUltimodigitatore("fradm2@gmail.com");
		notizia.setTesto("UIIPER22222MARE");
		notizia.setLunghezzatesto(notizia.getTesto().length());
		res=dao.updateNews(notizia);
		assertEquals(1, res);
		
	}
	@Ignore
	@Test
	public void testNews_in_update() throws Exception{
		boolean res=false;
		Notizia notizia = new Notizia();
		notizia.setId_N(115);
		notizia.setTitolo("Bari");
		notizia.setSottotitolo("Solemareluna");
		notizia.setAutore("fradm2@gmail.com");
		notizia.setUltimodigitatore("m@gmail.com");
		notizia.setTesto("UIIPER22222MARE");
		String user=("m@gmail.com");
		notizia.setLunghezzatesto(notizia.getTesto().length());
		res=dao.news_in_update(notizia, user);
		assertTrue(res);
	}
	@Ignore
	@Test
	public void testPermessoUpdateLockuno() throws Exception{
		boolean res=false;
		Notizia notizia = new Notizia();
		notizia.setId_N(115);
		notizia.setTitolo("Bari");
		notizia.setSottotitolo("Solemareluna");
		notizia.setAutore("fradm2@gmail.com");
		notizia.setUltimodigitatore("fradm2@gmail.com");
		notizia.setTesto("UIIPER22222MARE");
		String user="fradm2@gmail.com";
		res=dao.permesso_update(notizia, user);
		assertFalse(res);
		
	}
	@Ignore
	@Test
	public void testPermessoUpdateLockdue() throws Exception{
		boolean res=false;
		Notizia notizia = new Notizia();
		notizia.setId_N(115);
		notizia.setTitolo("Bari");
		notizia.setSottotitolo("Solemareluna");
		notizia.setAutore("fradm2@gmail.com");
		notizia.setUltimodigitatore("fradm2@gmail.com");
		notizia.setTesto("UIIPER22222MARE");
		String user="m@gmail.com";
		res=dao.permesso_update(notizia, user);
		assertTrue(res);
		
	}
	@Ignore
	@Test
	public void testPermessoUpdateStato() throws Exception{
		boolean res=false;
		String user="m@gmail.com";
		Notizia notizia = new Notizia();
		notizia.setId_N(114);
		notizia.setTitolo("Roma");
		notizia.setSottotitolo("PIOVE");
		notizia.setAutore("fradm2@gmail.com");
		notizia.setUltimodigitatore("fradm2@gmail.com");
		notizia.setTesto("UIIPER");
		res=dao.permesso_update(notizia, user);
		assertFalse(res);
		
	}
	@Ignore
	@Test
	public void testCancellaNewsLockata() throws Exception{
		boolean res=false;
		Notizia notizia = new Notizia();
		notizia.setId_N(115);
		notizia.setTitolo("Bari");
		notizia.setSottotitolo("Solemareluna");
		notizia.setAutore("fradm2@gmail.com");
		notizia.setUltimodigitatore("fradm2@gmail.com");
		notizia.setTesto("UIIPER22222MARE");
		assertFalse(dao.deleteNews(notizia));
		
		
	}
	@Ignore
	@Test
	public void testCancellaNewsLockataUltimoAcc() throws Exception{
		boolean res=false;
		Notizia notizia = new Notizia();
		notizia.setId_N(115);
		notizia.setTitolo("Bari");
		notizia.setSottotitolo("Solemareluna");
		notizia.setAutore("fradm2@gmail.com");
		notizia.setUltimodigitatore("fradm2@gmail.com");
		notizia.setUltimoAccesso("m@gmail.com");
		notizia.setTesto("UIIPER22222MARE");
		assertTrue(dao.deleteNews(notizia));
	
		
	}
	@Ignore
	@Test
	public void cancellaUpdateNews()throws Exception{
		boolean res=false;
		Notizia notizia = new Notizia();
		notizia.setId_N(115);
		res=dao.cancellaUpdateNews(115);
		assertTrue(res);
		
	}
	
	@Test
	public void Testrilascia_News()throws Exception{
		boolean res=false;
		Account acc=new Account();
		acc.setNome("Matteo");
		acc.setCognome("Sabatini");
		acc.setUsername("m@gmail.com");
		acc.setPassword("150be5b860e60a7fc7c7d9b9815e93d1");
		acc.setSigla_redazione("red19");
		acc.setSigla_giornalista("MA_SA");
		acc.setStato("A");
		res=dao.rilascia_notizie(acc);
		assertTrue(res);
		
	}

}
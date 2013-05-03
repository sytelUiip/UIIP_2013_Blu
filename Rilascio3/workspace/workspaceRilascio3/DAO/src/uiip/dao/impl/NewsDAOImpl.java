package uiip.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import oracle.jdbc.OracleTypes;

import org.apache.log4j.Logger;

import uiip.dao.iface.NewsDAOIface;
import uiip.entity.Account;
import uiip.entity.Notizia;
import uiip.factory.impl.ConnectionFactory;

public class NewsDAOImpl implements NewsDAOIface {

	private static Logger logger = Logger.getLogger("logApp");

	Connection connection = null;
	PreparedStatement ptmt = null;
	ResultSet resultSet = null;

	public NewsDAOImpl() {
		super();
	}

	@Override
	public Connection getConnection() throws Exception {
		Connection conn = null;
		conn = ConnectionFactory.getInstance().getConnection();
		return conn;
	}

	@Override
	public boolean insertNews(Notizia notizia) throws Exception {
		logger.info("metodo: insertNews, in: notizia.id_n=\""
				+ notizia.getId_N() + "\"");
		boolean result = false;
		connection = getConnection();
		String titolo = notizia.getTitolo();
		String sottotitolo = notizia.getSottotitolo();
		String autore = notizia.getAutore();
		String ultimoDig = notizia.getUltimodigitatore();
		String testo = notizia.getTesto();
		int lunghezza = notizia.getTesto().length();
		CallableStatement stm;
		try {
			stm = connection.prepareCall("{call INSERT_NOTIZIA(?,?,?,?,?,?)}");
			stm.setString(1, titolo);
			stm.setString(2, sottotitolo);
			stm.setString(3, autore);
			stm.setString(4, ultimoDig);
			stm.setString(5, testo);
			stm.setInt(6, lunghezza);
			stm.execute();
			result = true;
		} catch (SQLException e) {
			connection.rollback();
			result = false;
			throw e;
		}
		connection.commit();
		return result;
	}

	/*@Override
	public Notizia[] listNews() throws Exception {
		logger.info("metodo: listNews");
		connection = getConnection();
		Notizia[] lista_notizie = null;
		boolean result = false;
		CallableStatement stm;
		try {
			stm = connection.prepareCall("{call ALL_NOTIZIA(?)}");
			stm.registerOutParameter(1, OracleTypes.CURSOR);
			result = stm.execute();
			ResultSet rs = (ResultSet) stm.getObject(1);
			ArrayList<Notizia> lista = new ArrayList<Notizia>();
			while (rs.next()) {
				Notizia news = new Notizia();
				news.setId_N(rs.getInt(1));
				news.setTitolo(rs.getString(2));
				news.setAutore(rs.getString(3));
				news.setDatacreazione(rs.getString(4));
				news.setDatatrasmissione(rs.getString(5));
				news.setStato(rs.getString(6));
				news.setLock_n(rs.getString(7));
				news.setUltimodigitatore(rs.getString(8));
				news.setSottotitolo(rs.getString(9));
				news.setTesto(rs.getString(10));
				news.setUltimoAccesso(rs.getString(11));
				lista.add(news);
			}
			lista_notizie = new Notizia[lista.size()];
			for (int i = 0; i < lista.size(); i++) {
				lista_notizie[i] = lista.get(i);
			}
		} catch (SQLException e) {
			connection.rollback();
			result = false;
			throw e;
		}
		connection.commit();
		return lista_notizie;
	}*/
	
	@Override
	public Notizia[] listNewsOffset(int inizio, int fine) throws Exception {
		logger.info("metodo: listNews");
		connection = getConnection();
		Notizia[] lista_notizie = null;
		CallableStatement stm;
		try {
			stm = connection
			.prepareCall("{call FILTRO_ALL_NOTIZIA(?,?,?)}");
			stm.registerOutParameter(1, OracleTypes.CURSOR);
			stm.setInt(2, inizio);
			stm.setInt(3, fine);
			stm.execute();
			ResultSet rs = (ResultSet) stm.getObject(1);
			ArrayList<Notizia> lista = new ArrayList<Notizia>();
			while (rs.next()) {
				Notizia news = new Notizia();
				news.setId_N(rs.getInt(1));
				news.setTitolo(rs.getString(2));
				news.setAutore(rs.getString(3));
				news.setDatacreazione(rs.getString(4));
				news.setDatatrasmissione(rs.getString(5));
				news.setStato(rs.getString(6));
				news.setLock_n(rs.getString(7));
				news.setUltimodigitatore(rs.getString(8));
				news.setSottotitolo(rs.getString(9));
				news.setTesto(rs.getString(10));
				news.setUltimoAccesso(rs.getString(11));
				lista.add(news);
			}
			lista_notizie = new Notizia[lista.size()];
			for (int i = 0; i < lista.size(); i++) {
				lista_notizie[i] = lista.get(i);
			}
		} catch (SQLException e) {
			connection.rollback();
			
			throw e;
		}
		connection.commit();
		return lista_notizie;
	}

	@Override
	public boolean deleteNews(Notizia notizia) throws Exception {
		
		logger.info("metodo: deleteNews, in: notizia.id_N=\""
				+ notizia.getId_N() + "\"");
		connection = getConnection();
		int id = notizia.getId_N();
		String lock = null;
		String ultimoDig = null;
		boolean permesso = false;
		CallableStatement stm;
		try {
			stm = connection.prepareCall("{call GET_STATO_NOTIZIA(?,?)}");
			stm.registerOutParameter(1, OracleTypes.CURSOR);
			stm.setInt(2, id);
			stm.execute();
			ResultSet rs = (ResultSet) stm.getObject(1);
			String stato = "";
			while (rs.next()) {
				stato = rs.getString(1);
			}
			if(stato.equals("S")){
				stm = connection.prepareCall("{call GET_LOCK_NOTIZIA(?,?)}");
				stm.registerOutParameter(1, OracleTypes.CURSOR);
				stm.setInt(2, id);
				stm.execute();
				rs = (ResultSet) stm.getObject(1);
				while (rs.next()) {
					lock = rs.getString(1);
				}
				if (lock.equals("Y")) {
					stm = connection.prepareCall("{call GET_ULTIMO_ACCESSO(?,?)}");
					stm.registerOutParameter(1, OracleTypes.CURSOR);
					stm.setInt(2, id);
					stm.execute();
					rs = (ResultSet) stm.getObject(1);
					while (rs.next()) {
						ultimoDig = rs.getString(1);
					}
					if (ultimoDig.equals(notizia.getUltimoAccesso()))
						permesso = true;
				} else
					permesso = true;
				if (permesso) {
					stm = connection.prepareCall("{call DELETE_NOTIZIA(?)}");
					stm.setInt(1, id);
					stm.executeUpdate();
				}
			}else{
				permesso = false;
				throw new Exception("COD_NON_EDIT");
			}
		} catch (SQLException e) {
			connection.rollback();
			permesso = false;
			throw e;
		}
		connection.commit();
		return permesso;
	}

	@Override
	public Notizia viewNews(int id_n) throws Exception {
		logger.info("metodo: viewNews, in: id_n=\"" + id_n + "\"");
		connection = getConnection();
		CallableStatement st;
		Notizia news = null;
		
		try {
			st = connection.prepareCall("{call GET_INFO_NOTIZIA(?,?)}");
			st.registerOutParameter(1, OracleTypes.CURSOR);
			st.setInt(2, id_n);
			 st.execute();
			ResultSet rs = (ResultSet) st.getObject(1);
			while (rs.next()) {
				news = new Notizia();
				System.out.println("1=" + rs.getString(1));
				news.setId_N(rs.getInt(1));
				news.setLock_n(rs.getString(2));
				news.setStato(rs.getString(3));
				news.setTitolo(rs.getString(4));
				news.setSottotitolo(rs.getString(5));
				news.setAutore(rs.getString(6));
				news.setUltimodigitatore(rs.getString(7));
				news.setDatacreazione(rs.getString(8));
				news.setDatatrasmissione(rs.getString(9));
				news.setTesto(rs.getString(10));
				news.setLunghezzatesto(rs.getInt(11));
				news.setUltimoAccesso(rs.getString(12));
			}
		} catch (SQLException e) {
			connection.rollback();
			
			throw e;
		}
		connection.commit();
		return news;
	}

	/*@Override
	public Notizia[] visualizza_per_parametro(String param, String ricerca)
			throws Exception {
		logger.info("metodo: visualizza_per_parametro, in: param= \"" + param
				+ "\" ,ricerca=\"" + ricerca + "\"");
		connection = getConnection();
		Notizia[] lista_notizie;
		CallableStatement stm = null;
		boolean result = false;
		try {
			if (ricerca.equals("titolo")) {
				stm = connection
						.prepareCall("{call SEACH_NOTIZIA_TITOLO(?,?)}");
				stm.registerOutParameter(1, OracleTypes.CURSOR);
				stm.setString(2, param);
			} else if (ricerca.equals("autore")) {
				stm = connection
						.prepareCall("{call SEACH_NOTIZIA_AUTORE(?,?)}");
				stm.registerOutParameter(1, OracleTypes.CURSOR);
				stm.setString(2, param);
			} else if (ricerca.equals("stato")) {
				stm = connection.prepareCall("{call SEACH_NOTIZIA_STATO(?,?)}");
				stm.registerOutParameter(1, OracleTypes.CURSOR);
				stm.setString(2, param);
			}
			result = stm.execute();
			ResultSet rs = (ResultSet) stm.getObject(1);
			ArrayList<Notizia> lista = new ArrayList<Notizia>();
			while (rs.next()) {
				Notizia news = new Notizia();
				news.setId_N(rs.getInt(1));
				news.setTitolo(rs.getString(2));
				news.setAutore(rs.getString(3));
				news.setDatacreazione(rs.getString(4));
				news.setDatatrasmissione(rs.getString(5));
				news.setStato(rs.getString(6));
				news.setLock_n(rs.getString(7));
				news.setUltimodigitatore(rs.getString(8));
				lista.add(news);
			}
			lista_notizie = new Notizia[lista.size()];
			for (int i = 0; i < lista.size(); i++) {
				lista_notizie[i] = lista.get(i);
			}
		} catch (SQLException e) {
			connection.rollback();
			result = false;
			throw e;
		}
		connection.commit();
		return lista_notizie;
	}*/
	
	@Override
	public Notizia[] visualizza_per_parametro_offset(String param, String ricerca, int inizio, int fine)
			throws Exception {
		logger.info("metodo: visualizza_per_parametro, in: param= \"" + param
				+ "\" ,ricerca=\"" + ricerca + "\"");
		connection = getConnection();
		Notizia[] lista_notizie;
		CallableStatement stm = null;
		
		try {
			if (ricerca.equals("titolo")) {
				stm = connection
						.prepareCall("{call FILTRO_SEACH_NOTIZIA_TITOLO(?,?,?,?)}");
				stm.registerOutParameter(1, OracleTypes.CURSOR);
				stm.setInt(2, inizio);
				stm.setInt(3, fine);
				stm.setString(4, param);
			} else if (ricerca.equals("autore")) {
				stm = connection
						.prepareCall("{call FILTRO_SEACH_NOTIZIA_AUTORE(?,?,?,?)}");
				stm.registerOutParameter(1, OracleTypes.CURSOR);
				stm.setInt(2, inizio);
				stm.setInt(3, fine);
				stm.setString(4, param);
			} else if (ricerca.equals("stato")) {
				stm = connection.prepareCall("{call FILTRO_SEACH_NOTIZIA_STATO(?,?,?,?)}");
				stm.registerOutParameter(1, OracleTypes.CURSOR);
				stm.setInt(2, inizio);
				stm.setInt(3, fine);
				stm.setString(4, param);
			}
			stm.execute();
			ResultSet rs = (ResultSet) stm.getObject(1);
			ArrayList<Notizia> lista = new ArrayList<Notizia>();
			while (rs.next()) {
				Notizia news = new Notizia();
				news.setId_N(rs.getInt(1));
				news.setTitolo(rs.getString(2));
				news.setAutore(rs.getString(3));
				news.setDatacreazione(rs.getString(4));
				news.setDatatrasmissione(rs.getString(5));
				news.setStato(rs.getString(6));
				news.setLock_n(rs.getString(7));
				news.setUltimodigitatore(rs.getString(8));
				lista.add(news);
			}
			lista_notizie = new Notizia[lista.size()];
			for (int i = 0; i < lista.size(); i++) {
				lista_notizie[i] = lista.get(i);
			}
		} catch (SQLException e) {
			connection.rollback();
			
			throw e;
		}
		connection.commit();
		System.out.println("Numero notizie trovate: "+lista_notizie.length);
		return lista_notizie;
	}

	@Override
	public boolean cancellaUpdateNews(int id_n) throws Exception {
		logger.info("metodo: cancellaUpdateNews, in: Notiza.id_n=\"" + id_n
				+ " \"");
		connection = getConnection();
		boolean result = false;
		CallableStatement stm = connection
				.prepareCall("{call ANNULLA_MODIFICA(?)}");
		try {
			stm.setInt(1, id_n);

			stm.execute();
			stm = connection.prepareCall("{call GET_LOCK_NOTIZIA(?,?)}");
			stm.registerOutParameter(1, OracleTypes.CURSOR);
			stm.setInt(2, id_n);
			stm.execute();
			ResultSet rs = (ResultSet) stm.getObject(1);
			while (rs.next()) {
				String lock = rs.getString(1);
				if (lock.equals("Y"))
					result = false;
				else
					result = true;
			}
		} catch (SQLException e) {
			connection.rollback();
			result = false;
			throw e;
		}
		connection.commit();
		return result;
	}

	@Override
	public boolean rilascia_notizie(Account account) throws Exception {
		logger.info("metodo: rilascia_notizie, in: Account.username=\""
				+ account.getUsername() + " \"");
		connection = getConnection();
		boolean result = false;
		String user = account.getUsername();
		CallableStatement stm;
		try {
			stm = connection
					.prepareCall("{call RILASCIA_ALL_NOTIZIE(?)}");
		
		stm.setString(1, user);
		stm.execute();
		result=true;
		} catch (SQLException e) {
			connection.rollback();
			result = false;
			throw e;
		}
		connection.commit();
		return result;

	}

	@Override
	public boolean news_in_update(Notizia notizia, String user) throws Exception{
		logger.info("metodo: news_in_update");
		connection = getConnection();
		int id_n = notizia.getId_N();
		CallableStatement stm;
		boolean result = false;
		try {
			if (permesso_update(notizia, user)) {
				stm = connection
						.prepareCall("{call SET_ULTIMO_ACCESSO_E_LOCK(?,?)}");
				stm.setInt(1, id_n);
				stm.setString(2, user);
				stm.execute();
				result = true;
			} else {
				result = false;
			}
		} catch (SQLException e) {
			connection.rollback();
			result = false;
			throw e;
		} catch (Exception e) {
			connection.rollback();
			result = false;
			throw e;
		}
		connection.commit();
		return result;
	}

	public boolean permesso_update(Notizia notizia, String user) throws Exception{
		logger.info("metodo: permesso_update, in: notizia.id_N=\""
				+ notizia.getId_N() + "\"");
		boolean permesso = false;
		
		connection = getConnection();
		int id_n = notizia.getId_N();

		String ultimoDigi;
		CallableStatement stm;
		CallableStatement stm1;
		try {
			stm = connection.prepareCall("{call GET_LOCK_NOTIZIA(?,?)}");
		
		stm.registerOutParameter(1, OracleTypes.CURSOR);
		stm.setInt(2, id_n);
		stm.execute();
		ResultSet rs = (ResultSet) stm.getObject(1);
		while (rs.next()) {
			String lock = rs.getString(1);
			if (lock.equals("Y")) {
				ultimoDigi = user;
				stm = connection.prepareCall("{call GET_ULTIMO_ACCESSO(?,?)");
				stm.registerOutParameter(1, OracleTypes.CURSOR);
				stm.setInt(2, id_n);
				stm.execute();
				rs = (ResultSet) stm.getObject(1);
				while (rs.next()) {
					String ultimo = rs.getString(1);
					if (ultimo.equals(ultimoDigi)) {
						permesso = true;
					} else {
						permesso= false;
					}
				}
			} else {
				permesso = true;
			}
		}
		if (permesso == true) {
			stm1 = connection.prepareCall("{call GET_STATO_NOTIZIA(?,?)}");
			stm1.registerOutParameter(1, OracleTypes.CURSOR);
			stm1.setInt(2, id_n);
			stm1.execute();
			ResultSet rst = (ResultSet) stm1.getObject(1);
			while (rst.next()) {
				String stato = rst.getString(1);
				if (!stato.equals("S")) {
					permesso = false;
				}
			}
		}
		} catch (SQLException e) {
			connection.rollback();
			permesso = false;
			throw e;
		}
		connection.commit();
		return permesso;
	}

	public int updateNews(Notizia notizia) throws Exception {
		logger.info("metodo: updateNews in: notizia.titolo=\""
				+ notizia.getTitolo() + "\"");
		connection = getConnection();
		CallableStatement stm;
		int result = 0;
		try {
			stm = connection
					.prepareCall("{call REGISTRA_NOTIZIA(?,?,?,?,?,?)}");
			stm.setInt(1, notizia.getId_N());
			stm.setString(2, notizia.getTitolo());
			stm.setString(3, notizia.getSottotitolo());
			stm.setString(4, notizia.getUltimodigitatore());
			stm.setString(5, notizia.getTesto());
			stm.setInt(6, notizia.getLunghezzatesto());
			stm.execute();
			result = 1;
		} catch (SQLException e) {
			connection.rollback();
			result=0;
			throw e;
		}
		connection.commit();
		return result;
	}

	public int total_update(Notizia notizia, String user) throws Exception {
		logger.info("metodo: total_update, in: notizia.id_N=\""
				+ notizia.getId_N() + "\"");
		boolean result = permesso_update(notizia, user);
		if (result)
			return updateNews(notizia);
		else
			return 0;
	}

	@Override
	public int getCountNotizie()throws Exception{
		logger.info("metodo: getCountNotizie");
		connection = getConnection();
		int count=0;
		CallableStatement stm;
		try {
			stm = connection.prepareCall("{call GET_COUNT_NOTIZIE(?)}");
			stm.registerOutParameter(1, OracleTypes.CURSOR);
			stm.execute();
			ResultSet rs = (ResultSet) stm.getObject(1);
			while (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			connection.rollback();
			throw e;
		}
		connection.commit();
		return count;
	}
}

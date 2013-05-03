package uiip.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import oracle.jdbc.OracleTypes;

import org.apache.log4j.Logger;
import uiip.dao.iface.NewsBatchDAOIface;
import uiip.entity.Notizia;
import uiip.factory.impl.ConnectionFactory;

public class NewsBatchDAOImpl implements NewsBatchDAOIface {

	private static Logger logger = Logger.getLogger("logApp");

	Connection connection = null;
	PreparedStatement ptmt = null;
	ResultSet resultSet = null;

	public NewsBatchDAOImpl() {
		super();
	}

	@Override
	public Connection getConnection() throws Exception {
		Connection conn = null;
		conn = ConnectionFactory.getInstance().getConnection();
		return conn;
	}

	public boolean transmissionNews(Notizia notizia, String user)
			throws Exception {
		logger.info("metodo: transmissionNews");
		connection = getConnection();
		int id_n = notizia.getId_N();
		boolean result = false;
		CallableStatement stm;
		try {
			stm = connection
					.prepareCall("{call UPDATE_NOTIZIA_IN_CODA_TX(?,?)}");
			stm.setInt(1, id_n);
			stm.setString(2, user);
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

	public boolean permesso_transmission(Notizia notizia, String user) throws Exception {
		logger.info("metodo: permesso_update, in: notizia.id_N=\""
				+ notizia.getId_N() + "\"");
		boolean permesso = true;
		
		connection = getConnection();
		int id_n = notizia.getId_N();
		CallableStatement stm;
		CallableStatement stm1;
		String ultimoDigi = null;
		
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
					if (!stato.equals("S"))
						permesso = false;
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

	@Override
	public int total_transmission(Notizia notizia, String user)
			throws Exception {
		logger.info("metodo: total_transmission");
		int res = 0;
		boolean result = permesso_transmission(notizia, user);
		if (result) {
			transmissionNews(notizia, user);
			res = 1;
			return res;
		} else
			return res;
	}

	@Override
	public boolean insertNewsRicevuta(Notizia notizia) throws Exception {
		logger.info("metodo: insertNewsRicevuta");
		boolean result = false;
		connection = getConnection();
		String titolo = notizia.getTitolo();
		String sottotitolo = notizia.getSottotitolo();
		String autore = notizia.getAutore();
		String datacreazione = notizia.getDatacreazione();
		String datacreaz = datacreazione.substring(0, 10);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date dataCreaz = sdf.parse(datacreaz);
		java.sql.Date dCreaz = new java.sql.Date(dataCreaz.getTime());
		String testo = notizia.getTesto();
		int lunghezza = notizia.getTesto().length();
		CallableStatement stm;
		try {
			stm = connection
					.prepareCall("{call INSERT_NOTIZIA_RICEVUTA(?,?,?,?,?,?)}");
			stm.setString(1, titolo);
			stm.setString(2, sottotitolo);
			stm.setString(3, autore);
			stm.setDate(4, dCreaz);
			stm.setString(5, testo);
			stm.setInt(6, lunghezza);
			
			stm.execute();
			result = true;
		} catch (Exception e) {
			connection.rollback();
			result = false;
			throw e;
		}
		connection.commit();
		return result;
	}

	@Override
	public Notizia[] lista_notizie_da_trasmettere() throws Exception {
		logger.info("metodo: lista_notizie_da_trasmettere");
		connection = getConnection();
		Notizia[] lista_notizie;
		CallableStatement stm = null;
		
		try {
			stm = connection.prepareCall("{call ALL_NOTIZIE_DA_TRASMETTERE(?)}");
			stm.registerOutParameter(1, OracleTypes.CURSOR);
//			stm.setString(2, "Q");
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
		} catch (Exception e) {
			connection.rollback();
			
			throw e;
		}
		connection.commit();
		return lista_notizie;
	}

	@Override
	public boolean newsTrasmessa(Notizia notizia) throws Exception {
		logger.info("metodo: newsTrasmessa");
		connection = getConnection();
		int id_n = notizia.getId_N();
		boolean result = false;
		CallableStatement stm;
		try {
			stm = connection.prepareCall("{call UPDATE_NOTIZIA_TRASMESSA(?)}");

			stm.setInt(1, id_n);
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
}

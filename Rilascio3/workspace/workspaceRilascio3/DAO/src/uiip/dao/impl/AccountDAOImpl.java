package uiip.dao.impl;

import java.sql.*;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import oracle.jdbc.OracleTypes;

import uiip.factory.impl.ConnectionFactory;

import uiip.dao.iface.AccountDAOIface;
import uiip.entity.Account;
import uiip.entity.Gruppo;

public class AccountDAOImpl implements AccountDAOIface {

	private static Logger logger=Logger.getLogger("logApp");

	Connection connection = null;
	PreparedStatement ptmt = null;
	ResultSet resultSet = null;

	public AccountDAOImpl() {
		super();
	}

	@Override
	public Connection getConnection() throws Exception {
		Connection conn = null;
		conn = ConnectionFactory.getInstance().getConnection();
		return conn;
	}

	@Override
	public boolean insertAccount(Account account) throws Exception {
		logger.info("metodo: insertAccount, in: account.username=\""
				+ account.getUsername() + "\"");
		boolean result = false;
		connection = getConnection();
		String nome = account.getNome();
		String cognome = account.getCognome();
		String user = account.getUsername();
		String pass = account.getPassword();
		String siglag = account.getSigla_giornalista();
		String siglar = account.getSigla_redazione();
		CallableStatement stm;
		try {
			stm = connection.prepareCall("{call EXIST_AMM_E_GIORN(?,?)}");
			stm.registerOutParameter(1, OracleTypes.CURSOR);
			stm.setString(2, user);
			stm.execute();
			ResultSet rs = (ResultSet) stm.getObject(1);
			while (rs.next()) {
				if (rs.getInt(1) == 2) {
					throw new Exception("COD_INS");
				} else if (rs.getInt(1) == 1) {
					stm = connection.prepareCall("{call INSERT_ASS_ACC_GRUP(?,?)}");
					stm.setString(1, user);
					stm.setString(2, "g");
					stm.execute();
					result = true;
				} else {
					stm = connection.prepareCall("{call INSERT_ACCOUNT(?,?,?,?,?,?)}");
					stm.setString(1, nome);
					stm.setString(2, cognome);
					stm.setString(3, user);
					stm.setString(4, pass);
					stm.setString(5, siglar);
					stm.setString(6, siglag);
					stm.execute();
					stm = connection.prepareCall("{call INSERT_ASS_ACC_GRUP(?,?)}");
					stm.setString(1, user);
					stm.setString(2, "g");
					stm.execute();
					result = true;
				}
			}	
		} catch (SQLException e) {
			connection.rollback();
			result = false;
			throw e;
		}
		connection.commit();
		return result;
	}

	/*@Override
	public Account[] listAccount() throws Exception {
		logger.info("metodo: listAccount");
		connection = getConnection();
		Account[] lista_account = null;
		CallableStatement stm;
		boolean result = false;
		try {
			stm = connection.prepareCall("call ALL_ACCOUNT(?)");
			stm.registerOutParameter(1, OracleTypes.CURSOR);
			result = stm.execute();
			ResultSet rs = (ResultSet) stm.getObject(1);
			ArrayList<Account> lista = new ArrayList<Account>();
			while (rs.next()) {
				Account acc = new Account();
				acc.setNome(rs.getString(1));
				acc.setCognome(rs.getString(2));
				acc.setUsername(rs.getString(3));
				acc.setPassword(rs.getString(4));
				acc.setSigla_redazione(rs.getString(5));
				acc.setSigla_giornalista(rs.getString(6));
				acc.setStato(rs.getString(7));
				lista.add(acc);
			}
			lista_account = new Account[lista.size()];
			for (int i = 0; i < lista.size(); i++) {
				lista_account[i] = lista.get(i);
			}
		} catch (SQLException e) {
			connection.rollback();
			result = false;
			throw e;
		}
		connection.commit();
		return lista_account;
	}*/
	
	@Override
	public Account[] listAccountOffset(int inizio, int fine) throws Exception {
		logger.info("metodo: listAccountOffset");
		System.out.println("inizio: "+inizio);
		System.out.println("fine: "+fine);
		connection = getConnection();
		
		System.out.println("dopo getconnection");
		Account[] lista_account = null;
		CallableStatement stm;
		
		try {
			stm = connection.prepareCall("{call FILTRO_ALL_ACCOUNT(?,?,?)}");
			stm.registerOutParameter(1, OracleTypes.CURSOR);
			stm.setInt(2, inizio);
			stm.setInt(3, fine);
			System.out.println("prima di execute");
			stm.execute();
			System.out.println("DOPO EXECUTE");
			ResultSet rs = (ResultSet) stm.getObject(1);
			ArrayList<Account> lista = new ArrayList<Account>();
			while (rs.next()) {
				Account acc = new Account();
				acc.setNome(rs.getString(1));
				acc.setCognome(rs.getString(2));
				acc.setUsername(rs.getString(3));
				acc.setPassword(rs.getString(4));
				acc.setSigla_redazione(rs.getString(5));
				acc.setSigla_giornalista(rs.getString(6));
				acc.setStato(rs.getString(7));
				lista.add(acc);
				System.out.println("DENTRO WHILE");
			}
			System.out.println("LUNFGEZZA LISTA: "+lista.size());
			lista_account = new Account[lista.size()];
			for (int i = 0; i < lista.size(); i++) {
				lista_account[i] = lista.get(i);
			}
		} catch (SQLException e) {
			System.out.println("SONO QUI??: "+e.getMessage());
			connection.rollback();
			
			throw e;
		}
		connection.commit();
		return lista_account;
	}

	@Override
	public int updateAccount(Account account) throws Exception {
		logger.info("metodo: updateAccount, in: account.username=\""
				+ account.getUsername() + "\"");
		connection = getConnection();
		CallableStatement stm;
		int result = 0;
		try {
			stm = connection.prepareCall("{call UPDATE_ACCOUNT(?,?,?,?,?,?)}");
			stm.setString(1, account.getNome());
			stm.setString(2, account.getCognome());
			stm.setString(3, account.getUsername());
			stm.setString(4, account.getPassword());
			stm.setString(5, account.getSigla_redazione());
			stm.setString(6, account.getSigla_giornalista());
			stm.executeUpdate();
			result=1;
		} catch (SQLException e) {
			connection.rollback();
			result = 0;
			throw e;
		}
			connection.commit();
		return result;
	}

	@Override
	public boolean deleteAccount(Account account) throws Exception {
		boolean result = false;
		logger.info("metodo: deleteAccount, in: account.username=\""+ account.getUsername() + "\"");
		connection = getConnection();
		String username = account.getUsername();
		CallableStatement stm;
		try {
			stm = connection.prepareCall("{call DELETE_ACCOUNT(?)}");
			stm.setString(1, username);
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

	@Override
	public Account getAccount(String username) throws Exception{
		logger.info("metodo: getAccount, in: username=\""+username+"\"");
		connection = getConnection();
		CallableStatement st;
		Account acc=null;
		
		try {
			st= connection
			.prepareCall("{call GET_INFO_USER(?,?)}");
			st.registerOutParameter(1, OracleTypes.CURSOR);
			st.setString(2, username);
			st.execute();
			ResultSet rs = (ResultSet) st.getObject(1);
			ArrayList<Gruppo> gruppi = new ArrayList<Gruppo>();
			while (rs.next()) {
				acc= new Account();
				acc.setNome(rs.getString(1));
				acc.setCognome(rs.getString(2));
				acc.setUsername(rs.getString(3));
				acc.setPassword(rs.getString(4));
				acc.setSigla_redazione(rs.getString(5));
				acc.setSigla_giornalista(rs.getString(6));
			}
			if(acc!=null){
			st = connection.prepareCall("{call GET_GRUPPO(?,?)}");
			st.registerOutParameter(1, OracleTypes.CURSOR);
			st.setString(2, username);
			st.execute();
			rs = (ResultSet) st.getObject(1);
			while (rs.next()) {
				Gruppo gruppo = new Gruppo();
				gruppo.setNomeGruppo(rs.getString(1));
				gruppi.add(gruppo);
			}
			Gruppo[] ar_gruppi = new Gruppo[gruppi.size()];
			for (int i = 0; i < gruppi.size(); i++) {
				ar_gruppi[i] = gruppi.get(i);
			}
			acc.setGruppi(ar_gruppi);
			}
		}catch (SQLException e) {
			connection.rollback();
			throw e;
		}
		connection.commit();
		return acc;
	}
	
	@Override
	public int getCountAccount()throws Exception{
		logger.info("metodo: getCountAccount");
		connection = getConnection();
		int count=0;
		CallableStatement stm;
		try {
			stm = connection.prepareCall("{call GET_COUNT_ACCOUNT(?)}");
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

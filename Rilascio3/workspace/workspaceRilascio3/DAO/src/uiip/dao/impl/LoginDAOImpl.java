package uiip.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import oracle.jdbc.OracleTypes;
import uiip.dao.iface.LoginDAOIface;
import uiip.entity.Account;
import uiip.entity.Gruppo;
import uiip.factory.impl.ConnectionFactory;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class LoginDAOImpl implements LoginDAOIface {

	private static Logger logger= Logger.getLogger("logApp");

	public LoginDAOImpl() {
		super();
	}

	Connection connection = null;
	PreparedStatement ptmt = null;
	ResultSet resultSet = null;

	public Connection getConnection() throws Exception {
		Connection conn = null;
		conn = ConnectionFactory.getInstance().getConnection();
		return conn;
	}

	public boolean controllaLogin(String user, String pass) throws Exception{
		logger.info("metodo: controllaLogin, in: username=\""+user+" password=\""+pass+"\"");
		connection = getConnection();
		CallableStatement st;
		boolean result = false;
		try{
			st= connection.prepareCall("{call EXIST_ACCOUNT(?,?)}");
			st.setString(1, user);
			st.registerOutParameter(2, OracleTypes.CURSOR);
//			result = st.execute();
			st.execute();
			System.out.println("DAO 2 MAGGIO PRIMA DEL WHILE");
			ResultSet rs = (ResultSet) st.getObject(2);
			while (rs.next()) {
				if (rs.getString(1).equals(user)) {
					st = connection.prepareCall("{call EXIST_ACC_CONTROL_PASS(?,?,?)}");
					st.registerOutParameter(1, OracleTypes.CURSOR);
					st.setString(2, user);
					st.setString(3, pass);
					result = st.execute();
					System.out.println("DAO 2 MAGGIO DENTRO WHILE");
					rs = (ResultSet) st.getObject(1);
					result = true;
					if(!rs.next())
						throw new Exception("COD_ERRLOG");
				}
			}
		}catch (Exception e) {
			connection.rollback();
			result = false;
			throw e;
		}
		connection.commit();
		System.out.println("DOPO COMMIT");
		System.out.println("stampo result: "+result);
		return result;
	}

	public Account getAccount(String user, String pass) throws Exception {
		logger.info("metodo: getAccount, in: username=\""+user+" password=\""+pass+"\"");
		connection = getConnection();
		CallableStatement st;
		Account acc=null;
		
		try{
			st= connection
			.prepareCall("{call GET_INFO_ACCOUNT(?,?,?)}");
			st.registerOutParameter(1, OracleTypes.CURSOR);
			st.setString(2, user);
			st.setString(3, pass);
			st.execute();
			ResultSet rs = (ResultSet) st.getObject(1);
			ArrayList<Gruppo> gruppi = new ArrayList<Gruppo>();
			while (rs.next()) {
				acc = new Account();
				acc.setNome(rs.getString(1));
				acc.setCognome(rs.getString(2));
				acc.setUsername(rs.getString(3));
				acc.setPassword(rs.getString(4));
				acc.setSigla_redazione(rs.getString(5));
				acc.setSigla_giornalista(rs.getString(6));
				acc.setStato(rs.getString(7));
			}
			st = connection.prepareCall("{call GET_GRUPPO(?,?)}");
			st.registerOutParameter(1, OracleTypes.CURSOR);
			st.setString(2, user);
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
		}catch (SQLException e) {
			connection.rollback();
			
			throw e;
		}
		connection.commit();
		return acc;
	}
	
}
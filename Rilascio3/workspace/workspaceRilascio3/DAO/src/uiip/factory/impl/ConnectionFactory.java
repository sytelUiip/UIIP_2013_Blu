package uiip.factory.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class ConnectionFactory{
		
	private String driverClassName  ;
	private String connectionUrl ;
	private String dbUser ;
	private String dbPwd ;

        private static ConnectionFactory connectionFactory = null;

        public ConnectionFactory() throws Exception {
        	Properties props=new Properties();
        	String res = "configurazioneDB.properties";
        	InputStream is = this.getClass().getClassLoader().getResourceAsStream(res);
        	props.load(is);
        	driverClassName=props.getProperty("driverClassName");
        	connectionUrl=props.getProperty("connectionUrl");
        	dbUser=props.getProperty("dbUser");
        	dbPwd=props.getProperty("dbPwd");
                try {
                        Class.forName(driverClassName);
                } catch (ClassNotFoundException e) {
                	throw new Exception("COD_CON");
                }
        }

        public Connection getConnection() throws Exception{
                Connection conn = null;
                try {
					conn = DriverManager.getConnection(connectionUrl, dbUser, dbPwd);
				} catch (SQLException e) {
					throw new Exception("COD_CON");
				}
                return conn;
        }

        public static ConnectionFactory getInstance() throws Exception{
                if (connectionFactory == null) {
                        try {
							connectionFactory = new ConnectionFactory();
						} catch (Exception e) {
							throw new Exception("COD_CON");
						}
                }
                return connectionFactory;
        }
}

package uiip.dao.iface;

import java.sql.Connection;

import uiip.entity.Notizia;

public interface NewsBatchDAOIface {

	public Connection getConnection() throws Exception;
	public int total_transmission(Notizia notizia,String user) throws Exception;
	public boolean insertNewsRicevuta(Notizia notizia) throws Exception;
	public Notizia[] lista_notizie_da_trasmettere() throws Exception;
	public boolean newsTrasmessa(Notizia notizia)throws Exception;
}

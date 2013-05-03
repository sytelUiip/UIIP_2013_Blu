package uiip.gestori.iface;

import uiip.entity.Notizia;

public interface GestoreNewsBatchIface {
	public int trasmissione_effettiva(Notizia notizia,String username, String user, String pass) throws Exception;
	public boolean inserisciNotiziaRicevuta(Notizia notizia) throws Exception;
	public Notizia[] ritornaListaNotizieDaTrasmettere() throws Exception;
	public boolean notiziaTrasmessa(Notizia notizia) throws Exception;

}
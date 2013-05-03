package uiip.entity;

import java.util.List;



public class Gruppo {
 private String id_g;	
 private String nomeGruppo;
 private List<Funzionalita> funzioni;
 
public List<Funzionalita> getFunzioni() {
	return funzioni;
}
public void setFunzioni(List<Funzionalita> funzioni) {
	this.funzioni = funzioni;
}
public String getId_g() {
	return id_g;
}
public void setId_g(String id_g) {
	this.id_g = id_g;
}
public String getNomeGruppo() {
	return nomeGruppo;
}
public void setNomeGruppo(String nomeGruppo) {
	this.nomeGruppo = nomeGruppo;
}

}

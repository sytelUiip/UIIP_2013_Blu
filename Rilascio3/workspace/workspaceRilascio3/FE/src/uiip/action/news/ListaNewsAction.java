package uiip.action.news;

import java.util.Map;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import uiip.ws.AccountWSStub;
import uiip.ws.LoginWSStub;
import uiip.ws.LoginWSStub.Account;
import uiip.ws.NewsWSStub;
import uiip.ws.NewsWSStub.Notizia;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ListaNewsAction extends ActionSupport {
	
	private String idN;
	private String autore;
	private String titolo;
	private String dataCreazione;
	private String dataTrasmissione;
	private String stato;
	private String lock;
	private String ultimoDigitatore;
	private String username;
	private String ultimoAccesso;
	private String parametro;
	private String ricerca;
	private int offset;
	private boolean avanti=false;
	private boolean indietro=false;
	
	public String execute(){
		
		System.out.println("SONO NELLA LISTA NEWS ACTION");
		NewsWSStub stub;
		LoginWSStub stub1;
		try{
			stub = new NewsWSStub();
			stub1 = new LoginWSStub();
			Notizia[] lista_notizie;
			NewsWSStub.ListaNotizeOffset list = new NewsWSStub.ListaNotizeOffset();
			
//			LoginWSStub.Account acc = (Account) ActionContext.getContext().getSession().get("account");
			Map session = ActionContext.getContext().getSession();
			LoginWSStub.Account acc = (uiip.ws.LoginWSStub.Account) session.get("account");
			
			String username = acc.getUsername();
			String password = acc.getPassword();
			
			System.out.println("SESSION.GET(OFFSET INIZIALE) "+session.get("offset"));
			if(session.get("offset")==null || (Integer)session.get("offset")==0){
				offset=1;
				NewsWSStub.NumeroTotNotizie count = new NewsWSStub.NumeroTotNotizie();
				count.setUsername(username);
				count.setPassword(password);
				NewsWSStub.NumeroTotNotizieResponse resp = stub.numeroTotNotizie(count);
				int numNotizie = resp.get_return();
				int numPagine = numNotizie/10;
				int resto = numNotizie%10;
				if(resto!=0){
					numPagine++;
				}
				session.put("numPagine",numPagine);
			}else{
				offset=(Integer) session.get("offset");
				if(avanti && offset<(Integer)session.get("numPagine")){
					offset++;
					avanti = false;
				}
				if(indietro && offset > 1){
					offset--;
					indietro = false;
				}
				
			}
			
			System.out.println("OFFSET "+offset);
			session.put("offset",offset);
			System.out.println("SONO NELLA LISTA NEWS ACTION");
			System.out.println("username"+username+"password"+password);
			
			System.out.println("sono in action user"+username+"pass"+password);
			System.out.println("offset iniziale"+(offset*10-9));
			System.out.println("offset finale"+(offset*10));
			
			list.setInizio(offset*10-9);
			list.setFine(offset*10);
			list.setUsername(username);
			list.setPassword(password);
			
			NewsWSStub.ListaNotizeOffsetResponse resp = stub.listaNotizeOffset(list);
			//resp = stub.listaNotizeOffset(list);

			ActionContext.getContext().getSession().put("account2", acc);
			
			if (getParametro() != null && getRicerca() != null /*&& !(getRicerca().equals("filtro"))*/) {
				NewsWSStub.Visualizza_per_parametro_offset ricerca = new NewsWSStub.Visualizza_per_parametro_offset();
				if(getRicerca().equals("stato")){
					setParametro(getParametro().toUpperCase());
				}
				ricerca.setParam(getParametro());
				ricerca.setRicerca(getRicerca());
				ricerca.setInizio(offset*10-9);
				ricerca.setFine(offset*10);
				ricerca.setUsername(username);
				ricerca.setPassword(password);
				NewsWSStub.Visualizza_per_parametro_offsetResponse ricerca_resp = stub.visualizza_per_parametro_offset(ricerca);
				System.out.println("getParametro nell'if !=null"+getParametro());
				System.out.println("getRicerca nell'if != null" + getRicerca());
				System.out.println("nell'if != null"+ricerca_resp.get_return().length);
				
				if (ricerca_resp.get_return().length != 0) {
					System.out.println(ricerca_resp.get_return().length+"lunghezza ricerca_risp");
					lista_notizie = ricerca_resp.get_return();
					ActionContext.getContext().getSession().put("listaNews", lista_notizie);
					return SUCCESS;
				} else {
					return ERROR;
				}
			}
			// System.out.println("**** LUNGHEZZA: "+resp.get_return().length);

			System.out.println("SONO NEL TRY DI LISTA NEWS ACTION");
			System.out.println("parametro"+getParametro());
			System.out.println("ricerca"+getRicerca());
			
			if ((getParametro() == null && getRicerca() == null)||getRicerca().equals("filtro")) {
					if (resp.get_return().length > 0) {
						lista_notizie = resp.get_return();
						ActionContext.getContext().getSession().put("listaNews", lista_notizie);
						return SUCCESS;
					} else {
						return ERROR;
					}
			} 
			else{
				return ERROR;
			}
//			else {
//				if (ricerca_resp.get_return()[0].get != 0) {
//					System.out.println(ricerca_resp.get_return().length+"lunghezza ricerca_risp");
//					lista_notizie = ricerca_resp.get_return();
//					ActionContext.getContext().getSession().put("listaNews", lista_notizie);
//					return SUCCESS;
//				} else {
//					return ERROR;
//				}
//
//			}

		}catch (Exception e) {
			addActionError(getText(e.getMessage()));
			return ERROR;
		}
	}

	public void validate() {
		if (getRicerca()!= null && getParametro()!= null && !getRicerca().equals("filtro") && getParametro().length() == 0) {
            addFieldError("parametro",  getText("parametro.required"));
            addActionError("parametro.required");
        }
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getIdN() {
		return idN;
	}

	public void setIdN(String idN) {
		this.idN = idN;
	}

	public String getAutore() {
		return autore;
	}

	public void setAutore(String autore) {
		this.autore = autore;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public String getDataCreazione() {
		return dataCreazione;
	}

	public void setDataCreazione(String dataCreazione) {
		this.dataCreazione = dataCreazione;
	}

	public String getDataTrasmissione() {
		return dataTrasmissione;
	}

	public void setDataTrasmissione(String dataTrasmissione) {
		this.dataTrasmissione = dataTrasmissione;
	}

	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	public String getLock() {
		return lock;
	}

	public void setLock(String lock) {
		this.lock = lock;
	}

	public String getUltimoDigitatore() {
		return ultimoDigitatore;
	}

	public void setUltimoDigitatore(String ultimoDigitatore) {
		this.ultimoDigitatore = ultimoDigitatore;
	}


	public String getUltimoAccesso() {
		return ultimoAccesso;
	}

	public void setUltimoAccesso(String ultimoAccesso) {
		this.ultimoAccesso = ultimoAccesso;
	}


	public String getParametro() {
		return parametro;
	}


	public void setParametro(String parametro) {
		this.parametro = parametro;
	}


	public String getRicerca() {
		return ricerca;
	}


	public void setRicerca(String ricerca) {
		this.ricerca = ricerca;
	}
	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}



	public void setAvanti(boolean avanti) {
		this.avanti = true;
	}


	public void setIndietro(boolean indietro) {
		this.indietro = true;
	}

	
}

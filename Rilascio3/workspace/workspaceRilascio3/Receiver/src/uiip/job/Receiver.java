package uiip.job;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.sun.org.apache.bcel.internal.classfile.Attribute;

import uiip.entity.Notizia;
import uiip.gestori.iface.GestoreNewsBatchIface;
import uiip.gestori.iface.GestoreNewsIface;
import uiip.gestori.impl.GestoreNewsBatchImpl;
import uiip.gestori.impl.GestoreNewsImpl;

public class Receiver implements Job {

    public void execute(JobExecutionContext context)
     throws JobExecutionException {

    	String cartella_name = "C:\\cartella_condivisa_ric";
		File f = new File (cartella_name);
		if(f.exists() && f.isDirectory()){

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setValidating(false);
			factory.setNamespaceAware(true);
			try {
				SchemaFactory schemaFactory = 
					SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
				factory.setSchema(schemaFactory.newSchema(
						new Source[] {new StreamSource("C:\\NotiziaReceivedSchema.xsd")}));
				DocumentBuilder builder  = factory.newDocumentBuilder();
				Document doc = null;
				File notiziaFile = null;
				for(int i=0;i<f.listFiles().length;i++){
					notiziaFile = f.listFiles()[i];
					Notizia notizia = null;
					try {
						doc = builder.parse(new InputSource(f.listFiles()[i].getPath()));
						doc.getDocumentElement().normalize();
						notizia = new Notizia();
						//Node not = doc.getElementsByTagName("Notizia").item(0);
						Node anagrafica = doc.getElementsByTagName("Anagrafica").item(0);
						/*notizia.setTitolo(anagrafica.getAttributes().item(0).getNodeValue());
						notizia.setSottotitolo(anagrafica.getAttributes().item(1).getNodeValue());
						notizia.setAutore(anagrafica.getAttributes().item(2).getNodeValue());
						notizia.setDatacreazione(anagrafica.getAttributes().item(3).getNodeValue());*/
						notizia.setAutore(anagrafica.getAttributes().getNamedItem("Autore").getNodeValue());
						notizia.setTitolo(anagrafica.getAttributes().getNamedItem("Titolo").getNodeValue());
						notizia.setSottotitolo(anagrafica.getAttributes().getNamedItem("Sottotitolo").getNodeValue());
						String dataCreazione = anagrafica.getAttributes().getNamedItem("DataCreazione").getNodeValue();
						notizia.setDatacreazione(dataCreazione);
						Node testo = doc.getElementsByTagName("Testo").item(0);
						notizia.setTesto(testo.getTextContent());
						notizia.setLunghezzatesto(notizia.getTesto().length());
						notizia.setUltimodigitatore("");
						GestoreNewsBatchIface gestNews = new GestoreNewsBatchImpl();
						gestNews.inserisciNotiziaRicevuta(notizia);
					} catch (SAXException e) {
						e.printStackTrace();
					} catch (Exception e) {
						e.printStackTrace();
					}finally{
							notiziaFile.delete();
					}
				}
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			} catch (SAXException e) {
				e.printStackTrace();
			}
		}

    }
}
package uiip.job;
import java.io.File;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import uiip.entity.Notizia;
import uiip.gestori.iface.GestoreNewsBatchIface;
import uiip.gestori.iface.GestoreNewsIface;
import uiip.gestori.impl.GestoreNewsBatchImpl;
import uiip.gestori.impl.GestoreNewsImpl;


public class Trasmitter implements Job {

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {

		GestoreNewsBatchIface gestNews = new GestoreNewsBatchImpl();
		Notizia[] notizie;
		try {
			notizie = gestNews.ritornaListaNotizieDaTrasmettere();

		Notizia not_curr = null;
		
		
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder;
		try {
			docBuilder = docFactory.newDocumentBuilder();
			Document doc = null;
			for(int i=0;i<notizie.length;i++){
				not_curr = notizie[i];

				doc= docBuilder.newDocument();
				Element rootElement = doc.createElement("Notizia");
				doc.appendChild(rootElement);
				
				Element anagrafica = doc.createElement("Anagrafica");
				anagrafica.setAttribute("Autore", not_curr.getAutore());
				anagrafica.setAttribute("Titolo", not_curr.getTitolo());
				anagrafica.setAttribute("Sottotitolo", not_curr.getSottotitolo());
				anagrafica.setAttribute("UltimoDigitatore", not_curr.getUltimodigitatore());
				String dataCreazione = not_curr.getDatacreazione().replace(" ", "T");
				anagrafica.setAttribute("DataCreazione", dataCreazione);
				rootElement.appendChild(anagrafica);
				
				Element testo = doc.createElement("Testo");
				testo.setTextContent(not_curr.getTesto());
				rootElement.appendChild(testo);
				
				String dataCreaz = not_curr.getDatacreazione().substring(0,10);
				String dataTrasm = not_curr.getDatatrasmissione().substring(0, 10);
				
				// write the content into xml file
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer;
				try {
					transformer = transformerFactory.newTransformer();
					DOMSource source = new DOMSource(doc);
					//create a SchemaFactory object
					SchemaFactory SF=SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI); 
					//create a Schema object
					Schema S=SF.newSchema(new File("C:\\NotiziaTransmittedSchema.xsd"));
					//create a Validator object
					Validator V=S.newValidator();
					//call validate method
					V.validate(source);
					StreamResult result = new StreamResult(new File("C:\\cartella_condivisa_tr\\"+ dataCreaz+"-"+not_curr.getId_N()+"-"+dataTrasm+".xml"));	 
					transformer.transform(source, result);
					System.out.println("File saved!");
				} catch (TransformerConfigurationException e) {
					e.printStackTrace();
				} catch (TransformerException e) {
					e.printStackTrace();
				}
				
				//Chiamata funzione per modificare lo stato da Q a T
				gestNews.notiziaTrasmessa(not_curr);
			}
		} catch (ParserConfigurationException e1) {
			e1.printStackTrace();
		}
	}catch (Exception e2) {
		e2.printStackTrace();
	}
	} 

}

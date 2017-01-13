package es.udl.asic.tool.actes;

import org.sakaiproject.tool.api.Session; 
import org.sakaiproject.tool.cover.SessionManager;
 
//import org.sakaiproject.api.kernel.session.Session;
//import org.sakaiproject.api.kernel.session.cover.SessionManager;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.fop.apps.Driver;
import org.apache.fop.messaging.MessageHandler;
import org.apache.xerces.dom.DocumentImpl;

import org.w3c.dom.*;


public class servPdf extends HttpServlet {

	/**
	 * Metode get del servlet que ens ha de servir la imatge
	 *  
	 */

	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		byte[] data = null; // array de bytes per obtenir la imatge

		//Obtenim la sessio
		Session session = SessionManager.getCurrentSession();
		
		//Obtenim el atribut ident de la sessio que es l'identificador
		Document document = (Document) session.getAttribute("document");

		Element acta = document.getDocumentElement();
		
		
		
		
		
		Element ellogo = document.createElement("logo");
		ellogo.appendChild(document.createTextNode(getFileName("udl.gif")));
		acta.appendChild(ellogo);	
		
		String tipus = (String) session.getAttribute("tipus");

				
		if (tipus.equals("pdf")){
			response.setContentType("application/pdf");
			generatePDF(document,"pdfwithoutstudents.xsl",response.getOutputStream());
		}
		else if(tipus.equals("pdfstudents")){
			response.setContentType("application/pdf");
			generatePDF(document,"pdfwithstudents.xsl",response.getOutputStream());
		}
		
		if (document!=null){
			System.out.println("El doc NO es null");
		}
		else {
			System.out.println("El doc SI es null");
		}
		
		session.removeAttribute("document");
		System.out.println("servlet-document");
		}
	
	
	private void generatePDF(Document doc, String xslFileName , OutputStream streamOut)
	{
		Driver driver = new Driver();

		//Logger logger = new ConsoleLogger(ConsoleLogger.LEVEL_ERROR);
		//MessageHandler.setScreenLogger(logger);
		//driver.setLogger(logger);

		driver.setOutputStream(streamOut);
		driver.setRenderer(Driver.RENDER_PDF);

		Transformer transformer = null;
		try
		{
			transformer =
				TransformerFactory.newInstance().newTransformer(
					new StreamSource(getFileName(xslFileName)));
		}

		catch (TransformerException e)
		{
			//Log.debug("sakai", "Actes.generatePDF(): " + e);
			e.printStackTrace();
			System.out.println("Error al tranformar");
			return;
		}

		Source x = new DOMSource(doc);

		try
		{
			transformer.transform(x, new SAXResult(driver.getContentHandler()));
		}

		catch (TransformerException e1)
		{
			System.out.println("Error al tranformar2");
			return;
		}
	}

	public String getFileName(String str){
		ServletContext servletContext = getServletContext();
		return servletContext.getRealPath("/actes/" + str);
	
	}
	

	
	}
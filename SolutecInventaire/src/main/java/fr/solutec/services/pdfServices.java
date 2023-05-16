package fr.solutec.services;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;


import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;

@Service
public class pdfServices {
	
	public byte[] generatePDF() throws FileNotFoundException, DocumentException {
		Document document = new Document();
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		PdfWriter.getInstance(document, outputStream);
		document.open();
		Paragraph paragraph = new Paragraph("Contenu de mon document PDF");
		document.add(paragraph);
		document.close();
		return outputStream.toByteArray();
	}

}

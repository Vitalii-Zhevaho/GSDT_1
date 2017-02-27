package ua.khai.pdf;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PRStream;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfDictionary;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfObject;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import ua.khai.exception.PDFException;

/**
 * Implementation of <tt>{@link PDFManager}</tt>.
 *
 * @author Vitalii Zhevaho
 */
public class PDFManagerImpl implements PDFManager {

    private static final Logger LOGGER = Logger.getLogger(PDFManagerImpl.class);

    private final String FILE_NAME = "pdf/template";
    private final String EXTENSION = ".pdf";
    private List<String> vars = new ArrayList<>();

    // public void generateTemplate() {
    // Document document = new Document();
    // try {
    // PdfWriter.getInstance(document, new FileOutputStream(this.FILE_NAME +
    // this.EXTENSION));
    // document.open();
    // document.add(new Paragraph("I'm, [fullName], a student. I was born in
    // [city]. I am [age] years old."));
    // LOGGER.trace("Template has been generated: " + this.FILE_NAME +
    // this.EXTENSION);
    // } catch (FileNotFoundException | DocumentException e) {
    // LOGGER.error("Template has not been generated", e);
    // throw new PDFException("Template has not been generated", e);
    // } finally {
    // document.close();
    // LOGGER.trace("Document has been closed");
    // }
    // }

    @Override
    public synchronized void fillTemplate(List<List<String>> lines) {
	this.vars = lines.get(0);
	for (int i = 1; i < lines.size(); i++) {
	    List<String> line = lines.get(i);
	    if (this.vars.size() != line.size()) {
		LOGGER.error("Invalid number of parameters at line " + i);
		throw new PDFException("Invalid number of parameters at line " + i);
	    }

	    final StringBuilder resultFile = new StringBuilder(this.FILE_NAME).append(i).append(this.EXTENSION);
	    Document document = null;
	    PdfReader reader = null;
	    PdfDictionary dict = null;
	    PdfObject object = null;
	    PdfCopy copy = null;
	    PdfStamper stamper = null;

	    try {
		document = new Document();
		reader = new PdfReader(this.FILE_NAME + this.EXTENSION);
		dict = reader.getPageN(1);
		object = dict.getDirectObject(PdfName.CONTENTS);

		copy = new PdfCopy(document, new FileOutputStream(resultFile.toString()));
		document.open();

		for (int j = 1; j <= reader.getNumberOfPages(); j++) {
		    copy.addPage(copy.getImportedPage(reader, j));
		}

		copy.freeReader(reader);
	    } catch (DocumentException | IOException e) {
		LOGGER.error("Template has not been filled", e);
		throw new PDFException("Template has not been filled", e);
	    } finally {
		document.close();
		copy.close();
	    }

	    try {
		if (object instanceof PRStream) {
		    for (int j = 0; j < this.vars.size(); j++) {
			PRStream stream = (PRStream) object;
			byte[] data = PdfReader.getStreamBytes(stream);
			String value = new String(lines.get(i).get(j));
			stream.setData(new String(data).replace(this.vars.get(j), value).getBytes("utf-8"));
		    }
		}

		stamper = new PdfStamper(reader, new FileOutputStream(resultFile.toString()));

		LOGGER.trace("Template has been filled: " + resultFile);
	    } catch (DocumentException | IOException e) {
		LOGGER.error("Template has not been filled", e);
		throw new PDFException("Template has not been filled", e);
	    } finally {
		try {
		    stamper.close();
		} catch (DocumentException | IOException e) {
		    LOGGER.error("PdfStamper has not been closed", e);
		}
		reader.close();
	    }
	}
    }

}

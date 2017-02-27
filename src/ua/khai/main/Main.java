package ua.khai.main;

import ua.khai.csv.CSVReader;
import ua.khai.csv.CSVReaderImpl;
import ua.khai.pdf.PDFManager;
import ua.khai.pdf.PDFManagerImpl;

/**
 * Main class for start project.
 *
 * @author Vladyslav Plotnikov
 */
public class Main {

    /**
     * Main method - the application entry point.
     */
    public static void main(String[] args) {

	CSVReader reader = new CSVReaderImpl();
	PDFManager manager = new PDFManagerImpl();

	manager.fillTemplate(reader.readCSVFile());

    }

}

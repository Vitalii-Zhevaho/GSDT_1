package ua.khai.csv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ua.khai.exception.CSVException;

/**
 * Implementation of interface <tt>{@link CSVReader}</tt>.
 *
 * @author Sergey Luniakin
 * @see ua.khai.csv.CSVReader
 */
public class CSVReaderImpl implements CSVReader {

    private static final Logger LOGGER = Logger.getLogger(CSVReaderImpl.class);

    private final String CSV_FILE = "data.csv";
    private List<List<String>> lines = new ArrayList<>();

    private List<String> asList(String... strings) {
	List<String> line = new ArrayList<>();
	for (String s : strings) {
	    line.add(s);
	}
	return line;
    }

    @Override
    public List<List<String>> readCSVFile() {
	String line = "";

	try (BufferedReader reader = new BufferedReader(new FileReader(this.CSV_FILE))) {
	    while ((line = reader.readLine()) != null) {
		line = line.substring(1, line.length() - 1);
		this.lines.add(this.asList(line.split("\",\"")));
	    }

	    LOGGER.trace("File read successfully");
	} catch (IOException e) {
	    System.err.println("File has not been read");
	    LOGGER.error("File has not been read", e);
	    throw new CSVException("File has not been read", e);
	}

	return this.lines;
    }

}

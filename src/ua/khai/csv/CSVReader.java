package ua.khai.csv;

import java.util.List;

/**
 * Interface to CSV-file readers.
 *
 * @author Sergey Luniakin
 */
public interface CSVReader {

    /**
     * Returns strings from csv-file.
     *
     * @return lines with parameters and parameter values from csv-file
     */
    List<List<String>> readCSVFile();

}

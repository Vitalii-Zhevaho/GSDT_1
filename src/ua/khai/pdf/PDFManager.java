package ua.khai.pdf;

import java.util.List;

/**
 * Interface PDFManager to work with PDF-documents.
 *
 * @author Vitalii Zhevaho
 */
public interface PDFManager {

    // /**
    // * Generates template.
    // */
    // void generateTemplate();

    /**
     * Fills a PDF-template by specified values.
     *
     * @param lines
     *            specified values of parameters
     */
    void fillTemplate(List<List<String>> lines);

}

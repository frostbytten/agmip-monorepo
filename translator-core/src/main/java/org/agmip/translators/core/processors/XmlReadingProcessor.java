package org.agmip.translators.core.processors;

import com.fasterxml.jackson.databind.JsonNode;
import org.agmip.translators.core.processors.handlers.DefaultXmlHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class XmlReadingProcessor {

    private final Path source;
    private XMLReader reader;

    public XmlReadingProcessor(Path source) {
        this.source = source.toAbsolutePath();
    }

    public void process() throws ParserConfigurationException, SAXException, IOException {
        SAXParser p = SAXParserFactory.newDefaultNSInstance().newSAXParser();
        reader = p.getXMLReader();
        reader.setContentHandler(new DefaultXmlHandler());
        // Check the file at the last possible second.
        if (Files.isReadable(source)) {
            reader.parse(new InputSource(new FileInputStream(source.toFile())));
        } else {
            System.out.println("File not available for processing: " + source);
        }
    }

    public JsonNode getResults() {
        return reader.getResults();
    }
}
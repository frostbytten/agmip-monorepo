package org.agmip.translators.core.processors;

import com.fasterxml.jackson.databind.JsonNode;
import org.agmip.translators.core.TranslatorSpec;
import org.agmip.translators.core.processors.handlers.DefaultXmlHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

public class XmlReadingProcessor {

    private final Path source;
    private final DefaultXmlHandler handler;

    public XmlReadingProcessor(Path source, TranslatorSpec spec) {
        this.source = source.toAbsolutePath();
        this.handler = new DefaultXmlHandler(spec);
    }

    public void process(InputStream stream) throws ParserConfigurationException, SAXException, IOException {
        SAXParser p = SAXParserFactory.newInstance().newSAXParser();
        XMLReader reader = p.getXMLReader();
        reader.setContentHandler(this.handler);
        // Check the file at the last possible second.
        reader.parse(new InputSource(stream));
    }

    public JsonNode getResults() {
        return handler.getResults();
    }
}
package org.agmip.translators.salus;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

public class SalusToAceWeather {
    public static void parse(Path fp) throws ParserConfigurationException, SAXException, IOException {
        System.out.println("Why are we here?");
        //Assuming this is XML, start vomiting out the start tags
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser p = factory.newSAXParser();
        XMLReader r = p.getXMLReader();
        r.setContentHandler(new SalusXmlWeatherHandler());
        r.parse(new InputSource(new FileInputStream(fp.toFile())));
    }
}

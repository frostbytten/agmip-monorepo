package org.agmip.translators.salus;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.Objects;

public class SalusXmlWeatherHandler extends DefaultHandler {
    private StringBuilder _sb;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        _sb = new StringBuilder();
        System.out.println(qName);
        if (attributes.getLength() != 0) {
            for(int x=0; x < attributes.getLength(); x++) {
                System.out.println("\t"+attributes.getQName(x) + ": " + attributes.getValue(x));
            }
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (Objects.isNull(_sb)) {
            _sb = new StringBuilder();
        }
        _sb.append(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        //System.out.println(_sb.toString());
    }
}


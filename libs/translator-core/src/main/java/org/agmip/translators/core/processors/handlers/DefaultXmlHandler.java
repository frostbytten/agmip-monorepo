package org.agmip.translators.core.processors.handlers;

import com.fasterxml.jackson.databind.JsonNode;
import org.agmip.translators.core.TranslatorSpec;
import org.agmip.translators.core.TranslatorSpecEntry;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.xpath.XPath;

public class DefaultXmlHandler extends DefaultHandler {
    private final TranslatorSpec spec;
    private String _ctx;
    public DefaultXmlHandler(TranslatorSpec spec) {
        this.spec = spec;
        this._ctx = "";
    }
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        this._ctx = addPathElement(qName);
        if (attributes.getLength() != 0) {
            for (int i = 0; i < attributes.getLength(); i++) {
                if (i > 0) {
                    this._ctx = removePathElement();
                }
                this._ctx = addPathElement(attributes.getQName(i));
                // TODO emit from here
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        this._ctx = removePathElement();
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
    }

    protected String addPathElement(String step) {
        return this._ctx += ("/" + step);
    }

    protected String removePathElement() {
        return this._ctx.substring(0, this._ctx.lastIndexOf("/"));
    }

    public JsonNode getResults() {
        return null;
    }
}

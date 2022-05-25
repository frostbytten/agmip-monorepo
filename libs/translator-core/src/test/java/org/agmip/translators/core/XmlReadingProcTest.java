package org.agmip.translators.core;

import org.agmip.translators.core.processors.XmlReadingProcessor;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;

public class XmlReadingProcTest {
    @Test
    public void fakeXPathImplementationTest() throws ParserConfigurationException, IOException, SAXException {
        String testXml = "<Hello><There>Value</There><Here>Else</Here></Hello>";
        InputStream s = new ByteArrayInputStream(testXml.getBytes(StandardCharsets.UTF_8));
        XmlReadingProcessor p = new XmlReadingProcessor(Paths.get(""), null);
        p.process(s);
        s.close();
    }
}
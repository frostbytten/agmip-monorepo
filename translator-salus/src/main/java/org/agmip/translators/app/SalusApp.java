package org.agmip.translators.app;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import org.agmip.translators.salus.SalusToAceWeather;

public class SalusApp {
    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
        if (args.length < 1) {
            System.out.println("please specify a file to read through");
            System.exit(1);
        }

        Path fp = FileSystems.getDefault().getPath(args[0]).toAbsolutePath();
        if (! Files.isReadable(fp)) {
            System.out.println("cannot read file " + fp);
            System.exit(1);
        } else {
            System.out.println("attempting to read: " + fp);
        }
        SalusToAceWeather.parse(fp);
    }
}

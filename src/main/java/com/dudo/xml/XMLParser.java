package com.dudo.xml;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

/**
 * User: Think
 * Date: 13-9-20
 */
public class XMLParser {
    public static void t() throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory saxParserFactory= SAXParserFactory.newInstance();
        SAXParser saxparser = saxParserFactory.newSAXParser();

        MySAXHandle mySAXHandle = new MySAXHandle();
        saxparser.parse("D:\\xmltest.xml",mySAXHandle);

    }

    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
        t();
    }
}

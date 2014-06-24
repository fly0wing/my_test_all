package com.dudo.xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * User: Think
 * Date: 13-9-20
 */
public class MySAXHandle extends DefaultHandler {
    public void startDocument() throws SAXException {
        System.out.println("start doc");
    }

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        System.out.println("start Element");
        System.out.println("\turi:" + uri);
        System.out.println("\tlocalName:" + localName);
        System.out.println("\tqName:" + qName);
        for ( int i = 0; i < attributes.getLength(); i++ ) {
            attributes.getLocalName(i);
            System.out.println("\t:" + attributes.getLocalName(i)
                    + " ֵ:" + attributes.getValue(i));
        int index= i;
        System.out.println("\tattr.getIndex:" +index);
        System.out.println("\tattr.getLength:" +attributes.getLength());
        System.out.println("\tattr.getLocalName:" +attributes.getLocalName(index));
        System.out.println("\tattr.getQName:" +attributes.getQName(index));
        System.out.println("\tattr.getType:" +attributes.getType(index));
        System.out.println("\tattr.getURI:" +attributes.getURI(index));
        System.out.println("\tattr.getValue:" +attributes.getValue(index));
        }
    }

    public void characters(char[] ch, int start, int length) throws SAXException {
        System.out.println("characters");
        char[] thisCh =new char[length];
        System.arraycopy(ch, start, thisCh, 0, length);
        System.out.println(String.valueOf(thisCh));
        System.out.println("\tch:" + String.valueOf(ch));
        System.out.println("\tstart:" + start);
        System.out.println("\tlength:" + length);
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {
        System.out.println("end Element");
        System.out.println("\turi:" +uri);
        System.out.println("\tlocalName:" + localName);
        System.out.println("\tqName:"+qName);
    }

    public void endDocument() throws SAXException {
        System.out.println("end doc");
    }

    public void warning( SAXParseException exception ) {
        System.out.println("*******WARNING******");
        System.out.println("\t:\t" + exception.getLineNumber());
        System.out.println("\t:\t" + exception.getColumnNumber());
        System.out.println("\t:\t" + exception.getMessage());
        System.out.println("********************");
    }
    public void error( SAXParseException exception ) throws SAXException {
        System.out.println("******* ERROR ******");
        System.out.println("\t:\t" + exception.getLineNumber());
        System.out.println("\t:\t" + exception.getColumnNumber());
        System.out.println("\t:\t" + exception.getMessage());
        System.out.println("********************");
    }
    public void fatalError( SAXParseException exception ) throws SAXException {
        System.out.println("******** FATAL ERROR ********");
        System.out.println("\t:\t" + exception.getLineNumber());
        System.out.println("\t:\t" + exception.getColumnNumber());
        System.out.println("\t:\t" + exception.getMessage());
        System.out.println("*****************************");
    }
}

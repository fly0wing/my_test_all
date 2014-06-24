package com.dudo.xml;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * User: zk
 * Date: 13-9-20
 */
public class DOMTest {
    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
        t();

    }
    public static void t() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();

//        DataInputStream dis = new DataInputStream(new FileInputStream(new File("D:\\xmltest.xml")));
        String res="<root> <action>action</action> <receiver>A</receiver> <sender>dsfsrvp</sender> <service>4000</service> <sid>12345678901234567890123456789012</sid> <body> <command>PFS1</command> <mercode>12345678901</mercode> <filename>XXXX</filename> <retcode>00</retcode> <retmsg>a</retmsg> </body> </root>";
//        res+=dis.readUTF();
//        res+="</root>";
        Document doc = db.parse(new ByteArrayInputStream(res.getBytes("utf-8")));

        p(doc.getElementsByTagName("retcode").item(0).getFirstChild().getNodeValue());
//        doc.getDoctype().toString();
//        NodeList nl = doc.getElementsByTagName("c");
//        nl.getLength();
//        Node my_node = nl.item(0);
//        String message = my_node.getFirstChild().getNodeValue();
//        System.out.println(message);

        p(""+doc.getLocalName());
//        p(""+doc.getDoctype().getName());
//        p(""+doc.getDoctype().getInternalSubset());
//        p(""+doc.getDoctype().getPublicId());
//        p(""+doc.getDoctype().getSystemId());
        p(""+doc.getDocumentElement().getTagName());
        p(""+doc.getFirstChild().getLocalName());
        p(""+doc.getNodeName());

    }

    private static void p(String s) {
        System.out.println(s);
    }
}

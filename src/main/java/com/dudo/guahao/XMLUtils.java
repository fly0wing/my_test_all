package com.dudo.guahao;

import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.htmlcleaner.XPatherException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangkai9
 * @date 2015/5/19
 */
public class XMLUtils {
    private static Logger logger = LoggerFactory.getLogger(XMLUtils.class);

    public static void crawlByXPath(String html, String xpathExp) throws IOException, ParserConfigurationException, SAXException, XPathExpressionException {

        XPathFactory xPathFactory = XPathFactory.newInstance();
        XPath xPath = xPathFactory.newXPath();

        XPathExpression expression = xPath.compile(xpathExp);
        String evaluate = expression.evaluate(html);
        logger.info(evaluate);

    }

    public static Object[] byPath(String contents, String xpath) throws XPatherException {
        HtmlCleaner hc = new HtmlCleaner();
        TagNode tn = hc.clean(contents);
        Object[] objects = tn.evaluateXPath(xpath);
        logger.debug("xpath:" + xpath + ",result:" + Arrays.toString(objects));
        return objects;
    }

    public static String byPathGetFirstNodeAttr(String contents, String xpath, String attribute) throws XPatherException {
        HtmlCleaner hc = new HtmlCleaner();
        TagNode tn = hc.clean(contents);
        Object[] objects = tn.evaluateXPath(xpath);
        logger.debug("xpath:" + xpath + ",find nodes:" + Arrays.toString(objects));
        for (Object object : objects) {
            if (object instanceof TagNode) {
                String href = ((TagNode) object).getAttributeByName(attribute);
                return href;
            }
        }
        return "";
    }

    public static Map<String, String> byPathGetNameVal(String contents, String xpath) throws XPatherException {
        HashMap<String, String> result = new HashMap<>();
        HtmlCleaner hc = new HtmlCleaner();
        TagNode tn = hc.clean(contents);
        Object[] objects = tn.evaluateXPath(xpath);
        for (Object object : objects) {
            if (object instanceof TagNode) {
                TagNode node = (TagNode) object;
                result.put(node.getAttributeByName("name"), node.getAttributeByName("value"));
            }
        }
        logger.debug("xpath:" + xpath + ",find nodes:" + Arrays.toString(objects) + ",result:" + result);
        return result;
    }
}

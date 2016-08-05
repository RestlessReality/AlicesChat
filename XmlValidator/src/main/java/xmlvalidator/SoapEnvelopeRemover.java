package xmlvalidator;

import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;
import java.io.StringReader;
import java.util.Map;

import static java.lang.String.format;

@Component
public class SoapEnvelopeRemover {

    private static final String SOAP11_PREFIX = "//soap11:Body/*";

    /**
     * Reduces a xml message to the content of the {@code &lt;soap:body&gt;} element.
     *
     * @param xmlDokument the input xml as String
     * @return the reduced xml
     */
    public Document reduceXMLToSoapBody(String xmlDokument, Map<String, String> prefixToUri) {

        Document reducedXmlDocument = stringToDocument(xmlDokument);

        NodeList nodeList = getNodeListFromDocument(reducedXmlDocument, SOAP11_PREFIX, prefixToUri);

        if (nodeList != null && nodeList.getLength() != 0) {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            documentBuilderFactory.setNamespaceAware(true);

            try {
                reducedXmlDocument = documentBuilderFactory.newDocumentBuilder().newDocument();
            } catch (ParserConfigurationException e) {
                throw new IllegalStateException(e);
            }

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = reducedXmlDocument.importNode(nodeList.item(i), true);
                reducedXmlDocument.appendChild(node);
            }

        }

        return reducedXmlDocument;
    }

    /**
     * Converts a String into an w3c document
     *
     * @param xml the xml as String which should be converted into a document
     * @return the converted document
     */
    public Document stringToDocument(String xml) {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        documentBuilderFactory.setNamespaceAware(true);
        DocumentBuilder builder;
        Document document;

        try {
            builder = documentBuilderFactory.newDocumentBuilder();
            InputSource inputSource = new InputSource(new StringReader(xml));
            document = builder.parse(new InputSource(new StringReader(xml)));
        } catch (ParserConfigurationException | SAXException | IOException e) { //todo what's upp with the java version and multi-catch?
            throw new IllegalStateException("Error while parse the following string to a document object in soap tests "
                    + xml, e);
        }

        return document;
    }

    /**
     * Extracts the node list from the document which is found with the given xPathExpression
     *
     * @param xml             the document where the node should be found
     * @param xPathExpression the xPath to the node
     * @param prefixToUri     map to resolve issues with xPath 1.0
     * @return the node list which was found in the document with the given xPathExpression
     */
    public NodeList getNodeListFromDocument(Document xml, String xPathExpression, Map<String, String> prefixToUri) {
        NodeList nodeList;
        XPath xpath = XPathFactory.newInstance().newXPath();
        xpath.setNamespaceContext(new DcomNamespacesResolver(prefixToUri));
        String expression = xPathExpression.trim();

        try {
            nodeList = (NodeList) xpath.evaluate(expression, xml, XPathConstants.NODESET);
        } catch (XPathExpressionException e) {
            throw new IllegalStateException(format("%s is not a valid xPath expression", expression), e);
        }

        return nodeList;
    }



}

package xmlvalidator;

import java.util.HashMap;
import java.util.Map;

import static java.util.Collections.unmodifiableMap;

public class XmlNamespaces {
    /**
     * Map of prefixes to namespaces.
     */
    public final static Map<String, String> MAP = unmodifiableMap(new HashMap<String, String>() {
        {
            put("", "");
            put("soap", "http://schemas.xmlsoap.org/soap/envelope/");
            put("soap11", "http://schemas.xmlsoap.org/soap/envelope/");
            put("soap12", "http://www.w3.org/2003/05/soap-envelope");
            put("wsse", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd");
            put("wsu", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd");
            put("star-ns2", "http://www.starstandards.org/webservices/2005/10/transport");
            put("startrans09", "http://www.starstandards.org/webservices/2009/transport");
            put("star", "http://www.starstandards.org/STAR");
            put("star5", "http://www.starstandard.org/STAR/5");
            put("dcom", "http://www.bmwgroup.com/dmsCommunications/v1/types");
            put("ispa", "http://www.bmw.com/score");
            put("srp", "http://www.bmw.com/SRP");
            put("apsi", "http://www.bmwgroup.com/appointmentScheduling/types");
        }
    });

    /**
     * No instantiation.
     */
    private XmlNamespaces() {
    }
}

package xmlvalidator;

import javax.xml.XMLConstants;
import javax.xml.namespace.NamespaceContext;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class DcomNamespacesResolver implements NamespaceContext {

    private Map<String, String> prefixToUriMap = new HashMap();

    public DcomNamespacesResolver(Map<String, String> prefixToUriMap){
        this.prefixToUriMap = prefixToUriMap;
    }

    /**
     * This method is automatically called by xpath with each evaluate to resolve namespaces issues with XPath 1.0
     *
     * @param prefix to search for
     * @return uri
     */
    public String getNamespaceURI(String prefix) {
        if (prefix == null) {
            return prefixToUriMap.get(XMLConstants.NULL_NS_URI);
        } else {
            return prefixToUriMap.get(prefix);
        }
    }

    /**
     * This method is just here because it exists in the DifferenceListener interface. So, it needs a dummy
     * implementation.
     */
    public String getPrefix(String namespaceURI) {
        throw new UnsupportedOperationException("Not needed in the DcomNameSpaceResolver");
    }

    /**
     * This method is just here because it exists in the DifferenceListener interface. So, it needs a dummy
     * implementation.
     */
    public Iterator getPrefixes(String namespaceURI) {
        throw new UnsupportedOperationException("Not needed in the DcomNameSpaceResolver");
    }
}

package ca.dum;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;

import org.testng.annotations.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class TransitXMLFactoryTests {

    @Test
    public void transitWebServiceShouldReturnXMLDocument() throws Exception {
        String query = "stops/50889";
        Document xmlDocument = TransitXMLFactory.retrieveXMlDocument(query);
        Node node = xmlDocument.getFirstChild();
        assertThat(node.getTextContent(), containsString("Northbound Shorehill at John Bruce"));
    }

    @Test
    public void transitWebServiceWithBadQueryShouldReturnServerError() throws Exception {
        String query = "badquery/50889";
        Boolean isExceptionThrown = false;
        try {
            TransitXMLFactory.retrieveXMlDocument(query);
        } catch (IOException io) {
            if (io.getMessage().contains("Server returned HTTP response code: 500")) {
                isExceptionThrown = true;
            }
        }
        assertThat(isExceptionThrown, equalTo(true));
    }
    
    @Test
    public void transitWebServiceShouldReturnStops() throws Exception {
        String stop  = "50889";
        Document xmlDocument = TransitXMLFactory.retrieveXMlDocumentForStop(stop);
        Node node = xmlDocument.getFirstChild();
        assertThat(node.getTextContent(), containsString("Northbound Shorehill at John Bruce"));
        NodeList nodeList = xmlDocument.getElementsByTagName("stop");
        assertThat(nodeList.getLength(), equalTo(1));
    }
    
    @Test
    public void transitWebServiceShouldReturnSchedule() throws Exception {
        String stop  = "50889";
        Document xmlDocument = TransitXMLFactory.retrieveXMlDocumentForStopSchedule(stop);
        Node node = xmlDocument.getFirstChild();
        System.out.println(node.getTextContent());
        assertThat(node.getTextContent(), containsString("Northbound Shorehill at John Bruce"));
    }
    
    
    
}

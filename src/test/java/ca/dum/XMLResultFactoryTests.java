package ca.dum;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.net.URL;

import org.testng.annotations.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class XMLResultFactoryTests {

    @Test
    public void callWebServiceShouldReturnXMLDocument() throws Exception {
        String urlString = "http://api.winnipegtransit.com/stops/10064?api-key=xKC5GUwbHh2tTNNGhFA";
        Document xmlDocument = XMLResultFactory
                .retrieveXMlDocument(new URL(urlString));
        Node node = xmlDocument.getFirstChild();
        assertThat(node.getTextContent(), containsString("Northbound Osborne"));
    }

    @Test
    public void callWebServiceWithBadKeyShouldThrowIOException()
            throws Exception {
        String urlString = "http://api.winnipegtransit.com/stops/10064?api-key=BADKEYTEST";
        Boolean isExceptionThrown = false;
        try {
            XMLResultFactory.retrieveXMlDocument(new URL(urlString));
        } catch (IOException io) {
            if (io.getMessage().contains(
                    "Server returned HTTP response code: 500")) {
                isExceptionThrown = true;
            }
        }
        assertThat(isExceptionThrown, equalTo(true));
    }

}

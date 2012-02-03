package ca.dum;

import java.io.IOException;
import java.net.URL;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class TransitXMLFactory extends XMLResultFactory {
    public static final String SERVER_ADDRESS = "api.winnipegtransit.com";
    public static final String API_KEY = "xKC5GUwbHh2tTNNGhFA";

    public static final String QUERY_STOPS = "/stops/";
    public static final String QUERY_SCHEDULE = "/schedule";

    public static Document retrieveXMlDocument(String query) throws IOException, ParserConfigurationException,
            SAXException {
        String url = "HTTP://" + SERVER_ADDRESS + "/" + query + "?api-key=" + API_KEY;
        return retrieveXMlDocument(new URL(url));
    }

    public static Document retrieveXMlDocumentForStop(String stop) throws IOException, ParserConfigurationException,
            SAXException {
        String url = "HTTP://" + SERVER_ADDRESS + QUERY_STOPS + stop + "?api-key=" + API_KEY;
        return retrieveXMlDocument(new URL(url));
    }

    public static Document retrieveXMlDocumentForStopSchedule(String stop) throws IOException,
            ParserConfigurationException, SAXException {
        String url = "HTTP://" + SERVER_ADDRESS + QUERY_STOPS + stop + QUERY_SCHEDULE + "?api-key=" + API_KEY;
        return retrieveXMlDocument(new URL(url));
    }

}

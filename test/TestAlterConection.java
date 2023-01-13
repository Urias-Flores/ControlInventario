import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class TestAlterConection {
    public static void main(String[] args) throws 
            ParserConfigurationException, 
            SAXException, IOException, 
            XPathExpressionException, 
            TransformerConfigurationException,
            TransformerException {
        //Conection.TryConection();
        
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.parse("./src/META-INF/persistence.xml");
        
        document.getDocumentElement().normalize();
        
        String nodePath = "/persistence/persistence-unit/properties/property";
        NodeList properties = (NodeList) XPathFactory.newInstance().newXPath().compile(nodePath).evaluate(document, XPathConstants.NODESET);
        
        Element url = (Element) properties.item(0);
        Element user = (Element) properties.item(1);
        Element password = (Element) properties.item(3);
        user.setAttribute("value", "root");
        
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer t = tf.newTransformer();
        DOMSource ds = new DOMSource(document);
        
        StreamResult consoleResult = new StreamResult(System.out);
        t.transform(ds, consoleResult);
        
        StreamResult fileResult = new StreamResult(new File("./src/META-INF/persistence.xml"));
        t.transform(ds, fileResult);
    }
}

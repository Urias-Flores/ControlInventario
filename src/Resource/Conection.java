package Resource;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
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

public class Conection {
    public static EntityManagerFactory emf = null;
    
    public static EntityManagerFactory CreateEntityManager(){
        try{
            if(emf == null){
                emf = Persistence.createEntityManagerFactory("ControlInventarioPU");
                System.out.println("Message: Conexion con el servidor abierta");
            }
        }catch(Exception ex){
            System.out.print("Error: "+ex.getMessage());
        }
        return emf;
    }
    
    public static void Disconnect(EntityManager em){
        if(em != null){
            em.close();
            emf.close();
            System.out.println("Message: Conexion con el servidor cerrada");
        }
    }
    
    public void tryOther(){
        Persistence.createEntityManagerFactory("", null);
    }
    
    public static boolean alterConnection(String IP, String Port, String User, String Password){
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse("./src/META-INF/persistence.xml");
            
            document.getDocumentElement().normalize();
            
            String nodePath = "/persistence/persistence-unit/properties/property";
            NodeList properties = (NodeList) XPathFactory.newInstance().newXPath().compile(nodePath).evaluate(document, XPathConstants.NODESET);
            
            Element url = (Element) properties.item(0);
            Element user = (Element) properties.item(1);
            Element password = (Element) properties.item(3);
            url.setAttribute("value", "jdbc:mysql://"+IP+":"+Port+"/inventario?zeroDateTimeBehavior=CONVERT_TO_NULL");
            user.setAttribute("value", User);
            password.setAttribute("value", Password);
            
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer t = tf.newTransformer();
            DOMSource ds = new DOMSource(document);
            
            StreamResult fileResult = new StreamResult(new File("./src/META-INF/persistence.xml"));
            t.transform(ds, fileResult);
            return true;
        } catch (ParserConfigurationException | SAXException | IOException | XPathExpressionException | TransformerException ex) {
            Logger.getLogger(Conection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}

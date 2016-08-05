package xmlvalidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Component
public class DirScanner {

    @Autowired
    private XmlValidator xmlValidator;

    @Autowired
    private SoapEnvelopeRemover soapEnvelopeRemover;

    /**
     * Recursively checks xml-files in directory for conformity with the schemafile
     * @param directory The directory in which is searched for xml-files
     * @param schemafile The xsd-Schemafile against which the xml-files are checked
     */
    public int checkDir( File directory, File schemafile ){

        int nrOfXmls = 0;

        if (!directory.isDirectory()){
            System.out.println("Method input was no directory, but should be.");
            return nrOfXmls;
        }

        File[] filesInDir = directory.listFiles();
        for(File file: filesInDir){
            System.out.println(file); //todo remove

            if (file.isDirectory()){
                checkDir( file, schemafile);
            }else {
                // check if xml-file
                if (!file.getName().toLowerCase().endsWith(".xml")) {
                    continue;

                }else {
                    nrOfXmls++;

                    // remove Soap-envelope //todo NOT YET USED
                    try {
                        Document document = soapEnvelopeRemover.reduceXMLToSoapBody(getFileContentAsString(file), XmlNamespaces.MAP);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    // check if conform to schema
                    System.out.println("checking xml-file against schema");
                    try {
                        if (xmlValidator.isSchemaConform(file, schemafile)) {
                            System.out.println("yes, it's conform!");
                        } else {
                            System.out.println("not conform");
                        }
                    } catch (SAXException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return nrOfXmls;

    }

    private String getFileContentAsString(File file) throws IOException {
        byte[] encoded = Files.readAllBytes(file.toPath());
        return new String(encoded);
    }

}

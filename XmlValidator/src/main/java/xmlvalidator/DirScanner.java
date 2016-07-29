package xmlvalidator;

import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

//todo eigtl in 2 Klassen aufteilen
// soap-envelope entfernen, siehe dcom-methode: reduceXMLToSoapBody

public class DirScanner {

    /**
     * Recursively checks xml-files in directory for conformity with the schemafile
     * @param directory
     * @param schemafile
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
                }
                nrOfXmls++;

                // if yes, check if conform to schema
                System.out.println("xml-file is now being checked against schema");
                try {
                    if(isSchemaConform(file, schemafile)){
                        System.out.println("yes, it's conform!");
                    }else {
                        System.out.println("not conform");
                    }
                } catch (SAXException e) {
                    e.printStackTrace();
                }

            }
        }

        return nrOfXmls;

    }


    private boolean isSchemaConform(File inXmlFile, File schemafile) throws SAXException {
        boolean isValid = true;
        Source xmlFile = new StreamSource(inXmlFile);

        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

        Schema schema = schemaFactory.newSchema(schemafile);

        Validator validator = schema.newValidator();
        try {
            validator.validate(xmlFile);
            System.out.println(xmlFile.getSystemId() + " is valid");
        } catch (SAXException e) {
            System.out.println(xmlFile.getSystemId() + " is NOT valid");
            System.out.println("Reason: " + e.getLocalizedMessage());
            isValid = false;
        } catch (IOException e) {
            e.printStackTrace();
            isValid = false;
        }

        return isValid;
    }


}

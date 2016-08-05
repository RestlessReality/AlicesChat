package xmlvalidator;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.File;

public class Main {

    /**
     * Give directory which should be recursively searched
     * Give schema-file which should be used to check if the found xml-files conform to it
     * @param args 1. the directory 2. the schema-file
     */
    public static void main(String[] args)
    {
        // Start the Spring-Application Context for the Spring-Beans
        ApplicationContext ctx = new AnnotationConfigApplicationContext("xmlvalidator");
        DirScanner dirScanner = ctx.getBean(DirScanner.class);

        if (args.length != 2){
            if (args.length < 2) {
                System.out.println("You entered too few arguments. Please supply directory and schema-file at program start.");
            }else if(args.length > 2){
                System.out.println("You entered too many arguments. Please supply directory and schema-file at program start.");
            }
            System.exit(1);
        }

        // Parse directory argument
        final String inDirectory = args[0];
        File directory = new File(inDirectory);
        if (!directory.isDirectory()){
            System.out.println("First input was no directory, but should be.");
            System.exit(1);
        }

        // Parse schemafile argument
        final String schemafile = args[1]; //todo further checks?
        File schema = new File(schemafile);
        if( !schema.getName().toLowerCase().endsWith(".xsd") ){
            System.out.println("Second input was no xsd, but should be.");
            System.exit(1);
        }

        int nrOfXmls = dirScanner.checkDir(directory, schema);
        System.out.println("Number of xml-files found: " + nrOfXmls);

    }

}

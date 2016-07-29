package xmlvalidator;

import java.io.File;

public class Main {

    /**
     * Give directory which should be recursively searched
     * Give schema-file which should be used to check if the found xml-files conform to it
     * @param args 1. the directory 2. the schema-file
     */
    public static void main(String[] args)
    {

        if (args.length != 2){
            System.out.println("You did not enter 2 arguments (directory and schema-file) at program start.");
            System.exit(1);
        }

        // Parse directory
        final String inDirectory = args[0];
        System.out.println(inDirectory);//todo remove
        File directory = new File(inDirectory);
        if (!directory.isDirectory()){
            System.out.println("First input was no directory, but should be.");
            System.exit(1);
        }

        // Parse schemafile
        final String schemafile = args[1]; //todo further checks?
        System.out.println(schemafile); //todo remove
        File schema = new File(schemafile);
        if( !schema.getName().toLowerCase().endsWith(".xsd") ){
            System.out.println("Second input was no xsd, but should be.");
            System.exit(1);
        }
        System.out.println("yes, schema is xsd!"); //todo remove


        DirScanner dirScanner = new DirScanner();
        int nrOfXmls = dirScanner.checkDir(directory, schema);
        System.out.println("Number of xml-files found: " + nrOfXmls);

    }

}

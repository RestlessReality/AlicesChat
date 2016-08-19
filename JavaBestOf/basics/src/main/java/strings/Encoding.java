package strings;


import org.apache.commons.codec.binary.BinaryCodec;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * Example how String maps to bits using different encodings.
 */
public class Encoding {

    /**
     * Runs the program.
     *
     * @param args not used
     */
    public static void main(String[] args) {

        // Java source files can be saved with any encoding.
        // You can set the source file encoding to be used in the IntelliJ settings.
        //
        // When the source files are read during compilation, the Compiler uses the default platform
        // encoding in order to interpret the source files correctly.
        // You can explicitly tell the Compiler which encoding to use by "javac -encoding..."
        //
        // When Maven is used you can set the encoding using properties:
        // http://stackoverflow.com/questions/3017695/how-to-configure-encoding-in-maven

        // Internally Java uses UTF-16 as encoding in memory: @see java.lang.String

        final String originalMessage = "Hello World§%^&€";
        System.out.println("Original message: " + originalMessage);

        // Back conversion of Unicode Code Points online: https://r12a.github.io/apps/conversion/
        System.out.println("Code Points:");
        originalMessage.codePoints().forEach(codePoint -> System.out.print(codePoint + " "));
        System.out.println("\n");

        // Charsets map code points into a binary representation
        Charset[] charSets = {
                StandardCharsets.US_ASCII,
                StandardCharsets.ISO_8859_1,
                StandardCharsets.UTF_8,
                StandardCharsets.UTF_16,
                StandardCharsets.UTF_16BE,
                StandardCharsets.UTF_16LE
        };


        for (Charset charset : charSets) {
            final byte[] bytes = originalMessage.getBytes(charset);
            System.out.println("Bytes encoded with " + charset + ": " + BinaryCodec.toAsciiString(bytes));
            System.out.println("Bytes interpreted with " + charset + ": " + new String(bytes, charset));
            System.out.print("\n");
        }
    }
}

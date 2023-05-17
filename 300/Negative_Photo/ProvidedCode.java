import java.io.FileInputStream;
import java.io.IOException;

/**
 * Utility for simplying PPM file reading.
 * 
 * @author Dr. Gothard
 */
public class ProvidedCode {
   /**
    * Return the next number from the input file, skipping comments and whitespace.
    * 
    * @param input the FileInputStream to read from
    * @return the next number in the file header (skipping comments and whitespace)
    */
   public static int nextInt(FileInputStream input) throws IOException {
      int b = input.read();
      String numberChars = "";

      // Skip comments and whitespace
      while (b == '#' || Character.isWhitespace((char) b)) {

         // Skip entire line
         if (b == '#') {
            while (b != '\n')
               b = input.read();
         }
         // eat the whitespace (or hard return)
         b = input.read();
      }

      // b is not a comment or whitespace.
      // Reads in the number, character at a time.
      while ((Character.isDigit(b))) {
         numberChars = numberChars + (char) b;
         b = input.read();
      }

      // Convert to an integer and return.
      return Integer.parseInt(numberChars);
   }
}

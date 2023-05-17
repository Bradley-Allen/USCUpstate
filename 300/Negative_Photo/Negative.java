/**
 * Project 4 -- Negative.java && ProvidedCode.java
 * Reads in a PPM image file,
 * outputs a PPM image file in negative colors.
 * 
 * @author Bradley Allen
 *
 */
import java.io.*;
import java.util.Scanner;

public class Negative {

	public static void main(String[] args) throws IOException {
		// Prompts user input, input/output PPM
		Scanner scan = new Scanner(System.in);
		System.out.print("Please enter the input PPM file: ");
		String inputFile = scan.next();
		System.out.print("Please enter the output PPM file: ");
		String outputFile = scan.next();
		scan.close();
		
		// Reads input PPM
		FileInputStream in = new FileInputStream(new File(inputFile));
		int p = in.read();
		String magicNumber = Integer.toString(nextInt(in));
		int width = nextInt(in);
		String widthstr = Integer.toString(width);
		int height = nextInt(in);
		String heightstr = Integer.toString(height);
		int maxColor = nextInt(in);
		String maxColorstr = Integer.toString(maxColor);
		int rasterSize = width*height*3;
		byte[] raster = new byte[rasterSize];
		in.read(raster, 0, rasterSize);
		
		// Inverts color values from raster
		for (int i = 0; i <= rasterSize-1; ++i) {
			byte colorval = raster[i];
			byte newcolorval = (byte) (maxColor - colorval);
			raster[i] = newcolorval;
		}
		
		// Writes new PPM file with negative color values
		FileOutputStream out = new FileOutputStream(new File(outputFile));
		out.write(p);
		out.write(magicNumber.getBytes());
		out.write("\n".getBytes());
		out.write("# Negative image by Bradley".getBytes());
		out.write("\n".getBytes());
		out.write(widthstr.getBytes());
		out.write(" ".getBytes());
		out.write(heightstr.getBytes());
		out.write("\n".getBytes());
		out.write(maxColorstr.getBytes());
		out.write("\n".getBytes());
		out.write(raster);
		out.close();
	}
	
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

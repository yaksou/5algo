package inlamningsuppgift5;

import java.io.*;
import java.util.*;

public class Uppgift2 {

	public static void main(String[] args) {

		TreeSet words;
		PrintWriter out; // A stream for writing to the output file

		words = new TreeSet();

		/*
		 * Get the input file name from the user and try to create the input
		 * stream. If there is a FileNotFoundException, print a message and
		 * terminate the program.
		 */
		Scanner scan = new Scanner(System.in);
		try {
			Scanner input = new Scanner(new FileReader("Robotics.txt"));
			while (input.hasNext()) {
				words.add(input.next());
			}
		} catch (FileNotFoundException e) {
			System.out.println("Can't find file ");
			return;
		}

		/*
		 * Get the output file name from the user and try to create the output
		 * stream. If there is an IOException, print a message and terminate the
		 * program.
		 */
		try {
			out = new PrintWriter(new FileWriter("Printout.txt"));
		} catch (IOException e) {
			System.out.print("Can't open file ");
			System.out.println(e.toString());
			return;
		}

		/* Write all the words from the list to the ouput stream. */

		Iterator iter = words.iterator();
		while (iter.hasNext())
			out.println(iter.next());

		/*
		 * Finish up by checking for an error on the output stream and printing
		 * either a warning message or a message that the words have been output
		 * to the output file.
		 */

		if (out.checkError() == true) {
			System.out.println("Some error occured while writing output.");
			System.out.println("Output might be incomplete or invalid.");
		} else {
			System.out.println("words.size()" + "printout");
		}

	}

}
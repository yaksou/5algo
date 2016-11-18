package inlamningsuppgift5;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Tree {
	public static void main(String[] asd) {

		TreeSetCounter<String> tree = new TreeSetCounter<String>();
		String s = "";

		// The name of the file to open.
		String fileName = "Robotics.txt";

		// This will reference one line at a time
		String line = null;

		try {
			// FileReader reads text files in the default encoding.
			FileReader fileReader = new FileReader("Robotics.txt");

			// Always wrap FileReader in BufferedReader.
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			while ((line = bufferedReader.readLine()) != null) {
				s = s + line + "\n";
			}

			// Always close files.
			bufferedReader.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + fileName + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + fileName + "'");
			// Or we could just do this:
			// ex.printStackTrace();
		}

		String[] a = s.split("\\W+");

		for (int i = 0; i < a.length; i++) {
			tree.addWord(a[i].toLowerCase());
		}
		TreeIterator<String> newtree = tree.Iterator();
		int n = 1;

		while (newtree.hasNext()) {
			NodeCounter temp = newtree.next();
			System.out.println(n + " " + temp.getElement().toString() + ", antal: " + temp.getCount());
			n++;
		}
	}

}

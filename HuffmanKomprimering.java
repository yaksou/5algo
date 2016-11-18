package inlamningsuppgift5;

//Du f�r (om du vill) �ndra strukturen i klassen Huffman genom att l�gga till metoder eller 
//variabler. Observera att variablerna och metoderna i klassen �r static.

import java.util.*;
import java.io.*;

class HuffmanKomprimering {

	private static ArrayList<HuffmanTree> theForest;
	private static int[] freqs;
	private static String[] codes = new String[256];
	private static int setSize;
	private static BufferedReader file;
	private static ArrayList<Integer> texten;
	private static String a;
	private static int zeros;

	private static void readFile(String filename) throws IOException {
		try {
			file = new BufferedReader(new FileReader(filename));

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		texten = new ArrayList<Integer>();
		int a;
		while ((a = file.read()) != -1) {

			freqs[a]++;
			texten.add(a);

		}

		file.close();
		// l�ser in ett tecken i taget fr�n filen (-1 motsvarar EndOfFile)
		// som
		// ascii
		// p� platsen i arrayen som motsvarar tecknet adderas 1,
		// freqs[ascii]++;

	}

	private static void makeTree() {
		// skapar Huffman tr�d med ascii-kod i noden och frekvensen som vikt
		// l�gger till tr�det in theForest

		for (int i = 0; i < freqs.length; i++) {
			if (freqs[i] != 0) {
				theForest.add(new HuffmanTree(i, freqs[i]));
			}
		}
		Collections.sort(theForest);

		while (theForest.size() > 1) {
			theForest.add(new HuffmanTree(theForest.remove(0), theForest.remove(0)));
			Collections.sort(theForest);
		}
	}

	private static void makeCods() {

		// anv�nder metoden codes() f�r din slutliga HT. Metoden finns
		// definierad i klassen HuffmanTree. Unders�k den.

		theForest.get(0).codes(codes);
	}

	private static void showResults() {
		for (int i = 0; i < codes.length; i++) {
			if (codes[i] != null) {
				System.out.println("Tecken: " + Character.toString((char) i) + ". Huffman bin�rt: " + codes[i]);
				System.out.println("Frekvens:" + freqs[i]);

			}
		}
		System.out.println("Antal bits f�rst: " + (texten.size() * 8));
		System.out.println("Antal bits efter komprimering: " + (a.length() + zeros));
	}

	private static void makeFile() throws IOException {
		// anv�nd stringTobyte f�r att bygga den komprimerade filen.
		a = "";
		for (int i = 0; i < texten.size(); i++) {
			a = a + codes[texten.get(i)];
		}
		FileOutputStream writer = new FileOutputStream(new File("C:\\Users\\Kemdee\\Desktop\\Tree.txt"));
		int index = 0;
		while (a.length() > index + 8) {
			writer.write(stringTobyte(a.substring(index, index + 8)));
			index += 8;
		}
		if (index < a.length()) {
			zeros = 0;
			String b = a.substring(index);
			for (int i = 0; i < (8 - (a.length() - index)); i++) {
				b = b + "0";
				zeros++;
			}
			writer.write(stringTobyte(b));
		}
		writer.close();
	}

	// metoden tar som argument en str�ng (11000111) och konvertera den till
	// byte
	// anv�nd metoden f�r att konvertera dina koder till byte som skall
	// sedan
	// sparas till filen.

	public static byte stringTobyte(String s) {
		byte b = 0;
		if (s.length() != 8) {
			throw new RuntimeException("The string representation of the byte in not of proper length!");
		}
		for (int i = 0; i < 8; i++) {
			if (s.charAt(i) == '1')
				b |= (1 << (7 - i));

		}
		return b;
	}

	public static void main(String[] a) throws IOException {
		setSize = 256; // s�tt m�nga olika tecken.
		freqs = new int[256];
		theForest = new ArrayList<HuffmanTree>();
		Scanner scan = new Scanner(System.in);
		System.out.println("Skriv filens namn");
		String fileName = scan.next();
		readFile(fileName);
		makeTree();
		makeCods();
		makeFile();
		showResults();

	}
}

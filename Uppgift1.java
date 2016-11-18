package inlamningsuppgift5;

import java.util.*;

public class Uppgift1 {

	public static void printSeries(int n) {
		if (n >= 0) {
			System.out.println(n);
			printSeries(n - 1);
		}
	}

	public static boolean palindromeWord(String s) {
		if (s.length() == 0 || s.length() == 1)
			return true;
		if (s.charAt(0) == s.charAt(s.length() - 1))
			return palindromeWord(s.substring(1, s.length() - 1));
		return false;
	}

	public static int powersofN(int base, int a) {
		if (a < 0) {
			throw new IllegalArgumentException("Illegal Power Argument");
		}
		if (a == 0) {
			return 1;
		} else {
			return base * powersofN(base, a - 1);
		}
	}

	public static void main(String[] args) {
		printSeries(10);

		Scanner scan = new Scanner(System.in);
		System.out.println("type desired word to check if its a palindrome or not");
		String s = scan.nextLine();
		if (palindromeWord(s))
			System.out.println(s + " is a palindrome");
		else
			System.out.println(s + " is not a palindrome");

		System.out.println();
		
		for (int i = 0; i < 10; i++) {
			System.out.println(powersofN(2, i));
		}
	}

}

package com.caesarcypher.nem;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class CaesarCypherTest {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String input = "";
		StringBuilder text = new StringBuilder();
		System.out.println("Enter text (enter 'q' to finish)");
		while (true) {
			input = in.nextLine();
			if (input.equals("q")) {
				break;
			}
			text.append(input);
		}
		System.out.println("\nEnter encryption rotation:");
		int rotation = Integer.parseInt(in.next());

		String t = text.toString().replaceAll("\\s+", " ");
		HashMap<String, List<String>> signaturesMap = CaesarCypher.getSignaturesMap(t.split(" "));
		System.out.println("\nSorted by signatures:");
		for (String key : signaturesMap.keySet()) {
			System.out.println(signaturesMap.get(key));
		}

		System.out.println("\nEncrypted:");
		String encrypted = CaesarCypher.encrypt(t, rotation);
		System.out.println(encrypted);

		System.out.println("\nDecrypted:");
		String decrypted = CaesarCypher.decrypt(encrypted, rotation);
		System.out.println(decrypted);

		in.close();
	}
}

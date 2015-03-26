package com.caesarcypher.nem;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class CaesarCypher {

	private static final char UNICODE_a = 0x0061;
	private static final char UNICODE_z = 0x007A;

	private static char rotateCharDown(char c, int r) {
		while (r > 0) {
			if (c == UNICODE_a) {
				c = UNICODE_z;
			} else {
				c--;
			}
			r--;
		}
		return c;
	}

	private static char rotateCharUp(char c, int r) {
		while (r > 0) {
			if (c == UNICODE_z) {
				c = UNICODE_a;
			} else {
				c++;
			}
			r--;
		}
		return c;
	}

	private static String getCaesarSignature(String word) {
		StringBuilder letters = new StringBuilder();
		// find rotation for the first letter by rotating it to 'a'
		char c = word.charAt(0);
		int rotation = 0;
		while (c != UNICODE_a) {
			c--;
			rotation++;
		}
		letters.append(c);
		// use rotation on all other characters to obtain the full signature
		for (int i = 1; i < word.length(); i++) {
			c = rotateCharDown(word.charAt(i), rotation);
			letters.append(c);
		}
		return letters.toString();
	}

	// creates a map of caesar signature -> matching words
	private static HashMap<String, List<String>> sortByCaesarCyphers(List<String> words) {
		HashMap<String, List<String>> signaturesMap = new HashMap<String, List<String>>();
		String signature;
		for (String word : words) {
			signature = getCaesarSignature(word.toLowerCase());
			// if the map doesn't contain matches for the current signature,
			// initialize them
			if (!signaturesMap.containsKey(signature)) {
				signaturesMap.put(signature, new ArrayList<String>());
			}
			signaturesMap.get(signature).add(word);
		}
		return signaturesMap;
	}

	public static HashMap<String, List<String>> getSignaturesMap(String[] words) {
		if (words == null) {
			return null;
		}
		return sortByCaesarCyphers(Arrays.asList(words));
	}

	public static String encrypt(String data, int rotation) {
		if (data == null) {
			return "";
		}
		StringBuilder encrypted = new StringBuilder();
		for (int i = 0; i < data.length(); i++) {
			encrypted.append(rotateCharDown(data.charAt(i), rotation));
		}
		return encrypted.toString();
	}

	public static String decrypt(String data, int rotation) {
		if (data == null) {
			return "";
		}
		StringBuilder dencrypted = new StringBuilder();
		for (int i = 0; i < data.length(); i++) {
			dencrypted.append(rotateCharUp(data.charAt(i), rotation));
		}
		return dencrypted.toString();
	}

	public static void main(String[] args) {
		char a = 'a';
		a = CaesarCypher.rotateCharUp(a, 5);
		System.out.println(a);
		a = CaesarCypher.rotateCharDown(a, 5);
		System.out.println(a);
	}
}

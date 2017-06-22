package io.robusta.java.classic;

import java.util.Arrays;

/**
 * Created by nicorama on 21/06/2017.
 */
public class ArrayApp {

	int[] buildArray(int a, int b, int c) {

		int[] tab = new int[3];

		tab[0] = a;
		tab[1] = b;
		tab[2] = c;

		return tab;
	}

	boolean equality(int[] array1, int[] array2) {

		if (Arrays.equals(array1, array2)) {
			return true;
		} else {
			return false;
		}

	}

	String asString(int[] numbers) {

		String result = "";

		for (int i = 0; i < numbers.length; i++) {
			String elementString = "" + numbers[i] + ":";
			result = result + elementString;
		}

		return result;
	}

	String asStringJoin(int[] numbers) {
		String result = "";

		for (int i = 0; i < numbers.length; i++) {
			if (i == numbers.length - 1) {
				String elementString = "" + numbers[i];
				result = result + elementString;
			} else {
				String elementString = "" + numbers[i] + ":";
				result = result + elementString;
			}
		}

		return result;
	}

	String asString(String[] strings) {

		String result = "";

		for (int i = 0; i < strings.length; i++) {
			if (i == strings.length - 1) {
				String elementString = strings[i];
				result = result + elementString;
			} else {
				String elementString = strings[i] + " ";
				result = result + elementString;
			}
		}

		return result;
	}

	String asString(Card[] cards) {

		String result = "";

		for (int i = 0; i < cards.length; i++) {
			if (i == 0) {
				String elementString = "[" + cards[i].value +""+ cards[i].color + " ";
				result = result + elementString;
			} else if (i == cards.length - 1) {
				String elementString = cards[i].value +""+ cards[i].color + "]";
				result = result + elementString;
			} else {
				String elementString = cards[i].value +""+ cards[i].color + " ";
				result = result + elementString;
			}
		}

		return result;
	}

}

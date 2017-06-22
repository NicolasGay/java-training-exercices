package io.robusta.java.classic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nicorama on 21/06/2017.
 */
public class ListApp {

	List<Integer> buildList(int a, int b, int c) {

		List<Integer> result = new ArrayList<Integer>();

		result.add(a);
		result.add(b);
		result.add(c);

		return result;
	}

	ArrayList<Integer> buildList(int... numbers) {

		ArrayList<Integer> result = new ArrayList<Integer>();

		for (int i = 0; i < numbers.length; i++) {
			result.add(numbers[i]);
		}

		return result;
	}

	boolean equality(List<Integer> list1, List<Integer> list2) {

		if (list1.equals(list2)) {
			return true;
		}

		return false;
	}

	String asString(List<Integer> numbers) {
		return null;
	}

	int first(List<Integer> ints) {
		return ints.get(0);
	}

	int last(List<Integer> ints) {
		return ints.get(ints.size() - 1);
	}

	int medium(List<Integer> ints) {

		if (ints.size() % 2 == 0) {
			return ints.get(ints.size() / 2);
		} else {
			return ints.get((ints.size() / 2)+0.5);
		}

	}
}

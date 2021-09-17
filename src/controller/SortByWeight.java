package controller;

import java.util.Comparator;

import model.food;

public class SortByWeight implements Comparator<food> {

	@Override
	public int compare(food f1, food f2) {
		if (f1.getWeight() > f2.getWeight()) {
			return 1;
		}
		return -1;
	}
}

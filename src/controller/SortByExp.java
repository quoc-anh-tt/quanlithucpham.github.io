package controller;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

import model.food;

public class SortByExp implements Comparator<food> {

	@Override
	public int compare(food food1, food food2) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date date1 = sdf.parse(food1.getExp());
			Date date2 = sdf.parse(food2.getExp());
			return date2.compareTo(date1);
		} catch (Exception e) {
			return (Integer) null;
		}
	}

}

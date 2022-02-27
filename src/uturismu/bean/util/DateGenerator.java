package uturismu.bean.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

public class DateGenerator {

	private static List<Day> days;
	private static List<Month> months;
	private static List<Year> years;
	private static List<Year> futureYears;
	
	static {
		days = new ArrayList<Day>(31);
		for (int i = 1; i <= 31; i++) {
			Day day = new Day();
			day.setLabel(String.valueOf(i));
			day.setValue(i);
			days.add(day);
		}

		InputStream stream = DateGenerator.class.getResourceAsStream("months.properties");
		Properties prop = new Properties();
		try {
			prop.load(stream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		months = new ArrayList<Month>(12);
		for (int i = 1; i <= 12; i++) {
			Month month = new Month();
			month.setLabel(prop.getProperty(String.valueOf(i)));
			month.setValue(i);
			months.add(month);
		}

		years = new ArrayList<Year>();
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		int startYear = (currentYear - 115);
		int endYear = currentYear - 18;
		for (int i = endYear; i >= startYear; i--) {
			Year year = new Year();
			year.setLabel(String.valueOf(i));
			year.setValue(i);
			years.add(year);
		}
		
		futureYears = new ArrayList<Year>();
		currentYear = Calendar.getInstance().get(Calendar.YEAR);
		startYear = (currentYear);
		endYear = currentYear + 100;
		for (int i = startYear; i <= endYear; i++) {
			Year year = new Year();
			year.setLabel(String.valueOf(i));
			year.setValue(i);
			futureYears.add(year);
		}
	}

	private DateGenerator() {

	}

	public static List<Day> getDays() {
		return days;
	}

	public static void setDays(List<Day> days) {
		DateGenerator.days = days;
	}

	public static List<Month> getMonths() {
		return months;
	}

	public static void setMonths(List<Month> months) {
		DateGenerator.months = months;
	}

	public static List<Year> getYears() {
		return years;
	}

	public static void setYears(List<Year> years) {
		DateGenerator.years = years;
	}

	public static List<Year> getFutureYears() {
		return futureYears;
	}

	public static void setFutureYears(List<Year> futureYears) {
		DateGenerator.futureYears = futureYears;
	}

}
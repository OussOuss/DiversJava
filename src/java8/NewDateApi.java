package java8;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;

/**
 * The java.time classes are immutable, any instance method that seems to modify
 * one, like plus, minus, or with, produces a new instance.
 * 
 * @author ex580
 *
 */
public class NewDateApi {

	public static void main(String[] args) {
		// basicDateTimeClasses();

		// creatingDateTimeForExistantInstance();

		adjustersAndQueries();
	}

	private static void basicDateTimeClasses() {
		System.out.println("Instant.now(): " + Instant.now()); // Instant.now(): 2018-04-04T08:28:32.838420400Z
		System.out.println("LocalDate.now(): " + LocalDate.now()); // LocalDate.now(): 2018-04-04
		System.out.println("LocalTime.now(): " + LocalTime.now()); // LocalTime.now(): 10:28:32.995436100
		System.out.println("LocalDateTime.now(): " + LocalDateTime.now()); // 2018-04-04T10:28:32.995436100
		System.out.println("ZonedDateTime.now(): " + ZonedDateTime.now()); // 2018-04-04T10:28:32.996436200+02:00[Europe/Paris]

		System.out.println("First landing on the Moon:");
		LocalDate moonLandingDate = LocalDate.of(1969, Month.JULY, 20);
		LocalTime moonLandingTime = LocalTime.of(20, 18);
		System.out.println("Date: " + moonLandingDate); // Date: 1969-07-20
		System.out.println("Time: " + moonLandingTime); // Time: 20:18
		System.out.println("Neil Armstrong steps onto the surface: ");
		LocalTime walkTime = LocalTime.of(20, 2, 56, 150_000_000);
		LocalDateTime walk = LocalDateTime.of(moonLandingDate, walkTime);
		System.out.println(walk); // 1969-07-20T20:02:56.150

		LocalDateTime dateTime = LocalDateTime.of(2017, Month.JULY, 4, 13, 20, 10);
		ZonedDateTime nyc = dateTime.atZone(ZoneId.of("America/New_York"));
		System.out.println(nyc); // 2017-07-04T13:20:10-04:00[America/New_York]
		ZonedDateTime london = nyc.withZoneSameInstant(ZoneId.of("Europe/London"));
		System.out.println(london); // 2017-07-04T18:20:10+01:00[Europe/London]

		System.out.println("Days in Feb in a leap year: " + Month.FEBRUARY.length(true)); // 29
		System.out.println("Day of year for first day of Aug (leap year): " + Month.AUGUST.firstDayOfYear(true)); // 214
		System.out.println("Month.of(1): " + Month.of(1)); // Month.of(1): JANUARY
		System.out.println("Adding two months: " + Month.JANUARY.plus(2)); // Adding two months: MARCH
		System.out.println("Subtracting a month: " + Month.MARCH.minus(1)); // Subtracting a month: FEBRUARY
	}

	private static void creatingDateTimeForExistantInstance() {
		try {
			// Manipulation de la class LocalDate pour ajouter soustraire des dates ...
			localDatePlus();

			// Manipulation de la class LocalTime pour ajouter soustraire des heures/minutes
			// ...
			localTimePlus();

			// Manipulation de la class LocalDateTime pour ajouter soustraire des
			// jours/heures/minutes ...
			// le plus/minus de la class LocalDateTime prend soit une Period ou Duration
			// comme paramètre
			plus_minus();

			//// Manipulation de la class LocalDate pour ajouter soustraire des
			//// dates/heures/minutes ...
			with();

			// Vu que férier dans une année non bissextil n'a po 29jours on obtient une
			// exception
			withInvalidDate();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	private static void localDatePlus() throws Exception {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate start = LocalDate.of(2017, Month.FEBRUARY, 2);
		LocalDate end = start.plusDays(3);
		System.out.println("2017-02-05" + end.format(formatter));
		end = start.plusWeeks(5);
		System.out.println("2017-03-09" + end.format(formatter));
		end = start.plusMonths(7);
		System.out.println("2017-09-02" + end.format(formatter));
		end = start.plusYears(2);
		System.out.println("2019-02-02" + end.format(formatter));
	}

	private static void localTimePlus() throws Exception {
		DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_TIME;
		LocalTime start = LocalTime.of(11, 30, 0, 0);
		LocalTime end = start.plusNanos(1_000_000);
		System.out.println("11:30:00.001" + end.format(formatter));
		end = start.plusSeconds(20);
		System.out.println("11:30:20" + end.format(formatter));
		end = start.plusMinutes(45);
		System.out.println("12:15:00" + end.format(formatter));
		end = start.plusHours(5);
		System.out.println("16:30:00" + end.format(formatter));
	}

	private static void plus_minus() throws Exception {
		Period period = Period.of(2, 3, 4); // 2 years, 3 months, 4 days
		LocalDateTime start = LocalDateTime.of(2017, Month.FEBRUARY, 2, 11, 30);
		LocalDateTime end = start.plus(period);
		System.out.println("2019-05-06T11:30:00" + end.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
		end = start.plus(3, ChronoUnit.HALF_DAYS);
		System.out.println("2017-02-03T23:30:00" + end.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
		end = start.minus(period);
		System.out.println("2014-10-29T11:30:00" + end.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
		end = start.minus(2, ChronoUnit.CENTURIES);
		System.out.println("1817-02-02T11:30:00" + end.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
		end = start.plus(3, ChronoUnit.MILLENNIA);
		System.out.println("5017-02-02T11:30:00" + end.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
	}

	private static void with() throws Exception {
		LocalDateTime start = LocalDateTime.of(2017, Month.FEBRUARY, 2, 11, 30);
		LocalDateTime end = start.withMinute(45);
		System.out.println("2017-02-02T11:45:00" + end.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
		end = start.withHour(16);
		System.out.println("2017-02-02T16:30:00" + end.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
		end = start.withDayOfMonth(28);
		System.out.println("2017-02-28T11:30:00" + end.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
		end = start.withDayOfYear(300);
		System.out.println("2017-10-27T11:30:00" + end.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
		end = start.withYear(2020);
		System.out.println("2020-02-02T11:30:00" + end.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
	}

	/**
	 * Vu que férier dans une année non bissextil n'a po 29jours on obtient une
	 * exception
	 */
	private static void withInvalidDate() throws Exception {
		LocalDateTime start = LocalDateTime.of(2017, Month.FEBRUARY, 2, 11, 30);
		start.withDayOfMonth(29);
	}

	/**
	 * The TemporalAdjuster and TemporalQuery classes provide interesting ways to
	 * work with the Date-Time classes. They provide useful built-in methods and
	 * ways to implement your own
	 */
	private static void adjustersAndQueries() {
		
		try {
			adjusters();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	private static void adjusters() throws Exception {
		LocalDateTime start = LocalDateTime.of(2017, Month.FEBRUARY, 2, 11, 30);
		LocalDateTime end = start.with(TemporalAdjusters.firstDayOfNextMonth());
		System.out.println("2017-03-01T11:30"+ end.toString());
		end = start.with(TemporalAdjusters.next(DayOfWeek.THURSDAY));
		System.out.println("2017-02-09T11:30"+ end.toString());
		end = start.with(TemporalAdjusters.previousOrSame(DayOfWeek.THURSDAY));
		System.out.println("2017-02-02T11:30"+ end.toString());
	}
}

package examples;

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
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalQueries;
import java.util.Calendar;
import java.util.Date;
import java.util.stream.IntStream;

/**
 * The examples.time classes are immutable, any instance method that seems to modify
 * one, like plus, minus, or with, produces a new instance.
 * 
 * @author ex580
 *
 */
public class NewDateApi {

	public static void main(String[] args) {
		// basicDateTimeClasses();

		// creatingDateTimeForExistantInstance();

		// adjustersAndQueries();

		convertDateToLocalDate();
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
			// comme param�tre
			plus_minus();

			//// Manipulation de la class LocalDate pour ajouter soustraire des
			//// dates/heures/minutes ...
			with();

			// Vu que f�rier dans une ann�e non bissextil n'a po 29jours on obtient une
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
	 * Vu que f�rier dans une ann�e non bissextil n'a po 29jours on obtient une
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

			payDay();

			queries();

			worldCupDay();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private static void adjusters() throws Exception {
		LocalDateTime start = LocalDateTime.of(2017, Month.FEBRUARY, 2, 11, 30);
		LocalDateTime end = start.with(TemporalAdjusters.firstDayOfNextMonth());
		System.out.println("2017-03-01T11:30" + end.toString());
		end = start.with(TemporalAdjusters.next(DayOfWeek.THURSDAY));
		System.out.println("2017-02-09T11:30" + end.toString());
		end = start.with(TemporalAdjusters.previousOrSame(DayOfWeek.THURSDAY));
		System.out.println("2017-02-02T11:30" + end.toString());
	}

	/**
	 * Exemple de personalisation du TemporalAdjuster On recupere la date de paye
	 * qui est le dernier jour ouvrable du mois
	 *
	 */
	private class PaydayAdjuster implements TemporalAdjuster {
		public Temporal adjustInto(Temporal input) {
			LocalDate date = LocalDate.from(input);
			int day = date.with(TemporalAdjusters.lastDayOfMonth()).getDayOfMonth();

			date = date.withDayOfMonth(day);
			if (date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY) {
				date = date.with(TemporalAdjusters.previous(DayOfWeek.FRIDAY));
			}
			return input.with(date);
		}
	}

	private static void payDay() throws Exception {
		TemporalAdjuster adjuster = new NewDateApi().new PaydayAdjuster();
		IntStream.of(1).mapToObj(day -> LocalDate.of(2018, Month.APRIL, day))
				.forEach(date -> System.out.println(date.with(adjuster).getDayOfMonth()));
	}

	private static void queries() throws Exception {
		System.out.println(ChronoUnit.DAYS + " " + LocalDate.now().query(TemporalQueries.precision()));
		System.out.println(ChronoUnit.NANOS + " " + LocalTime.now().query(TemporalQueries.precision()));
		System.out.println(ZoneId.systemDefault() + " " + ZonedDateTime.now().query(TemporalQueries.zone()));
		System.out.println(ZoneId.systemDefault() + " " + ZonedDateTime.now().query(TemporalQueries.zoneId()));
	}

	/**
	 * M�thode qui retourne le nombre de jours restant par rapport � une date
	 */
	private static long daysUntilWorldCup(TemporalAccessor temporal) {
		int day = temporal.get(ChronoField.DAY_OF_MONTH);
		int month = temporal.get(ChronoField.MONTH_OF_YEAR);
		int year = temporal.get(ChronoField.YEAR);
		LocalDate date = LocalDate.of(year, month, day);
		LocalDate tlapd = LocalDate.of(year, Month.JUNE, 14);
		if (date.isAfter(tlapd)) {
			tlapd = tlapd.plusYears(4);
		}
		return ChronoUnit.DAYS.between(date, tlapd);
	}

	private static void worldCupDay() throws Exception {
		IntStream.of(5).mapToObj(n -> LocalDate.of(2018, Month.APRIL, n))
				.forEach(date -> System.out.println(date.query(NewDateApi::daysUntilWorldCup)));
	}

	private static void convertDateToLocalDate() {
		try {
			Date date = new Date();
			java.sql.Date sqlDate = new java.sql.Date(date.getTime());
			Calendar calendar = Calendar.getInstance();
			LocalDate localDate = null;
			// Java8
			localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

			// Java9
			localDate = LocalDate.ofInstant(date.toInstant(), ZoneId.systemDefault());

			// Sql Date
			localDate = sqlDate.toLocalDate();

			// Calendar
			ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(calendar.toInstant(),
					calendar.getTimeZone().toZoneId());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}

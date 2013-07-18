package biz.vidal.jdatagen.jodatime;

import static biz.vidal.jdatagen.Generators.generator;
import static biz.vidal.jdatagen.jodatime.JodaIncrementors.plusDays;
import static com.google.common.collect.Iterables.limit;

import java.util.Iterator;

import org.joda.time.DateMidnight;
import org.joda.time.Interval;

/**
 * @author <a href="http://vidal.biz">Cedric Vidal</a>
 * 
 */
public class JodaGenerators {

	public static Iterable<DateMidnight> dateDomain(
			final DateMidnight startDate, Integer size) {
		return limit(generator(startDate, plusDays(1)), size);
	}

	public static Iterable<Interval> intervalDomain(
			final Iterable<DateMidnight> startDates, final Integer stayDuration) {
		return new Iterable<Interval>() {
			public Iterator<Interval> iterator() {
				return new Iterator<Interval>() {
					Iterator<DateMidnight> departureDateIt = startDates
							.iterator();
					Iterator<DateMidnight> arrivalDatesIt = null;
					DateMidnight currentDeparture = null;
					DateMidnight currentArrival = null;

					public void remove() {
					}

					public Interval next() {
						if (currentDeparture == null) {
							currentDeparture = departureDateIt.next();
						}
						if (arrivalDatesIt == null) {
							arrivalDatesIt = dateDomain(
									currentDeparture.plusDays(1), stayDuration)
									.iterator();
						}
						if (currentArrival == null) {
							currentArrival = arrivalDatesIt.next();
							return new Interval(currentDeparture,
									currentArrival);
						}

						if (arrivalDatesIt.hasNext()) {
							currentArrival = arrivalDatesIt.next();
							return new Interval(currentDeparture,
									currentArrival);
						} else {
							arrivalDatesIt = null;
						}

						if (departureDateIt.hasNext()) {
							currentDeparture = departureDateIt.next();
						}

						if (arrivalDatesIt == null) {
							arrivalDatesIt = dateDomain(
									currentDeparture.plusDays(1), stayDuration)
									.iterator();
							currentArrival = arrivalDatesIt.next();
						}
						return new Interval(currentDeparture, currentArrival);
					}

					public boolean hasNext() {
						return currentDeparture == null
								|| departureDateIt.hasNext()
								|| arrivalDatesIt.hasNext();
					}
				};
			}
		};
	}
}

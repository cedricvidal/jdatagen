package biz.vidal.jdatagen.jodatime;

import static biz.vidal.jdatagen.jodatime.JodaGenerators.dateDomain;
import static biz.vidal.jdatagen.jodatime.JodaGenerators.intervalDomain;
import static com.google.common.collect.Iterables.size;
import static org.junit.Assert.assertEquals;

import java.util.Iterator;

import org.joda.time.DateMidnight;
import org.joda.time.Interval;
import org.junit.Test;

/**
 * @author <a href="http://vidal.biz">Cedric Vidal</a>
 * 
 */
public class JodaGeneratorsTest {

	@Test
	public void testDateDomain() {
		Iterable<DateMidnight> dates = dateDomain(date(2013, 1, 1), 3);
		assertEquals(3, size(dates));
		Iterator<DateMidnight> iterator = dates.iterator();
		assertEquals(date(2013, 1, 1), iterator.next());
		assertEquals(date(2013, 1, 2), iterator.next());
		assertEquals(date(2013, 1, 3), iterator.next());
	}

	protected DateMidnight date(int year, int month, int day) {
		return new DateMidnight(year, month, day);
	}

	@Test
	public void testIntervalDomain() {
		Iterable<Interval> dates = intervalDomain(
				dateDomain(date(2013, 1, 1), 3), 2);
		assertEquals(6, size(dates));
	}

}

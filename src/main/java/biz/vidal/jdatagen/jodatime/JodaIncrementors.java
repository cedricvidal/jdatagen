package biz.vidal.jdatagen.jodatime;

import org.joda.time.DateMidnight;

import com.google.common.base.Function;

/**
 * @author <a href="http://vidal.biz">Cedric Vidal</a>
 * 
 */
public class JodaIncrementors {

	public static Function<DateMidnight, DateMidnight> plusDays(final int days) {
		return new Function<DateMidnight, DateMidnight>() {
			public DateMidnight apply(DateMidnight input) {
				return input.plusDays(days);
			}
			@Override
			public String toString() {
				return "plusDays(" + days + ")";
			}
		};
	}

}

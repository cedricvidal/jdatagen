package biz.vidal.jdatagen;

import java.util.Iterator;

import com.google.common.base.Function;

/**
 * @author <a href="http://vidal.biz">Cedric Vidal</a>
 * 
 */
public class Generators {

	public static <T> Iterable<T> generator(final T start,
			final Function<T, T> next) {
		return new Iterable<T>() {
			public Iterator<T> iterator() {
				return new Iterator<T>() {
					private T current = null;
	
					public void remove() {
					}
	
					public T next() {
						if (current == null) {
							current = start;
						} else {
							current = next.apply(current);
						}
						return current;
					}
	
					public boolean hasNext() {
						return true;
					}
				};
			}
			@Override
			public String toString() {
				return "generator(" + start + ", " + next + ")";
			}
		};
	}

}

package myMath;

import java.util.Comparator;


public class Monom_Comperator implements Comparator<Monom> {

	/**
	 * Compares two Monoms by their power.
	 *
	 * @param o1 Monom
	 * @param o2 Monom
	 * @return int 0 if equal, -1 if o1 is greater than o2, 1 if o2 is greater
	 */

	@Override
	public int compare(Monom o1, Monom o2) {
		if (o1.get_power() > o2.get_power()) return 1;
		if (o1.get_power() < o2.get_power()) return -1;
		return 0;
	}
}

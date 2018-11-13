package myMath;

import java.awt.Point;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * This class represents a Polynom with add, multiply functionality, it also should support the following:
 * 1. Riemann's Integral: https://en.wikipedia.org/wiki/Riemann_integral
 * 2. Finding a numerical value between two values (currently support root only f(x)=0).
 * 3. Derivative
 * 
 * @author Sagi Oshri and Itay Tuson
 *
 */

public class Polynom implements Polynom_able{

	private List<Monom> list;
	
	public Polynom() {
		list = new ArrayList<>();
	}

	public Polynom(Polynom_able polynom) {
		list = new ArrayList<>();

        Iterator<Monom> it = polynom.iteretor();

        while (it.hasNext()) {
            Monom next = it.next();
            Monom newMonom = new Monom(next);
		    list.add(newMonom);
        }

        if (!polynom.isZero()) refinePolynom();
    }
	
	public Polynom(String polynom) {
	    String trimmedPolynom = polynom.trim().replace("*","");

	    if (trimmedPolynom.length() > 0 && !(trimmedPolynom.length() == 1 && trimmedPolynom.contentEquals("0"))) {

	        list = extractPolynomFromString(polynom);
            refinePolynom();

        } else {
	        throw new RuntimeException(new RuntimeException("Can't initialize an empty String as a Polynom"));
        }

    }
	//ax^3 + bx^2 + cx + d = f(x)
	//(4,1),(5,3),(12,-4),(20,2)
/*	public Polynom(Point[] p) {
		if (p.length<2) {
			throw new RuntimeException("Can't build polynom from " + p.length + "points");
		}
		int pow=p.length-1;
		for (int i = 0; i < p.length; i++) {
			double x= p[i].getX();
			double y= p[i].getY();
			Polynom pn= new Polynom();
			for (int j = 0; j < pow; j++) {
				Monom newMonom = new Monom(x, j);
				pn.add(newMonom);
			}
		}
	}*/
	
	@Override
	public double f(double x) {
		
		double sum = 0;

		Iterator<Monom> it = iteretor();

	    while(it.hasNext()) {

	    	Monom monom = it.next();

	    	double result = monom.f(x);
	    	
	    	sum += result;

	      }
	    
		return sum;
	}

	@Override
	public void add(Polynom_able p1) {

        if (this == p1) {

            for (Monom monom : list) {

                monom.multiply(new Monom(2, 0));
            }

        } else {

            Iterator<Monom> it = p1.iteretor();

            while(it.hasNext()) {

                Monom monom = it.next();

                list.add(monom);
            }

            refinePolynom();
        }
	}

	@Override
	public void add(Monom m1) {

	    if (list.size() == 0) list.add(m1);

	    else {
	        boolean in = false;

            for (Monom monom : list) {
                if (monom.get_power() == m1.get_power()) {
                    monom.add(m1);
                    in = true;
                }
            }

            if (!in) {
                list.add(m1);
                refinePolynom();
            }
        }
	}

    @Override
	public void substract(Polynom_able p1) {

        if (this.equals(p1)) {
            list.clear();
        } else {

            Iterator<Monom> it = p1.iteretor();

            while (it.hasNext()) {

                Monom monom = it.next();

                monom = generateNegativeMonom(monom);

                list.add(monom);

            }

            refinePolynom();
        }
	}

	@Override
	public void multiply(Polynom_able p1) {

		Polynom tempPolynom;
		Polynom finalPolynom = new Polynom();

		refinePolynom();

        for (Monom myMonom : list) {

            tempPolynom = new Polynom();

            Iterator<Monom> it = p1.iteretor();

            while (it.hasNext()) {

                Monom tempMonom = new Monom(myMonom);

                Monom secMonom = it.next();

                tempMonom.multiply(secMonom);

                tempPolynom.add(tempMonom);
            }

            Iterator<Monom> it2 = tempPolynom.iteretor();

            while (it2.hasNext()) {
                Monom resultedMonom = it2.next();
                finalPolynom.add(resultedMonom);
            }
        }
		
		list.clear();
		this.add(finalPolynom);	
	}

	@Override
	public boolean equals(Polynom_able p1) {

	    // If this is the same Object
	    if (this == p1) return true;

		Polynom newPolynom = (Polynom) p1;

        refinePolynom();
        newPolynom.refinePolynom();
		
		if (list.size() != newPolynom.list.size()) {
			return false;
		}

        // Sizes are equal after refining

        Iterator<Monom> it = iteretor();
        Iterator<Monom> it2 = newPolynom.iteretor();

	    while(it.hasNext()) {
	    	
	    	Monom myMonom = it.next();
	    	Monom relateMonom = it2.next();
	    	
	    	if (!myMonom.equals(relateMonom)) {
	    		return false;
	    	}
	      } 
	      
		return true;
	}

	@Override
	public boolean isZero() {

        refinePolynom();

        return list.size() == 0;

    }

    /**
     * Source: https://www.geeksforgeeks.org/program-for-bisection-method/
     * @param x0 starting point
     * @param x1 end point
     * @param eps step (positive) value
     * @return double root point
     */
	@Override
    public double root(double x0, double x1, double eps) {

        double mid = x0;

        while((x1-x0) >= eps) {

            mid=(x0+x1)/2;

            if(this.f(mid)==0) return mid;
            else if (this.f(x0)*this.f(mid)<0) x1=mid;
            else x0=mid;
        }
        return mid;
    }

	@Override
	public Polynom_able copy() {

	    Polynom_able newPolynom = new Polynom();

        Iterator<Monom> it = iteretor();

        while (it.hasNext()) {
	        newPolynom.add(it.next());
        }

        return newPolynom;
	}

	@Override
	public Polynom_able derivative() {

        Polynom_able newPolynom = new Polynom();

        Iterator<Monom> it = iteretor();

        while(it.hasNext()) {
	    	
	    	Monom monom = it.next();
	    	monom = monom.derivative();
	    	newPolynom.add(monom);
	      }
		return newPolynom;
	}

	@Override
	public double area(double x0, double x1, double eps) {
		
		double sum = 0;
		
		for (double i = x0; i <= x1; i+=eps) {
			sum += this.f(i)*eps;
		}
		
		return sum;
	}
	
	/**
	 * 
	 * @param x0 starting point
	 * @param x1 ending point
	 * @param eps number of steps for the rectangles calculating the area
	 * @return the area under the X axis and above the function
	 */
	
	public double area2(double x0, double x1, double eps) {
		double sum = 0;
		for (double i = x0; i <= x1; i+=eps) {
			if(this.f(i)<0)
			sum += this.f(i)*eps;
		}
		return Math.abs(sum);
	}

	@Override
	public Iterator<Monom> iteretor() {
        return list.iterator();
	}

    /**
     * Prints the Polynomial sequence
     * @return String Polynom
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        DecimalFormat df = new DecimalFormat("#.##");

        if (list.size() == 0) {
            result.append("0");
        } else {
            for (int i=0;i<list.size();i++) {

                if (list.get(i).get_power() == 0) {
                    if (list.get(i).get_coefficient() >= 0 && i != 0) {
                        result.append("+").append(df.format(list.get(i).get_coefficient()));
                    }  else {
                        result.append(df.format(list.get(i).get_coefficient()));
                    }
                } else if (list.get(i).get_power() == 1){
                    if (list.get(i).get_coefficient() >= 0 && i != 0) {
                        result.append("+").append(df.format(list.get(i).get_coefficient())).append("*X");
                    }  else {
                        result.append(df.format(list.get(i).get_coefficient())).append("*X");
                    }
                } else {
                    if (list.get(i).get_coefficient() >= 0 && i != 0) {
                        result.append("+").append(df.format(list.get(i).get_coefficient())).append("*X^").append(list.get(i).get_power());
                    } else {
                        result.append(df.format(list.get(i).get_coefficient())).append("*X^").append(list.get(i).get_power());
                    }
                }
            }
        }
        return result.toString();
    }

    /**
     * Takes current Polynom and refines it.
     * Adds monoms with the same power, removes monoms that are equal to zero
     * and sorts monoms from low powers to high powers.
     */
	private void refinePolynom() {

        if (list.size() > 1) {
            for (int i=0;i<list.size();i++) {

                int power = list.get(i).get_power();
                double sum = list.get(i).get_coefficient();

                for (int z=i+1;z<list.size();z++) {

                    if (power == list.get(z).get_power()) {

                        sum += list.get(z).get_coefficient();

                        list.remove(z);
                        z--;
                    }
                }

                // If changed, update list
                if (sum != list.get(i).get_coefficient()) {
                    list.set(i, new Monom(sum, power));
                }

                // If zero, remove
                if (sum == 0)  {
                    list.remove(i);
                    i--;
                }
            }
            list.sort(new Monom_Comperator());
        }
	}

    /**
     * Takes monom and returns the negative monom.
     *
     * @param monom to change
     * @return Monom with negative coefficient
     */
	private Monom generateNegativeMonom(Monom monom) {
		
		double current = monom.get_coefficient();
		int power = monom.get_power();

		return new Monom((-1) * current, power);
	}

    /**
     * Takes a string that defines a polynom and extracting the Polynom Object.
     * Contains 2 helper functions: refineMonom() and addMonomFromString().
     *
     * @param polynom String to extract to Polynom Object
     * @return ArrayList<Monom> that represents a Polynom
     */
	private ArrayList<Monom> extractPolynomFromString(String polynom) {

		ArrayList<Monom> tempList = new ArrayList<>();
		
		polynom = polynom.replace(" ", "");
		
		if (polynom.contains("*")) {
			polynom = polynom.replace("*", "");
		}
				
		int lastIndex = 0;
		String tempMonom;
		
		for (int i=0;i<polynom.length();i++) {
			char c = polynom.charAt(i);
			
			if (c == '+' || c == '-' && i != 0) {
				
				tempMonom = polynom.substring(lastIndex, i);
				lastIndex = i;
                tempMonom = refineMonom(tempMonom);

				if (tempMonom.indexOf(0) != 'x' || tempMonom.indexOf(0) != 'X') {

                    addMonomFromString(tempList, tempMonom);

                } else {
					
					double coefficient = 1;
					int power;

					String stringPower = tempMonom.substring(2);
					power = Integer.parseInt(stringPower);

					tempList.add(new Monom(coefficient,power));
				}
			}

			if (i == polynom.length()-1) {

				tempMonom = polynom.substring(lastIndex, i+1);
                tempMonom = refineMonom(tempMonom);
                addMonomFromString(tempList, tempMonom);
            }
		
		}
		return tempList;
	}

    /**
     * 1. Helper function for extractPolynomFromString().
     * Takes a not-completed monom from the format: number or 'x' and returns a String with
     * the completed String monom that will fit the parent method.
     *
     * @param monom to be refined from an uncompleted format like "1" or "x"
     * @return String from the format: "double * X ^ int"
     */
	private String refineMonom(String monom) {

        boolean number = false, x = false;

        monom = monom.trim().toLowerCase();

        if (!monom.contains("^")) {

            if (monom.contains("1") || monom.contains("2") || monom.contains("3") || monom.contains("4")
                    || monom.contains("5") || monom.contains("6") || monom.contains("7") || monom.contains("8")
                    || monom.contains("9") || monom.contains("0")) {

                number = true;
            }

            if (monom.contains("x")) x = true;

            if (number && x) monom += "^1";
            else if (number) monom += "x^0";
            else if (x) monom += "^1";
        }

        return monom;
    }

    /**
     * 2. Helper function for extractPolynomFromString().
     * Takes the current tempList of monoms and tempMonom String and extract the
     * Monom Object, finally adds it to the tempList.
     *
     * @param tempList that represents the current monoms
     * @param tempMonom String to refine and add to tempList
     */
    private void addMonomFromString(ArrayList<Monom> tempList, String tempMonom) {

        for (int k = 0; k<tempMonom.length(); k++) {
            char stop = tempMonom.charAt(k);

            double coefficient;
            int power;
            int indexCheck = 0;
            boolean minus = false;

            if (stop == 'x' || stop == 'X') {

                if (tempMonom.charAt(0) == '-' || tempMonom.charAt(0) == '+') {
                    indexCheck = 1;

                    if (tempMonom.charAt(0) == '-') {
                        minus = true;
                    }
                }

                coefficient = getCoefficient(tempMonom, k, indexCheck, minus);

                String stringPower = tempMonom.substring(k+2);
                power = Integer.parseInt(stringPower);

                tempList.add(new Monom(coefficient,power));
            }
        }
    }

    /**
     * Helper function for addMonomFromString().
     * Takes parameters that define: Temporary String monom, 2 indexes and boolean that indicates if the coefficient is positive.
     * Returns the correct coefficient with a sign.
     *
     * @param tempMonom to extract the coefficient
     * @param k that represents the current index on the Monom String
     * @param indexCheck that represents the start index of the coefficient
     * @param minus that implies if coefficient is negative
     * @return double that represents the coefficient of the current monom
     */
    private double getCoefficient(String tempMonom, int k, int indexCheck, boolean minus) {
        double coefficient;

        if (k == indexCheck) {

            if (!minus) coefficient = 1;
            else coefficient = -1;

        } else {
            String stringCoefficient = tempMonom.substring(0,k);
            coefficient = Double.parseDouble(stringCoefficient);
        }
        return coefficient;
    }

    /**
     * Get specified monom at index from list
     * @param index
     * @return Monom in that index
     */
    public Monom getMonomAt(int index) {
        if (index > list.size() - 1) throw new NullPointerException("Monom in that index is not exist. List size is: " + list.size() + ", Index: " + index);
        return list.get(index);
    }
}


package myMath;
/**
 * This class represents a simple "Monom" of shape a*x^b, where a is a real number and a is an integer (summed a none negative), 
 * see: https://en.wikipedia.org/wiki/Monomial 
 * The class implements function and support simple operations as: construction, value at x, derivative, add and multiply.
 *
 * @author Sagi Oshri and Itay Tuson
 *
 */

public class Monom implements function{
	
	private double _coefficient;
	private int _power;

	public Monom(double a, int b){
		this.set_coefficient(a);
		this.set_power(b);
	}

	public Monom(Monom ot) {
		this(ot.get_coefficient(), ot.get_power());
	}

	/**
	 * Simple get method
	 * @return int that represent the Monom power
	 */
	public int get_power() {
		return _power;
	}

	/**
	 * Simple get method
	 * @return double that represents the Monom coefficient
	 */
	public double get_coefficient() {
		return _coefficient;
	}

	/**
	 * Simple set method
	 * @param a double value
	 */
	private void set_coefficient(double a){
		this._coefficient = a;
	}

	/**
	 * Simple set method
	 * @param p int value
	 */
	private void set_power(int p) {
		this._power = p;
	}

	/**
	 * Takes current monom and calculates it's derivative
	 * @return Monom's derivative
	 */
	public Monom derivative() {
		Monom newMonom;
		if(this._power>=1) {
			newMonom = new Monom(_coefficient * _power, _power-1);
		}
		else {
			newMonom = new Monom(0, 0);
		}
		return newMonom;
	}

	/**
	 * Takes another Monom and adds it to current Monom
	 * @param addedMonom Monom to add
	 */
	public void add(Monom addedMonom) {

		if (this._power != addedMonom._power) throw new RuntimeException("Can't add monoms with different powers");

        this.set_coefficient(this._coefficient + addedMonom._coefficient);
        this.set_power(this._power);

	}

	/**
	 * Takes another Monom and multiplies it with current Monom
	 * @param multipliedMonom Monom to multiply with
	 */
	public void multiply(Monom multipliedMonom) {

		this.set_coefficient(this._coefficient * multipliedMonom._coefficient);
		this.set_power(this._power + multipliedMonom._power);
	}

	/**
	 * Checks if two Monoms are equal
	 * @param m1 to be checked with current Monom
	 * @return true if given Monom is equal to current Monom
	 */
	public boolean equals(Monom m1) {

        return this._coefficient == m1._coefficient && this._power == m1._power;
    }

	/**
	 * Takes number and calculates the function at this point
	 * @param x as the point
	 * @return double that represents the result of function
	 */
	@Override
    public double f(double x) {
        return _coefficient * Math.pow(x,_power) ;
    }
}

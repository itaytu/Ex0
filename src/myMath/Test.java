package myMath;

/**
 * Test class that checks all the functions.
 *
 * @author Sagi Oshri and Itay Tuson
 */

public class Test {

    public static void main(String[] args) {
        test1();
        test2();
    }

    /**
     * Test the functionality of:
     * Polynoms add, subtract, multiply, derivative, value at x, root and area.
     */
    public static void test2() {
        System.out.println("\n\n-------test2-------\n");

        Polynom_able p1 = new Polynom("5x^5-4x^4+3x^3+10");
        Polynom_able p2 = new Polynom("2x^5-4x^4+x^3+1");

        System.out.println("Adding p2 to p1\n");
        System.out.println("("+p1 + ")+(" + p2+")");

        p1.add(p2);
        System.out.println("Expected \"11+4*X^3-8*X^4+7*X^5\":");
        System.out.println("Result: " + p1+"\n");


        System.out.println("Substract p2 from p1\n");

        System.out.println(p1 + "-(" + p2+")");
        p1.substract(p2);
        System.out.println("Expected \"10+3*X^3-4*X^4+5*X^5\":");
        System.out.println("Result: " + p1+"\n");

        System.out.println("Multiply p3 by p2\n");

        Polynom_able p3 = p1.copy();
        System.out.println("p1 Copied to p3");
        System.out.println("("+p3+")*("+p2+")");
        p3.multiply(p2);
        System.out.println("Expected \"10+13*X^3-44*X^4+25*X^5+3*X^6-16*X^7+27*X^8-28*X^9+10*X^10\":");
        System.out.println("Result: "+p3+"\n");

        System.out.println("Derivative of p1\n");
        System.out.println("("+p1+")\'");
        System.out.println("Expected \"9*X^2-16*X^3+25*X^4\":");
        System.out.println("Result: " + p1.derivative()+"\n");

        System.out.println("Derivative of p2\n");
        System.out.println("("+p2+")\'");
        System.out.println("Expected \"3*X^2-16*X^3+10*X^4\":");
        System.out.println("Result: " + p2.derivative() +"\n");

        System.out.println("Function check of p1 and p2\n");
        System.out.println(p1 + " : f(5) -> Expected 13510: " + p1.f(5));
        System.out.println(p2 + " : f(-8) -> Expected -82431: " + p2.f(-8));

        double eps = 0.00001;
        System.out.println("\nRoot check of p1 and p2\n");

        System.out.println("Root of p1 | Expected result around -0.95704: " + p1.root(-200, 300, eps));

        System.out.println("Root of p2 | Expected result around -0.6180: " + p2.root(-100, 100, eps));

        eps = 0.00001;
        System.out.println("\nArea check of p1 and p2\n");

        System.out.println("p1 | x0: -2, x1: 1 | Expected result around -60.15: " + p1.area(-2, 1, eps));
        System.out.println("p1 | x0: 0.5, x1: 2.5 | Expected result around 174.588: " + p1.area(0.5, 2.5, eps));
        System.out.println("p2 | x0: -0.5, x1: 1 | Expected result around 1.2375: " + p2.area(-0.5, 1, eps));
        System.out.println("p2 | x0: 0, x1: 1 | Expected result around 0.7833: " + p2.area(0, 1, eps));
    }

    /**
     * Test of Monom Constructors:
     * double coef, int pow
     * Copy Constructor
     * Polynom constructors:
     * -Copy Constructor
     * -String Constructor and ToString check
     * -Empty Constructor
     * IsZero test
     * Equals test
     */
    public static void test1() {
        System.out.println("\n\n-------test1-------\n");

        System.out.println("Monom Constructor:");
        Monom m = new Monom(1.73, 5);
        System.out.println("Expected coeffecient: 1.73\nExpected power: 5");
        System.out.println("Actual coeffecient: " + m.get_coefficient() +"\nActual power: " + m.get_power());

        System.out.println("\nPolynom Constructor:");
        Polynom_able p1 = new Polynom();						//empty constructor
        System.out.println("Empty Constructor p1:");
        System.out.println("The polynom p1 should be empty");
        System.out.println(p1 + "(is Polynom Empty: " + p1.isZero()+")");


        Polynom_able p2 = new Polynom("3x^2 - 7x + 3x + 4 + 10x^5 -4");	//string constructor
        System.out.println("\nString Constructor & ToString check");
        System.out.println("The expected Polynom p2:\n-4*x+3*x^2+10*x^5");
        System.out.println("Your Polynom is:\n" + p2.toString());
        Polynom p3 = new Polynom("7 + x^2 - 4x^3");


        Polynom_able p4 = new Polynom(p3);								//Copy constructor
        System.out.println("\nCopy Constructor p3 to p4:");
        System.out.println("The expected Polynom p4:\n7+x^2-4*x^3");
        System.out.println("Your Polynom is:\n" + p4.toString());


        System.out.println("\nIsZero test:");
        Polynom_able p5 = new Polynom("10x + x^3 + 7 - 8x");	//isZero test
        Monom m1 = new Monom (-2, 1);
        Monom m2 = new Monom (-7, 0);
        Monom m3 = new Monom (-1, 3);
        p5.add(m1);
        p5.add(m2);
        p5.add(m3);
        Polynom_able p6 = new Polynom("10x + x^3 - 8x - x^3 - 2x");
        Monom m4 = new Monom (-2, 1);
        p6.add(m4);
        System.out.println("The expected output for p5 is: true");
        System.out.println("Your output is: " + p5.isZero());
        System.out.println("The expected output for p6 is: false");
        System.out.println("Your output is: " + p6.isZero());


        System.out.println("\nEquals test:");								//Equals test
        Polynom_able p7 = new Polynom("1 + 3x - x^2 + 11.3x^3");
        Polynom_able p8 = new Polynom("3 + 7x - 7x^2 + 21.3x^3 - 10x^3 - 4x + 6x^2 - 2");
        System.out.println("the expected output for p7 & p8 is: true");
        System.out.println("Your output is: " + p7.equals(p8));
        Polynom_able p9 = new Polynom("1 + 3x - x^2 + 11.3x^3");
        Polynom_able p10 = new Polynom("3 + 7x - 7x^2 + 21.3x^3 - 10x^3 - 4x + 6x^2 - 2 + x^5");
        System.out.println("the expected output for p9 & p10 is: false");
        System.out.println("Your output is: " + p9.equals(p10));
    }
}

package myMath;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class PolynomTest {

    Polynom p, p2;

    @BeforeEach
    void setUp() {
        p = new Polynom("3x^2 + 7x - 2");
        p2 = new Polynom("x^2 + 7.5x - 2x^5");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void f() {
        double result = p.f(3);
        assertEquals(46,result, "Function is wrong");
    }

    @Test
    void addPolynom() {
        p.add(p2);

        Polynom expected = new Polynom("4x^2 + 14.5x -2 -2x^5");


        assertTrue(p.equals(expected), "1. addPolynom function is wrong");
        assertFalse(p.equals(p2), "2. addPolynom function is wrong");

    }

    @Test
    void addMonom() {
        Monom m1 = new Monom(3.5,2);
        Monom m2 = new Monom(3,1);
        p.add(m1);
        p.add(m2);

        Polynom expected = new Polynom("6.5x^2 + 10x -2");
        assertTrue(p.equals(expected), "1. addMonom function is wrong");
        assertFalse(p.equals(p2), "2. addMonom function is wrong");
    }

    @Test
    void substract() {
        Polynom p1 = new Polynom("3x^2 + 7x - 2");
        Polynom expected = new Polynom();

        p.substract(p1);

        assertTrue(p.equals(expected), "1. substract function is wrong");
        assertFalse(p.equals(p2), "2. substract function is wrong");
    }

    @Test
    void multiply() {
        Polynom expected = new Polynom("3x^4+22.5x^3-6x^7+7x^3+52.5x^2-14x^6-2x^2-15x+4x^5");
        p.multiply(p2);
        assertTrue(p.equals(expected), "multiply function is wrong");
    }

    @Test
    void equals() {
        Polynom p3 = new Polynom("x^2 + 7.5x - 2x^5");

        assertTrue(p.equals(p),"1. equals function is wrong");
        assertFalse(p.equals(p2),"2. equals function is wrong");
        assertTrue(p2.equals(p3),"3. equals function is wrong");
    }

    @Test
    void isZero() {
        Polynom p3 = new Polynom();
        p.substract(p);

        assertTrue(p3.isZero(),"1. isZero function is wrong");
        assertTrue(p.isZero(),"2. isZero function is wrong");
        assertFalse(p2.isZero(),"3. isZero function is wrong");
    }

    @Test
    void root() {
        double eps = 0.001;
        double root1 = 0.25733;
        double root2 = -2.59066;

        assertEquals(root1,p.root(0,1,eps),0.001,"1. root function is wrong");
        assertEquals(root2,p.root(-3,0,eps),0.001,"2. root function is wrong");
    }

    @Test
    void copy() {
        Polynom pCopied = new Polynom(p);

        assertTrue(pCopied.equals(p),"1. copy function is wrong");
        assertFalse(pCopied.equals(p2),"2. copy function is wrong");
    }

    @Test
    void derivative() {
        Polynom derivativedPolynom = (Polynom) p.derivative();
        Polynom expected = new Polynom("6x + 7");

        assertTrue(derivativedPolynom.equals(expected),"1. derivative function is wrong");
        assertFalse(derivativedPolynom.equals(p),"2. derivative function is wrong");
    }

    @Test
    void area() {
        double eps = 0.001;
        int expected = -2;

        assertEquals(expected,p.area(-1,1,eps),0.01, "area function is wrong");
    }
    
    @Test
    void area2() {
    	Polynom p3= new Polynom("0.2x^4-1.5x^3+3.0x^2-x-5");
    	double expected= 25.1836;
    	double actual= p3.area2(-2, 6, 0.01);
    	assertEquals(expected, actual, 0.01,"The function is wrong");
    }

    @Test
    void iteretor() {
        Iterator<Monom> it = p.iteretor();
        int index = 0;

        while (it.hasNext()) {

            Monom monom =  it.next();
            Monom expected = p.getMonomAt(index);
            index++;

            switch (index) {
                case 0:
                    assertTrue(monom.equals(expected));
                    break;
                case 1:
                    assertTrue(monom.equals(expected));
                    break;
                case 2:
                    assertTrue(monom.equals(expected));
                    break;
                default:
                    break;
            }
        }
    }
}
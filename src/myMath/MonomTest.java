<<<<<<< HEAD
package myMath;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MonomTest {
	Monom m1, m2;
	
	@BeforeEach
	void setUp() throws Exception {
		m1= new Monom(7.3, 3);
		m2= new Monom(-13, 3);
	}

	@Test
	void testDerivative() {
		Monom m=new Monom(21.9, 2);
		boolean flag = false;
		m1=m1.derivative();
		if(m1.get_coefficient()==m.get_coefficient() && m1.get_power()==m.get_power()) flag = true;
		assertEquals( true, flag, "Function is wrong");
	}

	@Test
	void testAdd() {
		Monom m=new Monom(-5.7,3);
		boolean flag = false;
		m1.add(m2);
		if(m1.get_coefficient()==m.get_coefficient() && m1.get_power()==m.get_power()) flag = true;
		assertEquals(true , flag,"Function is wrong");
	}

	@Test
	void testMultiply() {
		Monom m=new Monom(-94.9, 6);
		boolean flag = false;
		m1.multiply(m2);
		assertEquals(m1.get_coefficient(), m.get_coefficient(), 0.0001, "Function is wrong");
		assertEquals(m1.get_power(), m.get_power(), "Function is wrong");
	}

	@Test
	void testEqualsMonom() {
		assertEquals(false, m1.equals(m2), "Function is wrong");
	}

	@Test
	void testF() {
		double x=m1.f(2);
		assertEquals(58.4, x, "Function is wrong");
	}
=======
package myMath;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MonomTest {
	Monom m1, m2;
	
	@BeforeEach
	void setUp() throws Exception {
		m1= new Monom(7.3, 3);
		m2= new Monom(-13, 3);
	}

	@Test
	void testDerivative() {
		Monom m=new Monom(21.9, 2);
		boolean flag = false;
		m1=m1.derivative();
		if(m1.get_coefficient()==m.get_coefficient() && m1.get_power()==m.get_power()) flag = true;
		assertEquals( true, flag, "Function is wrong");
	}

	@Test
	void testAdd() {
		Monom m=new Monom(-5.7,3);
		boolean flag = false;
		m1.add(m2);
		if(m1.get_coefficient()==m.get_coefficient() && m1.get_power()==m.get_power()) flag = true;
		assertEquals(true , flag,"Function is wrong");
	}

	@Test
	void testMultiply() {
		Monom m=new Monom(-94.9, 6);
		boolean flag = false;
		m1.multiply(m2);
		assertEquals(m1.get_coefficient(), m.get_coefficient(), 0.0001, "Function is wrong");
		assertEquals(m1.get_power(), m.get_power(), "Function is wrong");
	}

	@Test
	void testEqualsMonom() {
		assertEquals(false, m1.equals(m2), "Function is wrong");
	}

	@Test
	void testF() {
		double x=2;
		assertEquals(58.4, m1.f(x), "Function is wrong");
	}
>>>>>>> 50ae85a741bcbfda6b2a3c4d7f94a43fa4c32fd4
}
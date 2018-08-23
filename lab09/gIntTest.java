// gIntTest - tests the gInt class
// lab09
//
// Chris Kasper
// 12/1/2017
//
//import org.junit.Test;
//import junit.framework.* ;

import java.io.*;
import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;

public class gIntTest {

	private gInt x;
	private gInt y;
	private gInt z;

	@Before	// Run before *each* test
	public void setUp() {
		x = new gInt(3);
		y = new gInt(1,4);
		z = new gInt(2,-2);
	}

	// Test methods

	@Test
	public void testReal() // Checks the real() method from gInt
	{
		int expected_x_real = 3; 
		int expected_y_real = 1;
		int expected_z_real = 2;

		assertEquals(x.real(),expected_x_real);
		assertEquals(y.real(),expected_y_real);
		assertEquals(z.real(),expected_z_real);
	}

	@Test
	public void testImag() // Checks the imag() method from gInt
        {
		int expected_x_imag = 0;
                int expected_y_imag = 4;
                int expected_z_imag = -2;

                assertEquals(x.imag(),expected_x_imag);
                assertEquals(y.imag(),expected_y_imag);
                assertEquals(z.imag(),expected_z_imag);
        }
	
	@Test
	public void testAdd() // Checks the add() method from gInt
	{
		gInt yz = y.add(z);
		gInt xy = x.add(y);

		int expected_1r = 3; // real part of y+z
		int expected_1i = 2; // imag part of y+z

		int expected_2r = 4; // real part of x+y
		int expected_2i = 4; // imag part of y+z

		assertNotNull(yz);
		assertNotNull(xy);	
	
		assertEquals(expected_1r,yz.real());
		assertEquals(expected_1i,yz.imag());
		assertEquals(expected_2r,xy.real());
		assertEquals(expected_2i,xy.imag());
	}

	@Test
        public void testMult() // Checks the multiply() method from gInt
        {
                gInt yz = y.multiply(z);
                gInt xy = x.multiply(y);

                int expected_1r = 10; // real part of y*z
                int expected_1i = 6; // imag part of y*z

                int expected_2r = 3; // real part of x*y
                int expected_2i = 12; // imag part of y*z

                assertNotNull(yz);
                assertNotNull(xy);

                assertEquals(expected_1r,yz.real());
                assertEquals(expected_1i,yz.imag());
                assertEquals(expected_2r,xy.real());
                assertEquals(expected_2i,xy.imag());
        }

	@Test
	public void testNorm() // Checks the norm() method from gInt
	{
		float x_norm = x.norm();
		float y_norm = y.norm();
		float z_norm = z.norm();

		double expected_xnorm = 3.0;
		double expected_ynorm = 4.1231056;
		double expected_znorm = 2.8284271;

		assertEquals(x_norm,(float)expected_xnorm,0.0002);
		assertEquals(y_norm,(float)expected_ynorm,0.0002);
		assertEquals(z_norm,(float)expected_znorm,0.0002);
	}

	public static void main( String [] args )
	{
		org.junit.runner.JUnitCore.runClasses( gIntTest.class ) ;
		//// Or, from the command line:
		// $ java org.junit.runner.JUnitCore MoneyTest
	}
}


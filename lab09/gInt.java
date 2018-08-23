// gInt.java (lab09)
//
// Chris Kasper
// 12/1/17

import java.util.*;
import java.io.*;

public class gInt {

	protected int real;
	protected int imag;

	public int real()
	{
		return real;		
	}

	public int imag()
	{
		return imag;
	}

	public gInt add( gInt rhs )
	{
		int n_real = real + rhs.real();
		int n_imag = imag + rhs.imag();
		gInt n_gint = new gInt(n_real,n_imag);	
		return n_gint;
	}

	public gInt multiply( gInt rhs )
        {
		int n_real = real*rhs.real()-imag*rhs.imag();
                int n_imag = real*rhs.imag()+imag*rhs.real();
                gInt n_gint = new gInt(n_real,n_imag);
                return n_gint;	
        }

	public float norm()
	{
		double l2 = Math.sqrt((real*real)+(imag*imag));
		return (float)l2;
	}

	public gInt(int r, int i)
	{
		this.real = r;
		this.imag = i;
	}

	public gInt(int r)
        {
                this.real = r;
                this.imag = 0;
        }

	public static void main(String[] args) {
		gInt x = new gInt(2,1);
		gInt y = new gInt(1,3);

		System.out.println("x="+x.real()+"+"+x.imag()+"i");
		System.out.println("y="+y.real()+"+"+y.imag()+"i");
				
		gInt z = x.add(y);
		System.out.println("z="+z.real()+"+"+z.imag()+"i");

  		gInt a = x.multiply(y);
		System.out.println("a="+a.real()+"+"+a.imag()+"i");

		System.out.println("L2 of a ="+a.norm());

	}
	
}

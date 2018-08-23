/* Chris Kasper
 * prob3.java -> 3 from Cattle Drive
 *
*/
import java.io.* ;

public class prob3
{
	public static void main( String [] argv )
	{
		int num = Integer.parseInt(argv[0]);
		if ((num % 100) == 0 && (num % 400) != 0 )
		{
			System.out.println("Not a leap year");
		}
		else if ( (num % 4) == 0 )
		{
			System.out.println("Leap year!");
		}
		else
		{
			System.out.println("Not a leap year");
		}
	}	
}

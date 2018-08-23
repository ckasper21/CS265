/* Chris Kasper
 * prob2.java -> 2 from Cattle Drive
 *
*/
import java.io.* ;

public class prob2
{
	public static void main( String [] argv )
	{
		int num = Integer.parseInt(argv[0]);
		if ( (num % 2) == 0)
		{
			System.out.println("even");
		}
		else
		{
			System.out.println("odd");
		}
	}	
}

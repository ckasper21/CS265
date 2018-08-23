/* Chris Kasper
 * prob4.java -> 8 from Cattle Drive
 *
*/
import java.io.* ;
import java.util.Date;

public class prob4
{
	public static void main( String [] argv )
	{
		int code = Integer.parseInt(argv[0]);
		Date date = new Date();
		long mil = date.getTime();
		long sec = mil/1000 ;
		long days = sec/86400 ;
		switch( code )
		{
			case 0:
				System.out.println("milliseconds since January 1, 1970: " + mil);
				break;
			case 1:
				System.out.println("seconds since January 1, 1970: " + sec);
				break;
			case 2:
				System.out.println("days since January 1, 1970: " + days);
				break;
			case 3:
				System.out.println("Current date and time: " + date);
				break;
		}
	}	
}

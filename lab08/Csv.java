/* Chris Kasper
 * Csv.java -> parsing CSVs
*/

import java.io.* ;
import java.util.*;

public class Csv
{
	private String line;
	private Scanner fin;
	private ArrayList<String> fields = new ArrayList<String>();

	// Initialize CSV object
	public Csv(String file) {
		try {
			fin = new Scanner(new FileReader(file));
		} catch (Exception error) {
			System.out.println(error);
		}
	}	 
	// Get next line in CSV
	public String getLine(){
		if (fin.hasNextLine()){
			line=fin.nextLine();
		} 
		else {
			return null;
		}
		fields = split(line); // Split CSV records
		return line;
	}
	// Split function - separator is comma
        private ArrayList<String> split(String line){
		ArrayList<String> flds = new ArrayList<String>(); // Indexable fields
		int i,j;
		i=0;
		if (line.length()==0){
			return flds;
		}	
		do {
			if (i<line.length() && line.charAt(i)=='"') { // Check for quotes
				StringBuffer field = new StringBuffer(); //Buffer is better then String object
				j=advquoted(line,++i,field);
				flds.add(field.toString()); //Add to arraylist from bufferstring
			} else {
				j=advplain(line,i++);
				flds.add(line.substring(i-1,j)); // Add to arraylist
			}
			i=j+1;
		} while (j < line.length());
		return flds; // return arraylist of line
	}
	// Find next separator when quote exists
        private int advquoted(String line, int i, StringBuffer field){
        	int j;
		for (j=i;j<line.length();j++){ 
			if (line.charAt(j)=='"' && (++j ==line.length() || line.charAt(j) != '"')) { // Indexing till the last quote is found
				int k=line.indexOf(",",j); 
				if (k==-1) { // Separator not found
					k=line.length();
				}
				field.append(line.substring(j,k));
				j=k;
				break;
			}
			field.append(line.charAt(j)); // Add to buffer
		}
		return j; // return index of last quote
	}
	// Find next separator for plain text
	private int advplain(String line, int i){
        	int j;
		j = line.indexOf(',',i); // Find next separator
  	        if (j==-1) { // Index of -1 means it wasn't found. Indiciating the end of the line. 
                       	j=line.length();
                }
		return j;

	}

        public int getnfield(){  // Get number of fields from current line
		return fields.size();
        } 

        public String getfield(int n){  // Get specific field from current line
		if ( n<0 || n>= fields.size() ) {
			return "";
		} else {
			return fields.get(n);
		} 
        }

	public static void main( String [] argv ){
		// Test for lab 8
		String file="test.csv";
		Csv csvfile=new Csv(file);

		String c_line=csvfile.getLine(); // current line			
		while (c_line!=null){
			System.out.println("line= " + c_line);
			int i;
			for (i=0; i<csvfile.getnfield(); i++){
				System.out.println("field["+i+"]: "+csvfile.getfield(i));
			}
			c_line=csvfile.getLine();
		}
	}	
}

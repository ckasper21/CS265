import java.util.*;
import java.io.*;

/**
* <h1>Infixparser</h1>
* This program converts infixed expressions to postfix expressions to then
* be evaluated.
* 
* @author Chris Kasper
* @since 11/22/17
*/
// javac 1.8.0_144

public class infixparser {
	
	/**
	* Accepts elements of an expression to determine whether or not
	* it is an operand or operator and returns the element as a token.
	*
	* @param String : elements such as '2','1','+','/',etc
	* @return Token : will be either an operand or operator
	*/	
	
	static public Token tokenParser(String element)
	{
		Token t = new Operand(0);
		try {
                        int x = Integer.parseInt(element);
                        t = new Operand(x);
                } catch (Exception e) {
                        switch (element) {
				case "+":
					t = new Operator(opType.ADD);
					break;
				case "-":
					t = new Operator(opType.SUB);
                                        break;
				case "*":
                                        t = new Operator(opType.MULT);
                                        break;
				case "/":
                                        t = new Operator(opType.DIV);
                                        break;
				case "%":
                                        t = new Operator(opType.MOD);
                                        break;
				case "(":
                                        t = new Operator(opType.LPAR);
                                        break;
				case ")":
                                        t = new Operator(opType.RPAR);
                                        break;
			}
                }
		return t;
	}

	/**
	* Accepts an infix expression and returns 
	* its postfix representation.
	* 
	* @param String : infix expression
	* @return ArrayList<Token> : postfix expression
	*/

	static public ArrayList<Token> infix2postfix(String eq)
	{	
		eq+=" )";
		Token t = new Operator(opType.LPAR);
		Stack<Token> s = new Stack<Token>();
		ArrayList<Token> tl = new ArrayList<Token>();
		String[] split = eq.trim().split("\\s+");
		s.push(t);

		for (int i=0;i<split.length;i++){
			t = tokenParser(split[i]);
			if (t.isOperator()){
				Operator op = (Operator)t;
				if (op.getVal()==opType.LPAR){
					s.push(t);
				} else if (op.getVal()==opType.RPAR){
					t = s.pop();
					op = (Operator)t;
					while (op.getVal()!=opType.LPAR){
						tl.add(t);
						t=s.pop();	
						op=(Operator)t;
					}	
				} else {
					Operator p = (Operator)s.peek();
					while ((op.getPrec() <= p.getPrec()) && (p.getVal().getName()!="LParen")){
						tl.add(s.pop());
						p = (Operator)s.peek();
					}		
					s.push(t);
				}
			
			} else {
				tl.add(t);
			}		
		}
		return tl;
	}
	
	/**
	* Accepts the postfix representation of an expression and
	* returns its output.
	*
	* @param ArrayList<Token> postfix expression
	* @return int postfix expression output
	*/

        static public int evalPostfix(ArrayList<Token> tl)
        {
		Stack<Token> s = new Stack<Token>();
		int i=0;
		while (i<tl.size()){
			Token t = tl.get(i);
			i++;
			if (t.isOperand()){
				s.push(t);
			} else {
				Operand y = (Operand)s.pop();
				Operand x = (Operand)s.pop();
				int ans = 0;
				Operator op = (Operator)t;
				String sym = op.getVal().getName();
				switch (sym){
                                        case "Add":
                                        ans = x.getVal() + y.getVal();
                                        break;
                                        case "Sub":
                                        ans = x.getVal() - y.getVal();
                                        break;
                                        case "Mult":
                                        ans = x.getVal() * y.getVal();
                                        break;
                                        case "Div":
                                        ans = x.getVal() / y.getVal();
                                        break;
                                        case "Mod":
                                        ans = x.getVal() % y.getVal();
                                        break;
                                }
				s.push(new Operand(ans));
			}
		}
		Operand result = (Operand)s.pop();
		return result.getVal();
        }

	/**
	* Prints postfix expressions
	*/
	
        static public void printPostfix(ArrayList<Token> tl, int ans)
        {
		String exp = "";
		int i=0;
		while (i<tl.size()){
	                Token t = tl.get(i);
			i++;
			if ( t.isOperator() ) {
				Operator op = (Operator)t;
				String sym = op.getVal().getName();	
				switch (sym){
					case "Add":
					exp += "+ ";
					break;
                                        case "Sub":
                                        exp += "- ";
                                        break;
                                        case "Mult":
                                        exp += "* ";
                                        break;
                                        case "Div":
                                       	exp += "/ ";
                                        break;
                                        case "Mod":
                                       	exp += "% ";
                                        break;
				}
			} else {
				Operand num = (Operand)t;
				exp+=num.getVal()+" ";
			}
		}
		System.out.println(exp+ "= " +ans);
        }

	/**
	* Looks for file named 'input.infix' that contains infix expressions
	* to be evaluated (if filename is given upon call, it will use that instead)  
	*/

	static public void main( String[] argv )
	{	
		ArrayList<Token> postfix = new ArrayList<Token>();
		Scanner sc;
		String file = "";
		
		if (argv.length==0){
			file = "input.infix";
		} else {
			file = argv[0];
		}
					
		try {
			sc = new Scanner(new File(file));
			while (sc.hasNext()){
                        	postfix = infix2postfix(sc.nextLine());
				int ans = evalPostfix(postfix);
				printPostfix(postfix,ans);
			} 
		} catch (FileNotFoundException e) {
				System.out.println(e);
		}	
	}
}

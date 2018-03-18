import java.util.*;

//Simplificacion del ejercicio para no usar archivos:
//Fijarse si una expresión matemática esta bien escrita sintácticamente

public class Ejer8{

	private static Stack<Character> stack;

	public static void main (String[] args){
		stack = new Stack<Character>();
		String expr = "{(asd)}[{()}](asd)";
		if(wellWritten(expr)){
			System.out.println("Well Written");
		}else{
			System.out.println("Error");
		}
	}

	private static boolean wellWritten(String s){
		for(Character c : s.toCharArray()){
			if(c == '{' || c == '(' || c == '['){
				stack.push(c);
			}else if(c == '}'){
				if(!stack.isEmpty() && stack.peek().equals('{')){
					stack.pop();
				}else{
					return false;
				}
			}else if( c == ')'){
				if(!stack.isEmpty() && stack.peek().equals('(')){
					stack.pop();
				}else{
					return false;
				}
			}else if( c == ']'){
				if(!stack.isEmpty() && stack.peek().equals('[')){
					stack.pop();
				}else{
					return false;
				}
			}
		}
		return true;
	}
}
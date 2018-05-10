import java.util.*;

public class Ejer4{

	private Map<Character, Character[]> keyboard;
	private Stack<Character> stack;

	private void initKeyboard(){
		keyboard = new HashMap<Character, Character[]>();
		keyboard.put('1', null);
		keyboard.put('2', new Character[]{'A', 'B', 'C'});
		keyboard.put('3', new Character[]{'D', 'E', 'F'});
		keyboard.put('4', new Character[]{'G', 'H', 'I'});
		keyboard.put('5', new Character[]{'J', 'K', 'L'});
		keyboard.put('6', new Character[]{'M', 'N', 'O'});
		keyboard.put('7', new Character[]{'P', 'Q', 'R', 'S'});
		keyboard.put('8', new Character[]{'T', 'U', 'V'});
		keyboard.put('9', new Character[]{'W', 'X', 'Y', 'Z'});
		keyboard.put('0', null);
	}

	public static void main(String[] args){
		Ejer4 ejer = new Ejer4();
		ejer.stack = new Stack<Character>();
		ejer.initKeyboard();
		ejer.numbcombinations("234");
	}

	public void numbcombinations(String s){
		if(s == null) return;
		char[] numbers = s.toCharArray();
		Character[] partRet = new Character[s.length()];
		Character c;
		int index = 0;
		pushLetters(numbers[index]); // introduce first letters		
		while(!stack.isEmpty()){
			if(index == s.length()){
				if(index != -1) printArray(partRet);
				index--;
			}else{
				c = stack.pop();
				if(c == null){ //va a ser -1 cuando termine todas las combinaciones
					index--;	
				} else{
					partRet[index++] = c;
					if(index >= 0 && index != s.length()) pushLetters(numbers[index]);			
				}				
			}			
		}
	}

	private void pushLetters(char numb){
		stack.push(null);
		for(int i = keyboard.get(numb).length; i > 0; i--){
			stack.push(keyboard.get(numb)[i - 1]);
		}
	}

	private void printArray(Character[] array){
		for(Character c : array){
			System.out.print(c + " ");
		}
		System.out.println();
	}
}
import java.util.*;

public class Ejer15{
	
	private Map<Character, Character[]> keyboard;

	public static void main(String[] args){
		Ejer15 ejer = new Ejer15();
		ejer.initKeyboard();
		ejer.numbcombinations("256");
	}

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

	/* Se puede ver del ejemplo del partialResultado a "256" : 
	AJM, AJN, AJO, AKM, AKN, AKO, ALM, ALN, ALO,
	BJM, BJN, BJO, BKM, BKN, BKO, BLM, BLN, BLO,
	CJM, CJN, CJO, CKM, CKN, CKO, CLM, CLN, CLO

	Que la 2da y 3era solución son idénticas a la primera salvo la primer letra
	de cada palabra. Por eso, creo que es ineficiente repetir el cálculo de todas
	las permutaciones en vez de simplemente guardar una única solución y luego 
	"pisar" las letras con los demás caracteres
	*/

	public void numbcombinations(String s){
		Character[] firstLetters = null, partialResult = null, result = null;
		char[] numbers = s.toCharArray();
		int totalSize = 1;
		for(Character c : numbers){
			if(firstLetters == null){
				firstLetters = keyboard.get(c);			
				totalSize = firstLetters.length;
			}else{
				totalSize *= keyboard.get(c).length;	
			}			
		}
		keyboard.replace('0', new Character[] {keyboard.get(numbers[0])[0]});
		numbers[0] = '0';
		partialResult = new Character[numbers.length]; //va a guardar las combinaciones
		result = new Character[totalSize];
		calculateInitialSolution(partialResult, numbers, result, 0, 0);
		for(Character let : result){
			System.out.print(let);
		}
		System.out.println();
		for(int i = 1; i < firstLetters.length; i++){			
			stepFirstLetterOfWord(firstLetters[i], result, numbers.length);
		}
		keyboard.replace('0', null);		
	}


	/* Mismo problema que: dado n arrays de elementos, entregar todas las posibles 
	** permutaciones de sus elementos.
	** En este caso, n sería numbers.length -1, que son los distintos grupos de letras */

	//https://www.geeksforgeeks.org/find-possible-words-phone-digits/ funciona pero no guarda los resultados

	//Modificado para que guarde los resultados en un array

	private int calculateInitialSolution(Character[] partialResult, char[] numbers, Character[] result, int currentNumber, int indx){
		if(currentNumber == numbers.length){
			//save array
			for(int i = 0; i < partialResult.length; i++){
				if(partialResult[i] != null){
					result[indx++] = partialResult[i];
				}
			}
			return indx;
		}
		int resIndx = indx;
		for(int i = 0; i < keyboard.get(numbers[currentNumber]).length; i++){
			partialResult[currentNumber] = keyboard.get(numbers[currentNumber])[i];
			resIndx = calculateInitialSolution(partialResult, numbers, result, currentNumber + 1, resIndx);
			if(numbers[currentNumber] == 1) return resIndx;			
		}
		return resIndx;
	}

	private void stepFirstLetterOfWord(Character c, Character[] result, int combLength){
		for(int i = 0; i < result.length; i++){
			if(i % combLength == 0){
				result[i] = c;
			}
		}
		for(Character let : result){
			System.out.print(let);
		}
		System.out.println();
	}
}
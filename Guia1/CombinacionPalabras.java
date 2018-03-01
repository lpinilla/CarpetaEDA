//Dado un string (Ej "ABC") entregar todas sus posibles combinaciones
import java.util.*;

public class CombinacionPalabras{

	private static String s = "ABBA";

	public static void main(String args[]){
		comb(s);
	}
	public static void comb(String w){
		if(w == null) throw new IllegalArgumentException();
		Map<Character, Integer> m = new HashMap<Character, Integer>();
		for(Character c : w.toCharArray()){
			if(m.containsKey(c)){
				m.replace(c, m.get(c) +1);
			}else{
				m.put(c, 1);
			}			
		}
		combRec("", m);
	}

	private static void combRec(String curr, Map<Character, Integer> map){
		if(allCharactersUsed(map)) {
			System.out.println(curr);
			return;
		}
		for(Character c : map.keySet()){
			if(map.get(c) != 0){				
				map.replace(c, new Integer(map.get(c) -1));
				curr = curr.concat(c.toString());
				combRec(curr, map);
				map.replace(c, new Integer(map.get(c) +1));
				curr = curr.substring(0, curr.length() -1);
			}
		}
	}

	private static boolean allCharactersUsed(Map<Character, Integer> m){
		for(int b : m.values()){
			if(b != 0) return false;
		}
		return true;
	}

}
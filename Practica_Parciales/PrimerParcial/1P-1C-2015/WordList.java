public interface WordList {

	/** Agrega una palabra al final de la lista. */
	public void add(String word);
	
	/** Imprime las palabras almacenadas, en el orden que fueron insertadas. */
	public void print();
	
	/** Elimina todas las palabras que tienen cantidad par de letras. */
	public void removeEven();
	
	/** Elimina todas las palabras que tienen cantidad impar de letras. */
	public void removeOdd();	
}
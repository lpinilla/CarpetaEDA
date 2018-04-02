public interface Bag<T> {

	/** Agrega el elemento*/
	public void add(T value); //O(n)
	
	/** Devuelve la cantidad de veces que el elemento pasado por parámetro existe en la colección. */
	public int occurrencesOf(T value); //O(n)

	/** Elimina el elemento que más veces aparece en ese momento y lo devuelv­­­e. */
	public T removeMostPopular(); //O(1)


}
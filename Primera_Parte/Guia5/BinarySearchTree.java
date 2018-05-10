public interface BinarySearchTree<T> implements Iterable<BinaryTree<T>>{
	/**
	* Agrega una clave al árbol.
	* Si ya existe, no hace nada y el árbol no se modifica.
	*/
	public void add(T key);
	/**
	* Elimina una clave del árbol.
	* Si no existe, no hace nada y el árbol no se modifica.
	*/
	public void remove(T key);
	/**
	* Determina si el árbol contiene o no una clave.
	*/
	public boolean contains(T key);
	/**
	* Retorna la cantidad de claves almacenadas.
	*/
	public int size();

	//Ejer9

	public int heightOfValue(T value);
	
	public int nOfLeaves();

	public T biggestElem();

	public void printNodeParents(BinaryTree<T> tree);

	public void printNodeChildren(BinaryTree<T> tree);

	public Iterator<BinaryTree<T>> preOrderIterator();

	public Iterator<BinaryTree<T>> InOrderIterator();	

	public Iterator<BinaryTree<T>> PostOrderIterator();	

}
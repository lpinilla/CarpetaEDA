public class Ejer5{

	/*Lo más conveniente seria utilizar un flag
	**isReversed para que este metodo sea O(1)*/

	public void revert(){
		if(size == 0) return;
		revertR(root);
	}

	private void revertR(Node<T> curr){
		if(curr == null) return;
		Node<T> aux = curr.next;
		curr.next = curr.prev;
		curr.prev = aux;
		revertR(curr.prev);
	}

}
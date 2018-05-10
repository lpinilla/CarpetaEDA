import java.util.*;

public class BagImpl<T extends Comparable<T>> implements Bag<T>{

	private class Node<T>{
		T elem;
		Node<T> next, prev;
		int occurrences;

		public Node(T elem){
			this.elem = elem;
			this.next = this.prev = null;
			this.occurrences = 1;
		}
	}
	
	private Comparator<T> cmp;
	private Node<T> root;

	public BagImpl(Comparator<T> cmp){
		this.cmp = cmp;
	}

	public void add(T elem){
		if(elem == null) throw new IllegalArgumentException();
		this.root = addR(this.root, elem, null);
	}

	private Node<T> addR(Node<T> curr, T elem, Node<T> prev){
		if(curr == null) {
			Node<T> nNode = new Node<T>(elem);
			nNode.prev = prev;
			return nNode;
		}
		if(curr.elem.compareTo(elem) == 0){
			curr.occurrences++;
			curr = checkOccurrences(curr);			
			return curr;	
		} 
		curr.next = addR(curr.next, elem, curr);
		return curr;
	}

	private Node<T> checkOccurrences(Node<T> curr){
		Node<T> aux = curr.prev;
		while(aux.prev != null){
			if(curr.occurrences > aux.occurrences){
				swapNodes(curr, aux);	
			}else{
				aux = aux.prev;
			}
		}
		if(aux.occurrences < curr.occurrences){ //root node
			swapNodes(aux, curr);
		}
		return aux;
	}

	/*2 opciones: cambiar el valor de los nodos o cambiar
	**los punteros entre los nodos (8 en total)
	**la segunda opción es la mejor pero es más complicada
	**y no vale la pena a menos que T sea un objeto de gran
	**tamaño*/
	private void swapNodes(Node<T> n1, Node<T> n2){
		T aux = n1.elem;
		n1.elem = n2.elem;
		n2.elem = aux;
		int aux2 = n1.occurrences; //cambiar también las repeticiones
		n1.occurrences = n2.occurrences;
		n2.occurrences = aux2;
	}


	public int occurrencesOf(T value){
		if(value == null) throw new IllegalArgumentException();
		return occurrencesOfR(this.root, value);
	}

	private int occurrencesOfR(Node<T> curr, T elem){
		if(curr == null) return 0;
		if(curr.elem.compareTo(elem) == 0) return curr.occurrences;
		return occurrencesOfR(curr.next, elem);
	}

	public T removeMostPopular(){
		T aux = this.root.elem;
		this.root = this.root.next;
		return aux;
	}
}
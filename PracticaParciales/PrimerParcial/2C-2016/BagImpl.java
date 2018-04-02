import java.util.*;

public class BagImpl<T extends Comparable<T>> implements Bag<T>{

	private class Node<T>{
		T elem;
		Node<T> next;
		int occurrences;

		public Node(T elem){
			this.elem = elem;
			this.next = null;
			this.occurrences = 0;
		}
	}
	
	private Comparator<T> cmp;
	private Node<T> root;

	public BagImpl(Comparator<T> cmp){
		this.cmp = cmp;
	}

	public void add(T elem){
		if(elem == null) throw new IllegalArgumentException();
		this.root = addR(this.root, elem);
	}

	private Node<T> addR(Node<T> curr, T elem){
		if(curr == null) return new Node<T>(elem);
		if(curr.elem.compareTo(elem) == 0){
			curr.occurrences++;
			return curr;	
		} 
		curr.next = addR(curr.next, elem);
		return curr;
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
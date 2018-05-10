import java.util.*;

public class SetAsList<T>{

	private class Node<T>{
		T value;
		Node<T> next;

		public Node(T value, Node<T> next){
			this.value = value;
			this.next = next;
		}
	}

	private Node<T> first;
	private int size;

	public SetAsList(){
		this.first = null;
		this.size = 0;
	}

	public void add(T elem){
		if(elem == null) throw new IllegalArgumentException();
		first = addR(elem, first);
	}

	public Node<T> addR(T elem, Node<T> curr){
		if(curr == null){ 
			this.size++;
			return new Node<T>(elem, null);
		}
		if(curr.value.equals(elem)) return curr;
		curr.next = addR(elem, curr.next);
		return curr;
	}

	public void clear(){
		first = null;
		size = 0;
	}

	public boolean contains(T elem){
		if(elem == null) throw new IllegalArgumentException();
		return containsR(elem, first);
	}

	private boolean containsR(T elem, Node<T> curr){
		if(curr == null) return false;
		if(curr.value.equals(elem)) return true;
		return containsR(elem, curr.next);
	}

	public boolean isEmpty(){
		return this.size == 0;
	}

	public void remove(T elem){
		Node<T> prev = null, curr = first;
		while(curr != null || !curr.value.equals(elem)){
			prev = curr;
			curr = curr.next;
		}
		if(curr == null) throw new NoSuchElementException();
		if(prev == null) first = first.next;
		prev.next = curr.next;
		this.size--;
	}

	public int size(){
		return this.size;
	}

	public Object[] toArray(){
		if(this.size == 0) return null;
		Object[] result = new Object[this.size];
		return toArrayR(result, first, 0);
	}

	private Object[] toArrayR(Object[] ret, Node<T> curr, int index){
		if(curr == null) return ret;
		ret[index] = curr.value;
		return toArrayR(ret, curr.next, ++index);
	}
}
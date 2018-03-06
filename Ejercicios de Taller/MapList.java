public class MapList<S, T>{
	
	private class Node<S, T>{
		S key;
		T value;
		Node<S, T> next;

		public Node(S key, T value, Node<S, T> next){
			this.key = key;
			this.value = value;
			this.next = next;
		}
	}

	private Node<S,T> first;

	public void add(S key, T value){
		if(value == null) throw new IllegalArgumentException();
		first = addR(key, value, first);
	}

	private Node<S, T> addR(S key, T value, Node<S, T> curr){
		if(curr == null) return new Node(key, value, null);
		if(curr.key.equals(key)){
			curr.value = value;
			return curr;
		}
		curr.next = addR(key, value, curr.next);
		return curr;
	}

	public T get(S key) throws Exception{
		Node<S,T> curr = first, prev = null;
		T ret = null;
		while(curr != null && !curr.equals(key)){
			prev = curr;
			curr = curr.next;
		}
		if(curr == null) throw new Exception();
		if(prev == null) return first.value;
		prev.next = curr.next;
		curr.next = first;
		first = curr;
		return curr.value;
	}
}
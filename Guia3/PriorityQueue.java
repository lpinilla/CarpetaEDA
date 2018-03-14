public class PriorityQueue<T>{
	
	private class PQNode<T>{
		T value;
		PQNode<T> next;
		int level;

		public PQNode(T value, PQNode<T> next, int level){
			this.value = value;
			this.next = next;
			this.level = level;
		}
	}

	PQNode<T> root;

	public PriorityQueue(){
		this.root = null;
	}

	public boolean isEmpty(){
		return this.root == null;
	}

	public T dequeue(){
		T ret = this.root.value;
		this.root = this.root.next;
		return ret;
	}

	public void enqueue(T elem, int priority){
		if(elem == null || priority <= 0) throw IllegalArgumentException();
		this.root = rEnqueue(elem, priority, this.root);
	}

	private PQNode<T> rEnqueue(T elem, int priority, PQNode<T> curr){
		if(curr == null) return new PQNode<T>(elem, null, priority);
		if(priority <= curr.level){
			return new PQNode<T>(elem, curr, priority);			
		}
		curr.next = rEnqueue(elem, priority, curr.next);
		return curr;
	}
}
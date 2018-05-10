import java.util.*;

public class ThreadedBT<T extends Comparable<T>> {
	
	private class Node<T>{
		T elem;
		Node<T> left, right;
		boolean visited;

		public Node(T elem, Node<T> left, Node<T> right){
			this.elem = elem;
			this.left = left;
			this.right = right;
			this.visited = false;
		}
	}

	private Node<T> root;
	public Comparator<T> cmp;

	public ThreadedBT(Comparator<T> cmp){
		this.cmp = cmp;
	}


	public void inOrder(){
		inOrderHelper(root);
	}

	private void inOrderHelper(Node<T> curr){
		if(curr.right == null){
			System.out.println(curr.elem);
			return;
		}
		if(curr.left != null && !curr.left.visited){
			inOrderHelper(curr.left); 
		}
		System.out.println(curr.elem);
		curr.visited = true;
		inOrderHelper(curr.right);
	}

	public void add(T elem){
		if(elem == null) throw new IllegalArgumentException();
		this.root = addR(root, elem, root, root);
	}

	private Node<T> addR(Node<T> curr, T elem, Node<T> pred, Node<T> suc){
		if(curr == null){
			return new Node<T>(elem, pred, suc); //FIXME
		}
		int comp = curr.elem.compareTo(elem);
		if(comp == 0) return curr;
		if(comp > 0){
			curr.left = addR(curr.left, elem, pred, curr);
		}else{
			curr.right = addR(curr.right, elem, curr, suc);
		}
		return curr;
	}
}
import java.util.*;

public class BinaryTree<T>{
	
	private class Node<T>{
		T value;
		Node left, right;
	}

	Node root;

	public boolean isSortedBFS(Comparator<T> cmp){
		if(root == null) return true;
		T max = null, prevMax = null;
		BinaryTree<T> aux = null;
		Dequeue<BinaryTree<T>> q = new LinkedList<BinaryTree<T>>();
		q.offer(root);
		q.offer(null);
		while(!q.isEmpty()){
			aux = q.poll();
			if(aux == null){
				q.offer(null);
				if(q.peek() == null) break; //no more nodes, could also use return true;			
				prevMax = max;				
				max = null;
			}else{			
				if(prevMax != null){
					if(cmp.compare(prevMax, aux) > 0) return false;
				}
				if(max == null || cmp.compare(aux.value, max) > 0){
					max = aux.value;
				}			
				if(aux.left != null) q.offer(aux.left);
				if(aux.right != null) q.offer(aux.right);			
			}
		}
		return true;
	}
}
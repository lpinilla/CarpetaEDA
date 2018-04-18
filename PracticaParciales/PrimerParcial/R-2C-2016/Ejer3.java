import java.util.*;

public class Ejer3{

	private class SpecialNode{
		BinaryTree tree;
		int maxValue;

		public SpecialNode(BinaryTree tree, int maxValue){
			this.tree = tree;
			this.maxValue = maxValue;
		}
	}
	
	public void BFSUpToSum(BinaryTree tree, int n){
		if(tree == null || n <= 0) throw new IllegalArgumentException();
		Deque<SpecialNode> queue = new LinkedList<SpecialNode>();
		queue.offer(new SpecialNode(tree, n));
		BinaryTree aux = null;
		while(!queue.isEmpty()){
			aux = queue.poll();
			if(aux.tree.value <= aux.maxValue){
				System.out.println(aux.tree.value);
				if(aux.tree.left != null){
					queue.offer( new SpecialNode(tree.left, aux.maxValue - aux.tree.value));
				}
				if(aux.tree.right != null){
					queue.offer( new SpecialNode(tree.right, aux.maxValue - aux.tree.value));
				}
			}
		}
	}
}
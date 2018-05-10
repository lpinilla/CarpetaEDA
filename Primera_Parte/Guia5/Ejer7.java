public class Ejer7{

	public static <T extends Comparable<T>> boolean isBalanced(BinaryTree<T> tree){	
		if(tree == null) return true;
		return BinaryTree.isBST(tree) && isBalancedR(tree);

	}

	private static <T extends Comparable<T>> boolean isBalancedR(BinaryTree<T> tree){
		if(tree == null) return true;
		int l = BinaryTree.treeHeight(tree.left),
		 	r = BinaryTree.treeHeight(tree.right);
		 if(Math.abs(r - l) <= 1 && isBalanced(tree.left)
		 	&& isBalanced(tree.right)){
		 	return true;
		 }
		 return false;
	}	

	public static void main(String[] args){
		BinaryTree<Integer> tree = new BinaryTree<Integer>(3, null, null);
		tree.left = new BinaryTree<Integer>(2, null, null);
		tree.left.left = new BinaryTree<Integer>(4, null, null);
		tree.right = new BinaryTree<Integer>(5, null, null);
		System.out.println(isBalanced(tree));
	}
}
import java.util.*;

public class BinaryTree<T> {

	private T value;
	private BinaryTree<T> left;
	private BinaryTree<T> right;

	public BinaryTree(T value) {
		this(value, null, null);
	}

	public BinaryTree(T value, BinaryTree<T> left, BinaryTree<T> right) {
		this.value = value;
		this.left = left;
		this.right = right;
	}

	public T getValue() {
		return value;
	}

	public BinaryTree<T> getLeft() {
		return left;
	}

	public BinaryTree<T> getRight() {
		return right;
	}

	public static <T> int treeHeight(BinaryTree<T> tree){
		if(tree == null) return -1;
		return 1 + Math.max(treeHeight(tree.left), treeHeight(tree.right));
	}

	public static <T> int nNodes(BinaryTree<T> tree){
		if(tree == null) return 0;
		return nNodes(tree.left) + 1 + nNodes(tree.right);
	}

	public static <T> int ocurrencesOf(BinaryTree<T> tree, T elem){
		if(tree == null) return 0;
		return ((tree.value.equals(elem))? 1 : 0 ) + 
			ocurrencesOf(tree.left, elem) + ocurrencesOf(tree.right, elem);
	}

	public static <T> int levelWidth(BinaryTree<T> tree, int level){
		if(tree == null) return 0;
		if(level == 0) return 1;
		return levelWidth(tree.left, --level) + levelWidth(tree.right, level);
	}

	public static <T extends Comparable<T>> T maxElem(BinaryTree<T> tree){
		if(tree == null) return null;
		return maxElemR(tree, tree.value); //root value
	}

	private static <T extends Comparable<T>> T maxElemR(BinaryTree<T> tree, T currentMax){
		if(tree.left == null && tree.right == null) return tree.value;
		if(tree.value.compareTo(currentMax) > 0){ //tree value is higher than current
			currentMax = tree.value;
		}
		T maxRight = null, maxLeft = null;
		if(tree.left != null){ 
			maxLeft = maxElemR(tree.left, currentMax);}

		if(tree.right != null){
			maxRight = maxElemR(tree.right, currentMax);
		}
		T max = ( maxLeft.compareTo(maxRight) > 0) ? maxLeft : maxRight;
		max = ( max.compareTo(currentMax) > 0) ? max : currentMax;
		return max;
	}


	//para que hacer tests con junit si existe el mágico printf?
	public static void main(String[] args){
		BinaryTree<Integer> bt = new BinaryTree<Integer>(1, null, null);
		bt.left = new BinaryTree<Integer>(2, null, null);
		bt.right = new BinaryTree<Integer>(3, null, null);
		bt.left.left = new BinaryTree<Integer>(4, null, null);
		bt.left.right = new BinaryTree<Integer>(5, null, null);
		bt.right.left = new BinaryTree<Integer>(5, null, null);
		bt.right.right = new BinaryTree<Integer>(7, null, null);

		System.out.println(nNodes(bt) == 7);

		System.out.println(treeHeight(bt) == 2);

		System.out.println(levelWidth(bt, 0) == 1);

		System.out.println(levelWidth(bt, 1) == 2);

		System.out.println(levelWidth(bt, 2) == 4);

		System.out.println(ocurrencesOf(bt, new Integer(3)) == 1);

		System.out.println(maxElem(bt) == 7);


	}
}
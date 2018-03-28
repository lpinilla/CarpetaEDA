public class Ejer4{
	
	public BinaryTree<Integer> arrayToBST(int[] array){
		if(array == null) return null;
		BinaryTree<Integer> tree = addNode(array, 0, array.length);	
		return 	tree;
	}

	private BinaryTree<Integer> addNode(int[] array, int i, int j){
		if(j == i) return null;
		int mid = i + (j - i) / 2;
		BinaryTree<Integer> curr = new BinaryTree<Integer>(array[mid],
			addNode(array, i, mid), addNode(array, mid + 1, j));
		return curr;
	}

	public static void main(String[] args){
		int[] array = new int[] {1, 2, 3, 4, 5};
		Ejer4 ejer = new Ejer4();
		BinaryTree<Integer> bt = ejer.arrayToBST(array);
		BinaryTree.BFSPrint(bt);
	}
}
public class Ejer2<T> {

	private BST<T> tree;
	private Comparator<T> cmp;

	public boolean isSubTree(BST<T> subTtree){		
		return isSubTHelper(tree, subTtree);
	}

	private boolean isSubTHelper(BST<T> t1, BST<T> t2){
		if(t1 == null && t2 == null) return true;
		if( t1 != null && t2 == null ||
			t1 == null && t2 != null) return false;
		int comp = t1.getValue().compareTo(t2.getValue());
		if(comp == 0){
			return isSubTHelper(t1.getLeft(), t2.getLeft()) && 
				isSubTHelper(t1.getRight(), t2.getRight());
		}else if(comp < 0){
			return isSubTHelper(t1.right, t2);
		}
		return isSubTHelper(t1.left, t2);
	}
}
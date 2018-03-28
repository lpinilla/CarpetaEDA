import java.util.*;

//Using BinaryTree implementation

public class BST<T> implements BinarySearchTree<T> {

	private int size;
	private BinaryTree<T> root;
	private Comparator<T> cmp;

	public BST(Comparator<T> cmp){
		this.size = 0;
		this.cmp = cmp;
	}

	public void add(T key){
		if(key == null) return;
		addR(root, key);
	}

	private BinaryTree<T> addR(BinaryTree<T> curr, T elem){
		if(curr == null) return new BinaryTree(elem, null, null);		
		if(curr.value == null){
			curr.value = elem;
			return curr;
		}
		int c = curr.value.compareTo(elem.value);
		if(c == 0) return curr;
		if(c < 0){
			curr.right = addR(curr.right, elem);
		}else{
			curr.left = addR(curr.left, elem);
		}
		return curr;
	}


	public boolean contains(T key){
		if(key == null) throw IllegalArgumentException();
		return containsR(root, key);
	}

	private boolean containsR(BinaryTree<T> curr, T elem){
		if(curr == null) return false;
		int comp = curr.getValue().compareTo(elem);
		if(comp == 0) return true;
		if(comp < 0) return containsR(curr.getRight(), elem);
		return containsR(curr.getLeft(), elem);
	}


	public void remove(T key){
		if(key == null) return;
		root = removeR(root, key);
	}

	private BinaryTree<T> removeR(BinaryTree<T> curr, T elem){ //ni idea si funciona
		if(curr == null) return null;
		int comp = curr.getValue().compareTo(elem);
		if(comp == 0) { //delete node
			if(curr.left == null && curr.right == null){ // no childs
				return null;
			}else if((curr.left != null && curr.right == null) || //1 child
					 (curr.left == null && curr.right != null) ){
				if(curr.left == null){
					curr.value = curr.right.value;
					curr.right = null;
				}else{
					curr.value = curr.left.value;
					curr.left = null;
				}
			}else{ //2 childs
				curr.value = findInOrderPred(curr.right, null).value;
				curr.right = removeR(curr.right, curr.value);
			}
		}else if(comp < 0){
			curr.right = removeR(curr.right, elem);
		}else{
			curr.left = removeR(curr.left, elem);
		}
		return curr;
	}

	private BinaryTree<T> findInOrderPred(BinaryTree<T> curr, BinaryTree<T> prev){
		if(curr == null) return prev;
		findInOrderPred(curr.left, curr);
	}

	
	public int size(){
		return this.size;
	}
}
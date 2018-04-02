import java.util.*;

//Using BinaryTree implementation

public class BST<T> implements BinarySearchTree<T>, Iterable<BinaryTree<T>> {

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
		this.size++;
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
		this.size--;
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

	public int heightOfValue(T value){
		if(value == null) throw new IllegalArgumentException();
		return heightOfValueR(root, value, 0);
	}

	public int heightOfValueR(BinaryTree<T> curr, T value, int aux){
		if(curr == null) return (aux + 1) * -1; //aux to return -1
		int comp = curr.value.compareTo(value);
		if(comp == 0) return 0;
		if(comp < 0) return 1 + heightOfValueR(curr.right, value, aux + 1);
		return 1 + heightOfValueR(curr.left, value, aux + 1);
	}

	public int nOfLeaves(){
		if(size == 0) return 0;
		return nOfLeavesR(root);	
	}

	private int nOfLeavesR(BinaryTree<T> curr){
		if(curr.left == null && curr.right == null) return 1;
		return nOfLeavesR(curr.left) + nOfLeavesR(curr.right);
	}

	public T biggestElem(){
		if(size == 0) return null;
		return biggestElemR(root);
	}

	private T biggestElemR(BinaryTree<T> curr){
		if(curr.left == null && curr.right == null) return curr.value;
		if(curr.right != null) return biggestElemR(curr.right);
		return curr.value;	
	}

	public void printNodeParents(BinaryTree<T> tree){
		if(tree == null) throw new IllegalArgumentException();
		printNodeParentsR(root, tree);
	}

	private void printNodeParentsR(BinaryTree<T> curr, BinaryTree<T> other){
		if(curr == null || curr.equals(other)) return;
		int comp = curr.value.compareTo(other.value);
		System.out.println(curr.value);
		if(comp > 0) return printNodeParentsR(curr.left, other);
		return printNodeParentsR(curr.right, other);
	}

	public void printNodeChildren(BinaryTree<T> tree){
		if(tree == null) throw new IllegalArgumentException();
		printNodeChildrenR(root, tree, false);
	}

	private void printNodeChildrenR(BinaryTree<T> curr, BinaryTree<T> other, boolean haveToPrint){
		if(curr == null) return;
		int comp = curr.value.compareTo(other.value);
		if(comp == 0 || haveToPrint){
			System.out.println(curr.value);
			printNodeChildrenR(curr.left, other, true);
			printNodeChildrenR(curr.right, other, true);
		}
		if(comp > 0) return printNodeParentsR(curr.left, other, false);
		return printNodeParentsR(curr.right, other, false);
	}

	public List<T> searchByRange(T min, T max){
		if(min == null || max == null) throw new IllegalArgumentException();
		List<T> ret = new LinkedList<T>();
		inOrderSearchByRange(root, min, max, ret);
		return ret;
	}

	private void inOrderSearchByRange(BinaryTree<T> curr, T min, T max, List<T> ret){
		if(curr == null) return;
		inOrderSearchByRange(curr.left, min, max, ret);
		if( curr.value.compareTo(min) >= 0 && curr.value.compareTo(max) <= 0){
			ret.add(curr.value);
		}
		inOrderSearchByRange(curr.right, min, max, ret);
	}

	private class TreePreOrderIterator<T> implements Iterator<BinaryTree<T>> {

		Stack<BinaryTree<T>> s
		BinaryTree<T> aux;

		public TreePreOrderIterator(BinaryTree<T> root){
			this.aux = null;
			this.s = new Stack<BinaryTree<T>>();
			s.push(root);
		}


		public boolean hasNext(){
			return !s.isEmpty();
		}

		public BinaryTree<T> next(){
			if(!hasNext()) throw new NoSuchElementException();
			aux = s.pop();
			if(aux.right != null) s.push(aux.right);
			if(aux.left != null) s.push(aux.left);
			return aux; 
		}
	}


	public Iterator<BinaryTree<T>> inOrderIterator(){
		TreePreOrderIterator<BinaryTree<T>> it = new TreePreOrderIterator<BinaryTree<T>>(root);
		return it;
	}

	private class TreeInOrderIterator<T> implements Iterator<BinaryTree<T>> {

		private class NodeMarker<T> {
			BinaryTree<T> tree;
			boolean visited;

			public NodeMarker(BinaryTree<T> t, boolean visited){
				this.tree = t;
				this.visited = visited;
			}
		}

		Stack<NodeMarker<T>> s;
		NodeMarker<T> aux;


		public TreeInOrderIterator(BinaryTree<T> root){
			this.aux = null;
			this.s = new Stack<NodeMarker<T>>();
			s.push(new NodeMarker(root, false));
		}


		public boolean hasNext(){
			return !s.isEmpty();
		}

		public BinaryTree<T> next(){
			if(!hasNext()) throw new NoSuchElementException();
			aux = s.peek();
			if(!aux.visited){
				aux.visited = true;
				if(aux.tree.right != null){
					s.push(new NodeMarker(aux.tree.right, false));
				}
				if(aux.tree.left != null){
					s.push(new NodeMarker(aux.tree.left, false));
				}
				if(aux.tree.left == null && aux.tree.right == null){
					return s.pop();
				}
			}else{
				return s.pop();
			}
		}
	}


	public Iterator<BinaryTree<T>> InOrderIterator(){
		TreeInOrderIterator<BinaryTree<T>> it = TreeInOrderIterator<BinaryTree<T>>(root);
		return it;
	}	

	public Iterator<BinaryTree<T>> PostOrderIterator();	
}
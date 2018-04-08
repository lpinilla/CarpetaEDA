public class Ejer1{
	
	public static <T> Node<T> treeToList(Node<T> root){ //O(n)
		Node<T> larger = root, smaller = root;
		treeToList(root, null, null); //null or root?
		while(larger.large != null){
			larger = larger.large;
		}
		while(smaller.small != null){
			smaller = smaller.small;
		}
		smaller.small = larger;
		larger.large = smaller;		
		return smaller;
	}

	private static <T> void auxTreeToList(Node<T> curr, Node<T> smaller, Node<T> larger){
		if(curr.small == null && curr.large == null){
			if(larger != null){
				larger.small = curr;
			}
			curr.large = larger;
			if(smaller != null){
				smaller.small = curr;
			}
			curr.small = smaller;
		}
		curr.small = auxTreeToList(curr.small, smaller, curr);
		curr.large = auxTreeToList(curr.large, curr, larger);
	}
}
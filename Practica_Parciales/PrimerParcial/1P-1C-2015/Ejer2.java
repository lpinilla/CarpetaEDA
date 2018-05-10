import java.util.*;

public class Ejer2{

	public static <T> Set<BinaryTree<T>> findMatches(BinaryTree<T> t1, BianryTree<T> t2){
		Set<BinaryTree<T>> ret = new TreeSet<BinaryTree<T>>();
		helperM(t1, t2, ret)
	}

	private static <T> boolean helperM(BinaryTree<T> t1, BinaryTree<T> t2, Set<BinaryTree<T>> ret){
		if(t1 == null && t2 == null) return true;
		if(t1 == null && t2 != null) return false; //o tambien al revez?
		if(t1.left != null && t1.right != null){
			if(t2.left == null || t2.right == null){h
				return helperM(t1, t2.left, ret) || helperM(t1, t2.right, ret);
			}
			return t1.value.equals(t2.value);
		}
		boolean left = false, right = false;
		left = helperM(t1.left, t2.left, ret);
		right = helperM(t1.right, t2.right, ret);
	}
	
}
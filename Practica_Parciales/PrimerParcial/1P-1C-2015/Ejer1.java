import java.util.*;

public class Ejer1{

	private static class InfoPack{
		int l, r, max;

		public InfoPack(int l, int r, int max){
			this.l = l;
			this.r = r;
			this.max = max;
		}
	}
	
	public static <T> int longestPathLength(BinaryTree<T> tree){
		if(tree == null) return 0;
		return helperM(tree).max - 1;
	}

	private static <T> InfoPack helperM(BinaryTree<T> tree){
		if(tree.left == null && tree.right == null){
			return new InfoPack(0,0, 0);
		}
		int left = 0, right = 0, maxLeft = 0, maxRight = 0, max = 0;		
		InfoPack pack = null;
		if(tree.left != null){
			pack = helperM(tree.left);
			left = 1 + Matf.max(pack.left, pack.right);
			maxLeft = pack.max;
		}
		if(tree.right != null){
			pack = helperM(tree.right);
			right = 1 + Matf.max(pack.left, pack.right);
			maxRight = pack.max;	
		}
		max = left + right + 1;
		if(max < maxLeft || max < maxRight){
			max = Math.max(maxLeft, maxRight);
		}
		return new InfoPack(left, right, max);
	}


}
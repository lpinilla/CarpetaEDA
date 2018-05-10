import java.util.*;

public class PostOrderTree{

	private class Node{
		Node left, right;
		Object o; //Integer or Expresion

		public Node(Object elem, Node left, Node right){
			this.o = elem;
			this.left = left;
			this.right = right;
		}

		public Node(Object elem){
			this(elem, null, null);
		}
	}

	private interface Operator{
		public int operate(int a, int b);
	}

	private Node root;
	private String exp;

	private boolean isOperator(char c){
		return c == '*' || c == '+' || c == '-' || c == '/';
	}

	public void BFSPrint(){
		Queue<Node> q = new LinkedList<Node>();
		q.offer(this.root);
		Node aux;
		while(!q.isEmpty()){
			aux = q.poll();
			if(aux.o instanceof Operator){
				System.out.print(aux.o + " ");					
			}else{
				System.out.print(aux.o + " ");				
			}
			if(aux.left != null){
				q.offer(aux.left);
			}
			if(aux.right != null){
				q.offer(aux.right);
			}			
		}
		System.out.println();
	}

	private Operator createOperator(char c){
		Operator o = null;
		switch(c){
			case '+':
				o = (int a, int b)->a+b;
				break;
			case '-':
				o = (int a, int b)->a-b;
				break;
			case '*':
				o = (int a, int b)->a*b;
				break;
			case '/':
				o = (int a, int b)->a/b;
				break;
		}
		return o;
	}

	public PostOrderTree(String exp){
		if(exp == null || Character.isDigit(exp.charAt(exp.length - 1))) throw new IllegalArgumentException();
		for(char c : exp.toCharArray()){
			if(!isOperator(c) && !Character.isDigit(c)) throw new IllegalArgumentException();
		}
		this.exp = exp;
		this.root = null;
	}


	public void expToTree(){
		Integer idx = new Integer(0);
		this.root = addNodes(root, exp.toCharArray(), idx);
	}

	private Node addNodes(Node curr, char[] exp, Integer index){
		if(index >= exp.length) return null;
		curr = new Node(null);
		curr.left = addNodes(curr.left, exp, ++index);
		curr.right = addNodes(curr.right, exp, ++index);
		if(Character.isDigit(exp[index])){
			curr.o = new Node(exp[index]);
		}else{
			curr.o = new Node(createOperator(exp[++index]));
		}
		return curr;
	}


	public static void main(String[] args){
		if(args.length == 0) throw new IllegalArgumentException("Expression is null");
		String exp = args[0];
		PreOrderTree t = new PreOrderTree(exp);
		t.expToTree();
		//t.BFSPrint(); //to test
		System.out.println(t.evaluateExpr());
	}

}
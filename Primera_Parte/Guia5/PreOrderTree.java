import java.util.*;

public class PreOrderTree{
	
	/*clase para cada nodo del árbol
	**para lo que guarda el nodo puedo usar
	**Object y tener cuidado o tener 1 instancia
	**de Integer y otra de "Operator" y manejarme
	**con cuidado, la segunda opción ocupa más espacio
	**en memoria por nodo*/

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

	public PreOrderTree(String exp){
		if(exp == null || Character.isDigit(exp.charAt(0))) throw new IllegalArgumentException();
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
		if(isOperator(exp[index])){			
			curr = new Node(createOperator(exp[index]));
			curr.left = addNodes(curr.left, exp, ++index);
			curr.right = addNodes(curr.right, exp, ++index);
		}else{
			curr = new Node(exp[index]);
		}
		return curr;
	}

	private boolean isOperator(char c){
		return c == '*' || c == '+' || c == '-' || c == '/';
	}

	public int evaluateExpr(){
		return evaluate(this.root);
	}

	private int evaluate(Node curr){
		if(curr == null) return 0;
		if(curr.o instanceof Operator){			
			Operator op = (Operator) curr.o;
			return op.operate(evaluate(curr.left), evaluate(curr.right));
		}
		return new Integer((Character) curr.o - '0');
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

	public static void main(String[] args){
		if(args.length == 0) throw new IllegalArgumentException("Expresion is null");
		String exp = args[0];
		PreOrderTree t = new PreOrderTree(exp);
		t.expToTree();
		//t.BFSPrint(); //to test
		System.out.println(t.evaluateExpr());
	}
}
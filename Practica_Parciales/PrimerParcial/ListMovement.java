public class ListMovement{
	
	private class Node{
		int data;
		Node next;

		public Node(int n){
			this.data = n;
			this.next = null;
		}
	}

	Node root;

	public ListMovement(){
		root = null;
	}

	public void add(int n){
		if(root == null){
			root = new Node(n);			
		}else{
			Node nNode = new Node(n);
			nNode.next = root;
			root = nNode;
		}
	}

	public Node getMediumNode(){
		Node aux1 = root, aux2 = root.next;
		while(aux2 != null){
			aux2 = aux2.next;
			if(aux2 != null){
				aux1 = aux1.next;
				aux2 = aux2.next;
			}
		}
		return aux1;
	}

	public Node getOneThirdNode(){
		Node aux1 = root, aux2 = root.next;
		if(aux2 != null) aux2 = aux2.next;
		while(aux2 != null){
			aux2 = aux2.next;
			if(aux2 != null){
				aux1 = aux1.next;
				aux2 = aux2.next;
			}
		}
		return aux1;	
	}

	public static void main(String args[]){
		ListMovement l = new ListMovement();
		for(int i = 0; i < 12; i++){
			l.add(i);
		}
		System.out.println("1/2 " + l.getMediumNode().data);
		System.out.println("1/3 " + l.getOneThirdNode().data);
	}
}
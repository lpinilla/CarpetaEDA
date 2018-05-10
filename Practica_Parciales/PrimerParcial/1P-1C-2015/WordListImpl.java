public class WordListImpl implements WordList{
	
	private class Node{
		String word;
		Node next, nextToPrint;		

		public Node(String word){
			this.word = word;
			this.next = null;
			this.nextToPrint = null;
		}
	}

	private class List{
		Node root, last;

		public List(){
			root = last = null;
		}

		public Node add(String word){
			if(root == null){
				root = new Node(word);
				last = root;
				return last;
			}
			last.next = new Node(word);
			last = last.next;
			return last;
		}
	}

	List odd, even;
	Node firstAdded, lastAdded;

	public WordListImpl(){
		odd = new List();
		even = new List();
		lastAdded = firstAdded = null;
	}

	public void removeEven(){
		this.even = null;
	}

	public void removeOdd(){
		this.odd = null;
	}

	public void add(String word){
		if(word == null) return;
		Node aux;
		if(word.lenght % 2 == 0){
			aux = even.add(word);
		}else{
			aux = odd.add(word);
		}
		if(firstAdded == null){
			firstAdded = aux;
		}
		if(lastAdded == null){
			lastAdded = aux;
		}else{
			lastAdded.nextToPrint = aux;
			lastAdded = aux;
		}
	}

	public void print(){
		if(odd == null && even == null) return;
		if(odd == null && even != null){
			printLinearList(even);
		}else if(odd != null && even == null){
			printLinearList(odd);
		}else{
			Node aux = firstAdded;
			while(aux != null){
				System.out.println(aux.word);
				aux = aux.nextToPrint();
			}
		}
	}

	private void printLinearList(List l){
		Node aux = l.root;
		while(aux != null){
			System.out.println(aux.word);
			aux = aux.next;
		}
	}



}
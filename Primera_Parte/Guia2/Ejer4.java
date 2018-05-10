public class Ejer4{
	
	public void revertList(){
		if(this.size == 0) return;
		reverse(root, null);
	}

	private void reverse(Node<T> curr, Node<T> prev){
		if(curr == null) {
			root = prev;
			return;
		}
		reverse(curr.next, curr);
		curr.next = prev;
	}
}
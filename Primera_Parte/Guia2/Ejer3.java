public class Ejer3{
	
	//variable arguments: union(List<T> ... lists)
	public void union(Collection<List<T>> lists){
		List<T> ret = new List<T>();
		Node<T> aux = null;
		for(List<T> l : lists){
			aux = l.root;
			for(int i = 0; i < l.size; i++){
				ret.add(aux);
				aux = aux.next;
			}
		}	
	}
}
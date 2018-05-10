public class Ejer7<T>{

	private Stack<T> s1, s2;

	public Ejer7(){
		s1 = new Stack<T>();
		s2 = new Stack<T>();
	}
	

	public void enqueue(T elem){
		s1.push(elem);
	}

	public T dequeue(){
		while(!s1.isEmpty()){
			s2.push(s1.pop());
		}
		T ret = s2.pop();
		while(!s2.isEmpty()){
			s1.push(s2.pop());
		}
		return ret;
	}
}
public class Ejer2{

	public int count(Condition cond){
		if(cond == null) throw new IllegalArgumentException();
		return countR(cond, root);
	}

	private int countR(Condition cond, Node<T> curr){
		if(curr == null) return 0;
		return countR(cond, curr.next) + (cond(curr.value)? 1: 0);
	}

	public List<T> filter(Condition cond){
		if(cond == null) throw new IllegalArgumentException();
		List<T> ret = new List<T>(); //enviar comparator si lo necesita
		filterR(root, ret, cond);
		return ret;
	}

	private void filterR(Node<T> curr, List<T> ret, Condition cond){
		if(curr == null) return;
		if(cond(curr)) ret.add(curr);
		filterR(curr.next, ret, cond);
	}

	public List<S> map(Function<T, S> f){
		if(f == null) throw new IllegalArgumentException();
		List<S> ret = new List<S>();
		mapR(f, ret, root);
		return ret;
	}

	private void mapR(Function<T,S> f, List<S> ret, Node<T> curr){
		if(curr == null) return;
		ret.add(f(curr.value));
		mapR(f, ret, curr.next);
	}


	public S inject(Function<S, T> f, S initialArg){
		if(f == null || initialArg == null) throw new IllegalArgumentException();
		return injectR(root, f, initialArg);
	}

	private S injectR(Node<T> curr, Function<S, T> f, S prevVal){
		if(curr == null) return f(prevVal, curr.value);
		return injectR(curr.next, f, f(prevVal, curr.value));
	}

}
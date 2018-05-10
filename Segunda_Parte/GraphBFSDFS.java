public class GraphBFSDFS{

	private interface Function<T>{

		public T apply(T value);
	}
	
	public void injectDFS(V origin, Function f){
		Node root = nodes.get(origin);
		if(root == null) return;
		clearMarks();
		DFShelper(root, f);
	}

	private void DFShelper(Node curr, Function f){		
		if(!curr.visited){
			curr.visited = true;
			curr.info = f.apply(curr.info);
			for(Arc a : curr.adj){
				helper(curr, f);
			}
		}
	}

	public void injectBFS(V origin, Function f){
		Node root = nodes.get(origin), aux = null;
		if(root == null) return;
		clearMarks();
		Queue<Node> q = new LinkedList<Node>();
		root.visited = true;
		q.offer(root);
		while(!q.isEmpty()){
			aux = q.poll();
			aux.info = f.apply(aux.info);
			for(Arc a : aux.adj){
				if(!a.neighbor.visited){					
					a.neighbor.visited = true;
					q.offer(a.neighbor);
				}
			}
		}
	}
}
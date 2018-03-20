public class CountingSort{

	private int[] range;
	
	public CountingSort(int lowerIndex, int topIndex){
		range = new int[] { lowerIndex, topIndex};
	}	

	public int[] sort(int[] array){
		int[] index = new int[range[1] - range[0]], ret = new int[array.length];
		for(int i = 0; i < array.length; i++){ //set index array
			index[array[i]]++;
		} 
		for(int i = 1; i < index.length; i++){ //add previous counts to index
			index[i] += index[i-1];
		}
		for(int i = 0; i < array.length; i++){
			ret[index[array[i]] - 1] = array[i];
			index[array[i]]--;
		}
		return ret;
	}

	private void printArray(int[] array){
		for(int i = 0; i < array.length; i++){
			System.out.print(array[i]);
		}
		System.out.println();
	}

	public static void main(String[] args){
		CountingSort cs = new CountingSort(0, 9);
		int[] array = new int[] {1, 4, 1, 2, 7, 5, 2};
		array = cs.sort(array);
		cs.printArray(array);
	}
}
public class Sorting{
	

	public static void main(String[] args){
		int[] array = new int[] { 2 , 1 ,0 ,5 ,6 ,7 ,5, 3, 1 ,9 ,0};
		selectionSort(array);
		printArray(array);
	}

	public static void selectionSort(int[] array){
		int min = 0, aux = 0;
		for(int i = 0; i < array.length; i++){
			min = i;
			for(int j = i + 1; j < array.length; j++){
				if(array[j] <= array[min]){
					min = j;
				}
			}
			aux = array[i];
			array[i] = array[min];
			array[min] = aux;
		}
	}

	private static void printArray(int[] array){
		for(int i = 0; i < array.length; i++){
			System.out.print(array[i]);
		}
		System.out.println();
	}
}
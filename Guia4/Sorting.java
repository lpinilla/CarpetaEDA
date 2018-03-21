public class Sorting{
	

	public static void main(String[] args){
		int[] array = new int[] { 2 , 1 ,0 ,5 ,6 ,7 ,5, 3, 1 ,9 ,0};
		//selectionSort(array);
		//insertionSort(array);
		bubbleSort(array);
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

	public static void insertionSort(int[] array){
		int aux;
		for(int i = 0; i < array.length; i++){
			for(int j = i; j > 0; j--){
				if(array[j] < array[j-1]){
					aux = array[j];
					array[j] = array[j-1];
					array[j-1] = aux;
				}
			}
		}
	}

	public static void bubbleSort(int[] array){
		int aux;
		for(int i = 0; i < array.length; i++){
			for(int j= i+1; j < array.length; j++){
				if(array[i] > array[j]){
					aux = array[i];
					array[i] = array[j];
					array[j] = aux;
				}
			}
		}
	}

	private static void printArray(int[] array){
		for(int i = 0; i < array.length; i++){
			System.out.print(array[i]);
		}
		System.out.println();
	}
}
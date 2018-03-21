import java.util.*;

public class Sorting{
	

	public static void main(String[] args){
		int[] array = new int[] { 2 , 1 ,0 ,5 ,6 ,7 ,5, 3, 1 ,9 ,0};
		//selectionSort(array);
		//insertionSort(array);
		//bubbleSort(array);
		//mergeSort(array);
		quickSort(array);
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

	public static void mergeSort(int[] array){
		int[] aux = new int[array.length];
		for(int i : array){
			aux[i] = array[i];
		}
		mSort(array, aux,  0, array.length - 1);
	}


	private static void mSort(int[] array, int[] aux, int from, int to){
		int size = to - from + 1;
		if(size <= 1) return;
		int n = from + size/ 2;
		mSort(array, aux, from, n - 1);
		mSort(array, aux, n, to);
		int i1 = from, i2 = n, i = from;
		while( i1 <= n -1 || i2 <= to){
			if( i2 > to || (i1 <= n-1 && array[i1] < array[i2])){
				aux[i++] = array[i1++];
			}else{
				aux[i++] = array[i2++];
			}
		}
		for(int k = from; k <= to; k++){
			array[k] = aux[k];
		}
	}

	public static void quickSort(int[] array){
		qSort(array, 0, array.length -1);
	}

	private static void qSort(int[] array, int from, int to){
		int size = to - from + 1;
		if( size <= 2) return;
		int k = from, pivot = (int) (Math.random() * size) + from; 
		swap(array, pivot, from);
		for(int i = from + 1; i <= to; i++){
			if(array[i] < array[from]){
				swap(array, ++k, i);
			}
		}
		swap(array, k, from);
		qSort(array, from, k - 1);
		qSort(array, k + 1, to);
	}

	private static void swap(int[] array, int i, int j){
		int aux = array[i];
		array[i] = array[j];
		array[j] = aux;
	}

	private static void printArray(int[] array){
		for(int i = 0; i < array.length; i++){
			System.out.print(array[i]);
		}
		System.out.println();
	}
}
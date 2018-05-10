public class Ejer14{
	
	private static boolean[][] matrix;
	private static int dim = 10;

	//White = true, Black = false

	public static void main(String[] args){
		matrix = new boolean[dim][dim];
		initialPaint(matrix);
		printMatrix(matrix);
		paint(3, 4, true);
		printMatrix(matrix);
		paint(3,4, false);
		printMatrix(matrix);
	}

	private static void paint(int x, int y, boolean color){
		if((x < 0 || x >= dim || y < 0 || y >= dim) || matrix[x][y] == color) return;
		matrix[x][y] = color;
		paint(x + 1, y, color);
		paint(x - 1, y, color);
		paint(x, y + 1, color);
		paint(x, y - 1, color);
	}

	private static void initialPaint(boolean[][] m){
		for(int i = 0; i < dim; i++){
			for(int j = 0; j < dim; j++){
				if(i == 2 || i == dim - 3 || j == 2 || j == dim - 3){
					m[i][j] = true;
				}
			}
		}
	}

	private static void printMatrix(boolean[][] m){
		System.out.println("--------------------");
		char c;
		for(int i = 0; i < dim; i++){
			for(int j = 0; j < dim; j++){
				if(m[i][j]){
					c = '*';
				}else{
					c = '°';
				}
				System.out.print(c + " ");
			}
			System.out.println();
		}	
	}
}
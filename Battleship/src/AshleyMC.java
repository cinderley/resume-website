
public class AshleyMC {

	public static void main(String[] args) {
		char[][] guesses = new char[10][10];
		for(int row = 0; row < 10; row ++) {
			for(int col = 0; col < 10; col ++) {
				guesses[row][col] = '.';
			}
		}
		makeGuess(guesses);		
	}
	public static String makeGuess(char[][] guesses) {

		int[][] map = new int[10][10];
		int[][] random = new int[10][10];
		int[][] random2 = new int[10][10];
		int[][] random3 = new int[10][10];
		int[][] random4 = new int[10][10];
		int[][] random5 = new int[10][10];
		int patrolBoat = 2;
		int submarine = 3;
		int destroyer = 3;
		int battleship = 4;
		int acCarrier = 5;
		char pBoat = '1';
		char sub = '2';
		char dest = '3';
		char bShip = '4';
		char acCar = '5';
		int highestProbRow = 0;
		int highestProbCol = 0;
		int highestProb = map[highestProbRow][highestProbCol];


		for(int i = 0; i < 5; i ++) {
			randomBoard(random, guesses);
			for(int row = 0; row < 10; row ++) {
				for(int col = 0; col < 10; col ++) {
					if(random[row][col] >= 1) {
						map[row][col] ++;
					}
				}
			}
			System.out.println("This is where the boats are");
			for(int row = 0; row < 10; row ++) {
				for(int col = 0; col < 10; col ++) {
					System.out.print(map[row][col] + " ");
				}
				System.out.println();
			}
			System.out.println();
		}		

		// Choose a spot to guess
		for(int row = map.length - 1; row >= 0; row --) {
			for(int col = map.length - 1; col >= 0; col --) {
				if(map[row][col] > highestProb /*&& guesses[row][col] == '.'*/) {
					highestProb = map[row][col];
					highestProbRow = row;
					highestProbCol = col;
					System.out.println(highestProbRow);
					System.out.println(highestProbCol);
				} 
			}
		}

		char a = (char)((int)'A' + highestProbRow);
		String guess = a + Integer.toString(highestProbCol+1);
		//		for(int row = 0; row < 10; row ++) {
		//			for(int col = 0; col < 10; col ++) {
		//				System.out.print(guesses[row][col]);
		//			}
		//			System.out.println();
		//		}
		System.out.println(guess);
		return guess;
	}

	public static boolean shipSunk(char[][] guesses, int[][]map, char shipType, int shipSize) {
		int shipHit = 0;
		boolean shipSunk = false;
		for(int row = 0; row < guesses.length; row ++) {
			for(int col = 0; col < guesses[row].length; col ++) {
				if(guesses[row][col] == shipType) {
					shipHit ++;
					map[row][col] = 1;
				}
			}
		}
		if(shipHit == shipSize) {
			shipSunk = true;
		}
		return shipSunk;
	}

	public static boolean shipFitsX(char[][] guesses, int[][] map, int shipSize, int row, int col) {
		boolean fits = false;
		int spotCount = 0;
		for(int i = 0; i < shipSize - 1 && col < guesses.length - shipSize + 1; i ++) {
			if(isOpen(guesses, row, col + i) && isOpen(guesses, row, col + i + 1)) {
				spotCount ++;
			} 
		} 
		fits = spotCount == shipSize - 1?true:false;
		return fits;
	}

	public static boolean shipFitsY(char[][] guesses, int[][] map, int shipSize, int row, int col) {
		boolean fits = false;
		int spotCount = 0;
		for(int i = 0; i < shipSize - 1 && row < guesses.length - shipSize + 1; i ++) {
			if(isOpen(guesses, row + i, col) && isOpen(guesses, row + i + 1, col)) {
				spotCount ++;
			} 
		}
		fits = spotCount == shipSize - 1?true:false;
		return fits;
	}

	public static boolean isOpen(char[][] guesses, int r, int c) {
		return guesses[r][c] == '.' || guesses[r][c] == 'X';
	}


	private static void shuffleArray(int[] array)
	{
		System.out.println("Begin Shuffle array");
		int index, temp;

		for (int i = array.length - 1; i > 0; i--)
		{
			index = (int)(Math.random()*(i + 1));
			temp = array[index];
			array[index] = array[i];
			array[i] = temp;
		}
		System.out.println("End Shuffle array");
	}

	
	static int counter = 0;
	public static void randomBoard(int[][] gameBoard, char[][] guesses){
		System.out.println("Current Iteration " + ++counter);
		int[] ships = new int [5];
		int[] shipIndex = {0,1,2,3,4};
		ships[0] = 2;
		ships[1] = 3;
		ships[2] = 3;
		ships[3] = 4;
		ships[4] = 5;

		shuffleArray(shipIndex);
		for(int temp = 0; temp < ships.length; temp ++) {
			System.out.println("Inside temp loop");
			int ship = shipIndex[temp];
			boolean valid = false;
			int orient = (int) (Math.random() * 2);
			int x, y;
//			for(int row = 0; row < 10; row ++) {
//				for(int col = 0; col < 10; col ++) {
//					if(guesses[row][col] != '.') {
//						gameBoard[row][col] = 1;
//					}
//				}
//			}
			if(orient == 0) {// horizontal
				//System.out.println("Horizontal");
				System.out.println("before horiz while");
				while(!valid) {
					System.out.println("inside horizontal loop");

					//x = (int)(Math.random()* (gameBoard.length - ships[ship] -1)) +1;
					//y = (int)(Math.random()* (gameBoard.length -2)) +1 ;
					x = (int)(Math.random()* (gameBoard.length - ships[ship] + 1));
					y = (int)(Math.random()* (gameBoard.length));
					int check = 0;
					for(int i = 0; i<ships[ship]; i ++){
						check += gameBoard[x+i][y];
						System.out.println("value of check is " + check);
						System.out.println("value of valid is " + valid);
					}
					if(check == 0){
						for(int i = 0; i<ships[ship]; i ++){
							gameBoard[x + i][y] = ship + 1;
						}
						valid = true;
					} 
				}
			}else{ // vertical
				//System.out.println("Vertical");
				System.out.println("before vert while");
				while(!valid){
					System.out.println("inside vertical loop");

					x = (int)(Math.random()* (gameBoard.length));
					y = (int)(Math.random()* (gameBoard.length - ships[ship] + 1));
					//x = (int)(Math.random()* (gameBoard.length - 2 ))+1;
					//y = (int)(Math.random()* (gameBoard.length - ships[ship] -1))+1;
					int check = 0;
					for(int i = 0; i < ships[ship]; i ++){
						check += gameBoard[x][y +i ];
						System.out.println("value of check inside is " + check);
						System.out.println("value of valid is " + valid);
						System.out.println("value of spot in gameboard is " + gameBoard[x][y + i]);
					}
					System.out.println("value of check outside is " + check);
					if(check == 0){
						for(int i = 0; i < ships[ship]; i ++){
							gameBoard[x][y + i] = ship + 1;
						}
						valid = true;
					} 
				}
			}
		}
		System.out.println("End Random Board");
	}
}



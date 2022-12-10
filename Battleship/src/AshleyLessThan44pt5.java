
public class AshleyLessThan44pt5 {

	public static String makeGuess(char[][] guesses) {

		int[][] map = new int[10][10];
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
		int highestProb = map[0][0];
		int highestProbRow = 0;
		int highestProbCol = 0;


		// Create Heat Map
		for(int row = 0; row < map.length; row ++) {
			for(int col = 0; col < map[row].length - 1; col ++) {
				if(shipFitsX(guesses, map, patrolBoat, row, col) == true && shipSunk(guesses, pBoat, patrolBoat) == false) {
					increaseX(map, row, col, patrolBoat);
				}
				if(shipFitsX(guesses, map, submarine, row, col) == true && shipSunk(guesses, sub, submarine) == false) {
					increaseX(map, row, col, submarine);
				}
				if(shipFitsX(guesses, map, destroyer, row, col) == true && shipSunk(guesses, dest, destroyer) == false) {
					increaseX(map, row, col, destroyer);
				}
				if(shipFitsX(guesses, map, battleship, row, col) == true && shipSunk(guesses, bShip, battleship) == false) {
					increaseX(map, row, col, battleship);
				}
				if(shipFitsX(guesses, map, acCarrier, row, col) == true && shipSunk(guesses, acCar, acCarrier) == false) {
					increaseX(map, row, col, acCarrier);
				}
			}
		}

		for(int row = 0; row < map.length - 1; row ++) {
			for(int col = 0; col < map[row].length; col ++) {
				if(shipFitsY(guesses, map, patrolBoat, row, col) == true && shipSunk(guesses, pBoat, patrolBoat) == false) {
					increaseY(map, row, col, patrolBoat);						
				}
				if(shipFitsY(guesses, map, submarine, row, col) == true && shipSunk(guesses, sub, submarine) == false) {
					increaseY(map, row, col, submarine);						
				}
				if(shipFitsY(guesses, map, destroyer, row, col) == true && shipSunk(guesses, dest, destroyer) == false) {
					increaseY(map, row, col, destroyer);						
				}
				if(shipFitsY(guesses, map, battleship, row, col) == true && shipSunk(guesses, bShip, battleship) == false) {
					increaseY(map, row, col, battleship);						
				}
				if(shipFitsY(guesses, map, acCarrier, row, col) == true && shipSunk(guesses, acCar, acCarrier) == false) {
					increaseY(map, row, col, acCarrier);						
				}
			}
		}


		// update Heat Map based on hits and misses
		for(int row = 0; row < guesses.length; row ++) {
			for(int col = 0; col < guesses.length; col ++) {
				if(guesses[row][col] == 'X') {
					hit(guesses, map, row, col);
				} else if(guesses[row][col] == 'O') {
					miss(map, row, col);
				}
			}
		}

		// Choose a spot to guess
		for(int row = 0; row < map.length; row ++) {
			for(int col = 0; col < map[row].length; col ++) {
				if(map[row][col] > highestProb && guesses[row][col] == '.') {
					highestProb = map[row][col];
					highestProbRow = row;
					highestProbCol = col;
				} 
			}
		}
		
		// Make guess
		char a = (char)((int)'A' + highestProbRow);
		String guess = a + Integer.toString(highestProbCol+1);
		return guess;
	}

	public static boolean shipFitsX(char[][] guesses, int[][] map, int shipSize, int row, int col) {
		boolean fits = false;
		int spotCount = 0;
		for(int i = 0; i < shipSize - 1 && col < guesses.length - shipSize + 1; i ++) {
			if(guesses[row][col + i] == '.' && guesses[row][col + i + 1] == '.') {
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
			if(guesses[row + i][col] == '.' && guesses[row + i + 1][col] == '.') {
				spotCount ++;
			} 
		}
		fits = spotCount == shipSize - 1?true:false;
		return fits;
	}

	public static void increaseX(int[][] map, int row, int col, int shipSize) {
		for(int s = 0; s < shipSize; s ++) {
			map[row][col + s] ++;
		}						
	}

	public static void increaseY(int[][] map, int row, int col, int shipSize) {
		for(int s = 0; s < shipSize; s ++) {
			map[row + s][col] ++;
		}						
	}
	
	public static boolean shipSunk(char[][] guesses, char shipType, int shipSize) {
		int shipHit = 0;
		boolean shipSunk = false;
		for(int row = 0; row < guesses.length; row ++) {
			for(int col = 0; col < guesses[row].length; col ++) {
				if(guesses[row][col] == shipType) {
					shipHit ++;
				}
			}
		}
		if(shipHit == shipSize) {
			shipSunk = true;
		}
		return shipSunk;
	}

	public static void hit(char[][] guesses, int[][] map, int row, int col) {
		map[row][col] = 0;
		if(row < guesses.length - 1 && guesses[row + 1][col] == '.') {
			map[row + 1][col] += 100;
		} else if(row > 0 && row < guesses.length - 1 && guesses[row + 1][col] == 'X') {
			map[row - 1][col] += 200;
			if(row < guesses.length - 2 && guesses[row + 2][col] == '.') {
				map[row + 2][col] += 200;
			}
		}
		if(row > 0 && guesses[row - 1][col] == '.') {
			map[row - 1][col] += 100;
		} else if(row < guesses.length - 1 && row > 0 && guesses[row - 1][col] == 'X') {
			map[row + 1][col] += 200;
			if(row > 1 && guesses[row - 2][col] == '.') {
				map[row - 2][col] += 200;
			}
		}
		if(col < guesses.length - 1 && guesses[row][col + 1] == '.') {
			map[row][col + 1] += 100;
		} else if(col > 0 && col < guesses.length - 1 && guesses[row][col + 1] == 'X') {
			map[row][col - 1] += 200;
			if(col < guesses.length - 2 && guesses[row][col + 2] == '.') {
				map[row][col + 2] += 200;
			}
		}
		if(col > 0 && guesses[row][col - 1] == '.') {
			map[row][col - 1] += 100;
		} else if(col < guesses.length - 1 && col > 0 && guesses[row][col - 1] == 'X') {
			map[row][col + 1] += 200;
			if(col > 1 && guesses[row][col - 2] == '.') {
				map[row][col - 2] += 200;
			}
		}
		//map[row][col] = 0;
	}

	public static void miss(int[][] map, int row, int col) {
		map[row][col] = 0;
	}
}


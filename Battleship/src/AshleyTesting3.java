
public class AshleyTesting3 {

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
		int highestProbRow = 0;
		int highestProbCol = 0;
		int highestProb = map[highestProbRow][highestProbCol];

		// Create Heat Map
		for(int row = 0; row < map.length; row ++) {
			for(int col = 0; col < map[row].length - 1; col ++) {
				if(shipFitsX(guesses, map, patrolBoat, row, col) == true && shipSunk(guesses, map, pBoat, patrolBoat) == false) {
					increaseX(map, row, col, patrolBoat);
				}
				if(shipFitsX(guesses, map, submarine, row, col) == true && shipSunk(guesses, map, sub, submarine) == false) {
					increaseX(map, row, col, submarine);
				}
				if(shipFitsX(guesses, map, destroyer, row, col) == true && shipSunk(guesses, map, dest, destroyer) == false) {
					increaseX(map, row, col, destroyer);
				}
				if(shipFitsX(guesses, map, battleship, row, col) == true && shipSunk(guesses, map, bShip, battleship) == false) {
					increaseX(map, row, col, battleship);
				}
				if(shipFitsX(guesses, map, acCarrier, row, col) == true && shipSunk(guesses, map, acCar, acCarrier) == false) {
					increaseX(map, row, col, acCarrier);
				}
			}
		}

		for(int row = 0; row < map.length - 1; row ++) {
			for(int col = 0; col < map[row].length; col ++) {
				if(shipFitsY(guesses, map, patrolBoat, row, col) == true && shipSunk(guesses, map, pBoat, patrolBoat) == false) {
					increaseY(map, row, col, patrolBoat);
				}
				if(shipFitsY(guesses, map, submarine, row, col) == true && shipSunk(guesses, map, sub, submarine) == false) {
					increaseY(map, row, col, submarine);						
				}
				if(shipFitsY(guesses, map, destroyer, row, col) == true && shipSunk(guesses, map, dest, destroyer) == false) {
					increaseY(map, row, col, destroyer);
				}
				if(shipFitsY(guesses, map, battleship, row, col) == true && shipSunk(guesses, map, bShip, battleship) == false) {
					increaseY(map, row, col, battleship);
				}
				if(shipFitsY(guesses, map, acCarrier, row, col) == true && shipSunk(guesses, map, acCar, acCarrier) == false) {
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
		for(int row = map.length - 1; row >= 0; row --) {
			for(int col = map.length - 1; col >= 0; col --) {
				if(map[row][col] >= highestProb && guesses[row][col] == '.') {
					highestProb = map[row][col];
					highestProbRow = row;
					highestProbCol = col;
				} 
			}
		}
		
		char a = (char)((int)'A' + highestProbRow);
		String guess = a + Integer.toString(highestProbCol+1);
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

	public static void increaseX(int[][] map, int row, int col, int shipSize) {
		for(int s = 0; s < shipSize; s ++) {
			map[row][col + s] += Math.pow((Math.abs(shipSize - 10)),2);
		}
	}

	public static void increaseY(int[][] map, int row, int col, int shipSize) {
		for(int s = 0; s < shipSize; s ++) {
			map[row + s][col] += Math.pow((Math.abs(shipSize - 10)),2);
		}
	}						

	public static void hit(char[][] guesses, int[][] map, int row, int col) {
		int oneX = 50;
		int twoX = 100;
		map[row][col] = 0;
		if(row < guesses.length - 1 && guesses[row + 1][col] == '.') {
			map[row + 1][col] *= oneX;
		} else if(row > 0 && row < guesses.length - 1 && guesses[row + 1][col] == 'X') {
			map[row - 1][col] *= twoX;
			if(row < guesses.length - 2 && guesses[row + 2][col] == '.') {
				map[row + 2][col] *= twoX;
			}
		}
		if(row > 0 && guesses[row - 1][col] == '.') {
			map[row - 1][col] *= oneX;
		} else if(row < guesses.length - 1 && row > 0 && guesses[row - 1][col] == 'X') {
			map[row + 1][col] *= twoX;
			if(row > 1 && guesses[row - 2][col] == '.') {
				map[row - 2][col] *= twoX;
			}
		}
		if(col < guesses.length - 1 && guesses[row][col + 1] == '.') {
			map[row][col + 1] *= oneX;
		} else if(col > 0 && col < guesses.length - 1 && guesses[row][col + 1] == 'X') {
			map[row][col - 1] *= twoX;
			if(col < guesses.length - 2 && guesses[row][col + 2] == '.') {
				map[row][col + 2] *= twoX;
			}
		}
		if(col > 0 && guesses[row][col - 1] == '.') {
			map[row][col - 1] *= oneX;
		} else if(col < guesses.length - 1 && col > 0 && guesses[row][col - 1] == 'X') {
			map[row][col + 1] *= twoX;
			if(col > 1 && guesses[row][col - 2] == '.') {
				map[row][col - 2] *= twoX;
			}
		}
	}

	public static void miss(int[][] map, int row, int col) {
		map[row][col] = 0;
	}

	public static boolean isOpen(char[][] guesses, int r, int c) {
		return guesses[r][c] == '.' || guesses[r][c] == 'X';
	}
}



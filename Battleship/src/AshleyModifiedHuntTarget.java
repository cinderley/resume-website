
public class AshleyModifiedHuntTarget {

	public static void main(String[] args) {
		char[][] guesses = new char[10][10];

		for(int row = 0; row < guesses.length; row ++) {
			for(int col = 0; col < guesses[row].length; col ++) {
				guesses[row][col] = '_';
			}
		}

		//test guesses here:
		guesses[5][5] = 'x';
		guesses[6][5] = 'x';

		System.out.printf("%24s%n", "ENEMY");
		System.out.print(" ");
		for(int i = 0; i < guesses.length; i ++) {
			System.out.printf("%4s", i + 1);
		}
		System.out.println();
		System.out.print("   ");

		for(int i = 0; i < guesses.length; i ++) {
			System.out.print("___");
		}
		System.out.println("_________");

		for(int row = 0; row < guesses.length; row ++) {
			for(int col = 0; col < guesses[row].length; col ++) {
				if(row >= 0 && col == 0) {
					char letter = (char)(65 + row);
					System.out.print(letter + " |_" + guesses[row][col] + "_|");
				} else {
					System.out.print("_" + guesses[row][col] + "_|");
				}
			}
			System.out.println();
		}
		for(int i = 0; i < 100; i ++) {
			makeGuess(guesses);
		}
	}

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

		//Print guesses board
		/*for(int row = 0; row < guesses.length; row ++) {
			for(int col = 0; col < guesses[row].length; col ++) {
				if(row >= 0 && col == 0) {
					char letter = (char)(65 + row);
					System.out.print(letter + " |_" + guesses[row][col] + "_|");
				} else {
					System.out.print("_" + guesses[row][col] + "_|");
				}
			}
			System.out.println();
		}

		System.out.println();

		// Print Heat Map	
		System.out.println();
		System.out.printf("%28s%n", "HEATMAP");
		System.out.print(" ");
		for(int i = 0; i < map.length; i ++) {
			System.out.printf("%5s", i + 1);
		}
		System.out.println();
		//System.out.print("   ");

		//for(int i = 0; i < map.length; i ++) {
		//System.out.print("___");
		//}
		//System.out.println("_________");

		for(int row = 0; row < map.length; row ++) {
			for(int col = 0; col < map[row].length; col ++) {
				if(row >= 0 && col == 0) {
					char letter = (char)(65 + row);
					System.out.print(letter + " |_" + map[row][col] + "_|");
				} else {
					System.out.print("_" + map[row][col] + "_|");
				}
			}
			System.out.println();
		}

		System.out.println();*/


		// Choose a spot to guess
		for(int row = 0; row < map.length; row ++) {
			for(int col = 0; col < map[row].length; col ++) {
				/*if(map[row][col] == highestProb && guesses[row][col] == '.'){
					if(tieBreaker(map, guesses, row, col, highestProbRow, highestProbCol, highestProb) == false) {
						highestProb = map[row][col];
						highestProbRow = row;
						highestProbCol = col;
					}
				} else */if(map[row][col] > highestProb && guesses[row][col] == '.') {
					highestProb = map[row][col];
					highestProbRow = row;
					highestProbCol = col;
				}

			}
		}


		char a = (char)((int)'A' + highestProbRow);
		String guess = a + Integer.toString(highestProbCol+1);
		//System.out.println(guess);
		//System.out.println("guess value is " + guesses[highestProbRow][highestProbCol]);
		//		System.out.println(highestProbRow);
		//		System.out.println(highestProbCol);
		//		System.out.println("highest value is " + highestProb);
		return guess;


	}

	public static boolean shipSunk(char[][] guesses, int[][]map, char shipType, int shipSize) {
		int shipHit = 0;
		boolean shipSunk = false;
		for(int row = 0; row < guesses.length; row ++) {
			for(int col = 0; col < guesses[row].length; col ++) {
				if(guesses[row][col] == shipType) {
					shipHit ++;
					map[row][col] = 0;
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

	public static void hit(char[][] guesses, int[][] map, int row, int col) {
		if(row < guesses.length - 1 && guesses[row + 1][col] == '.') {
			map[row + 1][col] += 100;
		} else if(row > 0 && row < guesses.length - 1 && guesses[row + 1][col] == 'X') {
			map[row - 1][col] += 200;
			if(row < guesses.length - 2 && guesses[row + 2][col] == '.') {
				map[row + 2][col] += 200;
			} else if(row < guesses.length -3 && guesses[row + 2][col] == 'X') {
				map[row + 3][col] += 200;
				if(row < guesses.length - 4 && guesses[row + 3][col] == '.') {
					map[row + 3][col] += 200;
				} else if(row < guesses.length -5 && guesses[row + 3][col] == 'X') {
					map[row + 4][col] += 200;
				}
			}
		}

		if(row > 0 && guesses[row - 1][col] == '.') {
			map[row - 1][col] += 100;
		} else if(row < guesses.length - 1 && row > 0 && guesses[row - 1][col] == 'X') {
			map[row + 1][col] += 200;
			if(row > 1 && guesses[row - 2][col] == '.') {
				map[row - 2][col] += 200;
			} else if(row > 2 && guesses[row - 2][col] == 'X') {
				map[row - 3][col] += 200;
				if(row > 3 && guesses[row - 3][col] == '.') {
					map[row - 3][col] += 200;
				} else if(row > 4 && guesses[row - 3][col] == 'X') {
					map[row - 4][col] += 200;
				}
			} 
		}

		if(col < guesses.length - 1 && guesses[row][col + 1] == '.') {
			map[row][col + 1] += 100;
		} else if(col > 0 && col < guesses.length - 1 && guesses[row][col + 1] == 'X') {
			map[row][col - 1] += 200;
			if(col < guesses.length - 2 && guesses[row][col + 2] == '.') {
				map[row][col + 2] += 200;
			} else if(col < guesses.length -3 && guesses[row][col + 2] == 'X') {
				map[row][col + 3] += 200;
				if(col < guesses.length -4 && guesses[row][col + 3] == '.') {
					map[row][col + 3] += 200;
				} else if(col < guesses.length -5 && guesses[row][col + 3] == 'X') {
					map[row][col + 4] += 200;
				}
			}
		}

		if(col > 0 && guesses[row][col - 1] == '.') {
			map[row][col - 1] += 100;
		} else if(col < guesses.length - 1 && col > 0 && guesses[row][col - 1] == 'X') {
			map[row][col + 1] += 200;
			if(col > 1 && guesses[row][col - 2] == '.') {
				map[row][col - 2] += 200;
			} else if(col > 2 && guesses[row][col - 2] == 'X') {
				map[row][col - 3] += 200;
				if(col > 3 && guesses[row][col - 3] == '.') {
					map[row][col - 3] += 200;
				} else if(col > 4 && guesses[row][col - 3] == 'X') {
					map[row][col - 4] += 200;
				}
			} 
		}

		map[row][col] = 0;
	}

	public static void miss(int[][] map, int row, int col) {
		map[row][col] = 0;
	}

	public static boolean tieBreaker(int[][] map, char[][] guesses, int row, int col, int highestProbRow, int highestProbCol, int highestProb) {
		int hpRowPlus = 0;
		int hpRowMinus = 0;
		int hpColPlus = 0;
		int hpColMinus = 0;
		int rowPlus = 0;
		int rowMinus = 0;
		int colPlus = 0;
		int colMinus = 0;
		boolean currentIsBest = false;

		if(highestProbRow < guesses.length - 1 && guesses[highestProbRow + 1][highestProbCol] == '.') {
			hpRowPlus = map[highestProbRow + 1][highestProbCol];
		}
		if(highestProbRow > 0 && guesses[highestProbRow - 1][highestProbCol] == '.') {
			hpRowMinus = map[highestProbRow - 1][highestProbCol];
		}
		if(highestProbCol < guesses.length - 1 && guesses[highestProbRow][highestProbCol + 1] == '.') {
			hpColPlus = map[highestProbRow][highestProbCol + 1];
		}
		if(highestProbCol > 0 && guesses[highestProbRow][highestProbCol - 1] == '.') {
			hpColMinus = map[highestProbRow][highestProbCol - 1];
		}
		if(row < guesses.length - 1 && guesses[row + 1][col] == '.') {
			rowPlus = map[row + 1][col];
		}
		if(row > 0 && guesses[row - 1][col] == '.') {
			rowMinus = map[row - 1][col];
		}
		if(col < guesses.length - 1 && guesses[row][col + 1] == '.') {
			colPlus = map[row][col + 1];
		}
		if(col > 0 && guesses[row][col - 1] == '.') {
			colMinus = map[row][col - 1];
		}

		int checkHighestProb = (hpRowPlus + hpRowMinus + hpColPlus + hpColMinus);
		int checkCurrentGuess = (rowPlus + rowMinus + colPlus + colMinus);
		if(checkCurrentGuess > checkHighestProb) {
			currentIsBest = true;
		}
		return currentIsBest;
	}
}



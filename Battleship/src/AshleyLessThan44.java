
public class AshleyLessThan44 {

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
		//		int lastGuessRow = 0;
		//		int lastGuessCol = 0;

		//initialize map
		//		for(int row = 0; row < map.length; row ++) {
		//			for(int col = 0; col < map[row].length; col ++) {
		//				map[row][col] = 1;
		//			}
		//		}
		//		
		
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

		// Print Heat Map	
		if(oneGame() == true) {
			System.out.println();
			System.out.printf("%28s%n", "HEATMAP");
			System.out.print(" ");
			for(int i = 0; i < map.length; i ++) {
				System.out.printf("%5s", i + 1);
			}
			System.out.println();
			System.out.print("   ");
	
			for(int i = 0; i < map.length; i ++) {
				System.out.print("___");
			}
			System.out.println("_________");
	
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
	
			System.out.println();
		}
				


		//int tie = 0;

		// Choose a spot to guess
		for(int row = map.length - 1; row >= 0; row --) {
			for(int col = map.length - 1; col >= 0; col --) {
				/*if(map[row][col] == highestProb) {
					highestProb = tieBreaker(guesses, map, row, col, acCarrier) > tieBreaker(guesses, map, highestProbRow, highestProbCol, acCarrier)? map[row][col]: highestProb;
				}*/
					if(map[row][col] > highestProb && guesses[row][col] == '.') {
					highestProb = map[row][col];
					highestProbRow = row;
					highestProbCol = col;
				} else if(map[row][col] == highestProb && highestProb > 1) {
					if(tieBreakerVal(guesses, map, row, col, 10) > tieBreakerVal(guesses, map, highestProbRow, highestProbCol, 10)) {
						highestProb = map[row][col];
						//highestProbRow = row;
						//highestProbCol = col;
					}
				}

					//System.out.println("tie, highest Prob spot is currently row " + highestProbRow + " and col " + highestProbCol);
					//					if(tieBreaker(guesses, map, row, col, acCarrier) < tieBreaker(guesses, map, highestProbRow, highestProbCol, acCarrier)) {
					//						highestProb = map[row][col];
					//						highestProbRow = row;
					//						highestProbCol = col;
					//						//System.out.println("tiebreaker used, highest Prob spot is now row " + highestProbRow + " and col " + highestProbCol);
					//					} else {
					//						highestProbRow = highestProbRow;
					//						highestProbCol = highestProbCol;
					//					}

					//highestProb = tieBreaker(guesses, map, row, col, acCarrier) < tieBreaker(guesses, map, highestProbRow, highestProbCol, acCarrier)? map[row][col]: highestProb;
					//System.out.println("tiebreaker not used, highest Prob spot is still row " + highestProbRow + " and col " + highestProbCol);
				} 
			}
		

		//System.out.println(tie);

		//		lastGuessRow = highestProbRow;
		//		lastGuessCol = highestProbCol;
		char a = (char)((int)'A' + highestProbRow);
		String guess = a + Integer.toString(highestProbCol+1);
		if(oneGame() == true) {
			System.out.println(guess);
		}
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
			map[row][col + s] += Math.abs(shipSize - 10);
		}						
	}

	public static void increaseY(int[][] map, int row, int col, int shipSize) {
		for(int s = 0; s < shipSize; s ++) {
			map[row + s][col] += Math.abs(shipSize - 10);
		}						
	}

	public static void hit(char[][] guesses, int[][] map, int row, int col) {
		map[row][col] = 0;
		if(row < guesses.length - 1 && guesses[row + 1][col] == '.') {
			map[row + 1][col] *= 50;
		} else if(row > 0 && row < guesses.length - 1 && guesses[row + 1][col] == 'X') {
			map[row - 1][col] *= 100;
			if(row < guesses.length - 2 && guesses[row + 2][col] == '.') {
				map[row + 2][col] *= 100;
			}
		}
		if(row > 0 && guesses[row - 1][col] == '.') {
			map[row - 1][col] *= 50;
		} else if(row < guesses.length - 1 && row > 0 && guesses[row - 1][col] == 'X') {
			map[row + 1][col] *= 100;
			if(row > 1 && guesses[row - 2][col] == '.') {
				map[row - 2][col] *= 100;
			}
		}
		if(col < guesses.length - 1 && guesses[row][col + 1] == '.') {
			map[row][col + 1] *= 50;
		} else if(col > 0 && col < guesses.length - 1 && guesses[row][col + 1] == 'X') {
			map[row][col - 1] *= 100;
			if(col < guesses.length - 2 && guesses[row][col + 2] == '.') {
				map[row][col + 2] *= 100;
			}
		}
		if(col > 0 && guesses[row][col - 1] == '.') {
			map[row][col - 1] *= 50;
		} else if(col < guesses.length - 1 && col > 0 && guesses[row][col - 1] == 'X') {
			map[row][col + 1] *= 100;
			if(col > 1 && guesses[row][col - 2] == '.') {
				map[row][col - 2] *= 100;
			}
		}
	}

	public static void miss(int[][] map, int row, int col) {
		map[row][col] = 0;
	}

	public static boolean isValid(int r, int c) {
		return r >= 0 && r < 10 && c >= 0 && c < 10;
	}
	public static boolean isOpen(char[][] guesses, int r, int c) {
		return guesses[r][c] == '.' || guesses[r][c] == 'X';
	}
	public static int tieBreakerVal(char[][] guesses, int[][] map, int row, int col, int shipSize) {
		int highest = 0;
		//int diags = 0;
		int left = 0;
		int right = 0;
		int up = 0;
		int down = 0;
		for(int r = row; row < shipSize && r < 10; r ++) {
			if(guesses[r][col] == '.' && map[r][col] > 0) {
				down += map[r][col];
			}
			//			if(guesses[r][col] == '.' && map[r][col] == 0) {
			//				down -= 20;
			//			}
			//			if(r == 9) {
			//				down -= 20;
			//			}
		}
		for(int r = row; row < shipSize && r > 0; r --) {
			if(guesses[r][col] == '.' && map[r][col] > 0) {
				up += map[r][col];
			}
			//			if(guesses[r][col] == '.' && map[r][col] == 0) {
			//				up -= 20;
			//			}
			//			if(r == 0) {
			//				up -= 20;
			//			}
		}
		for(int c = col; col < shipSize && c < 10; c ++) {
			if(guesses[row][c] == '.' && map[row][c] > 0) {
				right += map[row][c];
			}
			//			if(guesses[row][c] == '.' && map[row][c] == 0) {
			//				right -= 20;
			//			}
			//			if(c == 9) {
			//				right -= 20;
			//			}
		}
		for(int c = col; col < shipSize && c > 0; c --) {
			if(guesses[row][c] == '.' && map[row][c] > 0) {
				left += map[row][c];
			}
			//			if(guesses[row][c] == '.' && map[row][c] == 0) {
			//				left -= 20;
			//			}
			//			if(c == 0) {
			//				left -= 20;
			//			}
		}
		/*if(row < 9 && col < 9 && guesses[row + 1][col + 1] != '.') {
			diags ++;
			//center -= 40;
		} 
		if(row < 9 && col > 0 && guesses[row + 1][col - 1] != '.') {
			diags ++;
			//center -= 40;
		}
		if(row > 0 && col < 9 && guesses[row - 1][col + 1] != '.') {
			diags ++;
			//center -= 40;
		}
		if(row > 0 && col > 0 && guesses[row - 1][col - 1] != '.') {
			diags ++;
			//center -= 40;
		}*/
		//highest = (Math.abs(down - up)) + (Math.abs(left - right)) - diags;
		highest = down + left + up + right;
		if(oneGame() == true) {
			//System.out.println(highest + " is highest value for row " + row + " and col " + col);
		}
		return highest;
	}
	public static int tieBreaker(char[][] guesses, int[][] map, int row, int col, int shipSize) {
		int center = 0;
		int diags = 0;
		int left = 0;
		int right = 0;
		int up = 0;
		int down = 0;
		for(int r = row; row < shipSize && r < 10; r ++) {
			if(guesses[r][col] == '.' && map[r][col] > 0) {
				down ++;
			}
			if(guesses[r][col] == '.' && map[r][col] == 0) {
				down -= 20;
			}
			if(r == 9) {
				down -= 20;
			}
		}
		for(int r = row; row < shipSize && r > 0; r --) {
			if(guesses[r][col] == '.' && map[r][col] > 0) {
				up ++;
			}
			if(guesses[r][col] == '.' && map[r][col] == 0) {
				up -= 20;
			}
			if(r == 0) {
				up -= 20;
			}
		}
		for(int c = col; col < shipSize && c < 10; c ++) {
			if(guesses[row][c] == '.' && map[row][c] > 0) {
				right ++;
			}
			if(guesses[row][c] == '.' && map[row][c] == 0) {
				right -= 20;
			}
			if(c == 9) {
				right -= 20;
			}
		}
		for(int c = col; col < shipSize && c > 0; c --) {
			if(guesses[row][c] == '.' && map[row][c] > 0) {
				left ++;
			}
			if(guesses[row][c] == '.' && map[row][c] == 0) {
				left -= 20;
			}
			if(c == 0) {
				left -= 20;
			}
		}
		if(row < 9 && col < 9 && guesses[row + 1][col + 1] != '.') {
			diags ++;
			//center -= 40;
		} 
		if(row < 9 && col > 0 && guesses[row + 1][col - 1] != '.') {
			diags ++;
			//center -= 40;
		}
		if(row > 0 && col < 9 && guesses[row - 1][col + 1] != '.') {
			diags ++;
			//center -= 40;
		}
		if(row > 0 && col > 0 && guesses[row - 1][col - 1] != '.') {
			diags ++;
			//center -= 40;
		}
		center = (Math.abs(down - up)) + (Math.abs(left - right)) - diags;
		if(oneGame() == true) {
			//System.out.println(center + " is center value for row " + row + " and col " + col);
		}
		return center;
	}

	public static boolean oneGame() {
		return false;
	}
}




public class FailedStrategies {
	// Aircraft Carrier Strategy
	//int notFirst = 0;
	//		for(int row = 0; row < map.length - 1; row ++) {
	//			for(int col = 0; col < map[row].length; col ++) {
	//				if(guesses[row][col] != '.') {
	//					notFirst ++;
	//				}
	//				if(notFirst == 0) {
	//					if((row == 0 || row == 5) && (col == 4 || col == 9)) {
	//						map[row][col] += 5;
	//					} else if((row == 1 || row == 6) && (col == 0 || col == 5)) {
	//						map[row][col] += 5;
	//					} else if((row == 2 || row == 7) && (col == 1 || col == 6)) {
	//						map[row][col] += 5;
	//					} else if((row == 3 || row == 8) && (col == 2 || col == 7)) {
	//						map[row][col] += 5;
	//					} else if((row == 4 || row == 9) && (col == 3 || col == 8)) {
	//						map[row][col] += 5;
	//					}
	//				}
	//
	//			}
	//		}
	/*for(int row = map.length - 1; row >= 0; row --) {
	for(int col = map.length - 1; col >= 0; col --) {
		//int currentQ = quadrants(row, col);
		//int lastQ = quadrants(highestProbRow, highestProbCol);
		if(map[row][col] > highestProb && guesses[row][col] == '.') {
			highestProb = map[row][col];
			highestProbRow = row;
			highestProbCol = col;*/
/* else if(map[row][col] == highestProb && highestProb > 1) {
			if(lastQ == currentQ) {
				map[row][col] -= 1;
			}
		}*/
/*highestProb = tieBreaker(guesses, map, row, col, 10) < tieBreaker(guesses, map, highestProbRow, highestProbCol, 10)? map[row][col]: highestProb;
			highestProbRow = row;
			highestProbCol = col;
			/*if(map[row][col] == highestProb && highestProb > 1) {
				highestProb = tieBreakerVal(guesses, map, row, col, 10) > tieBreakerVal(guesses, map, highestProbRow, highestProbCol, 10)? map[row][col]: highestProb;
				highestProbRow = row;
				highestProbCol = col;

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
		} */



	// Battleship Strategy
	//		for(int row = 0; row < map.length - 1; row ++) {
	//			for(int col = 0; col < map[row].length; col ++) {
	//				if(guesses[row][col] != '.') {
	//					notFirst ++;
	//				}
	//				if(notFirst == 0) {
	//					if(guesses[row][col] == '.') {
	//						if((row == 0 || row == 4 || row == 8) && (col == 3 || col == 7)) {
	//							map[row][col] += 10;
	//						} else if((row == 1 || row == 5 || row == 9)  && (col == 0 || col == 4 || col == 8)) {
	//							map[row][col] += 10;
	//						} else if((row == 2 || row == 6)  && (col == 1 || col == 5 || col == 9)) {
	//							map[row][col] += 10;
	//						} else if((row == 3 || row == 7)  && (col == 2 || col == 6)) {
	//							map[row][col] += 10;
	//						} 
	//					}
	//				}
	//			}
	//		}

	// Submarine/Destroyer Strategy 
	/*for(int row = 0; row < map.length - 1; row ++) {
		for(int col = 0; col < map[row].length; col ++) {
			if(guesses[row][col] != '.') {
				notFirst ++;
			}
			if(notFirst < 5) {
				if(guesses[row][col] == '.') {
					if((row == 0 || row == 3 || row == 6 || row == 9) && (col == 2 || col == 5 || col == 8)) {
						map[row][col] += 25;
					} else if((row == 1 || row == 4 || row == 7)  && (col == 0 || col == 3 || col == 6 || col == 9)) {
						map[row][col] += 25;
					} else if((row == 2 || row == 5 || row == 8)  && (col == 1 || col == 4 || col == 7)) {
						map[row][col] += 25;
					} 
				}
			}
		}
	}*/

	// Patrol Boat Strategy
	//		for(int row = 0; row < map.length - 1; row ++) {
	//			for(int col = 0; col < map[row].length; col ++) {
	//					if(guesses[row][col] == '.') {
	//						if((row == 0 || row == 2 || row == 4 || row == 6 || row == 8) && (col == 2 || col == 4 || col == 6 || col == 8)) {
	//							map[row][col] += 25;
	//						} else if((row == 1 || row == 3 || row == 5 || row == 7 || row == 9)  && (col == 1 || col == 3 || col == 5 || col == 7 || col == 9)) {
	//							map[row][col] += 25;
	//						} 
	//					}
	//				}
	//			}

}


//Choose a spot to guess
//		for(int row = 0; row < map.length; row ++) {
//			for(int col = 0; col < map[row].length; col ++) {
//				/*if(map[row][col] == highestProb && guesses[row][col] == '.'){
//					if(tieBreaker(map, guesses, row, col, highestProbRow, highestProbCol, highestProb) == false) {
//						highestProb = map[row][col];
//						highestProbRow = row;
//						highestProbCol = col;
//					}
//				} else */if(map[row][col] > highestProb && guesses[row][col] == '.') {
//					highestProb = map[row][col];
//					highestProbRow = row;
//					highestProbCol = col;
//				}
//
//			}
//		}


//public static boolean tieBreaker(int[][] map, char[][] guesses, int row, int col, int highestProbRow, int highestProbCol, int highestProb) {
//	int hpRowPlus = 0;
//	int hpRowMinus = 0;
//	int hpColPlus = 0;
//	int hpColMinus = 0;
//	int rowPlus = 0;
//	int rowMinus = 0;
//	int colPlus = 0;
//	int colMinus = 0;
//	boolean currentIsBest = false;
//
//	if(highestProbRow < guesses.length - 1 && guesses[highestProbRow + 1][highestProbCol] == '.') {
//		hpRowPlus = map[highestProbRow + 1][highestProbCol];
//	}
//	if(highestProbRow > 0 && guesses[highestProbRow - 1][highestProbCol] == '.') {
//		hpRowMinus = map[highestProbRow - 1][highestProbCol];
//	}
//	if(highestProbCol < guesses.length - 1 && guesses[highestProbRow][highestProbCol + 1] == '.') {
//		hpColPlus = map[highestProbRow][highestProbCol + 1];
//	}
//	if(highestProbCol > 0 && guesses[highestProbRow][highestProbCol - 1] == '.') {
//		hpColMinus = map[highestProbRow][highestProbCol - 1];
//	}
//	if(row < guesses.length - 1 && guesses[row + 1][col] == '.') {
//		rowPlus = map[row + 1][col];
//	}
//	if(row > 0 && guesses[row - 1][col] == '.') {
//		rowMinus = map[row - 1][col];
//	}
//	if(col < guesses.length - 1 && guesses[row][col + 1] == '.') {
//		colPlus = map[row][col + 1];
//	}
//	if(col > 0 && guesses[row][col - 1] == '.') {
//		colMinus = map[row][col - 1];
//	}
//
//	int checkHighestProb = (hpRowPlus + hpRowMinus + hpColPlus + hpColMinus);
//	int checkCurrentGuess = (rowPlus + rowMinus + colPlus + colMinus);
//	if(checkCurrentGuess > checkHighestProb) {
//		currentIsBest = true;
//	}
//	return currentIsBest;
//}


//public static boolean tieBreaker(char[][] guesses, int row, int col, int highestProbRow, int highestProbCol, int highestProb) {
//
//	int down = 0;
//	int up = 0;
//	int left = 0;
//	int right = 0;
//	int hpDown = 0;
//	int hpUp = 0;
//	int hpLeft = 0;
//	int hpRight = 0;
//	boolean currentWins = false;
//
//	for(int r = row; r >= 0 && guesses[r][col] == '.'; r --) {
//		down ++;
//	}
//	for(int r = row; r < guesses.length && guesses[r][col] == '.'; r ++) {
//		up ++;	
//	}
//	for(int c = col; c >= 0 && guesses[row][c] == '.'; c --) {
//		left ++;
//	}
//	for(int c = col; c < guesses.length && guesses[row][c] == '.'; c ++) {
//		right ++;
//	}
//
//	for(int r = highestProbRow; r >= 0 && guesses[r][highestProbCol] == '.'; r --) {
//		hpDown ++;
//	}
//	for(int r = highestProbRow; r < guesses.length && guesses[r][highestProbCol] == '.'; r ++) {
//		hpUp ++;	
//	}
//	for(int c = highestProbCol; c >= 0 && guesses[highestProbRow][c] == '.'; c --) {
//		hpLeft ++;
//	}
//	for(int c = highestProbCol; c < guesses.length && guesses[highestProbRow][c] == '.'; c ++) {
//		hpRight ++;
//	}
//
//	int current = (down + up + left + right);
//	int hp = (hpDown + hpUp + hpLeft + hpRight);
//
//	if(current > hp) {
//		currentWins = true;
//	}
//	return currentWins;
//}

//public static void hit(char[][] guesses, int[][] map, int row, int col) {
//	
//	if(row < guesses.length - 1 && guesses[row + 1][col] == '.') {
//		map[row + 1][col] += 1000;
//	} else if(row > 0 && row < guesses.length - 1 && guesses[row + 1][col] == 'X') {
//		map[row - 1][col] += 2000;
//		if(row < guesses.length - 2 && guesses[row + 2][col] == '.') {
//			map[row + 2][col] += 2000;
//		} else if(row < guesses.length -3 && guesses[row + 2][col] == 'X') {
//			map[row + 3][col] += 2000;
//			if(row < guesses.length - 4 && guesses[row + 3][col] == '.') {
//				map[row + 3][col] += 2000;
//			} else if(row < guesses.length -5 && guesses[row + 3][col] == 'X') {
//				map[row + 4][col] += 2000;
//			}
//		}
//	}
//
//	if(row > 0 && guesses[row - 1][col] == '.') {
//		map[row - 1][col] += 1000;
//	} else if(row < guesses.length - 1 && row > 0 && guesses[row - 1][col] == 'X') {
//		map[row + 1][col] += 2000;
//		if(row > 1 && guesses[row - 2][col] == '.') {
//			map[row - 2][col] += 2000;
//		} else if(row > 2 && guesses[row - 2][col] == 'X') {
//			map[row - 3][col] += 2000;
//			if(row > 3 && guesses[row - 3][col] == '.') {
//				map[row - 3][col] += 2000;
//			} else if(row > 4 && guesses[row - 3][col] == 'X') {
//				map[row - 4][col] += 2000;
//			}
//		} 
//	}
//
//	if(col < guesses.length - 1 && guesses[row][col + 1] == '.') {
//		map[row][col + 1] += 1000;
//	} else if(col > 0 && col < guesses.length - 1 && guesses[row][col + 1] == 'X') {
//		map[row][col - 1] += 2000;
//		if(col < guesses.length - 2 && guesses[row][col + 2] == '.') {
//			map[row][col + 2] += 2000;
//		} else if(col < guesses.length -3 && guesses[row][col + 2] == 'X') {
//			map[row][col + 3] += 2000;
//			if(col < guesses.length -4 && guesses[row][col + 3] == '.') {
//				map[row][col + 3] += 2000;
//			} else if(col < guesses.length -5 && guesses[row][col + 3] == 'X') {
//				map[row][col + 4] += 2000;
//			}
//		}
//	}
//
//	if(col > 0 && guesses[row][col - 1] == '.') {
//		map[row][col - 1] += 1000;
//	} else if(col < guesses.length - 1 && col > 0 && guesses[row][col - 1] == 'X') {
//		map[row][col + 1] += 2000;
//		if(col > 1 && guesses[row][col - 2] == '.') {
//			map[row][col - 2] += 2000;
//		} else if(col > 2 && guesses[row][col - 2] == 'X') {
//			map[row][col - 3] += 2000;
//			if(col > 3 && guesses[row][col - 3] == '.') {
//				map[row][col - 3] += 2000;
//			} else if(col > 4 && guesses[row][col - 3] == 'X') {
//				map[row][col - 4] += 2000;
//			}
//		} 
//	}
//
//	map[row][col] = 0;
//}
/*if(guesses[row][col] != '.'){
continue;
}*/
/*else if(map[row][col] == highestProb && guesses[row][col] == '.') {
highestProb = (Math.abs(lastGuessRow - row) + Math.abs(lastGuessCol - col)) < (Math.abs(lastGuessRow - highestProbRow) + Math.abs(lastGuessCol - highestProbCol))? map[row][col]: map[highestProbRow][highestProbCol];
//if((Math.abs(lastGuessRow - row) + Math.abs(lastGuessCol - col)) == (Math.abs(lastGuessRow - highestProbRow) + Math.abs(lastGuessCol - highestProbCol))) {
//tie ++;
//}

if(lastGuessRow > 4 && lastGuessCol > 4 || lastGuessRow < 5 && lastGuessCol > 4) {
	highestProb = ((row >= lastGuessRow) && (col <= lastGuessCol))? map[row][col]: map[highestProbRow][highestProbCol];
} else if(lastGuessRow > 4 && lastGuessCol < 5 || lastGuessRow < 5 && lastGuessCol < 4) {
	highestProb = ((row <= lastGuessRow) && (col >= lastGuessCol))? map[row][col]: map[highestProbRow][highestProbCol];
} 

if(map[row][col] == highestProb && guesses[row][col] == '.'){
if(tieBreaker(map, guesses, row, col, highestProbRow, highestProbCol, highestProb) == false) {
	highestProb = map[row][col];
	highestProbRow = row;
	highestProbCol = col;
}
} } else*/ 

//public static int tieBreaker(char[][] guesses, int[][] map, int row, int col, int shipSize) {
//int center = 0;
//int left = 0;
//int right = 0;
//int up = 0;
//int down = 0;
//for(int r = row; row < shipSize && r < 10; r ++) {
//	if(guesses[r][col] == '.' && map[r][col] > 0) {
//		down ++;
//	}
//}
//for(int r = row; row < shipSize && r > 0; r --) {
//	if(guesses[r][col] == '.' && map[r][col] > 0) {
//		up ++;
//	}
//}
//for(int c = col; col < shipSize && c < 10; c ++) {
//	if(guesses[row][c] == '.' && map[row][c] > 0) {
//		right ++;
//	}
//}
//for(int c = col; col < shipSize && c > 0; c --) {
//	if(guesses[row][c] == '.' && map[row][c] > 0) {
//		left ++;
//	}
//}
//center = (Math.abs(down - up)) + (Math.abs(left - right));
//return center;
//}
//
//public static int tieBreakerRow(char[][] guesses, int[][] map, int row, int col, int highestProb) {
//int [] occurence = new int[10];
//int i = 0;
//int sum = 0;
//int center = 0;
//for(int r = row; r < guesses.length && guesses[r][col] == '.' && map[r][col] == highestProb; r ++) {
//	occurence[i] ++;
//	sum += i;
//	i ++;
//}
//if(i > 2) {
//	center = sum/i - 1;
//	//System.out.println("sum is " + sum);
//	//System.out.println("i is " + i);
//	if(center >= 0) {
//		return center;
//	} else {
//		return 0;
//	}
//
//} else {
//	return 0;
//}
//}
//public static int tieBreakerCol(char[][] guesses, int[][] map, int row, int col, int highestProb) {
//int [] occurence = new int[10];
//int i = 0;
//int sum = 0;
//int center = 0;
//for(int c = row; c < guesses.length && guesses[row][c] == '.' && map[row][c] == highestProb; c ++) {
//	occurence[i] ++;
//	sum += i;
//	i ++;
//}
//if(i > 2) {
//	center = sum/i - 1;
//	//System.out.println("sum is " + sum);
//	//System.out.println("i is " + i);
//	if(center >= 0) {
//		return center;
//	} else {
//		return 0;
//	}
//
//} else {
//	return 0;
//}
//}

//largest ship left alive
//int largestShip = 0;
//if(shipSunk(guesses, map, acCar, acCarrier) == false) {
//	largestShip = acCarrier;
//} else if(shipSunk(guesses, map, bShip, battleship) == false) {
//	largestShip = battleship;
//} else if(shipSunk(guesses, map, dest, destroyer) == false) {
//	largestShip = destroyer;
//} else if(shipSunk(guesses, map, sub, submarine) == false) {
//	largestShip = submarine;
//} else {
//	largestShip = patrolBoat;
//}
//
////smallest ship left alive
//int smallestShip = 0;
//if(shipSunk(guesses, map, pBoat, patrolBoat) == false) {
//	smallestShip = patrolBoat;
//} else if(shipSunk(guesses, map, sub, submarine) == false) {
//	smallestShip = submarine;
//} else if(shipSunk(guesses, map, dest, destroyer) == false) {
//	smallestShip = destroyer;
//} else if(shipSunk(guesses, map, bShip, battleship) == false) {
//	smallestShip = battleship;
//} else {
//	smallestShip = acCarrier;
//}

//lastQ = currentQ;
//
//// Heatmap Q1
//for(int row = 0; row < 5; row ++) {
//	for(int col = 0; col < 5; col ++) {
//		if(map[row][col] >= highestProbQ1 && guesses[row][col] == '.') {
//			highestProbQ1 = map[row][col];
//			highestProbRowQ1 = row;
//			highestProbColQ1 = col;
//		}
//	}
//}
//// Heatmap Q2
//for(int row = 0; row < 5; row ++) {
//	for(int col = 5; col < 10; col ++) {
//		if(map[row][col] >= highestProbQ2 && guesses[row][col] == '.') {
//			highestProbQ2 = map[row][col];
//			highestProbRowQ2 = row;
//			highestProbColQ2 = col;
//		}
//	}
//}
//
//// Heatmap Q3
//for(int row = 5; row < 10; row ++) {
//	for(int col = 0; col < 5; col ++) {
//		if(map[row][col] >= highestProbQ3 && guesses[row][col] == '.') {
//			highestProbQ3 = map[row][col];
//			highestProbRowQ3 = row;
//			highestProbColQ3 = col;
//		}
//	}
//}
//
//// Heatmap Q4
//for(int row = 5; row < 10; row ++) {
//	for(int col = 5; col < 10; col ++) {
//		if(map[row][col] >= highestProbQ4 && guesses[row][col] == '.') {
//			highestProbQ4 = map[row][col];
//			highestProbRowQ4 = row;
//			highestProbColQ4 = col;
//		}
//	}
//}
//
//
//if(highestProbQ1 > highestProb) {
//	highestProb = highestProbQ1;
//	highestProbRow = highestProbRowQ1;
//	highestProbCol = highestProbColQ1;
//	currentQ = 1;
//} else if(highestProbQ1 == highestProb) {
//	if(lastQ != 1) {
//		highestProb = highestProbQ1;
//		highestProbRow = highestProbRowQ1;
//		highestProbCol = highestProbColQ1;
//		currentQ = 1;
//	}
//}
//
//if(highestProbQ2 > highestProb) {
//	highestProb = highestProbQ2;
//	highestProbRow = highestProbRowQ2;
//	highestProbCol = highestProbColQ2;
//	currentQ = 2;
//} else if(highestProbQ2 == highestProb) {
//	if(lastQ != 2) {
//		highestProb = highestProbQ2;
//		highestProbRow = highestProbRowQ2;
//		highestProbCol = highestProbColQ2;
//		currentQ = 2;
//	}
//}
//
//if(highestProbQ3 > highestProb) {
//	highestProb = highestProbQ3;
//	highestProbRow = highestProbRowQ3;
//	highestProbCol = highestProbColQ3;
//	currentQ = 3;
//} else if(highestProbQ3 == highestProb) {
//	if(lastQ != 3) {
//		highestProb = highestProbQ3;
//		highestProbRow = highestProbRowQ3;
//		highestProbCol = highestProbColQ3;
//		currentQ = 3;
//	}
//}
//
//if(highestProbQ4 > highestProb) {
//	highestProb = highestProbQ4;
//	highestProbRow = highestProbRowQ4;
//	highestProbCol = highestProbColQ4;
//	currentQ = 4;
//} else if(highestProbQ4 == highestProb) {
//	if(lastQ != 4) {
//		highestProb = highestProbQ4;
//		highestProbRow = highestProbRowQ4;
//		highestProbCol = highestProbColQ4;
//		currentQ = 4;
//	}
//}

//public static int tieBreakerVal(char[][] guesses, int[][] map, int row, int col, int shipSize) {
//	int highest = 0;
//	//int diags = 0;
//	int left = 0;
//	int right = 0;
//	int up = 0;
//	int down = 0;
//	for(int r = row; row < shipSize && r < 10; r ++) {
//		if(guesses[r][col] == '.' && map[r][col] > 0) {
//			down += map[r][col];
//		}
//		//			if(guesses[r][col] == '.' && map[r][col] == 0) {
//		//				down -= 20;
//		//			}
//		//			if(r == 9) {
//		//				down -= 20;
//		//			}
//	}
//	for(int r = row; row < shipSize && r > 0; r --) {
//		if(guesses[r][col] == '.' && map[r][col] > 0) {
//			up += map[r][col];
//		}
//		//			if(guesses[r][col] == '.' && map[r][col] == 0) {
//		//				up -= 20;
//		//			}
//		//			if(r == 0) {
//		//				up -= 20;
//		//			}
//	}
//	for(int c = col; col < shipSize && c < 10; c ++) {
//		if(guesses[row][c] == '.' && map[row][c] > 0) {
//			right += map[row][c];
//		}
//		//			if(guesses[row][c] == '.' && map[row][c] == 0) {
//		//				right -= 20;
//		//			}
//		//			if(c == 9) {
//		//				right -= 20;
//		//			}
//	}
//	for(int c = col; col < shipSize && c > 0; c --) {
//		if(guesses[row][c] == '.' && map[row][c] > 0) {
//			left += map[row][c];
//		}
//		//			if(guesses[row][c] == '.' && map[row][c] == 0) {
//		//				left -= 20;
//		//			}
//		//			if(c == 0) {
//		//				left -= 20;
//		//			}
//	}
//	/*if(row < 9 && col < 9 && guesses[row + 1][col + 1] != '.') {
//		diags ++;
//		//center -= 40;
//	} 
//	if(row < 9 && col > 0 && guesses[row + 1][col - 1] != '.') {
//		diags ++;
//		//center -= 40;
//	}
//	if(row > 0 && col < 9 && guesses[row - 1][col + 1] != '.') {
//		diags ++;
//		//center -= 40;
//	}
//	if(row > 0 && col > 0 && guesses[row - 1][col - 1] != '.') {
//		diags ++;
//		//center -= 40;
//	}*/
//	//highest = (Math.abs(down - up)) + (Math.abs(left - right)) - diags;
//	highest = down + left + up + right;
//	if(oneGame() == true) {
//		//System.out.println(highest + " is highest value for row " + row + " and col " + col);
//	}
//	return highest;
//}

//		public static int tieBreakerRow(char[][] guesses, int[][] map, int row, int col, int highestProb) {
//			int [] occurence = new int[10];
//			int i = 0;
//			int sum = 0;
//			int center = 0;
//			for(int r = row; r < guesses.length && guesses[r][col] == '.' && map[r][col] == highestProb; r ++) {
//				occurence[i] ++;
//				sum += i;
//				i ++;
//			}
//			if(i > 2) {
//				center = sum/i - 1;
//				//System.out.println("sum is " + sum);
//				//System.out.println("i is " + i);
//				if(center >= 0) {
//					return center;
//				} else {
//					return 0;
//				}
//	
//			} else {
//				return 0;
//			}
//		}
//		public static int tieBreakerCol(char[][] guesses, int[][] map, int row, int col, int highestProb) {
//			int [] occurence = new int[10];
//			int i = 0;
//			int sum = 0;
//			int center = 0;
//			for(int c = row; c < guesses.length && guesses[row][c] == '.' && map[row][c] == highestProb; c ++) {
//				occurence[i] ++;
//				sum += i;
//				i ++;
//			}
//			if(i > 2) {
//				center = sum/i - 1;
//				//System.out.println("sum is " + sum);
//				//System.out.println("i is " + i);
//				if(center >= 0) {
//					return center;
//				} else {
//					return 0;
//				}
//	
//			} else {
//				return 0;
//			}
//		}

//if(row < 9 && col < 9 && guesses[row + 1][col + 1] != '.') {
//	diags ++;
//} 
//if(row < 9 && col > 0 && guesses[row + 1][col - 1] != '.') {
//	diags ++;
//}
//if(row > 0 && col < 9 && guesses[row - 1][col + 1] != '.') {
//	diags ++;
//}
//if(row > 0 && col > 0 && guesses[row - 1][col - 1] != '.') {
//	diags ++;
//}

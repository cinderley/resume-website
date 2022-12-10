
public class AndreaTheConquerorOfTheSea {

	public static String makeGuess(char[][] guesses){

		int [][] heatmap = new int [10][10]; 	  

		//update heat map with Patrol
		for(int row = 0; row < 10; row ++){
			for(int col = 0; col < 10; col++){
				if(isOpen(row, col, guesses) && isValid(row, col + 1) && isOpen(row, col+ 1, guesses)){
					heatmap[row][col]++;
					heatmap[row][col +1]++;
					//horizontal
				}
			}
		}

		for(int row = 0; row < 10; row ++){
			for(int col = 0; col < 10; col++){
				if(isOpen(row, col, guesses) && isValid(row + 1, col) && isOpen(row + 1, col, guesses)){
					heatmap[row][col]++;
					heatmap[row + 1][col]++;
					//vertical
				}
			}

		}

		//update heat map with Sub 
		for(int row = 0; row < 10; row ++){
			for(int col = 0; col < 10; col++){
				if(isOpen(row, col, guesses) && isValid(row, col + 1) && isOpen(row, col+ 1, guesses)&& isValid(row, col + 2) && isOpen(row, col+ 2, guesses)){
					heatmap[row][col]++;
					heatmap[row][col +1]++;
					heatmap[row][col +2]++;					
					//horizontal
				}
			}
		}

		for(int row = 0; row < 10; row ++){
			for(int col = 0; col < 10; col++){
				if(isOpen(row, col, guesses) && isValid(row + 1, col) && isOpen(row + 1, col, guesses) && isValid(row + 2, col) && isOpen(row + 2, col, guesses)){
					heatmap[row][col]++;
					heatmap[row + 1][col]++;
					heatmap[row + 2][col]++;
					//vertical
				}
			}

		}
		//update heat map with Destroyer 
		for(int row = 0; row < 10; row ++){
			for(int col = 0; col < 10; col++){
				if(isOpen(row, col, guesses) && isValid(row, col + 1) && isOpen(row, col+ 1, guesses)&& isValid(row, col + 2) && isOpen(row, col+ 2, guesses)){
					heatmap[row][col]++;
					heatmap[row][col +1]++;
					heatmap[row][col +2]++;					
					//horizontal
				}
			}
		}

		for(int row = 0; row < 10; row ++){
			for(int col = 0; col < 10; col++){
				if(isOpen(row, col, guesses) && isValid(row + 1, col) && isOpen(row + 1, col, guesses) && isValid(row + 2, col) && isOpen(row + 2, col, guesses)){
					heatmap[row][col]++;
					heatmap[row + 1][col]++;
					heatmap[row + 2][col]++;
					//vertical
				}
			}

		}

		//update heat map with Battleship 
		for(int row = 0; row < 10; row ++){
			for(int col = 0; col < 10; col++){
				if(isOpen(row, col, guesses) && isValid(row, col + 1) && isOpen(row, col+ 1, guesses)&& isValid(row, col + 2) && isOpen(row, col+ 2, guesses) && isValid(row, col + 3) && isOpen(row, col+ 3, guesses)){
					heatmap[row][col]++;
					heatmap[row][col +1]++;
					heatmap[row][col +2]++;	
					heatmap[row][col +3]++;
					//horizontal
				}
			}
		}

		for(int row = 0; row < 10; row ++){
			for(int col = 0; col < 10; col++){
				if(isOpen(row, col, guesses) && isValid(row + 1, col) && isOpen(row + 1, col, guesses) && isValid(row + 2, col) && isOpen(row + 2, col, guesses) && isValid(row + 3, col) && isOpen(row + 3, col, guesses)){
					heatmap[row][col]++;
					heatmap[row + 1][col]++;
					heatmap[row + 2][col]++;
					heatmap[row + 3][col]++;
					//vertical
				}
			}

		}	

		//update heat map with AirCraftCarrier 
		for(int row = 0; row < 10; row ++){
			for(int col = 0; col < 10; col++){
				if(isOpen(row, col, guesses) && isValid(row, col + 1) && isOpen(row, col+ 1, guesses)&& isValid(row, col + 2) && isOpen(row, col+ 2, guesses) && isValid(row, col + 3) && isOpen(row, col+ 3, guesses) && isValid(row, col + 4) && isOpen(row, col+ 4, guesses)){
					heatmap[row][col]++;
					heatmap[row][col +1]++;
					heatmap[row][col +2]++;	
					heatmap[row][col +3]++;
					heatmap[row][col +4]++;
					//horizontal
				}
			}
		}

		for(int row = 0; row < 10; row ++){
			for(int col = 0; col < 10; col++){
				if(isOpen(row, col, guesses) && isValid(row + 1, col) && isOpen(row + 1, col, guesses) && isValid(row + 2, col) && isOpen(row + 2, col, guesses) && isValid(row + 3, col) && isOpen(row + 3, col, guesses) && isValid(row + 4, col) && isOpen(row + 4, col, guesses)){
					heatmap[row][col]++;
					heatmap[row + 1][col]++;
					heatmap[row + 2][col]++;
					heatmap[row + 3][col]++;
					heatmap[row + 4][col]++;
					//vertical
				}
			}

		}	
		//hit target
		//Code created with the insight of Ashley Clark
		for(int row = 0; row < guesses.length; row++){
			for(int col = 0; col < guesses[row].length; col++){
				if(guesses[row][col] =='X' ) {
					heatmap[row][col]= 0;
					if(row < 9) {
						heatmap[row + 1][col] *= 100;
					}
					if(row > 0) {
						heatmap[row - 1][col] *= 100;
					}
					if(col < 9) {
						heatmap[row][col + 1] *= 100;
					}
					if(col > 0) {
						heatmap[row][col - 1] *=100;
					}
				}
			}
		}
		//print heatmap		
		//		for(int row = 0; row < heatmap.length; row++){
		//			for (int col = 0; col < heatmap[row].length; col++){
		//				System.out.print(heatmap[row][col] + " ");
		//
		//			}
		//			System.out.println(" ");
		//
		//
		//		}
		int maximumRow = 0;
		int maximumCol = 0;
		int maximum = 0;
		for (int row = 0; row < heatmap.length; row ++) {
			for (int col = 0; col < heatmap[row].length; col ++) {
				if(heatmap[row][col] > maximum) {
					maximum = heatmap[row][col];
					maximumRow=row;
					maximumCol=col;




				}
			}

		}

		char a = (char)((int)'A' + maximumRow);
		String guess = a + Integer.toString(maximumCol+1);
		//System.out.println(guess);

		return guess;
	}

	public static boolean isValid(int r, int c){
		return r >= 0 && r < 10 && c >= 0 && c < 10;
	}

	public static boolean isOpen(int r, int c, char[][]guesses){
		return guesses[r][c]=='.' || guesses[r][c] == 'X';
	}


}



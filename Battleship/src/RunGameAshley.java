import java.util.Arrays;
public class RunGameAshley{
    public static void main(String[] args){
        //int sum = 0;
        int games = 10000;
        //play();
        int[] allGS = new int[100];
       for(int i = 0; i < games; i++){
//            sum += play();
    	   for(int a = 0; a < 100; a ++) {
    		   allGS[a] += play()[a];
    	   }
      }
//        double avg = (double)sum/games;
//        System.out.println("Avg is " + avg);
       for(int b = 0; b < 100; b ++) {
    	   System.out.print(allGS[b] + ", ");
       }
    }
    

    public static int[] play(){
    	int[] spots = new int[100];
        final int DIMENSIONS = 10;
        char[][] s1Guesses = new char[DIMENSIONS][DIMENSIONS];
        String[] history = new String[DIMENSIONS * DIMENSIONS];

        for(int row = 0; row < DIMENSIONS; row++){
            for(int col = 0; col < DIMENSIONS; col++){
                s1Guesses[row][col] = '.';
            }
        }

        int[][] gameBoard = new int[DIMENSIONS][DIMENSIONS];
        BattleShipTools.randomBoard(gameBoard);
        // BattleShipTools.problemBoard(gameBoard);
        //BattleShipTools.printBoard(gameBoard);
        int moves;

        for(moves = 1; moves < DIMENSIONS*DIMENSIONS+1; moves++){
            //copy array before passing to students
            char[][] s1Copy = Arrays.copyOf(s1Guesses, s1Guesses.length);

            String guess1 = AshleyTesting.makeGuess(s1Copy);
            //String guess1 = AshleyLessThan44pt5.makeGuess(s1Copy);
            
            history[moves -1] = guess1;
            //System.out.println(guess1);
            boolean p1 = BattleShipTools.updateGuess(s1Guesses,guess1,gameBoard);
            //break if the player won
            if(p1){
            	for(int row = 0; row < s1Guesses.length; row ++) {
            		for(int col = 0; col < s1Guesses[row].length; col ++) {
            			if(s1Guesses[row][col] != 'O' && s1Guesses[row][col] != '.') {
            				spots[(row * 10) + col] = 1;
                        }
            		}
            	}
                break;
            }
           // System.out.println("----------------------------------------------------");
           // BattleShipTools.printBoard(s1Guesses);
        }

        if(moves == DIMENSIONS * DIMENSIONS + 1){
            System.out.println("Out of Moves");
        }

        //System.out.println("Completed in " + moves + " moves");
        /*  
        for(int i = 0; i < history.length; i++){
        if(i % 10 == 0)
        System.out.println();
        if(history[i] != null){
        System.out.print(history[i] + ", ");
        }

        }
         */
        //System.out.println();
        //  BattleShipTools.printBoard(s1Guesses);
        return spots;
    }
    
}

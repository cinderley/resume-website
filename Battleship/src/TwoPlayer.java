import java.util.Arrays;
public class TwoPlayer{
    public static void main(String[] args){
        //int sum = 0;
        int games = 50000;
        int p1 = 0;
        int p2 = 0;
        int ties = 0;
        for(int i = 0; i < games; i++){
            int winner = twoPlay();
            if(winner == 1)
                p1++;
            if(winner == 2)
                p2++;
            if(winner == 0)
                ties++;
        }
        //double avg = (double)sum/games;
        //System.out.println("Avg over " + games + " is " + avg);
        System.out.println("Ashley2 wins " + p1);
        //System.out.println("Andrea wins " + p2);
        System.out.println("Ashley3 wins " + p2);
        System.out.println("Ties: " + ties);
    }

    public static int twoPlay(){
        final int DIMENSIONS = 10;
        char[][] s1Guesses = new char[DIMENSIONS][DIMENSIONS];
        char[][] s2Guesses = new char[DIMENSIONS][DIMENSIONS];
        String[] history = new String[DIMENSIONS * DIMENSIONS];

        for(int row = 0; row < DIMENSIONS; row++){
            for(int col = 0; col < DIMENSIONS; col++){
                s1Guesses[row][col] = '.';
                s2Guesses[row][col] = '.';
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
            char[][] s2Copy = Arrays.copyOf(s2Guesses, s2Guesses.length);
            
            String guess1 = AshleyTesting2.makeGuess(s1Copy);
            //String guess2 = AndreaTheConquerorOfTheSea.makeGuess(s2Copy);
            String guess2 = AshleyTesting3.makeGuess(s2Copy);
            history[moves -1] = guess1;
            //System.out.println(guess1);
            boolean p1 = BattleShipTools.updateGuess(s1Guesses,guess1,gameBoard);
            boolean p2 = BattleShipTools.updateGuess(s2Guesses,guess2,gameBoard);
            
//            if(p2 && !p1) {
//            	System.out.println("----------------------------------------------------");
//                BattleShipTools.printBoard(s1Guesses);	
//            }
            //break if the player won
            if(p1 && !p2){
                return 1;
            }else if(p2 && !p1){
                return 2;
            }else if(p1 && p2){
                return 0;
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
        return moves;
    }

}

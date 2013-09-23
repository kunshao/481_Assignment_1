package com.example.tic_tac_toe;


public class TicTacToeGame {

		private char[][] board;
		private final static int BOARD_DIM = 3;
		
		public int PLAYER_1 = 1;
		public int PLAYER_2 = 2;
		
		public static final char PLAYER_1_MARK = 'X';
		public static final char PLAYER_2_MARK = 'O';
		public static final char EMPTY_SPACE_MARK = ' ';
		
		
		public int get_Board_Dim(){
			return BOARD_DIM ;
		}
		
		public char get_Player_Mark(int player){
			if (player == PLAYER_1)
				return PLAYER_1_MARK;
			else
				return PLAYER_2_MARK;
		}
		
		public TicTacToeGame(){
			board = new char[BOARD_DIM][BOARD_DIM];
			for (int i = 0; i < BOARD_DIM; i++) {
				for (int j = 0; j < BOARD_DIM; j++)
					board[i][j] = EMPTY_SPACE_MARK;
			}
			
		}
		
		public void clearBoard(){
			for (int i = 0; i < BOARD_DIM; i++) {
				for (int j = 0; j < BOARD_DIM; j++)
					board[i][j] = EMPTY_SPACE_MARK;
			}
		}
		
		public void setMove(int player, int row, int col){
			board[row][col] = get_Player_Mark(player); 
		}
		
		public boolean checkForWin(int player){
			boolean win = true;
			int k = 0;
			
			// check for horizontal condition
			for (int i = 0; i < BOARD_DIM; i++) {
				for (int j = 0; j < BOARD_DIM; j++){
					if (board[i][j] != get_Player_Mark(player)){
						win = false;
						break;
					}		
				}
				if (win == true)
					return true;
				win = true;
			}
			
			win = true;
			
			//checking for vertical condition
			for (int i = 0; i < BOARD_DIM; i++){
				for (int j = 0; j < BOARD_DIM; j++){
					if (board[j][i] != get_Player_Mark(player)){
						win = false;
						break;
					}
				}
				if (win == true)
					return true;
				win = true;
			}
			
			win = true;
			
			//checking for diagonal condition 1
			for(int i = 0; i < BOARD_DIM; i++)
				if (board[i][k++] != get_Player_Mark(player)){
					win = false;
					break;
				}
			
			if (win == true){
				return true;
			}
			
			k = 2;
			win = true;
			
			// checking for diagonal condition 2
			for (int i = 0; i < 3; i++)
	    		if (board[i][k--] != get_Player_Mark(player)) {
	    			win = false;
	    			break;
	    		}
	    	
	    	if (win == true) {
	    		return true;
	    	}
	    	
	       	return false;
			
		}// checkForWin()
		
		
		
}

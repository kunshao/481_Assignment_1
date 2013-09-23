package com.example.tic_tac_toe;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	private TicTacToeGame mGame;
	
	private Button mBoardButtons[];
	private Button mRestartGameButton;
	
	private TextView mInfoTextView;
	
	private int mPlayer;
	private boolean mGameOver;
	private int mMoveCounter;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        mBoardButtons = new Button[9];
        mBoardButtons[0] = (Button) findViewById(R.id.OneOne);
        mBoardButtons[1] = (Button) findViewById(R.id.OneTwo);
        mBoardButtons[2] = (Button) findViewById(R.id.OneThree);
        mBoardButtons[3] = (Button) findViewById(R.id.TwoOne);
        mBoardButtons[4] = (Button) findViewById(R.id.TwoTwo);
        mBoardButtons[5] = (Button) findViewById(R.id.TwoThree);
        mBoardButtons[6] = (Button) findViewById(R.id.ThreeOne);
        mBoardButtons[7] = (Button) findViewById(R.id.ThreeTwo);
        mBoardButtons[8] = (Button) findViewById(R.id.ThreeThree);

        mInfoTextView = (TextView) findViewById(R.id.Information);
        mRestartGameButton = (Button) findViewById(R.id.New_Game);
        
        mGame = new TicTacToeGame();
        mRestartGameButton.setOnClickListener(new RestartClickListener());
        startNewGame();
    }

    private void startNewGame() {
    	
    	mGame.clearBoard();
    	mGameOver = false;
    	mMoveCounter = 0;
    	
    	for (int i = 0; i < mBoardButtons.length; i++ ){
    		mBoardButtons[i].setText(" ");
    		mBoardButtons[i].setEnabled(true);
    		mBoardButtons[i].setOnClickListener(new ButtonClickListener(i));
    	}
    	
    	mInfoTextView.setText(R.string.Player_1_Turn);
    	mPlayer = mGame.PLAYER_1;
    }
    
    
    private class RestartClickListener implements View.OnClickListener {
    	public RestartClickListener(){}
    	public void onClick(View view){
    		startNewGame();
    	}
    }
    
    
    private class ButtonClickListener implements View.OnClickListener {
    	int location;
    	
    	public ButtonClickListener(int location){
    		this.location = location;
    	}
    	
    	public void onClick(View view){
    		if (!mGameOver){
    			if (mBoardButtons[location].isEnabled()){
    				
    				// make move
    				setMove(mPlayer,location);
    				++ mMoveCounter;
    				mInfoTextView.setText("Player" + Integer.toString(mPlayer) +"Made a move");
    				
    				// check for win
    				if (mGame.checkForWin(mPlayer)){  // if player wins, game over
    					if (mPlayer == mGame.PLAYER_1)
    						mInfoTextView.setText(R.string.Player_1_Wins);
    					if (mPlayer == mGame.PLAYER_2)
    						mInfoTextView.setText(R.string.Player_2_Wins);
    					mGameOver = true;
    				}
    				else if (mMoveCounter == 9) { // if no winner but board is full, game ends with tie
    					mInfoTextView.setText(R.string.It_is_a_Tie);
    					mGameOver = true;
    				}
    				else {  // if no winner and board is not full, switch turn and game continues
    					if (mPlayer == mGame.PLAYER_1){
    						mInfoTextView.setText(R.string.Player_2_Turn);
    						mPlayer = mGame.PLAYER_2;
    					}
    						
    					else{
    			    		mInfoTextView.setText(R.string.Player_1_Turn);
    		    			mPlayer = mGame.PLAYER_1;
    					}
    				}
    					
    			}
    			
    		}
    		
    	}//onClick
    	
    }//ButtonCLickListener
    
    private void setMove(int player, int location){
    	mGame.setMove(player, location/mGame.get_Board_Dim(), location%mGame.get_Board_Dim());
    	mBoardButtons[location].setEnabled(false);
    	mBoardButtons[location].setText(String.valueOf(mGame.get_Player_Mark(player)));
    	if (player == mGame.PLAYER_1)
    		mBoardButtons[location].setTextColor(Color.GREEN);
    	else
    		mBoardButtons[location].setTextColor(Color.RED);
    }
    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    
}

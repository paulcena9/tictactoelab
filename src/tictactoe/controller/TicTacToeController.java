package tictactoe.controller;

import java.util.Iterator;

import gamecore.GameEngine;
import gamecore.datastructures.vectors.Vector2i;
import gamecore.input.InputManager;
import tictactoe.AI.ITicTacToeAI;
import tictactoe.AI.TicTacToeAI;
import tictactoe.model.ITicTacToeBoard;
import tictactoe.model.PieceType;
import tictactoe.model.Player;
import tictactoe.model.TicTacToeBoard;
import tictactoe.model.TicTacToeEvent;
import tictactoe.view.ITicTacToeView;
import tictactoe.view.TicTacToeView;

/**
 * Controls a Tic Tac Toe game.
 * @author Dawn Nye
 * @author Jonny Bachman
 */
public class TicTacToeController implements ITicTacToeController
{
	/**
	 * Creates a new Tic Tac Toe controller with two human players.
	 * @param width The width of the game board.
	 * @param height The height of the game board.
	 * @param win_len The required combo length to have a winning position.
	 */
	public TicTacToeController(int width, int height, int win_len)
	{
		this(width,height,win_len,true,0,true,0);
		return;
	}
	
	/**
	 * Creates a new Tic Tac Toe controller.
	 * @param width The width of the game board.
	 * @param height The height of the game board.
	 * @param win_len The required combo length to have a winning position.
	 * @param player1_human If true, then the first player is human. If false, the first player is an AI.
	 * @param ai1_difficulty If player 1 is an AI, this is its difficulty.
	 * @param player2_human If true, then the second player is human. If false, the second player is an AI.
	 * @param ai1_difficulty If player 2 is an AI, this is its difficulty.
	 */
	public TicTacToeController(int width, int height, int win_len, boolean player1_human, int ai1_difficulty, boolean player2_human, int ai2_difficulty)
	{
		Width = width;
		Height = height;
		WinningLength = win_len;
		Boolean player = true;
		
		IsPlayerOneHuman = player1_human;
		
		if(ActivePlayer() == Player.CROSS)
			PlayerOneAI = new TicTacToeAI(Player.CIRCLE,0);
		
		IsPlayerTwoHuman = player2_human;
		
		if(ActivePlayer() == Player.CIRCLE)
			PlayerTwoAI = new TicTacToeAI(Player.CROSS,0);
		return;
	}
	
	public void Initialize()
	{
		// Create the board
		Model = new TicTacToeBoard(Width,Height,WinningLength);
		Model.Subscribe(this);
		
		// Create the view
		View = new TicTacToeView(Width,Height,WinningLength);
		
		// Initialize the game state
		ActivePlayer = Player.CROSS;
		Initialized = true;
		return;
	}
	
	public boolean Initialized()
	{return Initialized;}
	
	public void Update(long delta)
	{
		if(Model.IsFinished()) {
			Iterable<Vector2i> winner = Model.WinningSet();
			Iterator<Vector2i> itr = winner.iterator();
			if(winner != null) {
				while(itr.hasNext()) {
					View.MakeGolden(itr.next());
				}
			}
		
		}
		// Update our input manager
		Input = GameEngine.Game().GetService(InputManager.class);
		
		if(Input.GracelessInputSatisfied("Exit")){
			View = null;
		}
		else if(Input.GracelessInputSatisfied("Reset")) {
			View.Clear();
			Model.Clear();
		}
		else if(Input.GracelessInputSatisfied("Right")) {
		View.MoveCursor(new Vector2i(1,0));
		}
		else if(Input.GracelessInputSatisfied("Left")) {
			View.MoveCursor(new Vector2i(-1,0));
		}
		else if(Input.GracelessInputSatisfied("Down")) {
			View.MoveCursor(new Vector2i(0,1));
		}
		else if(Input.GracelessInputSatisfied("Up")) {
			View.MoveCursor(new Vector2i(0,-1));
		}
		else if(Input.GracelessInputSatisfied("Select")) {
			if(Model.IsCellEmpty(View.CursorPosition())) {
				if(ActivePlayer() == Player.CROSS) {
					View.PlacePiece(View.CursorPosition(), PieceType.CROSS);
					Model.Set(PieceType.CROSS, View.CursorPosition());
					ActivePlayer = Player.CIRCLE;
				}
				else if(ActivePlayer() == Player.CIRCLE) {
					View.PlacePiece(View.CursorPosition(), PieceType.CIRCLE);
					Model.Set(PieceType.CIRCLE, View.CursorPosition());
					ActivePlayer = Player.CROSS;
				}
			}
		}

		
		// Animate victory if necessary
		
		
		// Handle AI logic before human selections so that we have at least one frame after a human selection (if any humans exist) before the AI makes its move
		TicTacToeAI PlayerAI = null;
		if(ActivePlayer() == Player.CROSS) {
		PlayerAI = new TicTacToeAI(Player.CROSS,0);}
		else{
			PlayerAI = new TicTacToeAI(Player.CROSS,0);}
		
		Vector2i nextmove = PlayerAI.GetNextMove(Model);
		if(nextmove == null) {return;}
		if(ActivePlayer() == Player.CROSS) {
			View.PlacePiece(nextmove, PieceType.CROSS);
			Model.Set(PieceType.CROSS, nextmove);
			ActivePlayer = Player.CIRCLE;
		}
		else if(ActivePlayer() == Player.CIRCLE) {
			View.PlacePiece(nextmove, PieceType.CIRCLE);
			Model.Set(PieceType.CIRCLE, View.CursorPosition());
			ActivePlayer = Player.CROSS;
		}
		// This frame is important because the AI may lag the game, and the human will want to see their move
		
		
		// Now process selections (we do this after victory animation so that we don't skip a frame in the animation)
		
		
		return;
	}
	
	public void Dispose()
	{
		if(Disposed())
			return;
		
		if(!View.Disposed())
			View.Dispose();
		
		Disposed = true;
		return;
	}
	
	public boolean Disposed()
	{return Disposed;}
	
	public void OnNext(TicTacToeEvent event)
	{
		
		
		return;
	}
	
	public void OnError(Exception e)
	{return;}
	
	public void OnCompleted()
	{return;}
	
	public void ResetGame()
	{
		
		
		return;
	}
	
	public Player ActivePlayer()
	{return ActivePlayer;}
	
	public int Width()
	{return Width;}
	
	public int Height()
	{return Height;}
	
	public int WinningLength()
	{return WinningLength;}
	
	/**
	 * The Tic Tac Toe board.
	 */
	protected ITicTacToeBoard Model;
	
	/**
	 * The Tic Tac Toe view.
	 */
	protected ITicTacToeView View;
	
	/**
	 * The input manager for the game.
	 * This is registered as a service.
	 */
	protected InputManager Input;
	
	/**
	 * The width of the board.
	 */
	protected int Width;
	
	/**
	 * The height of the board.
	 */
	protected int Height;
	
	/**
	 * The winning length of the board.
	 */
	protected int WinningLength;
	
	/**
	 * If true, then player 1 (the cross player) is human controlled.
	 */
	protected boolean IsPlayerOneHuman;
	
	/**
	 * If player 1 is controlled by an AI, this is it.
	 */
	protected TicTacToeAI PlayerOneAI;
	
	/**
	 * If true, then player 2 (the circle player) is human controlled.
	 */
	protected boolean IsPlayerTwoHuman;
	
	/**
	 * If player 2 is controlled by an AI, this is it.
	 */
	protected TicTacToeAI PlayerTwoAI;
	
	/**
	 * The active player (if any).
	 */
	protected Player ActivePlayer;
	
	/**
	 * The amount of time delay between goldenizations upon victory.
	 */
	protected final long GoldenLag = 100;
	
	/**
	 * If true, then this component has been initialized.
	 */
	protected boolean Initialized;
	
	/**
	 * If true, then this component has been disposed.
	 */
	protected boolean Disposed;
}

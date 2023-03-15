package tictactoe.AI;

import java.util.ArrayList;

import java.util.List;

import gamecore.datastructures.vectors.Vector2i;
import tictactoe.model.TicTacToeBoard;
import tictactoe.model.ITicTacToeBoard;
import tictactoe.model.PieceType;
import tictactoe.model.Player;

public class TicTacToeAI implements ITicTacToeAI{
	public Player player;
	public int level;
	public Player minplayer;
	
	public TicTacToeAI(Player playa, int difficulty) {
		player = playa;
		level = difficulty;
		if (playa.equals(Player.CROSS)){
			minplayer = Player.CIRCLE;
		}
		else {minplayer = Player.CROSS;}
	}
	

@Override
	public Vector2i GetNextMove(ITicTacToeBoard board) {
		ITicTacToeBoard clone = board.Clone();
		Vector2i move = minimax(clone,player, minplayer);
		return move;
	}

@Override
	public Player GetPlayer() {
		// TODO Auto-generated method stub
		return player;
	}
	 public static Vector2i minimax(ITicTacToeBoard board, Player maximizingPlayer, Player minimizingPlayer) {
	        Vector2i bestMove = null;
	        int bestScore = Integer.MIN_VALUE;
	        Iterable<Vector2i> openspot = board.IndexSet(true);
	        for (Vector2i position : openspot) {
	                int score = min(board, maximizingPlayer, minimizingPlayer);
	                board.Set(PieceType.NONE, position);
	                if (score > bestScore) {
	                    bestScore = score;
	                    bestMove = position;
	                }
	            }
	        
	        return bestMove;
	    }

	    private static int max(ITicTacToeBoard board, Player maximizingPlayer, Player minimizingPlayer) {
	      PieceType holder = PieceType.NONE;
	      if (maximizingPlayer.equals(Player.CROSS)){
				holder = PieceType.CROSS;
			}
			else {holder = PieceType.CIRCLE;}
		
	    	if (board.IsFinished()) {
	            Player victor = board.Victor();
	            if (victor == maximizingPlayer) {
	                return 1;
	            } else if (victor == minimizingPlayer) {
	                return -1;
	            } else {
	                return 0;
	            }
	        }
	        int bestScore = Integer.MIN_VALUE;
	        Iterable<Vector2i> openspot = board.IndexSet(true);
	        for (Vector2i position : openspot) {
	                board.Set(holder, position);
	                int score = min(board, maximizingPlayer, minimizingPlayer);
	                board.Set(PieceType.NONE, position);
	                if (score > bestScore) {
	                    bestScore = score;
	                }
	            }
	        return bestScore;
	    }

	    private static int min(ITicTacToeBoard board, Player maximizingPlayer, Player minimizingPlayer) {
	        PieceType holder = PieceType.NONE;
		      if (minimizingPlayer.equals(Player.CROSS)){
					holder = PieceType.CROSS;
				}
				else {holder = PieceType.CIRCLE;}
	    	if (board.IsFinished()) {
	            Player victor = board.Victor();
	            if (victor == maximizingPlayer) {
	                return 1;
	            } else if (victor == minimizingPlayer) {
	                return -1;
	            } else {
	                return 0;
	            }
	        }
	        int bestScore = Integer.MAX_VALUE;
	        Iterable<Vector2i> openspot = board.IndexSet(true);
	        for (Vector2i position : openspot) {
	                board.Set(holder, position);
	                int score = max(board, maximizingPlayer, minimizingPlayer);
	                board.Set(PieceType.NONE, position);
	                if (score < bestScore) {
	                    bestScore = score;
	                }
	            }
	        return bestScore;
	    }

}




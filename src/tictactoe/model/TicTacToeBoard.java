package tictactoe.model;

import java.util.NoSuchElementException;
import gamecore.datastructures.grids.FixedSizeGrid;
import java.util.ArrayList;
import java.util.List;

import gamecore.datastructures.vectors.Vector2i;
import gamecore.observe.IObserver;
import gamecore.datastructures.grids.FixedSizeGrid;

public class TicTacToeBoard implements ITicTacToeBoard{

	/**
	 * Board constructor
	 * @author jonny
	 *
	 */
		public int width;
		public int height;
		public int k;
		public int size;
		public int count;
		public PieceType[][] matrix;
		
		/**
	     * Constructs a new Board object with the specified attributes and initializes the matrix.
	     * 
	     * @param width the width of the board
	     * @param height the height of the board
	     * @param count the count of the board
	     * @param k the number of connected cells needed to win the game
	     */
		public TicTacToeBoard(int width, int height, int k) {
	        this.width = width;
	        this.height = height;
	        this.size = width * height;
	        this.k = k;
	        this.matrix = new PieceType[width][height];
	        for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    matrix[i][j] = PieceType.NONE;
                }
		}
		 
	    }

	
	//How to access

	/**
	 * Gets the item at {@code index}.
	 * @param index The index to retrieve.
	 * @return Returns the item at {@code index}.
	 * @throws IndexOutOfBoundsException Thrown if {@code index} is out of bounds.
	 * @throws NoSuchElementException Thrown if no element exists at ({@code x},{@code y}).
	 * @throws NullPointerException Thrown if {@code index} is null.
	 */
	@Override
	public PieceType Get(Vector2i index) {
		int widthholder = index.X;
		int heightholder = index.Y;
		PieceType data = this.matrix[widthholder][heightholder];
		return data;
	}

	/**
	 * Sets the item at {@code index} to {@code t}.
	 * @param t The item to place at {@code index}.
	 * @param index The index to place {@code t} in.
	 * @return Returns the value placed into the grid so that this can be used like an assignment operator like a civilized language.
	 * @throws IndexOutOfBoundsException Thrown if {@code index} is out of bounds.
	 * @throws NullPointerException Thrown {@code index} is null or if {@code t} is null and the implementing class does not permit null entries.
	 */
	@Override
	public PieceType Set(PieceType t, Vector2i index) {
		int widthholder = index.X;
		int heightholder = index.Y;
	    this.matrix[widthholder][heightholder] = t;
		return t;
	}

	/**
	 * Removes the item (if any) at {@code index}.
	 * @param index The index to obliterate.
	 * @return Returns true if this grid was modified as a result of this call.
	 * @throws IndexOutOfBoundsException Thrown if {@code index} is out of bounds.
	 * @throws NullPointerException Thrown if {@code index} is null.
	 */
	@Override
	public boolean Remove(Vector2i index) {
		int widthholder = index.X;
		int heightholder = index.Y;
		if(IsCellOccupied(index)){
		this.matrix[widthholder][heightholder] = PieceType.NONE;
		return true;}
		else return false;
	}

	/**
	 * Determines if the cell at {@code index} is occupied.
	 * @param index The cell to check for occupation.
	 * @return Returns true if the cell is occupied and false otherwise.
	 * @throws IndexOutOfBoundsException Thrown in {@code index} is out of bounds.
	 * @throws NullPointerException Thrown if {@code index} is null.
	 */
	@Override
	public boolean IsCellOccupied(Vector2i index) {
		if(this.matrix[index.X][index.Y] != PieceType.NONE) {
		return true;}
		else {return false;}
	}

	/**
	 * Determines if the cell at {@code index} is vacant.
	 * @param index The cell to check for vacancy.
	 * @return Returns true if the cell is vacant and false otherwise.
	 * @throws IndexOutOfBoundsException Thrown in {@code index} is out of bounds.
	 * @throws NullPointerException Thrown if {@code index} is null.
	 */
	@Override
	public boolean IsCellEmpty(Vector2i index) {
		int widthholder = index.X;
		int heightholder = index.Y;
		if(matrix[widthholder][heightholder].equals(PieceType.NONE)) {
		return true;}
		else {return false;}
	}
	
 
	/**
	 * Returns an enumerable set of all items in the entire grid.
	 * @return Returns an enumeration of all the items in the entire grid.
	 */
	@Override
	public Iterable<PieceType> Items() {
	    List<PieceType> items = new ArrayList<>();
	    for (int i = 0; i < height; i++) {
	        for (int j = 0; j < width; j++) {
	            items.add(matrix[i][j]);
	        }
	    }
	    return items;
	}

	/**
	 * Returns an enumerable set of indices for the entire grid. 
	 * @return Returns an enumerable set containing all indicies in the grid.
	 */
	@Override
	public Iterable<Vector2i> IndexSet() {
	    List<Vector2i> items = new ArrayList<>();
	    for (int i = 0; i < height; i++) {
	        for (int j = 0; j < width; j++) {
	        	Vector2i index = new Vector2i(i,j);
	            items.add(index);
	        }
	    }
	    return items;
	}

	/**
	 * Returns an enumerable set of indices for the entire grid.
	 * @param nonempty If true, returns only nonempty indices.
	 * @return Returns an enumerable set containing all indicies in the grid.
	 */
	@Override
	public Iterable<Vector2i> IndexSet(boolean nonempty) {
	    List<Vector2i> items = new ArrayList<>();
	    for (int i = 0; i < this.height; i++) {
	        for (int j = 0; j < this.width; j++) {
	        	Vector2i index = new Vector2i(i,j);
	        	
	        	if(nonempty) {
	        	if(IsCellOccupied(index)){
	            items.add(index);
	        }}
	        	else if(!nonempty) {
	        		items.add(index);
	        	}
	    }}
	    return items;
	}

	/**
	 * Returns an enumerable set of neighbors of {@code index}.
	 * @param index The index whose neighbors we want to obtain.
	 * @return Returns an enumerable set of neighbors of {@code index}.
	 * @throws IndexOutOfBoundsException Thrown if {@code index} is out of bounds.
	 * @throws NullPointerException Thrown if {@code index} is null.
	 */
	@Override
	public Iterable<PieceType> Neighbors(Vector2i index) {
		 if (index == null) {
		        throw new NullPointerException("Index cannot be null.");
		    }
		    if (index.X < 0 || index.X >= Width() || index.Y < 0 || index.Y >= Height()) {
		        throw new IndexOutOfBoundsException("Index is out of bounds.");
		    }
		    List<PieceType> neighbors = new ArrayList<>();
		    for (int i = -1; i <= 1; i++) {
		        for (int j = -1; j <= 1; j++) {
		            if (i == 0 && j == 0) {
		                continue;
		            }
		            Vector2i neighborIndex = new Vector2i(index.X + i, index.Y + j);
		            if (neighborIndex.X >= 0 && neighborIndex.X < (this.Height()-1) && neighborIndex.Y >= 0 && neighborIndex.Y < (this.Width()-1)) {
		                    neighbors.add(Get(neighborIndex));
		                }
		        }
		    }
		    return neighbors;
	}

	/**
	 * Returns an enumerable set of neightbors of {@code index}.
	 * @param index The index to obtain the neighbors of.
	 * @return Returns an enumerable set containing all indicies adjacent to {@code index}.
	 * @throws IndexOutOfBoundsException Thrown if {@code index} is out of bounds.
	 * @throws NullPointerException Thrown if {@code index} is null.
	 */
	@Override
	public Iterable<Vector2i> NeighborIndexSet(Vector2i index) {
		 if (index == null) {
		        throw new NullPointerException("Index cannot be null.");
		    }
		    if (index.X < 0 || index.X >= this.Height() || index.Y < 0 || index.Y >= this.Width()) {
		        throw new IndexOutOfBoundsException("Index is out of bounds.");
		    }
		    List<Vector2i> neighborsindex = new ArrayList<>();
		    for (int i = -1; i <= 1; i++) {
		        for (int j = -1; j <= 1; j++) {
		            if (i == 0 && j == 0) {
		                continue;
		            }
		            Vector2i neighborIndex = new Vector2i(index.X + i, index.Y + j);
		            if (neighborIndex.X >= 0 && neighborIndex.X < (this.Height()-1) && neighborIndex.Y >= 0 && neighborIndex.Y < (this.Width()-1)) {
		                    neighborsindex.add(neighborIndex);
		                }
		            
		        }
		    }
		    return neighborsindex;
	}
	

	/**
	 * Returns an enumerable set of neightbors of {@code index}.
	 * @param index The index to obtain the neighbors of.
	 * @param nonempty If true, returns only nonempty indices.
	 * @return Returns an enumerable set containing all indicies adjacent to {@code index}.
	 * @throws IndexOutOfBoundsException Thrown if {@code index} is out of bounds.
	 * @throws NullPointerException Thrown if {@code index} is null.
	 */
	@Override
	public Iterable<Vector2i> NeighborIndexSet(Vector2i index, boolean nonempty) {
		 if (index == null) {
		        throw new NullPointerException("Index cannot be null.");
		    }
		    if (index.X < 0 || index.X >= Width() || index.Y < 0 || index.Y >= Height()) {
		        throw new IndexOutOfBoundsException("Index is out of bounds.");
		    }
		    List<Vector2i> neighborsindex2 = new ArrayList<>();
		    for (int i = -1; i <= 1; i++) {
		        for (int j = -1; j <= 1; j++) {
		            if (i == 0 && j == 0) {
		                continue;
		            }
		            Vector2i neighborIndex = new Vector2i(index.X + i, index.Y + j);
		            if (neighborIndex.X >= 0 && neighborIndex.X < (this.Height()-1) && neighborIndex.Y >= 0 && neighborIndex.Y < (this.Width()-1)) {
		                if(nonempty) {
		            	if (IsCellOccupied(neighborIndex)) {
		                    neighborsindex2.add(neighborIndex);
		                }}
		                else if(!nonempty) { neighborsindex2.add(neighborIndex);}
		            }
		        }
		    }
		    return neighborsindex2;
	}
	

	/**
	 * Determines if the given index lise on this grid.
	 * @param index The index to check.
	 * @return Returns true if the index is in bounds and false otherwise.
	 * @throws NullPointerException Thrown if {@code index} is null.
	 */
	
	@Override
	public boolean ContainsIndex(Vector2i index) {
		 if (index == null) {
		        throw new NullPointerException("Index cannot be null.");
		    }
		    if (index.X < 0 || index.X > (this.Height()-1) || index.Y < 0 || index.Y > (this.Width() -1)) {
		        return false;
		    }
		    
		return true;
	}

	/**
	 * Clears the grid (if able).
	 * @return Returns true if the grid was cleared and false otherwise.
	 */
	//Dont know why this doesnt work
	@Override
	public boolean Clear() {
		for(int i = 0; i < Width() ;i++) {
			for(int j = 0; j < Height(); j++) {
				Vector2i indexclear = new Vector2i(i,j);
				this.Remove(indexclear);	
			}
			
		}
		return true;
	}

	/**
	 * Determines the number of items stored in this grid.
	 * @return Returns the number of items stored in this grid.
	 */
	@Override
	public int Count() {
	
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				if(!matrix[i][j].equals(PieceType.NONE)) {
					count++;
				}
			}

		}
		return count;}

	/**
	 * Determines the number of cells in this grid.
	 * @return Returns the number of cells in this grid.
	 * If there are infinitely many cells, a negative value is returned.
	 */
	@Override
	public int Size() {
		return size;
	}

	/**
	 * Causes {@code eye} to begin observing this.
	 * Observers are allowed to subscribe multiple times if desired.
	 * Observers are garunteed to be notified in the order of subscription.
	 * @param eye The observer.
	 * @throws NullPointerException Thrown if {@code eye} is null.
	 */
	@Override
	public void Subscribe(IObserver<TicTacToeEvent> eye) {
	}

	/**
	 * Causes {@code eye} to stop observing this.
	 * Only removes at most the first/oldest instance of {@code eye} if subscribed multiple times.
	 * @param eye THe observer.
	 * @throws NullPointerException Thrown if {@code eye} is null.
	 */
	@Override
	public void Unsubscribe(IObserver<TicTacToeEvent> eye) {
		// TODO Auto-generated method stub
	}

	/**
	 * Clones this board.
	 * The new board does not contain any of this boards subscribers to its events.
	 * @return Returns a deep copy of this board.
	 */
	@Override
	public TicTacToeBoard Clone() {
		TicTacToeBoard clone = new TicTacToeBoard(width,height,k);
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				clone.matrix[i][j] = this.matrix[i][j];
				}
			}

		return clone;
	}
	/**
	 * Determines if this game is finished.
	 * A game is finished if no more moves can be made or if a player has at least {@code WinningLength()} number of pieces in a row horizontally, vertically, or diagonally.
	 * @return Returns true if the game is over and false otherwise.
	 */

	@Override
	public boolean IsFinished() {
		//Horizontal
	    for (int i = 0; i < height; i++) {
	        for (int j = 0; j <= width - k; j++) {
	            PieceType firstPiece = matrix[j][i];
	            if (firstPiece == PieceType.NONE) {
	                continue; 
	            }
	            boolean isWinningLine = true;
	            for (int l = 1; l < k; l++) {
	                if (matrix[j + l][i] != firstPiece) {
	                    isWinningLine = false;
	                    break;
	                }
	            }
	            if (isWinningLine) {
	                return true;
	            }
	        }
	    }

	    // Vertical
	    for (int i = 0; i < width; i++) {
	        for (int j = 0; j <= height - k; j++) {
	            PieceType firstPiece = matrix[i][j];
	            if (firstPiece == PieceType.NONE) {
	                continue; 
	            }
	            boolean isWinningLine = true;
	            for (int l = 1; l < k; l++) {
	                if (matrix[i][j + l] != firstPiece) {
	                    isWinningLine = false;
	                    break;
	                }
	            }
	            if (isWinningLine) {
	                return true;
	            }
	        }
	    }

	    // Diagonal
	    for (int i = 0; i <= width - k; i++) {
	        for (int j = 0; j <= height - k; j++) {
	            PieceType firstPiece = matrix[i][j];
	            if (firstPiece == PieceType.NONE) {
	                continue; // Empty cells cannot be winning
	            }
	            boolean isWinningLine = true;
	            for (int l = 1; l < k; l++) {
	                if (matrix[i + l][j + l] != firstPiece) {
	                    isWinningLine = false;
	                    break;
	                }
	            }
	            if (isWinningLine) {
	                return true;
	            }
	        }
	    }

	    // Diagonal
	    for (int i = width - 1; i >= k - 1; i--) {
	        for (int j = 0; j <= height - k; j++) {
	            PieceType firstPiece = matrix[i][j];
	            if (firstPiece == PieceType.NONE) {
	                continue;
	            }
	            boolean isWinningLine = true;
	            for (int l = 1; l < k; l++) {
	                if (matrix[i - l][j + l] != firstPiece) {
	                    isWinningLine = false;
	                    break;
	                }
	            }
	            if (isWinningLine) {
	                return true;
	            }
	        }
	    }

	    // full but no winner chicken dinner
	    for (int i = 0; i < width; i++) {
	        for (int j = 0; j < height; j++) {
	            if (matrix[i][j] == PieceType.NONE) {
	                return false; 
	            }
	        }
	    }
	    return true; 
	}

	/**
	 * Obtains a winning set of positions if one exists.
	 * @return If no one has won, null is returned.
	 * Otherwise, a set of positions representing a winning set for the winning player is returned.
	 * For example, in a 3x3 game with a winning length of 3, the winning player may win with a diagonal, so an example return value would be the set {(0,0),(1,1),(2,2)}. 
	 */
	@Override
	public Iterable<Vector2i> WinningSet() {
	    // Horizontal
	    for (int i = 0; i < width; i++) {
	        for (int j = 0; j <= height - k; j++) {
	            PieceType player = matrix[i][j];
	            if (player.equals(PieceType.NONE)) continue;
	            boolean isWinning = true;
	            for (int l = 1; l < k; l++) {
	                if (matrix[i][j+l] != player) {
	                    isWinning = false;
	                    break;
	                }
	            }
	            if (isWinning) {
	                List<Vector2i> winningSet = new ArrayList<>();
	                for (int l = 0; l < k; l++) {
	                    winningSet.add(new Vector2i(i, j+l));
	                }
	                return winningSet;
	            }
	        }
	    }
	    
	    // Vertical
	    for (int j = 0; j < height; j++) {
	        for (int i = 0; i <= width - k; i++) {
	            PieceType player = matrix[i][j];
	            if (player.equals(PieceType.NONE)) continue;
	            boolean isWinning = true;
	            for (int l = 1; l < k; l++) {
	                if (matrix[i+l][j] != player) {
	                    isWinning = false;
	                    break;
	                }
	            }
	            if (isWinning) {
	                List<Vector2i> winningSet = new ArrayList<>();
	                for (int l = 0; l < k; l++) {
	                    winningSet.add(new Vector2i(i+l, j));
	                }
	                return winningSet;
	            }
	        }
	    }
	    
	    // diagonals
	    for (int i = 0; i <= width - k; i++) {
	        for (int j = 0; j <= height - k; j++) {
	           PieceType player = matrix[i][j];
	            if (player.equals(PieceType.NONE)) continue;
	            boolean isWinning = true;
	            for (int l = 1; l < k; l++) {
	                if (matrix[i+l][j+l] != player) {
	                    isWinning = false;
	                    break;
	                }
	            }
	            if (isWinning) {
	                List<Vector2i> winningSet = new ArrayList<>();
	                for (int l = 0; l < k; l++) {
	                    winningSet.add(new Vector2i(i+l, j+l));
	                }
	                return winningSet;
	            }
	        }
	    }
	    
	    // reverse diagonals
	    for (int i = 0; i <= width - k; i++) {
	    	for (int j = k-1; j < height; j++) {
	    		PieceType player = matrix[i][j];
	    		if (player.equals(PieceType.NONE)) continue;
	    		boolean isWinning = true;
	    		for (int l = 1; l < k; l++) {
	    			if (matrix[i+l][j-l]!= player) {
	    				isWinning = false;
	    				break;
	    			}
	    		}
	    		if (isWinning) {
	    			List<Vector2i> winningSet = new ArrayList<>();
	    			for (int l = 0; l < k; l++) {
	    				winningSet.add(new Vector2i(i+l, j-l));
	    			}
	    			return winningSet;
	    		}
	    	}
	    }
	    return null;}
	
	/**
	 * Obtains a winning set of positions using {@code use_me} if one exists.
	 * @param use_me This must be part of the winning set.
	 * @return If no one has won using {@code use_me}, null is returned.
	 * Otherwise, a set of positions representing a winning set for the winning player is returned.
	 * For example, in a 3x3 game with a winning length of 3, the winning player may win with a diagonal, so an example return value would be the set {(0,0),(1,1),(2,2)}. 
	 */
	@Override
	public Iterable<Vector2i> WinningSet(Vector2i use_me) {
	    PieceType player = matrix[use_me.X][use_me.Y];
	    if (player.equals(PieceType.NONE)) {
	        return null;
	    }
	    for (int i = 0; i < width; i++) {
	        for (int j = 0; j < height; j++) {
	            if (i == use_me.X && j == use_me.Y) {
	                continue;
	            }
	            if (matrix[i][j] == player) {
	                Iterable<Vector2i> winningSet = WinningSet();
	                for (Vector2i pos : winningSet) {
	                    if (pos.equals(use_me)) {
	                        return winningSet;
	                    }
	                }
	            }
	        }
	    }

	    return null;
	}

	/**
	 * Determines the winner of this game.
	 * @return If the game is not finished, {@code NULL} is returned. If the game is a tie, {@code NEITHER} is returned. Otherwise, the winning player is return.
	 */
	@Override
	public Player Victor() {
		List<Vector2i> winner = (List<Vector2i>) WinningSet();
		Vector2i element = winner.get(0);
		if(matrix[element.X][element.Y].equals(PieceType.CIRCLE)) {
			return Player.CIRCLE;
		}
		else if(matrix[element.X][element.Y].equals(PieceType.CROSS)) {
			return Player.CROSS;
		}
		return null;
	}
	/**
	 * Obtains the width of the board.
	 */

	@Override
	public int Width() {
		
		return width;
	}
	/**
	 * Obtains the width of the board.
	 */
	@Override
	public int Height() {
		return height;
	}

	/**
	 * Obtains the number of pieces a player needs in a row to win.
	 */
	@Override
	public int WinningLength() {
		return k;
	}
	/**
	 * 
	 * Gets possible moves on the board.
	 * @return
	 */
	
	public List<Vector2i> GetAvailableMoves()
	{
	    List<Vector2i> moves = new ArrayList<>();
	    
	    for (int row = 0; row < 3; row++)
	    {
	        for (int col = 0; col < 3; col++)
	        {
	            if (matrix[row][col] == null)
	            {
	                moves.add(new Vector2i(row, col));
	            }
	        }
	    }
	    
	    return moves;
	}

}
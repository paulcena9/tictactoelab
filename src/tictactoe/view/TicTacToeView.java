package tictactoe.view;

import java.io.File;

import javax.imageio.ImageIO;

import gamecore.gui.gamecomponents.ImageComponent;
import gamecore.GameEngine;
import gamecore.datastructures.vectors.Vector2d;
import gamecore.datastructures.vectors.Vector2i;
import gamecore.gui.gamecomponents.MultiImageComponent;
import tictactoe.model.PieceType;


public class TicTacToeView implements ITicTacToeView {

	int Width;
	int Height;
	 int k;
	int Size;
	 int count;
	 boolean Dispose;
	 ImageComponent[][] matrix_gridcell;
	MultiImageComponent[][] matrix;
	ImageComponent Cursor;
	Vector2i Cursorpos;
	//Multiimage component is an array of images, and we can select which one ot display and grid cell has transparency
	//gamecore.gui.gamecompenent will let me add by index
	// mutli image component has a translate
	// game enginge.game will let me place things. Gameenginge.Game().AddComponent();

	public TicTacToeView (int width, int height, int winningscore){
		Dispose = false;
		Width = width;
		Height = height;
		k = winningscore;
		matrix_gridcell = new ImageComponent[width][height];
		matrix = new MultiImageComponent[width][height];
		Cursorpos = new Vector2i(0,0);
		Cursor = new ImageComponent(new File ("assets/images/Selection.png"));
		Cursor.SetDimensions(160,160);
		for (int i = 0; i < width; i++) {
			for(int j = 0; j < height; j++) {
				matrix_gridcell[i][j] = new ImageComponent(new File("assets/images/GridCell.png")); 
				matrix[i][j] = new MultiImageComponent();
				matrix[i][j].AddImage(new File("assets/images/Golden Circle.png")); 
				matrix[i][j].AddImage(new File("assets/images/Golden Cross.png")); 
				matrix[i][j].AddImage(new File("assets/images/Cross.png")); 
				matrix[i][j].AddImage(new File("assets/images/Circle.png")); 
				matrix[i][j].AddImage(new File ("assests/images/Selection.png"));
				GameEngine.Game().AddComponent(matrix_gridcell[i][j]);
				GameEngine.Game().AddComponent(matrix[i][j]);
				matrix_gridcell[i][j].Translate(168 * i, 168 * j);
				matrix[i][j].Translate(168 * i, 168 * j);
			} 
		}
		GameEngine.Game().AddComponent(Cursor);
		Cursor.Translate(2,2);
	}


	/**
	 * Places a piece on the board in cell {@code pos}.
	 * @param pos The cell of the board to place the piece.
	 * @param piece The piece to place. To clear the cell, use NONE.
	 * @throws NullPointerException Thrown if {@code pos} or {@code piece} is null.
	 * @throws Index out of bounds exception if pos is out of bounds.
	 */
	@Override
	public void PlacePiece(Vector2i pos, PieceType piece) {
		if (pos == null) {
			throw new NullPointerException("Pos cannot be null.");
		}
		if (piece == null) {
			throw new NullPointerException("Piece cannot be null.");
		}
		if (pos.X < 0 || pos.X >= this.Height() || pos.Y < 0 || pos.Y >= this.Width()) {
			throw new IndexOutOfBoundsException("Index is out of bounds.");
		}
		int widthholder = pos.X;
		int heightholder = pos.Y;
		if(piece.equals(PieceType.CROSS)) {
			matrix[widthholder][heightholder].SetSelectedImage(2);}
		else if(piece.equals(PieceType.CIRCLE)) {
			matrix[widthholder][heightholder].SetSelectedImage(3);}
		else if(piece.equals(PieceType.NONE)) {
			return;
		}

	}

	/**
	 * Makes the piece in cell {@code pos} golden.
	 * @param pos The cell of the board containing the piece to make golden.
	 * @return Returns true if a piece was made golden and false otherwise. This should only fail if no piece is in the cell or if the piece is already golden.
	 * @throws NullPointerException Thrown if {@code pos} is null.
	 */
	@Override
	public boolean MakeGolden(Vector2i pos) {
		if (pos == null) {
			throw new NullPointerException("Pos cannot be null.");}
		int widthholder = pos.X;
		int heightholder = pos.Y;
		if(matrix[widthholder][heightholder] == null) {
			return false;
		}
		int piece = matrix[widthholder][heightholder].GetSelectedImage();
		if(piece == 0 || piece == 1) {
			return false;
		}
		else if(piece == 2) {
			matrix[widthholder][heightholder].SetSelectedImage(1);
			return true;}
		else if(piece == 3) {
			matrix[widthholder][heightholder].SetSelectedImage(0);
			return true;}

		return false;
	}

	/**
	 * Clears the board by removing all pieces from its cells.
	 */
	@Override
	public void Clear() {
		for(int i = 0; i < this.Width() ;i++) {
			for(int j = 0; j < this.Height(); j++) {
				matrix[i][j].Hide();
			}}

	}

	/**
	 * Moves the cursor in the direciton {@code dir}.
	 * The cursor can only move one square at a time (diagonals count as one square).
	 * If the cursor would move diagonally off the board but can move a space horizontally or vertically, the latter movement will occur.
	 * @param dir The direction to move the cursor. Only the sign of each of its values will be used, not its magnitude.
	 * @return Returns true if the cursor moved (if not as far as originally intended) and false otherwise, such as if it attempted to move out of bounds.
	 * @throws NullPointerException Thrown if {@code dir} is null.
	 */
	@Override
	public boolean MoveCursor(Vector2i dir) {
		Vector2i newpos = new Vector2i(Cursorpos.X + dir.X, Cursorpos.Y + dir.Y);
		//check in bounds
		if (newpos.X >= 0 && newpos.X < Width && newpos.Y >= 0 && newpos.Y < Height) {
			Cursor.Translate(dir.X * 168,dir.Y * 168);
			Cursorpos = newpos;
			return true;
		}
		return false;

	}


	/**
	 * Removes any components this view added to the game engine from the engine.
	 * This operation cannot be undone.
	 */
	@Override
	public void Dispose() {
		//member resource dispose
		Dispose = true;
		return;
	}

	/**
	 * If true, then this view has been disposed. Returns false otherwise.
	 */
	@Override
	public boolean Disposed() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Obtains the current cursor position.
	 */
	@Override
	public Vector2i CursorPosition() {
		return Cursorpos;
	}

	/**
	 * Obtains the width of the game board (in cells).
	 */
	@Override
	public int Width() {
		// TODO Auto-generated method stub
		return Width;
	}

	/**
	 * Obtains the height of the game board (in cells).
	 */
	@Override
	public int Height() {
		// TODO Auto-generated method stub
		return Height;
	}

}

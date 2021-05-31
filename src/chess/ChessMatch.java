package chess;

import boardGame.Board;
import boardGame.Piece;
import boardGame.Position;
import chess.piece.King;
import chess.piece.Rook;

public class ChessMatch {
	
	private Board board;
	
	public ChessMatch(){
		board = new Board(8,8);
		initialSetUp();
	
	}
	public ChessPiece [][] getPieces(){
		ChessPiece [][] mat = new ChessPiece[board.getRows()][board.getColumns()];
		for(int i = 0; i<board.getRows(); i ++){
			for(int j = 0; j <board.getColumns(); j++){
				mat [i][j] = (ChessPiece) board.piece(i,j);
			}
		}
		return mat;
	}
	
	public ChessPiece perfomChessMove(ChessPosition sourcePosition, ChessPosition targetposition) {
		Position source = sourcePosition.toPosition();
		Position target = targetposition.toPosition();
		ValidateSourcePosition(source);
		ValidateTargetPosition(source, target);
		Piece capturedPiece = makeMove(source, target);
		return (ChessPiece)capturedPiece;
	}
	private Piece makeMove(Position source, Position target){
		Piece p = board.removePiece(source);
		Piece capturedPiece = board.removePiece(target);
		board.placePiece(p, target);
		return capturedPiece;
	}
	
	private void ValidateSourcePosition(Position position) {
		if(!board.thereIsAPiece(position)){
			throw new ChessException("There is  no piece on source position");
		}
		if(!board.piece(position).isThereAnyPossibleMovie()){
			throw new ChessException("There is no possible moves for the choosen piece");
		}
	}
	
	private void ValidateTargetPosition(Position source, Position target){
		if(!board.piece(source).possibleMovie(target)){
			throw new ChessException("The choosen piece can't  move to target position");
		}
	}
	
	private void placeNewPiece(char column, int row, ChessPiece piece) {
		board.placePiece(piece, new ChessPosition(column, row).toPosition());
	}
	private void initialSetUp() {
		placeNewPiece('c', 1, new Rook(board, Color.White));
		placeNewPiece('c', 2, new Rook(board, Color.White));
		placeNewPiece('d', 2, new Rook(board, Color.White));
		placeNewPiece('e', 2, new Rook(board, Color.White));
		placeNewPiece('e', 1, new Rook(board, Color.White));
		placeNewPiece('d', 1, new King(board, Color.White));
		
		placeNewPiece('c', 7, new Rook(board, Color.BLACK));
		placeNewPiece('c', 8, new Rook(board, Color.BLACK));
		placeNewPiece('d', 7, new Rook(board, Color.BLACK));
		placeNewPiece('e', 7, new Rook(board, Color.BLACK));
		placeNewPiece('e', 8, new Rook(board, Color.BLACK));
		placeNewPiece('d', 8, new King(board, Color.BLACK));
	}
}

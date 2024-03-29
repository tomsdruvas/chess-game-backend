package com.lazychess.chessgame.chessgame;

import static com.lazychess.chessgame.repository.mapper.CustomLegalSquareMapper.fromSquareToLegalMove;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.apache.commons.collections4.ListUtils;

import com.lazychess.chessgame.repository.mapper.CustomLegalSquareMapper;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class King extends Piece implements CastlingHasMoved {

    private boolean hasMoved;

    public King(String name, int row, int column, String colour) {
        super(name, row, column, colour);
        this.hasMoved = false;
    }

    @Override
    public void generateLegalMoves(Square[][] squares) {
        List<LegalMoveSquare> legalMoves = Arrays.stream(squares).flatMap(Arrays::stream)
            .filter(this::filterSquaresWithSameColourPiece)
            .filter(this::kingLegalMoves)
            .map(CustomLegalSquareMapper::fromSquareToLegalMove)
            .toList();

        List<LegalMoveSquare> castlingMoves = generateCastlingSquares(squares);
        List<LegalMoveSquare> legalMoveWithCastlingMoves = ListUtils.union(legalMoves, castlingMoves);
        setLegalMoves(legalMoveWithCastlingMoves);
    }

    private boolean kingLegalMoves(Square square) {

        boolean a = Math.abs(getPieceRow() - square.getRow()) == 1 && Math.abs(getPieceColumn() - square.getColumn()) == 1;
        boolean b = Math.abs(getPieceRow() - square.getRow()) == 1 && Math.abs(getPieceColumn() - square.getColumn()) == 0;
        boolean c = Math.abs(getPieceRow() - square.getRow()) == 0 && Math.abs(getPieceColumn() - square.getColumn()) == 1;

        return a || b || c;
    }

    private List<LegalMoveSquare> generateCastlingSquares(Square[][] squares) {
        List<LegalMoveSquare> castlingMovesToAdd = new ArrayList<>();

        if(getPieceColumn() == 3) {
            if (!getHasMoved() && squares[getPieceRow()][getPieceColumn() - 3].getPiece() instanceof CastlingHasMoved rook && !rook.getHasMoved()) {
                ifSquaresOnTwoSideAreEmptyAddCastlingMovesToKing(squares, castlingMovesToAdd);
            }
            if (!getHasMoved() && squares[getPieceRow()][getPieceColumn() + 4].getPiece() instanceof CastlingHasMoved rook && !rook.getHasMoved()) {
                ifSquaresOnThreeSideAreEmptyAddCastlingMovesToKing(squares, castlingMovesToAdd);
            }
        }
        return castlingMovesToAdd;
    }

    private void ifSquaresOnTwoSideAreEmptyAddCastlingMovesToKing(Square[][] squares, List<LegalMoveSquare> castlingMovesToAdd) {
        if (squares[getPieceRow()][getPieceColumn() - 1].squareEmpty() && squares[getPieceRow()][getPieceColumn() - 2].squareEmpty()) {
            LegalMoveSquare legalMoveSquareOnTwoSide = fromSquareToLegalMove(squares[getPieceRow()][getPieceColumn() - 2]);
            castlingMovesToAdd.add(legalMoveSquareOnTwoSide);
        }
    }

    private void ifSquaresOnThreeSideAreEmptyAddCastlingMovesToKing(Square[][] squares, List<LegalMoveSquare> castlingMovesToAdd) {
        if (squares[getPieceRow()][getPieceColumn() + 1].squareEmpty() && squares[getPieceRow()][getPieceColumn() + 2].squareEmpty() && squares[getPieceRow()][getPieceColumn() + 3].squareEmpty()) {
            LegalMoveSquare legalMoveSquareOnThreeSide = fromSquareToLegalMove(squares[getPieceRow()][getPieceColumn() + 2]);
            castlingMovesToAdd.add(legalMoveSquareOnThreeSide);
        }
    }

    public boolean getHasMoved() {
        return hasMoved;
    }

    public void hasMoved() {
        this.hasMoved = true;
    }

    public void removeCastlingMoves() {
        if(getPieceRow() == 7) {
            removeLegalMove(7,1);
            removeLegalMove(7,5);
        }

        if(getPieceRow() == 0) {
            removeLegalMove(0,1);
            removeLegalMove(0,5);
        }
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof King king)) return false;
        return getPieceRow() == king.getPieceRow() &&
            getPieceColumn() == king.getPieceColumn() &&
            Objects.equals(getName(), king.getName()) &&
            Objects.equals(getColour(), king.getColour()) &&
            getHasMoved() == king.getHasMoved();
    }

    @Override
    public final int hashCode() {
        return Objects.hash(getPieceRow(), getPieceColumn(), getName(), getColour(), getHasMoved());
    }

    @Override
    public String toString() {
        return "King{" +
            "name='" + getName() + '\'' +
            ", colour='" + getColour() + '\'' +
            ", row=" + getPieceRow() +
            ", column=" + getPieceColumn() +
            ", hasMoved=" + hasMoved +
            '}';
    }
}

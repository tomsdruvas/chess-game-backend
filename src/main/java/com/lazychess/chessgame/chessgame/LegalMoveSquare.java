package com.lazychess.chessgame.chessgame;

import java.io.Serializable;
import java.util.Objects;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public final class LegalMoveSquare implements Serializable {

    private int row;
    private int column;
    private Piece piece;

    public LegalMoveSquare(int row, int column, Piece piece) {
        this.row = row;
        this.column = column;
        this.piece = piece;
    }

    public LegalMoveSquare(int row, int column) {
        this.row = row;
        this.column = column;

    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public Piece getPiece() {
        return piece;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LegalMoveSquare that)) return false;

        if (row != that.row) return false;
        return column == that.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }

    @Override
    public String toString() {
        return "LegalMoveSquare{" +
            "row=" + row +
            ", column=" + column +
            ", piece=" + piece +
            '}';
    }
}

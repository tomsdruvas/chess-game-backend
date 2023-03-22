package com.lazychess.chessgame.testUtil;

import com.lazychess.chessgame.chessgame.Piece;

import lombok.Builder;

@Builder
public class ChessMoveNotion {

    private String pieceColour;
    private Class<? extends Piece> pieceClass;
    private boolean shouldTakePiece;
    private boolean shouldEndInCheck;
    private int pieceLocationColumn;
    private int pieceLocationRow;
    private boolean pawnPromotion;
    private Class<? extends Piece> pawnPromotionPieceType;
    private int row;
    private int column;

    public ChessMoveNotion(String pieceColour, Class<? extends Piece> pieceClass, boolean shouldTakePiece, boolean shouldEndInCheck, int pieceLocationColumn, int pieceLocationRow, boolean pawnPromotion, Class<? extends Piece> pawnPromotionPieceType, int row, int column) {
        this.pieceColour = pieceColour;
        this.pieceClass = pieceClass;
        this.shouldTakePiece = shouldTakePiece;
        this.shouldEndInCheck = shouldEndInCheck;
        this.pieceLocationColumn = pieceLocationColumn;
        this.pieceLocationRow = pieceLocationRow;
        this.pawnPromotion = pawnPromotion;
        this.pawnPromotionPieceType = pawnPromotionPieceType;
        this.row = row;
        this.column = column;
    }

    public Class<? extends Piece> getPawnPromotionPieceType() {
        return pawnPromotionPieceType;
    }

    public void setPawnPromotionPieceType(Class<? extends Piece> pawnPromotionPieceType) {
        this.pawnPromotionPieceType = pawnPromotionPieceType;
    }

    public boolean isPawnPromotion() {
        return pawnPromotion;
    }

    public void setPawnPromotion(boolean pawnPromotion) {
        this.pawnPromotion = pawnPromotion;
    }

    public int getPieceLocationRow() {
        return pieceLocationRow;
    }

    public void setPieceLocationRow(int pieceLocationRow) {
        this.pieceLocationRow = pieceLocationRow;
    }

    public int getPieceLocationColumn() {
        return pieceLocationColumn;
    }

    public void setPieceLocationColumn(int pieceLocationColumn) {
        this.pieceLocationColumn = pieceLocationColumn;
    }

    public Class<? extends Piece> getPieceClass() {
        return pieceClass;
    }

    public void setPieceClass(Class<? extends Piece> pieceClass) {
        this.pieceClass = pieceClass;
    }

    public boolean isShouldTakePiece() {
        return shouldTakePiece;
    }

    public void setShouldTakePiece(boolean shouldTakePiece) {
        this.shouldTakePiece = shouldTakePiece;
    }

    public boolean isShouldEndInCheck() {
        return shouldEndInCheck;
    }

    public void setShouldEndInCheck(boolean shouldEndInCheck) {
        this.shouldEndInCheck = shouldEndInCheck;
    }

    public String getPieceColour() {
        return pieceColour;
    }

    public void setPieceColour(String pieceColour) {
        this.pieceColour = pieceColour;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }
}

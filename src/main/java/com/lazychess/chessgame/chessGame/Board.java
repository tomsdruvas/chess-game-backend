package com.lazychess.chessgame.chessGame;

import static com.lazychess.chessgame.chessGame.ChessConstants.BLACK;
import static com.lazychess.chessgame.chessGame.ChessConstants.WHITE;

public class Board {

    private Square[][] squares;
    Piece 
        whiteRook1, whiteRook2,
        whiteKnight1, whiteKnight2,
        whiteBishop1, whiteBishop2,
        whiteQueen, whiteKing, whitePawn[];


    Piece
        blackRook1, blackRook2,
        blackKnight1, blackKnight2,
        blackBishop1, blackBishop2,
        blackQueen, blackKing, blackPawn[];

    public Board() {
        squares = new Square[8][8];
        this.loadSquares();
        this.loadPieces();
    }

    public void loadSquares() {
        boolean squareColour = true;

        for(int i=0;i<8;i++)
        {
            for(int j=0;j<8;j++)
            {
                squares[i][j] = new Square(i,j,squareColour);
                squareColour = !squareColour;
            }
        }

    }

    public void loadPieces() {

        whiteRook1 = new Rook("White Rook1",7,0,WHITE);
        whiteRook2 = new Rook("White Rook2",7,7,WHITE);
        whiteKnight1 = new Knight("WN1",7,1,WHITE);
        whiteKnight2 = new Knight("WN2",7,6,WHITE);
        whiteBishop1 = new Bishop("WB1",7,2,WHITE);
        whiteBishop2 = new Bishop("WB2",7,5,WHITE);
        whiteQueen = new Queen("WQ",7,3,WHITE);
        whiteKing = new King("WK",7,4,WHITE);

        whitePawn = new Piece[8];
        for(int i=0;i<8;i++)
        {
            whitePawn[i] = new Pawn("White Pawn"+(i+1),6 ,i,WHITE);
        }

        blackRook1 = new Rook("Black Rook1",0,0,BLACK);
        blackRook2 = new Rook("Black Rook2",0,7,BLACK);
        blackKnight1 = new Knight("Black Knight1",0,1,BLACK);
        blackKnight2 = new Knight("Black Knight2",0,6,BLACK);
        blackBishop1 = new Bishop("BlackBishop1",0,2,BLACK);
        blackBishop2 = new Bishop("BlackBishop2",0,5,BLACK);
        blackQueen = new Queen("Black Queen",0,3,BLACK);
        blackKing = new King("Black King",0,4,BLACK);

        blackPawn = new Piece[8];
        for(int i=0;i<8;i++)
        {
            blackPawn[i] = new Pawn("Black Pawn"+(i+1),1,i,BLACK);
        }

        squares[7][0].setPiece(whiteRook1);
        squares[7][1].setPiece(whiteKnight1);
        squares[7][2].setPiece(whiteBishop1);
        squares[7][3].setPiece(whiteQueen);
        squares[7][4].setPiece(whiteKing);
        squares[7][5].setPiece(whiteBishop2);
        squares[7][6].setPiece(whiteKnight2);
        squares[7][7].setPiece(whiteRook2);

        for(int i=0;i<8;i++)
        {
            squares[6][i].setPiece(whitePawn[i]);
        }

        squares[0][0].setPiece(blackRook1);
        squares[0][1].setPiece(blackKnight1);
        squares[0][2].setPiece(blackBishop1);
        squares[0][3].setPiece(blackQueen);
        squares[0][4].setPiece(blackKing);
        squares[0][5].setPiece(blackBishop2);
        squares[0][6].setPiece(blackKnight2);
        squares[0][7].setPiece(blackRook2);

        for(int i=0;i<8;i++)
        {
            squares[1][i].setPiece(blackPawn[i]);
        }
    }
}
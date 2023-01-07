package com.lazychess.chessgame.chessGame;

import java.util.ArrayList;

public class Knight extends Piece {

    private String name;
    private int row;
    private int column;
    private String colour;
    private ArrayList<Square> legalMoves;

    public Knight(String name, int row, int column, String colour) {
        this.name = name;
        this.row = row;
        this.column = column;
        this.colour = colour;
    }
}
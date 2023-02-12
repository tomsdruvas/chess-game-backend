package com.lazychess.chessgame.repository.mapper;

import org.springframework.stereotype.Component;

import com.lazychess.chessgame.chessgame.Board;
import com.lazychess.chessgame.repository.entity.BoardDao;

@Component
public class BoardDaoMapper {

    public BoardDao fromBoardObject(Board board) {
        BoardDao boardDao = new BoardDao();
        boardDao.setSquares(board.getSquares());
        boardDao.setStateOfTheGame(board.getStateOfTheGame());
        boardDao.setCurrentPlayerColour(board.getCurrentPlayerColourState());
        return boardDao;
    }
}
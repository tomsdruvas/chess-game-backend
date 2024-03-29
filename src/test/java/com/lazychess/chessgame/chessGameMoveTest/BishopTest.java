package com.lazychess.chessgame.chessGameMoveTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.lazychess.chessgame.chessgame.Bishop;
import com.lazychess.chessgame.chessgame.Board;
import com.lazychess.chessgame.chessgame.ChessGameState;
import com.lazychess.chessgame.chessgame.EmptyPiece;
import com.lazychess.chessgame.chessgame.King;
import com.lazychess.chessgame.chessgame.Pawn;
import com.lazychess.chessgame.chessgame.Piece;
import com.lazychess.chessgame.chessgame.Square;
import com.lazychess.chessgame.dto.ChessMoveRequest;

class BishopTest {

    private Board board;

    @BeforeEach
    public void loadChess() {
        board = new Board();
    }

    @Test
    void allBishopsShouldHaveZeroLegalMovesWhenInitiated() {
        List<Piece> allRooks = Arrays.stream(board.getSquares())
            .flatMap(Arrays::stream)
            .map(Square::getPiece)
            .filter(this::findAllBishopsByTheirStartingPosition)
            .filter(piece -> !(piece instanceof EmptyPiece))
            .toList();

        assertThat(allRooks).hasSize(4).allSatisfy(piece -> {
            assertThat(piece).isExactlyInstanceOf(Bishop.class);
            assertThat(piece.getLegalMoves()).isEmpty();
        });
    }

    @Test
    void afterMovingPawnsOutOfWay_allBishopsShouldHaveTwoLegalMovesWhenInitiated() {
        board.movePiece(6,1,5,1);
        board.movePiece(1,1,2,1);
        board.movePiece(6,6,5,6);
        board.movePiece(1,6,2,6);

        List<Piece> allRooks = Arrays.stream(board.getSquares())
            .flatMap(Arrays::stream)
            .map(Square::getPiece)
            .filter(piece -> piece instanceof Bishop)
            .toList();

        assertThat(allRooks).hasSize(4).allSatisfy(piece -> {
            assertThat(piece.getLegalMoves()).hasSize(2);
        });
    }

    @Test
    void afterMovingPawnsOutOfWay2_allBishopsShouldHaveFiveLegalMovesWhenInitiated() {
        board.movePiece(6,3,4,3);
        board.movePiece(1,4,3,4);
        board.movePiece(6,4,4,4);
        board.movePiece(1,3,3,3);

        List<Piece> allRooks = Arrays.stream(board.getSquares())
            .flatMap(Arrays::stream)
            .map(Square::getPiece)
            .filter(piece -> piece instanceof Bishop)
            .toList();

        assertThat(allRooks).hasSize(4).allSatisfy(piece -> {
            assertThat(piece.getLegalMoves()).hasSize(5);
        });
    }

    @Test
    void afterMovingPawnsOutOfWay2_andTakingOppositePieces_thereShouldBeLessPiecesOnTheBoard() {
        board.movePiece(6,1,5,1);
        board.movePiece(1,3,3,3);
        board.movePiece(6,3,4,3);
        board.movePiece(1,4,3,4);
        board.movePiece(6,4,4,4);

        board.movePiece(0,5,5,0);
        board.movePiece(7,5,2,0);
        board.movePiece(0,2,5,7);
        board.movePiece(7,2,2,7);

        board.movePiece(5,0,6,1);
        board.movePiece(2,0,1,1);
        board.movePiece(5,7,6,6);
        board.movePiece(2,7,1,6);

        List<Piece> allRooks = Arrays.stream(board.getSquares())
            .flatMap(Arrays::stream)
            .map(Square::getPiece)
            .filter(piece -> piece instanceof Bishop)
            .toList();

        List<Piece> allPawns = Arrays.stream(board.getSquares())
            .flatMap(Arrays::stream)
            .map(Square::getPiece)
            .filter(piece -> piece instanceof Pawn)
            .toList();

        assertThat(allRooks).hasSize(4).allSatisfy(piece -> {
            assertThat(piece.getLegalMoves()).hasSize(5);
        });
        assertThat(allPawns).hasSize(13);
    }

    @Test
    void bishopShouldNotBeAbleToPutOwnKingInCheck() {
        List<ChessMoveRequest> preInitChessMoveRequestList = List.of(
            new ChessMoveRequest(1, 4, 2, 4),
            new ChessMoveRequest(0, 5, 2, 5),
            new ChessMoveRequest(7, 2, 4, 5)
        );

        Board board = new Board(preInitChessMoveRequestList);
        board.movePiece(4,5,3,6);

        Piece whiteBishop = board.getSquares()[3][6].getPiece();
        Piece blackBishop = board.getSquares()[2][5].getPiece();
        Piece blackKing= board.getSquares()[0][3].getPiece();

        assertThat(whiteBishop).isExactlyInstanceOf(Bishop.class);
        assertThat(blackBishop).isExactlyInstanceOf(Bishop.class);
        assertThat(blackKing).isExactlyInstanceOf(King.class);

        assertThat(blackBishop.getLegalMoves())
            .noneMatch(square -> square.getRow() == 3 && square.getColumn() == 4)
            .noneMatch(square -> square.getRow() == 4 && square.getColumn() == 3)
            .noneMatch(square -> square.getRow() == 5 && square.getColumn() == 2)
            .noneMatch(square -> square.getRow() == 6 && square.getColumn() == 1)
            .anyMatch(square -> square.getRow() == 3 && square.getColumn() == 6);
    }

    @Test
    void bishopShouldBeAbleToTakeOppositeKingNotCheckMate() {
        List<ChessMoveRequest> preInitChessMoveRequestList = List.of(
            new ChessMoveRequest(7, 5, 5, 5),
            new ChessMoveRequest(0, 3, 2, 4)
        );

        Board board = new Board(preInitChessMoveRequestList);
        board.movePiece(5,5,4,6);


        List<Piece> allPieces = board.getAllPieces();
        assertThat(allPieces)
            .filteredOn(piece -> Objects.equals(piece.getColour(), "black") && !(piece instanceof King) && !(Objects.equals(piece.getName(), "Black Pawn6")))
            .hasSize(14).allSatisfy(piece -> {
                assertThat(piece.getLegalMoves()).isEmpty();
            });

        Piece blackKing = board.getSquares()[2][4].getPiece();
        assertThat(blackKing.getLegalMoves()).hasSize(4)
            .noneMatch(square -> square.getRow() == 3 && square.getColumn() == 5);
        assertThat(blackKing).isExactlyInstanceOf(King.class);

        Piece whiteBishop = board.getSquares()[4][6].getPiece();
        assertThat(whiteBishop)
            .isExactlyInstanceOf(Bishop.class)
            .satisfies(piece -> assertThat(Objects.equals(piece.getColour(), "white")).isTrue())
            .satisfies(piece -> assertThat(piece.getLegalMoves()).anyMatch(square -> square.getRow() == 2 && square.getColumn() == 4));
        assertThat(board.getStateOfTheGame()).isSameAs(ChessGameState.ONGOING);
    }

    @Test
    void bishopShouldBeAbleToTakeOppositeKingCheckMate() {
        List<ChessMoveRequest> preInitChessMoveRequestList = List.of(
            new ChessMoveRequest(0, 3, 4, 0),
            new ChessMoveRequest(7, 7, 4, 7),
            new ChessMoveRequest(6, 0, 5, 7),
            new ChessMoveRequest(7, 5, 5, 3),
            new ChessMoveRequest(6, 2, 2, 7),
            new ChessMoveRequest(7, 2, 6, 2)
        );

        Board board = new Board(preInitChessMoveRequestList);
        board.movePiece(6,6,5,6);

        List<Piece> allPieces = board.getAllPieces();
        assertThat(allPieces)
            .filteredOn(piece -> Objects.equals(piece.getColour(), "black") && !(piece instanceof King))
            .hasSize(15).allSatisfy(piece -> {
                assertThat(piece.getLegalMoves()).isEmpty();
            });

        Piece blackKing = board.getSquares()[4][0].getPiece();
        assertThat(blackKing.getLegalMoves()).isEmpty();
        assertThat(blackKing).isExactlyInstanceOf(King.class);

        Piece whiteBishop = board.getSquares()[6][2].getPiece();
        assertThat(whiteBishop)
            .isExactlyInstanceOf(Bishop.class)
            .satisfies(piece -> assertThat(Objects.equals(piece.getColour(), "white")).isTrue())
            .satisfies(piece -> assertThat(piece.getLegalMoves()).anyMatch(square -> square.getRow() == 4 && square.getColumn() == 0));

        assertThat(board.getStateOfTheGame()).isSameAs(ChessGameState.CHECKMATE);
        assertThatThrownBy(() -> board.movePiece(1,4,2,4))
            .hasMessage("The game is not in the ongoing state");
    }

    private boolean findAllBishopsByTheirStartingPosition(Piece piece) {
        return
            (piece.getPieceRow() == 7 && piece.getPieceColumn() == 5) ||
                (piece.getPieceRow() == 0 && piece.getPieceColumn() == 5) ||
                (piece.getPieceRow() == 7 && piece.getPieceColumn() == 2) ||
                (piece.getPieceRow() == 0 && piece.getPieceColumn() == 2);
    }
}

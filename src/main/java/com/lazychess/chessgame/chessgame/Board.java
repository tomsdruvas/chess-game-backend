package com.lazychess.chessgame.chessgame;

import static com.lazychess.chessgame.chessgame.ChessConstants.BLACK;
import static com.lazychess.chessgame.chessgame.ChessConstants.WHITE;
import static com.lazychess.chessgame.chessgame.ChessGameState.CHECKMATE;
import static com.lazychess.chessgame.chessgame.ChessGameState.ONGOING;
import static com.lazychess.chessgame.chessgame.ChessGameState.STALEMATE;
import static com.lazychess.chessgame.repository.mapper.CustomLegalSquareMapper.fromSquareToLegalMove;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.apache.commons.collections4.ListUtils;
import org.springframework.util.SerializationUtils;

import com.lazychess.chessgame.dto.ChessMoveRequest;
import com.lazychess.chessgame.dto.MovesDto;
import com.lazychess.chessgame.exception.EmptySourceSquareException;
import com.lazychess.chessgame.exception.GameIsNotInOnGoingStateException;
import com.lazychess.chessgame.exception.IllegalMoveException;
import com.lazychess.chessgame.exception.InvalidChessPieceForPawnPromotionException;
import com.lazychess.chessgame.exception.WrongColourPieceOnSquareException;

public class Board implements Serializable {

    private Square[][] squares;
    Piece whiteRook1;
    Piece whiteRook2;
    Piece whiteKnight1;
    Piece whiteKnight2;
    Piece whiteBishop1;
    Piece whiteBishop2;
    Piece whiteQueen;
    Piece whiteKing;
    Piece[] whitePawn;

    Piece blackRook1;
    Piece blackRook2;
    Piece blackKnight1;
    Piece blackKnight2;
    Piece blackBishop1;
    Piece blackBishop2;
    Piece blackQueen;
    Piece blackKing;
    Piece[] blackPawn;

    private ChessGameState stateOfTheGame = ONGOING;
    private String currentPlayerColourState = WHITE;
    private boolean pawnPromotionPending = false;
    private LegalMoveSquare latestMove;

    public Board(List<ChessMoveRequest> chessMoveRequestList) {
        this.squares = new Square[8][8];
        loadSquares();
        loadPieces();
        makePreInitialisationMoves(chessMoveRequestList);
        loadPieceLegalMoves(squares);
        setLatestMove(new LegalMoveSquare(0,0));
    }

    public Board() {
        this.squares = new Square[8][8];
        loadSquares();
        loadPieces();
        loadPieceLegalMoves(squares);
        setLatestMove(new LegalMoveSquare(0,0));
    }

    public void loadSquares() {
        boolean squareColour = true;

        for(int i=0;i<8;i++) {
            for(int j=0;j<8;j++) {
                squares[i][j] = new Square(i,j,squareColour);
                squareColour = !squareColour;
            }
        }

    }

    public void loadPieces() {
        whiteRook1 = new Rook("White Rook1",7,0,WHITE);
        whiteRook2 = new Rook("White Rook2",7,7,WHITE);
        whiteKnight1 = new Knight("White Knight1",7,1,WHITE);
        whiteKnight2 = new Knight("White Knight2",7,6,WHITE);
        whiteBishop1 = new Bishop("White Bishop1",7,2,WHITE);
        whiteBishop2 = new Bishop("White Bishop2",7,5,WHITE);
        whiteQueen = new Queen("White Queen",7,4,WHITE);
        whiteKing = new King("White King",7,3,WHITE);

        whitePawn = new Piece[8];
        for(int i=0;i<8;i++) {
            whitePawn[i] = new Pawn("White Pawn"+(i+1),6 ,i,WHITE);
        }

        blackRook1 = new Rook("Black Rook1",0,0,BLACK);
        blackRook2 = new Rook("Black Rook2",0,7,BLACK);
        blackKnight1 = new Knight("Black Knight1",0,1,BLACK);
        blackKnight2 = new Knight("Black Knight2",0,6,BLACK);
        blackBishop1 = new Bishop("Black Bishop1",0,2,BLACK);
        blackBishop2 = new Bishop("Black Bishop2",0,5,BLACK);
        blackQueen = new Queen("Black Queen",0,4,BLACK);
        blackKing = new King("Black King",0,3,BLACK);

        blackPawn = new Piece[8];
        for(int i=0;i<8;i++) {
            blackPawn[i] = new Pawn("Black Pawn"+(i+1),1,i,BLACK);
        }

        squares[7][0].setPiece(whiteRook1);
        squares[7][1].setPiece(whiteKnight1);
        squares[7][2].setPiece(whiteBishop1);
        squares[7][3].setPiece(whiteKing);
        squares[7][4].setPiece(whiteQueen);
        squares[7][5].setPiece(whiteBishop2);
        squares[7][6].setPiece(whiteKnight2);
        squares[7][7].setPiece(whiteRook2);

        for(int i=0;i<8;i++) {
            squares[6][i].setPiece(whitePawn[i]);
        }

        squares[0][0].setPiece(blackRook1);
        squares[0][1].setPiece(blackKnight1);
        squares[0][2].setPiece(blackBishop1);
        squares[0][3].setPiece(blackKing);
        squares[0][4].setPiece(blackQueen);
        squares[0][5].setPiece(blackBishop2);
        squares[0][6].setPiece(blackKnight2);
        squares[0][7].setPiece(blackRook2);

        for(int i=0;i<8;i++) {
            squares[1][i].setPiece(blackPawn[i]);
        }
    }


    public LegalMoveSquare getLatestMove() {
        return latestMove;
    }

    public void setLatestMove(LegalMoveSquare latestMove) {
        this.latestMove = latestMove;
    }

    public String getCurrentPlayerColourState() {
        return currentPlayerColourState;
    }

    public void setCurrentPlayerColourState(String currentPlayerColourState) {
        this.currentPlayerColourState = currentPlayerColourState;
    }

    public List<Piece> getAllPieces() {
        return Arrays.stream(squares)
            .flatMap(Arrays::stream)
            .map(Square::getPiece)
            .filter(piece -> !(piece instanceof EmptyPiece))
            .toList();
    }

    public Piece getPieceByName(String name) {
        Square square1 = Arrays.stream(squares).flatMap(Arrays::stream)
            .filter(square -> square.getPiece() != null)
            .filter(square -> Objects.equals(square.getPiece().getName(), name)).toList().stream().findFirst().orElseThrow();

        return square1.getPiece();
    }

    public boolean isPawnPromotionPending() {
        return pawnPromotionPending;
    }

    public void setPawnPromotionPending(boolean pawnPromotionPending) {
        this.pawnPromotionPending = pawnPromotionPending;
    }

    public void setSquares(Square[][] squares) {
        this.squares = squares;
    }

    public Square[][] getSquares() {
        return squares;
    }

    public ChessGameState getStateOfTheGame() {
        return stateOfTheGame;
    }

    public void setStateOfTheGame(ChessGameState stateOfTheGame) {
        this.stateOfTheGame = stateOfTheGame;
    }

    public void movePiece(int currentRow, int currentColumn, int newRow, int newColumn) {
        checkIfGameIsOnGoing();

        Piece pieceToMove = squares[currentRow][currentColumn].getPiece();
        List<LegalMoveSquare> legalMoves = pieceToMove.getLegalMoves();
        String currentPlayersColour = pieceToMove.getColour();

        validateMove(pieceToMove);

        if(isMoveInLegalMoves(legalMoves, newRow, newColumn)) {
            movePieceAction(currentRow, currentColumn, newRow, newColumn, pieceToMove);
            enPassantAndCastlingActions(currentRow, newRow, newColumn, pieceToMove, currentPlayersColour);
            loadPieceLegalMoves(squares);

            List<LegalMoveSquare> squaresTheKingIsInDanger = listOfSquaresWhereOppositeKingIsInDanger(currentPlayersColour, squares);

            if (!squaresTheKingIsInDanger.isEmpty()) {
                clearLegalMovesThatDontProtectTheKingOfAllPiecesApartFromKingWhenItIsInDanger(currentPlayersColour);
                clearCastlingMovesFromKingWhenItIsInDanger(currentPlayersColour);
                checkIfOppositeKingIsInCheckMateOrPlayerIsInStaleMate(currentPlayersColour);
            }
            else {
                removeLegalMovesThatPutKingInCheck(currentPlayersColour);
                removeCastlingMovesWhereKingIsGoingThroughACheckOrEndUpInCheck(currentPlayersColour);
            }

            setOppositeKingsLegalMovesToPreventCheckMateOnItself(currentPlayersColour);
            clearEnPassantForAllPawns(currentPlayersColour);
            checkIfOppositePlayerIsInStaleMate();
            setOppositeColourAsCurrentPlayer();
            checkIfItIsKingsOrRooksFirstMove(pieceToMove);
        }
        else {
            throw new IllegalMoveException("That is not a legal move for a " + pieceToMove.getClass().getSimpleName());
        }
    }

    private void checkIfGameIsOnGoing() {
        if(getStateOfTheGame() != ONGOING) {
            throw new GameIsNotInOnGoingStateException("The game is not in the ongoing state");
        }
    }

    private void validateMove(Piece pieceToMove) {
        checkIfSourceSquareIsEmpty(pieceToMove);
        checkIfSourceSquareHasCurrentPlayersPieceOnIt(pieceToMove);
    }

    private void checkIfSourceSquareIsEmpty(Piece piece) {
        if(piece instanceof EmptyPiece) {
            throw new EmptySourceSquareException("Source square does not have a piece on it");
        }
    }

    private void checkIfSourceSquareHasCurrentPlayersPieceOnIt(Piece pieceToMove) {
        String colour = pieceToMove.getColour();
        if (!Objects.equals(colour, getCurrentPlayerColourState())) {
            throw new WrongColourPieceOnSquareException("Source square does not have your colour piece on it");
        }
    }

    private void movePieceAction(int currentRow, int currentColumn, int newRow, int newColumn, Piece pieceToMove) {
        pieceToMove.setPieceRow(newRow);
        pieceToMove.setPieceColumn(newColumn);
        squares[newRow][newColumn].setPiece(pieceToMove);
        squares[currentRow][currentColumn].setPiece(new EmptyPiece());
        setLatestMove(new LegalMoveSquare(newRow, newColumn));
    }

    private void enPassantAndCastlingActions(int currentRow, int newRow, int newColumn, Piece pieceToMove, String currentPlayersColour) {
        ifMoveIsACastlingMoveAlsoMoveRook(pieceToMove, newRow, newColumn);
        ifMoveIsAnEnPassantMoveRemovePawn(pieceToMove, newRow, newColumn);
        checkIfEnPassantIsAvailableForNextMove(pieceToMove, currentPlayersColour, currentRow, newRow, newColumn);
        checkIfPawnPromotionIsAvailable(pieceToMove, newRow);
    }

    public void promoteAPawn(String newChessPieceType) {
        Square squareWithPawnToPromote = Arrays.stream(squares).flatMap(Arrays::stream)
            .filter(square -> square.getPiece() != null)
            .filter(square -> square.getPiece().getColour().equals(currentPlayerColourState))
            .filter(square -> square.getPiece() instanceof Pawn)
            .filter(square -> {
                if (Objects.equals(currentPlayerColourState, WHITE)) {
                    return square.getPiece().getPieceRow() == 0;
                } else if (Objects.equals(currentPlayerColourState, BLACK)) {
                    return square.getPiece().getPieceRow() == 7;
                }
                return false;
            })
            .toList().stream().findFirst().orElseThrow();

        String name = squareWithPawnToPromote.getPiece().getName() + "To" + newChessPieceType;
        int pieceRow = squareWithPawnToPromote.getPiece().getPieceRow();
        int pieceColumn = squareWithPawnToPromote.getPiece().getPieceColumn();
        String colour = squareWithPawnToPromote.getPiece().getColour();

        if (Objects.equals(newChessPieceType, "Queen")) {
            Queen queen = new Queen(name, pieceRow, pieceColumn, colour);
            squareWithPawnToPromote.setPiece(queen);
        } else if (Objects.equals(newChessPieceType, "Rook")) {
            Rook rook = new Rook(name, pieceRow, pieceColumn, colour);
            squareWithPawnToPromote.setPiece(rook);
        } else if (Objects.equals(newChessPieceType, "Bishop")) {
            Bishop bishop = new Bishop(name, pieceRow, pieceColumn, colour);
            squareWithPawnToPromote.setPiece(bishop);
        } else if (Objects.equals(newChessPieceType, "Knight")) {
            Knight knight = new Knight(name, pieceRow, pieceColumn, colour);
            squareWithPawnToPromote.setPiece(knight);
        } else {
            throw new InvalidChessPieceForPawnPromotionException(newChessPieceType + " is not a valid chess piece");
        }
        setPawnPromotionPending(false);
        loadPieceLegalMoves(squares);
        removeLegalMovesThatPutKingInCheck(currentPlayerColourState);
        checkIfOppositeKingIsInCheckMateOrPlayerIsInStaleMate(currentPlayerColourState);
        setOppositeColourAsCurrentPlayer();
    }

    private void checkIfPawnPromotionIsAvailable(Piece pieceToMove, int newRow) {
        if (pieceToMove instanceof Pawn pawn) {
            checkIfPawnHasMadeItToTheEndOfTheBoard(newRow, pawn);
        }
    }

    private void checkIfPawnHasMadeItToTheEndOfTheBoard(int newRow, Pawn pawn) {
        if ((Objects.equals(pawn.getColour(), WHITE) && newRow == 0) || (Objects.equals(pawn.getColour(), BLACK) && newRow == 7)) {
            setPawnPromotionPending(true);
        }
    }

    private void ifMoveIsAnEnPassantMoveRemovePawn(Piece pieceToMove, int newRow, int newColumn) {
        if(pieceToMove instanceof Pawn pawn && pawn.getEnPassantAvailable() && pawn.getEnPassantMoveToAdd().getRow() == newRow && pawn.getEnPassantMoveToAdd().getColumn() == newColumn) {
            squares[((Pawn) pieceToMove).getEnPassantPieceToRemove().getRow()][((Pawn) pieceToMove).getEnPassantPieceToRemove().getColumn()].setPiece(new EmptyPiece());
        }
    }

    private void clearEnPassantForAllPawns(String currentPlayersColour) {
        Arrays.stream(squares)
            .flatMap(Arrays::stream)
            .filter(square -> square.getPiece().getColour().equals(currentPlayersColour))
            .filter(square -> (square.getPiece() instanceof Pawn))
            .forEach(square -> ((Pawn) square.getPiece()).clearEnPassant());
    }

    private void checkIfEnPassantIsAvailableForNextMove(Piece pieceToMove, String currentPlayersColour, int currentRow, int newRow, int newColumn) {
        if (pieceToMove instanceof Pawn && (currentRow == 1 || currentRow == 6) && (Math.abs(currentRow - newRow) == 2)) {
                List<Piece> piecesToAddEnPassantMovesTo = findPiecesAvailableForEnPassantMove(newRow, newColumn, currentPlayersColour);
                if (newRow == 3) {
                    if (squares[newRow - 1][newColumn].getPiece() instanceof EmptyPiece) {
                        LegalMoveSquare enPassantSquareToAddToLegalMoves = fromSquareToLegalMove(squares[newRow - 1][newColumn]);
                        piecesToAddEnPassantMovesTo.forEach(piece -> {
                            ((EnPassantAvailability) piece).setEnPassantAvailable();
                            ((EnPassantAvailability) piece).setEnPassantMoveToAdd(enPassantSquareToAddToLegalMoves);
                            ((EnPassantAvailability) piece).setEnPassantPieceToRemove(new LegalMoveSquare(newRow, newColumn));

                        });
                    }
                } else if (newRow == 4 && (squares[newRow + 1][newColumn].getPiece() instanceof EmptyPiece)) {
                        LegalMoveSquare enPassantSquareToAddToLegalMoves = fromSquareToLegalMove(squares[newRow + 1][newColumn]);
                        piecesToAddEnPassantMovesTo.forEach(piece -> {
                            ((EnPassantAvailability) piece).setEnPassantAvailable();
                            ((EnPassantAvailability) piece).setEnPassantMoveToAdd(enPassantSquareToAddToLegalMoves);
                            ((EnPassantAvailability) piece).setEnPassantPieceToRemove(new LegalMoveSquare(newRow, newColumn));
                        });
                }
        }
    }

    private List<Piece> findPiecesAvailableForEnPassantMove(int newRow, int newColumn, String currentPlayersColour) {
        List<Piece> piecesToAddEnPassantMovesTo = new ArrayList<>();
        if (newColumn == 0) {
            piecesToAddEnPassantMovesTo.add(squares[newRow][newColumn + 1].getPiece());
        } else if (newColumn == 7) {
            piecesToAddEnPassantMovesTo.add(squares[newRow][newColumn - 1].getPiece());
        } else {
            piecesToAddEnPassantMovesTo.add(squares[newRow][newColumn + 1].getPiece());
            piecesToAddEnPassantMovesTo.add(squares[newRow][newColumn - 1].getPiece());
        }

        return piecesToAddEnPassantMovesTo.stream().filter(piece -> checkIfItIsOppositeColourPawn(piece, currentPlayersColour)).toList();
    }

    private boolean checkIfItIsOppositeColourPawn(Piece piece, String currentPlayersColour) {
        return (piece instanceof Pawn && !Objects.equals(piece.getColour(), currentPlayersColour));
    }

    private void ifMoveIsACastlingMoveAlsoMoveRook(Piece pieceToMove, int newRow, int newColumn) {
        if (pieceToMove instanceof King king && !king.getHasMoved()) {
            if(pieceToMove.getPieceRow() == 7 && newRow == 7 && newColumn == 1) {
                moveRookAsPartOfCastling(7,0,7,2);
            }

            if(pieceToMove.getPieceRow() == 7 && newRow == 7 && newColumn == 5) {
                moveRookAsPartOfCastling(7,7,7,4);
            }

            if(pieceToMove.getPieceRow() == 0 && newRow == 0 && newColumn == 1) {
                moveRookAsPartOfCastling(0,0,0,2);
            }

            if(pieceToMove.getPieceRow() == 0 && newRow == 0 && newColumn == 5) {
                moveRookAsPartOfCastling(0,7,0,4);
            }
        }
    }

    private void moveRookAsPartOfCastling(int rookCurrentRow, int rookCurrentColumn, int rookNewRow, int rookNewColumn) {
        Piece rookToMove = squares[rookCurrentRow][rookCurrentColumn].getPiece();
        movePieceAction(rookCurrentRow, rookCurrentColumn, rookNewRow, rookNewColumn, rookToMove);

    }

    private void removeCastlingMovesWhereKingIsGoingThroughACheckOrEndUpInCheck(String currentPlayersColour) {
        Piece oppositePlayersKingPiece = getOppositePlayersKingPiece(currentPlayersColour);
        List<LegalMoveSquare> allCurrentPlayerMoves = listOfPossibleMovesByCurrentPlayer(currentPlayersColour);

        if(oppositePlayersKingPiece.getPieceRow() == 7 && !((King) oppositePlayersKingPiece).getHasMoved()) {
            removeWhiteCastlingIfKingInCheckOrGoingThroughCheck(oppositePlayersKingPiece, allCurrentPlayerMoves);
        }

        if(oppositePlayersKingPiece.getPieceRow() == 0 && !((King) oppositePlayersKingPiece).getHasMoved()) {
            removeBlackCastlingIfKingInCheckOrGoingThroughCheck(oppositePlayersKingPiece, allCurrentPlayerMoves);
        }
    }

    private void removeWhiteCastlingIfKingInCheckOrGoingThroughCheck(Piece oppositePlayersKingPiece, List<LegalMoveSquare> allCurrentPlayerMoves) {
        boolean castlingWhiteSquare1 = allCurrentPlayerMoves.stream().anyMatch(square -> square.getRow() == 7 && square.getColumn() == 1);
        boolean castlingWhiteSquare2 = allCurrentPlayerMoves.stream().anyMatch(square -> square.getRow() == 7 && square.getColumn() == 2);
        if(castlingWhiteSquare1 || castlingWhiteSquare2) {
            oppositePlayersKingPiece.removeLegalMove(7,1);
        }
        boolean castlingWhiteSquare3 = allCurrentPlayerMoves.stream().anyMatch(square -> square.getRow() == 7 && square.getColumn() == 4);
        boolean castlingWhiteSquare4 = allCurrentPlayerMoves.stream().anyMatch(square -> square.getRow() == 7 && square.getColumn() == 5);
        if(castlingWhiteSquare3 || castlingWhiteSquare4) {
            oppositePlayersKingPiece.removeLegalMove(7,5);
        }
    }

    private static void removeBlackCastlingIfKingInCheckOrGoingThroughCheck(Piece oppositePlayersKingPiece, List<LegalMoveSquare> allCurrentPlayerMoves) {
        boolean castlingBlackSquare1 = allCurrentPlayerMoves.stream().anyMatch(square -> square.getRow() == 0 && square.getColumn() == 1);
        boolean castlingBlackSquare2 = allCurrentPlayerMoves.stream().anyMatch(square -> square.getRow() == 0 && square.getColumn() == 2);
        if(castlingBlackSquare1 || castlingBlackSquare2) {
            oppositePlayersKingPiece.removeLegalMove(0,1);
        }

        boolean castlingBlackSquare3 = allCurrentPlayerMoves.stream().anyMatch(square -> square.getRow() == 0 && square.getColumn() == 4);
        boolean castlingBlackSquare4 = allCurrentPlayerMoves.stream().anyMatch(square -> square.getRow() == 0 && square.getColumn() == 5);

        if(castlingBlackSquare3 || castlingBlackSquare4) {
            oppositePlayersKingPiece.removeLegalMove(0,5);
        }
    }

    private Piece getOppositePlayersKingPiece(String currentPlayersColour) {
        return Arrays.stream(squares).flatMap(Arrays::stream)
            .filter(square -> square.getPiece() != null)
            .filter(square -> !square.getPiece().getColour().equals(currentPlayersColour))
            .filter(square -> square.getPiece() instanceof King)
            .toList().stream().findFirst().orElseThrow().getPiece();
    }

    private void checkIfItIsKingsOrRooksFirstMove(Piece pieceToMove) {
        if(pieceToMove instanceof CastlingHasMoved piece && (!piece.getHasMoved())) {
            piece.hasMoved();
        }
    }

    private void setOppositeColourAsCurrentPlayer() {
        if (!isPawnPromotionPending()) {
            if (Objects.equals(getCurrentPlayerColourState(), WHITE)) {
                setCurrentPlayerColourState(BLACK);
            } else if (Objects.equals(getCurrentPlayerColourState(), BLACK)) {
                setCurrentPlayerColourState(WHITE);
            }
        }
    }

    private void loadPieceLegalMoves(Square[][] squares) {
        Arrays.stream(squares).forEach(pieces -> Arrays.stream(pieces).forEach(square -> square.getPiece().generateLegalMoves(squares)));
    }

    private void removeLegalMovesThatPutKingInCheck(String colour) {
        List<MovesDto> movesDtoList = Arrays.stream(squares)
            .flatMap(Arrays::stream)
            .map(Square::getPiece)
            .filter(piece -> !piece.getColour().equals(colour))
            .filter(piece -> piece.getLegalMoves() != null)
            .filter(piece -> !piece.getLegalMoves().isEmpty())
            .map(piece -> new MovesDto(
                piece.getName(),
                findLegalOwnMovesThatCheckKing(piece, colour)
            ))
            .filter(movesDto -> !movesDto.moves().isEmpty())
            .toList();

        movesDtoList
            .forEach(movesDto -> getPieceByName(movesDto.pieceName()).getLegalMoves().forEach(square -> {
                if(movesDto.moves().contains(square)){
                    getPieceByName(movesDto.pieceName()).removeLegalMove(square.getRow(), square.getColumn());
                }
            }));
    }

    private List<LegalMoveSquare> findLegalOwnMovesThatCheckKing(Piece piece, String colour) {

        return piece.getLegalMoves().parallelStream().filter(square -> {

            List<LegalMoveSquare> listOfSquaresWhereKingIsInDanger = simulateAMoveOnTheBoard(piece, colour, square);

            return !listOfSquaresWhereKingIsInDanger.isEmpty();
        }).toList();
    }

    private List<LegalMoveSquare> simulateAMoveOnTheBoard(Piece piece, String colour, LegalMoveSquare square) {
        Board boardClone = SerializationUtils.clone(this);
        Square[][] cloneSquares = boardClone.getSquares();
        Piece pieceClone = SerializationUtils.clone(piece);
        Piece enPassantRemovedPiece = null;

        if (pieceClone instanceof Pawn pawn && pawn.getEnPassantAvailable() && pawn.getEnPassantMoveToAdd().getRow() == square.getRow() && pawn.getEnPassantMoveToAdd().getColumn() == square.getColumn()) {
            enPassantRemovedPiece = SerializationUtils.clone(cloneSquares[pawn.getEnPassantPieceToRemove().getRow()][pawn.getEnPassantPieceToRemove().getColumn()].getPiece());
            cloneSquares[pawn.getEnPassantPieceToRemove().getRow()][pawn.getEnPassantPieceToRemove().getColumn()].clearPiece();
        }

        int currentPieceRow = pieceClone.getPieceRow();
        int currentPieceColumn = pieceClone.getPieceColumn();
        Piece clearedSquarePiece = SerializationUtils.clone(cloneSquares[pieceClone.getPieceRow()][pieceClone.getPieceColumn()].getPiece());
        Piece movedOnToPiece = SerializationUtils.clone(cloneSquares[square.getRow()][square.getColumn()].getPiece());

        cloneSquares[square.getRow()][square.getColumn()].setPiece(pieceClone);
        cloneSquares[pieceClone.getPieceRow()][pieceClone.getPieceColumn()].clearPiece();
        pieceClone.setPieceRow(square.getRow());
        pieceClone.setPieceColumn(square.getColumn());

        boardClone.loadPieceLegalMoves(cloneSquares);
        List<LegalMoveSquare> listOfSquaresWhereKingIsInDanger = listOfSquaresWhereOppositeKingIsInDanger(colour, cloneSquares);

        cloneSquares[square.getRow()][square.getColumn()].setPiece(movedOnToPiece);
        cloneSquares[clearedSquarePiece.getPieceRow()][clearedSquarePiece.getPieceColumn()].setPiece(clearedSquarePiece);
        pieceClone.setPieceRow(currentPieceRow);
        pieceClone.setPieceColumn(currentPieceColumn);

        if (enPassantRemovedPiece != null) {
            cloneSquares[enPassantRemovedPiece.getPieceRow()][enPassantRemovedPiece.getPieceColumn()].setPiece(enPassantRemovedPiece);
        }
        boardClone.loadPieceLegalMoves(cloneSquares);
        return listOfSquaresWhereKingIsInDanger;
    }

    private boolean isMoveInLegalMoves(List<LegalMoveSquare> legalMoves, int newRow, int newColumn) {
        return legalMoves.stream().anyMatch(square -> square.getRow() == newRow && square.getColumn() == newColumn);
    }

    private List<LegalMoveSquare> listOfSquaresWhereOppositeKingIsInDanger(String colour, Square[][] squares) {
        return Arrays.stream(squares)
            .flatMap(Arrays::stream)
            .filter(square -> square.getPiece().getColour().equals(colour))
            .filter(square -> square.getPiece().getLegalMoves() != null)
            .flatMap(square -> square.getPiece().getLegalMoves().stream())
            .filter(square -> square.getPiece() instanceof King)
            .toList();
    }

    private void clearLegalMovesThatDontProtectTheKingOfAllPiecesApartFromKingWhenItIsInDanger(String colour) {

        List<MovesDto> movesDtoList = movesThatCanSaveTheKingFromCheckmate(colour);

        Arrays.stream(squares)
            .flatMap(Arrays::stream)
            .filter(square -> !square.getPiece().getColour().equals(colour))
            .filter(square -> square.getPiece().getLegalMoves() != null)
            .forEach(square -> square.getPiece().clearLegalMoves());

        movesDtoList
            .forEach(movesDto -> getPieceByName(movesDto.pieceName()).setLegalMoves(movesDto.moves()));
    }

    private List<MovesDto> movesThatCanSaveTheKingFromCheckmate(String colour) {
        return Arrays.stream(squares)
            .flatMap(Arrays::stream)
            .map(Square::getPiece)
            .filter(piece -> !piece.getColour().equals(colour))
            .filter(piece -> piece.getLegalMoves() != null)
            .filter(piece -> !piece.getLegalMoves().isEmpty())
            .map(piece -> new MovesDto(
                piece.getName(),
                findLegalOppositePlayerMovesThatSaveKing(piece, colour)
            ))
            .filter(movesDto -> !movesDto.moves().isEmpty())
            .toList();
    }

    private List<LegalMoveSquare> findLegalOppositePlayerMovesThatSaveKing(Piece piece, String colour) {

        return piece.getLegalMoves().parallelStream().filter(square -> {

            List<LegalMoveSquare> listOfSquaresWhereKingIsInDanger = simulateAMoveOnTheBoard(piece, colour, square);

            return listOfSquaresWhereKingIsInDanger.isEmpty();
            }).toList();

    }

    private void clearCastlingMovesFromKingWhenItIsInDanger(String colour) {
        Arrays.stream(squares)
            .flatMap(Arrays::stream)
            .filter(square -> !square.getPiece().getColour().equals(colour))
            .filter(square -> square.getPiece() instanceof King && !((CastlingHasMoved) square.getPiece()).getHasMoved())
            .forEach(square -> ((King) square.getPiece()).removeCastlingMoves());
    }

    private void setOppositeKingsLegalMovesToPreventCheckMateOnItself(String colour) {
        Piece kingPiece = getOppositePlayersKingPiece(colour);

        List<LegalMoveSquare> listOfPossibleMovesByNextPlayer = listOfPossibleMovesByCurrentPlayer(colour);
        List<LegalMoveSquare> pawnStraightMoves = pawnStraightMoves(colour);
        List<LegalMoveSquare> pawnDiagonalLegalMovesWhereKingCannotGo = pawnDiagonalMoves(colour);

        List<LegalMoveSquare> listOfPossibleMovesByNextPlayerWithoutPawnStraightMoves = ListUtils.subtract(listOfPossibleMovesByNextPlayer, pawnStraightMoves);
        List<LegalMoveSquare> listOfPossibleMovesByNextPlayerWithoutPawnStraightMovesAndWithPawnDiagonalMoves = ListUtils.union(listOfPossibleMovesByNextPlayerWithoutPawnStraightMoves, pawnDiagonalLegalMovesWhereKingCannotGo);

        List<LegalMoveSquare> kingLegalMoves = kingPiece.getLegalMoves();

        List<LegalMoveSquare> kingLegalMovesWithoutDanger = ListUtils.subtract(kingLegalMoves, listOfPossibleMovesByNextPlayerWithoutPawnStraightMovesAndWithPawnDiagonalMoves);

        kingPiece.setLegalMoves(kingLegalMovesWithoutDanger);
    }

    private List<LegalMoveSquare> listOfPossibleMovesByCurrentPlayer(String colour) {
        return Arrays.stream(squares)
            .flatMap(Arrays::stream)
            .filter(square -> square.getPiece().getColour().equals(colour))
            .filter(square -> square.getPiece().getLegalMoves() != null)
            .flatMap(square -> square.getPiece().getLegalMoves().stream())
            .toList();
    }

    private List<LegalMoveSquare> listOfPossibleMovesByNextPlayer(String colour) {
        return Arrays.stream(squares)
            .flatMap(Arrays::stream)
            .filter(square -> !square.getPiece().getColour().equals(colour))
            .filter(square -> square.getPiece().getLegalMoves() != null)
            .flatMap(square -> square.getPiece().getLegalMoves().stream())
            .toList();
    }

    private List<LegalMoveSquare> pawnStraightMoves(String colour) {
        return Arrays.stream(squares)
            .flatMap(Arrays::stream)
            .map(Square::getPiece)
            .filter(piece -> piece.getColour().equals(colour))
            .filter(piece -> piece.getLegalMoves()!=null)
            .filter(Pawn.class::isInstance)
            .flatMap(piece -> ((Pawn) piece).generateStraightLegalMoves().stream())
            .toList();
    }

    private List<LegalMoveSquare> pawnDiagonalMoves(String colour) {
        return Arrays.stream(squares)
            .flatMap(Arrays::stream)
            .map(Square::getPiece)
            .filter(piece -> piece.getColour().equals(colour))
            .filter(piece -> piece.getLegalMoves()!=null)
            .filter(Pawn.class::isInstance)
            .flatMap(piece -> ((Pawn) piece).getDiagonalLegalMovesToPreventTheKingFromGoingIntoCheckMate(squares).stream())
            .toList();
    }

    private void checkIfOppositeKingIsInCheckMateOrPlayerIsInStaleMate(String currentPlayersColour) {
        Piece oppositePlayersKingPiece = getOppositePlayersKingPiece(currentPlayersColour);

        boolean kingInCheck = listOfPossibleMovesByCurrentPlayer(currentPlayerColourState).stream()
            .anyMatch(legalMoveSquare -> legalMoveSquare.getPiece() instanceof King);

        if(oppositePlayersKingPiece.getLegalMoves().isEmpty() && listOfPossibleMovesByNextPlayer(currentPlayerColourState).isEmpty() && kingInCheck) {
            setStateOfTheGame(CHECKMATE);
        } else if (oppositePlayersKingPiece.getLegalMoves().isEmpty() && listOfPossibleMovesByNextPlayer(currentPlayerColourState).isEmpty() && !kingInCheck) {
            setStateOfTheGame(STALEMATE);
        }
    }

    private void checkIfOppositePlayerIsInStaleMate() {
        boolean kingInCheck = listOfPossibleMovesByCurrentPlayer(currentPlayerColourState).stream()
            .anyMatch(legalMoveSquare -> legalMoveSquare.getPiece() instanceof King);

        if (listOfPossibleMovesByNextPlayer(currentPlayerColourState).isEmpty() && !kingInCheck && getStateOfTheGame() != CHECKMATE) {
            setStateOfTheGame(STALEMATE);
        }
    }

    private void makePreInitialisationMoves(List<ChessMoveRequest> chessMoveRequestList) {
        chessMoveRequestList.forEach(chessMoveDto -> {
            Piece pieceToMove = squares[chessMoveDto.currentRow()][chessMoveDto.currentColumn()].getPiece();
            pieceToMove.setPieceColumn(chessMoveDto.newColumn());
            pieceToMove.setPieceRow(chessMoveDto.newRow());
            squares[chessMoveDto.newRow()][chessMoveDto.newColumn()].setPiece(pieceToMove);
            squares[chessMoveDto.currentRow()][chessMoveDto.currentColumn()].setPiece(new EmptyPiece());
        });
    }
}

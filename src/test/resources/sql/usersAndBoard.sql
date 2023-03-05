INSERT INTO public.chess_game_user (cgb_app_user_id, last_login, password, username) VALUES ('test-user-data-id1', null, '$2a$10$0gJXCjduBg9FgnvFm8E7I.VWOejHp7J/Xpa/DMLu9xENiOm61FUxS', 'test_user1');
INSERT INTO public.chess_game_user (cgb_app_user_id, last_login, password, username) VALUES ('test-user-data-id2', null, '$2a$10$0gJXCjduBg9FgnvFm8E7I.VWOejHp7J/Xpa/DMLu9xENiOm61FUxS', 'test_user2');

INSERT INTO public.chess_game_board (cgb_id, cgb_current_player_colour, cgb_current_player_number, cgb_squares, cgb_game_state, cgb_winner_user_id) VALUES ('test-data-id01', 'white', '{"playerOneAppUserId": "test-user-data-id1", "playerTwoAppUserId": null, "activePlayerUsername": "test_user1", "playerOneAppUsername": "test_user1", "playerTwoAppUsername": null}', '[[{"row": 0, "piece": {"name": "Black Rook1", "type": "rook", "colour": "black", "hasMoved": false, "pieceRow": 0, "legalMoves": [], "pieceColumn": 0}, "colour": "white", "column": 0}, {"row": 0, "piece": {"name": "Black Knight1", "type": "knight", "colour": "black", "pieceRow": 0, "legalMoves": [{"row": 2, "piece": {"name": null, "type": "emptypiece", "colour": "empty", "pieceRow": 0, "legalMoves": [], "pieceColumn": 0}, "column": 0}, {"row": 2, "piece": {"name": null, "type": "emptypiece", "colour": "empty", "pieceRow": 0, "legalMoves": [], "pieceColumn": 0}, "column": 2}], "pieceColumn": 1}, "colour": "black", "column": 1}, {"row": 0, "piece": {"name": "Black Bishop1", "type": "bishop", "colour": "black", "pieceRow": 0, "legalMoves": [], "pieceColumn": 2}, "colour": "white", "column": 2}, {"row": 0, "piece": {"name": "Black King", "type": "king", "colour": "black", "hasMoved": false, "pieceRow": 0, "legalMoves": [], "pieceColumn": 3}, "colour": "black", "column": 3}, {"row": 0, "piece": {"name": "Black Queen", "type": "queen", "colour": "black", "pieceRow": 0, "legalMoves": [], "pieceColumn": 4}, "colour": "white", "column": 4}, {"row": 0, "piece": {"name": "Black Bishop2", "type": "bishop", "colour": "black", "pieceRow": 0, "legalMoves": [], "pieceColumn": 5}, "colour": "black", "column": 5}, {"row": 0, "piece": {"name": "Black Knight2", "type": "knight", "colour": "black", "pieceRow": 0, "legalMoves": [{"row": 2, "piece": {"name": null, "type": "emptypiece", "colour": "empty", "pieceRow": 0, "legalMoves": [], "pieceColumn": 0}, "column": 5}, {"row": 2, "piece": {"name": null, "type": "emptypiece", "colour": "empty", "pieceRow": 0, "legalMoves": [], "pieceColumn": 0}, "column": 7}], "pieceColumn": 6}, "colour": "white", "column": 6}, {"row": 0, "piece": {"name": "Black Rook2", "type": "rook", "colour": "black", "hasMoved": false, "pieceRow": 0, "legalMoves": [], "pieceColumn": 7}, "colour": "black", "column": 7}], [{"row": 1, "piece": {"name": "Black Pawn1", "type": "pawn", "colour": "black", "pieceRow": 1, "legalMoves": [{"row": 2, "piece": {"name": null, "type": "emptypiece", "colour": "empty", "pieceRow": 0, "legalMoves": [], "pieceColumn": 0}, "column": 0}, {"row": 3, "piece": {"name": null, "type": "emptypiece", "colour": "empty", "pieceRow": 0, "legalMoves": [], "pieceColumn": 0}, "column": 0}], "pieceColumn": 0}, "colour": "white", "column": 0}, {"row": 1, "piece": {"name": "Black Pawn2", "type": "pawn", "colour": "black", "pieceRow": 1, "legalMoves": [{"row": 2, "piece": {"name": null, "type": "emptypiece", "colour": "empty", "pieceRow": 0, "legalMoves": [], "pieceColumn": 0}, "column": 1}, {"row": 3, "piece": {"name": null, "type": "emptypiece", "colour": "empty", "pieceRow": 0, "legalMoves": [], "pieceColumn": 0}, "column": 1}], "pieceColumn": 1}, "colour": "black", "column": 1}, {"row": 1, "piece": {"name": "Black Pawn3", "type": "pawn", "colour": "black", "pieceRow": 1, "legalMoves": [{"row": 2, "piece": {"name": null, "type": "emptypiece", "colour": "empty", "pieceRow": 0, "legalMoves": [], "pieceColumn": 0}, "column": 2}, {"row": 3, "piece": {"name": null, "type": "emptypiece", "colour": "empty", "pieceRow": 0, "legalMoves": [], "pieceColumn": 0}, "column": 2}], "pieceColumn": 2}, "colour": "white", "column": 2}, {"row": 1, "piece": {"name": "Black Pawn4", "type": "pawn", "colour": "black", "pieceRow": 1, "legalMoves": [{"row": 2, "piece": {"name": null, "type": "emptypiece", "colour": "empty", "pieceRow": 0, "legalMoves": [], "pieceColumn": 0}, "column": 3}, {"row": 3, "piece": {"name": null, "type": "emptypiece", "colour": "empty", "pieceRow": 0, "legalMoves": [], "pieceColumn": 0}, "column": 3}], "pieceColumn": 3}, "colour": "black", "column": 3}, {"row": 1, "piece": {"name": "Black Pawn5", "type": "pawn", "colour": "black", "pieceRow": 1, "legalMoves": [{"row": 2, "piece": {"name": null, "type": "emptypiece", "colour": "empty", "pieceRow": 0, "legalMoves": [], "pieceColumn": 0}, "column": 4}, {"row": 3, "piece": {"name": null, "type": "emptypiece", "colour": "empty", "pieceRow": 0, "legalMoves": [], "pieceColumn": 0}, "column": 4}], "pieceColumn": 4}, "colour": "white", "column": 4}, {"row": 1, "piece": {"name": "Black Pawn6", "type": "pawn", "colour": "black", "pieceRow": 1, "legalMoves": [{"row": 2, "piece": {"name": null, "type": "emptypiece", "colour": "empty", "pieceRow": 0, "legalMoves": [], "pieceColumn": 0}, "column": 5}, {"row": 3, "piece": {"name": null, "type": "emptypiece", "colour": "empty", "pieceRow": 0, "legalMoves": [], "pieceColumn": 0}, "column": 5}], "pieceColumn": 5}, "colour": "black", "column": 5}, {"row": 1, "piece": {"name": "Black Pawn7", "type": "pawn", "colour": "black", "pieceRow": 1, "legalMoves": [{"row": 2, "piece": {"name": null, "type": "emptypiece", "colour": "empty", "pieceRow": 0, "legalMoves": [], "pieceColumn": 0}, "column": 6}, {"row": 3, "piece": {"name": null, "type": "emptypiece", "colour": "empty", "pieceRow": 0, "legalMoves": [], "pieceColumn": 0}, "column": 6}], "pieceColumn": 6}, "colour": "white", "column": 6}, {"row": 1, "piece": {"name": "Black Pawn8", "type": "pawn", "colour": "black", "pieceRow": 1, "legalMoves": [{"row": 2, "piece": {"name": null, "type": "emptypiece", "colour": "empty", "pieceRow": 0, "legalMoves": [], "pieceColumn": 0}, "column": 7}, {"row": 3, "piece": {"name": null, "type": "emptypiece", "colour": "empty", "pieceRow": 0, "legalMoves": [], "pieceColumn": 0}, "column": 7}], "pieceColumn": 7}, "colour": "black", "column": 7}], [{"row": 2, "piece": {"name": null, "type": "emptypiece", "colour": "empty", "pieceRow": 0, "legalMoves": [], "pieceColumn": 0}, "colour": "white", "column": 0}, {"row": 2, "piece": {"name": null, "type": "emptypiece", "colour": "empty", "pieceRow": 0, "legalMoves": [], "pieceColumn": 0}, "colour": "black", "column": 1}, {"row": 2, "piece": {"name": null, "type": "emptypiece", "colour": "empty", "pieceRow": 0, "legalMoves": [], "pieceColumn": 0}, "colour": "white", "column": 2}, {"row": 2, "piece": {"name": null, "type": "emptypiece", "colour": "empty", "pieceRow": 0, "legalMoves": [], "pieceColumn": 0}, "colour": "black", "column": 3}, {"row": 2, "piece": {"name": null, "type": "emptypiece", "colour": "empty", "pieceRow": 0, "legalMoves": [], "pieceColumn": 0}, "colour": "white", "column": 4}, {"row": 2, "piece": {"name": null, "type": "emptypiece", "colour": "empty", "pieceRow": 0, "legalMoves": [], "pieceColumn": 0}, "colour": "black", "column": 5}, {"row": 2, "piece": {"name": null, "type": "emptypiece", "colour": "empty", "pieceRow": 0, "legalMoves": [], "pieceColumn": 0}, "colour": "white", "column": 6}, {"row": 2, "piece": {"name": null, "type": "emptypiece", "colour": "empty", "pieceRow": 0, "legalMoves": [], "pieceColumn": 0}, "colour": "black", "column": 7}], [{"row": 3, "piece": {"name": null, "type": "emptypiece", "colour": "empty", "pieceRow": 0, "legalMoves": [], "pieceColumn": 0}, "colour": "white", "column": 0}, {"row": 3, "piece": {"name": null, "type": "emptypiece", "colour": "empty", "pieceRow": 0, "legalMoves": [], "pieceColumn": 0}, "colour": "black", "column": 1}, {"row": 3, "piece": {"name": null, "type": "emptypiece", "colour": "empty", "pieceRow": 0, "legalMoves": [], "pieceColumn": 0}, "colour": "white", "column": 2}, {"row": 3, "piece": {"name": null, "type": "emptypiece", "colour": "empty", "pieceRow": 0, "legalMoves": [], "pieceColumn": 0}, "colour": "black", "column": 3}, {"row": 3, "piece": {"name": null, "type": "emptypiece", "colour": "empty", "pieceRow": 0, "legalMoves": [], "pieceColumn": 0}, "colour": "white", "column": 4}, {"row": 3, "piece": {"name": null, "type": "emptypiece", "colour": "empty", "pieceRow": 0, "legalMoves": [], "pieceColumn": 0}, "colour": "black", "column": 5}, {"row": 3, "piece": {"name": null, "type": "emptypiece", "colour": "empty", "pieceRow": 0, "legalMoves": [], "pieceColumn": 0}, "colour": "white", "column": 6}, {"row": 3, "piece": {"name": null, "type": "emptypiece", "colour": "empty", "pieceRow": 0, "legalMoves": [], "pieceColumn": 0}, "colour": "black", "column": 7}], [{"row": 4, "piece": {"name": null, "type": "emptypiece", "colour": "empty", "pieceRow": 0, "legalMoves": [], "pieceColumn": 0}, "colour": "white", "column": 0}, {"row": 4, "piece": {"name": null, "type": "emptypiece", "colour": "empty", "pieceRow": 0, "legalMoves": [], "pieceColumn": 0}, "colour": "black", "column": 1}, {"row": 4, "piece": {"name": null, "type": "emptypiece", "colour": "empty", "pieceRow": 0, "legalMoves": [], "pieceColumn": 0}, "colour": "white", "column": 2}, {"row": 4, "piece": {"name": null, "type": "emptypiece", "colour": "empty", "pieceRow": 0, "legalMoves": [], "pieceColumn": 0}, "colour": "black", "column": 3}, {"row": 4, "piece": {"name": null, "type": "emptypiece", "colour": "empty", "pieceRow": 0, "legalMoves": [], "pieceColumn": 0}, "colour": "white", "column": 4}, {"row": 4, "piece": {"name": null, "type": "emptypiece", "colour": "empty", "pieceRow": 0, "legalMoves": [], "pieceColumn": 0}, "colour": "black", "column": 5}, {"row": 4, "piece": {"name": null, "type": "emptypiece", "colour": "empty", "pieceRow": 0, "legalMoves": [], "pieceColumn": 0}, "colour": "white", "column": 6}, {"row": 4, "piece": {"name": null, "type": "emptypiece", "colour": "empty", "pieceRow": 0, "legalMoves": [], "pieceColumn": 0}, "colour": "black", "column": 7}], [{"row": 5, "piece": {"name": null, "type": "emptypiece", "colour": "empty", "pieceRow": 0, "legalMoves": [], "pieceColumn": 0}, "colour": "white", "column": 0}, {"row": 5, "piece": {"name": null, "type": "emptypiece", "colour": "empty", "pieceRow": 0, "legalMoves": [], "pieceColumn": 0}, "colour": "black", "column": 1}, {"row": 5, "piece": {"name": null, "type": "emptypiece", "colour": "empty", "pieceRow": 0, "legalMoves": [], "pieceColumn": 0}, "colour": "white", "column": 2}, {"row": 5, "piece": {"name": null, "type": "emptypiece", "colour": "empty", "pieceRow": 0, "legalMoves": [], "pieceColumn": 0}, "colour": "black", "column": 3}, {"row": 5, "piece": {"name": null, "type": "emptypiece", "colour": "empty", "pieceRow": 0, "legalMoves": [], "pieceColumn": 0}, "colour": "white", "column": 4}, {"row": 5, "piece": {"name": null, "type": "emptypiece", "colour": "empty", "pieceRow": 0, "legalMoves": [], "pieceColumn": 0}, "colour": "black", "column": 5}, {"row": 5, "piece": {"name": null, "type": "emptypiece", "colour": "empty", "pieceRow": 0, "legalMoves": [], "pieceColumn": 0}, "colour": "white", "column": 6}, {"row": 5, "piece": {"name": null, "type": "emptypiece", "colour": "empty", "pieceRow": 0, "legalMoves": [], "pieceColumn": 0}, "colour": "black", "column": 7}], [{"row": 6, "piece": {"name": "White Pawn1", "type": "pawn", "colour": "white", "pieceRow": 6, "legalMoves": [{"row": 4, "piece": {"name": null, "type": "emptypiece", "colour": "empty", "pieceRow": 0, "legalMoves": [], "pieceColumn": 0}, "column": 0}, {"row": 5, "piece": {"name": null, "type": "emptypiece", "colour": "empty", "pieceRow": 0, "legalMoves": [], "pieceColumn": 0}, "column": 0}], "pieceColumn": 0}, "colour": "white", "column": 0}, {"row": 6, "piece": {"name": "White Pawn2", "type": "pawn", "colour": "white", "pieceRow": 6, "legalMoves": [{"row": 4, "piece": {"name": null, "type": "emptypiece", "colour": "empty", "pieceRow": 0, "legalMoves": [], "pieceColumn": 0}, "column": 1}, {"row": 5, "piece": {"name": null, "type": "emptypiece", "colour": "empty", "pieceRow": 0, "legalMoves": [], "pieceColumn": 0}, "column": 1}], "pieceColumn": 1}, "colour": "black", "column": 1}, {"row": 6, "piece": {"name": "White Pawn3", "type": "pawn", "colour": "white", "pieceRow": 6, "legalMoves": [{"row": 4, "piece": {"name": null, "type": "emptypiece", "colour": "empty", "pieceRow": 0, "legalMoves": [], "pieceColumn": 0}, "column": 2}, {"row": 5, "piece": {"name": null, "type": "emptypiece", "colour": "empty", "pieceRow": 0, "legalMoves": [], "pieceColumn": 0}, "column": 2}], "pieceColumn": 2}, "colour": "white", "column": 2}, {"row": 6, "piece": {"name": "White Pawn4", "type": "pawn", "colour": "white", "pieceRow": 6, "legalMoves": [{"row": 4, "piece": {"name": null, "type": "emptypiece", "colour": "empty", "pieceRow": 0, "legalMoves": [], "pieceColumn": 0}, "column": 3}, {"row": 5, "piece": {"name": null, "type": "emptypiece", "colour": "empty", "pieceRow": 0, "legalMoves": [], "pieceColumn": 0}, "column": 3}], "pieceColumn": 3}, "colour": "black", "column": 3}, {"row": 6, "piece": {"name": "White Pawn5", "type": "pawn", "colour": "white", "pieceRow": 6, "legalMoves": [{"row": 4, "piece": {"name": null, "type": "emptypiece", "colour": "empty", "pieceRow": 0, "legalMoves": [], "pieceColumn": 0}, "column": 4}, {"row": 5, "piece": {"name": null, "type": "emptypiece", "colour": "empty", "pieceRow": 0, "legalMoves": [], "pieceColumn": 0}, "column": 4}], "pieceColumn": 4}, "colour": "white", "column": 4}, {"row": 6, "piece": {"name": "White Pawn6", "type": "pawn", "colour": "white", "pieceRow": 6, "legalMoves": [{"row": 4, "piece": {"name": null, "type": "emptypiece", "colour": "empty", "pieceRow": 0, "legalMoves": [], "pieceColumn": 0}, "column": 5}, {"row": 5, "piece": {"name": null, "type": "emptypiece", "colour": "empty", "pieceRow": 0, "legalMoves": [], "pieceColumn": 0}, "column": 5}], "pieceColumn": 5}, "colour": "black", "column": 5}, {"row": 6, "piece": {"name": "White Pawn7", "type": "pawn", "colour": "white", "pieceRow": 6, "legalMoves": [{"row": 4, "piece": {"name": null, "type": "emptypiece", "colour": "empty", "pieceRow": 0, "legalMoves": [], "pieceColumn": 0}, "column": 6}, {"row": 5, "piece": {"name": null, "type": "emptypiece", "colour": "empty", "pieceRow": 0, "legalMoves": [], "pieceColumn": 0}, "column": 6}], "pieceColumn": 6}, "colour": "white", "column": 6}, {"row": 6, "piece": {"name": "White Pawn8", "type": "pawn", "colour": "white", "pieceRow": 6, "legalMoves": [{"row": 4, "piece": {"name": null, "type": "emptypiece", "colour": "empty", "pieceRow": 0, "legalMoves": [], "pieceColumn": 0}, "column": 7}, {"row": 5, "piece": {"name": null, "type": "emptypiece", "colour": "empty", "pieceRow": 0, "legalMoves": [], "pieceColumn": 0}, "column": 7}], "pieceColumn": 7}, "colour": "black", "column": 7}], [{"row": 7, "piece": {"name": "White Rook1", "type": "rook", "colour": "white", "hasMoved": false, "pieceRow": 7, "legalMoves": [], "pieceColumn": 0}, "colour": "white", "column": 0}, {"row": 7, "piece": {"name": "White Knight1", "type": "knight", "colour": "white", "pieceRow": 7, "legalMoves": [{"row": 5, "piece": {"name": null, "type": "emptypiece", "colour": "empty", "pieceRow": 0, "legalMoves": [], "pieceColumn": 0}, "column": 0}, {"row": 5, "piece": {"name": null, "type": "emptypiece", "colour": "empty", "pieceRow": 0, "legalMoves": [], "pieceColumn": 0}, "column": 2}], "pieceColumn": 1}, "colour": "black", "column": 1}, {"row": 7, "piece": {"name": "White Bishop1", "type": "bishop", "colour": "white", "pieceRow": 7, "legalMoves": [], "pieceColumn": 2}, "colour": "white", "column": 2}, {"row": 7, "piece": {"name": "White King", "type": "king", "colour": "white", "hasMoved": false, "pieceRow": 7, "legalMoves": [], "pieceColumn": 3}, "colour": "black", "column": 3}, {"row": 7, "piece": {"name": "White Queen", "type": "queen", "colour": "white", "pieceRow": 7, "legalMoves": [], "pieceColumn": 4}, "colour": "white", "column": 4}, {"row": 7, "piece": {"name": "White Bishop2", "type": "bishop", "colour": "white", "pieceRow": 7, "legalMoves": [], "pieceColumn": 5}, "colour": "black", "column": 5}, {"row": 7, "piece": {"name": "White Knight2", "type": "knight", "colour": "white", "pieceRow": 7, "legalMoves": [{"row": 5, "piece": {"name": null, "type": "emptypiece", "colour": "empty", "pieceRow": 0, "legalMoves": [], "pieceColumn": 0}, "column": 5}, {"row": 5, "piece": {"name": null, "type": "emptypiece", "colour": "empty", "pieceRow": 0, "legalMoves": [], "pieceColumn": 0}, "column": 7}], "pieceColumn": 6}, "colour": "white", "column": 6}, {"row": 7, "piece": {"name": "White Rook2", "type": "rook", "colour": "white", "hasMoved": false, "pieceRow": 7, "legalMoves": [], "pieceColumn": 7}, "colour": "black", "column": 7}]]', 'ONGOING', null);

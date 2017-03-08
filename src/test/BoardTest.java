package test;

import main.Board;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class BoardTest {

    private static final String BLANK_BOARD = "[ ][ ][ ]\n[ ][ ][ ]\n[ ][ ][ ]";

    private static final String X_MARKED_BOARD = "[ ][ ][ ]\n[X][ ][ ]\n[ ][ ][ ]";

    private static final String X_O_MARKED_BOARD = "[ ][ ][ ]\n[X][ ][ ]\n[ ][ ][O]";

    @Test
    public void check_board_is_blank() {
        Board board = new Board();

        Assertions.assertEquals(BLANK_BOARD, board.toString());
    }

    @Test
    public void check_board_is_marked_with_an_X_in_2_1() {
        Board board = new Board();
        board.markAsXAt(2, 1);

        Assertions.assertEquals(X_MARKED_BOARD, board.toString());
    }

    @Test
    public void check_board_is_marked_with_an_X_in_2_1_and_O_in_3_3(){
        Board board = new Board();
        board.markAsXAt(2, 1);
        board.markAsOAt(3,3);

        Assertions.assertEquals(X_O_MARKED_BOARD, board.toString());
    }

    @Test
    public void check_game_not_finished(){
        Board board = new Board();
        board.markAsXAt(1, 1);
        board.markAsOAt(1, 2);
        board.markAsOAt(1, 3);
        board.markAsOAt(2, 1);
        board.markAsOAt(2, 2);
        board.markAsXAt(2, 3);
        board.markAsXAt(3, 1);
        board.markAsXAt(3, 2);

        Assertions.assertEquals(false, board.isFinished());
    }

    @Test
    public void check_game_is_finished_no_winner(){
        Board board = new Board();
        board.markAsXAt(1, 1);
        board.markAsOAt(1, 2);
        board.markAsOAt(1, 3);
        board.markAsOAt(2, 1);
        board.markAsOAt(2, 2);
        board.markAsXAt(2, 3);
        board.markAsXAt(3, 1);
        board.markAsXAt(3, 2);
        board.markAsOAt(3, 3);

        Assertions.assertEquals(true, board.isFinished());
    }

    @Test
    public void check_game_is_finished_row(){
        Board board = new Board();
        board.markAsXAt(1, 1);
        board.markAsXAt(1, 2);
        board.markAsXAt(1, 3);

        Assertions.assertEquals(true, board.isFinished());
    }

    @Test
    public void check_game_is_finished_column(){
        Board board = new Board();
        board.markAsXAt(1, 2);
        board.markAsXAt(2, 2);
        board.markAsXAt(3, 2);

        Assertions.assertEquals(true, board.isFinished());
    }

    @Test
    public void check_game_is_finished_diagonal(){
        Board board = new Board();
        board.markAsXAt(1, 1);
        board.markAsXAt(2, 2);
        board.markAsXAt(3, 3);

        Assertions.assertEquals(true, board.isFinished());
    }

}

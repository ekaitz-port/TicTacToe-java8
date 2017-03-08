package main;

import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Board {

    private static final int ROWS = 3;
    private static final int COLUMNS = 3;

    private Cell[][] cells;

    public Board(){
        this.cells = new Cell[ROWS][COLUMNS];
        initialize();
    }

    private void initialize() {
        /*
        for(int i = 0; i < ROWS; i++) {
            for(int j = 0; j < COLUMNS; j++) {
                this.cells[i][j] = new Cell();
            }
        }*/

        this.cells = IntStream.range(0, ROWS) //creamos a manija un stream de ints de 0->3 (idem a un bucle for)
                .mapToObj(rowNum -> initColumns()) //por cada row, inicializa las columnas
                .toArray(Cell[][]::new); //inicializa la matriz de celdas
    }

    private Cell[] initColumns() {
        //idem al initialize, pero en lugar de con una matriz, con un vector
        return IntStream.range(0, COLUMNS)
                .mapToObj(colNum -> new Cell())
                .toArray(Cell[]::new);
    }

    public void markAsXAt(int row, int column) {
        this.cells[row-1][column-1].markAsX();
    }

    public void markAsOAt(int row, int column) {
        this.cells[row-1][column-1].markAsO();
    }

    public String toString(){
        /*
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < ROWS; i++) {
            for(int j = 0; j < COLUMNS; j++) {
                sb.append(this.cells[i][j].toString());
            }
            if(i != ROWS - 1) {sb.append("\n");}
        }

        return sb.toString();*/

        return Stream.of(cells) //Arrays.Stream(cells)
                .map(this::printRow) // row = Cell[]. Por cada Cell[] pintamos la row entera
                .collect(Collectors.joining("\n")); //joineamos el resultado de cada row separado por \n
    }

    private String printRow(Cell[] row) {
        return Stream.of(row) //cogemos cada Cell de la row
                .map(Cell::toString) //pintamos la Cell
                .collect(Collectors.joining()); //unimos el resultado
    }

    public boolean isFinished() {
        return allCellsMarked()
                || hasAnyWinnerRow()
                || hasAnyWinnerColumn()
                || hasAnyWinnerDiagonal();
    }

    private boolean allCellsMarked() {
        return Stream.of(cells)
                .parallel()
                .flatMap(Stream::of) //aplanamos todas las filas en un stream (row -> Stream.of(row))
                .allMatch(Cell::isMarked); //y si todas estan marcadas, fin de la partida
    }

    private boolean hasAnyWinnerRow() {
        return hasAnyWinnerRow(this.cells);
    }

    private boolean hasAnyWinnerColumn() {
        return hasAnyWinnerRow(transpose());
    }

    private boolean hasAnyWinnerDiagonal() {
        return hasAnyWinnerRow(extractDiagonals());
    }

    private boolean hasAnyWinnerRow(Cell[][] cells) {
        return Stream.of(cells)
                .anyMatch(this::isWinnerRow);
    }

    public boolean isWinnerRow(Cell[] row) {
        //hay algo mejor que un or?????

        return Stream.of(row).allMatch(Cell::isMarkedAsX) ||
                Stream.of(row).allMatch(Cell::isMarkedAsO);
    }

    private Cell[][] transpose() {
        /*
        Cell[][] transposedCells = new Cell[ROWS][COLUMNS];
        for(int i = 0; i < ROWS; i ++) {
            for(int j = 0; j < COLUMNS; j++) {
                transposedCells[j][i] = cells[i][j];
            }
        }
        return transposedCells;
        */

        return IntStream.range(0, ROWS) //creamos a manija un stream de ints de 0->3 (idem a un bucle for)
                .mapToObj(rowNum -> IntStream.range(0, COLUMNS)
                        .mapToObj(colNum -> this.cells[colNum][rowNum])
                        .toArray(Cell[]::new)) //por cada row, inicializa las columnas
                .toArray(Cell[][]::new);
    }

    private Cell[][] extractDiagonals() {
        Cell[][] diagonals = new Cell[2][3];

        diagonals[0][0] = this.cells[0][0];
        diagonals[0][1] = this.cells[1][1];
        diagonals[0][2] = this.cells[2][2];

        diagonals[1][0] = this.cells[0][2];
        diagonals[1][1] = this.cells[1][1];
        diagonals[1][2] = this.cells[2][0];

        return diagonals;
    }

    public Mark winner() {
        return null;
    }
}
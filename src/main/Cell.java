package main;

public class Cell {

    private Mark mark;

    public Cell() {
        this.mark = Mark.BLANK;
    }

    public void markAsX() {
        this.mark = Mark.X;
    }

    public void markAsO() {
        this.mark = Mark.O;
    }

    public String toString() {
        return "[" + this.mark.toString() + "]";
    }

    public boolean isMarked() {
        return this.mark != Mark.BLANK;
    }

    public boolean isMarkedAsX() {
        return this.mark == Mark.X;
    }

    public boolean isMarkedAsO() {
        return this.mark == Mark.O;
    }
}

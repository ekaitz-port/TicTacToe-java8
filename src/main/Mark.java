package main;

public enum Mark {

    X, O, BLANK;

    @Override
    public String toString() {
        if(this == BLANK) return " ";

        return super.toString();
    }

}

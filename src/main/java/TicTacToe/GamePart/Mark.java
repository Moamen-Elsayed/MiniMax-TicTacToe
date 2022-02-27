package TicTacToe.GamePart;

public enum Mark {
    X('X'),
    O('O'),
    BLANK(' ');

    private final char mark;

    Mark(char initMark){
        this.mark = initMark;
    }

    public boolean isMarked(){
        return this != BLANK;
    }

    public char getMark(){
        return this.mark;
    }

    public String toString(){
        return String.valueOf(mark);
    }
}

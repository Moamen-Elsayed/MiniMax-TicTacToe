package TicTacToe.GamePart;

public class Board {

    private final Mark[][] board;
    private Mark winningMark;
    private final int BOARD_WIDTH = 3;
    private boolean crossTurn, gameOver;
    private int availableMoves = BOARD_WIDTH * BOARD_WIDTH;

    public Board(){
        board = new Mark[BOARD_WIDTH][BOARD_WIDTH];
        crossTurn = true;
        gameOver = false;
        winningMark = Mark.BLANK;
        initializeBoard();
    }

    private void initializeBoard(){
        for(int row=0 ; row<BOARD_WIDTH ; row++)
            for(int col=0 ; col<BOARD_WIDTH ; col++)
                board[row][col] = Mark.BLANK;
    }

    public boolean placeMark(int row, int col){
        if(row < 0 || col < 0 || row >= BOARD_WIDTH || col >= BOARD_WIDTH || isTileMarked(row, col) || gameOver){
            return false;
        }

        availableMoves--;
        board[row][col] = crossTurn ? Mark.X : Mark.O;
        togglePlayer();
        checkWin(row, col);
        return true;
    }

    private void checkWin(int row, int column){
        int rowSum = 0;
        for(int c=0 ; c<BOARD_WIDTH ; c++)
            rowSum += getMarkAt(row, c).getMark();
        if(calcWinner(rowSum) != Mark.BLANK){
            System.out.println(winningMark + " wins on row " + row);
            return;
        }

        rowSum = 0;
        for(int r=0 ; r<BOARD_WIDTH ; r++)
            rowSum += getMarkAt(r, column).getMark();
        if(calcWinner(rowSum) != Mark.BLANK){
            System.out.println(winningMark + " wins on row " + column);
            return;
        }

        rowSum = 0;
        for(int i=0 ; i<BOARD_WIDTH ; i++)
            rowSum += getMarkAt(i, i).getMark();
        if(calcWinner(rowSum) != Mark.BLANK){
            System.out.println(winningMark + " wins on the top-left to bottom-right diagonal. ");
            return;
        }

        rowSum = 0;
        int indexMax = BOARD_WIDTH - 1;
        for(int i=0 ; i<=indexMax ; i++)
            rowSum += getMarkAt(i, indexMax-i).getMark();
        if(calcWinner(rowSum) != Mark.BLANK){
            System.out.println(winningMark + " wins on the top-right to bottom-left diagonal. ");
            return;
        }

        if(!anyMovesAvailable()){
            gameOver = true;
            System.out.println("Tie!");
        }
    }

    private Mark calcWinner(int rowSum){
        int Xwin = Mark.X.getMark() * BOARD_WIDTH;
        int Owin = Mark.O.getMark() * BOARD_WIDTH;

        if(rowSum == Xwin){
            gameOver = true;
            winningMark = Mark.X;
            return Mark.X;
        }else if(rowSum == Owin){
            gameOver = true;
            winningMark = Mark.O;
            return Mark.O;
        }
        return Mark.BLANK;
    }

    public void togglePlayer(){
        crossTurn = !crossTurn;
    }

    public boolean anyMovesAvailable(){
        return availableMoves > 0;
    }

    public Mark getMarkAt(int row, int column){
        return board[row][column];
    }

    public boolean isTileMarked(int row, int column){
        return board[row][column].isMarked();
    }

    public void setMarkAt(int row, int column, Mark newMark){
        board[row][column] = newMark;
    }

    public String toString(){
        StringBuilder strBldr = new StringBuilder();
        for(Mark[] row : board){
            for(Mark tile : row)
                strBldr.append(tile).append(' ');
            strBldr.append('\n');
        }
        return strBldr.toString();
    }

    public boolean isCrossTurn(){
        return crossTurn;
    }

    public int getWidth(){
        return BOARD_WIDTH;
    }

    public boolean isGameOver(){
        return gameOver;
    }

    public Mark getWinningMark(){
        return winningMark;
    }
}

package settings;

public class Settings {

    private BoardDimensions boardDimensions;
    private int winningCondition;

    public Settings(BoardDimensions boardDimensions, int winningCondition){
        this.boardDimensions = boardDimensions;
        this.winningCondition = winningCondition;
    }

    public BoardDimensions getBoardDimensions() {
        return boardDimensions;
    }

    public int getWinningCondition() {
        return winningCondition;
    }

    public int getBoardElementsCount() { return boardDimensions.getWidth() * boardDimensions.getHeight(); }


}

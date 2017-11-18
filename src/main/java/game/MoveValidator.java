package game;

import board.Board;

public class MoveValidator {
    private final Board board;

    public MoveValidator(Board board) {
        this.board = board;
    }

    public boolean moveIsValid(int fieldNumber){
        boolean moveIsValid = true;
        if(fieldNumber >= board.getElementsCount()){
            throw new IndexOutOfBoundsException("Field number "
                    + fieldNumber
                    + " is greater than number of last field"
                    + board.getElementsCount());
        }

        if(!board.isFieldEmpty(fieldNumber)){
            throw new RuntimeException("Field number " + fieldNumber + " is already marked");
        }
        return moveIsValid;
    }
}

import java.util.List;

public class MoveSupervisor {

    private final Board board;
    private int movesCount;
    private final MoveValidator moveValidator;

    public MoveSupervisor(Board board) {
        this.board = board;
        this.moveValidator = new MoveValidator(board);
    }

    public void move(String mark, int fieldNumber){
        if (moveValidator.moveIsValid(fieldNumber)) {
            if (mark.equals("o")) {
                board.setNought(fieldNumber);
                movesCount++;
            } else if (mark.equals("x")) {
                board.setCross(fieldNumber);
                movesCount++;
            } else {
                throw new RuntimeException("Mark : " + mark + " is no valid");
            }
        }
    }

    public boolean isFreeMoveExists(){
        if(movesCount < board.getElementsCount()){
            return true;
        }else{
            return false;
        }
    }

}

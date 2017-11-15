public class MoveSupervisor {

    private final Board board;
    private int movesCount;
    private final MoveValidator moveValidator;
    private final int winningCondition;
    private final MovesRegistry movesRegistry;

    public MoveSupervisor(Board board, int winningCondition, MovesRegistry movesRegistry) {
        this.board = board;
        this.moveValidator = new MoveValidator(board);
        this.winningCondition = winningCondition;
        this.movesRegistry = movesRegistry;
    }

    public void move(String mark, int fieldNumber){
        if (moveValidator.moveIsValid(fieldNumber)) {
            movesRegistry.addMove(fieldNumber, mark);
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

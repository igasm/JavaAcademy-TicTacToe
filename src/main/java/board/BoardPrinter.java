package board;

import game.MovesRegistry;
import game.Observer;
import settings.BoardDimensions;

import java.util.function.Consumer;

public class BoardPrinter implements Observer {

    private final Board board;
    private final BoardDimensions boardDimensions;
    private final Consumer<String> consoleWriter;

    public BoardPrinter(Board board, BoardDimensions boardDimensions, Consumer<String> consoleWriter) {
        this.board = board;
        this.boardDimensions = boardDimensions;
        this.consoleWriter = consoleWriter;
    }

    public void printBord(){
        consoleWriter.accept(board.toString());
    }

    public void printBoardWithMoves(MovesRegistry movesRegistry){
        StringBuilder stringBuilder = new StringBuilder();
        String newline = System.getProperty("line.separator");
        for(int i = 0; i < boardDimensions.getElementsCount(); i++){
            if(movesRegistry.moveExists(i)){
                stringBuilder.append("[" + String.format("%3s", movesRegistry.getMove(i)) + "]");
            }else {
                stringBuilder.append("[" + String.format("%3d", board.getFieldNumber(i)) + "]");
            }
            if ((i+1)%boardDimensions.getWidth() == 0){
                stringBuilder.append(newline);
            }
        }
        consoleWriter.accept(stringBuilder.toString().toString().trim());
    }

    @Override
    public void update(MovesRegistry movesRegistry) {
        printBoardWithMoves(movesRegistry);
    }
}

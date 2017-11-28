package game.board;

import game.engine.move.MovesRegistry;
import game.io.Writer;
import game.settings.BoardDimensions;

public class BoardPrinter {

    private final Board board;
    private final BoardDimensions boardDimensions;
    private final Writer consoleWriter;

    public BoardPrinter(Board board, BoardDimensions boardDimensions, Writer consoleWriter) {
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

}

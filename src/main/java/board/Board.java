package board;

import settings.BoardDimensions;

import java.util.List;
import java.util.function.Consumer;

public class Board{

    private List<Integer> board; //pola zawsze interfejsami!!!
    private BoardDimensions boardDimensions;
    private final Consumer<String> consoleWriter;

    public Board(List<Integer> board, BoardDimensions boardDimensions, Consumer<String> consoleWriter) {
       this.board = board;
       this.boardDimensions = boardDimensions;
       this.consoleWriter = consoleWriter;
    }

    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        String newline = System.getProperty("line.separator");
        for(int i = 0; i < board.size(); i++){
            stringBuilder.append("[" + String.format("%3d", board.get(i)) + "]");
            if ((i+1)%boardDimensions.getWidth() == 0){
                stringBuilder.append(newline);
            }
        }
        return stringBuilder.toString().toString().trim();
    }

    public int getElementsCount(){
        return boardDimensions.getElementsCount();
    }

    public int getWidth(){
        return boardDimensions.getWidth();
    }

    public int getFieldNumber(int i) { return board.get(i); }
}

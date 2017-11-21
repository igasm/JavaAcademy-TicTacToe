package board;

import settings.BoardDimensions;

import java.util.List;
import java.util.function.Consumer;

public class Board{

    private List<Integer> board; //pola zawsze interfejsami!!!
    private BoardDimensions boardDimensions;

    public Board(List<Integer> board, BoardDimensions boardDimensions) {
       this.board = board;
       this.boardDimensions = boardDimensions;
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

    public int getFieldNumber(int i) { return board.get(i); }
}

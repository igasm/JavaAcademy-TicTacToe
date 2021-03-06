package game.board;

import game.settings.BoardDimensions;

import java.util.List;

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
            stringBuilder.append("[").append(String.format("%3d", board.get(i))).append("]");
            if ((i+1)%boardDimensions.getWidth() == 0){
                stringBuilder.append(newline);
            }
        }
        return stringBuilder.toString().trim();
    }

    int getFieldNumber(int i) { return board.get(i); }
}

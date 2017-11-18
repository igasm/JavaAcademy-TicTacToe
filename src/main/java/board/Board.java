package board;

import game.MovesRegistry;
import game.Observer;
import settings.BoardDimensions;

import java.util.List;
import java.util.function.Consumer;

public class Board implements Observer {

    private List<Integer> boardWithFieldNumbers; //pola zawsze interfejsami!!!
    private List<String> boardWithMarks; //pola zawsze interfejsami!!!
    private BoardDimensions boardDimensions;
    private final Consumer<String> consoleWriter;

    public Board(List<Integer> boardWithFieldNumbers, List<String> boardWithMarks, BoardDimensions boardDimensions, Consumer<String> consoleWriter) {
       this.boardWithFieldNumbers = boardWithFieldNumbers;
       this.boardWithMarks = boardWithMarks;
       this.boardDimensions = boardDimensions;
        this.consoleWriter = consoleWriter;
    }

    public void clearMarks(){
        for(String mark : boardWithMarks){
            mark = "e";
        }
    }



    //TODO: jak to się różni od toString?
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < boardWithFieldNumbers.size(); i++){
            stringBuilder.append("|");
            if (boardWithMarks.get(i).equals("e")) {
                stringBuilder.append(boardWithFieldNumbers.get(i));
                if (i<10){
                    stringBuilder.append(" ");
                }
            } else {
                stringBuilder.append(boardWithMarks.get(i) + " ");
            }
            if ((i+1)%boardDimensions.getWidth() == 0){
                stringBuilder.append("|\n"); //TODO: czy /n działa tak samo na wszystkich systemach operacyjnych?
            }
        }
        return stringBuilder.toString().toString().trim();
    }

    public void setCross(int fieldNumber){
        boardWithMarks.set(fieldNumber, "x");
    }

    public void setNought(int fieldNumber){
        boardWithMarks.set(fieldNumber, "o");
    }

    public boolean isFieldEmpty(int fieldNumber){
        return boardWithMarks.get(fieldNumber).toUpperCase().trim().equals("E");

//        if(boardWithMarks.get(fieldNumber).equals("e")){ //TODO: czy porównanie Stringów z equals jest odpowiednie? "e".equals("E")?
//            return true;
//        }else{
//            return false;
//        } // if warunek return true else return false => return warunek?
    }

    public int getElementsCount(){
        return boardDimensions.getElementsCount();
    }

    public int getWidth(){
        return boardDimensions.getWidth();
    }

    public List<String> getBoardWithMarks() {
        return boardWithMarks;
    }

    @Override
    public void update(MovesRegistry movesRegistry) {
        clearMarks();
        for (int i = 0; i < boardWithFieldNumbers.size(); i++) {
            if (movesRegistry.moveExists(i)) {
                boardWithMarks.set(i, movesRegistry.getMove(i));
            }
        }
        consoleWriter.accept(toString());
    }
}

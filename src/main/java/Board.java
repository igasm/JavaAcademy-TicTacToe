import java.util.List;

public class Board {

    private List<Integer> boardWithFieldNumbers; //pola zawsze interfejsami!!!
    private List<String> boardWithMarks; //pola zawsze interfejsami!!!
    private BoardDimensions boardDimensions;


    public Board(List<Integer> boardWithFieldNumbers, List<String> boardWithMarks, BoardDimensions boardDimensions) {
       this.boardWithFieldNumbers = boardWithFieldNumbers;
       this.boardWithMarks = boardWithMarks;
       this.boardDimensions = boardDimensions;
    }

    public String getView(){
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i< boardWithFieldNumbers.size(); i++){
            if(boardWithMarks.get(i).equals("e")) {
                stringBuilder.append(boardWithFieldNumbers.get(i));
            }else{
                stringBuilder.append(boardWithMarks.get(i));
            }
            if((i+1)%boardDimensions.getWidth() == 0){
                stringBuilder.append("\n");
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
        if(boardWithMarks.get(fieldNumber).equals("e")){
            return true;
        }else{
            return false;
        }
    }

    public int getElementsCount(){
        return boardDimensions.getElementsCount();
    }

}

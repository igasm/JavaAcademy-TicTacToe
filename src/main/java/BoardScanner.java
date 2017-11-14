import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BoardScanner {
    private final Board board;


    public BoardScanner(Board board) {
        this.board = board;
    }

    public List<Sequence> scanAllDirections(int fieldNumber){
        List<Sequence> sequences = new ArrayList<>();
//        sequences.add(scanFromLeftTopToRightBottom(fieldNumber));
        sequences.add(scanHorizontally(fieldNumber));
        sequences.add(scanVertically(fieldNumber));
//        sequences.add(scanFromLeftBottomToRightTop(fieldNumber));
        return sequences;
    }

    public Sequence scanVertically(int fieldNumber){
        int startIndex = (int) Math.floor(fieldNumber/board.getWidth()) * board.getWidth();
        int lastIndex = startIndex + board.getWidth();
        List<String> line = board.getBoardWithMarks().subList(startIndex, lastIndex);
        return new Sequence(line);
    }

    public Sequence scanHorizontally(int fieldNumber){
        int rowNumber = (int) Math.floor(fieldNumber/board.getWidth());
        int startIndex = fieldNumber - board.getWidth() * rowNumber;
        List<String> column = new ArrayList<>();
        for(int i=startIndex; i<board.getElementsCount(); i+=board.getWidth()){
            column.add(board.getBoardWithMarks().get(i));
        }
        return new Sequence(column);
    }

    public Sequence scanFromLeftTopToRightBottom(int fieldNumber){
        LinkedList<String> diagonal = new LinkedList<>();
        int interval = board.getWidth() + 1;
        boolean executeSecondLoop = true;
        for(int i=fieldNumber; i>=0; i-=interval){
            diagonal.addFirst(board.getBoardWithMarks().get(i));
            //warunki brzegowe
            if(i%board.getWidth() == 0) {
                break;
            }
        }
        if(fieldNumber%board.getWidth() == (board.getWidth()-1)){
            executeSecondLoop = false;
        }
        if(executeSecondLoop) {
            for (int i = fieldNumber + interval; i < board.getElementsCount(); i += interval) {
                diagonal.addLast(board.getBoardWithMarks().get(i));
                if (i % board.getWidth() == (board.getWidth()-1)){
                    break;
                }
            }
        }
        return new Sequence(diagonal);
    }

    public Sequence scanFromLeftBottomToRightTop(int fieldNumber){
        int rowNumber = (int) Math.floor(fieldNumber/board.getWidth());
        int startIndex = fieldNumber - rowNumber*(board.getWidth()-1);
        List<String> diagonal = new ArrayList<>();
        for(int i=startIndex; i<board.getElementsCount()-1; i+=board.getWidth()-1){
            diagonal.add(board.getBoardWithMarks().get(i));
        }
        return new Sequence(diagonal);
    }


}

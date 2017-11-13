import java.util.ArrayList;
import java.util.List;

public class BoardScanner {
    private final Board board;


    public BoardScanner(Board board) {
        this.board = board;
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
        int rowNumber = (int) Math.floor(fieldNumber/board.getWidth());
        int startIndex = fieldNumber - ((board.getWidth()+1)*rowNumber);
        List<String> diagonal = new ArrayList<>();
        for(int i=startIndex; i<board.getElementsCount(); i+=board.getWidth()+1){
            diagonal.add(board.getBoardWithMarks().get(i));
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

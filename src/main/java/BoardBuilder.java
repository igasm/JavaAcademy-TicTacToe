import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class BoardBuilder {
    private final BoardDimensions boardDimensions;


    public BoardBuilder(BoardDimensions boardDimensions) {
        this.boardDimensions = boardDimensions;
    }

    List<Integer> buildBoardWithFieldNumbers(){
        List<Integer> board = IntStream.range(0, boardDimensions.getElementsCount()).mapToObj(a->new Integer(a)).collect(Collectors.toList());
        return board;
    }

    List<String> buildBoardWithMarks(){
        List<String> board = Stream.generate(() -> new String("e")).limit(boardDimensions.getElementsCount()).collect(Collectors.toList());
        return board;
    }
}

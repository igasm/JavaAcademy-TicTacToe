package board;

import settings.BoardDimensions;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class BoardBuilder {
    private final BoardDimensions boardDimensions;


    public BoardBuilder(BoardDimensions boardDimensions) {
        this.boardDimensions = boardDimensions;
    }

    public List<Integer> buildBoardWithFieldNumbers(){
        List<Integer> board = IntStream.range(0, boardDimensions.getElementsCount()).mapToObj(a->new Integer(a)).collect(Collectors.toList());
        return board;
    }

    public List<String> buildBoardWithMarks(){
        List<String> board = Stream.generate(() -> new String("e")).limit(boardDimensions.getElementsCount()).collect(Collectors.toList());
        return board;
    }
}
